package sort;

/**
 * <p>标题：冒泡排序 </p>
 * <p>功能：实现冒泡排序 </p>
 * <p>所属模块： 个人使用</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年8月30日 下午1:39:27</p>
 * <p>类全名：sort.BubbleSort</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class BubbleSort implements Sort
{
	@Override
	public long sort(int[] o)
	{
		if (o == null || o.length == 0)
		{
			return -1;
		}
		long start = System.currentTimeMillis();
		for (int i = 0; i < o.length - 1; i++)
		{
			for (int j = 0; j < o.length - i - 1; j++)
			{
				if (o[j] > o[j + 1])
				{//调换顺序
					o[j] = o[j + 1] - o[j];
					o[j + 1] = o[j + 1] - o[j];
					o[j] = o[j + 1] + o[j];
				}
			}
		}
		long end = System.currentTimeMillis();
		return (end - start);
	}
}
