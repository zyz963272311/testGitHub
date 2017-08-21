package xyz.zyzhu.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;
import xyz.zyzhu.pojo.Hello1RequestPojo;
import xyz.zyzhu.pojo.Hello1ResponsePojo;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年8月21日 下午5:48:35</p>
 * <p>类全名：xyz.zyzhu.webservice.Hello1WebService</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@WebService
public interface Hello1WebService
{
	@WebMethod
	Hello1ResponsePojo sayHello(Hello1RequestPojo requestPojo);
}
