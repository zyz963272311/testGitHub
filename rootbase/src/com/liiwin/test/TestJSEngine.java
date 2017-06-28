package com.liiwin.test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
/**
 * <p>标题： 测试java调用JS引擎</p>
 * <p>功能： </p>
 * <p>所属模块： rootbase-test</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年6月28日 下午12:13:03</p>
 * <p>类全名：com.liiwin.test.TestJSEngine</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestJSEngine
{
	public static void main(String[] args)
	{
		ScriptEngineManager engineManager = new ScriptEngineManager();
		ScriptEngine engine = engineManager.getEngineByExtension("js");
		String js = "a=3+4*5-6/8;\ncom.liiwin.test.TestJSEngine.testA(a+2);";
		try
		{
			System.out.println(engine.eval(js));
		} catch (ScriptException e)
		{
			throw new RuntimeException("报错内容", e);
		}
	}

	public static String testA(String a)
	{
		return a;
	}
}
