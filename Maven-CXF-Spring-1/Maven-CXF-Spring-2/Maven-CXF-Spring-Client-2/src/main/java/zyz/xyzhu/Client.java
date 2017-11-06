package zyz.xyzhu;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import zyz.xyzhu.serv.HelloWorld;



/**
 * <p>���⣺ Client</p>
 * <p>���ݣ� Client</p>
 * <p>����ʱ�䣺 2017��3��22��</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>��ȫ���� zyz.xyzhu.Client</p>
 * <p>���ߣ� Administrator</p>
 */
public class Client
{
	public static void main(String[] args)
	{
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setAddress("http://localhost:8090/Maven-CXF-Spring-1/ws/HelloWorld");
		factory.setServiceClass(HelloWorld.class);
		HelloWorld service = factory.create(HelloWorld.class);
		String str = service.sayHello();
		System.out.println(str);
	}
}
