package test;

import java.util.Scanner;
import num.random.Random;
import num.random.RandomArray;
import num.random.RandomDoubleFor;
import num.random.RandomSet;
/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年9月9日 下午3:46:08</p>
 * <p>类全名：test.TestRandom</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestRandom
{
	@SuppressWarnings("resource")
	public static void main(String[] args)
	{
		Random random;
		int length;
		long time;
		int[] o;
		Scanner scanner;
		String[] randomStr = { "双重for循环", "set特性判断", "替换下标方式" };
		int r;
		while (true)
		{
			scanner = new Scanner(System.in);
			length = 0;
			o = null;
			System.out.println("=================start================");
			System.out.println("本类将产生不重复的随机数");
			System.out.println("请根据菜单项列出想要使用的方式");
			for (int i = 1; i <= randomStr.length; i++)
			{
				System.out.println("方式名称:\t" + randomStr[i - 1] + "\t序号:\t" + i);
			}
			r = scanner.nextInt();
			switch (r)
			{
			case 1:
				random = new RandomDoubleFor();
				System.out.println("您选择的是" + randomStr[r - 1]);
				break;
			case 2:
				random = new RandomSet();
				System.out.println("您选择的是" + randomStr[r - 1]);
				break;
			case 3:
				random = new RandomArray();
				System.out.println("您选择的是" + randomStr[r - 1]);
				break;
			default:
				System.out.println("您选择的方式不在菜单中，请重新开始");
				continue;
			}
			System.out.println("请输入要产生的数组的个数，请输入大于零的数字");
			length = scanner.nextInt();
			o = new int[length];
			time = random.random(o);
			for (int i = 0; i < o.length; i++)
			{
				System.out.println(i + "\t\t" + o[i]);
			}
			System.out.println("运行时间为:\t" + time + "毫秒");
			System.out.println("=================end==================\r\n\r\n");
		}
	}
}
