package com.liiwin.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.dom4j.Element;
import com.liiwin.utils.GetXmlFile;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： Database工具</p>
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
	private final int	type;

	public Database(String databaseName)
	{
		Element root = GetXmlFile.getXMLElement("resources/cfgs/databases.xml");
		if (root == null)
		{
			throw new RuntimeException("获取数据库异常，databases.xml");
		}
		List<Element> elements = new ArrayList<>();
		GetXmlFile.getXMLElementByNameAndAttr(elements, root, "database", "name", databaseName);
		if (elements.isEmpty())
		{
			GetXmlFile.getXMLElementByNameAndAttr(elements, root, "database", "default", "1");
			if (elements.isEmpty())
			{
				throw new RuntimeException("获取数据库异常，databases.xml中未找到name=【" + databaseName + "】的database配置");
			}
		}
		if (elements.size() > 1)
		{
			throw new RuntimeException("获取数据库异常，databases.xml中找到多个name=【" + databaseName + "】的database配置");
		}
		Element database = elements.get(0);
		this.type = StrUtil.obj2int(database.attributeValue("type"));
		databaseName = database.attributeValue("name");
		String url = database.attributeValue("url");
		String driver = database.attributeValue("driver");
		String user = database.attributeValue("user");
		String password = database.attributeValue("password");
		if (StrUtil.isNullIn(url, driver, user, password))
		{
			throw new RuntimeException("获取数据库异常，databases.xml中name=【" + databaseName + "】的database的url,driver,user,password必须配置");
		}
		getConnection(databaseName, url, driver, user, password);
		getConnection(databaseName, url, driver, user, password);
	}

	private void getConnection(String databaseName, String url, String driver, String user, String password)
	{
		if (this.conn == null)
		{
			try
			{
				Class.forName(driver);
				if (!url.endsWith("/"))
				{
					url += "/";
				}
				this.conn = DriverManager.getConnection(url + databaseName + ".testtable", user, password);
				if (this.conn != null)
				{
					System.out.println("连接数据库成功");
				}
			} catch (Exception e)
			{
				e.printStackTrace();
				throw new RuntimeException("数据库连接异常" + e.getMessage());
			}
		}
	}

	public ResultSet getResultSet(String sql)
	{
		ResultSet rs = null;
		try
		{
			Statement statement = this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = statement.executeQuery(sql);
		} catch (SQLException e)
		{
			throw new RuntimeException("报错内容", e);
		}
		return rs;
	}

	public int getType()
	{
		return this.type;
	}

	public static void main(String[] args)
	{
		new Database("zyztestzzz");
	}
}
