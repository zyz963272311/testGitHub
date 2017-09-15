package com.liiwin.xml;

import org.dom4j.Document;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： XMLDocument</p>
 * <p>功能： </p>
 * <p>所属模块： rootbase</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2017年9月15日 下午7:32:48</p>
 * <p>类全名：com.liiwin.xml.XMLDocument</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class XMLDocument
{
	private String	name;
	private String	filePath;
	private String	version;
	private String	encoding;
	private XMLNode	rootNode;

	public XMLDocument()
	{
	}

	/**
	 * 
	 */
	public XMLDocument(String name, String filePath, String version, String encoding, XMLNode rootNode)
	{
		this.name = name;
		this.filePath = filePath;
		this.version = version;
		this.encoding = encoding;
		this.rootNode = rootNode;
	}

	public XMLDocument(Document document)
	{
		if (document != null)
		{
			name = document.getName();
			filePath = document.getPath();
			version = document.getXMLEncoding();
			encoding = null;
			rootNode = new XMLNode(document.getRootElement(), null);
		}
	}

	public String getName()
	{
		return name;
	}

	/**
	 * 将对象转换成XML
	 * @return
	 * 赵玉柱
	 */
	public String getElementString()
	{
		StringBuffer sb = new StringBuffer();
		if (hasVersionOrEncoding())
		{
			sb.append("<?xml");
			if (StrUtil.isNoStrTrimNull(version))
			{
				sb.append(" version=\"").append(version).append("\"");
			}
			if (StrUtil.isNoStrTrimNull(encoding))
			{
				sb.append(" encoding=\"").append(encoding).append("\"");
			}
			sb.append("?>");
		}
		if (rootNode != null)
		{
			sb.append(rootNode.getNodeString());
		}
		return sb.toString();
	}

	private boolean hasVersionOrEncoding()
	{
		return StrUtil.isNoStrTrimNull(encoding) || StrUtil.isNoStrTrimNull(version);
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getFilePath()
	{
		return filePath;
	}

	public void setFilePath(String filePath)
	{
		this.filePath = filePath;
	}

	public String getEncoding()
	{
		return encoding;
	}

	public void setEncoding(String encoding)
	{
		this.encoding = encoding;
	}

	public XMLNode getRootNode()
	{
		return rootNode;
	}

	public void setRootNode(XMLNode rootNode)
	{
		this.rootNode = rootNode;
	}

	public String getVersion()
	{
		return version;
	}

	public void setVersion(String version)
	{
		this.version = version;
	}
}
