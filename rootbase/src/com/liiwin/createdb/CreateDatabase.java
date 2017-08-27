package com.liiwin.createdb;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import com.liiwin.db.Database;
import com.liiwin.db.Databasetype;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： 生成表结构类</p>
 * <p>功能： </p>
 * <p>所属模块： rootbase</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2017年8月25日 下午8:04:41</p>
 * <p>类全名：com.liiwin.createdb.CreateDatabase</p>
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
		Map<Database,List<Map<String,Object>>> selectDBMessage = getSelectDBMessage(dbList);
		dbMessage2Table(selectDBMessage);
	}

	private static Map<Database,List<Map<String,Object>>> getSelectDBMessage(List<Database> dbList)
	{
		Map<Database,List<Map<String,Object>>> selectDBMessage = new HashMap<>();
		for (Database db : dbList)
		{
			List<Map<String,Object>> dbMessage = getDBMessage(db);
			selectDBMessage.put(db, dbMessage);
		}
		return selectDBMessage;
	}

	private static List<Map<String,Object>> getDBMessage(Database db)
	{
		int dbType = db.getType();
		if (dbType == Databasetype.MYSQL)
		{
			String sql = "select TABLE_NAME as tablename,COLUMN_NAME as columnname,COLUMN_COMMENT as comm,COLUMN_DEFAULT as dft,IS_NULLABLE as isnullable,DATA_TYPE as datatype,COLUMN_KEY as columnkey,NUMERIC_SCALE as scaly,NUMERIC_PRECISION as prec,CHARACTER_MAXIMUM_LENGTH as maxlength from information_schema.COLUMNS where table_schema=:table_schema ";
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
		System.out.println("不支持的数据库类型[" + db.getType() + "]");
		return null;
	}

	private static Map<String,Map<String,Table>> dbMessage2Table(Map<Database,List<Map<String,Object>>> selectDBMessage)
	{
		Map<String,Map<String,Table>> dbTableMap = new HashMap<>();
		if (selectDBMessage.isEmpty())
			return dbTableMap;
		Set<Entry<Database,List<Map<String,Object>>>> selectDBSet = selectDBMessage.entrySet();
		for (Entry<Database,List<Map<String,Object>>> selectDBEntry : selectDBSet)
		{
			Map<String,Table> tableMap = new HashMap<>();
			Database db = selectDBEntry.getKey();
			List<Map<String,Object>> selectDBMap = selectDBEntry.getValue();
			System.out.println(selectDBEntry);
			for (Map<String,Object> selectTableMap : selectDBMap)
			{
				String tableName = StrUtil.obj2str(selectTableMap.get("tablename"));
				Table table = null;
				if (tableMap.containsKey(tableName))
				{
					table = tableMap.get(tableName);
				} else
				{
					table = new Table();
					table.setDbName(db.getDatabaseName());
					table.setDbType(db.getType());
					table.setTableName(tableName);
					tableMap.put(tableName, table);
				}
				Column column = new Column();
				//					private String				tableName;									//表名
				//					private String				columnName;								//列名
				//					private String				dataType;									//字段类型
				//					private int					dataLength;								//字段长度
				//					private int					decimal;									//精度
				//					private String				comment;									//注释									//注释
				//					/**
				//					 * flags 
				//					 * 0：无约束
				//					 * 1：主键约束
				//					 * 2：非空约束
				//					 * 4：唯一约束
				//					 * 8：外键约束 一般不用
				//					 */
				//					private int					constraint;								//约束
				//					private Object				defaultValue;								//默认值
				column.setTableName(tableName);
				column.setComment(StrUtil.obj2str(selectTableMap.get("comm")));
				int dataLength = StrUtil.obj2int(selectTableMap.get("maxlength"), StrUtil.obj2int(tableMap.get("NUMERIC_PRECISION")));
				int decimal = StrUtil.obj2int(selectTableMap.get("scaly"));
				String columnName = StrUtil.obj2str(selectTableMap.get("columnname"));
				String dataType = StrUtil.obj2str(selectTableMap.get("datatype"));
				boolean isNullable = StrUtil.obj2bool(selectTableMap.get("isnullable"));
				boolean columnkey = StrUtil.obj2bool(StrUtil.obj2str(selectTableMap.get("columnkey")).equals("PRI"));
				int constraint = (isNullable ? 2 : 0) | (columnkey ? 1 : 0);
				column.setConstraint(constraint);
				column.setDataLength(dataLength);
				column.setDataType(dataType);
				column.setDecimal(decimal);
				column.setColumnName(columnName);
				Map<String,Column> columns = table.getColumns();
				if (columns == null)
				{
					columns = new HashMap<>();
				}
				columns.put(columnName, column);
				table.setColumns(columns);
			}
			dbTableMap.put(db.getDatabaseName(), tableMap);
		}
		return dbTableMap;
	}

	public static void main(String[] args)
	{
		createDatabase(new String[] { "project01", "ssm-test" });
	}
}
