package com.liiwin.utils;

import java.util.Collection;
import java.util.Map;
/**
 * <p>标题： 数据验证工具</p>
 * <p>功能： </p>
 * <p>所属模块： 数据验证工具</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2017年8月17日 下午5:36:36</p>
 * <p>类全名：com.liiwin.utils.Validator</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Validator
{
	public static boolean isNull(Object value)
	{
		boolean isNull = false;
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
		} else if (value instanceof Number)
		{
		}
		return isNull;
	}

	public static boolean isNotNull(Object value)
	{
		return !isNull(value);
	}

	public static void isNotNull(Object value, String name)
	{
		if (StrUtil.isStrTrimNull(name))
		{
			name = "unknown";
		}
		if (isNull(value))
		{
			throw new IllegalArgumentException("参数【" + name + "】不为空");
		}
	}

	public static void isNull(Object value, String name)
	{
		if (StrUtil.isStrTrimNull(name))
		{
			name = "unknown";
		}
		if (isNotNull(value))
		{
			throw new IllegalArgumentException("参数【" + name + "】为空");
		}
	}
}
