package com.liiwin.xmlnew.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.dom4j.io.SAXReader;
import com.liiwin.doc.Attribute;
import com.liiwin.doc.Document;
import com.liiwin.doc.Element;
import com.liiwin.doc.utils.DocumentUtils;
import com.liiwin.random.RandomString;
import com.liiwin.random.RandomStringImpl;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： XMLDocumentUtils</p>
 * <p>功能： </p>
 * <p>所属模块： rootbas</p>
 * <p>版权： Copyright © 2018 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2018年5月7日 下午2:07:45</p>
 * <p>类全名：com.liiwin.xmlnew.utils.XmlDocumentUtils</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class XmlDocumentUtils extends DocumentUtils
{
	@Override
	public Document buildDocumentByFile(File file)
	{
		SAXReader saxReader = new SAXReader();
		//获取原生的document
		org.dom4j.Document document;
		try
		{
			document = saxReader.read(file);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
		//将document转换为自己需要的Document
		return buildDocumentByDoc(document);
	}

	@Override
	public Document buildDocumentByPath(String path)
	{
		if (StrUtil.isStrTrimNull(path))
		{
			return null;
		}
		return buildDocumentByFile(new File(path));
	}

	@Override
	public Document buildDocumentByContent(String content)
	{
		if (StrUtil.isStrTrimNull(content))
		{
			return null;
		}
		RandomString rendom = new RandomStringImpl();
		String tempFileName = System.currentTimeMillis() + rendom.getRandomString(5, 'A', 'Z');
		File file = new File(tempFileName);
		FileOutputStream out = null;
		try
		{
			out = new FileOutputStream(file);
			out.write(content.getBytes());
		} catch (FileNotFoundException e)
		{
			throw new RuntimeException(e);
		} catch (IOException e)
		{
			throw new RuntimeException(e);
		} finally
		{
			if (out != null)
			{
				try
				{
					out.close();
				} catch (IOException e)
				{
					throw new RuntimeException(e);
				}
			}
		}
		return buildDocumentByFile(file);
	}

	private Document buildDocumentByDoc(org.dom4j.Document doc)
	{
		Document document = new Document();
		org.dom4j.Element rootElement = doc.getRootElement();
		Element root = buildElementByElem(rootElement, null, null);
		document.setRootElement(root);
		return document;
	}

	private Element buildElementByElem(org.dom4j.Element elem, String path, Element parent)
	{
		List<org.dom4j.Attribute> attrs = elem.attributes();
		Element element = new Element();
		//组装Element
		String name = elem.getName();
		element.setName(name);
		element.setText(elem.getText());
		element.setParentElement(parent);
		String curPath = path == null ? name : (path + "/" + name);
		element.setPath(curPath);
		//组装Attribute
		List<Attribute> attributes = buildAttributesByAttrs(attrs, curPath);
		element.setAttributes(attributes);
		//组装SubElements
		List<org.dom4j.Element> elements = elem.elements();
		List<Element> subElements = new ArrayList<>();
		if (elements != null && elements.size() > 0)
		{
			for (org.dom4j.Element subElem : elements)
			{
				Element subElement = buildElementByElem(subElem, curPath, element);
				subElements.add(subElement);
			}
		}
		element.setSubElements(subElements.isEmpty() ? null : subElements);
		return element;
	}

	private List<Attribute> buildAttributesByAttrs(List<org.dom4j.Attribute> attrs, String path)
	{
		if (attrs == null || attrs.isEmpty())
		{
			return null;
		}
		List<Attribute> result = new ArrayList<>();
		for (org.dom4j.Attribute attr : attrs)
		{
			Attribute attribute = new Attribute();
			String name = attr.getName();
			attribute.setName(name);
			attribute.setValue(attr.getValue());
			attribute.setPath(path == null ? name : path + "/" + name);
			result.add(attribute);
		}
		return result.isEmpty() ? null : result;
	}
}
