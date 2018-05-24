package test;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年3月6日 上午9:49:07</p>
 * <p>类全名：test.Bean1</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Bean2 extends Bean1
{
	private String	name1;
	private int		age1;

	public int getAge1()
	{
		return age1;
	}

	public String getName1()
	{
		return name1;
	}

	public void setAge1(int age1)
	{
		this.age1 = age1;
	}

	public void setName1(String name1)
	{
		this.name1 = name1;
	}

	@Override
	public String toString()
	{
		return "Bean1 [name=" + name1 + ", age=" + age1 + "]";
	}
}
