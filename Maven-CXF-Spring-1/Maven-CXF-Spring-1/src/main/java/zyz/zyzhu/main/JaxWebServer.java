package zyz.zyzhu.main;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import zyz.xyzhu.serv.HelloWorld;
import zyz.xyzhu.serv.impl.HelloWorldImpl;

/**
 * <p>标题： JaxWebServer</p>
 * <p>内容： JaxWebServer</p>
 * <p>创建时间： 2017年3月22日</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>类全名： zyz.zyzhu.main.JaxWebServer</p>
 * <p>作者： Administrator</p>
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
		System.out.println("发布成功");
	}
}
