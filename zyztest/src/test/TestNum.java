package test;

import java.util.Scanner;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年1月22日 下午2:03:53</p>
 * <p>类全名：test.TestNum</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestNum
{
	public static void main(String[] args)
	{
		System.out.println("计算142/50");
		Scanner s = new Scanner(System.in);
		float dest = 0.0f;
		while (true)
		{
			//System.out.println("要计算的数");
			float src = s.nextFloat();
			float all = src * 142 / 50;
			System.out.println("" + all);
			if (dest != 0.0f)
			{
				System.out.println("" + (all - dest));
			}
			dest = all;
		}
	}
}
