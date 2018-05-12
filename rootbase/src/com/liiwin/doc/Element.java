package com.liiwin.doc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： 一个节点</p>
 * <p>功能： </p>
 * <p>所属模块： rootbas</p>
 * <p>版权： Copyright © 2018 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2018年5月7日 上午10:03:03</p>
 * <p>类全名：com.liiwin.doc.Element</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Element implements Serializable
{
	private static final long	serialVersionUID	= 1L;
	private String				name;
	private String				path;
	private Element				parentElement;
	private List<Element>		subElements;
	private List<Attribute>		attributes;
	private String				text;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public Element getParentElement()
	{
		return parentElement;
	}

	public void setParentElement(Element parentElement)
	{
		this.parentElement = parentElement;
	}

	public List<Element> getSubElements()
	{
		return subElements;
	}

	public void setSubElements(List<Element> subElements)
	{
		this.subElements = subElements;
	}

	public List<Attribute> getAttributes()
	{
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes)
	{
		this.attributes = attributes;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	/**
	 * 添加子节点
	 * @param element
	 * @return
	 * 赵玉柱
	 */
	public Element addSubElement(Element element)
	{
		List<Element> subs = getSubElements();
		if (subs == null)
		{
			subs = new ArrayList<>();
		}
		element.reSetPath(getPath() + "/" + element.getName());
		subs.add(element);
		if (!subs.isEmpty())
		{
			setSubElements(subs);
		}
		return this;
	}

	/**
	 * 添加Attribute
	 * @param attr
	 * @return
	 * 赵玉柱
	 */
	public Element addAttribute(Attribute attr)
	{
		List<Attribute> attrs = getAttributes();
		if (attrs == null)
		{
			attrs = new ArrayList<>();
		}
		if (!checkAttrExistByName(attr))
		{
			attr.setPath(getPath() + "/" + attr.getName());
			attrs.add(attr);
		}
		if (!attrs.isEmpty())
		{
			setAttributes(attrs);
		}
		return this;
	}

	/**
	 * 动态追加Attributes
	 * @param attrList
	 * @return
	 * 赵玉柱
	 */
	public Element addAttributes(List<Attribute> attrList)
	{
		if (attrList != null && !attrList.isEmpty())
		{
			List<Attribute> attrs = getAttributes();
			if (attrs == null)
			{
				attrs = new ArrayList<>();
			}
			for (Attribute attr : attrs)
			{
				if (!checkAttrExistByName(attr))
				{
					attr.setPath(getPath() + "/" + attr.getName());
					attrs.add(attr);
				}
			}
			if (!attrs.isEmpty())
			{
				setAttributes(attrs);
			}
		}
		return this;
	}

	public boolean chechExist(Element element)
	{
		if (element == null)
		{
			return false;
		}
		return getElementByPath(element.getPath()) != null;
	}

	public boolean checkExistByName(Element element)
	{
		if (element == null)
		{
			return false;
		}
		return getSubElementByName(element.getName()) != null;
	}

	public boolean checkAttrExistByPath(Attribute attribute)
	{
		if (attribute == null)
		{
			return false;
		}
		return getAttrByPath(attribute.getPath()) != null;
	}

	public boolean checkAttrExistByName(Attribute attribute)
	{
		if (attribute == null)
		{
			return false;
		}
		return getAttrByName(attribute.getName()) != null;
	}

	private Element reSetPath(String path)
	{
		//重置path
		setPath(path);
		List<Element> subs = getSubElements();
		//重置sub的path
		if (subs != null)
		{
			for (Element element : subs)
			{
				element.reSetPath(path + "/" + element.getName());
			}
		}
		//重置Attribute的path
		List<Attribute> attrs = getAttributes();
		if (attrs != null)
		{
			for (Attribute attr : attrs)
			{
				attr.setPath(path + "/" + attr.getName());
			}
		}
		return this;
	}

	/**
	 * 获取跟节点
	 * @return
	 * 赵玉柱
	 */
	public Element getRootElement()
	{
		Element parent = getParentElement();
		if (parent == null)
		{
			return this;
		}
		return parent.getRootElement();
	}

	/**
	 * 根据路径获取节点
	 * @param path
	 * @return
	 * 赵玉柱
	 */
	public List<Element> getElementsByPath(String path)
	{
		if (StrUtil.isStrTrimNull(path))
		{
			return null;
		}
		//去掉最前面的/
		if (path.startsWith("/"))
		{
			path = path.substring(1);
		}
		if (StrUtil.isStrTrimNull(path))
		{
			return null;
		}
		String curPath = getPath();
		List<Element> result = new ArrayList<>();
		if (path.equals(curPath))
		{
			result.add(this);
			return result;
		}
		if (path.startsWith(curPath))
		{
			//path为子节点
			String subPath = path.substring(curPath.length());
			return getSubElementsByPath(subPath);
		} else
		{
			//curPath为子节点
			Element parent = getParentElement();
			if (parent == null)
			{
				return null;
			}
			return parent.getElementsByPath(path);
		}
	}

	/**
	 * 根据路径获取第一个匹配的节点
	 * @param path
	 * @return
	 * 赵玉柱
	 */
	public Element getElementByPath(String path)
	{
		if (StrUtil.isStrTrimNull(path))
		{
			return null;
		}
		//去掉最前面的/
		if (path.startsWith("/"))
		{
			path = path.substring(1);
		}
		if (StrUtil.isStrTrimNull(path))
		{
			return null;
		}
		String curPath = getPath();
		if (path.equals(curPath))
		{
			return this;
		}
		if (path.startsWith(curPath))
		{
			//path为子节点
			String subPath = path.substring(curPath.length());
			return getSubElementByPath(subPath);
		} else
		{
			//curPath为子节点
			Element parent = getParentElement();
			if (parent == null)
			{
				return null;
			}
			return parent.getElementByPath(path);
		}
	}

	/**
	 * 获取当前节点的某一个name=name的子节点
	 * @param name
	 * @return
	 * 赵玉柱
	 */
	public List<Element> getSubElementsByName(String name)
	{
		if (StrUtil.isStrTrimNull(name))
		{
			return null;
		}
		List<Element> subs = getSubElements();
		if (subs == null || subs.size() == 0)
		{
			return null;
		}
		name = name.replaceAll("/", "");
		//去掉首尾的/符号
		if (StrUtil.isStrTrimNull(name))
		{
			return null;
		}
		if (StrUtil.isStrTrimNull(name))
		{
			return null;
		}
		List<Element> result = new ArrayList<>();
		for (Element sub : subs)
		{
			if (name.equals(sub.getName()))
			{
				result.add(sub);
			}
		}
		return result.isEmpty() ? null : result;
	}

	public Element getSubElementByName(String name)
	{
		if (StrUtil.isStrTrimNull(name))
		{
			return null;
		}
		List<Element> subs = getSubElements();
		if (subs == null || subs.size() == 0)
		{
			return null;
		}
		name = name.replaceAll("/", "");
		//去掉首尾的/符号
		if (StrUtil.isStrTrimNull(name))
		{
			return null;
		}
		if (StrUtil.isStrTrimNull(name))
		{
			return null;
		}
		for (Element sub : subs)
		{
			if (name.equals(sub.getName()))
			{
				return sub;
			}
		}
		return null;
	}

	/**
	 * 获取当前节点的子节点
	 * @param path
	 * @return
	 * 赵玉柱
	 */
	public List<Element> getSubElementsByPath(String path)
	{
		if (StrUtil.isStrTrimNull(path))
		{
			return null;
		}
		//去掉前缀的/
		if (path.startsWith("/"))
		{
			path = path.substring(1);
		}
		int p = 0;
		if ((p = path.indexOf('/')) > 0)
		{
			String subName = path.substring(0, p);
			List<Element> subs = getSubElementsByName(subName);
			if (subs == null)
			{
				return null;
			}
			String subPath = path.substring(p);
			List<Element> result = new ArrayList<>();
			for (Element sub : subs)
			{
				List<Element> subsByPath = sub.getSubElementsByPath(subPath);
				if (subsByPath != null)
				{
					result.addAll(subsByPath);
				}
			}
			return result.isEmpty() ? null : result;
		} else
		{
			return getSubElementsByName(path);
		}
	}

	public Element getSubElementByPath(String path)
	{
		if (StrUtil.isStrTrimNull(path))
		{
			return null;
		}
		//去掉前缀的/
		if (path.startsWith("/"))
		{
			path = path.substring(1);
		}
		int p = 0;
		if ((p = path.indexOf('/')) > 0)
		{
			String subName = path.substring(0, p);
			Element sub = getSubElementByName(subName);
			if (sub == null)
			{
				return null;
			}
			String subPath = path.substring(p);
			return sub.getSubElementByPath(subPath);
		} else
		{
			return getSubElementByName(path);
		}
	}

	/**
	 * 根据attr.name获取当前节点的attribute
	 * @param name
	 * @return
	 * 赵玉柱
	 */
	public Attribute getAttrByName(String name)
	{
		if (StrUtil.isStrTrimNull(name))
		{
			return null;
		}
		name = name.replaceAll("/", "");
		if (StrUtil.isStrTrimNull(name))
		{
			return null;
		}
		List<Attribute> attrs = getAttributes();
		if (attrs == null || attrs.isEmpty())
		{
			return null;
		}
		for (Attribute attr : attrs)
		{
			if (name.equals(attr.getName()))
			{
				return attr;
			}
		}
		return null;
	}

	/**
	 * 根据path获取attr
	 * @param path
	 * @return
	 * 赵玉柱
	 */
	public Attribute getAttrByPath(String path)
	{
		if (StrUtil.isStrTrimNull(path))
		{
			return null;
		}
		if (path.endsWith("/"))
		{
			path = path.substring(0, path.length() - 1);
		}
		if (StrUtil.isStrTrimNull(path))
		{
			return null;
		}
		if (path.startsWith("/"))
		{
			path = path.substring(1);
		}
		if (StrUtil.isStrTrimNull(path))
		{
			return null;
		}
		int lastP = path.lastIndexOf('/');
		if (lastP < 0 || lastP == path.length())
		{
			return null;
		}
		String attrName = path.substring(lastP + 1);
		String elemPath = path.substring(0, lastP);
		String curPath = getPath();
		if (elemPath.equals(curPath))
		{
			return getAttrByName(attrName);
		} else if (elemPath.startsWith(curPath))
		{
			String subPath = path.substring(curPath.length() + 1);
			return getSubAttrByPath(subPath);
		} else
		{
			Element parent = getParentElement();
			if (parent == null)
			{
				return null;
			}
			return parent.getAttrByPath(path);
		}
	}

	/**
	 * 获取子节点的Attr
	 * @param subPath
	 * @return
	 * 赵玉柱
	 */
	public Attribute getSubAttrByPath(String subPath)
	{
		if (StrUtil.isStrTrimNull(subPath))
		{
			return null;
		}
		if (subPath.startsWith("/"))
		{
			subPath = subPath.substring(1);
		}
		if (StrUtil.isStrTrimNull(subPath))
		{
			return null;
		}
		if (subPath.endsWith("/"))
		{
			subPath = subPath.substring(0, subPath.length() - 1);
		}
		if (StrUtil.isStrTrimNull(subPath))
		{
			return null;
		}
		int lastP = subPath.lastIndexOf('/');
		if (lastP <= 0 || lastP == subPath.length())
		{
			return getAttrByName(subPath);
		}
		String attrName = subPath.substring(lastP + 1);
		String subPaths = subPath.substring(0, lastP);
		Element sub = getSubElementByPath(subPaths);
		if (sub == null)
		{
			return null;
		}
		return sub.getAttrByName(attrName);
	}

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
