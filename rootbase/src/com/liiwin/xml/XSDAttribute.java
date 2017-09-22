package com.liiwin.xml;

/**
 * <p>标题： XSD 节点属性</p>
 * <p>功能： </p>
 * <p>所属模块： rootbas</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2017年9月22日 上午10:39:45</p>
 * <p>类全名：com.liiwin.xml.XSDAttribute</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class XSDAttribute
{
	private String	name;
	private String	type;
	private int		option;
	private Object	fixedValue;
	private Object	defaultValue;

	/**
	 * 
	 */
	public XSDAttribute()
	{
	}

	/**
	 * @param name
	 * @param type
	 * @param option
	 * @param fixedValue
	 * @param defaultValue
	 */
	public XSDAttribute(String name, String type, int option, Object fixedValue, Object defaultValue)
	{
		super();
		this.name = name;
		this.type = type;
		this.option = option;
		this.fixedValue = fixedValue;
		this.defaultValue = defaultValue;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public int getOption()
	{
		return option;
	}

	public void setOption(int option)
	{
		this.option = option;
	}

	public Object getFixedValue()
	{
		return fixedValue;
	}

	public void setFixedValue(Object fixedValue)
	{
		this.fixedValue = fixedValue;
	}

	public Object getDefaultValue()
	{
		return defaultValue;
	}

	public void setDefaultValue(Object defaultValue)
	{
		this.defaultValue = defaultValue;
	}
}
