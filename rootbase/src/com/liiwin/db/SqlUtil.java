package com.liiwin.db;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import com.liiwin.random.RandomString;
import com.liiwin.random.RandomStringImpl;
import com.liiwin.utils.EmptyUtil;
import com.liiwin.utils.StrUtil;
import com.liiwin.utils.Validator;
/**
 * <p>标题： SQL工具类</p>
 * <p>功能： </p>
 * <p>所属模块： rootbase</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年5月17日 上午10:39:14</p>
 * <p>类全名：db.SqlUtil</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class SqlUtil
{
	//两种方式实现sql分页查询
	//页大小+页号
	public static String		PAGESIZE	= "Page.size";
	public static String		PAGENO		= "Page.no";
	//行号从+行号到
	public static String		ROWFROM		= "Row.from";
	public static String		ROWTO		= "Row.to";
	private static final int	inSize		= 1000;

	/**
	 * 将sql与params进行绑定
	 * @param db 
	 * @param sql 
	 * @param params
	 * @return
	 * 赵玉柱
	 */
	public static String sqlBindParams(Database db, String sql, Map<String,Object> params, List<Object> paramList)
	{
		if (params == null || params.isEmpty())
		{
			return sql;
		}
		if (paramList == null)
		{
			return sql;
		}
		if (StrUtil.isStrTrimNull(sql))
		{
			return sql;
		}
		//处理分页 格式化between与and的sql
		sql = mallocSql(db, sql, params);
		StringBuffer sqlSB = new StringBuffer();
		char[] sqlCharArr = sql.toCharArray();
		int fromIdx = -1;
		int endIdx = -1;
		for (int i = 0; i < sqlCharArr.length; i++)
		{
			char c = sqlCharArr[i];
			if (c == ':')
			{
				fromIdx = i;
				continue;
			}
			if (fromIdx >= 0 && Character.isJavaIdentifierStart(c))
			{
				//判断当前字符是否为合法标识符
				endIdx = i;
			} else
			{
				if (fromIdx >= 0 && endIdx > fromIdx)
				{
					sqlSB.append('?');
					String key = sql.substring(fromIdx + 1, endIdx+1);
					Object value = params.get(key);
					paramList.add(value);
				}
				sqlSB.append(c);
				fromIdx = -1;
				endIdx = -1;
			}
		}
		//		Set<Entry<String,Object>> entrySet = params.entrySet();
		//		for (Entry<String,Object> entry : entrySet)
		//		{
		//			String key = entry.getKey();
		//			if (StrUtil.isStrTrimNull(key))
		//			{
		//				continue;
		//			}
		//			Object value = entry.getValue();
		//			//			if (EmptyUtil.isEmpty(value))
		//			//			{
		//			//				continue;
		//			//			}
		//			if (key.endsWith(".[from]") || key.endsWith(".[to]"))
		//			{
		//				sql = buildBetweenAnd(sql, key, params);
		//			}
		//			if (key == PAGENO || key == PAGESIZE || key == ROWFROM || key == ROWTO)
		//			{
		//				sql = parsePages(db, sql, key, params);
		//			}
		//			sql = parseSql(db, sql, key, value);
		//		}
		return sqlSB.toString();
	}

	/**
	 * 将name和list拼接成name in (list) 的形式
	 * @param name
	 * @param list
	 * @param params
	 * @return
	 * 赵玉柱
	 */
	public static String bindSqlIn(String name, String list, Map<String,Object> params)
	{
		return bindSqlIn(name, list, params, 0);
	}

	/**
	 * 将name和list拼接成name in (list) 的形式
	 * @param name filter名
	 * @param list filter值
	 * @param params 拼接到的map中
	 * @param fromIdx 拼接的下标开始位置，防止有类似的条件
	 * @return
	 * 注意：当查询条件的in超过1000的情况下，查询将出现异常</br>
	 * 赵玉柱
	 */
	public static String bindSqlIn(String name, String list, Map<String,Object> params, int fromIdx)
	{
		String[] listArray = null;
		if (StrUtil.isNoStrTrimNull(list))
		{
			listArray = StrUtil.split(list, ',');
		}
		return bindSqlIn(name, listArray, params, fromIdx);
	}

	/**
	 * 将name和listArray拼接成name in (listArray) 的形式
	 * @param name filter名
	 * @param listArray filter值
	 * @param params 拼接到的map中
	 * @return 
	 * 赵玉柱
	 */
	public static String bindSqlIn(String name, Object[] listArray, Map<String,Object> params)
	{
		return bindSqlIn(name, listArray, params, 0);
	}

	/**
	 * 将name和listArray拼接成name in (listArray) 的形式
	 * @param name  filter名
	 * @param listArray filter值
	 * @param params filter值
	 * @param fromIdx 起始下标
	 * @return
	 * 赵玉柱
	 */
	public static String bindSqlIn(String name, Object[] listArray, Map<String,Object> params, int fromIdx)
	{
		StringBuffer sqlBuffer = new StringBuffer("(");
		fromIdx = fromIdx < 0 ? 0 : fromIdx;
		if (StrUtil.isNoStrTrimNull(name))
		{
			sqlBuffer.append(name).append(" in (");
			int length = sqlBuffer.length();
			int control = 0;
			if (listArray != null)
			{
				for (int i = 0; i < listArray.length; i++)
				{
					if (Validator.isNotNull(listArray[i]))
					{
						if (control >= inSize)
						{
							sqlBuffer.setLength(sqlBuffer.length() - 1);//去掉最后的逗号
							sqlBuffer.append(") or ").append(name).append(" in (");
							control = 0;
						}
						String key = name + (i + fromIdx);
						for (;; fromIdx++)
						{
							if (existKey(params, key))
							{
								key = name + (i + fromIdx);
							} else
							{
								break;
							}
						}
						params.put(key, listArray[i]);
						sqlBuffer.append(":").append(key).append(",");
					}
					control++;
				}
			}
			if (sqlBuffer.length() > length)
			{
				sqlBuffer.setLength(sqlBuffer.length() - 1);
				sqlBuffer.append("))");
			} else
			{
				return "";
			}
		}
		return sqlBuffer.toString();
	}

	private static boolean existKey(Map<String,Object> params, String name)
	{
		return params.containsKey(name);
	}

	/**
	 * 用params的key拼装出where的条件
	 * @param sql
	 * @param params
	 * @return
	 * 赵玉柱
	 */
	public static String addWhereFilterByMap(String sql, Map<String,Object> params)
	{
		Set<String> keys = params.keySet();
		StringBuffer sqlPartBuffer = new StringBuffer();
		for (String key : keys)
		{
			sqlPartBuffer.append(" ").append(key).append("=:").append(key).append(" and ");
		}
		if (sqlPartBuffer.length() > 0)
		{
			sqlPartBuffer.setLength(sqlPartBuffer.length() - 5);
		}
		return addWhereFilter(sql, sqlPartBuffer.toString());
	}

	/**
	 * sql语法 
	 * </br> from table where group by having order by
	 * @param sql
	 * @param sqlPart
	 * @return
	 * 赵玉柱
	 */
	public static String addWhereFilter(String sql, String sqlPart)
	{
		if (StrUtil.isStrTrimNull(sql))
		{
			return "";
		}
		int whereBefore = sql.toLowerCase().indexOf(" where ");
		if (whereBefore > 0)
		{
			//含有where条件
			return sql.substring(0, whereBefore + 7) + sqlPart + " and " + sql.substring(whereBefore + 7);
		} else
		{
			int next = sql.toLowerCase().indexOf(" group by ");
			if (next > 0)//含有"group by " 在group by前添加where条件
			{
				return sql.toLowerCase().substring(0, next) + " where " + sqlPart + sql.substring(next);
			}
			next = sql.toLowerCase().indexOf(" order by ");
			if (next > 0)//含有"order by " 在order by前添加where条件
			{
				return sql.substring(0, next) + " where " + sqlPart + sql.substring(next);
			}
			//这种情况说明即没有where条件，也没有where后面的条件，直接添加一个where 和sqlPart即可
			return sql + " where " + sqlPart;
		}
	}

	/**
	 * 用于查询分页，现在已经实现了MYsql和oracle的适配，SQL Server暂时没有适配，SQL Server暂时不可用
	 * @param db
	 * @param sql
	 * @param key
	 * @param params
	 * @return
	 * 赵玉柱
	 */
	public static String parsePages(Database db, String sql, String key, Map<String,Object> params)
	{
		if (db != null)
		{
			int type = db.getType();
			if (StrUtil.isNoStrTrimNull(key))
			{
				if (key.equals(PAGENO) || key.equals(PAGESIZE))
				{
					int pageno = StrUtil.obj2int(params.get(PAGENO));
					int pagesize = StrUtil.obj2int(params.get(PAGESIZE));
					if (pageno > 0 && pagesize > 0)
					{
						int from = pagesize * pageno + 1;
						int to = from + pagesize;
						//需要进行分页
						if (type == Databasetype.MYSQL)
						{
							//MYsql
							if (sql.toLowerCase().indexOf(" limit ") < 0)
							{
								sql = sql + " limit " + from + "," + to;
							}
						}
						if (type == Databasetype.ORACLE)
						{
							//oracle
							if (sql.toLowerCase().indexOf(" rownum ") < 0)
							{
								String sqlPart = " rownum>" + from + " and rownum<" + to;
								sql = addWhereFilter(sql, sqlPart);
							}
						}
						if (type == Databasetype.SQLSQRVER)
						{
							//SQL server 暂时不实现
						}
					}
				} else if (key.equals(ROWFROM) || key.equals(ROWTO))
				{
					int from = StrUtil.obj2int(params.get(ROWFROM));
					int to = StrUtil.obj2int(params.get(ROWTO));
					if (from > 0 && to > 0)
					{
						//需要进行分页
						if (type == Databasetype.MYSQL)
						{
							//MYsql
							if (sql.toLowerCase().indexOf(" limit ") < 0)
							{
								sql = sql + " limit " + from + "," + to;
							}
						}
						if (type == Databasetype.ORACLE)
						{
							//oracle
							if (sql.toLowerCase().indexOf(" rownum ") < 0)
							{
								String sqlPart = " rownum>" + from + " and rownum<" + to;
								sql = addWhereFilter(sql, sqlPart);
							}
						}
						if (type == Databasetype.SQLSQRVER)
						{
							//SQL server 暂时不实现
						}
					}
				}
			}
		}
		return sql;
	}

	/**
	 * 格式化sql语句
	 * @param db
	 * @param src
	 * @param key
	 * @param value
	 * @return
	 * 赵玉柱
	 */
	@SuppressWarnings("unused")
	private static String parseSql(Database db, String src, String key, Object value)
	{
		if (EmptyUtil.isEmpty(value))
		{
			return replaceSql(src, ":" + key, "null");
		}
		Class<? extends Object> valueType = value.getClass();
		//常用类型
		boolean assignFlag = String.class.isAssignableFrom(valueType);
		if (assignFlag == true)
		{
			return replaceSql(src, ":" + key, "'" + value + "'");
		}
		//基本数据类型
		assignFlag = Byte.class.isAssignableFrom(valueType);
		if (assignFlag == true)
		{
			return replaceSql(src, ":" + key, "" + ((Byte) (value)).byteValue());
		}
		assignFlag = Short.class.isAssignableFrom(valueType);
		if (assignFlag == true)
		{
			return replaceSql(src, ":" + key, "" + ((Short) (value)).shortValue());
		}
		assignFlag = Integer.class.isAssignableFrom(valueType);
		if (assignFlag == true)
		{
			return replaceSql(src, ":" + key, "" + ((Integer) (value)).intValue());
		}
		assignFlag = Long.class.isAssignableFrom(valueType);
		if (assignFlag == true)
		{
			return replaceSql(src, ":" + key, "" + ((Long) (value)).longValue());
		}
		assignFlag = Float.class.isAssignableFrom(valueType);
		if (assignFlag == true)
		{
			return replaceSql(src, ":" + key, "" + ((Float) (value)).floatValue());
		}
		assignFlag = Double.class.isAssignableFrom(valueType);
		if (assignFlag == true)
		{
			return replaceSql(src, ":" + key, "" + ((Double) (value)).doubleValue());
		}
		assignFlag = Character.class.isAssignableFrom(valueType);
		if (assignFlag == true)
		{
			return replaceSql(src, ":" + key, "'" + ((Character) (value)).charValue() + "'");
		}
		//其他类型
		assignFlag = BigDecimal.class.isAssignableFrom(valueType);
		if (assignFlag == true)
		{
			return replaceSql(src, ":" + key, "" + ((BigDecimal) (value)).toString());
		}
		//特殊Date类型，对于不同数据库，应该采用不同的方式进行处理
		assignFlag = Date.class.isAssignableFrom(valueType);
		if (assignFlag == true)
		{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//mysql
			if (db.getType() == Databasetype.MYSQL)
			{
				//SELECT STR_TO_DATE('2010-05-20 11:22:33','%Y-%m-%d %H:%i:%s') 
				return replaceSql(src, ":" + key, "STR_TO_DATE('" + format.format((Date) value) + "','%Y-%m-%d %H:%i:%s')");
			}
			//Oracle
			if (db.getType() == Databasetype.ORACLE)
			{
				return replaceSql(src, ":" + key, "to_date('" + format.format((Date) value) + "','YYYY-MM-DD HH24:MI:SS')");
			}
			//SQLServer
			if (db.getType() == Databasetype.SQLSQRVER)
			{
				return replaceSql(src, ":" + key, "CONVERT(DATETIME,'" + format.format((Date) value) + "')");
			}
		}
		return src;
	}

	/**
	 * 根据key绑定 where a between 'a' and 'b' 缺少from或者to，则将用大于号或者小于号代替
	 * @param key
	 * @param params
	 * @return
	 * 赵玉柱
	 */
	public static String buildBetweenAnd(String sql, String key, Map<String,Object> params)
	{
		StringBuffer sqlTemp = new StringBuffer();
		if (StrUtil.isNoStrTrimNull(key) && (key.endsWith(".[from]") || key.endsWith(".[to]")))
		{
			String name = key.substring(0, key.lastIndexOf('.'));
			if (StrUtil.isStrTrimNull(name))
			{
				return sql;
			}
			String betweenKey = name + ".[from]";
			String andKey = name + ".[to]";
			if (sql.toLowerCase().indexOf(betweenKey) >= 0 || sql.toLowerCase().indexOf(andKey) >= 0)
			{
				return sql;
			}
			Object between = params.get(betweenKey);
			Object and = params.get(andKey);
			if (between == null && and == null)
			{
				return sql;
			}
			if (between == null)
			{
				sqlTemp.append(" (").append(name).append(" < ").append(":").append(andKey).append(" ) ");
				return addWhereFilter(sql, sqlTemp.toString());
			}
			if (and == null)
			{
				sqlTemp.append(" (").append(name).append(" > ").append(":").append(betweenKey).append(" ) ");
				return addWhereFilter(sql, sqlTemp.toString());
			}
			sqlTemp.append(" (").append(name).append(" between ").append(":").append(betweenKey).append(" and ").append(":").append(andKey).append(" ) ");
			return addWhereFilter(sql, sqlTemp.toString());
		}
		return sql;
	}

	/**
	 * 解决 存在 key 同时存在 x32与x32%下出现的bug,%表示任意字符
	 * @param sql
	 * @param key
	 * @param value
	 * @return
	 * 赵玉柱
	 */
	private static String replaceSql(String sql, String key, String value)
	{
		sql = sql.replace(key + ")", value + ")");
		sql = sql.replace(key + ",", value + ",");
		sql = sql.replace(key + " ", value + " ");
		sql = sql.replace(key + "(", value + "(");
		return sql;
	}

	/**
	 * 将sql进行初步格式化
	 * @param sql
	 * @param params
	 * @return
	 * 赵玉柱
	 */
	private static String mallocSql(Database db, String sql, Map<String,Object> params)
	{
		//1、拼装分页
		if (params.containsKey(PAGENO))
		{
			sql = parsePages(db, sql, PAGENO, params);
		}
		if (params.containsKey(PAGESIZE))
		{
			sql = parsePages(db, sql, PAGESIZE, params);
		}
		if (params.containsKey(ROWFROM))
		{
			sql = parsePages(db, sql, ROWFROM, params);
		}
		if (params.containsKey(ROWTO))
		{
			sql = parsePages(db, sql, ROWTO, params);
		}
		Set<String> fromKeys = new HashSet<>();
		for (Entry<String,Object> entry : params.entrySet())
		{
			String key = entry.getKey();
			//2、拼装sqlBetween与and
			if (key.endsWith(".[from]"))
			{
				if (!fromKeys.contains(key))
				{
					fromKeys.add(key);
					sql = buildBetweenAnd(sql, key, params);
				}
			} else if (key.endsWith(".[to]"))
			{
				String betKey = key.replace(".[to]", ".[from]");
				if (!fromKeys.contains(betKey))
				{
					sql = buildBetweenAnd(sql, key, params);
					fromKeys.add(betKey);
				}
			}
		}
		return sql;
	}

	public static void main(String[] args)
	{
		main2();
		main1();
	}

	/**
	 * 
	 * 赵玉柱
	 */
	private static void main2()
	{
		Map<String,Object> params = new HashMap<>();
		params.put("a.[from]", new Date());
		params.put("a.[to]", new Date(System.currentTimeMillis()));
		String sql = "select * from table where 1=1 group by a";
		sql = buildBetweenAnd(sql, "a.[from]", params);
		System.out.println(sql);
	}

	/**
	 * 
	 * 赵玉柱
	 */
	private static void main1()
	{
		String sql = "select * from menu where testlimit=:a and mname=:b";
		Map<String,Object> params = new HashMap<>();
		params.put("a", "a");
		params.put("b", "11");
		params.put("c", 'c');
		params.put("d", 123);
		params.put("e", new Date());
		params.put("f", new Date());
		//		params.put("g.[from]", new Date());
		//		params.put("g.[to]", new Date());
		//		params.put("h.[to]", new Date());
		params.put(PAGENO, 1);
		params.put(PAGESIZE, 2);
		params.put(ROWFROM, 3);
		params.put(ROWTO, 4);
		Database db = new Database("zyztest");
		sql = sqlBindParams(db, sql, params, new ArrayList<>());
		System.out.println(sql);
		RandomString rs = new RandomStringImpl();
		String[] array = rs.getRandomStringArray(5, 10000, 'a', 'z');
		String name = "a";
		Map<String,Object> params1 = new HashMap<>();
		long start = System.currentTimeMillis();
		sql = addWhereFilter(sql, bindSqlIn(name, array, params1, 0));
		sql = sqlBindParams(db, sql, params1, new ArrayList<>());
		System.out.println(sql);
		System.out.println("解析sql消耗时间为" + (System.currentTimeMillis() - start));
		db.close();
	}
}
