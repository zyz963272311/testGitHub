package com.liiwin.xsd.domain;

import java.io.Serializable;
import java.util.Map;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
/**
 * <p>标题： xsd文档对象</p>
 * <p>功能： </p>
 * <p>所属模块： 01</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2018年4月12日 下午1:59:15</p>
 * <p>类全名：snsoft.rootbas.codedataexp.domain.XSDDocumnet</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class XSDDocumnet implements Serializable
{
	private static final long	serialVersionUID	= 4449287193936039442L;
	String						encoding;									//编码
	String						xmlns;										//xml的名字空间
	Map<String,String>			xmlnsOther;								//其他的工作空间
	String						targetNamespace;							//声明节点的来源位置
	//qualified:必须；unqualified：非必须2
	String						elementFormDefault;						//
	XSDNode						rootNood;

	public String getEncoding()
	{
		return encoding;
	}

	public void setEncoding(String encoding)
	{
		this.encoding = encoding;
	}

	public String getXmlns()
	{
		return xmlns;
	}

	public void setXmlns(String xmlns)
	{
		this.xmlns = xmlns;
	}

	public Map<String,String> getXmlnsOther()
	{
		return xmlnsOther;
	}

	public void setXmlnsOther(Map<String,String> xmlnsOther)
	{
		this.xmlnsOther = xmlnsOther;
	}

	public String getTargetNamespace()
	{
		return targetNamespace;
	}

	public void setTargetNamespace(String targetNamespace)
	{
		this.targetNamespace = targetNamespace;
	}

	public String getElementFormDefault()
	{
		return elementFormDefault;
	}

	public void setElementFormDefault(String elementFormDefault)
	{
		this.elementFormDefault = elementFormDefault;
	}

	public XSDNode getRootNood()
	{
		return rootNood;
	}

	public void setRootNood(XSDNode rootNood)
	{
		this.rootNood = rootNood;
	}

	/**
	 * 根据path获取节点
	 * @param path
	 * @return
	 * 赵玉柱
	 */
	public XSDNode getNodeByPathAndName(String path, String name)
	{
		if (rootNood == null)
		{
			return null;
		}
		return rootNood.getNodeByPath(path);
	}

	/**
	 * 根据node的path和attr的name获取attr属性
	 * @param path
	 * @param attrName
	 * @return
	 * 赵玉柱
	 */
	public XSDAttribute getAttrByNodePathAndAttrName(String path, String attrName)
	{
		if (rootNood == null)
		{
			return null;
		}
		return rootNood.getAttrByNodePathAndAttrName(path, attrName);
	}

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE) + "\n";
	}
}
