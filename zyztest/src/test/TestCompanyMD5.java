package test;

/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年11月29日 上午11:43:02</p>
 * <p>类全名：test.TestCompanyMD5</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestCompanyMD5
{
	public static void main(String[] args)
	{
		String abc = "000";
		a(abc);
		System.out.println(abc);
	}

	static void a(String a)
	{
		a += "321";
	}
}