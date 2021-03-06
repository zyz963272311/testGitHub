package xyz.zyzhu.initlistener;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import xyz.zyzhu.pojo.HelloRequestPojo;
import xyz.zyzhu.pojo.HelloResponsePojo;
import xyz.zyzhu.webservice.HelloWebService;

import com.liiwin.config.BasConfig;

/**
 * <p>
 * 标题： TODO
 * </p>
 * <p>
 * 功能：
 * </p>
 * <p>
 * 所属模块： TODO
 * </p>
 * <p>
 * 版权： Copyright © 2017 zyzhu
 * </p>
 * <p>
 * 公司: zyzhu.xyz
 * </p>
 * <p>
 * 创建日期：2017年8月12日 上午11:57:39
 * </p>
 * <p>
 * 类全名：xyz.zyzhu.initlistener.Test
 * </p>
 * 作者：赵玉柱 初审： 复审： 监听使用界面:
 * 
 * @version 8.0
 */
public class Test {
	public static void main(String[] args) {
		try {
			URL url = new URL(BasConfig.getPropertie("webservice-localhost")
					+ "/spring.HelloWebService?WSDL");
			QName name = new QName("http://impl.webservice.zyzhu.xyz/",
					"HelloWebServiceImplService");
			Service service = Service.create(url, name);
			HelloWebService helloService = service
					.getPort(HelloWebService.class);
			HelloRequestPojo helloRequestPojo = new HelloRequestPojo();
			helloRequestPojo.setName("赵玉柱");
			HelloResponsePojo sayHello = helloService
					.sayHello(helloRequestPojo);
			System.out.println(sayHello);
		} catch (MalformedURLException e) {
			throw new RuntimeException("报错内容", e);
		}
	}
}
