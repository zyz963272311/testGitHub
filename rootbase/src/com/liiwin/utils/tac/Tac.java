package com.liiwin.utils.tac;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import javax.script.Bindings;
import javax.script.ScriptEngine;
import com.liiwin.random.RandomString;
import com.liiwin.random.RandomStringImpl;
import com.liiwin.scriptengine.ScriptEngineUtil;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： TAC 此类中不允许出现任何一个静态方法</p>
 * <p>功能： </p>
 * <p>所属模块： rootbas</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2017年11月18日 下午4:15:28</p>
 * <p>类全名：com.liiwin.utils.Tac</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Tac
{
	protected ScriptEngine	engine;
	protected WriterWaper	waper;

	public Tac(String engineName, WriterWaper waper)
	{
		if (StrUtil.isStrTrimNull(engineName))
		{
			engineName = "js";
		}
		if (waper == null)
		{
			waper = new WriterWaper();
		}
		this.waper = waper;
		engine = ScriptEngineUtil.getEngineByExtension(engineName);
	}

	/**
	 * 
	 */
	public Tac(String engineName)
	{
		this(engineName, null);
	}

	public Tac()
	{
		this("js");
	}

	public List<Object> execute(String str)
	{
		//step 1 取行数据 分成等号左右
		if (StrUtil.isStrTrimNull(str))
		{
			return null;
		}
		checkGrammar(str);
		//随机生成一个字符串，作为Tac得变量
		RandomString random = new RandomStringImpl();
		String prefix = random.getRandomString(20, 'a', 'z');
		String prefix1 = random.getRandomString(20, 'a', 'z');
		String additional = prefix1 + " = Java.type(\"" + getClass().getName() + "\");";
		additional = additional + "\n" + prefix + " = new " + prefix1 + "(\"js\"," + waper.toString().replace("@", "").replace(".", "") + ")";
		str = additional + "\n" + str;
		List<Object> result = new ArrayList<>();
		String[] strArray = StrUtil.split(str, '\n');
		Bindings bindings = engine.createBindings();
		bindings.put(waper.toString().replace("@", "").replace(".", ""), waper);
		int i = 0;
		try
		{
			for (String subStr : strArray)
			{
				i++;
				if (StrUtil.isStrTrimNull(subStr))
				{
					continue;
				}
				//step 2 执行行数据 等号右边，并将输出流放到返回结果中
				String[] subStrArray = StrUtil.split(subStr, '=');
				subStrArray[subStrArray.length - 1] = buildMethod(prefix, subStrArray[subStrArray.length - 1].trim());
				String s = StrUtil.strcat("=", subStrArray);
				Object o = ScriptEngineUtil.execute(engine, Object.class, s, bindings);
				if (subStr.trim().startsWith("print"))
				{
					result.add(o);
				}
				if (subStrArray != null && subStrArray.length > 0)
				{
					//step 3 将执行的结果进行数据绑定
					bindings.put(subStrArray[0], o);
				}
				//step 4 行移动
			}
		} catch (Exception e)
		{
			throw new RuntimeException("第" + (i - 1) + "行错误：" + e.getMessage(), e);
		}
		return result;
	}

	/**
	 * println(stmp.format(t))
	 * print(stmp.format(t))
	 * @param subStr
	 * @return
	 * 赵玉柱
	 */
	@SuppressWarnings("unused")
	private String getExecutStr(String subStr)
	{
		String key = null;
		if (subStr.startsWith("println"))
		{
			key = "println";
		} else if (subStr.startsWith("print"))
		{
			key = "print";
		}
		subStr = subStr.substring(key.length() + 1);
		subStr = subStr.substring(0, subStr.length() - 1);
		return subStr;
	}

	/**
	 * @param o
	 * @return
	 * 赵玉柱
	 * @throws IOException 
	 */
	public String println(Object o) throws IOException
	{
		String str = StrUtil.obj2str(o);
		waper.write(str);
		waper.write("\n");
		waper.flush();
		return str + "\n";
	}

	/**
	 * 输出信息，不换行
	 * @param o
	 * @return
	 * 赵玉柱
	 * @throws IOException 
	 */
	public String print(Object o) throws IOException
	{
		return print(o, null);
	}

	/**
	 * 输出信息，
	 * @param o
	 * @param dim
	 * @return
	 * 赵玉柱
	 * @throws IOException 
	 */
	public String print(Object o, String dim) throws IOException
	{
		String str = StrUtil.obj2str(o) + (dim == null ? "" : dim);
		waper.write(str);
		waper.flush();
		return str;
	}

	/**
	 * 判断是否是数组
	 * @param o
	 * @return
	 * 赵玉柱
	 */
	public boolean isArray(Object o)
	{
		if (o == null)
		{
			return false;
		}
		return o.getClass().isArray();
	}

	/**
	 * 将str重组，将本类内部得方法替换
	 * @param prefix
	 * @param str
	 * @return
	 * 赵玉柱
	 */
	private String buildMethod(String prefix, String str)
	{
		Set<String> allMethod = getAllMethod();
		if (StrUtil.isStrTrimNull(str))
		{
			return null;
		}
		String tempStr = str.trim();
		for (String msd : allMethod)
		{
			if (tempStr.startsWith(msd + "("))
			{
				tempStr = StrUtil.strcat(prefix, str, ".");
			}
		}
		return tempStr;
	}

	/**
	 * 获取Tac所有得方法
	 * @return
	 * 赵玉柱
	 */
	private Set<String> getAllMethod()
	{
		TacCache cache = TacCache.getInstance();
		Set<Method> publicMethods = cache.getPublicMethods();
		Set<String> pubMethods = new HashSet<>();
		for (Method method : publicMethods)
		{
			pubMethods.add(method.getName());
		}
		return pubMethods;
	}

	/**
	 * 获取数组得长度
	 * @param o
	 * @return
	 * 赵玉柱
	 */
	public int arrayLength(Object o)
	{
		if (!isArray(o))
		{
			return 0;
		} else
		{
			return Array.getLength(o);
		}
	}

	/**
	 * 生成一个数组
	 * @param cls
	 * @param length
	 * @return
	 * 赵玉柱
	 */
	@SuppressWarnings("unchecked")
	public <T extends Object> T[] newArray(Class<T> cls, int length)
	{
		if (length <= 0)
		{
			return null;
		}
		return (T[]) Array.newInstance(cls, length);
	}

	/**
	 * 获取数据类型
	 * @param o
	 * @return
	 * 赵玉柱
	 */
	public Class<? extends Object> typeOf(Object o)
	{
		return o.getClass();
	}

	/**
	 * 打印一个数组
	 * @param o
	 * @return
	 * 赵玉柱
	 * @throws IOException 
	 */
	public String printArray(Object o) throws IOException
	{
		return printArray(o, ",");
	}

	/**
	 * 打印array，中间用回车分割
	 * @param o
	 * @return
	 * 赵玉柱
	 * @throws IOException 
	 */
	public String printlnArray(Object o) throws IOException
	{
		return printArray(o, "\n");
	}

	public String printArray(Object o, String dim) throws IOException
	{
		StringBuffer strArray = new StringBuffer();
		if (isArray(o))
		{
			int length = arrayLength(o);
			for (int i = 0; i < length; i++)
			{
				Object oi = Array.get(o, i);
				String si = print(oi, dim);
				strArray.append(si == null ? " " : si);
			}
		}
		if (strArray.length() > 0)
		{
			strArray.setLength(strArray.length() - 1);
		}
		return "[ " + strArray + " ]";
	}

	/**
	 * 校验语法
	 * @param str
	 * 赵玉柱
	 */
	private void checkGrammar(String str)
	{
		Stack<Grammer> grammerStack = new Stack<>();
		TacCache cache = TacCache.getInstance();
		String with_end = cache.getWith_endPropertie();//需要用end结尾得语法
		String must_pre = cache.getMust_prePropertie();//必须又此前缀
		String[] with_endArray = StrUtil.split(with_end, ';');
		String[][] must_preArray = StrUtil.split(must_pre, ';', ',');
		String[] strArray = StrUtil.split(str, '\n');
		int wel = arrayLength(with_endArray);
		int mpL = arrayLength(must_preArray);
		int length = arrayLength(strArray);
		for (int i = 0; i < length; i++)
		{
			String subStr = strArray[i];
			if (StrUtil.isStrTrimNull(subStr))
			{
				continue;
			}
			String subStrNoBlank = StrUtil.trim(subStr);
			if (StrUtil.isStrTrimNull(subStrNoBlank))
			{
				continue;
			}
			if (StrUtil.startWith(subStrNoBlank, "end "))
			{
				String[] splitNoBlank = StrUtil.split(subStrNoBlank, ' ');
				if (splitNoBlank.length == 2)
				{
					if (grammerStack.isEmpty())
					{
						throw new RuntimeException("第[" + i + "]行错误,未找到命令起始位置[" + subStr + "]");
					}
					Grammer grammer = grammerStack.peek();
					List<String> grammerList = grammer.getGrammerList();
					if (StrUtil.equals(grammerList.get(0), splitNoBlank[1]))
					{
						grammerStack.pop();
					} else
					{
						throw new RuntimeException("第[" + i + "]行错误,未找到命令起始位置[" + subStr + "]");
					}
				} else
				{
					throw new RuntimeException("第[" + i + "]行错误的结束[" + subStr + "]");
				}
			}
			//将所有的含有end的添加到栈中
			for (int j = 0; j < wel; j++)
			{
				if (StrUtil.equals(subStrNoBlank, with_endArray[j]) || StrUtil.startWith(subStrNoBlank, with_endArray[j], 0, false))
				{
					List<String> grammerList = new ArrayList<>();
					grammerList.add(subStrNoBlank);
					Grammer grammer = new Grammer(grammerList, i + 1);
					grammerStack.push(grammer);
				}
			}
			//校验结尾
			for (int j = 0; j < mpL; j++)
			{
				if (StrUtil.equals(subStrNoBlank, must_preArray[j][0]) || StrUtil.startWith(subStrNoBlank, must_preArray[j][0], 0, false))
				{
					if (grammerStack.isEmpty())
					{
						throw new RuntimeException("第[" + i + "]行异常,语句[" + must_preArray[j][0] + "]前必须有[" + must_preArray[j][1] + "]语句");
					}
					Grammer grammer = grammerStack.peek();
					String[] must_pre_array = StrUtil.split(must_preArray[j][1], '|');
					List<String> grammerList = grammer.getGrammerList();
					if (!grammerList.contains(must_pre_array[0]))
					{
						throw new RuntimeException("第[" + i + "]行异常,语句[" + must_preArray[j][0] + "]前必须有[" + must_preArray[j][1] + "]语句!");
					}
				}
			}
		}
		if (!grammerStack.isEmpty())
		{
			throw new RuntimeException("语句未正确结束，请核对");
		}
	}

	public static void main(String[] args)
	{
		System.out.println(Tac.class.getName());
	}
}
