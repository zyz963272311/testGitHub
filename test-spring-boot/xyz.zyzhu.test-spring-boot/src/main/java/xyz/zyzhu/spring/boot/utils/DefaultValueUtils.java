package xyz.zyzhu.spring.boot.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liiwin.date.DateUtil;
import com.liiwin.utils.StrUtil;

import xyz.zyzhu.spring.boot.annotation.DefaultValue;
/**
 * <p>标题： 默认值工具类</p>
 * <p>功能： </p>
 * <p>所属模块： 默认值工具类</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年7月30日 下午5:25:26</p>
 * <p>类全名：xyz.zyzhu.spring.boot.utils.DefaultValueUtils</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class DefaultValueUtils
{
	/**
	 * 根据class获取默认值，需要clazz具有默认的构造函数
	 * @param clazz
	 * @param recursion 递归参数列表
	 * @return
	 * 赵玉柱
	 */
	public static <E extends Object> E buildDefaultValueByClass(Class<E> clazz, Class<?> superClass,boolean recursion)
	{
		if (clazz == null)
		{
			return null;
		}
		E instance = null;
		try {
			instance = clazz.newInstance();
			buildDefaultValue(instance,superClass, recursion);
		} catch (InstantiationException e1) {
			throw new RuntimeException("报错内容",e1);
		} catch (IllegalAccessException e1) {
			throw new RuntimeException("报错内容",e1);
		}
		
		return instance;
	}
	/**
	 * 
	 * @param o
	 * @param recursion
	 * 赵玉柱
	 */
	public static void buildDefaultValue(Object o,Class<?> superClass,boolean recursion)
	{
		if(o == null)
		{
			return;
		}
		Class<?> clazz = o.getClass();
		Field[] fields = clazz.getDeclaredFields();
		if(fields == null||fields.length == 0)
		{
			return ;
		}
		Map<String, Method> getterMethods = ObjectUtils.getClassGetterMethods(clazz);
		Map<String, Method> setterMethods = ObjectUtils.getClassSetterMethods(clazz);
		for(Field field:fields)
		{
			Class<?> fieldClazz = field.getClass();
			boolean simpleType = ObjectUtils.isSimpleType(fieldClazz);
			try {
			Method getterMethod = getterMethods.get(field.getName());
			Method setterMethod = setterMethods.get(field.getName());
			if(simpleType)
			{
				Object invoke = getterMethod.invoke(o);
				if(invoke==null)
				{
					DefaultValue annotation = field.getAnnotation(DefaultValue.class);
					if(annotation!=null)
					{
						String defaultValueAnno = annotation.defaultValue();
						if(StrUtil.isNoStrTrimNull(defaultValueAnno))
						{
							Object value = null;
							if(Integer.class.equals(fieldClazz)||int.class.equals(fieldClazz))
							{
								value = StrUtil.obj2int(defaultValueAnno);
							}
							if(long.class.equals(fieldClazz)||Long.class.equals(fieldClazz))
							{
								value = StrUtil.obj2long(defaultValueAnno);
							}
							if(short.class.equals(fieldClazz)||Short.class.equals(fieldClazz))
							{
								value = (short)StrUtil.obj2int(defaultValueAnno);
							}
							if(byte.class.equals(fieldClazz)||Byte.class.equals(fieldClazz))
							{
								value = (byte) StrUtil.obj2int(defaultValueAnno);
							}
							if(char.class.equals(fieldClazz)||Character.class.equals(fieldClazz))
							{
								value = defaultValueAnno.trim().charAt(0);
							}
							if(Boolean.class.equals(fieldClazz)||boolean.class.equals(fieldClazz))
							{
								value = StrUtil.obj2bool(defaultValueAnno);
							}
							if(float.class.equals(fieldClazz)||Float.class.equals(fieldClazz))
							{
								value = (float)StrUtil.obj2double(defaultValueAnno);
							}
							if(double.class.equals(fieldClazz)||Double.class.equals(fieldClazz))
							{
								value = StrUtil.obj2double(defaultValueAnno);
							}
							if(Date.class.equals(fieldClazz))
							{
								value = DateUtil.formateDate(defaultValueAnno);
							}
							if(String.class.equals(fieldClazz))
							{
								value = defaultValueAnno;
							}
						}
					}
				}
			}
			else if(superClass!=null&&superClass.isAssignableFrom(fieldClazz))
			{
				Object value  = null;
				value =getterMethod.invoke(o);
				if(value == null)
				{
					value = buildDefaultValueByClass(fieldClazz,superClass, recursion);
				}
				else
				{
					buildDefaultValue(value, superClass,recursion);
				}
				setterMethod.invoke(o, value);
			}
			} catch (IllegalAccessException e) {
				throw new RuntimeException("报错内容",e);
			} catch (IllegalArgumentException e) {
				throw new RuntimeException("报错内容",e);
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("报错内容",e);
			}
		}
	}
}
