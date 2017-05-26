package com.liiwin.db;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import com.liiwin.utils.EmptyUtil;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： SQL工具类</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
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
	/**
	 * 将sql与params进行绑定
	 * @param db 
	 * @param sql 
	 * @param params
	 * @return
	 * 赵玉柱
	 */
	public static String sqlBindParams(Database db, String sql, Map<String,Object> params)
	{
		if (params == null || params.isEmpty())
		{
			return sql;
		}
		Set<Entry<String,Object>> entrySet = params.entrySet();
		for (Entry<String,Object> entry : entrySet)
		{
			String key = entry.getKey();
			if (StrUtil.isStrTrimNull(key))
			{
				continue;
			}
			Object value = entry.getValue();
			if (EmptyUtil.isEmpty(value))
			{
				continue;
			}
			sql = parseSql(db, sql, key, value);
		}
		return sql;
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
	 * 赵玉柱
	 */
	public static String bindSqlIn(String name, String list, Map<String,Object> params, int fromIdx)
	{
		StringBuffer sqlBuffer = new StringBuffer();
		fromIdx = fromIdx < 0 ? 0 : fromIdx;
		if (StrUtil.isNoStrTrimNull(name))
		{
			sqlBuffer.append(name).append(" in (");
			int length = sqlBuffer.length();
			if (StrUtil.isNoStrTrimNull(list))
			{
				String[] listArray = StrUtil.split(list, ',');
				for (int i = 0; i < listArray.length; i++)
				{
					if (StrUtil.isNoStrTrimNull(listArray[i]))
					{
						String key = name + (i + fromIdx);
						params.put(key, listArray[i]);
						sqlBuffer.append(":").append(key).append(",");
					}
				}
			}
			if (sqlBuffer.length() > length)
			{
				sqlBuffer.setLength(sqlBuffer.length() - 1);
				sqlBuffer.append(")");
			} else
			{
				return "";
			}
		}
		return sqlBuffer.toString();
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
	private static String parseSql(Database db, String src, String key, Object value)
	{
		Class valueType = value.getClass();
		//常用类型
		boolean assignFlag = String.class.isAssignableFrom(valueType);
		if (assignFlag == true)
		{
			return src.replace(":" + key, "'" + value + "'");
		}
		//基本数据类型
		assignFlag = Byte.class.isAssignableFrom(valueType);
		if (assignFlag == true)
		{
			return src.replace(":" + key, "" + ((Byte) (value)).byteValue());
		}
		assignFlag = Short.class.isAssignableFrom(valueType);
		if (assignFlag == true)
		{
			return src.replace(":" + key, "" + ((Short) (value)).shortValue());
		}
		assignFlag = Integer.class.isAssignableFrom(valueType);
		if (assignFlag == true)
		{
			return src.replace(":" + key, "" + ((Integer) (value)).intValue());
		}
		assignFlag = Long.class.isAssignableFrom(valueType);
		if (assignFlag == true)
		{
			return src.replace(":" + key, "" + ((Long) (value)).longValue());
		}
		assignFlag = Float.class.isAssignableFrom(valueType);
		if (assignFlag == true)
		{
			return src.replace(":" + key, "" + ((Float) (value)).floatValue());
		}
		assignFlag = Double.class.isAssignableFrom(valueType);
		if (assignFlag == true)
		{
			return src.replace(":" + key, "" + ((Double) (value)).doubleValue());
		}
		assignFlag = Character.class.isAssignableFrom(valueType);
		if (assignFlag == true)
		{
			return src.replace(":" + key, "'" + ((Character) (value)).charValue() + "'");
		}
		//其他类型
		assignFlag = BigDecimal.class.isAssignableFrom(valueType);
		if (assignFlag == true)
		{
			return src.replace(":" + key, "" + ((BigDecimal) (value)).toString());
		}
		//特殊Date类型，对于不同数据库，应该采用不同的方式进行处理
		assignFlag = Date.class.isAssignableFrom(valueType);
		if (assignFlag == true)
		{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//mysql
			if (db.getType() == 1)
			{
				//SELECT STR_TO_DATE('2010-05-20 11:22:33','%Y-%m-%d %H:%i:%s') 
				return src.replace(":" + key, "STR_TO_DATE('" + format.format((Date) value) + "','%Y-%m-%d %H:%i:%s')");
			}
			//Oracle
			if (db.getType() == 2)
			{
				return src.replace(":" + key, "to_date('" + format.format((Date) value) + "','YYYY-MM-DD HH24:MI:SS')");
			}
			//SQLServer
			if (db.getType() == 4)
			{
				return src.replace(":" + key, "CONVERT(DATETIME,'" + format.format((Date) value) + "')");
			}
		}
		return src;
	}

	public static void main(String[] args)
	{
		String sql = "select * from table where a= :a ane b = :b and c = :c and d = :d and e = :e";
		Map<String,Object> params = new HashMap<>();
		params.put("a", "a");
		params.put("b", BigDecimal.ZERO);
		params.put("c", 'c');
		params.put("d", 123);
		params.put("e", new Date());
		params.put("f", new Date());
		Database db = new Database("zyztest");
		sql = sqlBindParams(db, sql, params);
		System.out.println(sql);
		String a = "a,b,c,d,e";
		String name = "a";
		Map<String,Object> params1 = new HashMap<>();
		sql += " and " + bindSqlIn(name, a, params1);
		sql = sqlBindParams(db, sql, params1);
		System.out.println(sql);
		db.close();
	}
}
