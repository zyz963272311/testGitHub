package com.liiwin.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
/**
 * <p>标题： 测试反射 用TYPE</p>
 * <p>功能： </p>
 * <p>所属模块： 测试反射</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年7月13日 下午9:43:57</p>
 * <p>类全名：com.liiwin.reflect.Test</p>
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
		Set<String> set = new HashSet<>();
		TestA<Number,BigDecimal> testA = new TestA<>();
		set.getClass();
		Type types = testA.getClass();
		System.out.println(types);
		Type[] actualTypeArguments = ((ParameterizedType) types).getActualTypeArguments();
		Type type = actualTypeArguments[0];
		System.out.println(type);
	}
}
