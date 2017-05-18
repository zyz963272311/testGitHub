package test;

import java.util.Random;
import java.util.Scanner;
import sort.BubbleSort;
import sort.InsertSort;
import sort.MergeSort;
import sort.QuickSort;
import sort.SelectSort;
import sort.Sort;
/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年8月31日 下午3:09:00</p>
 * <p>类全名：test.TextSort</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TextSort
{
	/**
	 * @param args
	 * x250-2
	 */
	public static void main(String[] args)
	{
		String[] sortStr = { "冒泡排序", "选择排序", "插入排序", "归并排序", "快速排序" };
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		int i;
		int j;
		Sort sort;
		Random random = new Random();
		long time;
		int[] o;
		while (true)
		{
			System.out.println("请输入排序算法对应的序号");
			for (int l = 0; l < sortStr.length; l++)
			{
				System.out.println("算法名称=\t" + sortStr[l] + "\t序号=\t" + (l + 1));
			}
			sort = null;
			i = scanner.nextInt();
			o = null;
			switch (i)
			{
			case 1:
				sort = new BubbleSort();
				break;
			case 2:
				sort = new SelectSort();
				break;
			case 3:
				sort = new InsertSort();
				break;
			case 4:
				sort = new MergeSort();
				break;
			case 5:
				sort = new QuickSort();
				break;
			default:
				System.out.println("您输入的编号不在此区间内");
				break;
			}
			if (sort != null)
			{
				long std = System.currentTimeMillis();
				System.out.println("您选择了\t" + sortStr[i - 1]);
				System.out.println("请输入数组大小");
				j = scanner.nextInt();
				System.out.println("正在生成数组............");
				System.out.println("原数组为:");
				o = new int[j];
				for (int k = 0; k < j; k++)
				//for (int k = o.length - 100000 >= 0 ? o.length - 100000 : 0; k < o.length; k++)
				{
					o[k] = random.nextInt(j);
				}
				for (int k = o.length - 1000 >= 0 ? o.length - 1000 : 0; k < o.length; k++)
				{
					System.out.println(k + "\t" + o[k]);
				}
				long end = System.currentTimeMillis();
				System.out.println("排序中，请稍后......");
				time = sort.sort(o);
				System.out.println("排序后为:");
				//for (int k = 0; k < o.length; k++)
				for (int k = o.length - 1000 >= 0 ? o.length - 1000 : 0; k < o.length; k++)
				{
					System.out.println(k + "\t" + o[k]);
				}
				System.out.println("排序方式为:\t" + sortStr[i - 1] + "\t数据量:\t" + o.length + "\t生成数据时间:" + (end - std) + "毫秒\t排序时间为:\t" + time + "毫秒");
				System.out.println("\r\n\r\n\r\n");
			}
		}
	}
}
