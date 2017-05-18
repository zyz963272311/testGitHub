package test;

import java.util.Random;
import java.util.Scanner;
import str.FirstToLast;
/**
 * <p>标题：测试str.First2Last </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年8月30日 上午10:24:39</p>
 * <p>类全名：test.TextFirst2Last</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TextFirst2Last
{
	/**
	 * @param args
	 * x250-2
	 */
	public static void main(String[] args)
	{
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		int size = 0;
		Object[] o;
		Random random = new Random();
		while (true)
		{
			size = 0;
			o = null;
			System.out.println("请输入想要调换的数组个数");
			try
			{
				size = scanner.nextInt();
				o = new Object[size];
			} catch (Exception e)
			{
				System.out.println("输入个数错误!请输入数字");
			}
			System.out.println("正在生成随机数组");
			try
			{
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
				throw new RuntimeException("报错内容", e);
			}
			for (int i = 0; i < size; i++)
			{
				o[i] = random.nextInt(size);
			}
			System.out.println("========================分割线========================\n原Object数组");
			for (Object typeO : o)
			{
				System.out.println(typeO);
			}
			FirstToLast.first2last(o);
			System.out.println("========================分割线========================\n现Object数组");
			for (Object typeO : o)
			{
				System.out.println(typeO);
			}
		}
	}
}
