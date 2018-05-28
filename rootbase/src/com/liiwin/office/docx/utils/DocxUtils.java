package com.liiwin.office.docx.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.log4j.PropertyConfigurator;
import org.docx4j.Docx4J;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import com.liiwin.config.BasConfig;
import com.liiwin.file.types.FileTypeEnum;
import com.liiwin.file.utils.FileUtil;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： docx文件工具类</p>
 * <p>功能： </p>
 * <p>所属模块： rootbas</p>
 * <p>版权： Copyright © 2018 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2018年5月24日 上午10:23:53</p>
 * <p>类全名：com.liiwin.office.docx.utils.DocxUtils</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class DocxUtils
{
	/**
	 * docx路径转换成html路径，在网页直接预览
	 * @param docxpath
	 * @return
	 * 赵玉柱
	 */
	public static String docxPath2HtmlPath(String docxpath)
	{
		if (StrUtil.isStrTrimNull(docxpath))
		{
			return null;
		}
		return docxFile2HtmlPath(new File(docxpath));
	}

	/**
	 * docx文件转换成html路径，在网页直接预览
	 * @param file
	 * @return
	 * 赵玉柱
	 */
	public static String docxFile2HtmlPath(File docxFile)
	{
		File htmlFile = docxFile2HtmlFile(docxFile);
		return (htmlFile != null && htmlFile.isFile()) ? htmlFile.getAbsolutePath() : null;
	}

	/**
	 * 将docxfile转换成htmlfile
	 * @param docxFile
	 * @return
	 * 赵玉柱
	 */
	public static File docxFile2HtmlFile(File docxFile)
	{
		try
		{
			if (docxFile == null)
			{
				throw new RuntimeException("docx文件不可为空");
			}
			if (!docxFile.isFile())
			{
				throw new RuntimeException("传入参数非文件类型");
			}
			String name = docxFile.getName();
			String[] fileAndExtName = FileUtil.getFileNameAndExtNameByFileName(name);
			if (StrUtil.isNullIn(true, fileAndExtName))
			{
				throw new RuntimeException("获取文件名称或文件后缀名失败" + name);
			}
			String htmlName = fileAndExtName[0];
			String extName = fileAndExtName[1];
			if (!FileTypeEnum.DOCX.checkFileType(name))
			{
				throw new RuntimeException("当前文件[" + name + "]非docx文件");
			}
			WordprocessingMLPackage wmp = WordprocessingMLPackage.load(docxFile);
			String mdPath = FileUtil.getMD5Path(name);
			String docx2HtmlRootPath = getBasPath();
			String basPath = docx2HtmlRootPath + mdPath + File.separator;
			String htmlPath = basPath + htmlName + "." + "html";
			File htmlFile = new File(htmlPath);
			if (!htmlFile.exists())
			{
				FileUtil.newFile(htmlFile);
			}
			Docx4J.toHTML(wmp, basPath + "ImgDir", "ImgDir", new FileOutputStream(htmlFile));
			return htmlFile;
		} catch (Docx4JException | IOException e)
		{
			throw new RuntimeException("报错内容", e);
		}
	}

	public static String getDocxUploadBasPath()
	{
		String upload = BasConfig.getPropertie("update-docx-path");
		if (StrUtil.isStrTrimNull(upload))
		{
			throw new RuntimeException("未配置[docx上传路径]--[update-docx-path]");
		}
		return upload;
	}

	/**
	 * 获取docx2html的基本路径
	 * @return
	 * 赵玉柱
	 */
	public static String getBasPath()
	{
		String docx2HtmlRootPath = BasConfig.getPropertie("docx2html-path");
		if (StrUtil.isStrTrimNull(docx2HtmlRootPath))
		{
			throw new RuntimeException("未配置[docx转html路径]--[docx2html-path]");
		}
		return docx2HtmlRootPath;
	}

	public static void main(String[] args)
	{
		PropertyConfigurator.configure(DocxUtils.class.getResource("/resources/log4j.properties"));
		long start = System.currentTimeMillis();
		String docxPath = "C:/Users/x250-2/Desktop/git学习.docx";
		String docxPath2HtmlPath = docxPath2HtmlPath(docxPath);
		long end = System.currentTimeMillis();
		System.out.println(docxPath2HtmlPath);
		System.out.println((end - start) + "毫秒");
	}
}
