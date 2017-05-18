package num.random;

import java.util.HashSet;
import java.util.Iterator;
/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年9月9日 下午4:08:44</p>
 * <p>类全名：num.random.RandomSet</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class RandomSet implements Random
{
	java.util.Random	random	= new java.util.Random();

	@Override
	public long random(int[] o)
	{
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		HashSet<Integer> set = new HashSet<Integer>();
		randomSet(o.length, o.length, 0, set);
		System.out.println("set长度" + set.size());
		System.out.println("o的长度" + o.length);
		Iterator<Integer> iterator = set.iterator();
		int i = 0;
		while (iterator.hasNext())
		{
			o[i] = iterator.next();
			i++;
		}
		long end = System.currentTimeMillis();
		return end - start;
	}

	/** 
	 * 随机指定范围内N个不重复的数 
	 * 利用HashSet的特征，只能存放不同的值 
	 * @param min 指定范围最小值 
	 * @param max 指定范围最大值 
	 * @param n 随机数个数 
	 * @param HashSet<Integer> set 随机数结果集 
	 */
	void randomSet(int n, int length, int k, HashSet<Integer> set)
	{
		for (int i = 0; i < n; i++)
		{
			int num = random.nextInt(length);
			System.out.println(num);
			set.add(num);
		}
		System.out.println("第" + k + "次循环,本次添加" + n + "个");
		int setSize = set.size();
		if (setSize < length)
		{
			randomSet(length - setSize, length, ++k, set);
		}
	}
}
