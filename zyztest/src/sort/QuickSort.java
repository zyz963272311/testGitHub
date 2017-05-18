package sort;

/**
 * <p>标题：快速排序 </p>
 * <p>功能：实现快速排序 </p>
 * <p>所属模块： 个人使用</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年8月30日 下午1:40:35</p>
 * <p>类全名：sort.QuickSort</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class QuickSort implements Sort, StringSort
{
	@Override
	public long sort(int[] o)
	{
		// TODO Auto-generated method stub
		if (o == null || o.length == 0)
		{
			return -1;
		}
		long start = System.currentTimeMillis();
		quick_sort(o, 0, o.length - 1);
		long end = System.currentTimeMillis();
		return (end - start);
	}

	//快速排序
	void quick_sort(int s[], int l, int r)
	{
		if (l < r)
		{
			//Swap(s[l], s[(l + r) / 2]); //将中间的这个数和第一个数交换 参见注1
			int i = l, j = r, x = s[l];
			while (i < j)
			{
				while (i < j && s[j] >= x)
				{
					j--;
				}
				if (i < j)
				{
					s[i++] = s[j];
				}
				while (i < j && s[i] < x)
				{
					i++;
				}
				if (i < j)
				{
					s[j--] = s[i];
				}
			}
			s[i] = x;
			quick_sort(s, l, i - 1); // 递归调用 
			quick_sort(s, i + 1, r);
		}
	}

	@Override
	public long sort(String[] o)
	{
		// TODO Auto-generated method stub
		if (o == null || o.length == 0)
		{
			return -1;
		}
		long start = System.currentTimeMillis();
		quick_sort(o, 0, o.length - 1);
		long end = System.currentTimeMillis();
		return (end - start);
	}

	//快速排序
	void quick_sort(String s[], int l, int r)
	{
		if (l < r)
		{
			//Swap(s[l], s[(l + r) / 2]); //将中间的这个数和第一个数交换 参见注1
			int i = l, j = r;
			String X = s[l];
			while (i < j)
			{
				while (i < j && s[j].compareTo(X) > 0)
				{
					j--;
				}
				if (i < j)
				{
					s[i++] = s[j];
				}
				while (i < j && s[i].compareTo(X) < 0)
				{
					i++;
				}
				if (i < j)
				{
					s[j--] = s[i];
				}
			}
			s[i] = X;
			quick_sort(s, l, i - 1); // 递归调用 
			quick_sort(s, i + 1, r);
		}
	}
}
