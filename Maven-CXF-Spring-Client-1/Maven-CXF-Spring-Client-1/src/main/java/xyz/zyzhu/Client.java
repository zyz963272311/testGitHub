package xyz.zyzhu;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import zyz.zyzhu.serv.HelloWorld;

/**
 * <p>���⣺ Client</p>
 * <p>���ݣ� Client</p>
 * <p>����ʱ�䣺 2017��3��22��</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>��ȫ���� xyz.zyzhu.Client</p>
 * <p>���ߣ� Administrator</p>
 */
public class Client
{

	@SuppressWarnings("resource")
	public static void main(String[] args)
	{
		try
		{
		ApplicationContext ctx = new ClassPathXmlApplicationContext("cfg/spring-client.xml");
		HelloWorld helloWorld =ctx.getBean("client", HelloWorld.class);
		String result = helloWorld.sayHello();
		System.out.println(result);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
