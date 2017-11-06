package xyz.zyzhu.demo;

import javax.xml.ws.Endpoint;


/**
 * <p>标题： WebServiceApp</p>
 * <p>内容： WebServiceApp</p>
 * <p>创建时间： 2017年3月4日</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>类全名： xyz.zyzhu.demo.WebServiceApp</p>
 * <p>作者： Administrator</p>
 */
public class WebServiceApp
{

	public static void main(String[] args)
	{
		System.out.println("Web Service Start");
		HelloWorldImpl hello = new HelloWorldImpl();
		String address = "http://localhost:8080/helloWorld";
		Endpoint.publish(address, hello);
		System.out.println("Web Service Started");
	}
}
