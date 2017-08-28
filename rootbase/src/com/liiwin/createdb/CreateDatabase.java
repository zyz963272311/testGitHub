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
		Map<String,Map<String,Table>> dbMessage2Table = dbMessage2Table(selectDBMessage);
		Map<String,Map<String,Table>> selectDBFileMessage = getSelectDBFileMessage(dbList, dbFile);
		Map<Database,List<String>> sqlDbMap = compareDBAndDBFile(dbMessage2Table, selectDBFileMessage, dbList);
		System.out.println(dbMessage2Table);
		System.out.println(selectDBFileMessage);
		execUpdateSql(sqlDbMap);
	}

	private static Map<Database,List<Map<String,Object>>> getSelectDBMessage(List<Database> dbList)
	{
		Map<Database,List<Map<String,Object>>> selectDBMessage = new HashMap<>();
		for (Database db : dbList)
		{
			List<Map<String,Object>> dbMessage = getDBMessage(db);
			System.out.println("dbMessage");
			System.out.println(dbMessage);
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
		{
			return dbTableMap;
		}
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
				int dataLength = StrUtil.obj2int(selectTableMap.get("maxlength"), StrUtil.obj2int(tableMap.get("maxlength")));
				int decimal = StrUtil.obj2int(selectTableMap.get("scaly"));
				String columnName = StrUtil.obj2str(selectTableMap.get("columnname"));
				String dataType = StrUtil.obj2str(selectTableMap.get("datatype"));
				boolean isNullable = StrUtil.obj2bool(selectTableMap.get("isnullable"));
				boolean columnkey = StrUtil.obj2bool(StrUtil.obj2str(selectTableMap.get("columnkey")).equals("PRI"));
				int constraint = (!isNullable ? 2 : 0) | (columnkey ? 1 : 0);
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
				System.out.println(column);
				columns.put(columnName, column);
				table.setColumns(columns);
			}
			dbTableMap.put(db.getDatabaseName(), tableMap);
		}
		return dbTableMap;
	}

	private static Map<String,Map<String,Table>> getSelectDBFileMessage(List<Database> dbList, List<BufferedReader> dbFile)
	{
		if (dbFile == null || dbFile.isEmpty())
		{
			System.out.println("dbFile为空");
			return null;
		}
		Map<String,Map<String,Table>> selectDBFileMessage = new HashMap<>();
		for (int i = 0; i < dbFile.size(); i++)
		{
			Map<String,Table> dbFileMessage = new HashMap<>();
			BufferedReader reader = dbFile.get(i);
			Database db = dbList.get(i);
			if (reader != null)
			{
				try
				{
					while (reader.read() != -1)
					{
						String row = reader.readLine();
						String srcRow = row;
						if (row != null)
						{
							row = StrUtil.removeSub(row, new char[] { ' ', '\t', '\n' }, 3, 0);
							if (row.startsWith("//"))
							{
								//这个开头表示注释，这种数据不进行处理
								continue;
							}
							System.out.println(row);
							row = row.replaceAll("\\s{1,}", "#");
							String[] colMsg = StrUtil.split(row, '#');
							if (colMsg == null || colMsg.length < 6)
							{
								throw new RuntimeException("dbFile配置错误,行信息[" + srcRow + "]");
							}
							String tablename = colMsg[0];
							Table table = dbFileMessage.get(tablename);
							if (table == null)
							{
								table = new Table();
								table.setDbName(db.getDatabaseName());
								table.setDbType(db.getType());
								table.setTableName(tablename);
							}
							String columnname = colMsg[1];
							Map<String,Column> columns = table.getColumns();
							if (columns == null)
							{
								columns = new HashMap<>();
							}
							Column column = columns.get(columnname);
							if (column == null)
							{
								String comment = colMsg[2];
								String datatype = colMsg[3];
								String datalength_decimal = colMsg[4];
								int constraint = Integer.parseInt(colMsg[5].substring(2), 2);
								String defaultValue = null;
								if (colMsg.length > 6)
								{
									defaultValue = colMsg[6];
								}
								column = new Column();
								column.setTableName(tablename);
								column.setColumnName(columnname);
								column.setComment(comment);
								column.setConstraint(constraint);
								column.setDataType(datatype);
								String[] split = StrUtil.split(datalength_decimal, ',');
								column.setDataLength(StrUtil.obj2int(split[0]));
								column.setDecimal(StrUtil.obj2int(split[1]));
								column.setDefaultValue(defaultValue);
								columns.put(columnname, column);
							}
							table.setColumns(columns);
							dbFileMessage.put(tablename, table);
						}
					}
				} catch (Exception e)
				{
					System.out.println("db文件读取失败:" + e.getMessage());
				} finally
				{
					try
					{
						reader.close();
					} catch (Exception e)
					{
						System.out.println("reader关闭异常" + reader);
					}
				}
			}
			selectDBFileMessage.put(db.getDatabaseName(), dbFileMessage);
		}
		return selectDBFileMessage;
	}

	private static Map<Database,List<String>> compareDBAndDBFile(Map<String,Map<String,Table>> dbMessage2Table, Map<String,Map<String,Table>> selectDBFileMessage, List<Database> dbList)
	{
		if (dbMessage2Table == null || dbMessage2Table.size() == 0 || selectDBFileMessage == null || selectDBFileMessage.size() == 0)
		{
			return null;
		}
		Map<Database,List<String>> returnSql = new HashMap<>();
		for (Database db : dbList)
		{
			String dbName = db.getDatabaseName();
			if (!dbMessage2Table.containsKey(dbName))
			{
				continue;
			}
			Map<String,Table> dbFileMessage = selectDBFileMessage.get(dbName);
			Map<String,Table> dbMessage = dbMessage2Table.get(dbName);
			Set<Entry<String,Table>> tableFileMessage = dbFileMessage.entrySet();
			if (tableFileMessage != null)
			{
				for (Entry<String,Table> tableFileEntry : tableFileMessage)
				{
					String fileTableName = tableFileEntry.getKey();
					Table fileTable = tableFileEntry.getValue();
					Table dbTable = dbMessage.get(fileTableName);
					returnSql.put(db, compareTableAndTableFile(fileTable, dbTable));
				}
			}
		}
		return returnSql;
	}

	private static List<String> compareTableAndTableFile(Table fileTable, Table dbTable)
	{
		List<String> returnSql = new ArrayList<>();
		int dbType = fileTable.getDbType();
		if (dbTable == null)
		{
			Map<String,Column> columns = fileTable.getColumns();
			//新增表结构
			switch (dbType)
			{
			case Databasetype.MYSQL:
				StringBuffer sql = new StringBuffer();
				StringBuffer primarySB = new StringBuffer(" primary key (");
				int srcPriLength = primarySB.length();
				sql.append("create table `" + fileTable.getTableName() + "`\n(\n");
				Set<Entry<String,Column>> columnEntrySet = columns.entrySet();
				for (Entry<String,Column> columnEntry : columnEntrySet)
				{
					Column column = columnEntry.getValue();
					sql.append(column.getColumnName() + " " + column.getDataType() + "(" + column.getDataLength() + (column.getDecimal() > 0 ? ("," + column.getDecimal()) : "") + ")");
					String comment = column.getComment();
					if (comment != null)
					{
						sql.append(" COMMENT '" + comment + "'");
					}
					int constraint = column.getConstraint();
					if ((constraint & 1) == 1)
					{
						primarySB.append(column.getColumnName() + ",");
					}
					if ((constraint & 2) == 2)
					{
						sql.append(" not null ");
					}
					String defaultValue = column.getDefaultValue();
					if (!StrUtil.asNull(defaultValue))
					{
						sql.append(" default " + defaultValue + "");
					}
					sql.append(",\n");
				}
				if (primarySB.length() > srcPriLength)
				{
					sql.setLength(sql.length() - 1);
					primarySB.setLength(primarySB.length() - 1);
					primarySB.append(")");
					sql.append("\n");
					sql.append(primarySB);
				} else
				{
					sql.setLength(sql.length() - 2);
				}
				sql.append("\n)");
				returnSql.add(sql.toString());
				break;
			case Databasetype.ORACLE:
				break;
			case Databasetype.SQLSQRVER:
				break;
			}
		} else
		{
			Map<String,Column> columns = fileTable.getColumns();
			Map<String,Column> dbColumns = dbTable.getColumns();
			//更新表结构
			switch (dbType)
			{
			case Databasetype.MYSQL:
				Set<Entry<String,Column>> columnEntrySet = columns.entrySet();
				for (Entry<String,Column> columnEntry : columnEntrySet)
				{
					StringBuffer sql = new StringBuffer();
					String columnName = columnEntry.getKey();
					Column column = columnEntry.getValue();
					if (!dbColumns.containsKey(columnName))
					{
						//添加字段
						sql.append("alter table `" + column.getTableName() + "` add ");
						sql.append("`" + column.getColumnName() + "` " + column.getDataType() + "(" + column.getDataLength() + (column.getDecimal() > 0 ? ("," + column.getDecimal()) : "") + ")");
						String comment = column.getComment();
						if (comment != null)
						{
							sql.append(" COMMENT '" + comment + "'");
						}
						int constraint = column.getConstraint();
						if ((constraint & 1) == 1)
						{
							sql.append(" primary key PK_" + column.getTableName() + "_" + column.getColumnName());
						}
						if ((constraint & 2) == 2)
						{
							sql.append(" not null ");
						}
						String defaultValue = column.getDefaultValue();
						if (!StrUtil.asNull(defaultValue))
						{
							sql.append(" default " + defaultValue + ",\n");
						}
					} else
					{
						//修改字段
						Column dbColumn = dbColumns.get(columnName);
						if (dbColumn.isDiffWith(column))
						{
							sql.append("alter table `" + column.getTableName() + "` modify column ");
							sql.append("`" + column.getColumnName() + "` " + column.getDataType() + "(" + column.getDataLength() + (column.getDecimal() > 0 ? ("," + column.getDecimal()) : "") + ")");
							String comment = column.getComment();
							if (comment != null)
							{
								sql.append(" COMMENT '" + comment + "'");
							}
							int constraint = column.getConstraint();
							if ((constraint & 2) == 2)
							{
								sql.append(" not null ");
							}
							String defaultValue = column.getDefaultValue();
							if (!StrUtil.asNull(defaultValue))
							{
								sql.append(" default " + defaultValue);
							}
						}
					}
					returnSql.add(sql.toString());
				}
				break;
			case Databasetype.ORACLE:
				break;
			case Databasetype.SQLSQRVER:
				break;
			}
		}
		return returnSql;
	}

	private static void execUpdateSql(Map<Database,List<String>> sqlDBMap)
	{
		if (sqlDBMap != null)
		{
			Set<Entry<Database,List<String>>> sqlSet = sqlDBMap.entrySet();
			for (Entry<Database,List<String>> sqlEntry : sqlSet)
			{
				boolean rollback = true;
				Database db = null;
				try
				{
					db = sqlEntry.getKey();
					List<String> value = sqlEntry.getValue();
					if (value != null)
					{
						for (String sql : value)
						{
							if (!StrUtil.isStrTrimNull(sql))
							{
								db.execSqlForWrite(sql);
							}
						}
					}
					db.commit();
					rollback = false;
				} catch (Exception e)
				{
					System.out.println("createDatabase异常" + e.getMessage());
					throw e;
				} finally
				{
					if (db != null)
					{
						if (rollback)
						{
							db.rollback(rollback, true);
						}
					}
				}
			}
		}
	}

	public static void main(String[] args)
	{
		createDatabase(new String[] { "project01", "ssm-test" });
	}
}
