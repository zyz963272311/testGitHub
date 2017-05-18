package design.pattern.state;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年2月27日 下午5:02:46</p>
 * <p>类全名：design.pattern.state.State</p>
 * 作者：x250-2
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
//状态类
public class State
{
	private String	value;

	public State(String value)
	{
		this.value = value;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public void method1()
	{
		System.out.println("invoke method1");
	}

	public void method2()
	{
		System.out.println("invoke method2");
	}
}
