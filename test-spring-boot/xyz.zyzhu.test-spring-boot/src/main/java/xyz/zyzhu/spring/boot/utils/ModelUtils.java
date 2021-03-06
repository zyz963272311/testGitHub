package xyz.zyzhu.spring.boot.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.persistence.Table;
import com.liiwin.db.SqlUtil;
import com.liiwin.utils.StrUtil;
import xyz.zyzhu.spring.boot.annotation.FieldDef;
import xyz.zyzhu.spring.boot.db.BootDatabase;
import xyz.zyzhu.spring.boot.db.BootDatabasePoolManager;
import xyz.zyzhu.spring.boot.model.BasModel;
import xyz.zyzhu.spring.boot.model.BootStrapQueryRequestModel;
import xyz.zyzhu.spring.boot.model.TableColumnDef;
/**
 * <p>标题： 对象工具类</p>
 * <p>功能： </p>
 * <p>所属模块： 对象工具类</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年8月7日 上午10:20:12</p>
 * <p>类全名：xyz.zyzhu.spring.boot.utils.ModelUtils</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class ModelUtils
{
	/**
	 * 类字段缓存
	 */
	private static Map<Class<?>,List<Field>>		classFieldsCache		= new ConcurrentHashMap<>();
	/**
	 * 表与class缓存
	 */
	private static Map<Class<?>,String>				classTableCache			= new ConcurrentHashMap<>();
	/**
	 * 类字段与表字段对应缓存
	 */
	private static Map<Class<?>,Map<String,Field>>	classColumnFieldsCache	= new ConcurrentHashMap<>();
	/**
	 * 类字段与表对应缓存
	 */
	private static Map<Class<?>,Map<Field,String>>	classColumnCache		= new ConcurrentHashMap<>();
	/**
	 * 类的主键字段缓存
	 */
	private static Map<Class<?>,List<String>>		classPrimaryColsCache	= new ConcurrentHashMap<>();
	/**
	 * 表的主键字段缓存
	 */
	private static Map<String,List<String>>			tablePrimaryColsCache	= new ConcurrentHashMap<>();
	/**
	 * 表的字段缓存
	 */
	private static Map<String,List<String>>			tableColsCache			= new ConcurrentHashMap<>();
	/**
	 * 表的字段缓存
	 */
	private static Map<String,List<TableColumnDef>>	tableColsDefCache		= new ConcurrentHashMap<>();
	/**
	 * 类上的主键 field缓存
	 */
	private static Map<Class<?>,List<Field>>		classPrimaryFieldsCache	= new ConcurrentHashMap<>();
	/**
	 * 清除缓存的锁
	 */
	private static Lock								clearLock				= new ReentrantLock();

	/**
	 * 获取model的field缓存
	 * @param t
	 * @return
	 * 赵玉柱
	 */
	public static <T extends BasModel> List<Field> getClassFields(Class<T> t)
	{
		/**
		 * 此处防止两个相同的类进行操作，不会对classFielsCache进行锁操作
		 */
		if (t == null)
		{
			return null;
		}
		synchronized (t)
		{
			List<Field> fields = classFieldsCache.get(t);
			if (fields != null)
			{
				return fields;
			}
			fields = new ArrayList<>();
			getClassFields(t, fields);
			classFieldsCache.put(t, fields);
			return fields;
		}
	}

	public static <T extends BasModel> Map<String,Field> getClassFieldColumns(Class<T> t)
	{
		if (t == null)
		{
			return null;
		}
		Map<String,Field> map = null;
		synchronized (t)
		{
			map = classColumnFieldsCache.get(t);
			if (map != null)
			{
				return map;
			}
		}
		Map<Field,String> classColumns = getClassColumns(t);
		map = new HashMap<>();
		String tablename = BasModel.getTableName(t);
		Set<String> columnsSet = new HashSet<>();
		BootDatabase db = BootDatabasePoolManager.getReadDatabaseByTable("tbcolumn");
		List<TableColumnDef> query = getTableColumnDefByTableName(db, tablename);
		if (query != null)
		{
			for (TableColumnDef def : query)
			{
				columnsSet.add(def.getColname());
			}
		}
		if (classColumns != null)
		{
			for (Entry<Field,String> entry : classColumns.entrySet())
			{
				String column = entry.getValue();
				Field field = entry.getKey();
				if (columnsSet.contains(column))
				{
					map.put(column, field);
				} else
				{
					throw new RuntimeException("表" + tablename + "不存在列" + column);
				}
			}
		}
		classColumnFieldsCache.put(t, map);
		BootDatabasePoolManager.close(db);
		return map;
	}

	/**
	 * 获取model的field对应列名缓存
	 * @param t
	 * @return
	 * 赵玉柱
	 */
	public static <T extends BasModel> Map<Field,String> getClassColumns(Class<T> t)
	{
		if (t == null)
		{
			return null;
		}
		Map<Field,String> map = null;
		synchronized (t)
		{
			map = classColumnCache.get(t);
			if (map != null)
			{
				return map;
			}
		}
		List<Field> classFields = getClassFields(t);
		synchronized (t)
		{
			//再一次获取，如果取到，则不进行数据处理
			map = classColumnCache.get(t);
			if (map != null)
			{
				return map;
			}
			map = new HashMap<>();
			String tablename = BasModel.getTableName(t);
			Set<String> columnsSet = new HashSet<>();
			BootDatabase db = BootDatabasePoolManager.getReadDatabaseByTable("tbcolumn");
			List<TableColumnDef> query = getTableColumnDefByTableName(db, tablename);
			if (query != null)
			{
				for (TableColumnDef def : query)
				{
					String colName = def.getColname();
					columnsSet.add(colName);
				}
			}
			for (Field field : classFields)
			{
				FieldDef annotation = field.getAnnotation(FieldDef.class);
				if (annotation != null)
				{
					String column = annotation.column();
					if (StrUtil.isStrTrimNull(column))
					{
						column = field.getName();
					}
					if (columnsSet.contains(column))
					{
						map.put(field, column);
					} else
					{
						throw new RuntimeException("表" + tablename + "不存在字段" + column);
					}
				}
			}
			classColumnCache.put(t, map);
			BootDatabasePoolManager.close(db);
			return map;
		}
	}

	/**
	 * 获取所有的主键字段,为数据库字段
	 * @param t
	 * @return
	 * 赵玉柱
	 */
	public static <T extends BasModel> List<String> getPrimaryKeyCols(Class<T> t)
	{
		if (t == null)
		{
			return null;
		}
		if (classPrimaryColsCache.containsKey(t))
		{
			return classPrimaryColsCache.get(t);
		}
		String modelTable = getModelTable(t);
		Map<Field,String> classColumns = getClassColumns(t);
		List<String> primaryKeys = new ArrayList<>();
		List<String> tablePrimaryCols = getTablePrimaryCols(modelTable);
		Set<String> primarysSet = new HashSet<>(tablePrimaryCols);
		if (modelTable != null && classColumns != null && !classColumns.isEmpty())
		{
			for (String colname : primarysSet)
			{
				if (classColumns.containsValue(colname))
				{
					primaryKeys.add(colname);
				}
			}
		}
		classPrimaryColsCache.put(t, primaryKeys);
		return primaryKeys;
	}

	/**
	 * 获取表的索引字段
	 * @param tablename
	 * @return
	 * 赵玉柱
	 */
	public static List<String> getTablePrimaryCols(String tablename)
	{
		return getTablePrimaryCols(tablename, null);
	}

	/**
	 * 获取表的索引字段
	 * @param tablename
	 * @param db
	 * @return
	 * 赵玉柱
	 */
	public static List<String> getTablePrimaryCols(String tablename, BootDatabase db)
	{
		if (StrUtil.isStrTrimNull(tablename))
		{
			throw new RuntimeException("表名不可为空");
		}
		if (tablePrimaryColsCache.containsKey(tablename))
		{
			return tablePrimaryColsCache.get(tablename);
		}
		boolean needClose = false;
		if (db == null)
		{
			db = BootDatabasePoolManager.getReadDatabaseByTable("tbcolumn");
			needClose = true;
		}
		//用于去重
		Set<String> primarysSet = new HashSet<>();
		try
		{
			TableColumnDef queryDef = new TableColumnDef();
			queryDef.setTbname(tablename);
			List<TableColumnDef> query = getTableColumnDefByTableName(db, tablename);
			if (query != null)
			{
				for (TableColumnDef def : query)
				{
					if (def.isPrimary())
					{
						primarysSet.add(def.getColname());
					}
				}
			}
			List<String> result = new ArrayList<>(primarysSet);
			tablePrimaryColsCache.put(tablename, result);
			return result;
		} finally
		{
			if (needClose)
			{
				BootDatabasePoolManager.close(db);
			}
		}
	}

	public static <T extends BasModel> String getQueryFilter(T t)
	{
		if (t == null)
		{
			return null;
		}
		StringBuffer filter = new StringBuffer();
		Map<String,Object> values = t.getTableValues();
		int i = 0;
		int length = values.size();
		if (length > 0)
		{
			filter.append(" where ");
		}
		for (Entry<String,Object> entry : values.entrySet())
		{
			String key = entry.getKey();
			filter.append(key).append("=:").append(key);
			if (i != length - 1)
			{
				filter.append(" and ");
			}
		}
		if (filter.length() == 0)
		{
			return null;
		}
		filter.append(" ");
		return filter.toString();
	}

	/**
	 * 获取所有的主键字段,为数据库字段
	 * @param t
	 * @return
	 * 赵玉柱
	 */
	public static <T extends BasModel> List<Field> getPrimaryFields(Class<T> t)
	{
		if (t == null)
		{
			return null;
		}
		if (classPrimaryFieldsCache.containsKey(t))
		{
			return classPrimaryFieldsCache.get(t);
		}
		String modelTable = getModelTable(t);
		Map<String,Field> classColumns = getClassFieldColumns(t);
		List<Field> primaryKeys = new ArrayList<>();
		if (modelTable != null && classColumns != null && !classColumns.isEmpty())
		{
			List<String> primaryKeyCols = getPrimaryKeyCols(t);
			for (Entry<String,Field> entry : classColumns.entrySet())
			{
				String colname = entry.getKey();
				if (primaryKeyCols.contains(colname))
				{
					primaryKeys.add(classColumns.get(colname));
				}
			}
		}
		classPrimaryFieldsCache.put(t, primaryKeys);
		return primaryKeys;
	}

	/**
	 * 获取Model对应的table
	 * @param t
	 * @return
	 * 赵玉柱
	 */
	public static <T extends BasModel> String getModelTable(Class<T> t)
	{
		if (t == null)
		{
			return null;
		}
		if (classTableCache.containsKey(t))
		{
			return classTableCache.get(t);
		}
		Table annotation = t.getAnnotation(Table.class);
		if (annotation != null)
		{
			String name = annotation.name();
			if (StrUtil.isNoStrTrimNull(name))
			{
				classTableCache.put(t, name);
				return name;
			}
		}
		classTableCache.put(t, null);
		return null;
	}

	/**
	 * 字段缓存到List中
	 * @param t
	 * @param fields
	 * 赵玉柱
	 */
	@SuppressWarnings("unchecked")
	private static <T extends BasModel> void getClassFields(Class<T> t, List<Field> fields)
	{
		Field[] declaredFields = t.getDeclaredFields();
		if (declaredFields != null)
		{
			for (Field field : declaredFields)
			{
				FieldDef annotation = field.getAnnotation(FieldDef.class);
				if (annotation != null)
				{
					//非数据库字段不添加到字段中
					boolean noColumn = StrUtil.obj2bool(annotation.notColumn());
					if (!noColumn)
					{
						fields.add(field);
					}
				}
			}
		}
		Class<?> superclass = t.getSuperclass();
		if (superclass != null && BasModel.class.isAssignableFrom(superclass))
		{
			getClassFields((Class<T>) superclass, fields);
		}
	}

	/**
	 * 获取表与字段的缓存
	 * @param tablename
	 * @return
	 * 赵玉柱
	 */
	public static List<String> getTableColumnByTableName(String tablename)
	{
		return getTableColumnByTableName(null, tablename);
	}

	/**
	 * 获取表与字段的缓存
	 * @param configDb
	 * @param tablename
	 * @return
	 * 赵玉柱
	 */
	public static List<String> getTableColumnByTableName(BootDatabase configDb, String tablename)
	{
		if (tablename == null)
		{
			throw new RuntimeException("表名不可为空");
		}
		if (tableColsCache.containsKey(tablename))
		{
			return tableColsCache.get(tablename);
		}
		boolean needClose = false;
		if (configDb == null)
		{
			configDb = BootDatabasePoolManager.getDatabaseByTable("tbcolumn", false);
			needClose = true;
		}
		List<String> columns = new ArrayList<>();
		try
		{
			List<TableColumnDef> defs = getTableColumnDefByTableName(configDb, tablename);
			if (defs != null)
			{
				for (TableColumnDef def : defs)
				{
					String colname = def.getColname();
					columns.add(colname);
				}
			}
			tableColsCache.put(tablename, columns);
			return columns;
		} finally
		{
			if (needClose)
			{
				BootDatabasePoolManager.close(configDb);
			}
		}
	}

	/**
	 * 根据表名获取字段信息
	 * @param configDb
	 * @param tablename
	 * @return
	 * 赵玉柱
	 */
	public static List<TableColumnDef> getTableColumnDefByTableName(BootDatabase configDb, String tablename)
	{
		synchronized (tablename)
		{
			if (StrUtil.isStrTrimNull(tablename))
			{
				throw new RuntimeException("表名不可为空");
			}
			if (tableColsDefCache.containsKey(tablename))
			{
				return tableColsDefCache.get(tablename);
			}
			String sql = "select * from tbcolumn where tbname=:tbname";
			Map<String,Object> params = new HashMap<>();
			params.put("tbname", tablename);
			List<Map<String,Object>> listMapFromSql = configDb.getListMapFromSql(sql, params);
			if (listMapFromSql == null || listMapFromSql.isEmpty())
			{
				return null;
			}
			List<TableColumnDef> result = new ArrayList<>();
			for (Map<String,Object> map : listMapFromSql)
			{
				TableColumnDef def = new TableColumnDef();
				String colname = StrUtil.obj2str(map.get("colname"));
				def.setColname(colname);
				String tbname = StrUtil.obj2str(map.get("tbname"));
				def.setTbname(tbname);
				String comment = StrUtil.obj2str(map.get("comment"));
				def.setComment(comment);
				String defaultvalue = StrUtil.obj2str(map.get("defaultvalue"));
				def.setDefaultvalue(defaultvalue);
				String datatype = StrUtil.obj2str(map.get("datatype"));
				def.setDatatype(datatype);
				Object dataLengthObj = map.get("dataLength");
				if (dataLengthObj != null)
				{
					int dataLength = StrUtil.obj2int(dataLengthObj);
					def.setDataLength(dataLength);
				}
				Object decimalObj = map.get("decimal");
				if (decimalObj != null)
				{
					int decimal = StrUtil.obj2int(decimalObj);
					def.setDecimal(decimal);
				}
				Object flagsObj = map.get("flags");
				if (flagsObj != null)
				{
					int flags = StrUtil.obj2int(flagsObj);
					def.setFlags(flags);
				}
				result.add(def);
			}
			tableColsDefCache.put(tablename, result);
			return result;
		}
	}

	public static String getQueryString(BootStrapQueryRequestModel requestModel, BootDatabase db, List<Object> queryParams)
	{
		return getQueryString(requestModel, db, queryParams, true);
	}

	/**
	 * 根据bootstrap组装查询sql
	 * @param requestModel
	 * @return
	 * 赵玉柱
	 */
	public static String getQueryString(BootStrapQueryRequestModel requestModel, BootDatabase db, List<Object> queryParams, boolean buildCount)
	{
		if (requestModel == null)
		{
			return null;
		}
		String tablename = requestModel.getTablename();
		if (StrUtil.isStrTrimNull(tablename))
		{
			throw new RuntimeException("表名不可为空");
		}
		String columns = requestModel.getColumns();
		if (StrUtil.isStrTrimNull(columns))
		{
			columns = "*";
		}
		String queryFilter = requestModel.getQueryFilter();
		Integer pagefrom = requestModel.getPagefrom();
		Integer pagesize = requestModel.getPagesize();
		List<Map<String,Object>> moreQueryFilter = requestModel.getMoreQueryFilter();
		StringBuffer moreSb = new StringBuffer();
		Map<String,Object> params = new HashMap<>();
		if (moreQueryFilter != null && !moreQueryFilter.isEmpty())
		{
			boolean appendAnd = false;
			for (Map<String,Object> moreQuery : moreQueryFilter)
			{
				//名称
				String name = StrUtil.obj2str(moreQuery.get("name"));
				//sql名称
				String sqlname = StrUtil.obj2str(moreQuery.get("sqlname"));
				if (StrUtil.isStrTrimNull(name) && StrUtil.isStrTrimNull(sqlname))
				{
					continue;
				}
				//表名前缀
				String prefix = StrUtil.obj2str(moreQuery.get("prefix"));
				//比较方式
				int compareMethod = StrUtil.obj2int(moreQuery.get("compareMethod"));
				//值
				Object value = moreQuery.get("value");
				if (value == null)
				{
					continue;
				}
				String field = (StrUtil.isStrTrimNull(prefix) ? "" : (prefix + ".")) + (StrUtil.isStrTrimNull(sqlname) ? name : sqlname);
				String buildSql = BootSqlUtils.buildSql(db, field, value, params, compareMethod);
				if (buildSql != null)
				{
					appendAnd = moreSb.length() > 0;
					if (appendAnd)
					{
						moreSb.append(" and ");
					}
					moreSb.append(buildSql);
				}
			}
		}
		if (buildCount)
		{
			if ((pagefrom != null && pagefrom.intValue() >= 1) || (pagesize != null && pagesize.intValue() >= 1))
			{
				int pagefromInt = pagefrom == null ? 0 : pagefrom.intValue() > 0 ? pagefrom.intValue() : 0;
				int pagesizeInt = pagesize == null ? 0 : pagesize.intValue() > 0 ? pagesize.intValue() : 1;
				params.put(SqlUtil.PAGENO, pagefromInt);
				params.put(SqlUtil.PAGESIZE, pagesizeInt);
			}
		}
		String groupFilter = requestModel.getGroupFilter();
		String havingFilter = requestModel.getHavingFilter();
		String sortColumns = requestModel.getSortColumns();
		StringBuffer sqlSb = new StringBuffer("select ");
		sqlSb.append(columns).append(" from ").append(tablename);
		if (StrUtil.isNoStrTrimNull(queryFilter))
		{
			sqlSb.append(" where ").append(queryFilter);
		}
		if (moreSb.length() > 0)
		{
			if (sqlSb.indexOf(" where ") >= 0)
			{
				sqlSb.append(" and ");
			}
			sqlSb.append(moreSb.toString());
		}
		if (StrUtil.isNoStrTrimNull(havingFilter))
		{
			sqlSb.append(" having ").append(havingFilter);
		}
		if (StrUtil.isNoStrTrimNull(groupFilter))
		{
			sqlSb.append(" group by ").append(groupFilter);
		}
		if (StrUtil.isNoStrTrimNull(sortColumns))
		{
			sqlSb.append(" order by ").append(sortColumns);
		}
		String sqlBindParams = SqlUtil.sqlBindParams(db, sqlSb.toString(), params, queryParams);
		return sqlBindParams;
	}

	public static String getQueryCountString(BootStrapQueryRequestModel requestModel, BootDatabase db, List<Object> queryParams)
	{
		String queryString = getQueryString(requestModel, db, queryParams, false);
		//"select count(*) count from (queryString)"
		if (queryString != null)
		{
			return "select count(*) count from (" + queryString + ") as v_count ";
		}
		return null;
	}

	/**
	 * 清除缓存
	 * 
	 * 赵玉柱
	 */
	public static void clearAll()
	{
		try
		{
			if (clearLock.tryLock(10, TimeUnit.MINUTES))
			{
				try
				{
					classFieldsCache.clear();
					classTableCache.clear();
					classColumnFieldsCache.clear();
					classColumnCache.clear();
					classPrimaryColsCache.clear();
					tablePrimaryColsCache.clear();
					tableColsCache.clear();
					tableColsDefCache.clear();
					classPrimaryFieldsCache.clear();
				} finally
				{
					clearLock.unlock();
				}
			}
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			throw new RuntimeException("报错内容", e);
		}
	}
}
