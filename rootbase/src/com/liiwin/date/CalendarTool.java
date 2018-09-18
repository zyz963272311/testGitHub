package com.liiwin.date;

import java.util.Calendar;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * <p>标题：日历工具，显示日历 </p>
 * <p>功能：日历工具，显示日历 </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年9月28日 下午2:09:13</p>
 * <p>类全名：date.CalendarTool</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class CalendarTool
{
	private static Logger logger = LoggerFactory.getLogger(CalendarTool.class);

	public static void getDate(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		int[][] array = new int[6][7];
		for (int i = 0; i < dayInMonth; i++)
		{
			calendar.set(Calendar.DAY_OF_MONTH, i + 1);
			int weekInMonth = calendar.get(Calendar.WEEK_OF_MONTH);
			int nowDayInMonth = calendar.get(Calendar.DAY_OF_WEEK);
			array[weekInMonth - 1][nowDayInMonth - 1] = i + 1;
		}
		String[] weeks = { "日", "一", "二", "三", "四", "五", "六" };
		for (String w : weeks)
		{
			logger.error(w + "\t");
		}
		System.err.println();
		for (int i = 0; i < array.length; i++)
		{
			for (int j = 0; j < array[i].length; j++)
			{
				if (array[i][j] != 0)
				{
					logger.error("" + array[i][j]);
				}
				System.err.print("\t");
				if ((j + 1) % 7 == 0)
				{
					System.err.println();
				}
			}
		}
	}
}
