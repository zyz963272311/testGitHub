package com.liiwin.xsd.domain;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import com.liiwin.xsd.constans.CodeDataConstans;
/**
 * <p>标题： xsd属性对象</p>
 * <p>功能： </p>
 * <p>所属模块： 01</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2018年4月12日 下午2:05:35</p>
 * <p>类全名：snsoft.rootbas.codedataexp.domain.XSDAttribute</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class XSDAttribute implements Serializable
{
	private static final long	serialVersionUID	= -3552076109137421918L;
	//此三个节点暂时是xsd使用的，其他的节点暂时不进行定义
	String						name;											//节点名称
	//规定内建的数据类型或简单类型
	//
	String						type;											//节点类型
	//optional - 属性是可选的并且可以具有任何值（默认）
	//prohibited - 不能使用属性
	//required - 属性的必需的
	String						use;											//节点属性

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

	public String getUse()
	{
		return use;
	}

	public void setUse(String use)
	{
		this.use = use;
	}

	/**
	 * 获取当前attribute的类型
	 * @return
	 * 赵玉柱
	 */
	public int getAttrType()
	{
		String curTupe = getType();
		if (CodeDataConstans.ARRT_TYPE_INT_STR.equals(curTupe))
		{
			return CodeDataConstans.ATTR_TYPE_INT;
		}
		if (CodeDataConstans.ARRT_TYPE_BOOLEAN_STR.equals(curTupe))
		{
			return CodeDataConstans.ATTR_TYPE_BOOLEAN;
		}
		if (CodeDataConstans.ARRT_TYPE_STRING_STR.equals(curTupe))
		{
			return CodeDataConstans.ATTR_TYPE_STRING;
		}
		return CodeDataConstans.ATTR_TYPE_STRING;
	}

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE) + "\n";
	}
}
