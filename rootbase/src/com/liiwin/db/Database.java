package com.liiwin.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.liiwin.createdb.Table;
import com.liiwin.utils.GetXmlFile;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： Database工具 </p>
 * <p>功能：</p>
 * <p>所属模块： rootbase</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年5月22日 下午2:24:26</p>
 * <p>类全名：com.liiwin.db.Database</p>
 * 作者：赵玉柱 初审： 复审： 监听使用界面:
 * 
 * @version 8.0
 */
public class Database
{
	private static Logger	logger	= LoggerFactory.getLogger(Database.class);
	private AtomicBoolean	isWrite	= new AtomicBoolean(true);
	//基础属性
	private String			databaseName;
	private String			url;
	private String			driver;
	private String			user;
	private String			password;
	protected int			type;
	//连接属性
	protected Connection	conn;
	private int				minConnects;										//最小连接数
	private int				maxConnects;										//最大连接数
	private int				initConnections;									//初始化线程个数
	private long			connTimeOut;										//重复获得连接的频率
	private int				maxActiveConnections;								//允许的最大连接数
	private long			connectionTimeOut;									//最大超时时间默认20分钟
	private boolean			isCurrentConnection;								//是否获取当前的链接
	private boolean			isCheckPool;										//是否检查连接池
	private long			lazyCheck;											//延迟多长时间开始检查
	private long			periodCheck;										//检查频率

	protected Database()
	{
	}

	/**
	 * 根据DBname获取DB，此DB需要在下面的XML文件中进行配置
	 * @param databaseName
	 */
	public Database(String databaseName)
	{
		Element root = DBElement.getRoot("/resources/cfg/databases.xml");
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
		this.minConnects = StrUtil.obj2int(database.attributeValue("minConnects"), 1);
		this.maxConnects = StrUtil.obj2int(database.attributeValue("maxConnects"), 10);
		this.initConnections = StrUtil.obj2int(database.attributeValue("initConnections"), 5);
		this.connTimeOut = StrUtil.obj2long(database.attributeValue("connTimeOut"), 1000);
		this.maxActiveConnections = StrUtil.obj2int(database.attributeValue("maxActiveConnections"), 100);
		this.connectionTimeOut = StrUtil.obj2int(database.attributeValue("connectionTimeOut"), 1000 * 60 * 20);
		this.isCurrentConnection = StrUtil.obj2bool(database.attributeValue("isCurrentConnection"), true);
		this.isCheckPool = StrUtil.obj2bool(database.attributeValue("isCheckPool"), true);
		this.lazyCheck = StrUtil.obj2int(database.attributeValue("lazyCheck"), 1000 * 60 * 60);
		this.periodCheck = StrUtil.obj2int(database.attributeValue("periodCheck"), 1000 * 60 * 60);
		if (StrUtil.isNullIn(url, driver, user, password))
		{
			throw new RuntimeException("获取数据库异常，databases.xml中name=【" + databaseName + "】的database的url,driver,user,password必须配置");
		}
		getConnection(this);
	}

	/**
	 * 获取数据库连接
	 * @param db
	 * 赵玉柱
	 */
	public void getConnection(Database db)
	{
		if (db != null)
		{
			getConnection(db.getDatabaseName(), db.getUrl(), db.getDriver(), db.getUser(), db.getPassword());
		}
	}

	/**
	 * 获取数据库连接
	 * @param databaseName
	 * @param url
	 * @param driver
	 * @param user
	 * @param password
	 * 赵玉柱
	 */
	public void getConnection(String databaseName, String url, String driver, String user, String password)
	{
		if (getConn() == null)
		{
			try
			{
				if (StrUtil.isNullIn(databaseName, url, driver, user, password))
				{
					throw new RuntimeException("参数【databaseName,url,driver,user,password】不可为空");
				}
				if (!DriverClassCache.loadDriver(driver))
				{
					throw new RuntimeException("加载驱动：" + driver + "失败");
				}
				//				Class.forName(driver);
				if (!url.endsWith("/"))
				{
					url += "/";
				}
				this.conn = DriverManager.getConnection(url + databaseName, user, password);
				//				this.conn = DriverManager.getConnection(url + "/" + databaseName + "?user=" + user + "&pasword=" + password + "&useUnicode=true&characterEncoding=utf-8");
				if (this.conn != null)
				{
					logger.error("数据库连接成功");
				}
			} catch (Exception e)
			{
				e.printStackTrace();
				throw new RuntimeException("数据库连接异常" + e.getMessage());
			}
		}
	}

	/**
	 * 根据sql获取Map
	 * @param sql
	 * @param params
	 * @return
	 * 赵玉柱
	 */
	public Map<String,Object> getMapFromSql(String sql, Map<String,Object> params)
	{
		List<Object> paramsList = new ArrayList<>();
		sql = SqlUtil.sqlBindParams(this, sql, params, paramsList);
		return getMapFromSqlByListParam(sql, paramsList);
	}

	/**
	 * 
	 * @param sql
	 * @param paramsList
	 * @return
	 * 赵玉柱
	 */
	public Map<String,Object> getMapFromSqlByListParam(String sql, List<Object> paramsList)
	{
		List<Map<String,Object>> listMap = getListMapFromSqlByListParam(sql, paramsList);
		if (listMap == null || listMap.isEmpty())
		{
			return null;
		}
		return listMap.get(0);
	}

	/**
	 * 根据sql
	 * @param sql
	 * @return
	 * 赵玉柱
	 */
	public Map<String,Object> getMapFromSql(String sql)
	{
		logger.error("执行sql[" + sql + "]");
		List<Map<String,Object>> resultList = getListMapFromSql(sql);
		if (resultList != null && !resultList.isEmpty())
		{
			return resultList.get(0);
		}
		return null;
	}

	/**
	 * 根据DB获取list
	 * @param sql
	 * @param params
	 * @return
	 * 赵玉柱
	 */
	public List<Map<String,Object>> getListMapFromSql(String sql, Map<String,Object> params)
	{
		List<Object> paramsList = new ArrayList<>();
		sql = SqlUtil.sqlBindParams(this, sql, params, paramsList);
		return getListMapFromSqlByListParam(sql, paramsList);
	}

	public List<Map<String,Object>> getListMapFromSqlByListParam(String sql, List<Object> paramsList)
	{
		logger.error("执行sql[" + sql + "],参数" + paramsList);
		List<Map<String,Object>> resultList = new ArrayList<>();
		try
		{
			ResultSet rs = getResultSet(sql, paramsList);
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
				column[i] = rsm.getColumnLabel(i + 1);
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

	/**
	 * 根据sql获取List
	 * @param sql
	 * @return
	 * 赵玉柱
	 */
	public List<Map<String,Object>> getListMapFromSql(String sql)
	{
		logger.error("执行sql[" + sql + "]");
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
				column[i] = rsm.getColumnLabel(i + 1);
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

	/**
	 * 查询数据，返回resultset
	 * @param sql
	 * @param params
	 * @return
	 * 赵玉柱
	 */
	public ResultSet sqlSelect(String sql, Map<String,Object> params)
	{
		List<Object> paramsList = new ArrayList<>();
		sql = SqlUtil.sqlBindParams(this, sql, params, paramsList);
		return sqlSelect(sql);
	}

	/**
	 * 查询数据，获取resultset
	 * @param sql
	 * @return
	 * 赵玉柱
	 */
	public ResultSet sqlSelectWithParamList(String sql, List<Object> paramsList)
	{
		return getResultSet(sql, paramsList);
	}

	public ResultSet sqlSelect(String sql)
	{
		return getResultSet(sql, null);
	}

	/**
	 * 表插入
	 * @param table
	 * @param paramsList
	 * @return
	 * 赵玉柱
	 */
	public int insertTableList(String table, List<Map<String,Object>> paramsList)
	{
		int result = 0;
		if (StrUtil.isNoStrTrimNull(table) && paramsList != null && !paramsList.isEmpty())
		{
			for (Map<String,Object> params : paramsList)
			{
				int insert = insertTable(table, params);
				result += insert;
			}
		}
		return result;
	}

	/**
	 * 插入表
	 * @param table
	 * @param params
	 * @return
	 * 赵玉柱
	 */
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
			throw new RuntimeException("表名不可为空且要插入的数据不可为空");
		}
	}

	/**
	 * 更新表
	 * @param table
	 * @param oldValues
	 * @param newValues
	 * @return
	 * 赵玉柱
	 */
	public int updateTable(String table, Map<String,Object> oldValues, Map<String,Object> newValues)
	{
		if (StrUtil.isNoStrTrimNull(table) && oldValues != null && !oldValues.isEmpty() && newValues != null && !newValues.isEmpty())
		{
			StringBuffer sb = new StringBuffer("update ").append(table).append(" set ");
			Set<String> keys = newValues.keySet();
			int length = keys.size();
			int i = 0;
			for (String key : keys)
			{
				sb.append(key).append("=").append(newValues.get(key));
				if (i != length - 1)
				{
					sb.append(",");
				}
				i++;
			}
			sb.append(" where ");
			Set<String> wKeys = oldValues.keySet();
			length = wKeys.size();
			i = 0;
			for (String wKey : wKeys)
			{
				sb.append(wKey).append(" = ").append(oldValues.get(wKey));
				if (i != length - 1)
				{
					sb.append(" and ");
				}
			}
			String sql = sb.toString();
			return update(sql);
		} else
		{
			throw new RuntimeException("表名不可为空且要插入的数据不可为空");
		}
	}

	/**
	 * 插入表
	 * @param sql
	 * @param params
	 * @return
	 * 赵玉柱
	 */
	public int insert(String sql, Map<String,Object> params)
	{
		logger.error(getDatabaseName() + ":insert[" + sql + "]参数" + params);
		List<Object> paramsList = new ArrayList<>();
		String sqlTemp = SqlUtil.sqlBindParams(this, sql, params, paramsList);
		return insertByParamList(sqlTemp, paramsList);
	}

	/**
	 * 插入表
	 * @param sql
	 * @return
	 * 赵玉柱
	 */
	public int insert(String sql)
	{
		return update(sql);
	}

	public int insertByParamList(String sql, List<Object> paramList)
	{
		return updateWithParamsList(sql, paramList);
	}

	/**
	 * 表是否存在
	 * @param table
	 * @return
	 * 赵玉柱
	 */
	public boolean tableExist(String table)
	{
		String sql = null;
		Map<String,Object> params = new HashMap<>();
		params.put("tablename", table);
		switch (type)
		{
		case Databasetype.MYSQL:
			//mysql
			sql = "select * from information_schema.TABLES a where a.table_name=:tablename ";
			break;
		default:
			break;
		}
		Map<String,Object> existTable = getMapFromSql(sql, params);
		if (existTable != null && !existTable.isEmpty())
		{
			return true;
		}
		return false;
	}

	public void createTableIfNotExist(Table table)
	{
		if (!tableExist(table.getTableName()))
		{
			//如果表不存在，则创建表
		}
	}

	/**
	 * 更新表
	 * @param sql
	 * @param params
	 * @return
	 * 赵玉柱
	 */
	public int update(String sql, Map<String,Object> params)
	{
		List<Object> paramsList = new ArrayList<>();
		sql = SqlUtil.sqlBindParams(this, sql, params, paramsList);
		return updateWithParamsList(sql, paramsList);
	}

	/**
	 * 更新表
	 * @param sql
	 * @return
	 * 赵玉柱
	 */
	public int updateWithParamsList(String sql, List<Object> paramsList)
	{
		int result = -1;
		try
		{
			if (connIsOpen())
			{
				PreparedStatement statement = getConn().prepareStatement(sql);
				if (paramsList != null && !paramsList.isEmpty())
				{
					int size = paramsList.size();
					for (int i = 0; i < size; i++)
					{
						statement.setObject(i + 1, paramsList.get(i));
					}
				}
				result = statement.executeUpdate();
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

	public int update(String sql)
	{
		return updateWithParamsList(sql, null);
	}

	/**
	 * 根据sql获取ResultSet
	 * @param sql
	 * @return
	 * 赵玉柱
	 */
	public ResultSet getResultSet(String sql, List<Object> paramList)
	{
		ResultSet rs = null;
		try
		{
			if (connIsOpen())
			{
				PreparedStatement prepareStatement = getConn().prepareStatement(sql);
				if (paramList != null)
				{
					int size = paramList.size();
					for (int i = 0; i < size; i++)
					{
						prepareStatement.setObject(i + 1, paramList.get(i));
					}
				}
				rs = prepareStatement.executeQuery();
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

	/**
	 * 
	 * @param sql
	 * @return
	 * 赵玉柱
	 */
	public ResultSet getResultSet(String sql)
	{
		return getResultSet(sql, null);
	}

	/**
	 * 根据sql执行一个写操作
	 * @param sql
	 * @param params
	 * @return
	 * 赵玉柱
	 */
	public boolean execSqlForWrite(String sql, Map<String,Object> params)
	{
		List<Object> paramsList = new ArrayList<>();
		sql = SqlUtil.sqlBindParams(this, sql, params, paramsList);
		return execSqlForWriteWithParamsList(sql, paramsList);
	}

	public boolean execSqlForWriteWithParamsList(String sql, List<Object> paramsList)
	{
		logger.error(getDatabaseName() + ":execSqlforWrite[" + sql + "]参数" + paramsList);
		boolean result = false;
		try
		{
			if (connIsOpen())
			{
				PreparedStatement statement = getConn().prepareStatement(sql);
				if (paramsList != null && !paramsList.isEmpty())
				{
					int size = paramsList.size();
					for (int i = 0; i < size; i++)
					{
						statement.setObject(i + 1, paramsList.get(i));
					}
				}
				result = statement.executeUpdate() > 0;
			} else
			{
				throw new RuntimeException("db" + databaseName + "已经断开连接");
			}
		} catch (Exception e)
		{
			throw new RuntimeException(e.getMessage());
		}
		return result;
	}

	public boolean execSqlForWrite(String sql)
	{
		return execSqlForWriteWithParamsList(sql, null);
	}

	/**
	 * 获取数据库类型
	 * @return
	 * 赵玉柱
	 */
	public int getType()
	{
		return this.type;
	}

	/**
	 * 获取当前数据库连接是否已经打开
	 * @return
	 * 赵玉柱
	 */
	public boolean connIsOpen()
	{
		boolean isOpen = false;
		if (this != null)
		{
			try
			{
				Connection conn = getConn();
				if (conn != null && !conn.isClosed())
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

	/**
	 * 关闭当前连接
	 * 
	 * 赵玉柱
	 */
	public void close()
	{
		if (this != null)
		{
			try
			{
				if (connIsOpen())
				{
					getConn().close();
				}
			} catch (SQLException e)
			{
				throw new RuntimeException("报错内容", e);
			}
		}
	}

	/**
	 * 提交事务
	 * 
	 * 赵玉柱
	 */
	public void commit()
	{
		try
		{
			if (getConn() != null && !getConn().isClosed())
			{
				try
				{
					getConn().commit();
				} finally
				{
					getConn().setAutoCommit(true);
				}
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
			throw new RuntimeException("数据提交失败" + e.getMessage());
		}
	}

	/**
	 * 创建事务
	 * 
	 * 赵玉柱
	 */
	public void beginTrans()
	{
		if (connIsOpen())
		{
			try
			{
				getConn().setAutoCommit(false);
			} catch (SQLException e)
			{
				throw new RuntimeException("创建DB连接失败，无法开启事务:", e);
			}
		}
	}

	/**
	 * 获取db是否在事务中
	 * @return
	 * 赵玉柱
	 */
	public boolean inTrans()
	{
		boolean inTrans = false;
		if (connIsOpen())
		{
			try
			{
				inTrans = getConn().getAutoCommit();
			} catch (SQLException e)
			{
				throw new RuntimeException("获取DB是否在事务中失败:", e);
			}
		}
		return inTrans;
	}

	/**
	 * 回滚
	 * @param rollback
	 * 赵玉柱
	 */
	public void rollback(boolean rollback)
	{
		rollback(rollback, true);
	}

	/**
	 * 回滚并是否关闭DB
	 * @param rollback
	 * @param closeDB
	 * 赵玉柱
	 */
	public void rollback(boolean rollback, boolean closeDB)
	{
		try
		{
			if (rollback)
			{
				getConn().rollback();
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
			throw new RuntimeException("回滚失败" + e.getMessage());
		} finally
		{
			if (closeDB)
			{
				close();
			}
		}
	}

	public String getDatabaseName()
	{
		return databaseName;
	}

	protected void setDatabaseName(String databaseName)
	{
		this.databaseName = databaseName;
	}

	public String getUrl()
	{
		return url;
	}

	protected void setUrl(String url)
	{
		this.url = url;
	}

	public String getDriver()
	{
		return driver;
	}

	protected void setDriver(String driver)
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

	public int getMinConnects()
	{
		return minConnects;
	}

	public int getMaxConnects()
	{
		return maxConnects;
	}

	public long getConnTimeOut()
	{
		return connTimeOut;
	}

	public int getMaxActiveConnections()
	{
		return maxActiveConnections;
	}

	public long getConnectionTimeOut()
	{
		return connectionTimeOut;
	}

	public boolean isCurrentConnection()
	{
		return isCurrentConnection;
	}

	public boolean isCheckPool()
	{
		return isCheckPool;
	}

	public long getLazyCheck()
	{
		return lazyCheck;
	}

	public long getPeriodCheck()
	{
		return periodCheck;
	}

	public int getInitConnections()
	{
		return initConnections;
	}

	public void setInitConnections(int initConnections)
	{
		this.initConnections = initConnections;
	}

	public void setMinConnects(int minConnects)
	{
		this.minConnects = minConnects;
	}

	public void setMaxConnects(int maxConnects)
	{
		this.maxConnects = maxConnects;
	}

	public void setConnTimeOut(long connTimeOut)
	{
		this.connTimeOut = connTimeOut;
	}

	public void setMaxActiveConnections(int maxActiveConnections)
	{
		this.maxActiveConnections = maxActiveConnections;
	}

	public void setConnectionTimeOut(long connectionTimeOut)
	{
		this.connectionTimeOut = connectionTimeOut;
	}

	public void setCurrentConnection(boolean isCurrentConnection)
	{
		this.isCurrentConnection = isCurrentConnection;
	}

	public void setCheckPool(boolean isCheckPool)
	{
		this.isCheckPool = isCheckPool;
	}

	public void setLazyCheck(long lazyCheck)
	{
		this.lazyCheck = lazyCheck;
	}

	public void setPeriodCheck(long periodCheck)
	{
		this.periodCheck = periodCheck;
	}

	public static void main(String[] args)
	{
		//家里电脑
		Database db = new Database("project01");
		//公司电脑
		//		Database db = new Database("zyztest");
		List<Map<String,Object>> listMapFromSql = db.getListMapFromSql("select * from information_schema.COLUMNS where table_schema='" + db.getDatabaseName() + "'");
		logger.error("{}", listMapFromSql);
	}

	public void setIsWrite()
	{
		isWrite.set(true);
	}

	public void setIsRead()
	{
		isWrite.set(false);
	}

	public boolean getIsWrite()
	{
		return isWrite.get();
	}
}
