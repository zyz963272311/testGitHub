package com.liiwin.scriptengine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;
import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： java 脚本引擎</p>
 * <p>功能： </p>
 * <p>所属模块： rootbase</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2017年8月10日 下午3:31:55</p>
 * <p>类全名：com.liiwin.scriptengine.ScriptEngineUtil</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 * 赵玉柱
 * <table border="1" >
 * <tr><th>名称<th>拓展名<th>mimetype
 * <tr><td>JavaScript<td>js<td>text/javascript
 * <tr><td>
 * </table>
 * <p>
 * 目前支持的引擎
 * name	Mozilla Rhino<br>
 * engineversion	1.7 release 3 PRERELEASE<br>
 * languageVersion	1.8<br>
 * extensions	[js]<br>
 * MimeTypes	[application/javascript, application/ecmascript, text/javascript, text/ecmascript]<br>
 * names	[js, rhino, JavaScript, javascript, ECMAScript, ecmascript]
 * </p>
 */
public class ScriptEngineUtil
{
	/**
	 * 根据文件拓展名获取脚本引擎
	 * @param extension 拓展名
	 * @return
	 * 赵玉柱
	 */
	public static ScriptEngine getEngineByExtension(String extension)
	{
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByExtension(extension);
		if (engine == null)
		{
			throw new RuntimeException("拓展名为【" + extension + "】的脚本类型不支持");
		}
		return engine;
	}

	public static ScriptEngine getEngineByMime(String mime)
	{
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByMimeType(mime);
		if (engine == null)
		{
			throw new RuntimeException("mime类型为【" + mime + "】的脚本类型不支持");
		}
		return engine;
	}

	/**
	 * 根据脚本名称获取脚本引擎
	 * @param name 脚本名称
	 * @return
	 * 赵玉柱
	 */
	public static ScriptEngine getEngineByName(String name)
	{
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName(name);
		if (engine == null)
		{
			throw new RuntimeException("名称为【" + name + "】的脚本类型不支持");
		}
		return engine;
	}

	/**
	 * 执行脚本代码
	 * @param engine 脚本引擎
	 * @param clazz 返回的类
	 * @param scriptStr 脚本代码
	 * @return
	 * 赵玉柱
	 */
	public static <T extends Object> T execute(ScriptEngine engine, Class<T> clazz, String scriptStr)
	{
		if (engine == null)
		{
			return null;
		}
		Bindings bindings = engine.createBindings();
		return execute(engine, clazz, scriptStr, bindings);
	}

	/**
	 * 执行脚本代码
	 * @param engine 脚本引擎
	 * @param clazz 返回的类
	 * @param scriptStr 脚本类型
	 * @param bindings 脚本绑定值
	 * @return
	 * 赵玉柱
	 */
	public static <T extends Object> T execute(ScriptEngine engine, Class<T> clazz, String scriptStr, Bindings bindings)
	{
		if (engine == null)
		{
			return null;
		}
		T t = null;
		try
		{
			t = (T) engine.eval(scriptStr, bindings);
		} catch (ScriptException e)
		{
			throw new RuntimeException("报错内容", e);
		}
		return t;
	}

	/**
	 * 执行脚本Reader
	 * @param engine 脚本引擎
	 * @param clazz 返回的类
	 * @param reader  脚本reader
	 * @return
	 * 赵玉柱
	 */
	public static <T extends Object> T execute(ScriptEngine engine, Class<T> clazz, Reader reader)
	{
		if (engine == null)
		{
			return null;
		}
		Bindings bindings = engine.createBindings();
		return execute(engine, clazz, reader, bindings);
	}

	/**
	 * 执行脚本reader
	 * @param engine 脚本引擎
	 * @param clazz 返回的类
	 * @param reader 脚本reader
	 * @param bindings 脚本绑定值
	 * @return
	 * 赵玉柱
	 */
	public static <T extends Object> T execute(ScriptEngine engine, Class<T> clazz, Reader reader, Bindings bindings)
	{
		if (engine == null)
		{
			return null;
		}
		T t = null;
		try
		{
			t = (T) engine.eval(reader, bindings);
		} catch (ScriptException e)
		{
			throw new RuntimeException("报错内容", e);
		}
		return t;
	}

	/**
	 * 根据文件执行脚本
	 * @param engine 脚本引擎
	 * @param clazz 返回的类
	 * @param filePath 文件路径
	 * @return
	 * 赵玉柱
	 */
	public static <T extends Object> T executeScriptFilepath(ScriptEngine engine, Class<T> clazz, String filePath)
	{
		if (engine == null)
		{
			return null;
		}
		if (StrUtil.isStrTrimNull(filePath))
		{
			return null;
		}
		Reader reader = null;
		try
		{
			reader = new FileReader(new File(filePath));
		} catch (FileNotFoundException e)
		{
			throw new RuntimeException("报错内容", e);
		}
		return execute(engine, clazz, reader);
	}

	/**
	 * 根据文件路径执行脚本
	 * @param engine 脚本引擎
	 * @param clazz 返回的类型
	 * @param filePath 文件路径
	 * @param bindings 绑定的值
	 * @return
	 * 赵玉柱
	 */
	public static <T extends Object> T executeScriptFilepath(ScriptEngine engine, Class<T> clazz, String filePath, Bindings bindings)
	{
		if (engine == null)
		{
			return null;
		}
		if (StrUtil.isStrTrimNull(filePath))
		{
			return null;
		}
		Reader reader = null;
		try
		{
			reader = new FileReader(new File(filePath));
		} catch (FileNotFoundException e)
		{
			throw new RuntimeException("报错内容", e);
		}
		return execute(engine, clazz, reader, bindings);
	}

	/**
	 * 执行脚本文件
	 * @param engine 脚本引擎
	 * @param clazz 返回的类
	 * @param file 脚本文件
	 * @return
	 * 赵玉柱
	 */
	public static <T extends Object> T executeScriptFile(ScriptEngine engine, Class<T> clazz, File file)
	{
		if (engine == null)
		{
			return null;
		}
		if (file == null)
		{
			return null;
		}
		if (!file.isFile())
		{
			return null;
		}
		Reader reader = null;
		try
		{
			reader = new FileReader(file);
		} catch (FileNotFoundException e)
		{
			throw new RuntimeException("报错内容", e);
		}
		return execute(engine, clazz, reader);
	}

	/**
	 * 执行脚本文件
	 * @param engine 脚本引擎
	 * @param clazz 返回的类型
	 * @param file 脚本文件
	 * @param bindings 绑定值
	 * @return
	 * 赵玉柱
	 */
	public static <T extends Object> T executeScriptFile(ScriptEngine engine, Class<T> clazz, File file, Bindings bindings)
	{
		if (engine == null)
		{
			return null;
		}
		if (file == null)
		{
			return null;
		}
		if (!file.isFile())
		{
			return null;
		}
		Reader reader = null;
		try
		{
			reader = new FileReader(file);
		} catch (FileNotFoundException e)
		{
			throw new RuntimeException("报错内容", e);
		}
		return execute(engine, clazz, reader, bindings);
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		ScriptEngine script = getEngineByExtension("js");
		Bindings bindings = script.createBindings();
		bindings.put("a", 3);
		bindings.put("b", 4);
		Object o = execute(script, Object.class, new FileReader(new File("D:/MyProject/OnGithub/testEngine.js")), bindings);
		System.out.println(o);
	}

	/**
	 * 
	 * 赵玉柱
	 */
	private static void test2()
	{
		ScriptEngineManager manager = new ScriptEngineManager();
		List<ScriptEngineFactory> engineFactories = manager.getEngineFactories();
		for (ScriptEngineFactory engineFactory : engineFactories)
		{
			System.out.println("=============================================");
			System.out.println("name\t" + engineFactory.getEngineName());
			System.out.println("engineversion\t" + engineFactory.getEngineVersion());
			System.out.println("languageVersion\t" + engineFactory.getLanguageVersion());
			System.out.println("extensions\t" + engineFactory.getExtensions());
			System.out.println("MimeTypes\t" + engineFactory.getMimeTypes());
			System.out.println("names\t" + engineFactory.getNames());
		}
	}

	/**
	 * 
	 * 赵玉柱
	 */
	private static void test1()
	{
		ScriptEngine engine1 = getEngineByMime("text/javascript");
		ScriptEngine engine2 = getEngineByExtension("js");
		ScriptEngine engine3 = getEngineByName("JavaScript");
		try
		{
			engine1.eval("print(1)");
			engine2.eval("println(1)");
			engine3.eval("println(1)");
		} catch (ScriptException e)
		{
			throw new RuntimeException("报错内容", e);
		}
	}
}
