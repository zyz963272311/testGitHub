package test;

import annotation.Test1;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年4月15日 下午9:32:34</p>
 * <p>类全名：test.TestAnno</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestAnno
{
	@Test1(name = "aaaaa")
	private String	aaa;
	private String	bbb;

	public String getAaa()
	{
		return aaa;
	}

	public void setAaa(String aaa)
	{
		//setValue("aaa", aaa);
		this.aaa = aaa;
	}

	public String getBbb()
	{
		return bbb;
	}

	public void setBbb(String bbb)
	{
		//setValue("bbb", bbb);
		this.bbb = bbb;
	}
}
