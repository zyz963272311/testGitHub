package com.liiwin.xsd.constans;

/**
 * <p>标题： 字典码表导出常量类</p>
 * <p>功能： </p>
 * <p>所属模块： 01</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2018年4月14日 下午1:42:40</p>
 * <p>类全名：snsoft.rootbas.codedataexp.constans.CodeDataConstans</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public interface CodeDataConstans
{
	public static final String	DEFAULT					= "default";
	//int类型
	public static final int		ATTR_TYPE_INT			= 1;
	public static final String	ARRT_TYPE_INT_STR		= "integer";
	//String类型
	public static final int		ATTR_TYPE_STRING		= 2;
	public static final String	ARRT_TYPE_STRING_STR	= "string";
	//boolean类型
	public static final int		ATTR_TYPE_BOOLEAN		= 3;
	public static final String	ARRT_TYPE_BOOLEAN_STR	= "boolean";
	//忽略的节点名称
	//annotation:注解
	//documentation:文档
	public static final String	INGAL_ELEMENT_NAME		= "annotation,documentation";
	//码表定义
	public static final String	CODETBL					= "codetbl";
	//码表属性
	public static final String	CODETBLA				= "codetbla";
	//界面定义
	public static final String	UI						= "ui";
	//界面属性定义
	public static final String	UIA						= "uia";
	//界面单元
	public static final String	UIC						= "uic";
	//界面单元属性
	public static final String	UICA					= "uica";
}
