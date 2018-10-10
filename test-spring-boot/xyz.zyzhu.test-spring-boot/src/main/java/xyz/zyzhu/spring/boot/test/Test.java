package xyz.zyzhu.spring.boot.test;

import java.util.function.BiFunction;
import java.util.function.Consumer;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2017年11月27日 下午8:24:07</p>
 * <p>类全名：xyz.zyzhu.spring.boot.test.Test</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Test
{
	public static void main(String[] args)
	{
		TestBiFunction<Object,Object> f = new TestBiFunction<>();
		Object a = "a";
		Object b = "b";
	}

	/**
	 * @param a
	 * @param b
	 * @param f
	 * 赵玉柱
	 */
	/**
	 * 
	 * 赵玉柱
	 */
	protected static void testConsurm()
	{
		TestConsumer consumer = new TestConsumer();
		set(consumer, c -> {
			consumer.test();
		});
	}

	private static Object t1(Object a, Object b, BiFunction<Object,Object,Object> f)
	{
		Object apply = f.apply(a, b);
		return apply;
	}

	/**
	 * 
	 * 赵玉柱
	 */
	private static <T extends Object> void set(T t, Consumer<T> c)
	{
		System.out.println(111);
		c.accept(t);
	}
}
class TestC extends Test
{
	private String	f3;
	private String	f4;
}
