package com.liiwin.xsd.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： xsd节点对象</p>
 * <p>功能： </p>
 * <p>所属模块：01</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2018年4月12日 下午1:59:22</p>
 * <p>类全名：snsoft.rootbas.codedataexp.domain.XSDNode</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class XSDNode implements Serializable
{
	private static final long	serialVersionUID	= -1648700549351969576L;
	String						xpath;											//路径
	String						name;											//名称，不带前缀
	String						fullName;										//全名，带有前缀
	String						type;											//类型
	String						minOccurs;										//最小出现次数
	String						maxOccurs;										//最大出现次数
	//annotation暂时不需要此节点，此节点为注释使用
	//documentation暂时不需要此节点，此节点为注释使用
	XSDNode						parentNode;									//父节点
	List<XSDNode>				childNode;										//子节点
	List<XSDAttribute>			attributes;									//节点的属性

	public String getXpath()
	{
		return xpath;
	}

	public void setXpath(String xpath)
	{
		this.xpath = xpath;
	}

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

	public String getMinOccurs()
	{
		return minOccurs;
	}

	public void setMinOccurs(String minOccurs)
	{
		this.minOccurs = minOccurs;
	}

	public String getMaxOccurs()
	{
		return maxOccurs;
	}

	public void setMaxOccurs(String maxOccurs)
	{
		this.maxOccurs = maxOccurs;
	}

	public XSDNode getParentNode()
	{
		return parentNode;
	}

	public void setParentNode(XSDNode parentNode)
	{
		this.parentNode = parentNode;
	}

	public List<XSDNode> getChildNode()
	{
		return childNode;
	}

	public void setChildNode(List<XSDNode> childNode)
	{
		this.childNode = childNode;
	}

	public List<XSDAttribute> getAttributes()
	{
		return attributes;
	}

	public void setAttributes(List<XSDAttribute> attributes)
	{
		this.attributes = attributes;
	}

	public String getFullName()
	{
		return fullName;
	}

	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}

	/**
	 * 根据path获取node
	 * @param path
	 * @return
	 * 赵玉柱
	 */
	public XSDNode getNodeByPath(String path)
	{
		if (StrUtil.isStrTrimNull(path) || StrUtil.isStrTrimNull(name))
		{
			return null;
		}
		if (path.equals(xpath))
		{
			return this;
		}
		if (xpath.startsWith(path))
		{
			//这种情况下说明path是在xpath中，说明其path是xpath的父节点
			XSDNode parentNode2 = getParentNode();
			if (parentNode2 == null)
			{
				return null;
			}
			return parentNode2.getNodeByPath(path);
		} else if (path.startsWith(xpath))
		{
			String childPath = path.substring(xpath.length());
			XSDNode childNodeByPath = getChildNodeByPath(childPath);
			return childNodeByPath;
		} else
		{
			XSDNode parentNode2 = getParentNode();
			if (parentNode2 == null)
			{
				return null;
			}
			return parentNode2.getNodeByPath(path);
		}
	}

	/**
	 * 根据path获取子node
	 * @param path
	 * @return
	 * 赵玉柱
	 */
	public XSDNode getChildNodeByPath(String path)
	{
		if (StrUtil.isStrTrimNull(path))
		{
			return null;
		}
		if (path.startsWith("/"))
		{
			path = path.substring(1);
		}
		//		if (!path.startsWith(fullName))
		//		{
		//			return null;
		//		}
		String curName = getName();
		if (path.equals(curName))
		{
			return this;
		}
		if (path.indexOf(curName + "/") == 0)
		{
			path = path.substring(curName.length());
		}
		int p = path.indexOf('/');
		String childFullname = path;
		if (p > 0)
		{
			childFullname = childFullname.substring(0, p);
		}
		XSDNode cldNode = getNodeByFullName(childFullname, name);
		if (p > 0)
		{
			if (cldNode != null)
			{
				return cldNode.getChildNodeByPath(path.substring(p));
			}
		}
		return cldNode;
	}

	/**
	 * 追加子节点
	 * @param node
	 * @return
	 * 赵玉柱
	 */
	public XSDNode addChild(XSDNode node)
	{
		if (node == null)
		{
			return this;
		}
		List<XSDNode> childNode2 = getChildNode();
		if (childNode2 == null)
		{
			childNode2 = new ArrayList<>();
			setChildNode(childNode2);
		}
		String curXpath = getXpath();
		String childXpath = "/" + node.getName();
		if (!StrUtil.isStrTrimNull(curXpath))
		{
			childXpath = curXpath + childXpath;
		}
		node.setXpath(childXpath);
		childNode2.add(node);
		return this;
	}

	/**
	 * 追加子节点
	 * @param nodes
	 * @return
	 * 赵玉柱
	 */
	public XSDNode addChilds(List<XSDNode> nodes)
	{
		if (nodes == null || nodes.isEmpty())
		{
			return this;
		}
		List<XSDNode> childNode2 = getChildNode();
		if (childNode2 == null)
		{
			childNode2 = new ArrayList<>();
			setChildNode(childNode2);
		}
		for (XSDNode node : nodes)
		{
			String curXpath = getXpath();
			String childXpath = "/" + node.getName();
			if (!StrUtil.isStrTrimNull(curXpath))
			{
				childXpath = curXpath + childXpath;
			}
			node.setXpath(childXpath);
		}
		childNode2.addAll(nodes);
		return this;
	}

	/**
	 * 追加属性
	 * @param attr
	 * @return
	 * 赵玉柱
	 */
	public XSDNode addAttr(XSDAttribute attr)
	{
		if (attr == null)
		{
			return this;
		}
		List<XSDAttribute> attributes2 = getAttributes();
		if (attributes2 == null)
		{
			attributes2 = new ArrayList<>();
			setAttributes(attributes2);
		}
		attributes2.add(attr);
		return this;
	}

	/**
	 * 追加属性
	 * @param attrs
	 * @return
	 * 赵玉柱
	 */
	public XSDNode addAttrs(List<XSDAttribute> attrs)
	{
		if (attrs == null || attrs.isEmpty())
		{
			return this;
		}
		List<XSDAttribute> attributes2 = getAttributes();
		if (attributes2 == null)
		{
			attributes2 = new ArrayList<>();
			setAttributes(attributes2);
		}
		attributes2.addAll(attrs);
		return this;
	}

	/**
	 * 根据全名获取子节点
	 * @param fullName
	 * @return
	 * 赵玉柱
	 */
	public XSDNode getNodeByFullName(String fullName, String name)
	{
		List<XSDNode> childNode2 = getChildNode();
		if (childNode2 != null)
		{
			for (XSDNode node : childNode2)
			{
				if (node.name.equals(fullName))
				{
					return node;
				}
			}
		}
		return null;
	}

	/**
	 * 根据path与attrname获取attr节点
	 * @param path
	 * @param attrName
	 * @return
	 * 赵玉柱
	 */
	public XSDAttribute getAttrByNodePathAndAttrName(String path, String attrName)
	{
		XSDNode nodeByPath = getNodeByPath(path);
		if (nodeByPath != null)
		{
			return nodeByPath.getAttrByName(attrName);
		}
		return null;
	}

	/**
	 * 根据attrname获取attr
	 * @param attrName
	 * @return
	 * 赵玉柱
	 */
	public XSDAttribute getAttrByName(String attrName)
	{
		List<XSDAttribute> attributes2 = getAttributes();
		if (attributes2 == null || attributes2.size() == 0 || StrUtil.isStrTrimNull(attrName))
		{
			return null;
		}
		for (XSDAttribute attribute : attributes2)
		{
			if (attrName.equals(attribute.getName()))
			{
				return attribute;
			}
		}
		return null;
	}

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE) + "\n";
	}
}
