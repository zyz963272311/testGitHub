package test;

import java.util.Scanner;
import util.StrUtil;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年2月6日 下午4:50:33</p>
 * <p>类全名：test.TestLowerUpper</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestLowerUpper
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入要转换的字符串");
		while (true)
		{
			String strSrc = scanner.nextLine();
			if (StrUtil.isNoStrTrimNull(strSrc))
			{
				System.out.println("源字符串：\t" + strSrc);
				System.out.println("大写：\t" + strSrc.toUpperCase());
				System.out.println("小写：\t" + strSrc.toLowerCase());
			}
		}
	}
}
