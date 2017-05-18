package test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年12月5日 上午10:49:26</p>
 * <p>类全名：test.TestException</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestException
{
	@SuppressWarnings("resource")
	public static void main(String[] args)
	{
		Map<Character,Integer> map = new HashMap<Character,Integer>();
		System.out.println("请输入字符串");
		Scanner scanner = new Scanner(System.in);
		String resource = scanner.nextLine();
		char[] chs = resource.toCharArray();
		for (char c : chs)
		{
			if (map.containsKey(new Character(c)))
			{
				map.put(c, map.get(new Character(c)) + 1);
			} else
			{
				map.put(new Character(c), 1);
			}
		}
		System.out.println(map);
	}
}
