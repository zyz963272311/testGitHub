package design.pattern.responsibility;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年2月27日 下午2:45:05</p>
 * <p>类全名：design.pattern.responsibility.MyHandler</p>
 * 作者：x250-2
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class MyHandler extends HandlerAbstract
{
	private final String	name;

	public MyHandler(String name)
	{
		this.name = name;
	}

	@Override
	public void operate()
	{
		System.out.println(name + "执行了!");
		if (getHandler() != null)
		{
			getHandler().operate();
		}
	}
}
