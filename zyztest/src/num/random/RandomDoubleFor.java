package num.random;

/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年9月9日 下午3:35:29</p>
 * <p>类全名：num.random.RandomDoubleFor</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class RandomDoubleFor implements Random
{
	@Override
	public long random(int[] o)
	{
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		java.util.Random random = new java.util.Random();
		for (int i = 0; i < o.length; i++)
		{
			o[i] = random.nextInt(o.length);
			for (int j = 0; j < i; j++)
			{
				if (o[j] == o[i])
				{
					i--;
					break;
				}
			}
		}
		long end = System.currentTimeMillis();
		return end - start;
	}
}
