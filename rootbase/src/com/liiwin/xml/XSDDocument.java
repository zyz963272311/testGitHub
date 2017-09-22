package com.liiwin.xml;

/**
 * <p>标题： XSD 文档类</p>
 * <p>功能： </p>
 * <p>所属模块： rootbas</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2017年9月22日 上午10:40:07</p>
 * <p>类全名：com.liiwin.xml.XSDDocument</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class XSDDocument
{
	private String		define;
	private XSDHead		head;
	private XSDElement	element;

	public String getDefine()
	{
		return define;
	}

	/**
	 * 
	 */
	public XSDDocument()
	{
		super();
	}

	/**
	 * @param define
	 * @param head
	 * @param element
	 */
	public XSDDocument(String define, XSDHead head, XSDElement element)
	{
		super();
		this.define = define;
		this.head = head;
		this.element = element;
	}

	public void setDefine(String define)
	{
		this.define = define;
	}

	public XSDHead getHead()
	{
		return head;
	}

	public void setHead(XSDHead head)
	{
		this.head = head;
	}

	public XSDElement getElement()
	{
		return element;
	}

	public void setElement(XSDElement element)
	{
		this.element = element;
	}
}
