package com.liiwin.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.liiwin.db.pool.DatabasePoolManager;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2018年8月9日 上午11:03:09</p>
 * <p>类全名：com.liiwin.db.DatabaseCacheUtils</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class DatabaseCacheUtils
{
	private static Map<String,String> tableDbCache = new ConcurrentHashMap<>();

	/**
	 * 根据表名获取数据库名称
	 * @param tableName
	 * @return
	 * 赵玉柱
	 */
	public static String getDbNameByTableName(String tableName)
	{
		if (StrUtil.isStrTrimNull(tableName))
		{
			return null;
		}
		if (tableDbCache.containsKey(tableName))
		{
			return tableDbCache.get(tableName);
		}
		Database configDatabase = DatabasePoolManager.getNewInstance().getConfigDatabase();
		String sql = "select dbname from tb where tbname=:tbname";
		Map<String,Object> params = new HashMap<>();
		params.put("tbname", tableName);
		Map<String,Object> mapFromSql = configDatabase.getMapFromSql(sql, params);
		if (mapFromSql == null || mapFromSql.isEmpty())
		{
			tableDbCache.put(tableName, null);
		}
		DatabasePoolManager.getNewInstance().close(configDatabase);
		String dbName = StrUtil.obj2str(mapFromSql.get("dbname"));
		tableDbCache.put(tableName, dbName);
		return dbName;
	}

	/**
	 * 判断两个表名是否在同一个数据库中
	 * @param tablename1
	 * @param tablename2
	 * @return
	 * 赵玉柱
	 */
	public static boolean tableSameDb(String tablename1, String tablename2)
	{
		if (StrUtil.isNullIn(tablename1, tablename2))
		{
			throw new RuntimeException("表名不可为空");
		}
		String dbname1 = getDbNameByTableName(tablename1);
		String dbname2 = getDbNameByTableName(tablename2);
		if (StrUtil.isStrTrimNull(dbname1))
		{
			throw new RuntimeException("根据表名" + tablename1 + "未查询到db名称");
		}
		if (StrUtil.isStrTrimNull(dbname2))
		{
			throw new RuntimeException("根据表名" + dbname2 + "未查询到db名称");
		}
		return dbname1.equals(dbname2);
	}

	/**
	 * 判断当前db是否对于 tablename可用
	 * @param db
	 * @param tablename
	 * @return
	 * 赵玉柱
	 */
	public static boolean sameDb(Database db, String tablename)
	{
		if (db == null)
		{
			throw new RuntimeException("db不可为空");
		}
		if (StrUtil.isStrTrimNull(tablename))
		{
			throw new RuntimeException("表名不可为空");
		}
		String dbname2 = getDbNameByTableName(tablename);
		if (StrUtil.isStrTrimNull(dbname2))
		{
			throw new RuntimeException("根据表名" + tablename + "未查询db名称");
		}
		return dbname2.equals(db.getDatabaseName());
	}

	/**
	 * 在已经获取到的db中再次获取可用的DB所在的索引
	 * @param dbs
	 * @param tablename
	 * @return
	 * 赵玉柱
	 */
	public static int getUseableDbByTable(List<Database> dbs, String tablename)
	{
		if (tablename == null)
		{
			throw new RuntimeException("表名不可为空");
		}
		if (dbs == null || dbs.isEmpty())
		{
			return -1;
		}
		int size = dbs.size();
		for (int i = 0; i < size; i++)
		{
			Database db = dbs.get(i);
			boolean sameDb = sameDb(db, tablename);
			if (sameDb)
			{
				return i;
			}
		}
		return -1;
	}

	/**
	 * 根据db名称获取可用的db所在的索引
	 * @param dbs
	 * @param dbname
	 * @return
	 * 赵玉柱
	 */
	public static <T extends Database> int getUseableDbByDbName(List<T> dbs, String dbname)
	{
		if (dbname == null)
		{
			throw new RuntimeException("db名称不可为空");
		}
		if (dbs == null || dbs.isEmpty())
		{
			return -1;
		}
		int size = dbs.size();
		for (int i = 0; i < size; i++)
		{
			Database db = dbs.get(i);
			boolean sameDb = dbname.equals(db.getDatabaseName());
			if (sameDb)
			{
				return i;
			}
		}
		return -1;
	}
}
