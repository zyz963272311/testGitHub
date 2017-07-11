package com.liiwin.http;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
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
public class HttpClientUtil
{
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
			System.out.println("RequestConfig初始化配置");
			SSLContextBuilder builder = new SSLContextBuilder();
			builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
			//配置同时支持http与https
			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create().register(//
					"http", PlainConnectionSocketFactory.getSocketFactory()).register(//
					"https", sslsf).build();
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
			System.out.println("RequestConfig初始化结束");
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

	public static CloseableHttpClient getHttpClient()
	{
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(pool)//设置连接池管理
				.setDefaultRequestConfig(requestConfig)//设置请求配置
				.setRetryHandler(new DefaultHttpRequestRetryHandler(0, false))//设置重试次数
				.build();
		return httpClient;
	}

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
		} finally
		{
		}
		return null;
	}
}
