package test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * <p>标题：Validator </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年10月22日 下午5:34:27</p>
 * <p>类全名：test.Test</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Test
{
	public static void main1(String[] args)
	{
		//		short s = Short.MAX_VALUE;
		//		s += 1;
		//		System.out.println(s);
		int times = 10000000;
		String str = null;
		StringBuffer sbr = new StringBuffer();
		StringBuilder sbl = new StringBuilder();
		long start = System.currentTimeMillis();
		int _time = times / 100;
		for (int i = 0; i < _time; i++)
		{
			str += "a";
		}
		System.out.println("String方式消耗时间为:" + (System.currentTimeMillis() - start));
		start = System.currentTimeMillis();
		for (int i = 0; i < times; i++)
		{
			sbr.append("a");
		}
		System.out.println("Buffer方式消耗时间为:" + (System.currentTimeMillis() - start));
		start = System.currentTimeMillis();
		for (int i = 0; i < times; i++)
		{
			sbl.append("a");
		}
		System.out.println("Builder方式消耗时间为:" + (System.currentTimeMillis() - start));
	}

	public static void main(String[] args)
	{
		BigDecimal big = new BigDecimal("sdfghj");
		System.out.println(big);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
		Date date = new Date();
		System.out.println(sdf.format(date));
		/**
		 * OGOWAYBILLADVANCE
		 * OGNOTARIALINFO
		 */
	}
}
