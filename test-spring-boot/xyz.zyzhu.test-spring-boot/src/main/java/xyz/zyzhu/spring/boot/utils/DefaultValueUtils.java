package xyz.zyzhu.spring.boot.utils;

import java.lang.reflect.Field;
/**
 * <p>标题： 默认值工具类</p>
 * <p>功能： </p>
 * <p>所属模块： 默认值工具类</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年7月30日 下午5:25:26</p>
 * <p>类全名：xyz.zyzhu.spring.boot.utils.DefaultValueUtils</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class DefaultValueUtils
{
	/**
	 * 根据class获取默认值，需要clazz具有默认的构造函数
	 * @param clazz
	 * @param recursion 递归参数列表
	 * @return
	 * 赵玉柱
	 */
	public static <E extends Object> E buildDefaultValue(Class<E> clazz, boolean recursion)
	{
		if (clazz == null)
		{
			return null;
		}
		Field[] fields = clazz.getDeclaredFields();
		return null;
	}
}
