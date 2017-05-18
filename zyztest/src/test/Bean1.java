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
public class Bean1
{
	private String	name;
	private int		age;

	public int getAge()
	{
		return age;
	}

	public String getName()
	{
		return name;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public String toString()
	{
		return "Bean1 [name=" + name + ", age=" + age + "]";
	}
}
