package com.liiwin.doc;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * <p>标题： 一个文档</p>
 * <p>功能： </p>
 * <p>所属模块： rootbas</p>
 * <p>版权： Copyright © 2018 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2018年5月7日 上午10:02:53</p>
 * <p>类全名：com.liiwin.doc.Document</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Document implements Serializable
{
	private static final long	serialVersionUID	= 1L;
	private Element				rootElement;

	public Element getRootElement()
	{
		return rootElement;
	}

	public void setRootElement(Element rootElement)
	{
		this.rootElement = rootElement;
	}

	/**
	 * 根据路径获取Element
	 * @param path
	 * @return
	 * 赵玉柱
	 */
	public Element getElementByPath(String path)
	{
		Element root = getRootElement();
		return root == null ? null : root.getElementByPath(path);
	}

	/**
	 * 根据路径获取attr
	 * @param path
	 * @return
	 * 赵玉柱
	 */
	public Attribute getAttrByPath(String path)
	{
		Element root = getRootElement();
		return root == null ? null : root.getAttrByPath(path);
	}

	/**
	 * 根据名称获取attr
	 * @param name
	 * @return
	 * 赵玉柱
	 */
	public Attribute getAttrByName(String name)
	{
		Element root = getRootElement();
		return root == null ? null : root.getAttrByName(name);
	}

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
