package com.liiwin.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.dom4j.Attribute;
import org.dom4j.Element;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： XML节点</p>
 * <p>功能： </p>
 * <p>所属模块： rootbase</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2017年9月15日 下午7:34:32</p>
 * <p>类全名：com.liiwin.xml.XMLNode</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class XMLNode
{
	private String				name;
	private Map<String,Object>	attrs;
	private String				value;
	private List<XMLNode>		childrenNode;
	private XMLNode				parent;

	public XMLNode()
	{
	}

	public XMLNode(String name, Map<String,Object> attrs, String value, List<XMLNode> childrenNode, XMLNode parent)
	{
		this.name = name;
		this.attrs = attrs;
		this.value = value;
		this.childrenNode = childrenNode;
		this.parent = parent;
	}

	public XMLNode(Element element, XMLNode parent)
	{
		if (element != null)
		{
			this.name = element.getName();
			this.value = element.getText();
			this.parent = parent;
			attrs = attrList2Attrs(element.attributes());
			List<Element> children = element.elements();
			if (children != null && !children.isEmpty())
			{
				for (Element child : children)
				{
					add(new XMLNode(child, this));
				}
			}
		}
	}

	private void add(XMLNode node)
	{
		if (childrenNode == null)
		{
			childrenNode = new ArrayList<>();
		}
		childrenNode.add(node);
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * 根据当前节点的某一个属性名称获取属性值
	 * @param attrName
	 * @return
	 * 赵玉柱
	 */
	public Object getAttrValue(String attrName)
	{
		if (attrs == null || attrs.isEmpty())
		{
			return null;
		} else
		{
			return attrs.get(attrName);
		}
	}

	public String getNodeString()
	{
		int level = getLevel();
		String prefix = StrUtil.strcat(null, null, level, ' ');
		prefix = StrUtil.obj2str(prefix, "");
		StringBuffer sb = new StringBuffer(level > 0 ? "\n" : "");
		sb.append(prefix);
		sb.append("<").append(name);
		if (hasAttrs())
		{
			for (Entry<String,Object> attrEntry : attrs.entrySet())
			{
				String attrName = attrEntry.getKey();
				String attrValue = StrUtil.obj2str(attrEntry.getValue());
				sb.append(" ").append(attrName).append("=").append(attrValue);
			}
		}
		if (hasChiildrenOrValue())
		{
			sb.append(">");
			if (StrUtil.isNoStrTrimNull(value))
			{
				sb.append(value);
			}
			if (hasCheldrenNodes())
			{
				for (XMLNode node : childrenNode)
				{
					sb.append(node.getNodeString());
				}
				sb.append("\n").append(prefix);
			}
			sb.append("</").append(name).append(">");
		} else
		{
			sb.append("/>");
		}
		return sb.toString();
	}

	/**
	 * 获取当前节点的等级
	 * @return
	 * 赵玉柱
	 */
	public int getLevel()
	{
		if (hasParent())
		{
			return parent.getLevel() + 1;
		}
		return 0;
	}

	/**
	 * 有无子节点或者value
	 * @return
	 * 赵玉柱
	 */
	private boolean hasChiildrenOrValue()
	{
		return hasCheldrenNodes() || StrUtil.isNoStrTrimNull(value);
	}

	/**
	 * 获取当前节点是否有属性
	 * @return
	 * 赵玉柱
	 */
	public boolean hasAttrs()
	{
		return !(attrs == null || attrs.isEmpty());
	}

	/**
	 * 获取当前节点是否还有子节点
	 * @return
	 * 赵玉柱
	 */
	public boolean hasCheldrenNodes()
	{
		return !(childrenNode == null || childrenNode.isEmpty());
	}

	public Set<String> getAttrNames()
	{
		if (hasAttrs())
		{
			attrs.keySet();
		}
		return null;
	}

	/**
	 * attrList 转换成 attrs
	 * @param attrList
	 * @return
	 * 赵玉柱
	 */
	public Map<String,Object> attrList2Attrs(List<Attribute> attrList)
	{
		Map<String,Object> attrs = null;
		if (attrList != null)
		{
			attrs = new HashMap<String,Object>();
			for (Attribute attr : attrList)
			{
				String key = attr.getName();
				if (StrUtil.isNoStrTrimNull(key))
				{
					attrs.put(key, attr.getValue());
				}
			}
		}
		return attrs;
	}

	public Map<String,Object> getAttrs()
	{
		return attrs;
	}

	/**
	 * 当前节点是否含有父节点
	 * @return
	 * 赵玉柱
	 */
	public boolean hasParent()
	{
		return parent != null;
	}

	public void setAttrs(Map<String,Object> attrs)
	{
		this.attrs = attrs;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public List<XMLNode> getChildrenNode()
	{
		return childrenNode;
	}

	public void setChildrenNode(List<XMLNode> childrenNode)
	{
		this.childrenNode = childrenNode;
	}

	public XMLNode getParent()
	{
		return parent;
	}

	public void setParent(XMLNode parent)
	{
		this.parent = parent;
	}
}
