package itext;

import java.io.IOException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
/**
 * <p>标题：iText打印的font类 </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年12月22日 下午5:43:06</p>
 * <p>类全名：itext.IFont</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class IFont
{
	private Font	font;

	//	new Font(//
	//			BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED),// 
	//			24, Font.BOLD, BaseColor.RED);
	private IFont() throws DocumentException, IOException
	{
		this("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
	}

	private IFont(String name, String encoding, boolean embedded) throws DocumentException, IOException
	{
		font = new Font(BaseFont.createFont(name, encoding, embedded));
	}

	public void setFont(Font font)
	{
		this.font = font;
	}

	public void setFontSize(float size) throws DocumentException, IOException
	{
		if (this.font == null)
		{
			this.font = new Font(BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED));
		}
		this.font.setSize(size);
	}

	public Font getFont()
	{
		return font;
	}
}
