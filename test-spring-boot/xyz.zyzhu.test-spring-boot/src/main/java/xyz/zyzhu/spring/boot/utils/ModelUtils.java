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
import javax.persistence.Table;
import com.liiwin.utils.StrUtil;
import xyz.zyzhu.spring.boot.annotation.FieldDef;
import xyz.zyzhu.spring.boot.db.BootDatabase;
import xyz.zyzhu.spring.boot.db.BootDatabasePoolManager;
import xyz.zyzhu.spring.boot.model.BasModel;
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
	 * 类上的主键 field缓存
	 */
	private static Map<Class<?>,List<Field>>		classPrimaryFieldsCache	= new ConcurrentHashMap<>();

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
		TableColumnDef queryDef = new TableColumnDef();
		queryDef.setTbname(tablename);
		List<TableColumnDef> query = db.query(queryDef);
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
			TableColumnDef defQuery = new TableColumnDef();
			defQuery.setTbname(tablename);
			List<TableColumnDef> query = db.query(defQuery);
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
		Set<String> primarysSet = new HashSet<>();
		BootDatabase db = BootDatabasePoolManager.getReadDatabaseByTable("tbcolumn");
		TableColumnDef queryDef = new TableColumnDef();
		queryDef.setTbname(modelTable);
		List<TableColumnDef> query = db.query(queryDef);
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
}
