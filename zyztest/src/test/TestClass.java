package test;

/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年11月11日 上午2:54:00</p>
 * <p>类全名：test.TestClass</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestClass
{
	public String		a;
	protected String	b;
	String				c;
	private String		d;

	public void a(String a)
	{
		System.out.println("a=" + a);
	}

	protected void b(String b)
	{
		System.out.println("b=" + b);
	}

	void c(String c)
	{
		System.out.println("c=" + c);
	}

	private void d(String d)
	{
		System.out.println("d=" + d);
	}

	public String getA()
	{
		System.out.println("a的get方法");
		return a;
	}

	protected String getB()
	{
		return b;
	}

	String getC()
	{
		return c;
	}

	private String getD()
	{
		return d;
	}

	public String hhh(String s)
	{
		return "hhh";
	}
}
