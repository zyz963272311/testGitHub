package xyz.zyzhu.spring.boot.model;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2017年11月17日 下午10:28:21</p>
 * <p>类全名：xyz.zyzhu.spring.boot.model.UserInfo</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class UserInfo
{
	String	name;
	String	password;

	public UserInfo()
	{
	}

	/**
	 * @param id
	 * @param name
	 * @param password
	 */
	public UserInfo(String name, String password)
	{
		super();
		this.name = name;
		this.password = password;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
}
