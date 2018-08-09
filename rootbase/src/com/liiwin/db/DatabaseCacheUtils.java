package com.liiwin.db;

import java.util.HashMap;
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
}
