package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import date.CalendarTool;
/**
 * <p>标题：测试日历工具 </p>
 * <p>功能：测试日历工具 </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年9月28日 下午2:28:37</p>
 * <p>类全名：test.TestCalendarTool</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestCalendarTool
{
	/**
	 * @param args
	 * x250-2
	 * @throws ParseException 
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) throws ParseException
	{
		Scanner scanner;
		SimpleDateFormat dateFormate = new SimpleDateFormat("yyyy-MM");
		while (true)
		{
			System.out.println("=========================START========================");
			System.out.println("请输入年月，形式为yyyy-MM，如2016-10");
			scanner = new Scanner(System.in);
			String dateValue = scanner.next();
			Date date = dateFormate.parse(dateValue);
			System.err.println("输年月为" + dateFormate.format(date));
			CalendarTool.getDate(date);
			try
			{
				Thread.sleep(200);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				throw new RuntimeException("报错内容", e);
			}
			System.out.println("\r\n\r\n==========================END=========================");
		}
	}
}
