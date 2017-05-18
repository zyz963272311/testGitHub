package test;

import date.Date;
/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年9月28日 上午9:54:41</p>
 * <p>类全名：test.TestDate</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 * <br><a href="http://www.java123.net/327322.html" alt="http://www.java123.net/327322.html">说明</a>：
 * <br>G为公元的标志，一般情况下仅仅显示公元二字
 * <br>yy对应两位的年,yyyy对应4位的年,Y与y效果相同
 * <br>M为月份,MM为无论什么情况下都是2位数字的月份,如9月=》09,10月=》10,MMMM对应的为大写的月份,如9月=》九月,10月=》十月
 * <br>d为日,d对应的每月中的第几天设置最小位数为d或D的个数，不设上限,d从1开始,dd从01开始,ddd从001开始,以此类推;D对应一年中第几天，D从1开始,DD从01开始,DDD从001开始，依次类推
 * <br>E为星期，位数为定影的星期显示最小位数
 * <br>w为一年中的周数，
 * <br>W为一个月中的周数
 * <br>F为月份中的星期
 * <br>h为时,h对应12小时制度,<span style="color:red">0时=》12</span>,如8时=》8,<span style="color:red">12时=》12</span>,13时=》1;H对应24小时制度,<span style="color:red">0时=》0</span>,时=》8,<span style="color:red">12时=》12</span>,13时=》13,最小位数为h或H的位数。
 * <br>K为am/pm中的小时数,0-11
 * <br>k一天中的小时数,1-24
 * <br>h为am/pm中的小时数,1-12
 * <br>H为一天中的小时数，0-23
 * <br>m为分，规律与月份M相同
 * <br>s为分，S为毫秒数,其他规律与月份M相同
 * <br>a为上午下午的中文
 * <br>z,zz,zzz为时区的简写,zzzz为时区的中文全称
 * <br>Z,ZZ,ZZZ与z,zz,zzz相同,ZZZZ为与美国标准时区的差距
 */
public class TestDate
{
	/**
	 * @param args
	 * x250-2
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args)
	{
		String[] dateFmts = { "yyyy-MM-dd hh:mm:ss", "yy-MM-dd hh:mm:ss", "yyyy/MM/dd hh:mm:ss", "yyyy-MM-dd HH:mm:ss", "yyyy-MMMM-dddd hhhh:mmmm:sss", "y/M/d h:m:s", "yyyy/MM/DD HH:mm:SS",
				"yyyy-MM-dd hh:MM:ss", "yyyy-mm-dd hh:MM:ss", "yy-MM-dd a hh:mm:ss z", "yy-MM-dd aa hh:mm:ss zz", "yy-MM-dd a hh:mm:ss zzzz", "EE yy-MM-dd a hh:mm:ss zzzz",
				"EEE yy-MM-dd a hh:mm:ss zzzz", "k:mm", "GG", "ZZ", "ZZZ", "ZZZZ", "zz", "zzz", "zzzz", "K" };
		System.out.println("=========================获取当前时间开始===================");
		for (String dateFmt : dateFmts)
		{
			System.out.println(dateFmt + "\t" + Date.getDate(dateFmt));
		}
		System.out.println("=========================获取当前时间结束===================");
		System.out.println("\r\n\r\n");
		System.out.println("========================获取自定义时间开始===================");
		java.util.Date date = new java.util.Date();
		date.setYear(2016 - 1900);
		date.setMonth(9);
		date.setDate(8);
		date.setHours(12);
		date.setMinutes(45);
		date.setSeconds(33);
		for (String dateFmt : dateFmts)
		{
			System.out.println(dateFmt + "\t" + Date.getDate(dateFmt, date));
		}
		System.out.println("========================获取自定义时间结束===================");
		System.out.println("\r\n\r\n");
		System.out.println("========================获取long时间开始===================");
		long l = date.getTime();
		for (String dateFmt : dateFmts)
		{
			System.out.println(dateFmt + "\t" + Date.getDate(dateFmt, l));
		}
		System.out.println("========================获取自定义时间结束===================");
	}
}
