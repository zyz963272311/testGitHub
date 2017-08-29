package com.liiwin.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
/**
 * <p>标题：StringUtil工具 </p>
 * <p>功能：StringUtil工具 </p>
 * <p>所属模块： ICIP企业端</p> 
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年9月20日 上午10:24:10</p>
 * <p>类全名：util.StrUtil</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class StrUtil
{
	/**
	 * 将一个对象集合拼接成一个字符串，并在各个对象转成的字符串之间使用指定分隔符link隔开<br>
	 * 示例1:List<String> list = new ArrayList<String>();<br>
	 * list.add("abc");<br>
	 * list.add("def");<br>
	 * list.add("ghi");<br>
	 * String result1=StrUtil.stract(list,"-");<br>
	 * String result2=StrUtil.stract(list,"*");<br>
	 * 结果1:result1的值是"abc-def-ghi"<br>
	 * 结果2:result2的值是"abc*def*ghi"<br>
	 * @param list 对象集合
	 * @param link 分隔符
	 * @return 若list不为空，返回一个String；若list为空，返回null<br>
	 * 赵玉柱
	 */
	public static String strcat(List<?> list, String link)
	{
		if (list == null || list.size() == 0)
		{
			return null;
		}
		StringBuffer sb = new StringBuffer();
		int i = 0;
		for (final Object o : list)
		{
			if (i != 0)
			{
				sb.append(link);
			}
			sb.append(o);
			i++;
		}
		return sb.toString();
	}

	/**
	 * 将传入的Object数组转换成字符串数组并返回
	 * @param a 传入的Object数组
	 * @return 字符串
	 * 赵玉柱
	 */
	public static String[] toStringArray(Object[] a)
	{
		//如果a为空或a被String[]类型包含，直接返回
		if (a == null || a instanceof String[])
		{
			return (String[]) a;
		}
		final int n = a.length;
		String[] str = new String[n];
		for (int i = 0; i < n; i++)
		{
			str[i] = a[i] == null ? null : a[i].toString();
		}
		return str;
	}

	/**
	 * 传入多个字符串，中间用特定字符连接<br>
	 * 示例1:String result1 = StrUtil.strcat("-","abc");<br>
	 * 结果1:result1的值是"abc"<br>
	 * 示例2:String result2 = StrUtil.strcat("-",null);<br>
	 * 结果2:result2的值是null<br>
	 * 示例3:String result3 = StrUtil.strcat("-","abc",null);<br>
	 * 结果3:result3的值是"abc"<br>
	 * 示例4:String result4 = StrUtil.strcat("-",null,"abc");<br>
	 * 结果4:result4的值是"abc"<br>
	 * 示例5:String result5 = StrUtil.strcat("-","abc","def");<br>
	 * 结果5:result5的值是"abc-def"<br>
	 * 示例6:String result6 = StrUtil.strcat("-","abc",null,"drf");<br>
	 * 结果6:result6的值是"abc-def"<br>
	 * 示例7:String result7 = StrUtil.strcat("-","abc","def","ghi");<br>
	 * 结果7:result7的值是"abc-def-ghi"<br>
	 * @param link 连接字符
	 * @param a 多个参数列表
	 * @return 连接后的字符串,若传入的多个字符串为空，则返回null<br>
	 * 赵玉柱
	 */
	public static String strcat(String link, String[] a)
	{
		if (a == null || a.length == 0)
		{
			return null;
		}
		int i = 0;
		StringBuffer sb = new StringBuffer();
		for (final String s : a)
		{
			if (s != null && s.length() > 0)
			{
				if (i > 0)
				{
					sb.append(link);
				}
				sb.append(s);
				i++;
			}
		}
		return sb.toString();
	}

	/**
	 * 将字符串进行拼接
	 * @param src
	 * @param s
	 * @param dim
	 * @return
	 * 赵玉柱
	 */
	public static String strcat(String src, String s, String dim)
	{
		if (StrUtil.isStrTrimNull(src))
		{
			return s;
		}
		if (StrUtil.isStrTrimNull(s))
		{
			return src;
		}
		StringBuffer sb = new StringBuffer(src);
		if (StrUtil.isNoStrTrimNull(dim))
		{
			sb.append(dim);
		}
		sb.append(s);
		return sb.toString();
	}

	public static String strcat(String src, String[] strArray, String dim)
	{
		StringBuffer sb = new StringBuffer();
		if (StrUtil.isNoStrTrimNull(src))
		{
			sb.append(src);
		}
		boolean dimIsNotNull = StrUtil.isNoStrTrimNull(dim);
		if (strArray != null && strArray.length > 0)
		{
			int length = strArray.length;
			for (int i = 0; i < length; i++)
			{
				if (StrUtil.isNoStrTrimNull(strArray[i]))
				{
					if (dimIsNotNull)
					{
						sb.append(dim);
					}
					sb.append(strArray[i]);
				}
			}
		}
		return sb.length() > 0 ? sb.toString() : "";
	}

	/**
	 * 判断一个String字符串是否在另一个Strlist数组中
	 * @param str 子字符串
	 * @param strList 源字符串，形如"abc,def,ggi"
	 * @return
	 * x250-2
	 */
	public static boolean isStrIn(String str, String strList)
	{
		if (str == null || "".equals(strList) || strList == null || "".equals(strList))
		{
			return false;
		}
		String[] strs = strList.split(",");
		return isStrIn(str, strs);
	}

	/**
	 * 判断一个String字符串是否在某一个String[]数组中
	 * @param str 子字符串
	 * @param strList 源字符串数组，形如{"abc","def",'ghi'}
	 * @return
	 */
	public static boolean isStrIn(String str, String[] strs)
	{
		if (str == null || "".equals(str) || strs == null || strs.length == 0)
		{
			return false;
		}
		for (String s : strs)
		{
			if (s == null || "".equals(s))
			{
				continue;
			}
			if (str.equals(s))
			{
				return true;
			}
		}
		return false;
	}

	public static String paramsBindToSqlIn(String str, Map<String,Object> params, boolean isIn)
	{
		if (str == null || "".equals(str))
		{
			return null;
		}
		if (params == null || params.size() <= 0)
		{
			return null;
		}
		StringBuffer sb = new StringBuffer();
		sb.append(str).append(isIn ? " in" : " not in").append(" (");
		int i = 0;
		for (String key : params.keySet())
		{
			if (i == params.size() - 1)
			{
				sb.append(params.get(key));
			} else
			{
				sb.append(params.get(key)).append(",");
			}
			i++;
		}
		sb.append(")");
		return sb.toString();
	}

	public static boolean isStrTrimNull(String str)
	{
		return null == str || "".equals(str) || str.trim().equals("") || str.equalsIgnoreCase("null");
	}

	public static boolean isNoStrTrimNull(String str)
	{
		return !isStrTrimNull(str);
	}

	public static String setFirstUpperOrLower(String src, boolean isUpper)
	{
		if (StrUtil.isStrTrimNull(src))
		{
			return null;
		}
		if (isUpper)
		{
			return src.substring(0, 1).toUpperCase() + src.substring(1);
		}
		return src.substring(0, 1).toLowerCase() + src.substring(1);
	}

	public static void main(String[] args)
	{
		String ret = removeSub(" 	dsadsa dsadsa 	\n", new char[] { ' ', '\t', '\n' }, 3, 0);
		System.out.println(ret);
	}

	public static int obj2int(Object o)
	{
		return obj2int(o, 0);
	}

	public static int obj2int(Object o, int defaultValue)
	{
		if (o instanceof Number)
		{
			return ((Number) o).intValue();
		}
		if (o instanceof String)
		{
			final String s = ((String) o).trim();
			try
			{
				return Integer.parseInt(s);
			} catch (Exception e)
			{
			}
			return Double.valueOf(s).intValue();
		}
		return defaultValue;
	}

	public static long obj2long(Object o)
	{
		return obj2long(o, 0L);
	}

	public static long obj2long(Object o, long defaultValue)
	{
		if (o instanceof Number)
		{
			return ((Number) o).longValue();
		}
		if (o instanceof String)
		{
			final String s = ((String) o).trim();
			try
			{
				return Long.parseLong(s);
			} catch (Exception e)
			{
			}
			return Double.valueOf(s).longValue();
		}
		return defaultValue;
	}

	public static double obj2double(Object o)
	{
		return obj2double(o, 0);
	}

	public static double obj2double(Object o, double defaultValue)
	{
		if (o instanceof Number)
		{
			return ((Number) o).doubleValue();
		}
		if (o instanceof String)
		{
			final String s = ((String) o).trim();
			try
			{
				return Double.parseDouble(s);
			} catch (Exception e)
			{
			}
		}
		return defaultValue;
	}

	public static boolean isNullIn(String... stringList)
	{
		return isNullIn(false, stringList);
	}

	public static boolean isNullIn(boolean stringListIsNull, String... stringList)
	{
		boolean hasNull = false;
		if (stringList == null || stringList.length == 0)
		{
			if (stringListIsNull)
			{
				return true;
			}
		}
		for (String s : stringList)
		{
			hasNull = isNull(s);
			if (hasNull)
			{
				break;
			}
		}
		return hasNull;
	}

	public static boolean isNull(String s)
	{
		return s == null || s.length() == 0;
	}

	public static boolean asNull(String s)
	{
		if (isNull(s))
		{
			return true;
		}
		if (s.toUpperCase().equals("NULL"))
		{
			return true;
		}
		return false;
	}

	public static String[][] split(String src, char c1, char c2)
	{
		String[] result1 = split(src, c1);
		if (result1 != null && result1.length > 0)
		{
			String[][] result2 = new String[result1.length][];
			for (int i = 0; i < result1.length; i++)
			{
				result2[i] = split(result1[i], c2);
			}
			return result2;
		}
		return null;
	}

	public static String[] split(String src, char c)
	{
		if (src != null)
		{
			return split(src, 0, src.length(), c);
		}
		return null;
	}

	public static String[] split(String src, int startIdx, char c)
	{
		if (src != null)
		{
			return split(src, startIdx, src.length(), c);
		}
		return null;
	}

	/**
	 * 判断一个数字是否在某一个数字列表之内
	 * @param crs
	 * @param comp
	 * @return
	 * 赵玉柱
	 */
	public static boolean isIntIn(int crs, int[] comp)
	{
		boolean result = false;
		if (comp == null || comp.length == 0)
		{
			result = true;
		}
		for (int i = comp.length - 1; i >= 0; i--)
		{
			if (crs == comp[i])
			{
				result = true;
				break;
			}
		}
		return result;
	}

	/**
	 * 将一个字符串按照某个字符从某个位置开始到某个位置结束进行截取
	 * @param src 源字符串
	 * @param startIdx 开始位置，最小为0
	 * @param endIdx 结束位置，最大为src.length-1
	 * @param c 按照这个字符截取
	 * @return
	 * 赵玉柱
	 */
	public static String[] split(String src, int startIdx, int endIdx, char c)
	{
		if (src == null)
		{
			return null;
		}
		int length = src.length();
		if (startIdx < 0 || startIdx >= length)
		{
			startIdx = 0;
		}
		if (endIdx < 0 || endIdx >= length)
		{
			endIdx = length - 1;
		}
		if (startIdx >= endIdx)
		{
			int tempIdx = startIdx;
			startIdx = endIdx;
			endIdx = tempIdx;
		}
		int n = 0;
		for (int i = startIdx; i <= endIdx; i++)
		{
			if (src.charAt(i) == c)
			{
				n++;
			}
		}
		if (n == 0)
		{
			return new String[] { src };
		}
		String[] result = new String[n + 1];
		int j = 0;
		int i = startIdx;
		for (; i < endIdx;)
		{
			int iend = src.indexOf(c, i);
			if (iend < 0)
			{
				break;
			}
			result[j++] = src.substring(i, iend);
			i = iend + 1;
		}
		result[j] = src.substring(i);
		return result;
	}

	/**
	 * 将一个int类型的一个String
	 * @param list
	 * @return
	 * 赵玉柱
	 */
	public static String intListToString(int[] list)
	{
		return intListToString(list, ',');
	}

	public static String obj2str(Object obj)
	{
		return obj2str(obj, null);
	}

	public static String obj2str(Object obj, String defaultValue)
	{
		return obj != null ? obj.toString() : defaultValue;
	}

	public static boolean obj2bool(Object obj)
	{
		return obj2bool(obj, false);
	}

	public static boolean obj2bool(Object obj, boolean defaultValue)
	{
		if (obj == null)
		{
			return defaultValue;
		}
		Class<?> clz = obj.getClass();
		if (Boolean.class.isAssignableFrom(clz))
		{
			return ((Boolean) obj).booleanValue();
		}
		if (Number.class.isAssignableFrom(clz))
		{
			return ((Number) obj).intValue() != 0;
		}
		return obj.toString().toUpperCase().equals("TRUE");
	}

	public static String intListToString(int[] list, char dim)
	{
		if (list == null || list.length == 0)
		{
			return "[]";
		}
		final StringBuffer sb = new StringBuffer('[');
		final int listLength = list.length;
		for (int i = 0; i < listLength; i++)
		{
			sb.append(list[i]).append(dim);
		}
		sb.setLength(sb.length() - 1);
		sb.append(']');
		return sb.toString();
	}

	/**
	 * 获取原字符串中所有子字符串出现的位置
	 * @param resStr
	 * @param str
	 * @return
	 */
	public static int[] getStrIndexs(String resStr, String str)
	{
		if (isNullIn(true, resStr, str))
		{
			return new int[] {};
		}
		if (resStr.length() < str.length())
		{
			return new int[] {};
		}
		List<Integer> resultInteger = new ArrayList<>();
		int fromIdx = 0;
		int idx = 0;
		while ((idx = resStr.indexOf(str, fromIdx)) >= 0)
		{
			resultInteger.add(idx);
			fromIdx = idx + 1;
		}
		if (resultInteger.isEmpty())
		{
			return new int[] {};
		}
		int[] result = new int[resultInteger.size()];
		for (int i = resultInteger.size() - 1; i >= 0; i--)
		{
			result[i] = resultInteger.get(i);
		}
		return result;
	}

	/**
	 * 获取一个字符串的一些片段
	 * @param resStr
	 * @param fromIdx 起点
	 * @param toIdx 重点
	 * @return
	 */
	public static String[] getSubstring(String resStr, int[] fromIdx, int[] toIdx)
	{
		if (isStrTrimNull(resStr))
		{
			return null;
		}
		if (fromIdx == null || toIdx == null || fromIdx.length != toIdx.length)
		{
			return null;
		}
		String[] result = null;
		double[] data1 = new double[fromIdx.length];
		double[] data2 = new double[toIdx.length];
		for (int i = 0; i < fromIdx.length; i++)
		{
			data1[i] = fromIdx[i];
		}
		for (int i = 0; i < toIdx.length; i++)
		{
			data2[i] = toIdx[i];
		}
		if (sameDirectionSameSymbol(data1, data2))
		{
			result = new String[fromIdx.length];
			for (int i = 0; i < fromIdx.length; i++)
			{
				result[i] = resStr.substring(fromIdx[i], toIdx[i]);
			}
		}
		return result;
	}

	/**
	 * 判断data1的每一个值-data2的每一个值的符号是不是相同的
	 * @param data1
	 * @param data2
	 * @return
	 */
	public static boolean sameDirectionSameSymbol(double[] data1, double[] data2)
	{
		if (data1 == null || data2 == null || data1.length == 1 || data2.length == 1)
		{
			return true;
		}
		boolean dataiBigThanData2 = data1[0] - data2[0] >= 0;
		boolean result = false;
		if (dataiBigThanData2)
		{
			result = data1BigThanData2(data1, data2);
		} else
		{
			result = data1BigThanData2(data1, data2);
		}
		return result;
	}

	/**
	 * 是否是data1的每个元素大于data2的对应的元素
	 * @param data1
	 * @param data2
	 * @return
	 */
	public static boolean data1BigThanData2(double[] data1, double[] data2)
	{
		if (data1 == null || data2 == null || data1.length == 1 || data2.length == 1)
		{
			return true;
		}
		if (data1.length != data2.length)
		{
			return false;
		}
		int length = data1.length - data2.length > 0 ? data2.length : data1.length;
		boolean result = true;
		for (int i = 0; i < length; i++)
		{
			result = data1[i] > data2[i];
			if (!result)
			{
				break;
			}
		}
		return result;
	}

	/**
	 * 是否是data1的每个元素小于等于data2的对应的元素
	 * @param data1
	 * @param data2
	 * @return
	 */
	public static boolean data1SmallThanData2(double[] data1, double[] data2)
	{
		if (data1 == null || data2 == null || data1.length == 1 || data2.length == 1)
		{
			return true;
		}
		if (data1.length != data2.length)
		{
			return false;
		}
		int length = data1.length - data2.length > 0 ? data2.length : data1.length;
		boolean result = true;
		for (int i = 0; i < length; i++)
		{
			result = data1[i] < data2[i];
			if (!result)
			{
				break;
			}
		}
		return result;
	}

	/**
	 * 判断一个int数组是不是同向递增
	 * @param data
	 * @return
	 */
	public static boolean isSameDirection(double[] data)
	{
		if (data == null || data.length <= 1)
		{
			return true;
		}
		boolean isAsc = (data[1] - data[0] >= 0);
		boolean result = false;
		if (isAsc)
		{
			result = isAscDirection(data);
		} else
		{
			result = isDescDirection(data);
		}
		return result;
	}

	/**
	 * 判断一个int数组是不是正向递增
	 * @param data
	 * @return
	 */
	public static boolean isAscDirection(double[] data)
	{
		if (data == null || data.length <= 1)
		{
			return true;
		}
		boolean result = true;
		for (int i = data.length - 1; i > 0; i--)
		{
			result = data[i] - data[i - 1] <= 0;
			if (!result)
			{
				break;
			}
		}
		return result;
	}

	/**
	 * 判断一个int数组是不是反向递增
	 * @param data
	 * @return
	 */
	public static boolean isDescDirection(double[] data)
	{
		if (data == null || data.length <= 1)
		{
			return true;
		}
		boolean result = true;
		for (int i = data.length - 1; i > 0; i--)
		{
			result = data[i] - data[i - 1] >= 0;
			if (!result)
			{
				break;
			}
		}
		return result;
	}

	/**
	 * 将data设置一个偏移量
	 * @param data
	 * @param deviation
	 * @return
	 */
	public static int[] setDeviation(int[] data, int deviation)
	{
		if (data == null || data.length == 0)
		{
			return data;
		}
		for (int i = data.length - 1; i >= 0; i--)
		{
			data[i] = data[i] + deviation;
		}
		return data;
	}

	/**
	 * 按照map中的值生成固定的get与set方法
	 * @param map
	 * @return
	 * 赵玉柱
	 */
	public static Map<String,String> dealGetMethod(Map<String,Object> map)
	{
		if (map == null)
		{
			return null;
		}
		if (map.isEmpty())
		{
			return new HashMap<String,String>();
		}
		Map<String,String> resultMap = new HashMap<>();
		Set<Entry<String,Object>> entrySet = map.entrySet();
		for (Entry<String,Object> entry : entrySet)
		{
			String methodStr = dealGetMethod(entry.getKey(), entry.getValue());
			if (isNoStrTrimNull(methodStr))
			{
				resultMap.put(entry.getKey(), methodStr);
			}
		}
		return resultMap;
	}

	public static Map<String,Method> dealSetMethod(Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		Class clazz = obj.getClass();
		Field[] fields = clazz.getFields();
		return dealSetMethod(fields, clazz);
	}

	/**
	 * 按照map中的值生成固定的get与set方法
	 * @param map
	 * @return
	 * 赵玉柱
	 */
	public static Map<String,String> dealSetMethod(Map<String,Object> map)
	{
		if (map == null)
		{
			return null;
		}
		if (map.isEmpty())
		{
			return new HashMap<String,String>();
		}
		Map<String,String> resultMap = new HashMap<>();
		Set<Entry<String,Object>> entrySet = map.entrySet();
		for (Entry<String,Object> entry : entrySet)
		{
			String methodStr = dealSetMethod(entry.getKey(), entry.getValue());
			if (isNoStrTrimNull(methodStr))
			{
				resultMap.put(entry.getKey(), methodStr);
			}
		}
		return resultMap;
	}

	/**
	 * 将key转换成get方法
	 * @param key
	 * @param obj
	 * @return
	 * 赵玉柱
	 */
	public static String dealGetMethod(String key, Object obj)
	{
		return dealMethod(key, obj, true);
	}

	/**
	 * 将key转换成get方法
	 * @param key
	 * @param obj
	 * @return
	 * 赵玉柱
	 */
	public static String dealSetMethod(String key, Object obj)
	{
		return dealMethod(key, obj, false);
	}

	/**
	 * 将一个key转换成一个get或set方法
	 * @param key key
	 * @param obj 对象
	 * @param isGet 是否是get方法
	 * @return
	 * 赵玉柱
	 */
	public static String dealMethod(String key, Object obj, boolean isGet)
	{
		if (obj == null || isStrTrimNull(key))
		{
			return null;
		}
		if (isGet)
		{
			if (obj instanceof Boolean)
			{
				return "is" + setFirstUpperOrLower(key, true);
			}
			return "get" + setFirstUpperOrLower(key, true);
		} else
		{
			return "set" + setFirstUpperOrLower(key, true);
		}
	}

	public static Map<String,Method> dealSetMethod(Field[] fields, Class clazz)
	{
		if (fields == null || fields.length == 0 || clazz == null)
		{
			return null;
		}
		Map<String,Method> resultMap = new HashMap<>();
		for (Field field : fields)
		{
			Method dealSetMethod = dealSetMethod(field, clazz);
			if (dealSetMethod != null)
			{
				resultMap.put(field.getName(), dealSetMethod);
			}
		}
		return resultMap;
	}

	@SuppressWarnings("unchecked")
	public static Method dealSetMethod(Field field, Class clazz)
	{
		if (field == null)
		{
			return null;
		}
		String fieldName = field.getName();
		try
		{
			return clazz.getMethod("set" + setFirstUpperOrLower(fieldName, true), field.getClass());
		} catch (NoSuchMethodException e)
		{
			throw new RuntimeException("报错内容", e);
		} catch (SecurityException e)
		{
			throw new RuntimeException("报错内容", e);
		}
	}

	@SuppressWarnings("unchecked")
	public static Method dealGetMethod(Field field, Class clazz)
	{
		if (field == null)
		{
			return null;
		}
		String fieldName = field.getName();
		try
		{
			//Boolean.class == field.getClass() || boolean.class == field.getClass() ||
			if (Boolean.class.isAssignableFrom(field.getClass()) || boolean.class.isAssignableFrom(field.getClass()))
			{
			}
			return clazz.getMethod("set" + setFirstUpperOrLower(fieldName, true), field.getClass());
		} catch (NoSuchMethodException e)
		{
			throw new RuntimeException("报错内容", e);
		} catch (SecurityException e)
		{
			throw new RuntimeException("报错内容", e);
		}
	}

	/**
	 * 将字符串组装成一个 {prefix}+{str}x length+{str}的字符串
	 * @param prefix
	 * @param str
	 * @param length
	 * @param limit
	 * @return
	 * 赵玉柱
	 */
	public static String strcat(String prefix, String str, int length, char limit)
	{
		if (isStrTrimNull(str) && isStrTrimNull(prefix))
		{
			return strcat(limit, length);
		}
		String result = null;
		if (isStrTrimNull(prefix))
		{
			int len = length - str.length();
			result = strcat(limit, len);
			result = result + str;
			return result;
		}
		if (isStrTrimNull(str))
		{
			int len = length - prefix.length();
			result = strcat(limit, len);
			result = prefix + result;
			return result;
		}
		int len = length - str.length() - prefix.length();
		result = strcat(limit, len);
		result = prefix + result + str;
		return result;
	}

	/**
	 * 将字符组装成一个长度为 length的 内部字符全部为limit字符串
	 * @param limit
	 * @param length
	 * @return
	 * 赵玉柱
	 */
	public static String strcat(char limit, int length)
	{
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < length; i++)
		{
			buffer.append(limit);
		}
		return buffer.toString();
	}

	/**
	 * 判断c是否在src中
	 * @param src
	 * @param c
	 * @return
	 * 赵玉柱
	 */
	public static boolean isCharIn(char[] src, char c)
	{
		if (src == null || src.length == 0)
		{
			return false;
		}
		for (char s : src)
		{
			if (c == s)
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * 将src的前/后n个limit字符去掉
	 * @param src
	 * @param limit
	 * @param direction 方向 1：从前向后；2：从后向前
	 * @param size 去掉多少位 0表示不限制
	 * @return
	 * 赵玉柱
	 */
	public static String removeSub(String src, char[] limit, int direction, int size)
	{
		if (src == null)
		{
			return src;
		}
		if (size < 0 || size > src.length())
		{
			return src;
		}
		int length = src.length();
		int from = 0;
		int to = length;
		if ((direction & 1) == 1)
		{
			//去掉前面的n个字符串
			if (size == 0)
			{
				size = length;
			}
			for (int i = 0; i < size; i++)
			{
				if (isCharIn(limit, src.charAt(i)))
				{
					from++;
					continue;
				} else
				{
					break;
				}
			}
		}
		if ((direction & 2) == 2)
		{
			//去掉后面的n个字符串
			if (size == 0)
			{
				size = length;
			}
			for (int i = 0; i < size; i++)
			{
				if (isCharIn(limit, src.charAt(length - i - 1)))
				{
					to--;
					continue;
				} else
				{
					break;
				}
			}
		}
		return src.substring(from, to);
	}

	public static boolean equals(Object a, Object b)
	{
		if (a == null && b == null)
		{
			return true;
		}
		if (a == null || b == null)
		{
			return false;
		}
		if (a.equals(b))
		{
			return true;
		}
		return false;
	}

	/**
	 * 快速交换两个值
	 * @param a
	 * @param b
	 * 赵玉柱
	 */
	public static void quickSwap(long a, long b)
	{
		a = a ^ b;
		b = a ^ b;
		a = a ^ b;
		System.out.println(a + "\t" + b);
	}
}
