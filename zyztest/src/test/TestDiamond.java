package test;

import java.util.Scanner;
/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年10月9日 上午9:48:44</p>
 * <p>类全名：test.TestDiamond</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestDiamond
{
	static final String	TAG	= "TestDiamond";

	/**
	 * @param args
	 * x250-2
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args)
	{
		Scanner scanner;
		String[] method = { "单边行数", "对角线行数" };
		int msd = 0;
		int lineCount = 0;
		err: while (true)
		{
			scanner = new Scanner(System.in);
			System.out.println("==========================START====================");
			System.out.println("请根据菜单序号选择对应方式");
			for (int i = 0; i < method.length; i++)
			{
				System.out.println("序号：\t" + i + "\t方式：\t" + method[i]);
			}
			msd = scanner.nextInt();
			switch (msd)
			{
			case 0:
				System.out.println("请输入行数");
				lineCount = scanner.nextInt();
				break;
			case 1:
				System.out.println("请输入行数");
				lineCount = scanner.nextInt();
				lineCount = (lineCount) / 2 + 1;
				break;
			default:
				System.err.println("您输入的序号有错误，请按照序号重新输入");
				continue err;
			}
			diamond(lineCount, '*');
			System.out.println("===========================END=====================");
		}
	}

	/**
	 * 输入形状的实现
	 * @param lineCount 单边行数
	 * @param shape 形状，为了以后扩展
	 * x250-2
	 */
	static void diamond(int lineCount, char shape)
	{
		lineCount = lineCount * 2 - 1;
		char i[][] = new char[lineCount][lineCount];
		for (int b = 0; b < lineCount; b++)
		{
			for (int c = 0; c < lineCount; c++)
			{
				if (b < lineCount / 2 + 1)
				{
					if (b + c == lineCount / 2 || c - b == lineCount / 2)
					{
						i[b][c] = shape;
					} else
					{
						i[b][c] = ' ';
					}
				}
				if (b >= lineCount / 2)
				{
					if (b + c == lineCount - 1 + lineCount / 2 || b - c == lineCount / 2)
					{
						i[b][c] = shape;
					} else
					{
						i[b][c] = ' ';
					}
				}
			}
		}
		for (int b = 0; b < lineCount; b++)
		{
			for (int c = 0; c < lineCount; c++)
			{
				System.out.print(i[b][c]);
			}
			System.out.println();
		}
	}
}
