package zyz.xyzhu.serv.impl;

import javax.jws.WebService;
import zyz.xyzhu.serv.HelloWorld;


/**
 * <p>标题： HelloWorld</p>
 * <p>内容： HelloWorld</p>
 * <p>创建时间： 2017年3月21日</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>类全名： zyz.xyzhu.serv.impl.HelloWorldImpl</p>
 * <p>作者： Administrator</p>
 */
@WebService
public class HelloWorldImpl implements HelloWorld
{

	public String sayHello()
	{
		return "hello world";
	}
}
