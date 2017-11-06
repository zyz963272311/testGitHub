package com.test.zhu.spring.beans;

/**
 * <p>标题： HelloWold</p>
 * <p>内容： HelloWold</p>
 * <p>创建时间： 2016年12月27日</p>
 * <p>copyRight @ zyzhu.xyz 2016</p>
 * <p>类全名： com.test.zhu.spring.beans.HelloWold</p>
 * <p>作者： Administrator</p>
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
