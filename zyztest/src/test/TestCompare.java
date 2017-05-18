package test;

import util.BasePackingUtil;
import util.Compare;
/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年10月25日 上午10:42:12</p>
 * <p>类全名：test.TestCompare</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestCompare
{
	/**
	 * @param args
	 * x250-2
	 */
	public static void main(String[] args)
	{
		Compare compare = new Compare();
		char[] a = { 'a', 'b', 'c', 'd', 'z' };
		Character[] A = BasePackingUtil.base2Packing(a);
		try
		{
			System.out.println(compare.getMax(A));
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
