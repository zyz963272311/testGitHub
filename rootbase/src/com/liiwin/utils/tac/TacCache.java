package com.liiwin.utils.tac;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： Tac缓存</p>
 * <p>功能： </p>
 * <p>所属模块： rootbas</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2017年11月18日 下午7:08:47</p>
 * <p>类全名：com.liiwin.utils.TacCache</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TacCache
{
	private static TacCache	cache;
	private Set<Method>		publicMethods;
	private Set<Field>		publicFields;
	private Properties		tacProperties;

	private TacCache()
	{
		init();
	}

	/**
	 * 初始化
	 * 
	 * 赵玉柱
	 */
	private void init()
	{
		Class<Tac> c = Tac.class;
		Method[] pubMethodArray = c.getMethods();
		publicMethods = new HashSet<>();
		publicFields = new HashSet<>();
		for (Method method : pubMethodArray)
		{
			publicMethods.add(method);
		}
		Field[] pubFieldArray = c.getFields();
		for (Field field : pubFieldArray)
		{
			publicFields.add(field);
		}
		tacProperties = new Properties();
		try
		{
			tacProperties.load(this.getClass().getResourceAsStream("/com/liiwin/utils/tac/tac.properties"));
		} catch (IOException e)
		{
			throw new RuntimeException("报错内容", e);
		}
	}

	public synchronized static TacCache getInstance()
	{
		if (cache == null)
		{
			cache = new TacCache();
		}
		return cache;
	}

	public Set<Method> getPublicMethods()
	{
		return publicMethods;
	}

	public Set<Field> getPublicFields()
	{
		return publicFields;
	}

	public String getNo_execPropertie()
	{
		return getStrPropertie("no_exec", "");
	}

	public String getWith_endPropertie()
	{
		return getStrPropertie("with_end", "");
	}

	public String getComplex_grammarPropertie()
	{
		return getStrPropertie("complex_grammar", "");
	}

	public String getMust_prePropertie()
	{
		return getStrPropertie("must_pre", "");
	}

	public String getNo_splitPropertie()
	{
		return getStrPropertie("complex_grammar", "");
	}

	public Properties getProperties()
	{
		return tacProperties;
	}

	public Object getPropertie(String name)
	{
		return tacProperties.get(name);
	}

	public String getStrPropertie(String name, String defaut)
	{
		return StrUtil.obj2str(getPropertie(name), defaut);
	}

	public String getStrPropertie(String name)
	{
		return getStrPropertie(name, null);
	}

	public static void main(String[] args)
	{
		TacCache cache = TacCache.getInstance();
	}
}
