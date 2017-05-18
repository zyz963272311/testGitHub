package design.pattern.state;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年2月27日 下午5:09:33</p>
 * <p>类全名：design.pattern.state.Context</p>
 * 作者：x250-2
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Context
{
	private State	state;

	public State getState()
	{
		return state;
	}

	public void setState(State state)
	{
		this.state = state;
	}

	public Context(State state)
	{
		this.state = state;
	}

	public void method(String invoke)
	{
		if (invoke.equals(state.getValue()))
		{
			state.method1();
		} else if (invoke.equals(state.getValue()))
		{
			state.method2();
		}
	}
}
