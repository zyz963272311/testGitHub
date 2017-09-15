package com.liiwin.xml;

import org.dom4j.Document;
import com.liiwin.utils.GetXmlFile;
/**
 * <p>标题： XML文件工具类</p>
 * <p>功能： </p>
 * <p>所属模块： rootbase</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2017年9月15日 下午5:42:11</p>
 * <p>类全名：com.liiwin.xml.XMLUtils</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class XMLUtils
{
	public static void main(String[] args)
	{
		String filePath = "C:/Users/x250-2/Desktop/任务列表/2017年08月30日任务/661121_20170905002.xml";
		Document xmlDocument = GetXmlFile.getXmlDocumentByAbsulatePath(filePath);
		if (xmlDocument == null)
		{
			throw new RuntimeException("根据XML文件未获取到Document信息");
		}
		System.out.println(xmlDocument.getClass());
		System.out.println(xmlDocument.asXML());
		XMLDocument document = new XMLDocument(xmlDocument);
		System.out.println(document.getElementString());
	}
}
