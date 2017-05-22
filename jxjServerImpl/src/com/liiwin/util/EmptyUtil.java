package com.liiwin.util;

import java.util.List;
import java.util.Map;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年5月17日 上午11:01:49</p>
 * <p>类全名：util.EmptyUtil</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class EmptyUtil
{
	/**
	 * 判断一个Object类型数据是否为空，为空返回true
	 * @param value
	 * @return
	 * 赵玉柱
	 */
	public static boolean isEmpty(Object value)
	{
		if (value == null)
		{
			return true;
		}
		if (value instanceof String)
		{
			return StrUtil.isStrTrimNull((String) value);
		}
		if (value instanceof Number)
		{
			try
			{
				((Number) value).doubleValue();
				return false;
			} catch (Exception e)
			{
				return true;
			}
		}
		if (value instanceof Character)
		{
			try
			{
				((Character) value).charValue();
				return false;
			} catch (Exception e)
			{
				return true;
			}
		}
		if (value instanceof Map)
		{
			if (((Map) value).isEmpty())
			{
				return true;
			}
			return false;
		}
		if (value instanceof List)
		{
			if (((List) value).isEmpty())
			{
				return true;
			}
			return false;
		}
		if (value instanceof Object[])
		{
			if (((Object[]) value).length == 0)
			{
				return true;
			}
			return false;
		}
		return false;
	}

	/**
	 * 判断一个对象是否是非空的，非空返回true；
	 * @param value
	 * @return
	 * 赵玉柱
	 */
	public static boolean isNotEmpty(Object value)
	{
		return !isEmpty(value);
	}
}
