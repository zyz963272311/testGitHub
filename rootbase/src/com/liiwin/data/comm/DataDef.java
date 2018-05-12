package com.liiwin.data.comm;

import java.io.Serializable;
/**
 * <p>标题： 导入导出定义</p>
 * <p>功能： </p>
 * <p>所属模块： rootbas</p>
 * <p>版权： Copyright © 2018 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2018年5月7日 下午4:42:24</p>
 * <p>类全名：com.liiwin.data.DataDef</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public abstract class DataDef implements Serializable
{
	private static final long	serialVersionUID	= 1L;
	private String				defno;						//导入导出定义编号
	private String				properties;				//
	private FieldDef			rootDef;					//根节点

	public String getDefno()
	{
		return defno;
	}

	public void setDefno(String defno)
	{
		this.defno = defno;
	}

	public FieldDef getRootDef()
	{
		return rootDef;
	}

	public void setRootDef(FieldDef rootDef)
	{
		this.rootDef = rootDef;
	}

	public String getProperties()
	{
		return properties;
	}

	public void setProperties(String properties)
	{
		this.properties = properties;
	}

	/**
	 * 是导读还是导出
	 * @return
	 * 赵玉柱
	 */
	public abstract Integer getIEFlags();

	/**
	 * 定义方式 1：XML；2：json
	 * @return
	 * 赵玉柱
	 */
	public abstract Integer getType();
}
