package test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
/**
 * <p>标题： 测试JAVA的反射方法</p>
 * <p>功能： 获取某个类中的所有方法与变量</p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年10月8日 下午5:31:10</p>
 * <p>类全名：test.TestFileMethod</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestFileMethod
{
	/**
	 * @param args
	 * x250-2
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Class<TestClass> c;
		TestClass testClass = new TestClass();
		c = (Class<TestClass>) testClass.getClass();
		Method[] method = c.getDeclaredMethods();
		Field[] fields = c.getDeclaredFields();
		for (Method m : method)
		{
			System.out.println("修饰符=" + m.getModifiers() + "方法=" + m.getName());
			if (m.getModifiers() == 1)
			{
				//				Object[] o = new Object[m.getParameterTypes().length];
				//				if (o.length > 0)
				//				{
				//					try
				//					{
				//						m.invoke(c.newInstance(), o);
				//					} catch (Exception e)
				//					{
				//						// TODO Auto-generated catch block
				//						throw new RuntimeException("报错内容", e);
				//					}
				//				} else
				//				{
				//					try
				//					{
				//						m.invoke(c.newInstance());
				//					} catch (Exception e)
				//					{
				//						// TODO Auto-generated catch block
				//						throw new RuntimeException("报错内容", e);
				//					}
				//				}
			}
			System.out.println(m.getReturnType().getName());
		}
		System.out.println("========================================");
		for (Field f : fields)
		{
			System.out.println(f.getName());
		}
	}
}
