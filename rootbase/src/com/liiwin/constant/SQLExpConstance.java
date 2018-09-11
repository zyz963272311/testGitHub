package com.liiwin.constant;

/**
 * <p>标题： sql表达式常量类</p>
 * <p>功能：此类中的所有常量用于后期的sql进行数据库无差别使用，用通配方式代表一个查询类型 </p>
 * <p>所属模块： rootbas</p>
 * <p>版权： Copyright © 2018 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2018年9月11日 下午10:52:19</p>
 * <p>类全名：com.liiwin.constant.SQLExpConstance</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public interface SQLExpConstance
{
	//bitand 按位与 mysql  a&b  oracle bitabd(a,b) 
	//使用时候位 BITAND3(col) 转换mysql为 col|3 转换oracle 为 bitand(col,3)
	public static final String	BIT_AND			= "%BIT_AND";
	//bitand 按位或  mysql a|b   oracle (a+b-bitand(a,b))
	public static final String	BIT_OR			= "%BIT_OR";
	//bitand 按位异或  mysql a^b  oracle BIT_OR-BIT_AND =(a+b-bitand(a,b)-bitand(a,b))= (a+b-bitand(a,b)*2)
	public static final String	BIT_XOR			= "%BIT_XOR";
	//当前时间的xxx天之前 
	//例如 %CURDATEP30 被解析为当前时间向前退30天
	public static final String	CUR_DATE_PRE	= "%CURDATEP";
	//当前时间向的xxx天之后
	//例如%CURDATEA30 被解析位当前时间向后推30天
	public static final String	CUR_DATE_AFT	= "%CURDATEA";
	//当前时间
	public static final String	CUR_DATE		= "%CURDATE";
	//当前年
	public static final String	CUR_YEAR		= "%CURYEAR";
	//当前月
	public static final String	CUR_MONTH		= "%CURMONTH";
	//当前日
	public static final String	CUR_DAY			= "%CURDAY";
	//当前时
	public static final String	CUR_HOUR		= "%CURHOUR";
	//当前分
	public static final String	CUR_MINUTE		= "%CURMINUTE";
	//当前秒
	public static final String	CUR_SECOND		= "%CURSECOND";
}
