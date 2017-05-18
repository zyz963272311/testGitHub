package date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
/**
 * <p>标题： 时间工具类</p>
 * <p>功能： </p>
 * <p>所属模块： 时间工具类</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年4月5日 下午8:34:36</p>
 * <p>类全名：date.DateUtil</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class DateUtil
{
	public final static String	DATA_FORMATE_1	= "yyyy-MM-dd hh:mm:ss";
	public final static String	DATA_FORMATE_2	= "yyyy-MM-dd hh:mm";
	public final static String	DATA_FORMATE_3	= "yyyy-MM-dd hh:mm:ss.S";
	public final static String	DATA_FORMATE_4	= "yyyy-MM-dd hh:mm:ss.S";
	public final static String	DATA_FORMATE_5	= "yyyy-MM-dd HH:mm:ss";
	public final static String	DATA_FORMATE_6	= "yyyy-MM-dd HH:mm";
	public final static String	DATA_FORMATE_7	= "yyyy-MM-dd HH:mm:ss.S";
	public final static String	DATA_FORMATE_8	= "yyyy-MM-dd HH:mm:ss.S";
	public final static String	DATA_FORMATE_9	= "yyyy-MM-dd";

	/**
	 * 获取某一个时间的所在周的周一，默认认为周一是第一天
	 * @param date
	 * @return
	 * 赵玉柱
	 */
	public static Date getCurWeekFirstDay(Date date)
	{
		return getCurWeekOneDay(date, Calendar.MONDAY);
	}

	/**
	 * 获取给定时间的当前周的最后一天
	 * @param date
	 * @return
	 * 赵玉柱
	 */
	public static Date getCurWeekLastDay(Date date)
	{
		return getCurWeekOneDay(date, Calendar.SUNDAY);
	}

	/**
	 * 获取当前时间所在周的周一
	 * @return
	 * 赵玉柱
	 */
	public static Date getCurWeekFirstDay()
	{
		return getCurWeekFirstDay(new Date());
	}

	/**
	 * 获取当前时间所在周的最后一天
	 * @return
	 * 赵玉柱
	 */
	public static Date getCurLastDay()
	{
		return getCurWeekLastDay(new Date());
	}

	/**
	 * 获取某个时间的当前周的某一天
	 * @param date
	 * @param day
	 * @return
	 * 赵玉柱
	 */
	public static Date getCurWeekOneDay(Date date, int day)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.set(Calendar.DAY_OF_WEEK, day);
		return cal.getTime();
	}

	/**
	 * 获取某一时间当前周的上一周的第一天
	 * @param date
	 * @return
	 * 赵玉柱
	 */
	public static Date getLastWeekFirstDay(Date date)
	{
		return getLastWeekOneDay(date, Calendar.MONDAY);
	}

	/**
	 * 获取当前时间的上一周的最后一天
	 * @param date
	 * @return
	 * 赵玉柱
	 */
	public static Date getLastWeekLastDay(Date date)
	{
		return getLastWeekOneDay(date, Calendar.SUNDAY);
	}

	/**
	 * 获取当前时间的上一周的第一天
	 * @return
	 * 赵玉柱
	 */
	public static Date getLastWeekFirstDay()
	{
		return getLastWeekFirstDay(new Date());
	}

	public static Date getLastWeekLastDay()
	{
		return getLastWeekLastDay(new Date());
	}

	/**
	 * 获取某一时间的上一周的某一天
	 * @param date
	 * @param day
	 * @return
	 * 赵玉柱
	 */
	public static Date getLastWeekOneDay(Date date, int day)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.add(Calendar.WEEK_OF_YEAR, -1);
		cal.set(Calendar.DAY_OF_WEEK, day);
		return cal.getTime();
	}

	/**
	 * 将时间格式话成字符串
	 * @param formateDate
	 * @return
	 * 赵玉柱
	 */
	public static String formateDate(String formateDate)
	{
		Date date = new Date();
		return formateDate(date, formateDate);
	}

	/**
	 * 将时间格式化成字符串
	 * @param date
	 * @param formate
	 * @return
	 * 赵玉柱
	 */
	public static String formateDate(Date date, String formate)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat(formate);
		return dateFormat.format(date);
	}

	/**
	 * 将字符串转化成时间格式
	 * @param dateText
	 * @param formate
	 * @return
	 * 赵玉柱
	 */
	public static Date text2Date(String dateText, String formate)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat(formate);
		try
		{
			return dateFormat.parse(dateText);
		} catch (ParseException e)
		{
			throw new RuntimeException("报错内容", e);
		}
	}

	/**
	 * 获取当前时区
	 * @return
	 * 赵玉柱
	 */
	public static String getLocatTimeZone()
	{
		Calendar cal = Calendar.getInstance();
		TimeZone timeZone = cal.getTimeZone();
		return timeZone.getDisplayName();
	}

	/**
	 * 获取某个时间的时区
	 * @param date
	 * @return
	 * 赵玉柱
	 */
	public static String getLocatTimeZone(Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		TimeZone timeZone = cal.getTimeZone();
		return timeZone.getDisplayName();
	}

	public static void main(String[] args)
	{
		Date date = getLastWeekFirstDay(new Date());
		System.out.println(formateDate(date, DATA_FORMATE_9));
		System.out.println(formateDate(DATA_FORMATE_7));
		System.out.println(text2Date(formateDate(DATA_FORMATE_7), DATA_FORMATE_7));
		System.out.println(getLocatTimeZone(new Date()));
	}
}
