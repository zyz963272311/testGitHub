package xyz.zyzhu.entry;

/**
 * <p>标题： 测试Spring</p>
 * <p>功能： </p>
 * <p>所属模块： 测试模块</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: zyzhu.xyz</p>
 * <p>创建日期：2017年8月1日 下午3:10:42</p>
 * <p>类全名：xyz.zyzhu.entry.Hello</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Hello
{
	private String	name;
	private String	say;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getSay()
	{
		return say;
	}

	public void setSay(String say)
	{
		this.say = say;
	}

	@Override
	public String toString()
	{
		return "Hello:[name=" + this.name + ",say=" + this.say + "]";
	}
}
