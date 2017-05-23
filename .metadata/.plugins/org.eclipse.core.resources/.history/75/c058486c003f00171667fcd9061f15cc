package com.liiwin.utils;

import java.io.File;
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
	public static Element getXMLElement(String filePath, String elementName)
	{
		Document document = getXmlDocument(filePath);
		Element root = document.getRootElement();
		System.out.println(root.getName());
		return root;
	}

	public static Element getXMLElementByFilter(Element parent, String filter, String elementName)
	{
		Attribute attr = parent.attribute(filter);
		if (attr != null)
		{
			if (elementName.equals(attr.getValue()))
			{
				return parent;
			}
		} else
		{
			List<Element> elements = parent.elements();
			for (Element element : elements)
			{
				Element e = getXMLElementByFilter(element, filter, elementName);
				if (e != null)
				{
					return e;
				}
			}
			return null;
		}
		return null;
	}

	public static Document getXmlDocument(String filePath)
	{
		Document document = null;
		try
		{
			SAXReader reader = new SAXReader();
			document = reader.read(new File(filePath));
		} catch (DocumentException e)
		{
			throw new RuntimeException("报错内容", e);
		}
		return document;
	}

	public static void main(String[] args)
	{
		Element e = getXMLElement("resources/cfgs/databases.xml", "database");
		Element e1 = getXMLElementByFilter(e, "name1", "zyztest");
		if (e1 != null)
		{
			System.out.println(e1.getName());
			List<Attribute> attrs = e1.attributes();
			for (Attribute attr : attrs)
			{
				System.out.println(attr.getName() + "\t" + attr.getValue());
			}
		}
		System.out.println();
	}
}
