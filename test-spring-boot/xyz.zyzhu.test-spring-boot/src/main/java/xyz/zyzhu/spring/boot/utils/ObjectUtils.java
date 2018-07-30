package xyz.zyzhu.spring.boot.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.liiwin.utils.StrUtil;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年7月30日 下午5:52:57</p>
 * <p>类全名：xyz.zyzhu.spring.boot.utils.ObjectUtils</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class ObjectUtils
{
	private static Map<String, Map<String,Method>> classGetterMethodCache = new ConcurrentHashMap<>();
	private static Map<String, Map<String,Method>> classSetterMethodCache = new ConcurrentHashMap<>();
	public static boolean isSimpleType(Class<?> clazz)
	{
		if(isBasType(clazz))
		{
			return true;
		}
		if(BigDecimal.class.equals(clazz))
		{
			return true;
		}
		if(Map.class.isAssignableFrom(clazz))
		{
			return true;
		}
		if(Set.class.isAssignableFrom(clazz))
		{
			return true;
		}
		if(List.class.isAssignableFrom(clazz))
		{
			return true;
		}
		if(Date.class.isAssignableFrom(clazz))
		{
			return true;
		}
		if(String.class.equals(clazz))
		{
			return true;
		}
		return false;
	}
	/**
	 * 当前类是否是基本数据类型
	 * @param clazz
	 * @return
	 * 赵玉柱
	 */
	public static boolean isBasType(Class<?> clazz)
	{
		if(clazz == null)
		{
			return false;
		}
		if(Boolean.class.equals(clazz)||boolean.class.equals(clazz))
		{
			return true;
		}
		if(Integer.class.equals(clazz)||int.class.equals(clazz))
		{
			return true;
		}
		if(Byte.class.equals(clazz)||byte.class.equals(clazz))
		{
			return true;
		}
		if(Short.class.equals(clazz)||short.class.equals(clazz))
		{
			return true;
		}
		if(Long.class.equals(clazz)||long.class.equals(clazz))
		{
			return true;
		}
		if(Float.class.equals(clazz)||float.class.equals(clazz))
		{
			return true;
		}
		if(Double.class.equals(clazz)||double.class.equals(clazz))
		{
			return true;
		}
		if(Character.class.equals(clazz)||char.class.equals(clazz))
		{
			return true;
		}
		return false;
	}
	/**
	 * 获取所有的get方法
	 * @param clazz
	 * @return
	 * 赵玉柱
	 */
	public static Map<String,Method> getClassGetterMethods(Class<?> clazz)
	{
		if(clazz == null)
		{
			return null;
		}
		String name = clazz.getName();
		if(classGetterMethodCache.containsKey(name))
		{
			return classGetterMethodCache.get(name);
		}
		Map<String,Method> getMethodMap = new ConcurrentHashMap<>();
		Field[] fields = clazz.getDeclaredFields();
		for(Field field:fields)
		{
			String fieldName = field.getName();
			Method getMethod = StrUtil.dealGetMethod(field, clazz);
			getMethodMap.put(fieldName, getMethod);
		}
		return classGetterMethodCache.put(name, getMethodMap);
	}
	/**
	 * 获取当前类的set方法
	 * @param clazz
	 * @return
	 * 赵玉柱
	 */
	public static Map<String, Method> getClassSetterMethods(Class<?> clazz)
	{
		if(clazz == null)
		{
			return null;
		}
		String name = clazz.getName();
		if(classSetterMethodCache.containsKey(name))
		{
			return classSetterMethodCache.get(name);
		}
		Map<String,Method> setMethodMap = new ConcurrentHashMap<>();
		Field[] fields = clazz.getDeclaredFields();
		for(Field field:fields)
		{
			String fieldName = field.getName();
			Method setMethod = StrUtil.dealSetMethod(field, clazz);
			setMethodMap.put(fieldName, setMethod);
		}
		return classSetterMethodCache.put(name, setMethodMap);
	}
}
