package xyz.zyzhu.spring.boot.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import com.liiwin.db.Database;
import com.liiwin.utils.StrUtil;
import xyz.zyzhu.spring.boot.annotation.FieldDef;
import xyz.zyzhu.spring.boot.utils.ModelUtils;
/**
 * <p>标题： 基础Model</p>
 * <p>功能： </p>
 * <p>所属模块： boot</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2017年12月5日 下午9:33:44</p>
 * <p>类全名：xyz.zyzhu.spring.boot.model.BasModel</p>
 * 作者：赵玉柱
 * <p>顺序：
 * <p>1、
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class BasModel implements Serializable
{
	private static final long	serialVersionUID	= 1L;
	private Map<String,Field>	classFields;
	/**
	 * 当前数据的原数据
	 */
	@FieldDef(notColumn = true)
	private Map<String,Object>	oldValues			= new HashMap<>();
	/**
	 * 当前数据的新数据，用于数据更新
	 */
	@FieldDef(notColumn = true)
	private Map<String,Object>	newValues			= new HashMap<>();
	/**
	 * 存盘类型 1:调用insert方法;2:调用update方法;4:删除更新[删除空字段]; 默认位1
	 */
	@FieldDef(notColumn = true)
	private Integer				saveMode;

	/**
	 * 获取所有与数据库有关的字段
	 * @return
	 * 赵玉柱
	 */
	public List<Field> getFields()
	{
		return BasModel.getFields(getClass());
	}

	/**
	 * 获取所有与数据库有关的字段
	 * @param t
	 * @return
	 * 赵玉柱
	 */
	public static <T extends BasModel> List<Field> getFields(Class<T> t)
	{
		return ModelUtils.getClassFields(t);
	}

	/**
	 * 获取所有的字段对应的数据库列名
	 * @return
	 * 赵玉柱
	 */
	public Map<Field,String> getColumns(Database db)
	{
		return getColumns(getClass(), db);
	}

	/**
	 * 获取所有的字段对应的数据库列名
	 * @param t
	 * @return
	 * 赵玉柱
	 */
	public static <T extends BasModel> Map<Field,String> getColumns(Class<T> t, Database db)
	{
		return ModelUtils.getClassColumns(t, db);
	}

	public BasModel setValues(Map<String,Object> values)
	{
		buildOldValues(values, false);
		for (Entry<String,Object> entry : values.entrySet())
		{
			setValue(entry.getKey(), entry.getValue());
		}
		return this;
	}

	/**
	 * 设置值
	 * @param key
	 * @param value
	 * @return
	 * 赵玉柱
	 */
	protected void setValue(String key, Object value)
	{
		newValues.put(key, value);
		if (classFields == null)
		{
			classFields = new HashMap<>();
			List<Field> fields = ModelUtils.getClassFields(getClass());
			if (fields != null)
			{
				for (Field field : fields)
				{
					classFields.put(field.getName(), field);
				}
			}
		}
		Field field = classFields.get(key);
		if (field != null)
		{
			try
			{
				field.set(this, value);
			} catch (Exception e)
			{
				throw new RuntimeException("设置属性值失败key=" + key + "value=" + value);
			}
		}
	}

	/**
	 * 获取表结构对应的值
	 * @return
	 * 赵玉柱
	 */
	public Map<String,Object> getTableValues(Database db)
	{
		Map<String,Object> values = getMapValues();
		Map<String,Object> result = new HashMap<>();
		Map<Field,String> classColumns = ModelUtils.getClassColumns(getClass(), db);
		for (Entry<Field,String> entry : classColumns.entrySet())
		{
			String key = entry.getKey().getName();
			if (values.containsKey(key))
			{
				Object value = values.get(key);
				result.put(key, value);
			}
		}
		return result;
	}

	/**
	 * 当前字段是否为主键字段
	 * @param column
	 * @return
	 * 赵玉柱
	 */
	public boolean isPrimaryKeyColumn(String column, Database db)
	{
		if (StrUtil.isStrTrimNull(column))
		{
			return false;
		}
		List<String> primaryKsys = getPrimaryKsys(db);
		if (primaryKsys == null || primaryKsys.isEmpty())
		{
			return false;
		}
		return primaryKsys.contains(column);
	}

	/**
	 * 判断当前字段是否位表字段
	 * @param column
	 * @return
	 * 赵玉柱
	 */
	public boolean isColumn(String column)
	{
		if (StrUtil.isStrTrimNull(column))
		{
			return false;
		}
		Map<String,Field> tempCassFields = getClassFields();
		if (tempCassFields == null || tempCassFields.isEmpty())
		{
			return false;
		}
		return tempCassFields.containsKey(column);
	}

	/**
	 * 
	 * @return
	 * 赵玉柱
	 */
	public Map<String,Object> getNewTableValues(Database db)
	{
		Map<String,Object> values = getNewValues();
		Map<String,Object> result = new HashMap<>();
		Map<Field,String> classColumns = ModelUtils.getClassColumns(getClass(), db);
		for (Entry<Field,String> entry : classColumns.entrySet())
		{
			String key = entry.getKey().getName();
			if (values.containsKey(key))
			{
				Object value = values.get(key);
				result.put(key, value);
			}
		}
		return result;
	}

	public Map<String,Object> getOldTableValues(Database db)
	{
		Map<String,Object> values = getOldValues();
		Map<String,Object> result = new HashMap<>();
		Map<Field,String> classColumns = ModelUtils.getClassColumns(getClass(), db);
		for (Entry<Field,String> entry : classColumns.entrySet())
		{
			String key = entry.getKey().getName();
			if (values.containsKey(key))
			{
				Object value = values.get(key);
				result.put(key, value);
			}
		}
		return result;
	}

	/**
	 * 获取所有的值，新值覆盖旧值
	 * @return
	 * 赵玉柱
	 */
	public Map<String,Object> getMapValues()
	{
		Map<String,Object> result = new HashMap<>();
		result.putAll(oldValues);
		result.putAll(newValues);
		return result;
	}

	public BasModel buildOldValues(Map<String,Object> oldValue)
	{
		return buildOldValues(oldValue, true);
	}

	public BasModel buildOldValues(Map<String,Object> oldValue, boolean clear)
	{
		if (clear)
		{
			oldValues.clear();
		}
		oldValues.putAll(oldValue);
		return this;
	}

	/**
	 * 获取内码
	 * @return
	 * 赵玉柱
	 */
	public List<String> getPrimaryKsys(Database db)
	{
		return getPrimaryKeys(getClass(), db);
	}

	/**
	 * 获取内码字段
	 * @param t
	 * @return
	 * 赵玉柱
	 */
	public static <T extends BasModel> List<String> getPrimaryKeys(Class<T> t, Database db)
	{
		return ModelUtils.getPrimaryKeyCols(t, db);
	}

	/**
	 * 获取当前类的主键字段
	 * @return
	 * 赵玉柱
	 */
	public List<Field> getPrimaryFields(Database db)
	{
		return getPrimaryFields(getClass(), db);
	}

	/**
	 * 获取当前类的主键字段
	 * @param t
	 * @return
	 * 赵玉柱
	 */
	public static <T extends BasModel> List<Field> getPrimaryFields(Class<T> t, Database db)
	{
		return ModelUtils.getPrimaryFields(t, db);
	}

	/**
	 * 获取新主键值
	 * @return
	 * 赵玉柱
	 */
	public List<Object> getOldPrimaryValues(Database db)
	{
		return getPrimaryValues(true, db);
	}

	/**
	 * 获取原主键值
	 * @return
	 * 赵玉柱
	 */
	public List<Object> getNewPrimaryValues(Database db)
	{
		return getPrimaryValues(false, db);
	}

	/**
	 * 获取主键值
	 * @param getOld
	 * @return
	 * 赵玉柱
	 */
	public List<Object> getPrimaryValues(boolean getOld, Database db)
	{
		List<Object> result = new ArrayList<>();
		List<Field> primaryFields = getPrimaryFields(db);
		if (primaryFields != null)
		{
			for (Field field : primaryFields)
			{
				result.add((getOld ? oldValues : newValues).get(field.getName()));
			}
		}
		return result;
	}

	/**
	 * 获取表名
	 * @return
	 * 赵玉柱
	 */
	public String getTableName()
	{
		return getTableName(getClass());
	}

	/**
	 * 获取表名
	 * @param t
	 * @return
	 * 赵玉柱
	 */
	public static <T extends BasModel> String getTableName(Class<T> t)
	{
		return ModelUtils.getModelTable(t);
	}

	public Map<String,Object> getNewValues()
	{
		return newValues;
	}

	public void setNewValues(Map<String,Object> newValues)
	{
		this.newValues = newValues;
	}

	public Integer getSaveMode()
	{
		return saveMode;
	}

	public void setSaveMode(Integer saveMode)
	{
		this.saveMode = saveMode;
	}

	public Map<String,Field> getClassFields()
	{
		return classFields;
	}

	public Map<String,Object> getOldValues()
	{
		return oldValues;
	}

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
