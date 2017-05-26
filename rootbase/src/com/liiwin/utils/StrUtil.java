package com.liiwin.utils;

import java.util.List;
import java.util.Map;
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
		String s = "1,2,3,4.5,6,7,8.9,a,b,c.d,e,f,g";
		String[][] ss = StrUtil.split(s, '.', ',');
		for (int i = 0; i < ss.length; i++)
		{
			String[] sss = ss[i];
			for (int j = 0; j < sss.length; j++)
			{
				System.out.print("i=" + (i) + "\tj=" + j + "\t" + sss[j]);
			}
			System.out.println("\n======================");
		}
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
		boolean hasNull = false;
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
}