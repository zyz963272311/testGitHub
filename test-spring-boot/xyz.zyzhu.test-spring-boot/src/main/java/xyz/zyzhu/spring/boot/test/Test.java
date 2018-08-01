package xyz.zyzhu.spring.boot.test;

import java.text.ParseException;
import com.liiwin.code.MakeCodeUtil;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2017年11月27日 下午8:24:07</p>
 * <p>类全名：xyz.zyzhu.spring.boot.test.Test</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Test
{
	public static void main(String[] args) throws ParseException
	{
		System.out.println(int.class);
	}

	public static String getName()
	{
		return "name" + 33321;
	}

	public String getCode()
	{
		return "code" + MakeCodeUtil.makeOuttercode("", 5, "code");
	}

	private static Class getObjClass(Object o)
	{
		if (o != null)
		{
			return o.getClass();
		}
		return null;
	}

	/**
	 * 
	 * 赵玉柱
	 */
	private static void constTest()
	{
		String content = "1221";
		String[] constellationArr = { "魔羯座", "水瓶座", "双鱼座", "牡羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座" };
		int[] constellationEdgeDay = { 20, 18, 20, 20, 20, 21, 22, 22, 22, 22, 21, 21 };
		String[] substring = StrUtil.getSubstring(content, new int[] { 0, 2 }, new int[] { 2, 4 });
		//月份
		int month = StrUtil.obj2int(substring[0]);
		//日
		int day = StrUtil.obj2int(substring[1]);
		String constellation = null;
		if (day >= constellationEdgeDay[month - 1])
		{
			constellation = constellationArr[month == 12 ? 0 : month];
		} else
		{
			constellation = constellationArr[month - 1];
		}
		System.out.println(constellation);
	}
}
