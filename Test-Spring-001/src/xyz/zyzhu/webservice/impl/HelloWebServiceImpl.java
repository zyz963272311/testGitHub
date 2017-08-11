package xyz.zyzhu.webservice.impl;

import javax.jws.WebService;
import xyz.zyzhu.pojo.HelloRequestPojo;
import xyz.zyzhu.pojo.HelloResponsePojo;
import xyz.zyzhu.webservice.HelloWebService;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: zyzhu.xyz</p>
 * <p>创建日期：2017年8月11日 上午11:32:35</p>
 * <p>类全名：xyz.zyzhu.webservice.impl.HelloWebServiceImpl</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@WebService
public class HelloWebServiceImpl implements HelloWebService
{
	@Override
	public HelloResponsePojo sayHello(HelloRequestPojo requestPojo)
	{
		HelloResponsePojo responsePojo = new HelloResponsePojo();
		try
		{
			if (requestPojo == null)
			{
				throw new RuntimeException("数据接入为空");
			}
			String name = requestPojo.getName();
			if (StrUtil.isStrTrimNull(name))
			{
				throw new RuntimeException("数据接入字段【name】不可为空");
			}
			System.out.println(name);
			responsePojo.setCode("200");
			responsePojo.setName(name);
			responsePojo.setStatus("success");
		} catch (Exception e)
		{
			responsePojo.setCode("300");
			responsePojo.setStatus("success");
			responsePojo.setMessage(e.getMessage());
		}
		return responsePojo;
	}
}
