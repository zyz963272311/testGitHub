package com.liiwin.constant;

/**
 * <p>标题： 正则表达式常量类</p>
 * <p>功能： </p>
 * <p>所属模块： rootbas</p>
 * <p>版权： Copyright © 2018 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2018年8月2日 上午9:28:41</p>
 * <p>类全名：com.liiwin.constant.RegExpConstant</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class RegExpConstant
{
	/**全数字*/
	public static final String	ALL_NUMBER			= "^[0-9]*$";
	/**至少输入n位数字*/
	public static final String	N_NUMBERS_AT_LEAST	= "^\\d{n}$";
	/**m到n位全数字*/
	public static final String	M_TO_N_NUMBERS		= "^\\d{m,n}$";
	/**正整数*/
	public static final String	POSITIVE_INTEGER	= "^\\+?[1-9][0-9]*$";
	/**负整数*/
	public static final String	NEGTIVE_INTEGER		= "^\\-[1-9][]0-9*$";
	/**只能输入26位英文字母，包括大小写*/
	public static final String	ALL_EN_CHAR			= "^[A-Za-z]+$";
	/**只能输入26位小写英文*/
	public static final String	ALL_EN_LOWER_CHAR	= "^[a-z]+$";
	/**只能输入26位大写英文*/
	public static final String	ALL_EN_UPPERER_CHAR	= "^[A-Z]+$";
	/**只能输入26位英文字母，包括大小写和数字*/
	public static final String	ALL_EN_AND_NUM_CHAR	= "^[A-Za-z0-9]+$";
	/**只能输入java定义类型的字符，包括字母，数字，下划线*/
	public static final String	ALL_JAVA_CHAR		= "^\\w+$";
	/**密码验证，全英文5到17位*/
	public static final String	PWD_5_17_EN_CHAR	= "^[a-zA-Z]\\w{5,17}$";
	/**用户名*/
	public static final String	USER_NAME			= "^[A-Za-z0-9_\\-\u4e00-\u9fa5]+$";
	/**验证是否含有^%&',;=?$\"等字符非法*/
	public static final String	INVALID_CHAR		= "[^%&',;=?$\\x22]+";
	/**所有字符均为中文汉字*/
	public static final String	ALL_CHN_CHAR		= "^[\u4e00-\u9fa5]{0,}$";
	/**验证邮箱格式*/
	public static final String	EMAIL				= "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
	/**验证HTTP或HTTPS地址*/
	public static final String	HTTP_URL			= "^http[s]{0,1}://%28[/\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$";
	/**验证电话号码，正确格式为*/
	public static final String	PHONE				= "^(\\(\\d{3,4}-)|\\d{3.4}-)?\\d{7,8}$";
	/**身份证验证*/
	public static final String	ID_CARD				= "^\\d{17}[\\d|x]|\\d{15}$";
	/**匹配QQ号，目前qq号最长为11位*/
	public static final String	QQ					= "^[1-9]([0-9]{5,11})$";
	/**邮编*/
	public static final String	POST_CODE			= "^\\d{6}$";
	/**IPV4地址*/
	public static final String	IPV4_ADDRESS		= "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
}
