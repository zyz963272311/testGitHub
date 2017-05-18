package singleton;

/**
 * <p>标题：单例模式-双重校验 </p>
 * <p>功能：单例模式 </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年9月12日 下午2:34:19</p>
 * <p>类全名：singleton.SingletonDoubleCheck</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class SingletonDoubleCheck
{
	private volatile static SingletonDoubleCheck	instance;
	private static int								count	= 0;

	private SingletonDoubleCheck()
	{
	}

	@SuppressWarnings("unused")
	private static SingletonDoubleCheck getInstance()
	{
		if (instance == null)
		{
			synchronized (SingletonDoubleCheck.class)
			{
				if (instance == null)
				{
					{
						instance = new SingletonDoubleCheck();
					}
				}
			}
		}
		return instance;
	}

	public static String getClassName()
	{
		return "SingletonDoubleCheck";
	}

	public static int getCount()
	{
		return ++count;
	}
}
