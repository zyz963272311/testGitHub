package zyz.xyzhu;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import zyz.xyzhu.serv.HelloWorld;



/**
 * <p>标题： Client</p>
 * <p>内容： Client</p>
 * <p>创建时间： 2017年3月22日</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>类全名： zyz.xyzhu.Client</p>
 * <p>作者： Administrator</p>
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
