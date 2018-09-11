package xyz.zyzhu.spring.boot.test;

import java.lang.reflect.Field;
import java.util.List;
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
	private String					f1;
	private String					f2;
	protected static List<Field>	fields;

	public static final <T extends Test> List<Field> getFields(Class<T> t)
	{
		if (fields == null)
		{
		}
		return null;
	}

	public static void main(String[] args)
	{
		String a = null;
		throw new RuntimeException(a.toString());
	}
}
class TestC extends Test
{
	private String	f3;
	private String	f4;
}
