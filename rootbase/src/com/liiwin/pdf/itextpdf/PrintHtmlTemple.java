package com.liiwin.pdf.itextpdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.apache.commons.io.FileUtils;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import com.liiwin.exception.DataException;
import com.liiwin.formula.FormulaParse;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： 根据html模版打印pdf</p>
 * <p>功能： </p>
 * <p>所属模块： rootbase--pdf--itextpdf</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年6月27日 下午5:36:28</p>
 * <p>类全名：com.liiwin.pdf.itextpdf.PrintHtmlTemple</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class PrintHtmlTemple
{
	/**
	 * 根据html模版文件生成pdf文件
	 * @param templtPath
	 * @param targetPath
	 * @param values
	 */
	public static void printHtmlTemple(String templtPath, String targetPath, Map<String,Object> values)
	{
		try
		{
			String str = FileUtils.readFileToString(new File(templtPath), Charset.forName("gbk"));
			String newStr = dealKV(str, values);
			StringReader rd = new StringReader(newStr);
			List<Element> parseToList = HTMLWorker.parseToList(rd, null, new HashMap<String,Object>());
			Document document = new Document(PageSize.A4);
			PdfWriter.getInstance(document, new FileOutputStream(targetPath));
			document.open();
			for (Element e : parseToList)
			{
				document.add(e);
			}
			System.out.println("templete完成");
			System.out.println(String.valueOf(rd));
			System.out.println(String.valueOf(str));
			document.close();
		} catch (IOException e)
		{
			throw new RuntimeException("报错内容", e);
		} catch (DocumentException e)
		{
			// TODO Auto-generated catch block
			throw new RuntimeException("报错内容", e);
		}
	}

	/**
	 * 根据模版文件生成的字符串信息，处理其中的公式，进行计算
	 * @param str
	 * @param values
	 * @return
	 */
	private static String dealKV(String str, Map<String,Object> values)
	{
		String resultStr = str;
		int[] formulaStartIdxs = StrUtil.getStrIndexs(str, "<formula>");
		formulaStartIdxs = StrUtil.setDeviation(formulaStartIdxs, "<formula>".length());
		int[] formulaEndIdxs = StrUtil.getStrIndexs(str, "</formula>");
		if (formulaStartIdxs == null || formulaEndIdxs == null || formulaStartIdxs.length != formulaEndIdxs.length)
		{
			throw new DataException("打印格式错误");
		}
		double[] data1 = new double[formulaStartIdxs.length];
		double[] data2 = new double[formulaEndIdxs.length];
		for (int i = 0; i < data1.length; i++)
		{
			data1[i] = formulaStartIdxs[i];
		}
		for (int i = 0; i < data2.length; i++)
		{
			data2[i] = formulaEndIdxs[i];
		}
		String[] formulas = null;
		if (StrUtil.isAscDirection(data1) && StrUtil.isAscDirection(data2))
		{
			formulas = StrUtil.getSubstring(resultStr, formulaStartIdxs, formulaEndIdxs);
		}
		Map<String,String> resultMap = bindFormulas(formulas, values);
		if (resultMap != null && !resultMap.isEmpty())
		{
			Set<String> keys = resultMap.keySet();
			for (String key : keys)
			{
				resultStr = resultStr.replace(key, resultMap.get(key));
			}
		}
		return resultStr;
	}

	/**
	 * 处理公式
	 * @param formulas
	 * @param values
	 * @return
	 */
	private static Map<String,String> bindFormulas(String[] formulas, Map<String,Object> values)
	{
		if (formulas == null || formulas.length == 0)
		{
			return null;
		}
		Map<String,String> result = new HashMap<>();
		ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
		try
		{
			for (int i = 0; i < formulas.length; i++)
			{
				String parse = FormulaParse.parse(formulas[i], values);
				result.put(formulas[i], StrUtil.obj2str(jse.eval(parse), formulas[i]));
				System.out.println("formula=" + formulas[i] + "\t结果是\t" + jse.eval(parse));
			}
		} catch (ScriptException e)
		{
			e.printStackTrace();
			throw new DataException(e.getMessage());
		}
		return result;
	}

	public static void main(String[] args)
	{
		String templtPath = "D:/MyProject/OnGithub/templete.html";
		String targetPath = "D:/templete.pdf";
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("ab", "123");
		values.put("cd", "456");
		printHtmlTemple(templtPath, targetPath, values);
	}
}
