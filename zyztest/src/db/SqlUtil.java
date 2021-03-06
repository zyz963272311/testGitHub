package db;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import util.EmptyUtil;
import util.StrUtil;
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
	public static String sqlBindParams(DataBase db, String sql, Map<String,Object> params)
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

	public static String bindSqlIn(String name, String list, Map<String,Object> params)
	{
		StringBuffer sqlBuffer = new StringBuffer();
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
	private static String parseSql(DataBase db, String src, String key, Object value)
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
			if (MySql.class.isAssignableFrom(db.getClass()))
			{
				//SELECT STR_TO_DATE('2010-05-20 11:22:33','%Y-%m-%d %H:%i:%s') 
				return src.replace(":" + key, "STR_TO_DATE('" + format.format((Date) value) + "','%Y-%m-%d %H:%i:%s')");
			}
			//Oracle
			if (Oracle.class.isAssignableFrom(db.getClass()))
			{
				return src.replace(":" + key, "to_date('" + format.format((Date) value) + "','YYYY-MM-DD HH24:MI:SS')");
			}
			//SQLServer
			if (SQLServer.class.isAssignableFrom(db.getClass()))
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
		sql = sqlBindParams(new SQLServer(), sql, params);
		System.out.println(sql);
	}
}
