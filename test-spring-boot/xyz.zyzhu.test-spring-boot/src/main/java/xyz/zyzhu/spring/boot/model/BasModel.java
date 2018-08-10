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
	 * 存盘类型  0:调用save方法;1:调用issert方法;2:调用delete方法;4:调用update方法;8:删除更新[删除空字段];
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
	public Map<Field,String> getColumns()
	{
		return getColumns(getClass());
	}

	/**
	 * 获取所有的字段对应的数据库列名
	 * @param t
	 * @return
	 * 赵玉柱
	 */
	public static <T extends BasModel> Map<Field,String> getColumns(Class<T> t)
	{
		return ModelUtils.getClassColumns(t);
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

	public Map<String,Object> getValues()
	{
		Map<String,Object> values = new HashMap<>();
		values.putAll(oldValues);
		values.putAll(newValues);
		return values;
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
	public List<String> getPrimaryKsys()
	{
		return getPrimaryKeys(getClass());
	}

	/**
	 * 获取内码字段
	 * @param t
	 * @return
	 * 赵玉柱
	 */
	public static <T extends BasModel> List<String> getPrimaryKeys(Class<T> t)
	{
		return ModelUtils.getPrimaryKeyCols(t);
	}

	/**
	 * 获取当前类的主键字段
	 * @return
	 * 赵玉柱
	 */
	public List<Field> getPrimaryFields()
	{
		return getPrimaryFields(getClass());
	}

	/**
	 * 获取当前类的主键字段
	 * @param t
	 * @return
	 * 赵玉柱
	 */
	public static <T extends BasModel> List<Field> getPrimaryFields(Class<T> t)
	{
		return ModelUtils.getPrimaryFields(t);
	}

	/**
	 * 获取新主键值
	 * @return
	 * 赵玉柱
	 */
	public List<Object> getOldPrimaryValues()
	{
		return getPrimaryValues(true);
	}

	/**
	 * 获取原主键值
	 * @return
	 * 赵玉柱
	 */
	public List<Object> getNewPrimaryValues()
	{
		return getPrimaryValues(false);
	}

	/**
	 * 获取主键值
	 * @param getOld
	 * @return
	 * 赵玉柱
	 */
	public List<Object> getPrimaryValues(boolean getOld)
	{
		List<Object> result = new ArrayList<>();
		List<Field> primaryFields = getPrimaryFields();
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
