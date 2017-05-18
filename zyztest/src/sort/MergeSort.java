package sort;

/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年8月31日 下午12:28:11</p>
 * <p>类全名：sort.MergeSort</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class MergeSort implements Sort
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
		long end = System.currentTimeMillis();
		return (end - start);
	}
}
