package design.pattern.singleton;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年2月22日 下午2:56:33</p>
 * <p>类全名：design.pattern.singleton.Signleton</p>
 * 作者：x250-2
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Singleton
{
	//私有的构造方法
	private Singleton()
	{
	}

	//创建一个内部类用来保护Singleton对象
	private static class SingletonFactory
	{
		private static Singleton	singleton	= new Singleton();
	}

	//获取实例的方法
	public static Singleton getInstance()
	{
		return SingletonFactory.singleton;
	}

	//序列化,保证序列化前后一致
	public Object readResolve()
	{
		return getInstance();
	}
}
