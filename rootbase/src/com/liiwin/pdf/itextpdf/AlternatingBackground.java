package com.liiwin.pdf.itextpdf;

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
 * <p>类全名：com.liiwin.pdf.itextpdf.AlternatingBackground</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class AlternatingBackground implements PdfPTableEvent
{
	private BaseColor[]	baseColors;

	/**
	 * 一个默认的隔行显示颜色的构造方法
	 */
	public AlternatingBackground()
	{
		this(null);
	}

	/**
	 * 构造方法，设置隔行显示颜色,若baseColors.length=1,表示不设置隔行换色
	 * @param baseColors
	 */
	public AlternatingBackground(BaseColor[] baseColors)
	{
		if (baseColors != null && baseColors.length >= 1)
		{
			this.baseColors = baseColors;
		} else
		{
			baseColors = new BaseColor[] { new BaseColor(235, 235, 235), new BaseColor(255, 255, 255) };
		}
	}

	@Override
	public void tableLayout(PdfPTable table, float[][] widths, float[] heights, int headerRows, int rowStart, PdfContentByte[] canvases)
	{
		int columns;
		Rectangle rect;
		int colorLength = baseColors.length;
		//合适的颜色：（235，235，235）
		int footer = widths.length - table.getFooterRows();
		int header = table.getHeaderRows() - table.getFooterRows() + 1;
		for (int row = header; row < footer; row++)
		{
			columns = widths[row].length - 1;
			rect = new Rectangle(widths[row][0], heights[row], widths[row][columns], heights[row + 1]);
			rect.setBackgroundColor(baseColors[row % colorLength]);
			rect.setBorder(Rectangle.NO_BORDER);
			canvases[PdfPTable.BASECANVAS].rectangle(rect);
		}
	}
}
