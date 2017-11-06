package zyz.zyzhu.main;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import zyz.xyzhu.serv.HelloWorld;

/**
 * <p>���⣺ JacWebClient</p>
 * <p>���ݣ� JacWebClient</p>
 * <p>����ʱ�䣺 2017��3��22��</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>��ȫ���� zyz.zyzhu.main.JacWebClient</p>
 * <p>���ߣ� Administrator</p>
 */
public class JacWebClient
{

	public static void main(String[] args)
	{
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setAddress("http://localhost:8090/Maven-CXF-Spring-1/ws/HelloWorld");
		factory.setServiceClass(HelloWorld.class);
		HelloWorld service = factory.create(HelloWorld.class);
		String result = service.sayHello();
		System.out.println(result);
	}
}
