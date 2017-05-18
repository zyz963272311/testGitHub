package test;

import java.util.Scanner;
import num.translate.Ten2Any;
/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年9月6日 上午11:27:26</p>
 * <p>类全名：test.TestTen2Any</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestTen2Any
{
	/**
	 * @param args
	 * x250-2
	 */
	public static void main(String[] args)
	{
		long oldNum, transNum;
		String newNum;
		Ten2Any ten2Any;
		try
		{
			while (true)
			{
				@SuppressWarnings("resource")
				Scanner scanner = new Scanner(System.in);
				ten2Any = null;
				System.out.println("=============start==============");
				System.out.println("请输入想要转换的数字(此数应不大于9223372036854775807)");
				oldNum = scanner.nextLong();
				System.out.println("请输入想要转换的进制(此数应该不大于38)");
				transNum = scanner.nextLong();
				ten2Any = new Ten2Any(oldNum, transNum);
				newNum = ten2Any.operator();
				System.out.println("");
				System.out.println("想要转换的数是:\t" + oldNum);
				System.out.println("想要转换的进制:\t" + transNum);
				System.out.println("转换后的进制数:\t" + newNum);
				System.out.println("==============end==============\n\n");
			}
		} catch (Exception e)
		{
			System.out.println("出错了:" + e.getMessage());
		}
	}
}
