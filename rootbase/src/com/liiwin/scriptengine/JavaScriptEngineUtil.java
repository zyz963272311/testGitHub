package com.liiwin.scriptengine;

import java.io.File;
import java.io.Reader;
import javax.script.Bindings;
import javax.script.ScriptEngine;
/**
 * <p>标题： JavaScript引擎</p>
 * <p>功能： </p>
 * <p>所属模块： rootbase</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2017年8月10日 下午5:04:36</p>
 * <p>类全名：com.liiwin.scriptengine.JavaScriptEngineUtil</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class JavaScriptEngineUtil
{
	/**
	 * 获取一个JS脚本引擎
	 * @return
	 * 赵玉柱
	 */
	public static ScriptEngine getScriptEngine()
	{
		return ScriptEngineUtil.getEngineByExtension("js");
	}

	/**
	 * 执行JS引擎
	 * @param clazz 返回的类
	 * @param js
	 * @return
	 * 赵玉柱
	 */
	public static <T extends Object> T execute(Class<T> clazz, String js)
	{
		ScriptEngine engine = getScriptEngine();
		return ScriptEngineUtil.execute(engine, clazz, js);
	}

	/**
	 * 执行JS引擎
	 * @param clazz 返回的类
	 * @param js JS代码
	 * @param bindings 绑定的数据
	 * @return
	 * 赵玉柱
	 */
	public static <T extends Object> T execute(Class<T> clazz, String js, Bindings bindings)
	{
		ScriptEngine engine = getScriptEngine();
		return ScriptEngineUtil.execute(engine, clazz, js, bindings);
	}

	/**
	 * 执行JS引擎
	 * @param clazz 返回的类
	 * @param reader 执行的JS代码reader
	 * @return
	 * 赵玉柱
	 */
	public static <T extends Object> T execute(Class<T> clazz, Reader reader)
	{
		ScriptEngine engine = getScriptEngine();
		return ScriptEngineUtil.execute(engine, clazz, reader);
	}

	/**
	 * 执行JS引擎
	 * @param clazz 返回的类
	 * @param reader js代码对应的Reader
	 * @param bindings 绑定的数据
	 * @return
	 * 赵玉柱
	 */
	public static <T extends Object> T execute(Class<T> clazz, Reader reader, Bindings bindings)
	{
		ScriptEngine engine = getScriptEngine();
		return ScriptEngineUtil.execute(engine, clazz, reader, bindings);
	}

	/**
	 * 根据JS所在的file路径执行js脚本
	 * @param clazz 返回的类型
	 * @param filepath JS文件路径
	 * @return
	 * 赵玉柱
	 */
	public static <T extends Object> T executeByFilepath(Class<T> clazz, String filepath)
	{
		ScriptEngine engine = getScriptEngine();
		return ScriptEngineUtil.executeScriptFilepath(engine, clazz, filepath);
	}

	/**
	 * 根据JS所在的File路径执行JS脚本
	 * @param clazz 返回的类型
	 * @param filepath JS文件路径
	 * @param bindings 绑定值
	 * @return
	 * 赵玉柱
	 */
	public static <T extends Object> T executeByFilepath(Class<T> clazz, String filepath, Bindings bindings)
	{
		ScriptEngine engine = getScriptEngine();
		return ScriptEngineUtil.executeScriptFilepath(engine, clazz, filepath, bindings);
	}

	/**
	 * 根据JS文件执行jS脚本
	 * @param clazz 返回的类型
	 * @param file JS文件
	 * @return
	 * 赵玉柱
	 */
	public static <T extends Object> T executeByFile(Class<T> clazz, File file)
	{
		ScriptEngine engine = getScriptEngine();
		return ScriptEngineUtil.executeScriptFile(engine, clazz, file);
	}

	/**
	 * 根据js文件执行JS脚本
	 * @param clazz 返回的类型
	 * @param file JS文件
	 * @param bindings JS绑定值
	 * @return
	 * 赵玉柱
	 */
	public static <T extends Object> T executeByFile(Class<T> clazz, File file, Bindings bindings)
	{
		ScriptEngine engine = getScriptEngine();
		return ScriptEngineUtil.executeScriptFile(engine, clazz, file, bindings);
	}

	public static void main(String[] args)
	{
		Bindings bindings = getScriptEngine().createBindings();
		bindings.put("a", 3);
		bindings.put("b", 4);
		System.out.println(executeByFile(Double.class, new File("D:/MyProject/OnGithub/testEngine.js"), bindings));
	}
}
