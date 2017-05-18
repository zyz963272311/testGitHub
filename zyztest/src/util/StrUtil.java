package util;

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
	public static String strcat(java.util.List list, String link)
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
		String sql = null;
		StringBuffer sb = new StringBuffer();
		sb.append(sql);
		System.out.println(sb.length() + "" + sb.toString());
	}
}
