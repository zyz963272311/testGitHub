package com.liiwin.sort;

/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年8月31日 下午12:27:49</p>
 * <p>类全名：sort.InsertSort</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class InsertSort implements Sort
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
		int i, j;
		int n = o.length;
		int target;
		for (i = 0; i < n; i++)
		{
			j = i;
			target = o[i];
			while (j > 0 && target < o[j - 1])
			{
				o[j] = o[j - 1];
				j--;
			}
			o[j] = target;
		}
		long end = System.currentTimeMillis();
		return (end - start);
	}
}
