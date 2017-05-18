package itext;

import java.util.List;
import java.util.Map;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPTableEvent;
/**
 * <p>标题：iText打印模板 </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年12月22日 下午4:27:51</p>
 * <p>类全名：itext.ITable</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class ITable
{
	private PdfPTable	table;

	public ITable(float[] collWidths)
	{
		this.table = new PdfPTable(collWidths);
	}

	//table中添加一个所有其他项目都默认的单元
	public void addCell(String text)
	{
		addCell(text, 1, 1);
	}

	public void addCell(String text, int rowspans, int collspans)
	{
		addCell(text, rowspans, collspans, 0);
	}

	//table中添加一个设置单元格高度的文字单元
	public void addCell(String text, float height)
	{
		addCell(text, 1, 1, height);
	}

	public void addCell(String text, int rowspans, int collspans, float height)
	{
		PdfPCell cell = new PdfPCell(new Paragraph(text));
		cell.setColspan(collspans);
		cell.setRowspan(rowspans);
		cell.setMinimumHeight(height);
		addCell(cell);
	}

	public void addCell(String text, Font font)
	{
		addCell(text, 1, 1, font);
	}

	public void addCell(String text, int rowspans, int collspans, Font font)
	{
		addCell(text, rowspans, collspans, 0, font);
	}

	public void addCell(String text, int rowspans, int collspans, float height, Font font)
	{
		Paragraph para = new Paragraph(text, font);
		PdfPCell cell = new PdfPCell(para);
		cell.setColspan(collspans);
		cell.setRowspan(rowspans);
		cell.setMinimumHeight(height);
		addCell(cell);
	}

	public void addCell(PdfPCell cell)
	{
		volidate(this.table);
		this.table.addCell(cell);
	}

	public void addCells(List<Map<String,Object>> records)
	{
	}

	public void addCells(List<Map<String,Object>> records, Font font)
	{
	}

	public void release()
	{
		this.table = null;
	}

	public PdfPTable getTable()
	{
		volidate(this.table);
		return this.table;
	}

	public void setTableStyle(PdfPTableEvent event)
	{
		volidate(this.table);
		this.table.setTableEvent(event);
	}

	/**
	 * 判断table是否为空，如果为空，则进行初始化操作
	 * @param table
	 * @author 赵玉柱
	 */
	private void volidate(PdfPTable table)
	{
		if (table == null)
		{
			table = setTable(new float[] { 1 });
		}
	}

	/**
	 * 为table初始化
	 * @param widths
	 * @return
	 * @author 赵玉柱
	 */
	private PdfPTable setTable(float[] widths)
	{
		return new PdfPTable(widths);
	}
}
