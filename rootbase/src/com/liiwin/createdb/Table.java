package com.liiwin.createdb;

import java.io.Serializable;
import java.util.Map;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2017年8月26日 下午1:14:30</p>
 * <p>类全名：com.liiwin.createdb.Table</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Table implements Serializable
{
	private static final long	serialVersionUID	= -413582564961721606L;
	private String				dbName;									//数据库名
	private int					dbType;									//数据库类型
	private String				tableName;									//表名
	private Map<String,Column>	columns;									//列名集合

	public String getDbName()
	{
		return dbName;
	}

	public void setDbName(String dbName)
	{
		this.dbName = dbName;
	}

	public int getDbType()
	{
		return dbType;
	}

	public void setDbType(int dbType)
	{
		this.dbType = dbType;
	}

	public String getTableName()
	{
		return tableName;
	}

	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}

	public Map<String,Column> getColumns()
	{
		return columns;
	}

	public void setColumns(Map<String,Column> columns)
	{
		this.columns = columns;
	}
}
