package collection;

/**
 * <p>标题：用来测试Collection的Person类 </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年12月13日 下午2:44:22</p>
 * <p>类全名：collection.Person</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Person
{
	private int		id;
	private String	name;
	private int		age;

	/**
	 * 构造方法
	 * @param id 工号
	 * @param name 姓名
	 * @param age 年龄
	 */
	public Person(int id, String name, int age)
	{
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	/**
	 * @return
	 * @see java.lang.String#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime + this.name.hashCode();
		result = prime * result + age;
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}

	@Override
	public String toString()
	{
		return "Person [id=" + id + ", name=" + name + ", age=" + age + ", hashCode=" + this.hashCode() + "]";
	}
}
