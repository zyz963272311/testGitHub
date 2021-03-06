package com.liiwin.createdb;

import java.io.Serializable;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： CreateDatabase使用的列对象</p>
 * <p>功能： </p>
 * <p>所属模块： rootbase</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2017年8月26日 下午1:00:23</p>
 * <p>类全名：com.liiwin.createdb.Column</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Column implements Serializable
{
	private static final long	serialVersionUID	= 401964077213938565L;
	private String				tableName;									//表名
	private String				columnName;									//列名
	private String				dataType;									//字段类型
	private int					dataLength;									//字段长度
	private int					decimal;									//精度
	private String				comment;									//注释
	/**
	 * flags 
	 * 0：无约束
	 * 1：主键约束
	 * 2：非空约束
	 * 4：唯一约束
	 * 8：外键约束 一般不用
	 */
	private int					constraint;									//约束
	private String				defaultValue;								//默认值

	public String getTableName()
	{
		return tableName;
	}

	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}

	public String getColumnName()
	{
		return columnName;
	}

	public void setColumnName(String columnName)
	{
		this.columnName = columnName;
	}

	public String getDataType()
	{
		return dataType;
	}

	public void setDataType(String dataType)
	{
		this.dataType = dataType;
	}

	public int getDataLength()
	{
		return dataLength;
	}

	public void setDataLength(int dataLength)
	{
		this.dataLength = dataLength;
	}

	public int getDecimal()
	{
		return decimal;
	}

	public void setDecimal(int decimal)
	{
		this.decimal = decimal;
	}

	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}

	public int getConstraint()
	{
		return constraint;
	}

	public void setConstraint(int constraint)
	{
		this.constraint = constraint;
	}

	public String getDefaultValue()
	{
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue)
	{
		this.defaultValue = defaultValue;
	}

	/**
	 * 比较两个Column对象是否一样
	 * @param column
	 * @return
	 * 赵玉柱
	 */
	public boolean isDiffWith(Column column)
	{
		if (column == null)
		{
			throw new RuntimeException("column不可为空");
		}
		if (!StrUtil.equals(columnName, column.getColumnName()))
		{
			return true;
		}
		if (!StrUtil.equals(dataType, column.getDataType()))
		{
			return true;
		}
		if (this.dataLength != column.getDataLength())
		{
			return true;
		}
		if (this.decimal != column.getDecimal())
		{
			return true;
		}
		if (this.constraint != column.constraint)
		{
			return true;
		}
		if (!StrUtil.equals(comment, column.getComment()))
		{
			return true;
		}
		if (!StrUtil.equals(defaultValue, column.getDefaultValue()))
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean equals(Object obj)
	{
		return toString().equals(obj.toString());
	}

	@Override
	public String toString()
	{
		return "tablename=[" + tableName + "]\tcolumnname=[" + columnName + "]\tcomment=[" + comment + "]\tnot null[" + ((constraint & 2) == 2) + "]\tprimary key[" + ((constraint & 1) == 1) + "]";
	}
}
