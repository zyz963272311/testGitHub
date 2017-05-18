package itext;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfWriter;
/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： @TODO</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年12月23日 上午10:10:47</p>
 * <p>类全名：itext.ITextPDF</p>
 * 作者：x250-2
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class ITextPDF
{
	private Document	document;
	private PdfWriter	writer;

	public ITextPDF(String fileName) throws IOException
	{
		String fileExtname = fileName.substring(fileName.lastIndexOf(".") + 1);
		if (fileExtname == null || "".equals(fileExtname))
		{
			fileName = fileName + ".pdf";
		}
		if (!fileExtname.toLowerCase().equals("pdf"))
		{
			throw new IOException("PDF文件必须要以.PDF结尾");
		}
		try
		{
			document = new Document();
			writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
		} catch (FileNotFoundException | DocumentException e)
		{
			try
			{
				throw new Exception(e.getMessage());
			} catch (Exception e1)
			{
			}
		}
	}

	public void open()
	{
		this.document.open();
	}

	public boolean isOpen()
	{
		return this.document.isOpen();
	}

	public void add(Element element)
	{
		try
		{
			this.document.add(element);
		} catch (DocumentException e)
		{
			throw new RuntimeException("不知道什么报错内容", e);
		}
	}

	public void add(Element[] elements)
	{
		for (Element element : elements)
		{
			add(element);
		}
	}

	public void close()
	{
		this.document.close();
	}
}
