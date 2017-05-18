package test;

import itext.AlternatingBackground;
import itext.IImage;
import itext.ITable;
import itext.ITextPDF;
import java.io.IOException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
/**
 * <p>标题： @TODO</p>
 * <p>功能： </p>
 * <p>所属模块： @TODO</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年12月23日 下午12:32:38</p>
 * <p>类全名：test.TestIText</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestIText
{
	public static void main(String[] args)
	{
		ITable itable = new ITable(new float[] { 1, 1, 1 });
		PdfPCell cell = new PdfPCell(new Paragraph("aaaa"));
		ITextPDF pdf = null;
		try
		{
			pdf = new ITextPDF("d://textIText.pdf");
			pdf.open();
			pdf.add(new Paragraph("321321321"));
			itable.addCell("abc");
			itable.addCell("ghi");
			itable.addCell("ghi");
			itable.addCell("ghi", 1, 3);
			itable.addCell("abc");
			itable.addCell("ghi");
			itable.addCell("ghi");
			itable.addCell("ghi", 1, 3);
			itable.setTableStyle(new AlternatingBackground());
			pdf.add(itable.getTable());
			IImage iImage = new IImage("D://2.jpg");
			pdf.add(iImage.getImage());
		} catch (IOException e)
		{
			throw new RuntimeException(e.getMessage());
		} finally
		{
			if (pdf.isOpen())
			{
				pdf.close();
			}
		}
	}
}
