package zyz.xyzhu.serv.impl;

import javax.jws.WebService;
import zyz.xyzhu.serv.HelloWorld;


/**
 * <p>���⣺ HelloWorld</p>
 * <p>���ݣ� HelloWorld</p>
 * <p>����ʱ�䣺 2017��3��21��</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>��ȫ���� zyz.xyzhu.serv.impl.HelloWorldImpl</p>
 * <p>���ߣ� Administrator</p>
 */
@WebService
public class HelloWorldImpl implements HelloWorld
{

	public String sayHello()
	{
		return "hello world";
	}
}
