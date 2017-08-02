package com.liiwin.code;

import com.liiwin.utils.RedisUtil;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： 一个创建内码与外码的工具类</p>
 * <p>功能： </p>
 * <p>所属模块： rootbase</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2017年8月1日 下午7:17:02</p>
 * <p>类全名：com.liiwin.code.MakeCodeUtil</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class MakeCodeUtil
{
	private static final int	DEFAULT_LENGTH	= 10;

	public static String makeOuttercode(String prefix, int codeLength, String key)
	{
		if (StrUtil.isNoStrTrimNull(prefix))
		{
		}
		String result = makeCode(prefix, codeLength, key);
		return result;
	}

	private static String makeCode(String prefix, int codeLength, String key)
	{
		if (StrUtil.isStrTrimNull(key))
		{
			throw new RuntimeException("获取码值不可为空");
		}
		int setLock = RedisUtil.setLock(key, 10, RedisUtil.MINUTE);
		String result = StrUtil.obj2str(setLock);
		if (codeLength <= 0)
		{
			codeLength = DEFAULT_LENGTH;
		}
		if (result.length() < codeLength)
		{
			result = StrUtil.strcat(prefix, result, codeLength, '0');
		}
		return StrUtil.obj2str(result);
	}

	public static void main(String[] args)
	{
		System.out.println(makeOuttercode("zyzhu", 0, "dsadsadsadsds"));
	}
}
