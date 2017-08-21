package com.liiwin.db;

import org.dom4j.Element;
import com.liiwin.utils.GetXmlFile;
/**
 * <p>标题： 用XML方式连接DB的缓存对象</p>
 * <p>功能： </p>
 * <p>所属模块： rootbase</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2017年8月21日 上午10:09:28</p>
 * <p>类全名：com.liiwin.db.DBElement</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class DBElement
{
	private static Element	root;

	public static Element getRoot(String filepath)
	{
		if (root != null)
		{
			return root;
		}
		return GetXmlFile.getXMLElement(filepath);
	}
}
