package singleton;

/**
 * <p>标题：单例模式-懒汉式加载-安全 </p>
 * <p>功能：单例模式 </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年9月12日 下午2:17:13</p>
 * <p>类全名：singleton.LazySingletonSecurity</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class LazySingletonSecurity
{
	private static LazySingletonSecurity	instance;
	/**
	 * @param i 表示函数掉用的次数
	 * @param j 表示if语句执行的次数
	 */
	private static int						i	= 0, j = 0, count = 0;

	private LazySingletonSecurity()
	{
	}

	public static synchronized LazySingletonSecurity getInstance()
	{
		i++;
		if (instance == null)
		{
			instance = new LazySingletonSecurity();
			j++;
		}
		System.out.println("instance:\t" + instance.toString() + "\n函数加载的次数:\t" + i + "\n判断的次数:\t" + j);
		return instance;
	}

	public static synchronized int getCount()
	{
		return ++count;
	}

	public static String getClassName()
	{
		return "LazySingletonSecurity";
	}
}
