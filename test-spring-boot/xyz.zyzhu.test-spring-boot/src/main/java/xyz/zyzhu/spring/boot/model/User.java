package xyz.zyzhu.spring.boot.model;

import java.util.Date;
import javax.persistence.Table;
import xyz.zyzhu.spring.boot.annotation.FieldDef;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年2月4日 下午4:38:58</p>
 * <p>类全名：xyz.zyzhu.spring.boot.model.User</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
//@Entity
@Table(name = "users")
public class User extends BasModel
{
	private static final long	serialVersionUID	= 1L;
	@FieldDef(defaultValue = "AutoCode:useridYYMMDD_______")
	private String				userid;
	@FieldDef()
	private String				username;
	@FieldDef()
	private String				password;
	@FieldDef()
	private Integer				usertype;
	@FieldDef()
	private Integer				origintypes;
	@FieldDef()
	private Date				birth;
	@FieldDef()
	private Date				createdate;
	@FieldDef()
	private String				creater;
	@FieldDef()
	private Date				modifydate;
	@FieldDef()
	private String				modifyer;
	@FieldDef()
	//下面的两个字段用于记录失败登录
	private Integer				errcount;
	@FieldDef()
	private Date				errtime;

	public String getUserid()
	{
		return userid;
	}

	public void setUserid(String userid)
	{
		setValue("userid", userid);
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

	public Integer getUsertype()
	{
		return usertype;
	}

	public void setUsertype(Integer usertype)
	{
		setValue("usertype", usertype);
	}

	public Integer getOrigintypes()
	{
		return origintypes;
	}

	public void setOrigintypes(Integer origintypes)
	{
		setValue("origintypes", origintypes);
	}

	public Date getBirth()
	{
		return birth;
	}

	public void setBirth(Date birth)
	{
		setValue("birth", birth);
	}

	public Date getCreatedate()
	{
		return createdate;
	}

	public void setCreatedate(Date createdate)
	{
		setValue("createdate", createdate);
	}

	public String getCreater()
	{
		return creater;
	}

	public void setCreater(String creater)
	{
		setValue("creater", creater);
	}

	public Date getModifydate()
	{
		return modifydate;
	}

	public void setModifydate(Date modifydate)
	{
		setValue("modifydate", modifydate);
	}

	public String getModifyer()
	{
		return modifyer;
	}

	public void setModifyer(String modifyer)
	{
		setValue("modifyer", modifyer);
	}

	public Integer getErrcount()
	{
		return errcount;
	}

	public void setErrcount(Integer errcount)
	{
		setValue("errcount", errcount);
	}

	public Date getErrtime()
	{
		return errtime;
	}

	public void setErrtime(Date errtime)
	{
		setValue("errtime", errtime);
	}
}
