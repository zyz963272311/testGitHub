package com.test.zhu.spring.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>标题： Main</p>
 * <p>内容： Main</p>
 * <p>创建时间： 2016年12月27日</p>
 * <p>copyRight @ zyzhu.xyz 2016</p>
 * <p>类全名： com.test.zhu.spring.beans.Main</p>
 * <p>作者： Administrator</p>
 */
public class Main
{
	public static void main(String[] args)
	{
		//下面这三行部分交给Spring的IOC容器完成
//		HelloWold hello =new HelloWold();
//		hello.setName("zhaoyuzhu");
//		hello.hello();
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		//从IOC容器中获取Bean示例
		HelloWold hello =(HelloWold)ctx.getBean("hellowold");
		hello.hello();
	}
}
