package xyz.zyzhu.spring.boot.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.Table;
import com.liiwin.annotation.FieldDef;
import com.liiwin.utils.ArrayUtil;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： 对象工具类</p>
 * <p>功能： </p>
 * <p>所属模块： boot</p>
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
	public static Map<String,Method> getClassGetterMethods(Class<?> clazz, Class<?> superClassType)
	{
		if (clazz == null)
		{
			return null;
		}
		if (superClassType == null)
		{
			superClassType = Object.class;
		}
		String name = clazz.getName();
		if (classGetterMethodCache.containsKey(name))
		{
			return classGetterMethodCache.get(name);
		}
		Map<String,Method> getMethodMap = new ConcurrentHashMap<>();
		Class<?> superclass = clazz.getSuperclass();
		Map<String,Method> supperGetterMethods = null;
		if (superclass != null && superClassType.isAssignableFrom(superclass))
		{
			supperGetterMethods = getClassGetterMethods(superclass, superClassType);
		}
		if (supperGetterMethods == null)
		{
			supperGetterMethods = new ConcurrentHashMap<>();
		}
		//将父类中所有gett方法补入
		for (Entry<String,Method> msdEntry : supperGetterMethods.entrySet())
		{
			String fldName = msdEntry.getKey();
			Method getMethod = msdEntry.getValue();
			try
			{
				if (getMethod != null)
				{
					getMethod = StrUtil.dealGetMethodByName(fldName, clazz);
				}
			} catch (Exception e)
			{
			}
			getMethodMap.put(fldName, getMethod);
		}
		//将当前类所有的get方法补入
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields)
		{
			String fieldName = field.getName();
			boolean isStatic = Modifier.isStatic(field.getModifiers());
			boolean isFinal = Modifier.isFinal(field.getModifiers());
			if (!isFinal && !isStatic)
			{
				try
				{
					Method getMethod = StrUtil.dealGetMethod(field, clazz);
					getMethodMap.put(fieldName, getMethod);
				} catch (Exception e)
				{
				}
			}
		}
		classGetterMethodCache.put(name, getMethodMap);
		return getMethodMap;
	}

	/**
	 * 获取当前类的set方法
	 * @param clazz
	 * @return
	 * 赵玉柱
	 */
	public static Map<String,Method> getClassSetterMethods(Class<?> clazz, Class<?> superClassType)
	{
		if (clazz == null)
		{
			return null;
		}
		if (superClassType == null)
		{
			superClassType = Object.class;
		}
		String name = clazz.getName();
		if (classSetterMethodCache.containsKey(name))
		{
			return classSetterMethodCache.get(name);
		}
		Map<String,Method> setMethodMap = new ConcurrentHashMap<>();
		Class<?> superclass = clazz.getSuperclass();
		Map<String,Method> supperSetterMethods = null;
		if (superclass != null && superClassType.isAssignableFrom(superclass))
		{
			supperSetterMethods = getClassSetterMethods(superclass, superClassType);
		}
		if (supperSetterMethods == null)
		{
			supperSetterMethods = new ConcurrentHashMap<>();
		}
		//将父类中所有sett方法补入
		for (Entry<String,Method> msdEntry : supperSetterMethods.entrySet())
		{
			String fldName = msdEntry.getKey();
			Method setMethod = msdEntry.getValue();
			try
			{
				if (setMethod != null)
				{
					try
					{
						setMethod = StrUtil.dealSetMethodByName(fldName, clazz, setMethod.getParameterTypes()[0]);
						setMethodMap.put(fldName, setMethod);
					} catch (Exception e)
					{
					}
				}
			} catch (Exception e)
			{
			}
		}
		//将当前类所有的sett方法补入
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields)
		{
			String fieldName = field.getName();
			boolean isStatic = Modifier.isStatic(field.getModifiers());
			boolean isFinal = Modifier.isFinal(field.getModifiers());
			if (!isFinal && !isStatic)
			{
				Method setMethod = null;
				try
				{
					setMethod = StrUtil.dealSetMethod(field, clazz);
					setMethodMap.put(fieldName, setMethod);
				} catch (Exception e)
				{
				}
			}
		}
		classSetterMethodCache.put(name, setMethodMap);
		return setMethodMap;
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
		try
		{
			Map<String,Object> buildExecParams = buildExecParams(str, true);
			Constructor<?> constructor = (Constructor<?>) buildExecParams.get(CONSTRUCTOR);
			Method method = (Method) buildExecParams.get(METHOD);
			List<Class<?>> classArgs = (List<Class<?>>) buildExecParams.get(CONSTRUCTOR_PARAMS);
			List<Class<?>> methodArgs = (List<Class<?>>) buildExecParams.get(METHOD_PARAMS);
			int classArgsSize = classArgs.size();
			int methodArgsSize = methodArgs.size();
			Object[] classParams = new Object[classArgsSize];
			int i = 0;
			for (; i < classArgsSize; i++)
			{
				Object arg = null;
				if (args != null && args.length > i)
				{
					arg = args[i];
				}
				classParams[i] = arg;
			}
			Object[] methodParams = new Object[methodArgsSize];
			for (i = 0; i < methodArgsSize; i++)
			{
				Object arg = null;
				if (args != null && args.length > i + classArgsSize)
				{
					arg = args[i + classArgsSize];
				}
				methodParams[i] = arg;
			}
			Object result = null;
			Object instance = null;
			if (constructor != null)
			{
				instance = constructor.newInstance(classParams);
			}
			result = method.invoke(instance, methodParams);
			return result;
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * 对象值校验
	 * @param obj
	 * 赵玉柱
	 */
	@SuppressWarnings("unchecked")
	public static void objValueCheck(Object obj)
	{
		if (obj == null)
		{
			return;
		}
		Class<?> clazz = obj.getClass();
		if (Map.class.isAssignableFrom(clazz))
		{
			Map<Object,Object> valueMap = (Map<Object,Object>) obj;
			for (Entry<Object,Object> valueEntry : valueMap.entrySet())
			{
				objValueCheck(valueEntry.getValue());
			}
			//map
		} else if (Collection.class.isAssignableFrom(clazz))
		{
			//集合
			Collection<Object> valueColl = (Collection<Object>) obj;
			for (Object coll : valueColl)
			{
				objValueCheck(coll);
			}
		}
		List<Field> fields = getClassFields(clazz, 2);
		Map<String,Method> getterMethods = getClassGetterMethods(clazz, Object.class);
		if (fields != null && !fields.isEmpty() && getterMethods != null && !getterMethods.isEmpty())
		{
			for (Field field : fields)
			{
				FieldDef fldAnno = field.getDeclaredAnnotation(FieldDef.class);
				Method getMethod = getterMethods.get(field.getName());
				if (getMethod != null)
				{
					String name = null;
					Object value = null;
					try
					{
						Class<?> fldClazz = field.getType();
						boolean isSimpleType = isSimpleType(fldClazz);
						boolean notNull = false;
						boolean notEmpty = false;
						boolean notBlank = false;
						String[] valueIn = null;
						String[] valueNotIn = null;
						if (isSimpleType)
						{
							//简单数据类型
							if (fldAnno != null)
							{
								notNull = fldAnno.notNull();
								notEmpty = fldAnno.notEmpty();
								notBlank = fldAnno.notBlank();
								valueIn = fldAnno.valueIn();
								valueNotIn = fldAnno.valueNotIn();
								String regEx = fldAnno.regEx();
								name = fldAnno.name();
								if (StrUtil.isStrTrimNull(name))
								{
									name = field.getName();
								}
								if (getMethod != null)
								{
									value = getMethod.invoke(obj);
								}
								if (notNull)
								{
									if (value == null)
									{
										throw new RuntimeException("字段不可为空");
									}
								}
								if (notEmpty)
								{
									if (value == null)
									{
										throw new RuntimeException("字段不可为空");
									}
									if (value instanceof String)
									{
										if (StrUtil.isNull((String) value))
										{
											throw new RuntimeException("字段不可为空");
										}
									}
									if (Map.class.isAssignableFrom(fldClazz))
									{
										Map<Object,Object> map = (Map<Object,Object>) value;
										if (map.isEmpty())
										{
											throw new RuntimeException("字段不可为空");
										}
									}
									if (Collection.class.isAssignableFrom(fldClazz))
									{
										Collection<Object> coll = (Collection<Object>) value;
										if (coll.isEmpty())
										{
											throw new RuntimeException("字段不可为空");
										}
									}
								}
								if (notBlank)
								{
									if (value == null)
									{
										throw new RuntimeException("字段不可为空");
									}
									if (value instanceof String)
									{
										if (StrUtil.isStrTrimNull((String) value))
										{
											throw new RuntimeException("字段不可为空");
										}
									}
								}
								if (valueIn != null && valueIn.length > 0)
								{
									String strValue = StrUtil.obj2str(value);
									if (!StrUtil.isStrIn(strValue, valueIn))
									{
										Set<String> set = ArrayUtil.toSet(valueIn);
										throw new RuntimeException("字段值需要在" + set + "中，当前值为【" + strValue + "】");
									}
								}
								if (valueNotIn != null && valueNotIn.length > 0)
								{
									String strValue = StrUtil.obj2str(value);
									if (StrUtil.isStrIn(strValue, valueNotIn))
									{
										Set<String> set = ArrayUtil.toSet(valueNotIn);
										throw new RuntimeException("字段值不可在" + set + "中，当前值为【" + strValue + "】");
									}
								}
								if (StrUtil.isNoStrTrimNull(regEx) && value != null && String.class.isAssignableFrom(fldClazz))
								{
									String strValue = StrUtil.obj2str(value);
									Pattern pattern = Pattern.compile(regEx);
									Matcher matcher = pattern.matcher(strValue);
									boolean matches = matcher.matches();
									if (!matches)
									{
										throw new RuntimeException("字段不匹配" + strValue);
									}
								}
							}
						} else
						{
							if (fldAnno != null)
							{
								notNull = fldAnno.notNull();
								notEmpty = fldAnno.notEmpty();
								notBlank = fldAnno.notBlank();
								valueIn = fldAnno.valueIn();
								valueNotIn = fldAnno.valueNotIn();
								name = fldAnno.name();
								if (StrUtil.isStrTrimNull(name))
								{
									name = field.getName();
								}
							}
							if (getMethod != null)
							{
								value = getMethod.invoke(obj);
							}
							//非简单数据类型
							if (Map.class.isAssignableFrom(fldClazz))
							{
								//map
								Map<Object,Object> valueMap = (Map<Object,Object>) value;
								if (notNull)
								{
									if (valueMap == null)
									{
										throw new RuntimeException("字段不可为空");
									}
								}
								if (notEmpty || notBlank)
								{
									if (valueMap == null || valueMap.isEmpty())
									{
										throw new RuntimeException("字段不可为空");
									}
								}
								if (valueMap != null)
								{
									for (Entry<Object,Object> valueEntry : valueMap.entrySet())
									{
										Object entValue = valueEntry.getValue();
										//进行递归校验
										objValueCheck(entValue);
									}
								}
							} else if (Collection.class.isAssignableFrom(fldClazz))
							{
								//集合
								Collection<Object> valueColl = (Collection<Object>) value;
								if (notNull)
								{
									if (valueColl == null)
									{
										throw new RuntimeException("字段不可为空");
									}
								}
								if (notEmpty || notBlank)
								{
									if (valueColl == null || valueColl.isEmpty())
									{
										throw new RuntimeException("字段不可为空");
									}
								}
								if (valueColl != null)
								{
									for (Object coll : valueColl)
									{
										//进行递归校验
										objValueCheck(coll);
									}
								}
							} else
							{
								//递归处理子节点
								if (notNull || notEmpty || notBlank)
								{
									if (value == null)
									{
										throw new RuntimeException("字段不可为空");
									}
								}
								if (value != null)
								{
									objValueCheck(value);
								}
							}
						}
					} catch (Exception e)
					{
						throw new RuntimeException("字段【" + name + "】校验失败", e);
					}
				}
			}
		}
	}

	/**
	 * 获取类内部的变量
	 * @param clazz
	 * @param option 0:当前类定义的;1:当前类定义的public;2:获取父类
	 * @return
	 * 赵玉柱
	 */
	public static List<Field> getClassFields(Class<?> clazz, int option)
	{
		List<Field> fieldList = new ArrayList<>();
		if (clazz == null)
		{
			return fieldList;
		}
		Field[] fields = clazz.getDeclaredFields();
		if (fields != null && fields.length > 0)
		{
			for (Field field : fields)
			{
				boolean isPublic = Modifier.isPublic(field.getModifiers());
				if ((option & 1) > 0 && !isPublic)
				{
					continue;
				}
				fieldList.add(field);
			}
		}
		if ((option & 2) > 0)
		{
			List<Field> classFields = getClassFields(clazz.getSuperclass(), option);
			fieldList.addAll(classFields);
		}
		return fieldList;
	}

	public static Object newInstance(Class<?> clazz, Object... args)
			throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		if (clazz == null)
		{
			return null;
		}
		Class<?>[] classArray = getClassArray(args);
		if (Modifier.isAbstract(clazz.getModifiers()) || Modifier.isInterface(clazz.getModifiers()))
		{
			if (Map.class.isAssignableFrom(clazz))
			{
				return new HashMap<>();
			}
			if (List.class.isAssignableFrom(clazz))
			{
				return new ArrayList<>();
			}
			if (Set.class.isAssignableFrom(clazz))
			{
				return new HashSet<>();
			}
		} else
		{
			return clazz.getConstructor(classArray).newInstance(args);
		}
		return null;
	}

	public static Class<?>[] getClassArray(Object... objects)
	{
		if (objects == null || objects.length == 0)
		{
			return null;
		}
		Class<?>[] classs = new Class[objects.length];
		for (int i = 0; i < objects.length; i++)
		{
			Object obj = objects[i];
			if (obj != null)
			{
				classs[i] = obj.getClass();
			} else
			{
				classs = null;
			}
		}
		return classs;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static int compareObject(Object o1, Object o2)
	{
		if (o1 == o2)
		{
			return 0;
		}
		if (o1 == null)
		{
			return -1;
		}
		if (o2 == null)
		{
			return 1;
		}
		if (!o1.getClass().equals(o2.getClass()))
		{
			throw new RuntimeException("不同类型不可进行比较");
		}
		if (o1.getClass().isAssignableFrom(Comparable.class))
		{
			return ((Comparable) o1).compareTo(o2);
		}
		throw new RuntimeException("无法比较量对象o1[" + o1 + "[,o2[" + o2 + "]");
	}

	/**
	 * 将value转换成type类型的value
	 * @param value
	 * @param type
	 * @return
	 * 赵玉柱
	 */
	public static Object getValueByType(Object value, Class<?> type)
	{
		if (value == null || type == null)
		{
			return null;
		}
		return null;
	}

	/**
	 * 构造执行参数
	 * @param str
	 * @param isThrow 是否抛出异常
	 * @return
	 * 赵玉柱
	 */
	private static Map<String,Object> buildExecParams(String str, boolean isThrow)
	{
		try
		{
			if (StrUtil.isStrTrimNull(str))
			{
				return null;
			}
			boolean noStatic = false;
			String tempStr = str.trim();
			if (tempStr.startsWith("new "))
			{
				noStatic = true;
			}
			//1、判断当前字符串是否对称
			Map<Character,Map<Integer,Integer>> symmetricIndex = StrUtil.getSymmetricIndex(tempStr);
			boolean isSymmetric = symmetricIndex != null;
			if (!isSymmetric)
			{
				return null;
			}
			int p = tempStr.indexOf('(');
			int _p = symmetricIndex.get('(').get(p);
			//方法需要右括号结尾
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
				int p1 = tempStr.indexOf('(', _p);
				int _p1 = symmetricIndex.get('(').get(p1);
				String classArgsStr = tempStr.substring(p + 1, _p);
				if (StrUtil.isNoStrTrimNull(classArgsStr))
				{
					String[] split = classArgsStr.split(",");
					for (String strArg : split)
					{
						Class<?> forName = Class.forName(strArg);
						classArgs.add(forName);
					}
				}
				String methodArgsStr = tempStr.substring(p1 + 1, _p1);
				if (StrUtil.isNoStrTrimNull(methodArgsStr))
				{
					String[] split = methodArgsStr.split(",");
					for (String strArg : split)
					{
						Class<?> forName = Class.forName(strArg);
						methodArgs.add(forName);
					}
				}
				methodName = tempStr.substring(_p + 2, p1);
			} else
			{
				String classMethod = tempStr.substring(0, p);
				int dotIdx = classMethod.lastIndexOf('.');
				if (dotIdx <= 0)
				{
					throw new RuntimeException("方法错误");
				}
				className = tempStr.substring(0, dotIdx);
				methodName = classMethod.substring(dotIdx + 1);
				String methodArgsStr = tempStr.substring(p + 1, _p);
				if (StrUtil.isNoStrTrimNull(methodArgsStr))
				{
					String[] split = methodArgsStr.split(",");
					for (String strArg : split)
					{
						Class<?> forName = Class.forName(strArg);
						methodArgs.add(forName);
					}
				}
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
