package xyz.zyzhu.utils;

import org.apache.http.client.methods.HttpPost;
import xyz.zyzhu.pojo.BasRequestPojo;
import xyz.zyzhu.pojo.BasResponsePojo;
import xyz.zyzhu.pojo.HelloRequestPojo;
import xyz.zyzhu.pojo.HelloResponsePojo;
import com.alibaba.fastjson.JSON;
import com.liiwin.config.BasConfig;
import com.liiwin.http.HttpClientUtil;
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
		httpurl = BasConfig.getPropertie("webservice-localhost");
	}

	/**
	 * 
	 */
	public SpringOpenApiClient()
	{
	}

	public <P extends BasResponsePojo,Q extends BasRequestPojo> P execute(Q requestPojo)
	{
		String jsonString = JSON.toJSONString(requestPojo);
		HttpPost httpPost = new HttpPost();
		String sendHttpPost = HttpClientUtil.sendHttpPost(httpurl, jsonString);
		System.out.println(sendHttpPost);
		return null;
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
