package zyz.zyzhu.main;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import zyz.xyzhu.serv.HelloWorld;
import zyz.xyzhu.serv.impl.HelloWorldImpl;

/**
 * <p>���⣺ JaxWebServer</p>
 * <p>���ݣ� JaxWebServer</p>
 * <p>����ʱ�䣺 2017��3��22��</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>��ȫ���� zyz.zyzhu.main.JaxWebServer</p>
 * <p>���ߣ� Administrator</p>
 */
public class JaxWebServer
{

	public static void main(String[] args)
	{
		JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
		factory.setAddress("http://localhost:8090/Maven-CXF-Spring-1/ws/HelloWorld");
		factory.setServiceClass(HelloWorld.class);
		factory.setServiceBean(new HelloWorldImpl());
		factory.create();
		System.out.println("�����ɹ�");
	}
}
