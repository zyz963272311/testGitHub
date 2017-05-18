package itext;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPTableEvent;
import com.itextpdf.text.pdf.PdfWriter;
/**
 * <p>标题：测试itext的jar包打印pdf文件 </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年12月21日 下午4:48:42</p>
 * <p>类全名：itext.ITextTest</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class ITextTest
{
	/**
	 * 步骤：
	 * 1.获得Document对象（可以自定义设置大小，用Rectangle类，默认大小为A4）
	 * 2.为Document对象创建监听并把PDF流写入到文件中
	 * 3.Document对象调用open函数，打开对象
	 * 4.Document调用add方法将内容添加到文档
	 * 5.Document调用cloce函数，关闭对象，同时PDFWriter生成PDF
	 * @author 赵玉柱
	 */
	public static void main(String[] args)
	{
		//打印文字，打印中文需要添加相同版本的asian(亚洲语言)jar包
		printText();
		//打印图片
		printImage();
		//打印表格
		printTable();
		//打印图片，按照二进制流
		printImageByBytes();
		//打印隔行换色的table
		try
		{
			printTableColor();
			printTableAlign();
		} catch (Exception e)
		{
			System.out.println("打印出错" + e.getMessage());
		}
	}

	public static void printTableAlign() throws Exception
	{
		Document document = new Document(PageSize.A4);
		//设置存放位置
		PdfWriter.getInstance(document, new FileOutputStream("D://1.pdf"));
		document.open();
		//生成三列表格
		PdfPTable table = new PdfPTable(3);
		//设置表格具体宽度
		table.setTotalWidth(90);
		//设置每一列所占的长度
		table.setWidths(new float[] { 50f, 15f, 25f });
		PdfPCell cell1 = new PdfPCell(new Paragraph("midcell"));
		cell1.setUseAscender(true);
		cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直方向居中，对应的还有TOP与BOTTOM
		cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);//设置水平方向居右，对应的还有LEFT与CENTER
		cell1.setMinimumHeight(100);
		table.addCell(cell1);
		table.addCell(new PdfPCell(new Phrase("無幽之路IText教程")));
		table.addCell(new PdfPCell(new Phrase("無幽之路IText教程")));
		document.add(table);
		document.close();
	}

	/**
	 * 按照二进制流的方式打印图片到pdf
	 * @author 赵玉柱
	 */
	public static void printImageByBytes()
	{
		byte[] imageByte = getImageBytes();
		Document document = new Document();
		try
		{
			PdfWriter.getInstance(document, new FileOutputStream("D://testImageByte.pdf"));
			document.open();
			document.add(new Paragraph("hello world"));
			Image image1 = Image.getInstance(imageByte);
			document.add(image1);
			System.out.println("按照二进制数组打印图片");
		} catch (Exception e)
		{
		} finally
		{
			if (document.isOpen())
			{
				document.close();
			}
		}
	}

	/**
	 * 方法用于打印文字
	 * 
	 *@author 赵玉柱
	 */
	public static void printText()
	{
		Rectangle rectangle = new Rectangle(500, 700);//设置内容大小
		Document document = new Document(rectangle, 100, 50, 50, 50);//设置页边距
		try
		{
			PdfWriter write = PdfWriter.getInstance(document, new FileOutputStream("d://test0000001.pdf"));
			document.addTitle("标题可以设置");
			document.open();
			PdfContentByte canvas = write.getDirectContentUnder();
			document.add(new Paragraph("hello world"));
			document.add(new Paragraph("hello world"));
			write.setCompressionLevel(0);
			canvas.saveState(); //q
			canvas.beginText(); //bt
			canvas.moveText(100, 200);
			canvas.setFontAndSize(BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED), 20);
			canvas.showText("你好");
			canvas.endText();
			canvas.restoreState();
		} catch (FileNotFoundException | DocumentException e)
		{
			System.out.println("错误信息" + e.getMessage());
		} catch (IOException e)
		{
			throw new RuntimeException("报错内容", e);
		} finally
		{
			if (document.isOpen())
			{
				System.out.println("将要关闭Document");
				document.close();
			}
		}
	}

	/**
	 * 方法用于打印页眉，页脚等
	 * 
	 * @author 赵玉柱
	 */
	public static void printElement()
	{
	}

	public static void printImage()
	{
		Document document = new Document(PageSize.A4);
		try
		{
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D://testImage.pdf"));
			writer.setStrictImageSequence(true);
			document.open();
			//设置背景开始，必须放在写内容的最上面，否则将被覆盖
			Image image1 = Image.getInstance("D://2.jpg");
			image1.setAlignment(Image.UNDERLYING);
			image1.setAbsolutePosition(0, 0);//左下角为起点
			image1.scaleAbsolute(PageSize.A4);
			document.add(image1);
			//设置背景结束
			Image image2 = Image.getInstance("D://1.jpg");
			document.add(image2);
			document.add(new Paragraph("this is 1st image"));
			System.out.println("没有出现异常");
		} catch (Exception e)
		{
			System.out.println("出现异常" + e.getMessage());
		} finally
		{
			if (document.isOpen())
			{
				document.close();
			}
		}
	}

	/**
	 * 为PDF设置北京颜色
	 * @author 赵玉柱 
	 */
	public static void printBackground()
	{
		Document document = new Document();
		try
		{
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D://testImage.pdf"));
			writer.setStrictImageSequence(true);
			document.open();
			document.add(new Paragraph("this is 1st image"));
			Image image1 = Image.getInstance("D://1.jpg");
			document.add(image1);
			System.out.println("没有出现异常");
		} catch (Exception e)
		{
			System.out.println("出现异常" + e.getMessage());
		} finally
		{
			if (document.isOpen())
			{
				document.close();
			}
		}
	}

	public static void printTable()
	{
		Rectangle pageSize = new Rectangle(PageSize.A4);
		Document document = new Document(pageSize);
		try
		{
			Font font = new Font(//
					BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED),// 
					24, Font.BOLD, BaseColor.RED);
			PdfWriter.getInstance(document, new FileOutputStream("D://testTable.pdf"));
			document.open();
			PdfPTable table = new PdfPTable(3);
			document.add(new Paragraph("tak me as tatle"));
			document.add(new Paragraph("tak me as tatle"));
			document.add(new Paragraph("tak me as tatle"));
			document.add(new Paragraph("tak me as tatle"));
			document.add(new Paragraph("tak me as tatle"));
			Paragraph par = new Paragraph("来个中文", font);
			par.setAlignment(Element.ALIGN_CENTER);
			document.add(par);
			table.setWidthPercentage(288 / 5.23f);
			table.setWidths(new int[] { 2, 1, 1 });
			PdfPCell cell = new PdfPCell(new Phrase("TABLE 1"));
			cell.setColspan(3);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase("cell with rows 2"));
			cell.setRowspan(2);
			table.addCell(cell);
			table.addCell("row 1; cell 1");
			table.addCell("row 1; cell 2");
			table.addCell("row 2; cell 1");
			table.addCell("row 2; cell 2");
			table.setSpacingBefore(5);
			table.setSpacingAfter(5);
			document.add(table);
		} catch (Exception e)
		{
		} finally
		{
			if (document.isOpen())
			{
				document.close();
			}
		}
	}

	public static byte[] getImageBytes()
	{
		byte[] imageBytes = null;
		InputStream is = null;
		ByteArrayOutputStream bos = null;
		try
		{
			System.out.println("读取文件开始");
			File image = new File("D://2.jpg");
			is = new FileInputStream(image);
			bos = new ByteArrayOutputStream(1024);
			byte[] b = new byte[1024];
			int n;
			while ((n = is.read(b)) != -1)
			{
				bos.write(b, 0, n);
			}
			is.close();
			bos.close();
			imageBytes = bos.toByteArray();
		} catch (Exception e)
		{
			System.out.println("读取文件异常" + e.getMessage());
		} finally
		{
		}
		return imageBytes;
	}

	public static void printTableColor() throws IOException, DocumentException
	{
		FileOutputStream outr = null;// 创建输出流
		// 生成随机数
		BaseFont baseFontChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font fontChinese = new Font(baseFontChinese, 12, Font.NORMAL);
		// step 1
		Document document = new Document();
		// step 2
		try
		{
			PdfWriter.getInstance(document, new FileOutputStream("D://testTableColor.pdf"));
			// step 3
			document.open();
			// step 4
			float[] widths = new float[] { 0.25f, 0.25f, 0.5f };
			PdfPTable table = new PdfPTable(widths);
			// the cell object
			PdfPCell cell;
			// we add a cell with colspan3
			cell = new PdfPCell(new Phrase("Cell with colspan 3"));
			cell.setColspan(3);
			table.addCell(cell);
			// now we add a cell with rowspan2
			cell = new PdfPCell(new Phrase("Cell with rowspan 2 跨行", fontChinese));
			cell.setRowspan(2);
			// cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setMinimumHeight(100);//设置单元行高
			table.addCell(cell);
			//			PdfPRow row = new PdfPRow(new PdfPCell[] { cell });
			// we add the four remaining cells with addCell()
			table.addCell("row 1-1; cell 1");
			table.addCell("row 1-2; cell 2");
			cell = new PdfPCell(new Phrase("row 2-1; cell 1"));
			cell.setMinimumHeight(100);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			//table.addCell("row 2-1; cell 1");
			table.addCell(cell);
			table.addCell("row 2-2; cell 2");
			Paragraph para = new Paragraph("text");
			para.setAlignment(1);
			cell = new PdfPCell(new Phrase(para));
			cell.setMinimumHeight(100);
			table.addCell(cell);
			//table.addCell("row 3-1; cell 1");
			table.addCell("row 3-2; cell 2");
			table.addCell("row 3-3; cell 3");
			table.addCell("row 4-1; cell 1");
			table.addCell("row 4-2; cell 2");
			table.addCell("row 4-3; cell 3");
			table.addCell("row 5-1; cell 1");
			table.addCell("row 5-2; cell 2");
			table.addCell("row 5-3; cell 3");
			table.addCell("row 6-1; cell 1");
			table.addCell("row 6-2; cell 2");
			table.addCell("row 6-3; cell 3");
			table.addCell("row 7-1; cell 1");
			table.addCell("row 7-2; cell 2");
			table.addCell("row 7-3; cell 3");
			table.addCell("row 8-1; cell 1");
			table.addCell("row 8-2; cell 2");
			table.addCell("row 8-3; cell 3");
			table.addCell("row 9-1; cell 1");
			table.addCell("row 9-2; cell 2");
			table.addCell("row 9-3; cell 3");
			//加入隔行换色事件
			PdfPTableEvent event = new AlternatingBackground();
			table.setTableEvent(event);
			//end
			document.add(table);
			document.close();
		} catch (DocumentException e1)
		{
			e1.printStackTrace();
		} finally
		{
			if (outr != null)
			{
				outr.close();
			}
			//			String cmd = "\"C:\\Program Files\\Foxit Software\\Foxit Reader\\Foxit Reader.exe\" ";// 这个是命令行打开福昕阅读器，我的福昕安装路径，你可以仿造我的写一个。
			//			System.out.println(cmd + "\"" + fileDesc + "\"" + " -n");
			//			Runtime.getRuntime().exec(cmd + "\"" + fileDesc + "\"" + " -n");
			//			// 打开pdf
		}
	}
}
