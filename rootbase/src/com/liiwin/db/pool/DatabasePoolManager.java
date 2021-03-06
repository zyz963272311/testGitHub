package com.liiwin.db.pool;

import java.sql.SQLException;
import java.util.Hashtable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.liiwin.db.Database;
import com.liiwin.db.DatabaseCacheUtils;
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
	private static Logger							logger	= LoggerFactory.getLogger(DatabasePoolManager.class);
	public static Hashtable<String,IDatabasePool>	pools	= new Hashtable<>();
	private static DatabasePoolManager				maneger;

	protected DatabasePoolManager()
	{
	}

	public static synchronized DatabasePoolManager getNewInstance()
	{
		if (maneger == null)
		{
			logger.error("DatabasePoolManager被实例化了");
			maneger = new DatabasePoolManager();
		}
		return maneger;
	}

	public Database getConfigDatabase()
	{
		return getDatabase("config");
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
			database = new Database(dbName);
			DatabasePool pool = new DatabasePool(database);
			pools.put(dbName, pool);
		}
		return database;
	}

	/**
	 * 根据表名获取数据库名称
	 * @param tableName
	 * @return
	 * 赵玉柱
	 */
	public Database getDatabaseByTable(String tableName)
	{
		String dbName = DatabaseCacheUtils.getDbNameByTableName(tableName);
		if (StrUtil.isStrTrimNull(dbName))
		{
			throw new RuntimeException("根据表名" + tableName + "获取数据库名称失败");
		}
		return getDatabase(dbName);
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
		database.commit();
		if (closeDB)
		{
			close(dbName, database);
		}
	}

	public void rollback(Database database, String dbName, boolean rollback, boolean closeDB)
	{
		if (!isDBNameOf(database, dbName))
		{
			throw new RuntimeException("database与dbName不匹配，不可进行回滚操作");
		}
		database.rollback(rollback);
		if (closeDB)
		{
			close(dbName, database);
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

	public static void main(String[] args)
	{
		Thread t1 = new Test1();
		t1.start();
		try
		{
			Thread.sleep(5000);
		} catch (InterruptedException e)
		{
			throw new RuntimeException("报错内容", e);
		}
		Thread t2 = new Test2();
		t2.start();
	}

	void test1()
	{
		DatabasePoolManager poolManager = DatabasePoolManager.getNewInstance();
		logger.error("poolManager=" + poolManager);
		long start = System.currentTimeMillis();
		Database database = poolManager.getDatabase("ssm-test");
		logger.error("初始化连接时长" + (System.currentTimeMillis() - start) + "ms");
		try
		{
			logger.error("休眠10秒");
			Thread.sleep(10000);
		} catch (InterruptedException e)
		{
			throw new RuntimeException("报错内容", e);
		}
		int times = 3;
		Database[] dbs = new Database[times];
		for (int i = 0; i < times; i++)
		{
			start = System.currentTimeMillis();
			Database db = poolManager.getDatabase("ssm-test");
			dbs[i] = db;
			logger.error("第" + i + "次连接时长为" + (System.currentTimeMillis() - start) + "ms");
		}
		try
		{
			logger.error("休眠10秒");
			Thread.sleep(10000);
		} catch (InterruptedException e)
		{
			throw new RuntimeException("报错内容", e);
		}
		start = System.currentTimeMillis();
		poolManager.close(database);
		logger.error("关闭DB时长" + (System.currentTimeMillis() - start) + "ms");
		if (dbs != null)
		{
			for (Database db : dbs)
			{
				start = System.currentTimeMillis();
				poolManager.close(db);
				logger.error("关闭DB时长" + (System.currentTimeMillis() - start) + "ms");
			}
		}
	}
}
