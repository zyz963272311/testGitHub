package sort;

/**
 * <p>标题：选择排序 </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年8月30日 下午1:41:44</p>
 * <p>类全名：sort.SelectSort</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class SelectSort implements Sort
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
		for (int i = 0; i < o.length; i++)
		{
			int min = o[i];
			int temp;
			int index = i;
			for (int j = i + 1; j < o.length; j++)
			{
				if (o[j] < min)
				{
					min = o[j];
					index = j;
				}
			}
			temp = o[i];
			o[i] = min;
			o[index] = temp;
		}
		long end = System.currentTimeMillis();
		return (end - start);
	}
}
