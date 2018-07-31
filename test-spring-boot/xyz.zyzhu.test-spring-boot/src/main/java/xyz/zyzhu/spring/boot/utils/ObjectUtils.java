package xyz.zyzhu.spring.boot.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.persistence.Table;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年7月30日 下午5:52:57</p>
 * <p>类全名：xyz.zyzhu.spring.boot.utils.ObjectUtils</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class ObjectUtils
{
	private static Map<String,Map<String,Method>>	classGetterMethodCache	= new ConcurrentHashMap<>();
	private static Map<String,Map<String,Method>>	classSetterMethodCache	= new ConcurrentHashMap<>();
	private static final String						CONSTRUCTOR				= "constructor";
	private static final String						CONSTRUCTOR_PARAMS		= "constructorParams";
	private static final String						METHOD					= "method";
	private static final String						METHOD_PARAMS			= "methodParams";
	private static final String						CLASS					= "class";

	public static boolean isSimpleType(Class<?> clazz)
	{
		if (isBasType(clazz))
		{
			return true;
		}
		if (BigDecimal.class.equals(clazz))
		{
			return true;
		}
		//		if(Map.class.isAssignableFrom(clazz))
		//		{
		//			return true;
		//		}
		//		if(Set.class.isAssignableFrom(clazz))
		//		{
		//			return true;
		//		}
		//		if(List.class.isAssignableFrom(clazz))
		//		{
		//			return true;
		//		}
		if (Date.class.isAssignableFrom(clazz))
		{
			return true;
		}
		if (String.class.equals(clazz))
		{
			return true;
		}
		return false;
	}

	/**
	 * 当前类是否是基本数据类型
	 * @param clazz
	 * @return
	 * 赵玉柱
	 */
	public static boolean isBasType(Class<?> clazz)
	{
		if (clazz == null)
		{
			return false;
		}
		if (Boolean.class.equals(clazz) || boolean.class.equals(clazz))
		{
			return true;
		}
		if (Integer.class.equals(clazz) || int.class.equals(clazz))
		{
			return true;
		}
		if (Byte.class.equals(clazz) || byte.class.equals(clazz))
		{
			return true;
		}
		if (Short.class.equals(clazz) || short.class.equals(clazz))
		{
			return true;
		}
		if (Long.class.equals(clazz) || long.class.equals(clazz))
		{
			return true;
		}
		if (Float.class.equals(clazz) || float.class.equals(clazz))
		{
			return true;
		}
		if (Double.class.equals(clazz) || double.class.equals(clazz))
		{
			return true;
		}
		if (Character.class.equals(clazz) || char.class.equals(clazz))
		{
			return true;
		}
		return false;
	}

	/**
	 * 获取所有的get方法
	 * @param clazz
	 * @return
	 * 赵玉柱
	 */
	public static Map<String,Method> getClassGetterMethods(Class<?> clazz)
	{
		if (clazz == null)
		{
			return null;
		}
		String name = clazz.getName();
		if (classGetterMethodCache.containsKey(name))
		{
			return classGetterMethodCache.get(name);
		}
		Map<String,Method> getMethodMap = new ConcurrentHashMap<>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields)
		{
			String fieldName = field.getName();
			Method getMethod = StrUtil.dealGetMethod(field, clazz);
			getMethodMap.put(fieldName, getMethod);
		}
		return classGetterMethodCache.put(name, getMethodMap);
	}

	/**
	 * 获取当前类的set方法
	 * @param clazz
	 * @return
	 * 赵玉柱
	 */
	public static Map<String,Method> getClassSetterMethods(Class<?> clazz)
	{
		if (clazz == null)
		{
			return null;
		}
		String name = clazz.getName();
		if (classSetterMethodCache.containsKey(name))
		{
			return classSetterMethodCache.get(name);
		}
		Map<String,Method> setMethodMap = new ConcurrentHashMap<>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields)
		{
			String fieldName = field.getName();
			Method setMethod = StrUtil.dealSetMethod(field, clazz);
			setMethodMap.put(fieldName, setMethod);
		}
		return classSetterMethodCache.put(name, setMethodMap);
	}

	/**
	 * 根据对象获取表名
	 * @param o
	 * @return
	 * 赵玉柱
	 */
	public static String getTableName(Object o)
	{
		if (o == null)
		{
			return null;
		}
		Class<?> clazz = o.getClass();
		return getTableNameByClass(clazz);
	}

	/**
	 * 根据类名获取表名
	 * @param clazz
	 * @return
	 * 赵玉柱
	 */
	public static String getTableNameByClass(Class<?> clazz)
	{
		if (clazz == null)
		{
			return null;
		}
		Table tableAnno = clazz.getDeclaredAnnotation(Table.class);
		String tablename = null;
		if (tableAnno != null)
		{
			tablename = tableAnno.name();
		}
		if (StrUtil.isStrTrimNull(tablename))
		{
			tablename = clazz.getName();
		}
		return tablename;
	}

	/**
	 * 字符串是否是合法的java方法
	 * 类型：
	 * <p>
	 * 1、静态方法:package.Class.method(Class... args1)
	 * <p>
	 * 2、静态内部类的静态方法 package.Class1.Class2.method(Class... args1)
	 * <p>
	 * 3、静态内部类的非静态方法：new package.Class1.Class2(Class ...args1).method(Class... args2)
	 * <p>
	 * 4、非静态方法：new package.Class(Class... args1).method(Class... args2)
	 * <p>
	 * 5、非静态内部类的非静态方法：new package.Class1(Class... args1).new Class2(Class... args2).method(Class... args3)
	 * <p>
	 * 6、非静态内部类的静态方法： 不合法
	 * <p>
	 * 目前仅考虑 1与4
	 * @param str
	 * @return
	 * 赵玉柱
	 */
	public static boolean strIsValidMethod(String str)
	{
		Map<String,Object> buildExecParams = buildExecParams(str, false);
		if (buildExecParams == null)
		{
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public static Object executeMethod(String str, Object... args)
	{
		Map<String,Object> buildExecParams = buildExecParams(str, true);
		Constructor<?> constructor = (Constructor<?>) buildExecParams.get(CONSTRUCTOR);
		Method method = (Method) buildExecParams.get(METHOD);
		List<Class<?>> classArgs = (List<Class<?>>) buildExecParams.get(CONSTRUCTOR_PARAMS);
		List<Class<?>> methodArgs = (List<Class<?>>) buildExecParams.get(METHOD_PARAMS);
		int classArgsSize = classArgs.size();
		int methodArgsSize = methodArgs.size();
		int argsSize = classArgsSize + methodArgsSize;
		if (!(argsSize == 0 && args == null || args.length == 0))
		{
			throw new RuntimeException("参数个数不比配");
		}
		Object[] classParams = new Object[classArgsSize];
		int i = 0;
		for (; i < classArgsSize; i++)
		{
			classParams[i] = args[i];
		}
		Object[] methodParams = new Object[methodArgsSize];
		for (i = 0; i < methodArgsSize; i++)
		{
			methodParams[i] = args[classArgsSize + i];
		}
		if (constructor == null)
		{
			//静态方法
			if (argsSize > 0)
			{
			}
		} else
		{
		}
		return null;
	}

	private static Map<String,Object> buildExecParams(String str, boolean isThrow)
	{
		try
		{
			if (StrUtil.isNoStrTrimNull(str))
			{
				return null;
			}
			boolean noStatic = false;
			String tempStr = str.trim();
			if (!tempStr.startsWith("new "))
			{
				noStatic = true;
			}
			//1、判断当前字符串是否对称
			boolean isSymmetric = StrUtil.symmetricCompare(tempStr);
			if (!isSymmetric)
			{
				return null;
			}
			int p = tempStr.indexOf('(');
			int _p = tempStr.lastIndexOf(')');
			//方法需要右括号结尾
			if (_p != tempStr.length() - 1)
			{
				return null;
			}
			if (p <= 0)
			{
				return null;
			}
			String className = null;
			String methodName = null;
			List<Class<?>> classArgs = new ArrayList<>();
			List<Class<?>> methodArgs = new ArrayList<>();
			if (noStatic)
			{
				className = tempStr.substring(4, p);
				int p1 = tempStr.indexOf(')', p);
				int _p1 = tempStr.indexOf('(', p);
				if (p1 <= _p1 || p1 >= _p || _p1 >= _p)
				{
					return null;
				}
				String classArgsStr = tempStr.substring(p + 1, p1);
				if (classArgsStr != null)
				{
					String[] split = classArgsStr.split(",");
					for (String strArg : split)
					{
						Class<?> forName = Class.forName(strArg);
						classArgs.add(forName);
					}
				}
				String methodArgsStr = tempStr.substring(_p1, tempStr.length() - 2);
				if (methodArgsStr != null)
				{
					String[] split = methodArgsStr.split(",");
					for (String strArg : split)
					{
						Class<?> forName = Class.forName(strArg);
						methodArgs.add(forName);
					}
				}
				methodName = tempStr.substring(p1 + 1, _p1);
			} else
			{
				String classMethod = tempStr.substring(0, p);
				int p2 = classMethod.lastIndexOf('.');
				if (p2 == 0 || p2 == classMethod.length() - 1)
				{
					return null;
				}
				className = classMethod.substring(0, p2);
				methodName = classMethod.substring(p2 + 1);
			}
			Class<?> classForName = Class.forName(className);
			Map<String,Object> execParams = new HashMap<>();
			if (noStatic)
			{
				//构造方法
				Constructor<?> constructor = classForName.getConstructor(classArgs.toArray(new Class[classArgs.size()]));
				execParams.put(CONSTRUCTOR, constructor);
			} else
			{
				execParams.put(CLASS, classForName);
			}
			execParams.put(CONSTRUCTOR_PARAMS, classArgs);
			Method method = classForName.getMethod(methodName, methodArgs.toArray(new Class[methodArgs.size()]));
			execParams.put(METHOD, method);
			execParams.put(METHOD_PARAMS, methodArgs);
			return execParams;
		} catch (Exception e)
		{
			if (isThrow)
			{
				throw new RuntimeException(e);
			}
			return null;
		}
	}
}
