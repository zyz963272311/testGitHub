package com.liiwin.doc.utils;

import java.io.File;
import com.liiwin.doc.Attribute;
import com.liiwin.doc.Document;
import com.liiwin.doc.Element;
import com.liiwin.xmlnew.utils.XmlDocumentUtils;
/**
 * <p>标题： Doc工具类</p>
 * <p>功能： </p>
 * <p>所属模块： Doc工具类</p>
 * <p>版权： Copyright © 2018 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2018年5月7日 上午10:58:48</p>
 * <p>类全名：com.liiwin.doc.utils.DocumentUtils</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public abstract class DocumentUtils
{
	public static DocumentUtils getXmlUtils()
	{
		return new XmlDocumentUtils();
	}

	/**
	 * 根据path获取Element
	 * @param document
	 * @param path
	 * @return
	 * 赵玉柱
	 */
	public static Element getDocElementByPath(Document document, String path)
	{
		return document == null ? null : document.getElementByPath(path);
	}

	/**
	 * 根据path获取Element
	 * @param element
	 * @param path
	 * @return
	 * 赵玉柱
	 */
	public static Element getElementByPath(Element element, String path)
	{
		return element == null ? null : element.getElementByPath(path);
	}

	/**
	 * 根据path获取当前节点的子节点
	 * @param element
	 * @param subPath
	 * @return
	 * 赵玉柱
	 */
	public static Element getSubElementByPath(Element element, String subPath)
	{
		return element == null ? null : element.getSubElementByPath(subPath);
	}

	/**
	 * 根据name获取当前节点的子节点
	 * @param element
	 * @param name
	 * @return
	 * 赵玉柱
	 */
	public static Element getSubElementByName(Element element, String name)
	{
		return element == null ? null : element.getSubElementByName(name);
	}

	/**
	 * 根据path获取当前Doc的Element的Attr
	 * @param document
	 * @param path
	 * @return
	 * 赵玉柱
	 */
	public static Attribute getDocAttrByPath(Document document, String path)
	{
		return document == null ? null : document.getAttrByPath(path);
	}

	/**
	 * 根据Path获取当前Elem的Attr
	 * @param element
	 * @param path
	 * @return
	 * 赵玉柱
	 */
	public static Attribute getAttrByPath(Element element, String path)
	{
		return element == null ? null : element.getAttrByPath(path);
	}

	/**
	 * 根据name获取当前节点的Attr
	 * @param element
	 * @param name
	 * @return
	 * 赵玉柱
	 */
	public static Attribute getAttrByName(Element element, String name)
	{
		return element == null ? null : element.getAttrByName(name);
	}

	/**
	 * 根据path获取当前节点的子节点的Attr
	 * @param element
	 * @param subPath
	 * @return
	 * 赵玉柱
	 */
	public static Attribute getSubAttrByPath(Element element, String subPath)
	{
		return element == null ? null : element.getSubAttrByPath(subPath);
	}

	/**
	 * 根据文件组装Document
	 * @param file
	 * @return
	 * 赵玉柱
	 */
	public abstract Document buildDocumentByFile(File file);

	/**
	 * 根据文件路径组装Document
	 * @param path
	 * @return
	 * 赵玉柱
	 */
	public abstract Document buildDocumentByPath(String path);

	/**
	 * 根据内容组装Document
	 * @param content
	 * @return
	 * 赵玉柱
	 */
	public abstract Document buildDocumentByContent(String content);
}
