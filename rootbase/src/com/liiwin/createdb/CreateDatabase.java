package com.liiwin.createdb;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import com.liiwin.db.Database;
import com.liiwin.db.Databasetype;
import com.liiwin.db.pool.DatabasePoolManager;
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

	/**
	 * 生成数据库
	 * @param dbList
	 * @param dbFile
	 * 赵玉柱
	 */
	private static void createDatabase(List<Database> dbList, List<BufferedReader> dbFile)
	{
		Map<Database,List<Map<String,Object>>> selectDBMessage = getSelectDBMessage(dbList);
		Map<String,Map<String,Table>> dbMessage2Table = dbMessage2Table(selectDBMessage);
		Map<String,Map<String,Table>> selectDBFileMessage = getSelectDBFileMessage(dbList, dbFile);
		//config tb表需要执行的sql
		Map<String,List<String>> tableExecSql = new HashMap<>();
		//config tbcol需要执行的sql
		Map<String,List<String>> tbcolExecSql = new HashMap<>();
		Map<Database,List<String>> sqlDbMap = compareDBAndDBFile(dbMessage2Table, selectDBFileMessage, dbList, tableExecSql, tbcolExecSql);
		System.out.println(dbMessage2Table);
		System.out.println(selectDBFileMessage);
		execUpdateSql(sqlDbMap, selectDBFileMessage, tableExecSql, tbcolExecSql);
	}

	/**
	 * 获取选择的db信息
	 * @param dbList
	 * @return
	 * 赵玉柱
	 */
	private static Map<Database,List<Map<String,Object>>> getSelectDBMessage(List<Database> dbList)
	{
		Map<Database,List<Map<String,Object>>> selectDBMessage = new HashMap<>();
		for (Database db : dbList)
		{
			List<Map<String,Object>> dbMessage = getDBMessage(db);
			System.out.println(dbMessage);
			selectDBMessage.put(db, dbMessage);
		}
		return selectDBMessage;
	}

	/**
	 * 获取DB信息
	 * @param db
	 * @return
	 * 赵玉柱
	 */
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
		return null;
	}

	/**
	 * db信息转table
	 * @param selectDBMessage
	 * @return
	 * 赵玉柱
	 */
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

	/**
	 * 获取db文件信息
	 * @param dbList
	 * @param dbFile
	 * @return
	 * 赵玉柱
	 */
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
						if (row != null)
						{
							row = StrUtil.removeSub(row, new char[] { ' ', '\t', '\n' }, 3, 0);
							if (row.startsWith("//"))
							{
								//这个开头表示注释，这种数据不进行处理
								continue;
							}
							//空行不处理
							if (StrUtil.isStrTrimNull(row))
							{
								System.out.println("空行不处理");
								continue;
							}
							System.out.println(row);
							row = row.replaceAll("\\s{1,}", "#");
							String[] colMsg = StrUtil.split(row, '#');
							if (colMsg == null || colMsg.length < 6)
							{
								System.out.println("表结构格式不正确");
								continue;
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

	/**
	 * 比较DB与DBfile
	 * @param dbMessage2Table
	 * @param selectDBFileMessage
	 * @param dbList
	 * @param tbcolExecSql 
	 * @param tableExecSql 
	 * @return
	 * 赵玉柱
	 */
	private static Map<Database,List<String>> compareDBAndDBFile(Map<String,Map<String,Table>> dbMessage2Table, Map<String,Map<String,Table>> selectDBFileMessage, List<Database> dbList,
			Map<String,List<String>> tableExecSql, Map<String,List<String>> tbcolExecSql)
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
			List<String> tableExecSqlList = new ArrayList<>();
			tableExecSql.put(dbName, tableExecSqlList);
			List<String> tbcolExecSqlList = new ArrayList<>();
			tableExecSql.put(dbName, tbcolExecSqlList);
			if (tableFileMessage != null)
			{
				for (Entry<String,Table> tableFileEntry : tableFileMessage)
				{
					String fileTableName = tableFileEntry.getKey();
					Table fileTable = tableFileEntry.getValue();
					Table dbTable = dbMessage.get(fileTableName);
					returnSql.put(db, compareTableAndTableFile(fileTable, dbTable, tableExecSqlList, tbcolExecSqlList));
				}
			}
		}
		return returnSql;
	}

	/**
	 * 比较table与tablefile
	 * @param fileTable
	 * @param dbTable
	 * @param tbcolExecSql 
	 * @param tableExecSql 
	 * @return
	 * 赵玉柱
	 */
	private static List<String> compareTableAndTableFile(Table fileTable, Table dbTable, List<String> tableExecSql, List<String> tbcolExecSql)
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
				String tableName = fileTable.getTableName();
				String tableExecSqlStr = "insert into `tb` (tbname,dbname,tblang1name) value('" + tableName + "','" + fileTable.getDbName() + "','" + fileTable.getTableName() + "')";
				tableExecSql.add(tableExecSqlStr);
				for (Entry<String,Column> columnEntry : columnEntrySet)
				{
					int flags = 0;
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
						flags += 1;
					}
					if ((constraint & 2) == 2)
					{
						sql.append(" not null ");
						flags += 2;
					}
					String defaultValue = column.getDefaultValue();
					if (!StrUtil.asNull(defaultValue))
					{
						sql.append(" default " + defaultValue + "");
					}
					sql.append(",\n");
					String tbcolExecSqlStr = "insert into `tbcolumn` (colname,tbname,comment,defaultvalue,datatype,dataLength,decimal,flags) value('" + column.getColumnName() + "','" + tableName
							+ "','" + comment + "','" + defaultValue + "','" + column.getDataType() + "'," + column.getDataLength() + "," + column.getDecimal() + "," + flags + ")";
					tbcolExecSql.add(tbcolExecSqlStr);
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

	/**
	 * 执行sql
	 * @param sqlDBMap
	 * 赵玉柱
	 * @param tbcolExecSql 
	 * @param tableExecSql 
	 */
	private static void execUpdateSql(Map<Database,List<String>> sqlDBMap, Map<String,Map<String,Table>> selectDBFileMessage, Map<String,List<String>> tableExecSql,
			Map<String,List<String>> tbcolExecSql)
	{
		if (sqlDBMap != null)
		{
			DatabasePoolManager poolManager = DatabasePoolManager.getNewInstance();
			Database cfgDB = poolManager.getConfigDatabase();
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
								//								sql = new String(sql.getBytes(Charset.defaultCharset()), Charset.forName("UTF-8"));
								//执行sql，生成表结构
								db.execSqlForWrite(sql);
							}
						}
					}
					Map<String,Table> tables = selectDBFileMessage.get(db.getDatabaseName());
					List<String> tableExecSqlList = tableExecSql.get(db.getDatabaseName());
					List<String> tbcolExecSqlList = tbcolExecSql.get(db.getDatabaseName());
					updateCFGTable(cfgDB, db, tables, tableExecSqlList, tbcolExecSqlList);
					cfgDB.commit();
					db.commit();
					rollback = false;
				} catch (Exception e)
				{
					System.out.println("createDatabase异常" + e.getMessage());
					throw new RuntimeException(e);
				} finally
				{
					try
					{
						if (db != null)
						{
							db.rollback(rollback, true);
						}
					} catch (Exception e)
					{
					}
					try
					{
						cfgDB.rollback(rollback, true);
					} catch (Exception e)
					{
					}
				}
			}
			poolManager.close(cfgDB);
		}
	}

	/**
	 * 更新库表信息到config库中
	 * @param cfgDB
	 * @param db
	 * @param tables
	 * 赵玉柱
	 * @param tbcolExecSqlList 
	 * @param tableExecSql 
	 */
	private static void updateCFGTable(Database cfgDB, Database db, Map<String,Table> tables, List<String> tableExecSql, List<String> tbcolExecSqlList)
	{
		if (!(cfgDB.tableExist("DB") && cfgDB.tableExist("TB")))
		{
			return;
		}
		Map<String,Object> params = new HashMap<>();
		params.put("dbname", db.getDatabaseName());
		String sqlQueryDB = "select * from db where dbname=:dbname ";
		Map<String,Object> resultMap = cfgDB.getMapFromSql(sqlQueryDB, params);
		Map<String,Object> updateDBParams = new HashMap<>();
		updateDBParams.put("url", db.getUrl());
		updateDBParams.put("username", db.getUser());
		updateDBParams.put("password", db.getPassword());
		updateDBParams.put("initcount", db.getInitConnections());
		updateDBParams.put("maxcount", db.getMaxConnects());
		updateDBParams.put("dbname", db.getDatabaseName());
		String dbSql = null;
		if (resultMap != null && !resultMap.isEmpty())
		{
			dbSql = "update db set url=:url ,username=:username ,password=:password ,initcount=:initcount ,maxcount=:maxcount  where dbname=:dbname  ";
		} else
		{
			dbSql = "insert into db(dbname,url,username,password,initcount,maxcount) values (:dbname ,:url ,:username ,:password ,:initcount ,:maxcount ) ";
		}
		//更新库信息到config库中
		cfgDB.execSqlForWrite(dbSql, updateDBParams);
		//更新表信息
		for (String tableExecSqlStr : tableExecSql)
		{
			cfgDB.execSqlForWrite(tableExecSqlStr);
		}
		//更新表结构信息
		for (String tbcolExecSqlStr : tbcolExecSqlList)
		{
			cfgDB.execSqlForWrite(tbcolExecSqlStr);
		}
		//		if (tables != null && !tables.isEmpty())
		//		{
		//			for (Entry<String,Table> tableEntry : tables.entrySet())
		//			{
		//				Table table = tableEntry.getValue();
		//				String sqlTab = "select * from tb where tbname=:tbname ";
		//				Map<String,Object> tableParams = new HashMap<>();
		//				tableParams.put("tbname", table.getTableName());
		//				Map<String,Object> tableMap = cfgDB.getMapFromSql(sqlTab, tableParams);
		//				String tbSql = null;
		//				Map<String,Object> updateTBParams = new HashMap<>();
		//				updateTBParams.put("dbname", table.getDbName());
		//				updateTBParams.put("tbname", table.getTableName());
		//				updateTBParams.put("tblang1name", table.getTableName());
		//				if (tableMap != null && !tableMap.isEmpty())
		//				{
		//					tbSql = "update tb set dbname=:dbname , tblang1name=:tblang1name ";
		//				} else
		//				{
		//					tbSql = "insert into tb(tbname,dbname,tblang1name) values(:tbname ,:dbname ,:tblang1name )";
		//				}
		//				cfgDB.execSqlForWrite(tbSql, updateTBParams);
		//			}
		//		}
	}

	public static void main(String[] args)
	{
		System.out.println("默认编码格式" + Charset.defaultCharset());
		createDatabase(new String[] { "zyztest", "config", "project01" });
	}
}
