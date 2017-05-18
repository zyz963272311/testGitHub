package test;

import java.util.HashMap;
import java.util.Map;
import util.StrUtil;
/**
 * <p>标题：测试StrUtil类 </p>
 * <p>功能：测试StrUtil类 </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年9月20日 上午11:17:03</p>
 * <p>类全名：test.TestStrUtil</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestStrUtil
{
	public static void main(String[] args)
	{
		/*
		//		Scanner scanner = new Scanner(System.in);
		String strcat[] = { "123", "456", null, "abc", "def" }, link = "-";
		List<Object> list = new ArrayList<Object>(5);
		Object[] a = { new InsertSort(), new MergeSort(), new SelectSort(), new QuickSort(), new BubbleSort() };
		String result1, result2[];
		for (String s : strcat)
		{
			list.add(s);
		}
		System.out.println("======================start======================");
		System.out.println("测试String strcat(java.util.List list, String link)");
		result1 = StrUtil.strcat(list, link);
		System.out.println("返回结果:" + result1);
		System.out.println("测试String[] toStringArray(Object[] a)");
		result2 = StrUtil.toStringArray(a);
		for (String s : result2)
		{
			System.out.println(s);
		}
		System.out.println("测试String strcat(String link, String... a)");
		result1 = StrUtil.strcat(link, strcat[0], strcat[1]);
		System.out.println(result1);
		result1 = StrUtil.strcat(link, strcat[0], strcat[2], strcat[3]);
		System.out.println(result1);
		result1 = StrUtil.strcat(link, strcat[2], strcat[0], strcat[3]);
		System.out.println(result1);
		result1 = StrUtil.strcat(link, strcat[0], strcat[1], strcat[2]);
		System.out.println(result1);
		result1 = StrUtil.strcat(link, strcat[0], strcat[2], strcat[3], strcat[4]);
		System.out.println(result1);
		System.out.println("=======================end=======================");
		*/
		/**
		 * 测试
		 * @see StrUtil.paramsBindToSqlIn
		 */
		Map<String,Object> map = new HashMap<String,Object>();
		for (int i = 0; i < 5; i++)
		{
			map.put("" + i, "" + i + "333");
		}
		System.out.println(StrUtil.paramsBindToSqlIn("str", map, true));
	}
}
