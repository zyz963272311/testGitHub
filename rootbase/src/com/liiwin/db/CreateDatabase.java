package com.liiwin.db;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： 生成表结构类</p>
 * <p>功能： </p>
 * <p>所属模块： rootbase</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2017年8月25日 下午8:04:41</p>
 * <p>类全名：com.liiwin.db.CreateDatabase</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class CreateDatabase
{
	/**
	 * 生成数据库
	 * @param dbNames
	 * 赵玉柱
	 */
	public static void createDatabase(String[] dbNames)
	{
		if (dbNames == null || dbNames.length == 0)
		{
			return;
		}
		List<Database> dbList = new ArrayList<>();
		List<BufferedReader> dbFile = new ArrayList<>();
		for (String dbName : dbNames)
		{
			if (StrUtil.isNoStrTrimNull(dbName))
			{
				try
				{
					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(CreateDatabase.class.getResourceAsStream("/resources/database/CreateDatabase_" + dbName + ".txt")));
					Database db = new Database(dbName);
					dbList.add(db);
					dbFile.add(bufferedReader);
				} catch (Exception e)
				{
					System.out.println("连接数据库" + dbName + "失败，不生成表结构");
					continue;
				}
			}
		}
		if (!dbList.isEmpty())
		{
			createDatabase(dbList, dbFile);
		}
	}

	private static void createDatabase(List<Database> dbList, List<BufferedReader> dbFile)
	{
	}

	private static Map<String,Object> getSelectDBMessage(List<Database> dbList)
	{
		return null;
	}

	private static List<Map<String,Object>> getDBMessage(Database db)
	{
		int dbType = db.getType();
		if (dbType == Databasetype.MYSQL)
		{
			String sql = "select * from information_schema.COLUMNS where table_schema=:table_schema";
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("table_schema", db.getDatabaseName());
			return db.getListMapFromSql(sql, params);
			//mysql
		} else if (dbType == Databasetype.ORACLE)
		{
			//oracle
		} else if (dbType == Databasetype.SQLSQRVER)
		{
			//sqlserver
		}
		return null;
	}
}
