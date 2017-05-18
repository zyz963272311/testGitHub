package _db;

import annotation.Test2;
import annotation.Test3;
import annotation.Test4;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年4月15日 下午10:20:51</p>
 * <p>类全名：annotation.TestAnno</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Test2(names = { @Test3(count = 3, fields = { "str1", "str2" }), @Test3(count = 3, fields = { "str3", "str2" }) })
public class TestAnno
{
	@Test4(name = "str1111")
	private String	str1;
	//@Test4(name = "str22222")
	private String	str2;
	@Test4(name = "str33333")
	private String	str3;

	public String getStr1()
	{
		return str1;
	}

	public void setStr1(String str1)
	{
		//setValue("str1", str1);
		this.str1 = str1;
	}

	public String getStr2()
	{
		return str2;
	}

	public void setStr2(String str2)
	{
		//setValue("str2", str2);
		this.str2 = str2;
	}

	public String getStr3()
	{
		return str3;
	}

	public void setStr3(String str3)
	{
		//setValue("str3", str3);
		this.str3 = str3;
	}
}
