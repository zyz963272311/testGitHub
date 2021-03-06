package com.liiwin.utils;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
/**
 * <p>标题： Class操作类</p>
 * <p>功能：</p>
 * <p>所属模块： rootbas</p>
 * <p>版权： Copyright © 2018 zyzhu</p>
 * <p>公司: xyz.zyzhu </p>
 * <p> 创建日期：2018年8月18日 上午11:35:14</p>
 * <p>类全名：com.liiwin.utils.BeanUtils</p>
 * 作者：赵玉柱 
 * 初审： 
 * 复审： 
 * 监听使用界面:
 * 
 * @version 8.0
 */
public class BeanUtils
{
	private static final ConcurrentHashMap<String,Class<?>> cacheClazz = new ConcurrentHashMap<>();

	public static Object newInstance(String classPath)
	{
		return newInstance(classPath, null, null);
	}

	@SuppressWarnings("unchecked")
	public static <T extends Object> Class<T> getClassByPath(String classpath, boolean isThrow)
	{
		Class<T> class1 = (Class<T>) cacheClazz.get(classpath);
		if (class1 == null)
		{
			try
			{
				class1 = (Class<T>) Class.forName(classpath);
				cacheClazz.put(classpath, class1);
			} catch (Exception e)
			{
				if (isThrow)
				{
					throw new RuntimeException("报错内容", e);
				}
			}
		}
		return class1;
	}

	public static <T extends Object> Class<T> getClassByPath(String classpath)
	{
		return getClassByPath(classpath, true);
	}

	public static <T extends Object> T newInstance(String classPath, List<Class<?>> paramType, List<Object> paramObject)
	{
		Class<T> forName = getClassByPath(classPath);
		T newInstance = newInstanceByClass(forName, paramType, paramObject);
		return newInstance;
	}

	public static <T> T instantiate(Class<T> clazz, boolean isThrow)
	{
		if (clazz.isInterface())
		{
			if (isThrow)
			{
				throw new RuntimeException(clazz.getName() + "Specified class is an interface");
			} else
			{
				return null;
			}
		}
		try
		{
			return clazz.newInstance();
		} catch (InstantiationException ex)
		{
			if (isThrow)
			{
				throw new RuntimeException(clazz.getName() + "Is it an abstract class?", ex);
			}
		} catch (IllegalAccessException ex)
		{
			if (isThrow)
			{
				throw new RuntimeException(clazz.getName() + "Is the constructor accessible?", ex);
			}
		}
		return null;
	}

	public static <T> T instantiate(Class<T> clazz)
	{
		return instantiate(clazz, true);
	}

	public static <T extends Object> T newInstanceByClass(Class<T> forName, List<Class<?>> paramType, List<Object> params)
	{
		return newInstanceByClass(forName, paramType, params, true);
	}

	@SuppressWarnings("unchecked")
	public static <T extends Object> T newInstanceByClass(Class<T> forName, List<Class<?>> paramType, List<Object> params, boolean isThrow)
	{
		if (forName == null)
		{
			return null;
		}
		Class<?>[] paramClazz = null;
		if (paramType != null && paramType.size() > 0)
		{
			paramClazz = new Class<?>[paramType.size()];
			paramType.toArray(paramClazz);
		}
		try
		{
			Constructor<T> constructor = forName.getConstructor(paramClazz);
			Object paramsObj = null;
			if (params != null && params.size() > 0)
			{
				paramsObj = new Object[params.size()];
				params.toArray((T[]) paramsObj);
			}
			T instance = constructor.newInstance(params);
			return instance;
		} catch (Exception e)
		{
			if (isThrow)
			{
				throw new RuntimeException("获取实例失败", e);
			}
		}
		return null;
	}
}
