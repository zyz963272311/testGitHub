package date;

import java.text.SimpleDateFormat;
/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年9月28日 上午9:25:06</p>
 * <p>类全名：date.Date</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Date
{
	/**
	 * 当仅仅使用dateFmt的情况下，默认获取当前时间"y"对应年,"M"对应月,"d"对应日,"h"对应12时,"H"对应24时,"s"对应秒
	 * @param dateFmt 显示格式
	 * @return 返回格式处理后的时间字符串
	 * 赵玉柱
	 */
	public static String getDate(String dateFmt)
	{
		long l = System.currentTimeMillis();
		java.util.Date date = new java.util.Date(l);
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateFmt);
		return dateFormat.format(date);
	}

	/**
	 * 根据传入的格式字符串与毫秒值，进行计算，返回时间的字符串
	 * @param dateFmt 返回的时间格式 "y"对应年,"M"对应月,"d"对应日,"h"对应12时,"H"对应24时,"s"对应秒
	 * @param l 距离1900年的毫秒值
	 * @return 返回格式处理后的时间字符串
	 * 赵玉柱
	 */
	public static String getDate(String dateFmt, long l)
	{
		java.util.Date date = new java.util.Date(l);
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateFmt);
		return dateFormat.format(date);
	}

	/**
	 * 根据传入的时间格式，将传入的java.util.Date对象进行格式化，返回时间字符串
	 * @param dateFmt 返回的时间格式 "y"对应年,"M"对应月,"d"对应日,"h"对应12时,"H"对应24时,"s"对应秒
	 * @param date 一个时间对象
	 * @return 返回格式处理后的时间字符串
	 * 赵玉柱
	 */
	public static String getDate(String dateFmt, java.util.Date date)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateFmt);
		return dateFormat.format(date);
	}
}
