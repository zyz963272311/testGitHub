package xyz.zyzhu.spring.boot.test;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年10月10日 下午2:12:05</p>
 * <p>类全名：xyz.zyzhu.spring.boot.test.TestImpl</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestImpl implements TestInterface1, TestInterface2
{
	@Override
	public void test()
	{
		System.out.println("333");
		TestInterface1.super.test();
	}
}
