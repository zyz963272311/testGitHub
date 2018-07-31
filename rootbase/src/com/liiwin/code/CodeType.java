package com.liiwin.code;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年5月26日 下午2:54:42</p>
 * <p>类全名：com.liiwin.code.CodeType</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class CodeType
{
	/**一个随机数字，说是随机数字，实际上是一个当前时间对应当天的毫秒数，正常的时间毫秒数应该是8位，超过的部分将自动生成一个随机数*/
	public static int	RANDOM_NUMBER	= 1;
	/**一个时间格式*/
	public static int	TIME			= 2;
	/**按照给定长度的字符串，直接扔上去*/
	public static int	FIX_LENGTH		= 4;
	//自增长
	public static int	AUTO_INCR		= 8;
}
