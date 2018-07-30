package com.liiwin.xml;

/**
 * <p>标题： 根据XSD文件校验XML文件是否合法</p>
 * <p>功能： </p>
 * <p>所属模块： rootbas</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2017年9月15日 下午11:05:02</p>
 * <p>类全名：com.liiwin.xml.XMLValidation</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class XMLValidation
{
	public static boolean validateXMLSchema(String xsdPath, String xmlPath)
	{
//		SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//		try
//		{
//			Schema schema = factory.newSchema(new File(xsdPath));
//			Validator validator = schema.newValidator();
//			validator.validate(new StreamSource(new File(xmlPath)));
//		} catch (SAXException | IOException e)
//		{
//			throw new RuntimeException("报错内容", e);
//		}
		return false;
	}
}
