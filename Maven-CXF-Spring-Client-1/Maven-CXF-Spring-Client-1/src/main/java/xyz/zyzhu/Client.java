package xyz.zyzhu;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import xyz.zyzhu.serv.HelloWorld;

/**
 * <p>标题： Client</p>
 * <p>内容： Client</p>
 * <p>创建时间： 2017年3月22日</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>类全名： xyz.zyzhu.Client</p>
 * <p>作者： Administrator</p>
 */
public class Client
{

	@SuppressWarnings("resource")
	public static void main(String[] args)
	{
		try
		{
		ApplicationContext ctx = new ClassPathXmlApplicationContext("cfg/spring-client.xml");
		HelloWorld helloWorld = (HelloWorld) ctx.getBean("client");
		String result = helloWorld.sayHello();
		System.out.println(result);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
