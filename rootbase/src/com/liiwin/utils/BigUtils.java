package com.liiwin.utils;

import java.math.BigDecimal;
/**
 * <p>标题： BigDecimal工具类</p>
 * <p>功能： </p>
 * <p>所属模块： rootbas</p>
 * <p>版权： Copyright © 2018 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2018年7月31日 上午9:29:38</p>
 * <p>类全名：com.liiwin.utils.BigUtils</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class BigUtils
{
	public final static BigDecimal	TEN		= BigDecimal.TEN;
	public final static BigDecimal	ZERO	= BigDecimal.ZERO;
	public final static BigDecimal	ONE		= BigDecimal.ONE;

	public static BigDecimal obj2big(Object obj)
	{
		return obj2big(obj, ZERO);
	}

	public static BigDecimal obj2big(Object obj, BigDecimal defaultValue)
	{
		try
		{
			return new BigDecimal(StrUtil.obj2str(obj));
		} catch (Exception e)
		{
			return defaultValue;
		}
	}
}
