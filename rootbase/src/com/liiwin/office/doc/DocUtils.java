package com.liiwin.office.doc;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.w3c.dom.Document;
import com.liiwin.config.BasConfig;
import com.liiwin.file.types.FileTypeEnum;
import com.liiwin.file.utils.FileUtil;
import com.liiwin.office.docx.utils.DocxUtils;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： Doc文件转换成html文件</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2018年6月1日 下午3:40:13</p>
 * <p>类全名：com.liiwin.office.doc.DocUtils</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class DocUtils
{
	/**
	 * docx路径转换成html路径，在网页直接预览
	 * @param docxpath
	 * @return
	 * 赵玉柱
	 */
	public static String docPath2HtmlPath(String docpath)
	{
		if (StrUtil.isStrTrimNull(docpath))
		{
			return null;
		}
		return docFile2HtmlPath(new File(docpath));
	}

	/**
	 * docx文件转换成html路径，在网页直接预览
	 * @param file
	 * @return
	 * 赵玉柱
	 */
	public static String docFile2HtmlPath(File docFile)
	{
		File htmlFile = docFile2HtmlFile(docFile);
		return (htmlFile != null && htmlFile.isFile()) ? htmlFile.getAbsolutePath() : null;
	}

	/**
	 * 将docxfile转换成htmlfile
	 * @param docxFile
	 * @return
	 * 赵玉柱
	 */
	public static File docFile2HtmlFile(File docFile)
	{
		BufferedWriter bw = null;
		FileOutputStream fos = null;
		try
		{
			if (docFile == null)
			{
				throw new RuntimeException("docx文件不可为空");
			}
			if (!docFile.isFile())
			{
				throw new RuntimeException("传入参数非文件类型");
			}
			String name = docFile.getName();
			String[] fileAndExtName = FileUtil.getFileNameAndExtNameByFileName(name);
			if (StrUtil.isNullIn(true, fileAndExtName))
			{
				throw new RuntimeException("获取文件名称或文件后缀名失败" + name);
			}
			String htmlName = fileAndExtName[0];
			if (!FileTypeEnum.DOC.checkFileType(name))
			{
				throw new RuntimeException("当前文件[" + name + "]非docx文件");
			}
			HWPFDocument document = new HWPFDocument(new FileInputStream(docFile));
			WordToHtmlConverter converter = new WordToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
			converter.setPicturesManager(new PicturesManager()
			{
				@Override
				public String savePicture(byte[] content, PictureType pictureType, String suggestedName, float widthInches, float heightInches)
				{
					return "pic/" + suggestedName;
				}
			});
			converter.processDocument(document);
			String docx2HtmlRootPath = getBasPath();
			String mdPath = FileUtil.getMD5Path(htmlName);
			String basPath = docx2HtmlRootPath + mdPath + File.separator;
			String htmlPath = basPath + htmlName + "." + "html";
			List<Picture> pics = document.getPicturesTable().getAllPictures();
			String picPath = basPath + "pic" + File.separator;
			File picDir = new File(picPath);
			if (!picDir.exists())
			{
				FileUtil.newDir(picDir);
			}
			if (pics != null)
			{
				for (int i = 0; i < pics.size(); i++)
				{
					Picture pic = pics.get(i);
					try
					{
						pic.writeImageContent(new FileOutputStream(picPath + pic.suggestFullFileName()));
					} catch (FileNotFoundException e)
					{
						e.printStackTrace();
					}
				}
			}
			Document htmlDocument = converter.getDocument();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			DOMSource domSource = new DOMSource(htmlDocument);
			StreamResult streamResult = new StreamResult(out);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer serializer = tf.newTransformer();
			serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			serializer.setOutputProperty(OutputKeys.INDENT, "yes");
			serializer.setOutputProperty(OutputKeys.METHOD, "html");
			serializer.transform(domSource, streamResult);
			out.close();
			File file = new File(htmlPath);
			if (!file.exists())
			{
				FileUtil.newFile(file);
			}
			fos = new FileOutputStream(file);
			bw = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));
			bw.write(new String(out.toByteArray()));
			return null;
		} catch (IOException e)
		{
			throw new RuntimeException("报错内容", e);
		} catch (ParserConfigurationException e)
		{
			throw new RuntimeException("报错内容", e);
		} catch (TransformerConfigurationException e)
		{
			// TODO Auto-generated catch block
			throw new RuntimeException("报错内容", e);
		} catch (TransformerException e)
		{
			// TODO Auto-generated catch block
			throw new RuntimeException("报错内容", e);
		} finally
		{
			try
			{
				if (bw != null)
					bw.close();
				if (fos != null)
					fos.close();
			} catch (IOException ie)
			{
			}
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
		String docPath = "C:/Users/x250-2/Desktop/git学习.doc";
		String docxPath2HtmlPath = docPath2HtmlPath(docPath);
		long end = System.currentTimeMillis();
		System.out.println(docxPath2HtmlPath);
		System.out.println((end - start) + "毫秒");
	}
}
