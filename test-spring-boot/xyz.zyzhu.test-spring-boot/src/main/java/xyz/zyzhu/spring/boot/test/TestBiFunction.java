package xyz.zyzhu.spring.boot.test;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年10月10日 下午3:19:52</p>
 * <p>类全名：xyz.zyzhu.spring.boot.test.TestBiFunction</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestBiFunction<A extends Object,B extends Object>
{
	public String test(A a, B b)
	{
		String c = "a:=" + a + "b:=" + b;
		System.out.println(c);
		return c;
	}
}
