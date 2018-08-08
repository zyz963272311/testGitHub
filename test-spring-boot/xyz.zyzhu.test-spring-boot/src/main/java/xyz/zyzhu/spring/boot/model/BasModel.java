package xyz.zyzhu.spring.boot.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
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
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class BasModel implements Serializable
{
	private static final long	serialVersionUID	= 1L;
	/**
	 * 当前数据的原数据
	 */
	@FieldDef(notColumn = true)
	private Map<String,Object>	oldValues;
	/**
	 * 当前数据的新数据，用于数据更新
	 */
	@FieldDef(notColumn = true)
	private Map<String,Object>	newValues;
	/**
	 * 存盘类型  0:调用save方法;1:调用issert方法;2:调用delete方法;4:调用update方法;8:删除更新[删除空字段];16:级联更新子表
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

	/**
	 * 设置值
	 * @param key
	 * @param value
	 * @return
	 * 赵玉柱
	 */
	public BasModel setValue(String key, Object value)
	{
		newValues.put(key, value);
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
		return null;
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

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
