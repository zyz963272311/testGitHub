package com.test.zhu.spring.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>���⣺ Main</p>
 * <p>���ݣ� Main</p>
 * <p>����ʱ�䣺 2016��12��27��</p>
 * <p>copyRight @ zyzhu.xyz 2016</p>
 * <p>��ȫ���� com.test.zhu.spring.beans.Main</p>
 * <p>���ߣ� Administrator</p>
 */
public class Main
{
	public static void main(String[] args)
	{
		//���������в��ֽ���Spring��IOC�������
//		HelloWold hello =new HelloWold();
//		hello.setName("zhaoyuzhu");
//		hello.hello();
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		//��IOC�����л�ȡBeanʾ��
		HelloWold hello =(HelloWold)ctx.getBean("hellowold");
		hello.hello();
	}
}
