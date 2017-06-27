package com.liiwin.pdf.itextpdf;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import org.apache.commons.io.FileUtils;
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
	public static void printHtmlTemple(String templtPath, String targetPath, Map<String,Object> values)
	{
		try
		{
			String str = FileUtils.readFileToString(new File(templtPath), Charset.forName("gbk"));
			String newStr = dealKV(str, values);
		} catch (IOException e)
		{
			throw new RuntimeException("报错内容", e);
		}
	}

	public static String dealKV(String str, Map<String,Object> values)
	{
		return null;
	}
}
