package qrcode;

import java.awt.image.BufferedImage;
import jp.sourceforge.qrcode.data.QRCodeImage;
/**
 * <p>标题： CodeImage</p>
 * <p>功能： </p>
 * <p>所属模块： ICIP/PSP/AQSIQ</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年12月27日 下午2:10:15</p>
 * <p>类全名：qrcode.CodeImage</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class CodeImage implements QRCodeImage
{
	BufferedImage	bufImg;

	public CodeImage(BufferedImage bufImg)
	{
		this.bufImg = bufImg;
	}

	@Override
	public int getPixel(int x, int y)
	{
		return bufImg.getRGB(x, y);
	}

	@Override
	public int getHeight()
	{
		return bufImg.getHeight();
	}

	@Override
	public int getWidth()
	{
		return bufImg.getWidth();
	}
}
