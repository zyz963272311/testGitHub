package com.liiwin.db.pool;

import java.sql.SQLException;
import java.util.Hashtable;
import com.liiwin.db.Database;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： 数据库连接池管理类</p>
 * <p>功能： </p>
 * <p>所属模块： rootbase</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2017年8月29日 下午4:26:29</p>
 * <p>类全名：com.liiwin.db.pool.DatabasePoolManager</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class DatabasePoolManager
{
	public Hashtable<String,IDatabasePool>	pools	= new Hashtable<>();
	private static DatabasePoolManager		maneger;

	private DatabasePoolManager()
	{
	}

	public static DatabasePoolManager getNewInstance()
	{
		if (maneger == null)
		{
			maneger = new DatabasePoolManager();
		}
		return maneger;
	}

	public Database getDatabase(String dbName)
	{
		if (StrUtil.isStrTrimNull(dbName))
		{
			throw new RuntimeException("获取DB异常：数据库名称不可为空");
		}
		Database database = null;
		if (pools.containsKey(dbName))
		{
			database = getPool(dbName).getDatabase();
		} else
		{
			throw new RuntimeException("db不存在[" + dbName + "]");
		}
		return database;
	}

	public IDatabasePool getPool(String dbName)
	{
		IDatabasePool pool = null;
		if (StrUtil.isStrTrimNull(dbName))
		{
			throw new RuntimeException("db名称不可为空");
		}
		if (pools.size() > 0)
		{
			pool = pools.get(dbName);
		}
		return pool;
	}

	/**
	 * 关闭DB
	 * @param database
	 * 赵玉柱
	 */
	public void close(Database database)
	{
		if (database == null)
		{
			throw new RuntimeException("database为空，不能执行关闭操作");
		}
		close(database.getDatabaseName(), database);
	}

	/**
	 * 关闭DB
	 * @param dbName
	 * @param database
	 * 赵玉柱
	 */
	public void close(String dbName, Database database)
	{
		if (database == null)
		{
			throw new RuntimeException("database为空，不能执行关闭操作");
		}
		String databaseName = database.getDatabaseName();
		if (!StrUtil.equals(dbName, databaseName))
		{
			throw new RuntimeException("要关闭的数据与数据库名称不匹配");
		}
		IDatabasePool pool = getPool(dbName);
		try
		{
			pool.releaseDatabase(database);
		} catch (SQLException e)
		{
			throw new RuntimeException("关闭DB出错", e);
		}
	}

	/**
	 * 清空DB连接
	 * @param dbName
	 * 赵玉柱
	 */
	public void destroy(String dbName)
	{
		IDatabasePool pool = getPool(dbName);
		if (pool != null)
		{
			pool.destroy();
		}
	}

	public void commit(Database database, boolean closeDB)
	{
		if (database == null)
		{
			throw new RuntimeException("db为空无法进行提交操作");
		}
		commit(database, database.getDatabaseName(), closeDB);
	}

	public void commit(Database database, String dbName, boolean closeDB)
	{
		if (!isDBNameOf(database, dbName))
		{
			throw new RuntimeException("database与dbName不匹配，不可进行提交操作");
		}
	}

	/**
	 * 判断dbName是不是database的dbName
	 * @param database
	 * @param dbName
	 * @return
	 * 赵玉柱
	 */
	public boolean isDBNameOf(Database database, String dbName)
	{
		if (StrUtil.isStrTrimNull(dbName))
		{
			return false;
		}
		if (database == null)
		{
			return false;
		}
		if (!StrUtil.equals(database.getDatabaseName(), dbName))
		{
			return true;
		}
		return false;
	}
}
