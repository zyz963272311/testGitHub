package zyz.xyzhu.serv;

import javax.jws.WebService;



/**
 * <p>标题： HelloWorld</p>
 * <p>内容： HelloWorld</p>
 * <p>创建时间： 2017年3月21日</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>类全名：zyz.xyzhu.serv.HelloWorld</p>
 * <p>作者： Administrator</p>
 */
// @WebService(endpointInterface = "zyz.xyzhu.serv.HelloWorld")
@WebService
public interface HelloWorld
{
	String sayHello();
}
