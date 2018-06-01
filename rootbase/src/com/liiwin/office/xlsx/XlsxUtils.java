package com.liiwin.office.xlsx;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import com.liiwin.config.BasConfig;
import com.liiwin.file.types.FileTypeEnum;
import com.liiwin.file.utils.FileUtil;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： xlsx工具类</p>
 * <p>功能： </p>
 * <p>所属模块： rootbas</p>
 * <p>版权： Copyright © 2018 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2018年5月24日 下午1:47:55</p>
 * <p>类全名：com.liiwin.office.xlsx.XlsxUtils</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class XlsxUtils
{
	public static File xlsxFile2HtmlFile(File xlsxFile)
	{
		try
		{
			if (xlsxFile == null)
			{
				throw new RuntimeException("xlsx文件不可为空");
			}
			if (!xlsxFile.isFile())
			{
				throw new RuntimeException("传入参数非文件类型");
			}
			String name = xlsxFile.getName();
			String[] fileAndExtName = FileUtil.getFileNameAndExtNameByFileName(name);
			if (StrUtil.isNullIn(true, fileAndExtName))
			{
				throw new RuntimeException("获取文件名称或文件后缀名失败" + name);
			}
			String htmlName = fileAndExtName[0];
			String extName = fileAndExtName[1];
			if (!FileTypeEnum.XLSX.checkFileType(extName))
			{
				throw new RuntimeException("当前文件[" + name + "]非xlsx文件");
			}
			InputStream stream = new FileInputStream(xlsxFile);
			Workbook workbook = WorkbookFactory.create(stream);
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e)
		{
			// TODO Auto-generated catch block
			throw new RuntimeException("报错内容", e);
		}
		return null;
	}

	/**
	 * 获取上传路径
	 * @return
	 * 赵玉柱
	 */
	public static String getUploadPath()
	{
		String upload = BasConfig.getPropertie("update-xlsx-path");
		if (StrUtil.isStrTrimNull(upload))
		{
			throw new RuntimeException("未配置[xlsx上传路径]--[update-xlsx-path]");
		}
		return upload;
	}

	/**
	 * 获取基础路径
	 * @return
	 * 赵玉柱
	 */
	public static String getBasPath()
	{
		String docx2HtmlRootPath = BasConfig.getPropertie("xlsx2html-path");
		if (StrUtil.isStrTrimNull(docx2HtmlRootPath))
		{
			throw new RuntimeException("未配置[xlsx转html路径]--[xlsx2html-path]");
		}
		return docx2HtmlRootPath;
	}

	public static void main(String[] args)
	{
	}
}
