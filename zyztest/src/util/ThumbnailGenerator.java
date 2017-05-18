package util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年5月16日 上午11:27:06</p>
 * <p>类全名：util.ThumbnailGenerator</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class ThumbnailGenerator
{
	public void transform(String originalFile, String thumbnailFile, int thumbWidth, int thumbHeight, int quality) throws Exception
	{
		Image image = javax.imageio.ImageIO.read(new File(originalFile));
		double thumbRatio = (double) thumbWidth / (double) thumbHeight;
		int imageWidth = image.getWidth(null);
		int imageHeight = image.getHeight(null);
		double imageRatio = (double) imageWidth / (double) imageHeight;
		if (thumbRatio < imageRatio)
		{
			thumbHeight = (int) (thumbWidth / imageRatio);
		} else
		{
			thumbWidth = (int) (thumbHeight * imageRatio);
		}
		if (imageWidth < thumbWidth && imageHeight < thumbHeight)
		{
			thumbWidth = imageWidth;
			thumbHeight = imageHeight;
		} else if (imageWidth < thumbWidth)
		{
			thumbWidth = imageWidth;
		} else if (imageHeight < thumbHeight)
		{
			thumbHeight = imageHeight;
		}
		BufferedImage thumbImage = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = thumbImage.createGraphics();
		graphics2D.setBackground(Color.WHITE);
		graphics2D.setPaint(Color.WHITE);
		graphics2D.fillRect(0, 0, thumbWidth, thumbHeight);
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);
		javax.imageio.ImageIO.write(thumbImage, "JPG", new File(thumbnailFile));
	}
}
