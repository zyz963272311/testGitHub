package xyz.zyzhu.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;
import xyz.zyzhu.pojo.HelloRequestPojo;
import xyz.zyzhu.pojo.HelloResponsePojo;
/**
 * <p>标题： 测试使用工具类发布Webservice</p>
 * <p>功能： </p>
 * <p>所属模块： testSpring</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: zyzhu.xyz</p>
 * <p>创建日期：2017年8月11日 上午11:16:09</p>
 * <p>类全名：xyz.zyzhu.webservice.HelloWebService</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@WebService
public interface HelloWebService
{
	@WebMethod
	HelloResponsePojo sayHello(HelloRequestPojo requestPojo);
}
