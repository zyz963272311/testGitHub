package com.liiwin.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import com.liiwin.test.Test;
/**
 * <p>标题： Map工具类</p>
 * <p>功能： </p>
 * <p>所属模块： rootbas</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年7月29日 下午8:35:22</p>
 * <p>类全名：com.liiwin.utils.MapUtil</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class MapUtil
{
	/**
	 * 将list转换成map&lt;List&gt;的形式
	 * @param list
	 * @param keys
	 * @param limit
	 * @return
	 * 赵玉柱
	 */
	public static Map<String,List<Map<String,Object>>> buildMapByList(List<Map<String,Object>> list, String keys, String limit)
	{
		if (list == null || StrUtil.isStrTrimNull(keys))
		{
			return null;
		}
		if (list.isEmpty())
		{
			return new HashMap<>();
		}
		String[] keyArray = StrUtil.split(keys, ',');
		List<String> keyList = new ArrayList<>();
		for (String key : keyArray)
		{
			keyList.add(key);
		}
		return buildMapByList1(list, keyList, limit);
	}

	/**
	 * 将from的数据拷贝到to中
	 * @param from
	 * @param to
	 * @param onlyRelease
	 * @return
	 * 赵玉柱
	 */
	public static Map<String,Object> copyMap(Map<String,Object> from, Map<String,Object> to, boolean onlyRelease)
	{
		if (from == null || from.isEmpty())
		{
			return to;
		}
		if ((to == null || to.isEmpty()) && onlyRelease)
		{
			return to;
		}
		if (to == null)
		{
			to = new HashMap<>();
		}
		for (Entry<String,Object> fromEntry : from.entrySet())
		{
			to.put(fromEntry.getKey(), fromEntry.getValue());
		}
		return to;
	}

	/**
	 * 将List组装成Map&lt;List&gt;形式
	 * @param list
	 * @param keyList
	 * @param limit
	 * @return
	 * 赵玉柱
	 */
	public static Map<String,List<Map<String,Object>>> buildMapByList1(List<Map<String,Object>> list, List<String> keyList, String limit)
	{
		if (list == null || keyList == null || keyList.isEmpty())
		{
			return null;
		}
		if (list.isEmpty())
		{
			return new HashMap<>();
		}
		Map<String,List<Map<String,Object>>> result = new HashMap<>();
		for (Map<String,Object> map : list)
		{
			String keyVAlue = getKeys(map, keyList, limit);
			List<Map<String,Object>> value = result.get(keyVAlue);
			if (value == null)
			{
				value = new ArrayList<>();
				result.put(keyVAlue, value);
			}
			value.add(map);
		}
		return result;
	}

	/**
	 * 获取map的key对应的value
	 * @param map
	 * @param keyList
	 * @param limit
	 * @return
	 * 赵玉柱
	 */
	public static String getKeys(Map<String,Object> map, List<String> keyList, String limit)
	{
		if (map == null || map.isEmpty() || keyList == null || keyList.isEmpty())
		{
			return null;
		}
		String result = null;
		for (String key : keyList)
		{
			Object object = map.get(key);
			String value = StrUtil.obj2str(object);
			result = result + (value == null ? "" : value) + (limit == null ? "" : limit);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public static <T extends Object> T toObject(Class<T> clazz, Map<String,Object> map)
	{
		if (map == null)
		{
			return null;
		}
		T t = null;
		try
		{
			t = clazz.newInstance();
			if (map.isEmpty())
			{
				return t;
			}
			Map<String,Method> methodMap = new HashMap<String,Method>();
			Method[] methods = clazz.getMethods();
			if (methods != null)
			{
				for (Method method : methods)
				{
					methodMap.put(method.getName(), method);
				}
			}
			if (map.isEmpty())
			{
				return t;
			}
			Map<String,String> setMap = StrUtil.dealSetMethod(map);
			if (setMap.isEmpty())
			{
				return t;
			}
			Set<String> methodSet = setMap.keySet();
			for (String field : methodSet)
			{
				Object o1 = map.get(field);
				String methodName = setMap.get(field);
				Method method = methodMap.get(methodName);
				Class[] paramsClass = method.getParameterTypes();
				if (method != null && paramsClass != null)
				{
					if (paramsClass != null && paramsClass.length > 0 && (o1.getClass() == paramsClass[0] || paramsClass[0].isAssignableFrom(o1.getClass())))
					{
						method.invoke(t, o1);
					}
				}
			}
		} catch (InstantiationException e)
		{
			throw new RuntimeException("报错内容", e);
		} catch (IllegalAccessException e)
		{
			throw new RuntimeException("报错内容", e);
		} catch (IllegalArgumentException e)
		{
			throw new RuntimeException("报错内容", e);
		} catch (InvocationTargetException e)
		{
			throw new RuntimeException("报错内容", e);
		}
		return t;
	}

	public static boolean constansKey(Map<String,Object> map, String keys)
	{
		return constainsKey(map, keys, ',');
	}

	public static boolean constainsKey(Map<String,Object> map, String keys, char limit)
	{
		List<String> keyList = StrUtil.toList(keys, limit);
		return constainsKey(map, keyList);
	}

	public static boolean constainsAllKey(Map<String,Object> map, String keys, char limit)
	{
		List<String> keyList = StrUtil.toList(keys, limit);
		return constainsAllKey(map, keyList);
	}

	public static <K,V> boolean constainsAllKey(Map<K,V> map, List<K> keys)
	{
		return constainsKeys(map, keys, true);
	}

	public static <K,V> boolean constainsKey(Map<K,V> map, List<K> keys)
	{
		return constainsKeys(map, keys, false);
	}

	public static <K,V> boolean constainsKeys(Map<K,V> map, List<K> keys, boolean isAll)
	{
		if (map == null || map.isEmpty() || keys == null || keys.size() == 0)
		{
			return false;
		}
		for (K key : keys)
		{
			boolean constans = map.containsKey(key);
			if (constans && !isAll)
			{
				return true;
			}
			if (!constans && isAll)
			{
				return false;
			}
		}
		return isAll;
	}

	public static Map<String,Object> toMap(Object o)
	{
		if (o == null)
		{
			return null;
		}
		Map<String,Method> setMethod = StrUtil.dealSetMethod(o);
		if (setMethod == null)
		{
			return null;
		}
		Map<String,Object> resultMap = new HashMap<>();
		if (setMethod.isEmpty())
		{
			return resultMap;
		}
		return resultMap;
	}

	public static void main(String[] args)
	{
		System.out.println(Number.class.isAssignableFrom(Integer.class));
		Map<String,Object> map = new HashMap<>();
		map.put("a", new Integer(1));
		map.put("b", new Boolean(false));
		map.put("c", new String("String"));
		map.put("d", new String[] { "aaa", "bbb" });
		Test t = toObject(Test.class, map);
		System.out.println(t);
		Map<String,Object> mp = toMap(t);
		System.out.println(mp);
	}
}
