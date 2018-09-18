package com.liiwin.xsd.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultElement;
import org.dom4j.tree.DefaultText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.liiwin.utils.StrUtil;
import com.liiwin.xsd.constans.CodeDataConstans;
import com.liiwin.xsd.domain.XSDAttribute;
import com.liiwin.xsd.domain.XSDDocumnet;
import com.liiwin.xsd.domain.XSDNode;
/**
 * <p>标题： xsd工具类</p>
 * <p>功能： </p>
 * <p>所属模块： 01</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2018年4月12日 下午12:02:59</p>
 * <p>类全名：snsoft.rootbas.codedataexp.utils.XSDUtils</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class XSDUtils
{
	private static Logger logger = LoggerFactory.getLogger(XSDUtils.class);

	/**
	 * 获取xsd文档对象
	 * @param path
	 * @return
	 * 赵玉柱
	 */
	public static Document getXsdDocumnet(String path)
	{
		return getXsdDocumnet(new File(path));
	}

	/**
	 * 获取xsd文档对象
	 * @param file
	 * @return
	 * 赵玉柱
	 */
	public static Document getXsdDocumnet(File file)
	{
		SAXReader saxReader = new SAXReader();
		Document document;
		try
		{
			document = saxReader.read(file);
		} catch (DocumentException e)
		{
			throw new RuntimeException("报错内容", e);
		}
		return document;
	}

	//打包报错，找不到 ByteInputStream
	//	/**
	//	 * 获取xsd文档对象 
	//	 * @param bytes
	//	 * @return
	//	 * 赵玉柱
	//	 * @see #getXsdDocumnet(File)
	//	 */
	//	@Deprecated
	//	public static Document getXsdDocumnet(byte[] bytes)
	//	{
	//		Document document = null;
	//		ByteInputStream input = new ByteInputStream();
	//		try
	//		{
	//			SAXReader saxReader = new SAXReader();
	//			input.read(bytes);
	//			document = saxReader.read(input);
	//		} catch (Exception e)
	//		{
	//			throw new RuntimeException(e);
	//		} finally
	//		{
	//			try
	//			{
	//				input.close();
	//			} catch (IOException e)
	//			{
	//				throw new RuntimeException("报错内容", e);
	//			}
	//		}
	//		return document;
	//	}
	/**
	 * 获取根节点
	 * @param path
	 * @return
	 * 赵玉柱
	 */
	public static Element getRootElement(String path)
	{
		Document document = getXsdDocumnet(path);
		return getRootElement(document);
	}

	/**
	 * 获取根节点
	 * @param file
	 * @return
	 * 赵玉柱
	 */
	public static Element getRootElement(File file)
	{
		Document document = getXsdDocumnet(file);
		return getRootElement(document);
	}

	//去掉方法 getXsdDocumnet 方法不可用
	//	/**
	//	 * 获取根节点
	//	 * @param bytes
	//	 * @return
	//	 * 赵玉柱
	//	 */
	//	public static Element getRootElement(byte[] bytes)
	//	{
	//		Document document = getXsdDocumnet(bytes);
	//		return getRootElement(document);
	//	}
	/**
	 * 获取根节点
	 * @param document
	 * @return
	 * 赵玉柱
	 */
	public static Element getRootElement(Document document)
	{
		if (document == null)
		{
			throw new NullPointerException("xsd文档对象不可为空");
		}
		Element root = document.getRootElement();
		return root;
	}

	/**
	 * 将dom4j的Document转换成XSDDocument
	 * @param document
	 * @return
	 * 赵玉柱
	 */
	public static XSDDocumnet transDocument2XsdDocument(Document document)
	{
		if (document == null)
		{
			throw new NullPointerException("xsd文档转换异常：参数document不可为空");
		}
		XSDDocumnet xDocument = new XSDDocumnet();
		Element root = getRootElement(document);
		//namespace
		List contents = root.content();
		//elements
		//element.qname.name=element==>节点名称
		//element.qname.name=complexType==>当前节点为复杂节点，需要存在子节点
		//element.qname.name=simpleType===>当前节点为简单数据类型
		//element.qname.name=attribute===>父节点的属性
		//忽略 sequence、annotation、documentation节点
		if (contents != null && !contents.isEmpty())
		{
			for (Object content : contents)
			{
				if (content instanceof Namespace)
				{
					Namespace namespace = (Namespace) content;
					String prefix = namespace.getPrefix();
					String uri = namespace.getURI();
					if (StrUtil.isStrTrimNull(prefix))
					{
						xDocument.setXmlns(uri);
					} else
					{
						Map<String,String> xmlnsOther = xDocument.getXmlnsOther();
						if (xmlnsOther == null)
						{
							xmlnsOther = new HashMap<>();
						}
						xmlnsOther.put("xmlns:" + prefix, uri);
						xDocument.setXmlnsOther(xmlnsOther);
					}
				}
				if (content instanceof DefaultText)
				{
				}
				if (content instanceof DefaultElement)
				{
					DefaultElement element = (DefaultElement) content;
					QName qName = element.getQName();
					String name = qName.getName();
					if (StrUtil.isStrIn("sequence,annotation,documentation", name))
					{
						continue;
					}
					//					transElement2XsdNode(root, null);
				}
				//				System.out.println(type);
				//				System.out.println(content);
			}
		}
		List<Attribute> attributes = root.attributes();
		if (attributes != null && attributes.size() > 0)
		{
			for (Attribute attribute : attributes)
			{
				String name = attribute.getName();
				String value = attribute.getValue();
				if ("targetNamespace".equals(name))
				{
					xDocument.setTargetNamespace(value);
				}
				if ("elementFormDefault".equals(name))
				{
					xDocument.setElementFormDefault(value);
				}
			}
		}
		XSDNode rootNode = transDocument2XsdNode(document, root);
		xDocument.setEncoding(document.getXMLEncoding());
		xDocument.setRootNood(rootNode);
		return xDocument;
	}

	public static XSDNode transDocument2XsdNode(Document document, Element root)
	{
		List<Element> complestypes = new ArrayList<>();
		List<Element> elements = root.elements("complexType");
		if (elements != null)
		{
			complestypes.addAll(elements);
		}
		XSDNode node = transElement2XsdNode(root, null, complestypes);
		return node;
	}

	/**
	 * 将节点xsd的Element节点转换成node
	 * @param root
	 * @param parentNode
	 * @return
	 * 赵玉柱
	 */
	public static XSDNode transElement2XsdNode(Element root, XSDNode parentNode, List<Element> complexTypes)
	{
		if (root == null)
		{
			throw new NullPointerException("转换Element到XSDNode时element节点为空");
		}
		List<Element> elements = root.elements();
		if ("sequence".equals(root.getName()))
		{
			if (elements != null && parentNode != null)
			{
				for (Element e : elements)
				{
					XSDNode childNode = transElement2XsdNode(e, parentNode, complexTypes);
					parentNode.addChild(childNode);
				}
			}
		}
		XSDNode node = new XSDNode();
		QName qName = root.getQName();
		String fullName = qName.getQualifiedName();
		node.setFullName(fullName);
		if (parentNode == null)
		{
			node.setXpath("/schema");
			node.setName("schema");
		}
		node.setParentNode(parentNode);
		List<Attribute> elementAttributs = root.attributes();
		if (elementAttributs != null && elementAttributs.size() > 0)
		{
			for (Attribute attribute : elementAttributs)
			{
				String attrName = attribute.getName();
				String attrValue = attribute.getValue();
				if ("name".equals(attrName))
				{
					node.setName(attrValue);
					String xpath = "/" + attrValue;
					if (parentNode != null)
					{
						xpath = parentNode.getXpath() + xpath;
					}
					node.setXpath(xpath);
				} else if ("type".equals(attrName))
				{
					node.setType(attrValue);
					buildNodeByComplexType(node, complexTypes);
				} else if ("minOccurs".equals(attrName))
				{
					node.setMinOccurs(attrValue);
				} else if ("maxOccurs".equals(attrName))
				{
					node.setMaxOccurs(attrValue);
				}
			}
		}
		List<XSDNode> childrenNode = new ArrayList<>();
		List<XSDAttribute> attributes = new ArrayList<>();
		for (Element element : elements)
		{
			QName childQName = element.getQName();
			String childName = childQName.getName();
			if (StrUtil.isStrIn(CodeDataConstans.INGAL_ELEMENT_NAME, childName))
			{
				continue;
			} else if ("sequence".equals(childName))
			{
				List<Element> elements2 = element.elements();
				if (elements2 != null)
				{
					for (Element e : elements2)
					{
						List<Attribute> elementsAttributs = e.attributes();
						if (elementsAttributs != null && elementsAttributs.size() > 0)
						{
							for (Attribute attribute : elementsAttributs)
							{
								String attrName = attribute.getName();
								String attrValue = attribute.getValue();
								if ("name".equals(attrName))
								{
									node.setName(attrValue);
									String xpath = "/" + attrValue;
									if (parentNode != null)
									{
										xpath = parentNode.getXpath() + xpath;
									}
									node.setXpath(xpath);
								} else if ("type".equals(attrName))
								{
									node.setType(attrValue);
									buildNodeByComplexType(node, complexTypes);
								} else if ("minOccurs".equals(attrName))
								{
									node.setMinOccurs(attrValue);
								} else if ("maxOccurs".equals(attrName))
								{
									node.setMaxOccurs(attrValue);
								}
							}
						}
					}
				}
			}
		}
		for (Element element : elements)
		{
			QName childQName = element.getQName();
			String childName = childQName.getName();
			if (StrUtil.isStrIn(CodeDataConstans.INGAL_ELEMENT_NAME, childName))
			{
				continue;
			} else if ("sequence".equals(childName))
			{
				continue;
			} else if ("attribute".equals(childName))
			{
				XSDAttribute xsdAttribute = getXsdAttribute(element);
				attributes.add(xsdAttribute);
			} else if ("complexType".equals(childName))
			{
				complexTypes.add(element);
			} else if ("element".equals(childName))
			{
				XSDNode childNode = transElement2XsdNode(element, node, complexTypes);
				childrenNode.add(childNode);
			} else
			{
				logger.error("未知的节点类型" + element);
			}
		}
		if (childrenNode.size() > 0)
		{
			node.setChildNode(childrenNode);
		}
		if (attributes.size() > 0)
		{
			node.setAttributes(attributes);
		}
		return node;
	}

	/**
	 * 根据node和complexTypes组装node节点
	 * @param node
	 * @param complexTypes
	 * 赵玉柱
	 */
	public static void buildNodeByComplexType(XSDNode node, List<Element> complexTypes)
	{
		String type = node.getType();
		boolean simpleType = isSimpleType(type, true);
		if (simpleType)
		{
			return;
		}
		Element complenTypeByType = getComplenTypeByType(complexTypes, type);
		if (complenTypeByType == null)
		{
			throw new RuntimeException("根据type[" + type + "]未查询到xsd中complexType节点name为[" + type + "]的节点");
		}
		List<Element> elements = complenTypeByType.elements();
		if (elements == null || elements.isEmpty())
		{
			throw new RuntimeException("xsd的complexType节点配置错误，complexType节点下必须存在子节点");
		}
		for (Element element : elements)
		{
			QName qName = element.getQName();
			//sequence--表示当前类型的节点下的element或attribute需要按照顺序
			//element
			//attribute
			String name = qName.getName();
			if (isIgnoreElement(name))
			{
				continue;
			} else if ("element".equals(name))
			{
				//节点
				XSDNode childElement = transElement2XsdNode(element, node, complexTypes);
				node.addChild(childElement);
			} else if ("attribute".equals(name))
			{
				//属性
				XSDAttribute xsdAttribute = getXsdAttribute(element);
				node.addAttr(xsdAttribute);
			} else if ("sequence".equals(name))
			{
				List<Element> elements2 = element.elements();
				for (Element element2 : elements2)
				{
					QName qName2 = element2.getQName();
					String name2 = qName2.getName();
					if ("element".equals(name2))
					{
						XSDNode childElement = transElement2XsdNode(element2, node, complexTypes);
						node.addChild(childElement);
					} else if ("attribute".equals(name2))
					{
						XSDAttribute xsdAttribute = getXsdAttribute(element2);
						node.addAttr(xsdAttribute);
					}
				}
			}
		}
	}

	/**
	 * 将xsd的element转换成Attribute
	 * @param element
	 * @return
	 * 赵玉柱
	 */
	public static XSDAttribute getXsdAttribute(Element element)
	{
		if (element == null)
		{
			throw new NullPointerException("获取xsd的属性时，element参数不可为空");
		}
		XSDAttribute xsdAttribute = new XSDAttribute();
		List<Attribute> attributes = element.attributes();
		List<Element> elements = element.elements();
		for (Attribute attribute : attributes)
		{
			String name = attribute.getName();
			String value = attribute.getValue();
			if ("name".equals(name))
			{
				xsdAttribute.setName(value);
			} else if ("use".equals(name))
			{
				xsdAttribute.setUse(value);
			}
		}
		if (elements != null && elements.size() > 0)
		{
			for (Element childElement : elements)
			{
				QName qName = childElement.getQName();
				String childName = qName.getName();
				//				if (StrUtil.isStrIn("annotation,documentation,sequence", childName, ','))
				if (isSimpleType(childName, true))
				{
					continue;
				}
				if ("simpleType".equals(childName))
				{
					List<Element> elementsRel = childElement.elements("restriction");
					if (elementsRel != null && elementsRel.size() > 0)
					{
						List<Attribute> attributesRel = elementsRel.get(0).attributes();
						if (attributesRel != null && attributesRel.size() > 0)
						{
							for (Attribute attribute : attributesRel)
							{
								if ("base".equals(attribute.getName()))
								{
									xsdAttribute.setType(attribute.getValue());
								}
							}
						}
					}
				}
			}
		}
		return xsdAttribute;
	}

	/**
	 * 根据name获取复杂类型
	 * @param complexTypes
	 * @param type
	 * @return
	 * 赵玉柱
	 */
	public static Element getComplenTypeByType(List<Element> complexTypes, String type)
	{
		if (StrUtil.isStrTrimNull(type) || complexTypes == null)
		{
			return null;
		}
		for (Element complenType : complexTypes)
		{
			Attribute attribute = complenType.attribute("name");
			if (attribute == null)
			{
				return null;
			}
			if (type.equals(attribute.getValue()))
			{
				return complenType;
			}
		}
		return null;
	}

	/**
	 * 判断是否是简单类型，默认抛出异常
	 * @param type
	 * @return
	 * 赵玉柱
	 */
	public static boolean isSimpleType(String type)
	{
		return isSimpleType(type, true);
	}

	/**
	 * 判断是否是简单类型
	 * @param type
	 * @param isThrow
	 * @return
	 * 赵玉柱
	 */
	public static boolean isSimpleType(String type, boolean isThrow)
	{
		if (StrUtil.isStrTrimNull(type))
		{
			if (isThrow)
			{
				throw new RuntimeException("type不可为空");
			}
			return false;
		}
		if (StrUtil.isStrIn("integer,string,boolean", type))
		{
			return true;
		}
		return false;
	}

	/**
	 * 获取忽略的节点名称
	 * @return
	 * 赵玉柱
	 */
	public static String getIgnoreElement()
	{
		return CodeDataConstans.INGAL_ELEMENT_NAME;
	}

	/**
	 * 是否是忽略校验的节点
	 * @param name
	 * @return
	 * 赵玉柱
	 */
	public static boolean isIgnoreElement(String name)
	{
		return StrUtil.isStrIn(getIgnoreElement(), name);
	}

	/**
	 * 根据path获取document的naode节点
	 * @param document
	 * @param path
	 * @return
	 * 赵玉柱
	 */
	public static XSDNode getDocumentNodeByPath(XSDDocumnet document, String path, String name)
	{
		if (document == null)
		{
			return null;
		}
		return document.getNodeByPathAndName(path, name);
	}

	/**
	 * 根据node和path获取节点信息
	 * @param node
	 * @param path
	 * @return
	 * 赵玉柱
	 */
	public static XSDNode getNodeByPath(XSDNode node, String path)
	{
		if (node == null)
		{
			return null;
		}
		return node.getNodeByPath(path);
	}

	/**
	 * 根据document node的path，attr的name获取节点的属性信息
	 * @param document
	 * @param path
	 * @param attrName
	 * @return
	 * 赵玉柱
	 */
	public static XSDAttribute getDocumentAttrByNodePathAndAttrName(XSDDocumnet document, String path, String attrName)
	{
		if (document == null)
		{
			return null;
		}
		return document.getAttrByNodePathAndAttrName(path, attrName);
	}

	/**
	 * 根据节点，节点路径 属性名称 获取节点的属性信息
	 * @param node
	 * @param path
	 * @param attrName
	 * @return
	 * 赵玉柱
	 */
	public static XSDAttribute getAttrByNodePathAndAttrName(XSDNode node, String path, String name, String attrName)
	{
		if (node == null)
		{
			return null;
		}
		return node.getAttrByNodePathAndAttrName(path, attrName);
	}
}
