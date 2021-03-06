package xyz.zyzhu.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import xyz.zyzhu.pojo.BasRequestPojo;
import xyz.zyzhu.pojo.BasResponsePojo;
import xyz.zyzhu.pojo.HelloRequestPojo;
import xyz.zyzhu.pojo.HelloResponsePojo;
import com.alibaba.fastjson.JSON;
import com.liiwin.config.BasConfig;
/**
 * <p>标题： 调用接口的客户端类</p>
 * <p>功能：</p>
 * <p>所属模块： 调用WebService接口</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: zyzhu.xyz</p>
 * <p>创建日期：2017年8月11日 下午2:46:11</p>
 * <p>类全名：xyz.zyzhu.utils.SpringOpenApiClient</p>
 * 作者：赵玉柱 
 * 初审： 
 * 复审： 
 * 监听使用界面:
 * 
 * @version 8.0
 */
public class SpringOpenApiClient
{
	private static String	httpurl	= null;
	static
	{
		httpurl = BasConfig.getPropertie("project-Test-Spring-001") + "/api/executeApi";
	}

	/**
	 * 
	 */
	public SpringOpenApiClient()
	{
	}

	@SuppressWarnings("unchecked")
	public <P extends BasResponsePojo,Q extends BasRequestPojo> P execute(Q requestPojo)
	{
		String jsonString = JSON.toJSONString(requestPojo);
		OutputStream out = null;
		//		BufferedReader in = null;
		Object responsePojo = null;
		InputStream in = null;
		try
		{
			URL apiUrl = new URL(httpurl);
			URLConnection conn = apiUrl.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("requestClass", requestPojo.getClass().getName());
			conn.setDoOutput(true);
			conn.setDoInput(true);
			out = conn.getOutputStream();
			out.write(jsonString.getBytes("utf-8"));
			out.flush();
			in = conn.getInputStream();
			byte[] respBytes = new byte[1024];
			int len = 0;
			String result = "";
			while ((len = in.read(respBytes)) > -1)
			{
				result += new String(respBytes, 0, len, "utf-8");
			}
			System.out.println(result);
			responsePojo = JSON.parseObject(result, requestPojo.getRespClass());
		} catch (MalformedURLException e)
		{
			throw new RuntimeException("报错内容", e);
		} catch (IOException e)
		{
			throw new RuntimeException("报错内容", e);
		} finally
		{
			if (in != null)
			{
				try
				{
					in.close();
				} catch (IOException e)
				{
					throw new RuntimeException("报错内容", e);
				}
			}
			if (out != null)
			{
				try
				{
					out.close();
				} catch (IOException e)
				{
					throw new RuntimeException("报错内容", e);
				}
			}
		}
		return (P) responsePojo;
	}

	public static void main(String[] args)
	{
		SpringOpenApiClient apiClient = new SpringOpenApiClient();
		HelloRequestPojo helloRequestPojo = new HelloRequestPojo();
		helloRequestPojo.setName("name");
		HelloResponsePojo execute = apiClient.execute(helloRequestPojo);
		System.out.println(execute);
	}
}
