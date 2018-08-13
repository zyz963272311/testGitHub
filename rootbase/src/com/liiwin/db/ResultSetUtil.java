package com.liiwin.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * 
 * <p>标题： resultset工具类</p>
 * <p>功能： </p>
 * <p>所属模块： rootbase</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2017年8月31日 下午4:03:43</p>
 * <p>类全名：com.liiwin.db.ResultSetUtil</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class ResultSetUtil
{
	/**
	 * 根据数据库类型和sql获取查询结果集
	 * 
	 * @param db
	 * @param sql
	 * @return 赵玉柱
	 */
	public static ResultSet getResultSet(Database db, String sql)
	{
		return db.getResultSet(sql);
	}

	public static ResultSet select(Database db, String sql, Map<String,Object> params)
	{
		List<Object> paramsList = new ArrayList<>();
		String sqlTemp = SqlUtil.sqlBindParams(db, sql, params, paramsList);
		return db.getResultSet(sqlTemp, paramsList);
	}

	/**
	 * 根据connection连接和sql获取查询结果集
	 * 
	 * @param conn
	 * @param sql
	 * @return 赵玉柱
	 * @see #getResultSet(Database, String)
	 */
	@Deprecated
	public static ResultSet getResultSet(Connection conn, String sql)
	{
		ResultSet rs = null;
		try
		{
			Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = statement.executeQuery(sql);
		} catch (SQLException e)
		{
			throw new RuntimeException("报错内容", e);
		}
		return rs;
	}

	public static List<Map<String,Object>> getListMapFromSql(Database db, String sql, Map<String,Object> params)
	{
		List<Object> paramsList = new ArrayList<>();
		String sqlTemp = SqlUtil.sqlBindParams(db, sql, params, paramsList);
		return getListMapFromSqlByParamsList(db, sqlTemp, paramsList);
	}

	public static List<Map<String,Object>> getListMapFromSqlByParamsList(Database db, String sql, List<Object> paramsList)
	{
		if (db != null)
		{
			return db.getListMapFromSqlByListParam(sql, paramsList);
		}
		return null;
	}

	public static Map<String,Object> getMapFromSql(Database db, String sql, Map<String,Object> params)
	{
		List<Object> paramsList = new ArrayList<>();
		String sqlTemp = SqlUtil.sqlBindParams(db, sql, params, paramsList);
		return getMapFromSql(db, sqlTemp);
	}

	public static Map<String,Object> getMapFromSqlByParmasList(Database db, String sql, List<Object> paramsList)
	{
		Map<String,Object> resultMap = null;
		List<Map<String,Object>> resultList = getListMapFromSqlByParamsList(db, sql, paramsList);
		if (resultList != null && !resultList.isEmpty())
		{
			resultMap = resultList.get(0);
		}
		return resultMap;
	}

	public static Map<String,Object> getMapFromSql(Database db, String sql)
	{
		return getMapFromSqlByParmasList(db, sql, null);
	}

	/**
	 * 测试方法
	 * 
	 * @param args
	 *            赵玉柱
	 */
	public static void main(String[] args)
	{
		ResultSet rs = getResultSet(new Database("zyztest"), "select * from testtable a, testtable2 b where a.id = b.id");
		if (rs != null)
		{
			try
			{
				rs.close();
			} catch (Exception e)
			{
				throw new RuntimeException("报错内容", e);
			}
		}
	}
}
