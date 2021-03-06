package com.liiwin.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
/**
 * <p>标题：  获取本地的XML文件</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年5月22日 上午10:04:21</p>
 * <p>类全名：com.liiwin.utils.GetClasspathXml</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class GetXmlFile
{
	/**
	 * 获取某一个XML文件的根节点
	 * @param filePath
	 * @return
	 * 赵玉柱
	 */
	public static Element getXMLElement(String filePath)
	{
		Document document = getXmlDocument(filePath);
		Element root = document.getRootElement();
		return root;
	}

	/**
	 * 获取parent节点及节点下所有attribute的name=attrName并且value=attrValue的节点List集合
	 * @param elements 节点集合
	 * @param parent 
	 * @param attrName 可空  空表示任何一个attrValue匹配即可
	 * @param attrValue 可空 空表示不限制
	 * 赵玉柱
	 */
	public static void getXMLElementByAttr(List<Element> elements, Element parent, String attrName, String attrValue)
	{
		getXMLElementByNameAndAttr(elements, parent, null, attrName, attrValue);
	}

	/**
	 * 获取parent节点及节点下所有name为elementName的节点的集合
	 * @param elements
	 * @param parent
	 * @param elementName 可空 空表示不限制
	 * 赵玉柱
	 */
	public static void getXMLElementByName(List<Element> elements, Element parent, String elementName)
	{
		getXMLElementByNameAndAttr(elements, parent, elementName, null, null);
	}

	/**
	 * 获取pqrent节点及节点下所有name为elementName并且attributeName=attrName并且attributeValue=attrValue的节点的集合
	 * @param elements
	 * @param parent
	 * @param elementName
	 * @param attrName
	 * @param attrValue
	 * 赵玉柱
	 */
	public static void getXMLElementByNameAndAttr(List<Element> elements, Element parent, String elementName, String attrName, String attrValue)
	{
		if (parent != null)
		{
			String name = parent.getName();
			if (elementName == null || elementName.trim().length() == 0 || elementName.equals(name))
			{
				if (attrName == null || attrName.trim().length() == 0)
				{
					if (attrValue == null || attrValue.trim().length() == 0)
					{
						elements.add(parent);
					} else
					{
						List<Attribute> attrs = parent.attributes();
						for (Attribute attr : attrs)
						{
							String value = attr.getValue();
							if (attrValue.equals(value))
							{
								elements.add(parent);
								break;
							}
						}
					}
				} else
				{
					String attr = parent.attributeValue(attrName);
					if (attrValue == null || attrValue.equals(attr))
					{
						elements.add(parent);
					}
				}
			}
			List<Element> elementTemp = parent.elements();
			for (Element element : elementTemp)
			{
				getXMLElementByNameAndAttr(elements, element, elementName, attrName, attrValue);
			}
		}
	}

	/**
	 * 获取路径为filePath的xml配置Document
	 * @param filePath
	 * @return
	 * 赵玉柱
	 */
	public static Document getXmlDocument(String filePath)
	{
		Document document = null;
		try
		{
			SAXReader reader = new SAXReader();
			InputStream in = GetXmlFile.class.getResourceAsStream(filePath);
			document = reader.read(in);
		} catch (DocumentException e)
		{
			throw new RuntimeException("报错内容", e);
		}
		return document;
	}

	/**
	 * 获取路径为filePath的xml配置Document
	 * @param filePath
	 * @return
	 * 赵玉柱
	 */
	public static Document getXmlDocumentByAbsulatePath(String filePath)
	{
		Document document = null;
		try
		{
			SAXReader reader = new SAXReader();
			InputStream in = new FileInputStream(filePath);
			document = reader.read(in);
		} catch (DocumentException e)
		{
			throw new RuntimeException("报错内容", e);
		} catch (FileNotFoundException e)
		{
			throw new RuntimeException("报错内容", e);
		}
		return document;
	}

	public static void main(String[] args)
	{
		Element e = getXMLElement("resources/update.xml");
		List<Element> elements = new ArrayList<>();
		getXMLElementByNameAndAttr(elements, e, "path", null, null);
		for (Element element : elements)
		{
			System.out.println(element.getName());
			List<Attribute> attrs = element.attributes();
			for (Attribute attr : attrs)
			{
				System.out.println(attr.getName() + "\t" + attr.getValue());
			}
			System.out.println("===================================");
		}
		System.out.println();
	}
}
