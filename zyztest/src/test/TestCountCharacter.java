package test;

import java.util.Scanner;
import str.CountCharacter;
/**
 * <p>标题： 测试字符串字符统计</p>
 * <p>功能： 测试字符串字符统计</p>
 * <p>所属模块： 个人测试使用</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 照样租户</p>
 * <p>创建日期：2016年9月3日 下午4:14:57</p>
 * <p>类全名：test.TestCountCharacter</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestCountCharacter
{
	public static void main(String[] args)
	{
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String str = null;
		while (true)
		{
			System.out.println("===============================================");
			System.out.println("请输入要统计的字符串");
			str = scanner.nextLine();
			//str = "adbs13姿z势12年概~3!a @x # $率 论zs12 szsgss  1234@#￥說說愛き ，。？！%……&*（）——{}【】";
			CountCharacter countCharacter = new CountCharacter();
			countCharacter.count(str);
		}
	}
}
