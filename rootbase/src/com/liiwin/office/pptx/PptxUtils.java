package com.liiwin.office.pptx;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import org.apache.commons.io.FileUtils;
import com.liiwin.config.BasConfig;
import com.liiwin.file.types.FileTypeEnum;
import com.liiwin.file.utils.FileUtil;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： pptx工具类</p>
 * <p>功能： </p>
 * <p>所属模块： rotbas</p>
 * <p>版权： Copyright © 2018 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2018年5月24日 下午3:19:41</p>
 * <p>类全名：com.liiwin.office.pptx.PptxUtils</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class PptxUtils
{
	/**
	 * 根据
	 * @param mp4File
	 * @return
	 * 赵玉柱
	 */
	public static File mp4File2HtmlFile(File mp4File)
	{
		Writer fw = null;
		PrintWriter pw = null;
		try
		{
			if (mp4File == null || !mp4File.isFile())
			{
				throw new RuntimeException("参数文件不可为空");
			}
			String filename = mp4File.getName();
			String[] nameAndExt = FileUtil.getFileNameAndExtName(mp4File);
			if (StrUtil.isNullIn(true, nameAndExt))
			{
				throw new RuntimeException("获取文件名与后缀名失败" + filename);
			}
			if (!FileTypeEnum.MP4.checkFileType(filename))
			{
				throw new RuntimeException("文件非mp4文件");
			}
			String md5Path = FileUtil.getMD5Path(filename);
			String basPath = getBasPath() + md5Path;
			StringBuffer html = new StringBuffer();
			FileUtils.copyFile(mp4File, new File(basPath + File.separator + filename));
			html.append("<!DOCTYPE html><html><head><meta charset='utf-8'><title>" + nameAndExt[0] + "</title></head>");
			html.append("<body style=\"margin:0px 0px;\"><div style=\"width:100%;margin:auto 0% auto 0%;\">");
			html.append("<video controls=\"controls\"  width=\"100%\"  height=\"100%\" name=\"media\" >");//无背景图片
			html.append(String.format("%s%s.%s%s%s%s%s", "<source src=\"", nameAndExt[0], nameAndExt[1], "\" type=\"audio/", nameAndExt[1], "\" >", "</video></div>"));
			html.append("</body></html>");
			File indexFile = new File(String.format("%s%s%s", basPath, File.separator, nameAndExt[0] + ".html"));
			FileUtil.newFile(indexFile);
			fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(indexFile), "UTF-8"));
			pw = new PrintWriter(fw);
			pw.write(html.toString());
			return indexFile;
		} catch (UnsupportedEncodingException e)
		{
			throw new RuntimeException("报错内容", e);
		} catch (FileNotFoundException e)
		{
			throw new RuntimeException("报错内容", e);
		} catch (IOException e)
		{
			throw new RuntimeException("报错内容", e);
		} finally
		{
			if (fw != null)
			{
				try
				{
					fw.close();
				} catch (IOException e)
				{
					throw new RuntimeException("报错内容", e);
				}
			}
			if (pw != null)
			{
				pw.close();
			}
		}
	}

	/**
	 * 获取上传路径
	 * @return
	 * 赵玉柱
	 */
	public static String getUploadPath()
	{
		String upload = BasConfig.getPropertie("update-pptx-path");
		if (StrUtil.isStrTrimNull(upload))
		{
			throw new RuntimeException("未配置[pptx上传路径]--[update-pptx-path]");
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
		String docx2HtmlRootPath = BasConfig.getPropertie("pptx2html-path");
		if (StrUtil.isStrTrimNull(docx2HtmlRootPath))
		{
			throw new RuntimeException("未配置[pptx转html路径]--[pptx2html-path]");
		}
		return docx2HtmlRootPath;
	}

	public static void main(String[] args)
	{
		String path = "D:/update/VID_20180524_163529.mp4";
		File file = new File(path);
		File mp4File2HtmlFile = mp4File2HtmlFile(file);
		System.out.println(mp4File2HtmlFile == null ? null : mp4File2HtmlFile.toString());
	}
}
