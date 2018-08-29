package xyz.zyzhu.spring.boot.model;

import javax.persistence.Table;
import xyz.zyzhu.spring.boot.annotation.FieldDef;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年8月28日 下午4:50:30</p>
 * <p>类全名：xyz.zyzhu.spring.boot.model.DbDef</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Table(name = "db")
public class DbDef extends BasModel
{
	private static final long	serialVersionUID	= 7603688596301149797L;
	@FieldDef()
	private String				dbname;
	@FieldDef()
	private String				url;
	@FieldDef()
	private String				username;
	@FieldDef()
	private String				password;
	@FieldDef()
	private Integer				initcount;
	@FieldDef()
	private Integer				maxcount;

	public String getDbname()
	{
		return dbname;
	}

	public void setDbname(String dbname)
	{
		setValue("dbname", dbname);
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		setValue("url", url);
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		setValue("username", username);
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		setValue("password", password);
	}

	public Integer getInitcount()
	{
		return initcount;
	}

	public void setInitcount(Integer initcount)
	{
		setValue("initcount", initcount);
	}

	public Integer getMaxcount()
	{
		return maxcount;
	}

	public void setMaxcount(Integer maxcount)
	{
		setValue("maxcount", maxcount);
	}
}
