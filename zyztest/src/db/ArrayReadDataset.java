package db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年5月16日 下午4:00:48</p>
 * <p>类全名：db.ArrayReadDataset</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class ArrayReadDataset implements ReadDataset
{
	private String			tablename;
	private DataColumn[]	dataColumns;
	private ResultSet		rs;
	private Object[][]		values;

	/**
	 * 构造方法，同时为values赋值
	 * @param values
	 * @param dataColumns
	 */
	public ArrayReadDataset(Object[][] values, DataColumn[] dataColumns)
	{
		if (values != null && values.length > 0 && dataColumns != null && dataColumns.length > 0)
		{
			for (int i = 0; i < values.length; i++)
			{
				if (values[i] != null && values[i].length > 0)
				{
					if (values[i].length != dataColumns.length)
					{
						throw new RuntimeException("values每行的列数必须与datacolumns的列数相同");
					}
				} else
				{
					throw new RuntimeException("values每行的不可为空");
				}
			}
		}
		this.values = values;
		this.dataColumns = dataColumns;
	}

	/**
	 * 参数为查询结果集的构造方法
	 * @param rs
	 */
	public ArrayReadDataset(ResultSet rs)
	{
		try
		{
			this.rs = rs;
			ResultSetMetaData rsmt = rs.getMetaData();
			int count = rsmt.getColumnCount();
			dataColumns = new DataColumn[count];
			for (int i = 0; i < count; i++)
			{
				int columnIdx = i + 1;
				String columnname = rsmt.getColumnName(columnIdx);
				String tablename = rsmt.getTableName(columnIdx);
				int width = rsmt.getColumnDisplaySize(columnIdx) * 5;
				int sqltype = rsmt.getColumnType(columnIdx);
				DataColumn dc = new DataColumn();
				dc.columnname = columnname;
				dc.displayFlag = 1;
				dc.sqltype = sqltype;
				dc.tablename = tablename;
				dc.title = columnname;
				dc.width = width;
				dataColumns[i] = dc;
			}
		} catch (SQLException e)
		{
			throw new RuntimeException("报错内容", e);
		}
	}

	@Override
	public void setTablename(String tablename)
	{
		this.tablename = tablename;
	}

	@Override
	public String getTableName()
	{
		return tablename;
	}

	@Override
	public DataColumn[] getDatacolumns()
	{
		return dataColumns;
	}

	@Override
	public void setDatacolumns(DataColumn[] dataColumns)
	{
		this.dataColumns = dataColumns;
	}

	/**
	 * 加载数据，如果values不为空，则直接返回values，如果values为空，则通过rs进行数据加载
	 */
	@Override
	public Object[][] loadDatasetValues()
	{
		if (values != null && values.length > 0)
		{
			return values;
		}
		try
		{
			//rs查询的结果为空
			rs.wasNull();
			if (rs.next() == false)
			{
				return values;
			}
			rs.last();
			int rows = rs.getRow();
			values = new Object[rows][dataColumns.length];
			rs.beforeFirst();
			rs.next();
			for (int i = 0; i < rows; i++)
			{
				Object[] rowValue = new Object[dataColumns.length];
				for (int j = 0; j < dataColumns.length; j++)
				{
					rowValue[j] = rs.getObject(j + 1);
				}
				values[i] = rowValue;
			}
		} catch (SQLException e)
		{
			throw new RuntimeException("报错内容", e);
		}
		return values;
	}

	public static void main(String[] args)
	{
		ResultSet rs = ResultSetUtil.getResultSet(new MySql(), "select * from testtable a, testtable2 b where a.id = b.id");
		if (rs != null)
		{
			try
			{
				ReadDataset dataset = new ArrayReadDataset(rs);
				Object[][] values = dataset.loadDatasetValues();
				for (Object[] rowValue : values)
				{
					for (Object value : rowValue)
					{
						System.out.println(value);
					}
				}
				ReadDataset dataset1 = new ArrayReadDataset(values, dataset.getDatacolumns());
				Object[][] values1 = dataset1.loadDatasetValues();
				for (Object[] rowValue : values1)
				{
					for (Object value : rowValue)
					{
						System.out.println(value);
					}
				}
			} catch (Exception e)
			{
				e.printStackTrace();
				throw new RuntimeException("报错内容", e);
			}
		}
	}
}
