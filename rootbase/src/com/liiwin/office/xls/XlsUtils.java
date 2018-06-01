package com.liiwin.office.xls;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.poi.hssf.converter.ExcelToHtmlConverter;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import com.liiwin.config.BasConfig;
import com.liiwin.file.types.FileTypeEnum;
import com.liiwin.file.utils.FileUtil;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2018年6月1日 下午3:44:13</p>
 * <p>类全名：com.liiwin.office.xls.XlsUtils</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class XlsUtils
{
	public static String convertExcelPath2HtmlPath(String excelPath)
	{
		File excelFile = new File(excelPath);
		File convertExcel2Html = convertExcel2Html(excelFile);
		return convertExcel2Html.getAbsolutePath();
	}

	public static String convertExcel2HtmlPath(File excelFile)
	{
		File convertExcel2Html = convertExcel2Html(excelFile);
		return convertExcel2Html.getAbsolutePath();
	}

	public static File convertExcel2Html(File excelFile)
	{
		File htmlFile = null;
		InputStream is = null;
		OutputStream os = null;
		StringWriter sw = null;
		try
		{
			if (excelFile == null)
			{
				throw new RuntimeException("docx文件不可为空");
			}
			if (!excelFile.isFile())
			{
				throw new RuntimeException("传入参数非文件类型");
			}
			String name = excelFile.getName();
			String[] fileAndExtName = FileUtil.getFileNameAndExtNameByFileName(name);
			if (StrUtil.isNullIn(true, fileAndExtName))
			{
				throw new RuntimeException("获取文件名称或文件后缀名失败" + name);
			}
			if (!(FileTypeEnum.XLS.checkFileType(name)))
			{
				throw new RuntimeException("excel转换成html仅支持xls类型的文件");
			}
			is = new FileInputStream(excelFile);
			HSSFWorkbook wb = new HSSFWorkbook(is);
			List<HSSFPictureData> allPictures = wb.getAllPictures();
			ExcelToHtmlConverter convert = new ExcelToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
			convert.setOutputColumnHeaders(false);// 不显示列的表头  
			convert.setOutputRowNumbers(false);// 不显示行的表头  
			convert.processWorkbook(wb);
			sw = new StringWriter();
			String docx2HtmlRootPath = getBasPath();
			String htmlName = fileAndExtName[0];
			String mdPath = FileUtil.getMD5Path(htmlName);
			String basPath = docx2HtmlRootPath + mdPath + File.separator;
			String htmlPath = basPath + htmlName + "." + "html";
			Transformer serializer = TransformerFactory.newInstance().newTransformer();
			serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			serializer.setOutputProperty(OutputKeys.INDENT, "yes");
			serializer.setOutputProperty(OutputKeys.METHOD, "html");
			serializer.transform(new DOMSource(convert.getDocument()), new StreamResult(sw));
			htmlFile = new File(htmlPath);
			if (!htmlFile.exists())
			{
				FileUtil.newFile(htmlFile);
			}
			os = new FileOutputStream(htmlFile);
			os.write(sw.toString().getBytes("UTF-8"));
			os.flush();
		} catch (FileNotFoundException e)
		{
			throw new RuntimeException("报错内容", e);
		} catch (ParserConfigurationException e)
		{
			throw new RuntimeException("报错内容", e);
		} catch (IOException e)
		{
			throw new RuntimeException("报错内容", e);
		} catch (TransformerConfigurationException e)
		{
			throw new RuntimeException("报错内容", e);
		} catch (TransformerFactoryConfigurationError e)
		{
			throw new RuntimeException("报错内容", e);
		} catch (TransformerException e)
		{
			throw new RuntimeException("报错内容", e);
		} finally
		{
			try
			{
				if (is != null)
				{
					is.close();
				}
				if (os != null)
				{
					os.close();
				}
				if (sw != null)
				{
					sw.close();
				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return htmlFile;
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
		String path = "C:/Users/x250-2/Desktop/TS1805110027问题记录20180531.xls";
		String convertExcelPath2HtmlPath = convertExcelPath2HtmlPath(path);
		System.out.println(convertExcelPath2HtmlPath);
	}
}
