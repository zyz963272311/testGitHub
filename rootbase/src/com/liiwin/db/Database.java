package com.liiwin.db;

import java.sql.Connection;
import org.dom4j.Element;
import com.liiwin.utils.GetXmlFile;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年5月22日 下午2:24:26</p>
 * <p>类全名：com.liiwin.db.Database</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Database
{
	private Connection	conn;

	public Database(String databaseName)
	{
		Element root = GetXmlFile.getXMLElement("resources/cfgs/databases.xml");
		if (root == null)
		{
			throw new RuntimeException("获取数据库异常，databases.xml");
		}
	}

	public static void main(String[] args)
	{
	}
}
