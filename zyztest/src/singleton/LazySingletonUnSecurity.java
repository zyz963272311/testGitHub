package singleton;

/**
 * <p>标题：单例模式-懒汉式加载 -不安全</p>
 * <p>功能：单例模式 </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年9月12日 下午2:06:36</p>
 * <p>类全名：singleton.LazySingleton</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class LazySingletonUnSecurity
{
	private static LazySingletonUnSecurity	instance;
	private static int						count	= 0;

	private LazySingletonUnSecurity()
	{
	};

	public static LazySingletonUnSecurity getInstance()
	{
		if (instance == null)
		{
			instance = new LazySingletonUnSecurity();
		}
		return instance;
	}

	public static String getClassName()
	{
		return "LazySingletonUnSecurity";
	}

	public static int getCount()
	{
		return ++count;
	}
}
