package design.pattern.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年2月23日 下午3:34:26</p>
 * <p>类全名：design.pattern.proxy.dynamic.UserDaoProxy</p>
 * 作者：x250-2
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class UserDaoProxy
{
	private final Object	target;

	public UserDaoProxy(Object target)
	{
		this.target = target;
	}

	public Object getProxyInstance()
	{
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler()
		{
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
			{
				System.out.println("开始事务");
				Object objValue = method.invoke(target, args);
				System.out.println("提交事务");
				return objValue;
			}
		});
	}
}
