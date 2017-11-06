
package xyz.zyzhu.util;

/**
 * <p>标题： StrUtil</p>
 * <p>内容： StrUtil</p>
 * <p>创建时间： 2017年2月12日</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>类全名： xyz.zyzhu.util.StrUtil</p>
 * <p>作者： Administrator</p>
 */
public class StrUtil
{

	public static boolean int2bool(int o)
	{
		return o == 0 ? false : true;
	}

	public static boolean isStrNull(String s)
	{
		return s == null || "".equals(s.trim());
	}

	public static boolean isNotStrNull(String s)
	{
		return !isStrNull(s);
	}

	public static int obj2int(Object o)
	{
		return obj2int(o, 0);
	}

	public static int obj2int(Object o, int defaultValue)
	{
		Integer result = null;
		if (o instanceof String)
		{
			if (isNotStrNull((String) o))
			{
				result = Integer.valueOf((String) o);
			}
		}
		return result == null ? defaultValue : result.intValue();
	}

	/**
	 * 简单计算一个String公式
	 * @param clazz	最终转换的包装类
	 * @param oper 计算公式
	 * @param dealSpace 是否处理空格，无空格可以
	 * @return
	 * @see oper(Class<T>, String)
	 */
	public static <T extends Number> T oper(Class<T> clazz, String oper, boolean dealSpace)
	{
		if (dealSpace)
		{
			oper = oper.replace(" ", "");
		}
		return oper(clazz, oper);
	}

	/**
	 * 简单计算一个String公式
	 * @param clazz 最终转换成的包装类
	 * @param oper 计算公式
	 * @return 不进行空格处理
	 */
	public static <T extends Number> T oper(Class<T> clazz, String oper)
	{
		T n = null;
		try
		{
			n = clazz.newInstance();
		} catch (InstantiationException e)
		{
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		return n;
	}

	public static <T extends Number> T oper(Class<T> clazz, String sign, String... arg1)
	{
		T n = null;
		if (sign == null || sign.trim().length() == 0)
		{
			return null;
		}
		try
		{
			n = clazz.newInstance();
		} catch (InstantiationException e)
		{
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		return n;
	}

	public static void main(String[] args)
	{
		String a = "123,456,789/101|333";
		String[] bs = a.split("\\D");
		for (String b : bs)
		{
			System.out.println(b);
		}
	}
}
