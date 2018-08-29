package xyz.zyzhu.spring.boot.db;

import java.lang.reflect.Field;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.sql.DataSource;
import com.liiwin.db.Database;
import com.liiwin.db.Databasetype;
import com.liiwin.utils.BeanUtils;
import com.liiwin.utils.StrUtil;
import xyz.zyzhu.spring.boot.model.BasModel;
import xyz.zyzhu.spring.boot.utils.DefaultValueUtils;
import xyz.zyzhu.spring.boot.utils.ModelUtils;
import xyz.zyzhu.spring.boot.utils.SpringBeanUtils;
import xyz.zyzhu.spring.config.DruidConfig;
/**
 * <p>标题： BootDatabase</p>
 * <p>功能： </p>
 * <p>所属模块： boot</p>
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
		try
		{
			conn = datasource.getConnection();
			DatabaseMetaData metaData = conn.getMetaData();
			String productName = metaData.getDatabaseProductName();
			if ("oracle".equals(productName))
			{
				type = Databasetype.ORACLE;
			} else if ("mysql".equals(productName))
			{
				type = Databasetype.MYSQL;
			} else if ("sqlserver".equals(productName))
			{
				type = Databasetype.SQLSQRVER;
			}
		} catch (SQLException e)
		{
			throw new RuntimeException("报错内容", e);
		}
	}

	public BootDatabase(DataSource datasource, String dbname)
	{
		if (datasource == null)
		{
			throw new RuntimeException("参数datasource不可为空");
		}
		this.datasource = datasource;
		try
		{
			conn = datasource.getConnection();
			DatabaseMetaData metaData = conn.getMetaData();
			String productName = metaData.getDatabaseProductName();
			if ("oracle".equals(productName))
			{
				type = Databasetype.ORACLE;
			} else if ("mysql".equals(productName))
			{
				type = Databasetype.MYSQL;
			} else if ("sqlserver".equals(productName))
			{
				type = Databasetype.SQLSQRVER;
			}
		} catch (SQLException e)
		{
			throw new RuntimeException("报错内容", e);
		}
		setDatabaseName(dbname);
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
		Map<String,Object> params = t.getTableValues();
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
		for (Map<String,Object> map : listMapFromSql)
		{
			T t = BeanUtils.instantiate(clazz);
			t.setValues(map);
			tList.add(t);
		}
		return tList;
	}

	/**************************************************查询分割线****************************************/
	/**************************************************删除****************************************/
	public <T extends BasModel> void deleteList(List<T> tList)
	{
		if (tList == null || tList.isEmpty())
		{
			return;
		}
		for (T t : tList)
		{
			delete(t);
		}
	}

	/**
	 * 单表执行删除操作
	 * @param t
	 * 赵玉柱
	 */
	public <T extends BasModel> void delete(T t)
	{
		if (t == null)
		{
			return;
		}
		String table = t.getTableName();
		if (StrUtil.isStrTrimNull(table))
		{
			throw new RuntimeException("当前对象无对应表名");
		}
		List<String> primaryKeys = t.getPrimaryKsys();
		List<Object> primaryValues = t.getPrimaryValues(false);
		if (primaryKeys == null || primaryKeys.isEmpty())
		{
			throw new RuntimeException("当前对象内码字段为空" + t);
		}
		if (primaryValues == null || primaryValues.isEmpty())
		{
			throw new RuntimeException("对象内码值为空" + t);
		}
		StringBuffer sb = new StringBuffer("delete from " + table + " where ");
		int length = primaryValues.size();
		if (length != primaryKeys.size())
		{
			throw new RuntimeException("内码字段与内码值数量不对应");
		}
		Map<String,Object> params = new HashMap<>();
		for (int i = 0; i < length; i++)
		{
			String key = primaryKeys.get(i);
			Object value = primaryValues.get(i);
			if (StrUtil.isStrTrimNull(key))
			{
				throw new RuntimeException("主键字段位空");
			}
			if (Objects.isNull(value))
			{
				throw new RuntimeException("主键值位空");
			}
			params.put(key, value);
			sb.append(key).append("=:").append(key);
			if (i != length - 1)
			{
				sb.append(" and ");
			}
			sb.append(" ");
		}
		String sql = sb.toString();
		execSqlForWrite(sql, params);
	}

	/**************************************************删除分割线****************************************/
	/**************************************************更新****************************************/
	/**
	 * 根据t执行更新
	 * @param tList
	 * 赵玉柱
	 */
	public <T extends BasModel> void updateList(List<T> tList)
	{
	}

	/**
	 * 根据t与column更新
	 * @param tList
	 * @param columns
	 * 赵玉柱
	 */
	public <T extends BasModel> void updateList(List<T> tList, String[] columns)
	{
		updateList(tList, true);
	}

	/**
	 * 根据t更新
	 * @param tList
	 * @param retainOld 当要更新的字段为空的情况下，是否保留旧值
	 * 赵玉柱
	 */
	public <T extends BasModel> void updateList(List<T> tList, boolean retainOld)
	{
		updateList(tList, null, retainOld);
	}

	/**
	 * 根据t与column更新
	 * @param tList
	 * @param columns
	 * @param retainOld 当字段为空的情况下，是否保留旧值
	 * 赵玉柱
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasModel> void updateList(List<T> tList, String[] columns, boolean retainOld)
	{
		if (tList == null || tList.isEmpty())
		{
			return;
		}
		Class<T> firstClazz = null;
		for (T t : tList)
		{
			if (t == null)
			{
				continue;
			}
			Class<T> clazz = (Class<T>) t.getClass();
			if (firstClazz == null)
			{
				firstClazz = clazz;
			}
			if (!firstClazz.equals(clazz))
			{
				throw new RuntimeException("当前对象非同一类型，不予更新操作");
			}
			update(t, columns, retainOld);
		}
	}

	/**
	 * 字段全部更新，并且保留旧值
	 * @param t
	 * 赵玉柱
	 */
	public <T extends BasModel> void update(T t)
	{
		update(t, true);
	}

	/**
	 * 字段全部更新,并且可以设置是否保留旧值
	 * @param t
	 * @param retainOld
	 * 赵玉柱
	 */
	public <T extends BasModel> void update(T t, boolean retainOld)
	{
		update(t, null, retainOld);
	}

	/**
	 * 执行更新操作
	 * @param t
	 * @param columns
	 * @param retainOld 当要更新的字段位空的情况下，是否保留原值
	 * 赵玉柱
	 */
	public <T extends BasModel> void update(T t, String[] columns, boolean retainOld)
	{
		if (t == null)
		{
			return;
		}
		String table = t.getTableName();
		if (StrUtil.isStrTrimNull(table))
		{
			throw new RuntimeException("当前对象不存在对应表名" + t.getClass());
		}
		List<String> primaryKsys = t.getPrimaryKsys();
		List<Object> primaryValues = t.getPrimaryValues(false);
		List<Object> oldPrimarys = t.getOldPrimaryValues();
		if (primaryKsys == null || primaryKsys.isEmpty())
		{
			throw new RuntimeException("当前对象不存在主键字段" + t.getClass());
		}
		if (primaryValues == null || primaryValues.isEmpty())
		{
			throw new RuntimeException("当前对象不存在主键值" + t.getClass());
		}
		if (columns == null || columns.length == 0)
		{
			Map<Field,String> columns2 = t.getColumns();
			if (columns2 == null || columns2.isEmpty())
			{
				throw new RuntimeException("当前对象获取到的数据库字段为空");
			}
			columns = new String[columns2.size()];
			int i = 0;
			for (Entry<Field,String> entry : columns2.entrySet())
			{
				String column = entry.getValue();
				columns[i++] = column;
			}
		}
		Map<String,Object> tableValues = t.getNewTableValues();
		StringBuffer sb = new StringBuffer("update " + table + " set ");
		Map<String,Object> params = new HashMap<>();
		int sbLength = sb.length();
		int length = columns.length;
		for (int i = 0; i < length; i++)
		{
			String column = columns[i];
			boolean isColumn = t.isColumn(column);
			if (isColumn)
			{
				//				if(isPrimary)
				//				{
				//					throw new RuntimeException("主键不允许更新");
				//				}
				Object object = tableValues.get(column);
				if (!Objects.isNull(object) || !retainOld)
				{
					sb.append(column).append("=:").append(column).append(",");
					params.put(column, object);
				}
			}
		}
		if (sb.length() > sbLength)
		{
			//去掉最后的逗号
			sb.setLength(sb.length() - 1);
		} else
		{
			throw new RuntimeException("column错误，当前class无字段对应");
		}
		sb.append(" ").append(" where ");
		length = primaryKsys.size();
		for (int i = 0; i < length; i++)
		{
			String column = primaryKsys.get(i);
			Object value = oldPrimarys.get(i);
			if (StrUtil.isStrTrimNull(column) || Objects.isNull(value))
			{
				throw new RuntimeException("主键字段不可为空" + t);
			}
			sb.append(column).append("=:").append(column).append("_old");
			params.put(column + "_old", value);
			if (i != length - 1)
			{
				sb.append(" and ");
			}
		}
		sb.append(" ");
		String sql = sb.toString();
		execSqlForWrite(sql, params);
	}

	/**************************************************更新分割线****************************************/
	/**************************************************插入****************************************/
	/**
	 * 将列表插入数据库
	 * @param tList
	 * 赵玉柱
	 */
	public <T extends BasModel> void insertList(List<T> tList)
	{
		insertList(tList, true);
	}

	/**
	 * 将列表插入数据库
	 * @param tList
	 * @param setDefault
	 * 赵玉柱
	 */
	public <T extends BasModel> void insertList(List<T> tList, boolean setDefault)
	{
		if (tList == null || tList.isEmpty())
		{
			return;
		}
		for (T t : tList)
		{
			insert(t, setDefault);
		}
	}

	/**
	 * 将t设置默认值后插入数据库
	 * @param t
	 * 赵玉柱
	 */
	public <T extends BasModel> void insert(T t)
	{
		insert(t, true);
	}

	/**
	 * 将t插入数据库
	 * @param t
	 * @param setDefault 是否设置默认值
	 * 赵玉柱
	 */
	public <T extends BasModel> void insert(T t, boolean setDefault)
	{
		if (t == null)
		{
			return;
		}
		StringBuffer sb = new StringBuffer("insert into ");
		String tableName = t.getTableName();
		if (StrUtil.isStrTrimNull(tableName))
		{
			throw new RuntimeException("当前类不存在对应的表名" + t.getClass());
		}
		/**
		 * 插入前先设置默认值
		 */
		if (setDefault)
		{
			DefaultValueUtils.buildDefaultValue(t, false, this);
		}
		Map<Field,String> columns = t.getColumns();
		Map<String,Object> values = t.getTableValues();
		StringBuffer columnSB = new StringBuffer("(");
		StringBuffer valueSB = new StringBuffer(" value (");
		if (columns == null || columns.isEmpty())
		{
			throw new RuntimeException("当前对象无对应表字段" + t.getClass());
		}
		if (values == null || values.isEmpty())
		{
			throw new RuntimeException("当前对象值为空，禁止进行插入操作" + t.getClass());
		}
		Map<String,Object> params = new HashMap<>();
		int length = columns.size();
		int i = 0;
		for (Entry<Field,String> entry : columns.entrySet())
		{
			String column = entry.getValue();
			Object object = values.get(column);
			columnSB.append(column);
			valueSB.append(":").append(column);
			if (i != length - 1)
			{
				columnSB.append(",");
				valueSB.append(",");
			}
			params.put(column, object);
			i++;
		}
		columnSB.append(")");
		valueSB.append(")");
		String sql = sb.toString() + " " + tableName + columnSB.toString() + valueSB.toString();
		execSqlForWrite(sql, params);
	}

	/**************************************************插入分割线****************************************/
	/**************************************************存盘****************************************/
	/**
	 * 根据t进行数据存盘
	 * @param tList
	 * 赵玉柱
	 */
	public <T extends BasModel> void saveList(List<T> tList)
	{
		if (tList == null || tList.isEmpty())
		{
			return;
		}
		for (T t : tList)
		{
			save(t);
		}
	}

	/**
	 * 根据t进行数据存盘
	 * @param t
	 * 赵玉柱
	 */
	public <T extends BasModel> void save(T t)
	{
		if (t == null)
		{
			return;
		}
		Integer saveModeInteger = t.getSaveMode();
		int saveMode = StrUtil.obj2int(saveModeInteger);
		if (saveMode == 0)
		{
			saveMode = 1;
		}
		if ((saveMode & 4) > 0)
		{
			//执行更新操作，并且不保存旧值
			update(t, false);
		} else if ((saveMode & 2) > 0)
		{
			//执行更新操作，并且保留旧值
			update(t);
		} else if ((saveMode & 1) > 0)
		{
			//执行插入操作
			insert(t);
		} else
		{
			throw new RuntimeException("不支持的操作" + saveMode);
		}
	}

	/**************************************************存盘分割线****************************************/
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
