package xyz.zyzhu.spring.boot.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.persistence.Table;
import com.liiwin.db.Database;
import com.liiwin.db.pool.DatabasePoolManager;
import com.liiwin.utils.StrUtil;
import xyz.zyzhu.spring.boot.annotation.FieldDef;
import xyz.zyzhu.spring.boot.model.BasModel;
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
	private static Map<Class<?>,List<Field>>		classFieldsCache	= new ConcurrentHashMap<>();
	/**
	 * 表与class缓存
	 */
	private static Map<Class<?>,String>				classTableCache		= new ConcurrentHashMap<>();
	/**
	 * 类字段与表对应缓存
	 */
	private static Map<Class<?>,Map<Field,String>>	classColumnCache	= new ConcurrentHashMap<>();
	/**
	 * 类的主键字段缓存
	 */
	private static Map<Class<?>,List<String>>		classPrimarysCache	= new ConcurrentHashMap<>();

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
			for (Field field : classFields)
			{
				FieldDef annotation = field.getAnnotation(FieldDef.class);
				String column = annotation.column();
				if (column == null)
				{
					column = field.getName();
				}
				map.put(field, column);
			}
			classColumnCache.put(t, map);
			return map;
		}
	}

	/**
	 * 获取所有的主键字段
	 * @param t
	 * @return
	 * 赵玉柱
	 */
	public static <T extends BasModel> List<String> getPrimaryKeys(Class<T> t)
	{
		if (t == null)
		{
			return null;
		}
		if (classPrimarysCache.containsKey(t))
		{
			return classPrimarysCache.get(t);
		}
		String modelTable = getModelTable(t);
		if (modelTable != null)
		{
		}
		Database configDatabase = DatabasePoolManager.getNewInstance().getConfigDatabase();
		String sql = "select * from table where table=:table";
		Map<String,Object> params = new HashMap<>();
		params.put("table", modelTable);
		List<Map<String,Object>> listMapFromSql = configDatabase.getListMapFromSql(sql, params);
		return null;
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
