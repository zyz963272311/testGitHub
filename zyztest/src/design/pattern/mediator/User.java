package design.pattern.mediator;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年2月27日 下午6:16:52</p>
 * <p>类全名：design.pattern.mediator.User</p>
 * 作者：x250-2
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public abstract class User
{
	private String			name;
	private final Mediator	mediator;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public User(Mediator mediator)
	{
		this.mediator = mediator;
	}

	public void sengMessage(User user, String message)
	{
		mediator.showMessage(this, user, message);
	}
}
