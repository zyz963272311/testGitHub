package com.test.zhu.spring.beans;

/**
 * <p>���⣺ HelloWold</p>
 * <p>���ݣ� HelloWold</p>
 * <p>����ʱ�䣺 2016��12��27��</p>
 * <p>copyRight @ zyzhu.xyz 2016</p>
 * <p>��ȫ���� com.test.zhu.spring.beans.HelloWold</p>
 * <p>���ߣ� Administrator</p>
 */
public class HelloWold
{
	public String name;

	public void setName(String name)
	{
		System.out.println("setName:"+name);
		this.name = name;
	}

	public void hello()
	{
		System.out.println("hello:" + name);
	}
}
