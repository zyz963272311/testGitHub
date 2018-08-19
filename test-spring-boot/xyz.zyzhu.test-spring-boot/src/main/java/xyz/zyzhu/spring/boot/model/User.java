package xyz.zyzhu.spring.boot.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@FieldDef(defaultValue = "AutoCode:useridYYMMDD_______")
	private String				id;
	private String				openid;
	private String				username;
	private String				headimage;
	private BigDecimal			posx;
	private BigDecimal			posy;
	private Integer				usertype;
	private Date				followtime;
	private Integer				followflags;
	private Date				modifydate;

	/**
	 * 
	 */
	public User()
	{
		super();
	}

	/**
	 * @param id
	 * @param openid
	 * @param username
	 * @param headimage
	 * @param posx
	 * @param posy
	 * @param usertype
	 * @param followtime
	 * @param followflags
	 * @param modifydate
	 */
	public User(String id, String openid, String username, String headimage, BigDecimal posx, BigDecimal posy, Integer usertype, Date followtime, Integer followflags, Date modifydate)
	{
		super();
		this.id = id;
		this.openid = openid;
		this.username = username;
		this.headimage = headimage;
		this.posx = posx;
		this.posy = posy;
		this.usertype = usertype;
		this.followtime = followtime;
		this.followflags = followflags;
		this.modifydate = modifydate;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		setValue("id", id);
	}

	public String getOpenid()
	{
		return openid;
	}

	public void setOpenid(String openid)
	{
		setValue("openid", openid);
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		setValue("username", username);
	}

	public String getHeadimage()
	{
		return headimage;
	}

	public void setHeadimage(String headimage)
	{
		setValue("headimage", headimage);
	}

	public BigDecimal getPosx()
	{
		return posx;
	}

	public void setPosx(BigDecimal posx)
	{
		setValue("posx", posx);
	}

	public BigDecimal getPosy()
	{
		return posy;
	}

	public void setPosy(BigDecimal posy)
	{
		setValue("posy", posy);
	}

	public Integer getUsertype()
	{
		return usertype;
	}

	public void setUsertype(Integer usertype)
	{
		setValue("usertype", usertype);
	}

	public Date getFollowtime()
	{
		return followtime;
	}

	public void setFollowtime(Date followtime)
	{
		setValue("followtime", followtime);
	}

	public Integer getFollowflags()
	{
		return followflags;
	}

	public void setFollowflags(Integer followflags)
	{
		setValue("followflags", followflags);
	}

	public Date getModifydate()
	{
		return modifydate;
	}

	public void setModifydate(Date modifydate)
	{
		setValue("followflags", followflags);
	}
}
