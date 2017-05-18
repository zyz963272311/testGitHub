package num.random;

/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年9月9日 下午5:48:28</p>
 * <p>类全名：num.random.RandomArray</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class RandomArray implements Random
{
	java.util.Random	random	= new java.util.Random();

	@Override
	public long random(int[] o)
	{
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		int length = o.length;
		o = randomArray(length, length);
		long end = System.currentTimeMillis();
		return end - start;
	}

	int[] randomArray(int length, int n)
	{
		int[] source = new int[n];
		for (int i = 0; i < source.length; i++)
		{
			source[i] = i;
		}
		int[] result = new int[n];
		int index = 0;
		for (int i = 0; i < result.length; i++)
		{
			index = random.nextInt(length);
			result[i] = source[index];
			source[index] = source[length - 1];
		}
		return result;
	}
}
