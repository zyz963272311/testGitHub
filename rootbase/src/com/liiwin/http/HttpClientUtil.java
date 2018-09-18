package com.liiwin.http;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： 模拟发送HTTP请求</p>
 * <p>功能： </p>
 * <p>所属模块： ROOTBASE</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年7月10日 下午2:27:15</p>
 * <p>类全名：com.liiwin.http.HttpClient</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@SuppressWarnings("deprecation")
public class HttpClientUtil
{
	private static Logger								logger					= LoggerFactory.getLogger(HttpClientUtil.class);
	/**编码格式utf-8*/
	public static final String							CHARSET_UTF_8			= "utf-8";
	/**HTTP内容类型*/
	public static final String							CONTENT_TYPE_TEXT_HTML	= "text/xml";
	/**HTTP内容类型，相当于form表单的形式提交数据*/
	public static final String							CONTENT_TYPE_FORM_URL	= "application/x-www-form-urlencoded";
	/**HTTP内容类型，相当于JSON形式提交数据*/
	public static final String							CONTENT_TYPE_JSON_URL	= "application/json;charset=utf-8";
	/**连接管理器*/
	private static PoolingHttpClientConnectionManager	pool;
	/**请求配置*/
	private static RequestConfig						requestConfig;
	static
	{
		try
		{
			logger.error("RequestConfig初始化配置");
			SSLContextBuilder builder = new SSLContextBuilder();
			builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
			//配置同时支持http与https
			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
					.register(//
							"http", PlainConnectionSocketFactory.getSocketFactory())
					.register(//
							"https", sslsf)
					.build();
			//初始化连接管理器
			pool = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
			//设置最大连接数
			pool.setMaxTotal(200);
			//设置最大路由
			pool.setDefaultMaxPerRoute(2);
			int socketTimeout = 10000;
			int connectTimeout = 10000;
			int connectionRequestTimeout = 10000;
			requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectionRequestTimeout).setSocketTimeout(socketTimeout)
					.setConnectTimeout(connectTimeout).build();
			logger.error("RequestConfig初始化结束");
		} catch (NoSuchAlgorithmException e)
		{
			throw new RuntimeException("报错内容", e);
		} catch (KeyStoreException e)
		{
			throw new RuntimeException("报错内容", e);
		} catch (KeyManagementException e)
		{
			throw new RuntimeException("报错内容", e);
		}
		requestConfig = RequestConfig.custom().setSocketTimeout(50000).setConnectTimeout(50000).setConnectionRequestTimeout(50000).build();
	}

	/**
	 * 获取一个httpClient
	 * @return
	 * 赵玉柱
	 */
	public static CloseableHttpClient getHttpClient()
	{
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(pool)//设置连接池管理
				.setDefaultRequestConfig(requestConfig)//设置请求配置
				.setRetryHandler(new DefaultHttpRequestRetryHandler(0, false))//设置重试次数
				.build();
		return httpClient;
	}

	/**
	 * 发送一个post请求
	 * @param httpPost
	 * @return
	 * 赵玉柱
	 */
	private static String sendHttpPost(HttpPost httpPost)
	{
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		String responseContent = null;
		try
		{
			//创建默认的httpcliuent实例
			httpClient = getHttpClient();
			httpPost.setConfig(requestConfig);
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (response.getStatusLine().getStatusCode() >= 300)
			{
				throw new Exception("HTTP Request is not success, Response code is " + response.getStatusLine().getStatusCode());
			}
			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode())
			{
				responseContent = EntityUtils.toString(entity, CHARSET_UTF_8);
				EntityUtils.consume(entity);
			}
		} catch (ClientProtocolException e)
		{
			throw new RuntimeException("报错内容", e);
		} catch (IOException e)
		{
			throw new RuntimeException("报错内容", e);
		} catch (Exception e)
		{
			throw new RuntimeException("报错内容", e);
		} finally
		{
			try
			{
				if (response != null)
				{
					response.close();
				}
			} catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
		return responseContent;
	}

	/**
	 * 发送一个get请求
	 * @param httpGet
	 * @return
	 * 赵玉柱
	 */
	private static String sendHttpGet(HttpGet httpGet)
	{
		CloseableHttpResponse response = null;
		CloseableHttpClient httpClient = null;
		String responseContent = null;
		try
		{
			httpClient = getHttpClient();
			httpGet.setConfig(requestConfig);
			response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if (response.getStatusLine().getStatusCode() >= 300)
			{
				throw new Exception("HTTP Request is not success, Response code is " + response.getStatusLine().getStatusCode());
			}
			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode())
			{
				responseContent = EntityUtils.toString(entity, CHARSET_UTF_8);
				EntityUtils.consume(entity);
			}
		} catch (ClientProtocolException e)
		{
			throw new RuntimeException("报错内容", e);
		} catch (IOException e)
		{
			throw new RuntimeException("报错内容", e);
		} catch (Exception e)
		{
			throw new RuntimeException("报错内容", e);
		} finally
		{
			try
			{
				if (response != null)
				{
					response.close();
				}
			} catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
		return responseContent;
	}

	/**
	 * 发送一个post请求
	 * @param httpUrl
	 * @return
	 * 赵玉柱
	 */
	public static String sendHttpPost(String httpUrl)
	{
		HttpPost httpPost = new HttpPost(httpUrl);
		return sendHttpPost(httpPost);
	}

	/**
	 * 发送一个get请求
	 * @param httpUrl
	 * @return
	 * 赵玉柱
	 */
	public static String sendHttpGet(String httpUrl)
	{
		HttpGet httpGet = new HttpGet(httpUrl);
		return sendHttpGet(httpGet);
	}

	/**
	 * 发送一个post请求，带文件
	 * @param httpUrl
	 * @param maps 参数
	 * @param fileLists 文件列表
	 * @return
	 * 赵玉柱
	 */
	public static String sendHttpPost(String httpUrl, Map<String,String> maps, List<File> fileLists)
	{
		HttpPost httpPost = new HttpPost(httpUrl);
		MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();
		if (maps != null && !maps.isEmpty())
		{
			Set<String> keys = maps.keySet();
			for (String key : keys)
			{
				meBuilder.addPart(key, new StringBody(maps.get(key), ContentType.TEXT_PLAIN));
			}
		}
		if (fileLists != null && !fileLists.isEmpty())
		{
			for (File file : fileLists)
			{
				FileBody fileBody = new FileBody(file);
				meBuilder.addPart("files", fileBody);
			}
		}
		HttpEntity reqEntity = meBuilder.build();
		httpPost.setEntity(reqEntity);
		return sendHttpPost(httpPost);
	}

	/**
	 * 发送一个post请求
	 * @param httpUrl
	 * @param params 参数格式 key1=value1&key2=value2
	 * @return
	 * 赵玉柱
	 */
	public static String sendHttpPost(String httpUrl, String params)
	{
		HttpPost httpPost = new HttpPost(httpUrl);
		try
		{
			if (StrUtil.isNoStrTrimNull(params))
			{
				StringEntity stringEntity = new StringEntity(params, CHARSET_UTF_8);
				stringEntity.setContentType(CONTENT_TYPE_FORM_URL);
				httpPost.setEntity(stringEntity);
			}
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
		return sendHttpPost(httpPost);
	}

	/**
	 * 发送一个post请求
	 * @param httpUrl
	 * @param params 请求参数
	 * @return
	 * 赵玉柱
	 */
	public static String sendHttpPost(String httpUrl, Map<String,String> params)
	{
		String param = convertStringParamter(params);
		return sendHttpPost(httpUrl, param);
	}

	/**
	 * 发送一个post请求，发送json数据
	 * @param httpUrl
	 * @param jsonParams json格式的参数
	 * @return
	 * 赵玉柱
	 */
	public static String sendHttpPostJson(String httpUrl, String jsonParams)
	{
		HttpPost httpPost = new HttpPost(httpUrl);
		try
		{
			if (StrUtil.isNoStrTrimNull(jsonParams))
			{
				StringEntity stringEntity = new StringEntity(jsonParams, CHARSET_UTF_8);
				stringEntity.setContentType(CONTENT_TYPE_JSON_URL);
				httpPost.setEntity(stringEntity);
			}
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
		return sendHttpPost(httpPost);
	}

	/**
	 * 发送post请求
	 * @param httpUrl
	 * @param xmlParams xml格式的参数
	 * @return
	 * 赵玉柱
	 */
	public static String sendHttpPostXml(String httpUrl, String xmlParams)
	{
		HttpPost httpPost = new HttpPost(httpUrl);
		try
		{
			if (StrUtil.isNoStrTrimNull(xmlParams))
			{
				StringEntity stringEntity = new StringEntity(xmlParams, CHARSET_UTF_8);
				stringEntity.setContentType(CONTENT_TYPE_TEXT_HTML);
				httpPost.setEntity(stringEntity);
			}
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
		return sendHttpPost(httpPost);
	}

	/**
	 * 将map键值对转换成key1=value1&key2=value2的形式
	 * @param map
	 * @return
	 * 赵玉柱
	 */
	public static String convertStringParamter(Map<String,String> map)
	{
		StringBuilder result = new StringBuilder();
		if (map != null && !map.isEmpty())
		{
			Iterator<String> iterator = map.keySet().iterator();
			String key = "";
			String value = "";
			while (iterator.hasNext())
			{
				key = iterator.next();
				value = StrUtil.obj2str(map.get(key), "");
				result.append(key).append("=").append(value);
				if (iterator.hasNext())
				{
					result.append("&");
				}
			}
		}
		return result.toString();
	}

	/**
	 * 测试方法
	 * @param args
	 * 赵玉柱
	 */
	public static void main(String[] args)
	{
		String httpUrl = "https://wx.qq.com/";
		System.out.println(sendHttpGet(httpUrl));
		String httpUrl1 = "https://wx.qq.com/";
		System.out.println(sendHttpPost(httpUrl1));
	}
}
