package util;

import java.util.Collection;
import java.util.Map;
/**
 * <p>标题：参数判断工具类 </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年11月9日 下午2:42:14</p>
 * <p>类全名：util.Validator</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Validator
{
	/**
	 * 检查参数名称为name的参数值value是否为空，若为空，则抛出异常
	 * @param value 参数值
	 * @param name 参数名
	 * @author 赵玉柱
	 */
	public static void isNotNull(Object value, String name)
	{
		boolean isNull = false;
		if (StrUtil.isStrTrimNull(name))
		{
			name = "unknown";
		}
		if (value == null)
		{
			isNull = true;
		} else if (value instanceof String)
		{
			isNull = ((String) value).trim().length() == 0;
		} else if (value instanceof Map)
		{
			isNull = ((Map) value).size() == 0;
		} else if (value instanceof Collection)
		{
			isNull = ((Collection) value).size() == 0;
		} else if (value instanceof Object[])
		{
			isNull = ((Object[]) value).length == 0;
		}
		if (isNull)
		{
			throw new IllegalArgumentException("参数" + name + "为空");
		}
	}

	/**
	 * 一次判断多个变得量是否为空，且仅能判断map中的key在names中的值
	 * @param params map值 如{["a",123],["b","xxx"],["c","孩子"],["d","java"]}
	 * @param names 要判断的key 如"a,b,c"
	 * <br>这种情况下仅能判断a,b,c对应的key值，不能判断params.get("d")的值是否为空
	 * @author 赵玉柱
	 */
	public static void isNotNull(Map<String,Object> params, String names)
	{
		if (StrUtil.isStrTrimNull(names))
		{
			throw new IllegalArgumentException("参数 names 为空");
		}
		if (params == null)
		{
			throw new IllegalArgumentException("参数" + names + "为空");
		}
		String[] nameArray = names.split(",");
		for (int i = 0; i < params.size(); i++)
		{
			isNotNull(params.get(nameArray[i]), nameArray[i]);
		}
	}

	public static void isNotNull(Object[] valueArray, String names)
	{
		if (StrUtil.isStrTrimNull(names))
		{
			throw new IllegalArgumentException("参数 names 为空");
		}
		if (valueArray == null || valueArray.length == 0)
		{
			throw new IllegalArgumentException("参数 valueArray 为空");
		}
		String[] nameArray = names.split(",");
		if ((valueArray.length - nameArray.length) != 0)
		{
			throw new IllegalArgumentException("参数 valueArray 与 参数 names 的个数不匹配");
		}
		for (int i = 0; i < nameArray.length; i++)
		{
			isNotNull(valueArray[i], nameArray[i]);
		}
	}
}
