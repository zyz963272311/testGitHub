package xyz.zyzhu.spring.boot.db;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.sql.DataSource;
import com.liiwin.db.Database;
import com.liiwin.utils.StrUtil;
import xyz.zyzhu.spring.boot.model.BasModel;
import xyz.zyzhu.spring.boot.utils.ModelUtils;
import xyz.zyzhu.spring.boot.utils.SpringBeanUtils;
import xyz.zyzhu.spring.config.DruidConfig;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年8月10日 上午10:06:50</p>
 * <p>类全名：xyz.zyzhu.spring.boot.db.BootDatabase</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class BootDatabase extends Database
{
	private AtomicBoolean	isWrite	= new AtomicBoolean(true);
	DataSource				datasource;

	/**************************************************构造方法****************************************/
	/**
	 * @param databaseName
	 */
	public BootDatabase(String databaseName)
	{
		this(databaseName, false);
	}

	public BootDatabase(String databaseName, boolean isRead)
	{
		super();
		if (StrUtil.isStrTrimNull(databaseName))
		{
			throw new RuntimeException("参数databaseName不可为空");
		}
		setDatabaseName(databaseName);
		DruidConfig druidConfig = SpringBeanUtils.getBean(DruidConfig.class);
		DataSource datasource = null;
		if (isRead)
		{
			datasource = druidConfig.readDatasourceByDbName(databaseName);
		} else
		{
			datasource = druidConfig.writeDataSourceByDbName(databaseName);
		}
		if (datasource == null)
		{
			throw new RuntimeException("获取数据源失败" + databaseName);
		}
		this.datasource = datasource;
	}

	public BootDatabase(DataSource datasource, String dbname)
	{
		if (datasource == null)
		{
			throw new RuntimeException("参数datasource不可为空");
		}
		this.datasource = datasource;
		setDatabaseName(dbname);
	}

	@Override
	public Connection getConn()
	{
		try
		{
			return datasource.getConnection();
		} catch (SQLException e)
		{
			throw new RuntimeException("报错内容", e);
		}
	}

	/**************************************************构造方法分割线****************************************/
	/**************************************************查询****************************************/
	@SuppressWarnings("unchecked")
	public <T extends BasModel> List<T> query(T t)
	{
		if (t == null)
		{
			return null;
		}
		String filter = ModelUtils.getQueryFilter(t);
		Map<String,Object> params = t.getValues();
		Class<T> class1 = (Class<T>) t.getClass();
		return query(class1, params, filter, null);
	}

	public <T extends BasModel> List<T> query(Class<T> clazz, String filter)
	{
		return query(clazz, null, filter);
	}

	/**
	 * 根据class与查询参数进行数据查询
	 * @param clazz
	 * @param params
	 * @param filter
	 * @return
	 * 赵玉柱
	 */
	public <T extends BasModel> List<T> query(Class<T> clazz, Map<String,Object> params, String filter)
	{
		return query(clazz, params, filter, null);
	}

	/**
	 * 根据对象查询数据
	 * @param t
	 * @return
	 * 赵玉柱
	 */
	public <T extends BasModel> T query1(T t)
	{
		return query1(t, null);
	}

	/**
	 * 根据对象与查询条件查询s数据
	 * @param t
	 * @param filter
	 * @return
	 * 赵玉柱
	 */
	public <T extends BasModel> T query1(T t, String filter)
	{
		return query1(t, null, filter);
	}

	/**
	 * 根据对象与查询参数查询数据
	 * @param t
	 * @param params
	 * @param filter
	 * @return
	 * 赵玉柱
	 */
	public <T extends BasModel> T query1(T t, Map<String,Object> params, String filter)
	{
		return query1(t, params, filter, null);
	}

	/**
	 * 根据对象与查询参数查询数据
	 * @param t
	 * @param params
	 * @param filter
	 * @param columns
	 * @return
	 * 赵玉柱
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasModel> T query1(T t, Map<String,Object> params, String filter, String[] columns)
	{
		Class<T> clazz = (Class<T>) t.getClass();
		List<T> query = query(clazz, params, filter, columns);
		if (query != null && !query.isEmpty())
		{
			return query.get(0);
		}
		return null;
	}

	/**
	 * 根据class与查询参数进行数据查询
	 * @param clazz
	 * @param filter
	 * @return
	 * 赵玉柱
	 */
	public <T extends BasModel> T query1(Class<T> clazz, String filter)
	{
		return query1(clazz, null, filter);
	}

	/**
	 * 根据class与查询参数进行数据查询
	 * @param clazz
	 * @param params
	 * @param filter
	 * @return
	 * 赵玉柱
	 */
	public <T extends BasModel> T query1(Class<T> clazz, Map<String,Object> params, String filter)
	{
		return query1(clazz, params, filter, null);
	}

	/**
	 * 根据class与查询参数进行数据查询
	 * @param clazz
	 * @param params
	 * @param filter
	 * @param columns
	 * @return
	 * 赵玉柱
	 */
	public <T extends BasModel> T query1(Class<T> clazz, Map<String,Object> params, String filter, String[] columns)
	{
		List<T> query = query(clazz, params, filter, columns);
		if (query != null && !query.isEmpty())
		{
			return query.get(0);
		}
		return null;
	}

	/**
	 * 根据class与查询参数进行数据查询
	 * @param clazz
	 * @param params
	 * @param filter
	 * @param columns
	 * @return
	 * 赵玉柱
	 */
	public <T extends BasModel> List<T> query(Class<T> clazz, Map<String,Object> params, String filter, String[] columns)
	{
		if (clazz == null)
		{
			return null;
		}
		if (columns == null || columns.length == 0)
		{
			Map<Field,String> classColumns = ModelUtils.getClassColumns(clazz);
			columns = new String[classColumns.size()];
			int i = 0;
			for (Entry<Field,String> entry : classColumns.entrySet())
			{
				columns[i++] = entry.getValue();
			}
		}
		StringBuffer sb = new StringBuffer("select ");
		int length = columns.length;
		int i = 0;
		for (String column : columns)
		{
			sb.append(column);
			if (i != length - 1)
			{
				sb.append(",");
			}
			i++;
		}
		String modelTable = ModelUtils.getModelTable(clazz);
		if (!StrUtil.trimStartWith(filter, "where "))
		{
			filter = " where " + filter;
		}
		String sql = sb.toString() + " from " + modelTable + filter;
		List<Map<String,Object>> listMapFromSql = getListMapFromSql(sql, params);
		if (listMapFromSql == null || listMapFromSql.isEmpty())
		{
			return null;
		}
		List<T> tList = new ArrayList<>();
		try
		{
			for (Map<String,Object> map : listMapFromSql)
			{
				T t = clazz.newInstance();
				t.setValues(map);
				tList.add(t);
			}
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
		return tList;
	}

	/**************************************************查询分割线****************************************/
	public BootDatabase setIsWrite()
	{
		isWrite.set(true);
		return this;
	}

	public BootDatabase setIsRead()
	{
		isWrite.set(false);
		return this;
	}

	public boolean getIsWrite()
	{
		return isWrite.get();
	}
}
