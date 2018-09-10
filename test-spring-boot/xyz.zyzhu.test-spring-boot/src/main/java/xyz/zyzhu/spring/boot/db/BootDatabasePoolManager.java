package xyz.zyzhu.spring.boot.db;

import java.util.Hashtable;
import com.alibaba.druid.pool.DruidDataSource;
import com.liiwin.db.Database;
import com.liiwin.db.DatabaseCacheUtils;
import com.liiwin.db.pool.IDatabasePool;
import com.liiwin.utils.StrUtil;
import xyz.zyzhu.spring.boot.model.BasModel;
import xyz.zyzhu.spring.boot.utils.ModelUtils;
import xyz.zyzhu.spring.boot.utils.SpringBeanUtils;
import xyz.zyzhu.spring.config.DruidConfig;
/**
 * <p>标题： spring Boot 使用的db Pool</p>
 * <p>功能： </p>
 * <p>所属模块： spring boot 使用的dbPool</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年8月10日 上午10:03:59</p>
 * <p>类全名：xyz.zyzhu.spring.boot.db.BootDatabasePoolManager</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class BootDatabasePoolManager
{
	private static Hashtable<String,IDatabasePool<BootDatabase>>	pools	= new Hashtable<>();
	private static BootDatabasePoolManager							maneger;
	private static DruidConfig										druidConfig;
	static
	{
		getNewInstance();
		druidConfig = SpringBeanUtils.getBean(DruidConfig.class);
		if (druidConfig == null)
		{
			throw new RuntimeException("初始化driod配置失败");
		}
	}

	protected BootDatabasePoolManager()
	{
	}

	public static synchronized BootDatabasePoolManager getNewInstance()
	{
		if (maneger == null)
		{
			maneger = new BootDatabasePoolManager();
		}
		return maneger;
	}

	/**
	 * 根据表名获取一个写库
	 * @param tablename
	 * @return
	 * 赵玉柱
	 */
	public static BootDatabase getWriteDatabaseByTable(String tablename)
	{
		return getDatabaseByTable(tablename, true);
	}

	/**
	 * 根据clazz获取数据库
	 * @param clazz
	 * @return
	 * 赵玉柱
	 */
	public static <T extends BasModel> BootDatabase getWriteDatabaseByClass(Class<T> clazz)
	{
		return getDatabaseByClass(clazz, true);
	}

	/**
	 * 根据clazz获取数据库
	 * @param clazz
	 * @return
	 * 赵玉柱
	 */
	public static <T extends BasModel> BootDatabase getReadDatabaseByClass(Class<T> clazz)
	{
		return getDatabaseByClass(clazz, false);
	}

	/**
	 * 根据clazz获取数据库
	 * @param clazz
	 * @param isWrite
	 * @return
	 * 赵玉柱
	 */
	public static <T extends BasModel> BootDatabase getDatabaseByClass(Class<T> clazz, boolean isWrite)
	{
		String modelTable = ModelUtils.getModelTable(clazz);
		return getDatabaseByTable(modelTable, isWrite);
	}

	/**
	 * 根据表名获取一个读库
	 * @param tablename
	 * @return
	 * 赵玉柱
	 */
	public static BootDatabase getReadDatabaseByTable(String tablename)
	{
		return getDatabaseByTable(tablename, false);
	}

	/**
	 * 根据表名获取一个写库或读库
	 * @param tablename
	 * @param isWrite
	 * @return
	 * 赵玉柱
	 */
	public static BootDatabase getDatabaseByTable(String tablename, boolean isWrite)
	{
		String dbname = DatabaseCacheUtils.getDbNameByTableName(tablename);
		return getDatabase(dbname, isWrite);
	}

	/**
	 * 根据库名获取一个读或写的数据库
	 * @param dbname
	 * @return
	 * 赵玉柱
	 */
	public static BootDatabase getDatabase(String dbname)
	{
		return getDatabase(dbname, true);
	}

	/**
	 * 根据数据库名获取一个读或写的数据库
	 * @param dbname
	 * @param isWrite
	 * @return
	 * 赵玉柱
	 */
	public static BootDatabase getDatabase(String dbname, boolean isWrite)
	{
		IDatabasePool<BootDatabase> pool = getPool(dbname, isWrite);
		BootDatabase database = null;
		String key = dbname + "@" + (isWrite ? "write" : "read");
		if (pool == null)
		{
			DruidDataSource datasource = druidConfig.dataSourceByDbName(dbname, isWrite);
			database = new BootDatabase(datasource, dbname);
			if (isWrite)
			{
				database.setIsWrite();
			} else
			{
				database.setIsRead();
			}
			pool = new BootDatabasePool(database);
			pools.put(key, pool);
		} else
		{
			database = (BootDatabase) pool.getDatabase();
		}
		return database;
	}

	public static IDatabasePool<BootDatabase> getPool(String dbName)
	{
		return getPool(dbName, true);
	}

	public static IDatabasePool<BootDatabase> getPool(String dbName, boolean isWrite)
	{
		IDatabasePool<BootDatabase> pool = null;
		if (StrUtil.isStrTrimNull(dbName))
		{
			throw new RuntimeException("db名称不可为空");
		}
		String key = dbName + "@" + (isWrite ? "write" : "read");
		if (pools.size() > 0)
		{
			pool = pools.get(key);
		}
		return pool;
	}

	/**
	 * 关闭DB
	 * @param database
	 * 赵玉柱
	 */
	public static void close(BootDatabase database)
	{
		if (database == null)
		{
			return;
		}
		database.close();
		//		if (database == null)
		//		{
		//			throw new RuntimeException("database为空，不能执行关闭操作");
		//		}
		//		close(database.getDatabaseName(), database);
	}

	/**
	 * 关闭DB
	 * @param dbName
	 * @param database
	 * 赵玉柱
	 */
	public static void close(String dbName, Database database)
	{
		if (database == null)
		{
			return;
		}
		boolean nameEqual = dbName.equals(database.getDatabaseName());
		if (nameEqual)
		{
			database.close();
		}
		//		if (database == null)
		//		{
		//			throw new RuntimeException("database为空，不能执行关闭操作");
		//		}
		//		String databaseName = database.getDatabaseName();
		//		boolean isWrite = database.getIsWrite();
		//		if (!StrUtil.equals(dbName, databaseName))
		//		{
		//			throw new RuntimeException("要关闭的数据与数据库名称不匹配");
		//		}
		//		IDatabasePool pool = getPool(dbName, isWrite);
		//		try
		//		{
		//			pool.releaseDatabase(database);
		//		} catch (SQLException e)
		//		{
		//			throw new RuntimeException("关闭DB出错", e);
		//		}
	}
}
