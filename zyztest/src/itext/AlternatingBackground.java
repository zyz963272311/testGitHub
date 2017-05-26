package itext;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPTableEvent;
/**
 * <p>标题：设置PDFPTable隔行换色 </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年12月22日 下午2:10:38</p>
 * <p>类全名：itext.AlternatingBackground</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class AlternatingBackground implements PdfPTableEvent
{
	@Override
	public void tableLayout(PdfPTable table, float[][] widths, float[] heights, int headerRows, int rowStart, PdfContentByte[] canvases)
	{
		int columns;
		Rectangle rect;
		//合适的颜色：（235，235，235）
		int footer = widths.length - table.getFooterRows();
		int header = table.getHeaderRows() - table.getFooterRows() + 1;
		for (int row = header; row < footer; row += 2)
		{
			columns = widths[row].length - 1;
			rect = new Rectangle(widths[row][0], heights[row], widths[row][columns], heights[row + 1]);
			rect.setBackgroundColor(new BaseColor(235, 235, 235));
			rect.setBorder(Rectangle.NO_BORDER);
			canvases[PdfPTable.BASECANVAS].rectangle(rect);
		}
	}
}