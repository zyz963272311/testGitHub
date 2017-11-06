package xyz.zyzhu.util;

import java.io.File;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;


/**
 * <p>标题： Dom4jUtil</p>
 * <p>内容： Dom4jUtil</p>
 * <p>创建时间： 2017年4月25日</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>类全名： xyz.zyzhu.util.Dom4jUtil</p>
 * <p>作者： Administrator</p>
 */
public class Dom4jUtil
{

	public static Document getXmlRead(String xmlFilePath)
	{
		SAXReader reader = new SAXReader();
		File file = new File(xmlFilePath);
		try
		{
			return reader.read(file);
		} catch (DocumentException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
