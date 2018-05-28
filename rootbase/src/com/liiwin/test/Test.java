package com.liiwin.test;

import java.io.Serializable;
import java.util.Arrays;
/**
 * <p>标题： 测试</p>
 * <p>功能： </p>
 * <p>所属模块： 测试</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年7月29日 下午11:39:34</p>
 * <p>类全名：com.liiwin.test.Test</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Test implements Serializable
{
	private Integer				a;
	private Boolean				b;
	private String				c;
	private String[]			d;
	private transient Integer	e;

	public Integer getA()
	{
		return a;
	}

	public void setA(Integer a)
	{
		//setValue("a", a);
		this.a = a;
	}

	public Boolean getB()
	{
		return b;
	}

	public void setB(Boolean b)
	{
		//setValue("b", b);
		this.b = b;
	}

	public String getC()
	{
		return c;
	}

	public void setC(String c)
	{
		//setValue("c", c);
		this.c = c;
	}

	public String[] getD()
	{
		return d;
	}

	public void setD(String[] d)
	{
		//setValue("d", d);
		this.d = d;
	}

	public Integer getE()
	{
		return e;
	}

	public void setE(Integer e)
	{
		this.e = e;
	}

	@Override
	public String toString()
	{
		return "Test [a=" + a + ", b=" + b + ", c=" + c + ", d=" + Arrays.toString(d) + ", e=" + e + "]";
	}
}
