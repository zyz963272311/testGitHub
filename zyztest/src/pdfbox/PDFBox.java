package pdfbox;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Iterator;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.text.PDFTextStripper;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
/**
 * <p>标题： 利用pdfbox读取pdf</p>
 * <p>功能： </p>
 * <p>所属模块： 利用pdfbox读取pdf</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年2月20日 下午4:28:06</p>
 * <p>类全名：pdfbox.PDFBox</p>
 * 作者：x250-2
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class PDFBox
{
	/**
	 * 利用pdfbox读取pdf文件
	 * @param args
	 * x250-2
	 */
	public static void main(String[] args)
	{
		String filePath = "d://阿里巴巴Java开发手册.pdf";
		String filePath_out = "d://pilePath_out.pdf";
		String filePath_image = "d://pdf_image";
		pdfReader(filePath);
		readPdfImages(filePath, filePath_out, filePath_image);
	}

	/**
	 * @param filePath 要读取的pdf文件路径
	 * @param filePath_out 任意的一个空的pdf文件
	 * @param filePath_image 保存图片的pdfImage路径 保存路径为${filePath_image}+结尾+后缀名
	 * x250-2
	 */
	private static void readPdfImages(String filePath, String filePath_out, String filePath_image)
	{
		//待解析的pdf
		File pdfFile = new File(filePath);
		//空白PDF，用来存储文件
		File pdfPath_out = new File(filePath_out);
		PDDocument document = null;
		PDDocument document_out = null;
		try
		{
			if (!pdfFile.exists())
			{
				throw new RuntimeException("待读取的PDF文件不存在，停止读取");
			}
			if (!pdfPath_out.exists())
			{
				Document doc = new Document();
				PdfWriter.getInstance(doc, new FileOutputStream(pdfPath_out));
				doc.addTitle("空白PDF");
				doc.addAuthor("赵玉柱");
				doc.open();
				doc.add(new Paragraph(" "));
				doc.close();
			}
			document = PDDocument.load(pdfFile);
			document_out = PDDocument.load(pdfPath_out);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		int pages = document.getNumberOfPages();
		float j = 1f;
		for (int i = 0; i < pages; i++)
		{
			PDPage page = document.getPage(i);
			PDPage page_out = document_out.getPage(0);
			PDResources resources = page.getResources();
			Iterable xobject = resources.getXObjectNames();
			if (xobject != null)
			{
				Iterator iterator = xobject.iterator();
				while (iterator.hasNext())
				{
					COSName key = (COSName) iterator.next();
					if (resources.isImageXObject(key))
					{
						try
						{
							PDImageXObject image = (PDImageXObject) resources.getXObject(key);
							//方式1，将pdf中的图片，分别另存为pdf文档
							/*PDPageContentStream contentStream = new PDPageContentStream(document_out, page_out, AppendMode.APPEND, true);
							float scale = 1f;
							contentStream.drawImage(image, 20, 20, image.getWidth(), image.getHeight());
							contentStream.close();
							document_out.save(filePath_image + j + ".pdf");
							System.out.println(image.getSuffix() + "--" + image.getWidth() + "--" + image.getHeight());
							*/
							//方式2，将PDF中的图片，另存为图片，这种方式保存为文件格式有问题
							File file = new File(filePath_image + j + ".jpg");
							FileOutputStream outputStream = new FileOutputStream(file);
							InputStream input = image.createInputStream();
							int byteCount = 0;
							byte[] bytes = new byte[1024];
							while ((byteCount = input.read(bytes)) > 0)
							{
								outputStream.write(bytes, 0, byteCount);
							}
							input.close();
							outputStream.close();
							j++;
							System.out.println(j);
						} catch (Exception e)
						{
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	/**
	 * @param filePath 要读取的pdf存在的路径
	 * x250-2
	 */
	private static void pdfReader(String filePath)
	{
		File pdfFile = new File(filePath);
		PDDocument document = null;
		try
		{
			//方式1
			/*
			InputStream input = null;
			input = new FileInputStream(pdfFile);
			PDFParser parse = new PDFParser(new RandomAccessBuffer(input));
			parse.parse();
			document = parse.getPDDocument();
			*/
			//方式2
			document = PDDocument.load(pdfFile);
			//逻辑处理
			//获取页码
			int pages = document.getNumberOfPages();
			System.out.println("页码是" + pages);
			//读取pdf内容
			PDFTextStripper stripper = new PDFTextStripper();
			//设置顺序读取
			stripper.setSortByPosition(true);
			//设置起始页
			stripper.setStartPage(1);
			//设置终止页
			stripper.setEndPage(pages);
			String content = stripper.getText(document);
			System.out.println(content);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
