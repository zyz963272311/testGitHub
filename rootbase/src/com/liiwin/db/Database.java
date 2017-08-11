package com.liiwin.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.dom4j.Element;
import com.liiwin.utils.GetXmlFile;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： Database工具  </p>
 * <p>功能： </p>
 * <p>所属模块： TODO </p>
 * <p>版权： Copyright © 2017 SNSOFT </p>
 * <p>公司: 北京南北天地科技股份有限公司 </p>
 * <p>创建日期：2017年5月22日 下午2:24:26 </p>
 * <p>类全名：com.liiwin.db.Database </p>
 * 作者：赵玉柱 
 * 初审： 
 * 复审： 
 * 监听使用界面:
 * @version 8.0
 */
public class Database
{
	private String		databaseName;
	private String		url;
	private String		driver;
	private String		user;
	private String		password;
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
		this.databaseName = database.attributeValue("name");
		this.url = database.attributeValue("url");
		this.driver = database.attributeValue("driver");
		this.user = database.attributeValue("user");
		this.password = database.attributeValue("password");
		if (StrUtil.isNullIn(url, driver, user, password))
		{
			throw new RuntimeException("获取数据库异常，databases.xml中name=【" + databaseName + "】的database的url,driver,user,password必须配置");
		}
		getConnection(this);
	}

	private void getConnection(Database db)
	{
		if (db != null)
		{
			getConnection(db.getDatabaseName(), db.getUrl(), db.getDriver(), db.getUser(), db.getPassword());
		}
	}

	private void getConnection(String databaseName, String url, String driver, String user, String password)
	{
		if (this.conn == null)
		{
			try
			{
				if (StrUtil.isNullIn(databaseName, url, driver, user, password))
				{
					throw new RuntimeException("参数【databaseName,url,driver,user,password】不可为空");
				}
				Class.forName(driver);
				if (!url.endsWith("/"))
				{
					url += "/";
				}
				this.conn = DriverManager.getConnection(url + databaseName, user, password);
				if (this.conn != null)
				{
					conn.setAutoCommit(false);
					System.out.println("连接数据库成功");
				}
			} catch (Exception e)
			{
				e.printStackTrace();
				throw new RuntimeException("数据库连接异常" + e.getMessage());
			}
		}
	}

	public Map<String,Object> getMapFromSql(String sql)
	{
		List<Map<String,Object>> resultList = getListMapFromSql(sql);
		if (resultList != null && !resultList.isEmpty())
		{
			return resultList.get(0);
		}
		return null;
	}

	public List<Map<String,Object>> getListMapFromSql(String sql)
	{
		List<Map<String,Object>> resultList = new ArrayList<>();
		try
		{
			ResultSet rs = getResultSet(sql);
			if (rs.next() == false)
			{
				return resultList;
			}
			rs.beforeFirst();
			ResultSetMetaData rsm = rs.getMetaData();
			int col = rsm.getColumnCount();
			String[] column = new String[col];
			for (int i = 0; i < col; i++)
			{
				column[i] = rsm.getColumnName(i + 1);
			}
			while (rs.next())
			{
				Map<String,Object> rowValue = new HashMap<String,Object>();
				for (int i = 0; i < col; i++)
				{
					rowValue.put(column[i], rs.getObject(i + 1));
				}
				resultList.add(rowValue);
			}
		} catch (SQLException e)
		{
			throw new RuntimeException("报错内容", e);
		}
		return resultList;
	}

	public ResultSet sqlSelect(String sql, Map<String,Object> params)
	{
		sql = SqlUtil.sqlBindParams(this, sql, params);
		return sqlSelect(sql);
	}

	public ResultSet sqlSelect(String sql)
	{
		return getResultSet(sql);
	}

	public int insertTable(String table, Map<String,Object> params)
	{
		if (StrUtil.isNoStrTrimNull(table) && params != null && !params.isEmpty())
		{
			StringBuffer sb = new StringBuffer("insert into ").append(table).append("(");
			Set<String> keys = params.keySet();
			StringBuffer tempValues = new StringBuffer(" values(");
			for (String key : keys)
			{
				sb.append(key).append(",");
				tempValues.append(":").append(key).append(",");
			}
			sb.setLength(sb.length() - 1);
			sb.append(") ");
			tempValues.setLength(tempValues.length() - 1);
			tempValues.append(") ");
			String sql = sb.toString() + tempValues.toString();
			return insert(sql, params);
		} else
		{
			throw new RuntimeException("表名不可为空切要插入的数据不可为空");
		}
	}

	public int insert(String sql, Map<String,Object> params)
	{
		String sqlTemp = SqlUtil.sqlBindParams(this, sql, params);
		return insert(sqlTemp);
	}

	public int insert(String sql)
	{
		return update(sql);
	}

	public int update(String sql, Map<String,Object> params)
	{
		sql = SqlUtil.sqlBindParams(this, sql, params);
		return update(sql);
	}

	public int update(String sql)
	{
		int result = -1;
		try
		{
			if (connIsOpen())
			{
				Statement statement = this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				result = statement.executeUpdate(sql);
			} else
			{
				throw new RuntimeException("连接已关闭");
			}
		} catch (SQLException e)
		{
			throw new RuntimeException("报错内容", e);
		}
		return result;
	}

	public ResultSet getResultSet(String sql)
	{
		ResultSet rs = null;
		try
		{
			if (connIsOpen())
			{
				Statement statement = this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				rs = statement.executeQuery(sql);
			} else
			{
				throw new RuntimeException("连接已关闭");
			}
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

	public boolean connIsOpen()
	{
		boolean isOpen = false;
		if (this != null)
		{
			try
			{
				if (this.conn != null && !this.conn.isClosed())
				{
					isOpen = true;
				}
			} catch (SQLException e)
			{
				throw new RuntimeException("报错内容", e);
			}
		}
		return isOpen;
	}

	public void close()
	{
		if (this != null)
		{
			try
			{
				if (this.conn != null && !this.conn.isClosed())
				{
					this.conn.close();
				}
			} catch (SQLException e)
			{
				throw new RuntimeException("报错内容", e);
			}
		}
	}

	public void commit()
	{
		try
		{
			if (getConn() != null && !getConn().isClosed())
			{
				getConn().commit();
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
			throw new RuntimeException("数据提交失败" + e.getMessage());
		}
	}

	public void rollback(boolean rollback)
	{
		rollback(rollback, true);
	}

	public void rollback(boolean rollback, boolean closeDB)
	{
		try
		{
			if (rollback)
			{
				getConn().rollback();
			}
			if (closeDB)
			{
				close();
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
			throw new RuntimeException("回滚失败" + e.getMessage());
		}
	}

	public String getDatabaseName()
	{
		return databaseName;
	}

	public void setDatabaseName(String databaseName)
	{
		this.databaseName = databaseName;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getDriver()
	{
		return driver;
	}

	public void setDriver(String driver)
	{
		this.driver = driver;
	}

	public String getUser()
	{
		return user;
	}

	public void setUser(String user)
	{
		this.user = user;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public Connection getConn()
	{
		return conn;
	}

	public static void main(String[] args)
	{
		Database db = new Database("zyztest");
		List<Map<String,Object>> listMapFromSql = db.getListMapFromSql("select * from testtable");
		for (Map<String,Object> map : listMapFromSql)
		{
			System.out.println(map);
		}
	}
}
