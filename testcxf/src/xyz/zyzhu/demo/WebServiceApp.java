package xyz.zyzhu.demo;

import javax.xml.ws.Endpoint;


/**
 * <p>���⣺ WebServiceApp</p>
 * <p>���ݣ� WebServiceApp</p>
 * <p>����ʱ�䣺 2017��3��4��</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>��ȫ���� xyz.zyzhu.demo.WebServiceApp</p>
 * <p>���ߣ� Administrator</p>
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
