package singleton;

/**
 * <p>标题：单例模式-饿汉 </p>
 * <p>功能：单例模式 </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年9月12日 下午2:22:33</p>
 * <p>类全名：singleton.HungrySingleton</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class HungrySingleton
{
	private static HungrySingleton	instance	= null;
	private static int				count		= 0;
	static
	{
		instance = new HungrySingleton();
	}

	private HungrySingleton()
	{
	}

	public static HungrySingleton getInstance()
	{
		System.out.println("饿汉方式创建单例");
		return instance;
	}

	public static String getClassName()
	{
		return "HungrySingleton";
	}

	public static int getCount()
	{
		return ++count;
	}
}
