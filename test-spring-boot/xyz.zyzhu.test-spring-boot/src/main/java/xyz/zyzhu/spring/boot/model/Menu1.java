package xyz.zyzhu.spring.boot.model;

import javax.persistence.Table;
import xyz.zyzhu.spring.boot.annotation.FieldDef;
/**
 * <p>标题： TODO</p>
 * <p>功能：</p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2017年12月3日 上午12:04:12</p>
 * <p>类全名：xyz.zyzhu.spring.boot.model.Menu</p>
 * 作者：赵玉柱 初审： 复审： 监听使用界面:
 * 
 * @version 8.0
 */
@Table(name = "menu1")
public class Menu1 extends BasModel
{
	private static final long	serialVersionUID	= 8548831581997780285L;
	@FieldDef(defaultValue = "AutoCode:mid______")
	String						mid;
	@FieldDef()
	String						mname;
	@FieldDef(defaultValue="0")
	Integer						offlags;
	@FieldDef()
	String						url;
	@FieldDef()
	String						limits;

	/**
	 * @param mname
	 * @param offlags
	 * @param url
	 * @param limits
	 */
	public Menu1(String mname, Integer offlags, String url, String limits)
	{
		super();
		this.mname = mname;
		this.offlags = offlags;
		this.url = url;
		this.limits = limits;
	}

	/**
	 * 
	 */
	public Menu1()
	{
		super();
	}

	public String getMid()
	{
		return mid;
	}

	public void setMid(String mid)
	{
		setValue("mid", mid);
	}

	public String getMname()
	{
		return mname;
	}

	public void setMname(String mname)
	{
		setValue("mname", mname);
	}

	public Integer getOfflags()
	{
		return offlags;
	}

	public void setOfflags(Integer offlags)
	{
		setValue("offlags", offlags);
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		setValue("limits", url);
	}

	public String getLimits()
	{
		return limits;
	}

	public void setLimits(String limits)
	{
		setValue("limits", limits);
	}
}
