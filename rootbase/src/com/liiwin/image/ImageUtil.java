package com.liiwin.image;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
/**
 * <p>标题： image工具类</p>
 * <p>功能： </p>
 * <p>所属模块： rootbase->image</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年6月27日 上午10:15:43</p>
 * <p>类全名：com.liiwin.image.ImageUtil</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class ImageUtil
{
	/**
	 * 按照给定的大小进行图片的压缩 图片的宽度与高度比例不变
	 * @param resImagePath
	 * @param newImagePath
	 * @param sizeX
	 * @param sizeY
	 * 赵玉柱
	 */
	public static void compressBySize(String resImagePath, String newImagePath, int sizeX, int sizeY)
	{
		compressBySize(resImagePath, newImagePath, sizeX, sizeY, true);
	}

	/**
	 * 按照给定的大小进行图片的压缩 
	 * @param resImagePath
	 * @param newImagePath
	 * @param sizeX
	 * @param sizeY
	 * @param keepAutoAspectRatio 是否按照原图片的比例
	 * 赵玉柱
	 */
	public static void compressBySize(String resImagePath, String newImagePath, int sizeX, int sizeY, boolean keepAutoAspectRatio)
	{
		try
		{
			Thumbnails.of(resImagePath).size(sizeX, sizeY).keepAspectRatio(keepAutoAspectRatio).toFile(newImagePath);
		} catch (IOException e)
		{
			throw new RuntimeException("报错内容", e);
		}
	}

	/**
	 * 按照一个比例将图片resize，可以缩小，可以放大，但是放大后，画质不变
	 * @param resImagePath
	 * @param newImagePath
	 * @param scale
	 * 赵玉柱
	 */
	public static void compressByScale(String resImagePath, String newImagePath, double scale)
	{
		try
		{
			Thumbnails.of(resImagePath).scale(scale).toFile(newImagePath);
		} catch (IOException e)
		{
			throw new RuntimeException("报错内容", e);
		}
	}

	/**
	 * 将图片按照一定的角度进行旋转
	 * @param resImagePath
	 * @param newImagePath
	 * @param rotate
	 * 赵玉柱
	 */
	public static void rotate(String resImagePath, String newImagePath, double rotate)
	{
		scaleAndRotate(resImagePath, newImagePath, 1, rotate);
	}

	/**
	 * 将图片进行resize后进行旋转，保持宽高比例
	 * @param resImagePath
	 * @param newImagePath
	 * @param sizeX
	 * @param sizeY
	 * @param rotate
	 * 赵玉柱
	 */
	public static void sizeAndRotate(String resImagePath, String newImagePath, int sizeX, int sizeY, double rotate)
	{
		sizeAndRotate(resImagePath, newImagePath, sizeX, sizeY, rotate, true);
	}

	/**
	 * 将图片进行resize后进行旋转
	 * @param resImagePath
	 * @param newImagePath
	 * @param sizeX
	 * @param sizeY
	 * @param rotate
	 * @param keepAutoAspectRatio 是否保持宽高比例
	 * 赵玉柱 
	 */
	public static void sizeAndRotate(String resImagePath, String newImagePath, int sizeX, int sizeY, double rotate, boolean keepAutoAspectRatio)
	{
		try
		{
			Thumbnails.of(resImagePath).size(sizeX, sizeY).keepAspectRatio(keepAutoAspectRatio).rotate(rotate).toFile(newImagePath);
		} catch (IOException e)
		{
			throw new RuntimeException("报错内容", e);
		}
	}

	/**
	 * 将图片进行一定比例的缩放后旋转
	 * @param resImagePath
	 * @param newImagePath
	 * @param scale
	 * @param rotate
	 * 赵玉柱
	 */
	public static void scaleAndRotate(String resImagePath, String newImagePath, double scale, double rotate)
	{
		try
		{
			Thumbnails.of(resImagePath).scale(scale).rotate(rotate).toFile(newImagePath);
		} catch (IOException e)
		{
			throw new RuntimeException("报错内容", e);
		}
	}

	/**
	 * 在图片中间添加一个红色水印
	 * @param resImagePath
	 * @param newImagePath
	 * @param waterText
	 * 赵玉柱
	 */
	public static void watermarkText(String resImagePath, String newImagePath, String waterText)
	{
		Color waterColor = Color.red;
		watermarkText(resImagePath, newImagePath, waterText, waterColor);
	}

	/**
	 * 在图片中间添加一个宋体字号50水印
	 * @param resImagePath
	 * @param newImagePath
	 * @param waterText
	 * @param waterColor
	 * 赵玉柱
	 */
	public static void watermarkText(String resImagePath, String newImagePath, String waterText, Color waterColor)
	{
		Font font = new Font("宋体", Font.PLAIN, 50);
		watermarkText(resImagePath, newImagePath, waterText, waterColor, font);
	}

	/**
	 * 在图片中间添加一个文字水印
	 * @param resImagePath
	 * @param newImagePath
	 * @param waterText
	 * @param waterColor
	 * @param font
	 * 赵玉柱
	 */
	public static void watermarkText(String resImagePath, String newImagePath, String waterText, Color waterColor, Font font)
	{
		watermarkText(resImagePath, newImagePath, waterText, waterColor, font, -1, -1);
	}

	/**
	 * 在图片得某一位置添加水印
	 * @param resImagePath
	 * @param newImagePath
	 * @param watermarkImagePath
	 * 赵玉柱
	 */
	public static void watermarkText(String resImagePath, String newImagePath, String waterText, Color waterColor, Font font, int posX, int posY)
	{
		try (FileOutputStream outImgStream = new FileOutputStream(newImagePath);)
		{
			// 读取原图片信息  
			File srcImgFile = new File(resImagePath);
			Image srcImg = ImageIO.read(srcImgFile);
			int srcImgWidth = srcImg.getWidth(null);
			int srcImgHeight = srcImg.getHeight(null);
			// 加水印  
			BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bufImg.createGraphics();
			g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
			//Font font = new Font("Courier New", Font.PLAIN, 12);  
			//			Font font = new Font("宋体", Font.PLAIN, 50);
			g.setColor(waterColor); //根据图片的背景设置水印颜色  
			g.setFont(font);
			//			int x = srcImgWidth - getWatermarkLength(waterText, g) - 3;
			//			int y = srcImgHeight - 3;
			//int x = (srcImgWidth - getWatermarkLength(watermarkStr, g)) / 2;  
			//int y = srcImgHeight / 2;  
			if (posX < 0)
			{
				int waterLength = getWatermarkLength(waterText, g);
				posX = (srcImgWidth - waterLength) / 2;
			}
			if (posY <= 0)
			{
				int waterHeight = getWatermarkHeight(waterText, g);
				posY = (srcImgHeight + waterHeight) / 2;
			}
			g.drawString(waterText, posX, posY);
			g.dispose();
			// 输出图片  
			ImageIO.write(bufImg, "jpg", outImgStream);
			outImgStream.flush();
		} catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException("生成水印图片失败");
		}
	}

	/** 
	* 获取水印文字总长度 
	* @param waterMarkContent 水印的文字 
	* @param g 
	* @return 水印文字总长度 
	*/
	public static int getWatermarkLength(String waterMarkContent, Graphics2D g)
	{
		return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());
	}

	/**
	 * 获取水印文字高度
	 * @param waterMarkContent
	 * @param g
	 * @return
	 * 赵玉柱
	 */
	public static int getWatermarkHeight(String waterMarkContent, Graphics2D g)
	{
		return g.getFontMetrics(g.getFont()).getDescent();
	}

	/**
	 * 在图片的正中间添加一个不透明的水印
	 * @param resImagePath
	 * @param newImagePath
	 * @param watermarkImagePath 水印路径
	 * 赵玉柱
	 */
	public static void watermark(String resImagePath, String newImagePath, String watermarkImagePath)
	{
		watermark(resImagePath, newImagePath, watermarkImagePath, Positions.CENTER);
	}

	/**
	 * 在图片的某一个位置添加一个不透明的水印
	 * @param resImagePath
	 * @param newImagePath
	 * @param watermarkImagePath
	 * @param position
	 * 赵玉柱
	 */
	public static void watermark(String resImagePath, String newImagePath, String watermarkImagePath, Positions position)
	{
		watermark(resImagePath, newImagePath, watermarkImagePath, position, 1);
	}

	/**
	 * 在图片的某一个位置添加一个水印，并设置水印的透明度
	 * @param resImagePath
	 * @param newImagePath
	 * @param watermarkImagePath
	 * @param position
	 * @param transparency
	 * 赵玉柱
	 */
	public static void watermark(String resImagePath, String newImagePath, String watermarkImagePath, Positions position, float transparency)
	{
		watermark(resImagePath, newImagePath, watermarkImagePath, position, transparency, 1);
	}

	/**
	 * 在图片的某一个位置添加一个某一透明度的水印，并设置图片的输出质量
	 * @param resImagePath 原图路径
	 * @param newImagePath 新图路径
	 * @param watermarkImagePath 水印图片路径
	 * @param position 位置枚举
	 * @param transparency 透明度
	 * @param quantity 图片质量
	 * 赵玉柱
	 */
	public static void watermark(String resImagePath, String newImagePath, String watermarkImagePath, Positions position, float transparency, double quantity)
	{
		watermark(resImagePath, newImagePath, watermarkImagePath, position, transparency, quantity, 1);
	}

	/**
	 * 将图片按照一定的比例进行压缩，并在图片的某一个位置添加一个某一透明度的水印，设置图片的输出质量
	 * @param resImagePath 原图路径
	 * @param newImagePath 新图路径
	 * @param watermarkImagePath 水印图片路径
	 * @param position 位置枚举
	 * @param transparency 透明度
	 * @param quantity 输出质量
	 * @param scale 新图与原图的比例
	 * 赵玉柱
	 */
	public static void watermark(String resImagePath, String newImagePath, String watermarkImagePath, Positions position, float transparency, double quantity, double scale)
	{
		try
		{
			Thumbnails.of(resImagePath).scale(scale).watermark(position, ImageIO.read(new File(watermarkImagePath)), transparency).outputQuality(quantity).toFile(newImagePath);
		} catch (IOException e)
		{
			throw new RuntimeException("报错内容", e);
		}
	}

	/**
	 * 将图片在图片的中间部分进行裁剪出regionX，regionY大小的小图片
	 * @param resImagePath
	 * @param newImagePath
	 * @param regionX
	 * @param regionY
	 * 赵玉柱
	 */
	public static void sourceRegion(String resImagePath, String newImagePath, int regionX, int regionY)
	{
		sourceRegion(resImagePath, newImagePath, Positions.CENTER, regionX, regionY);
	}

	/**
	 * 把图片从positionX，positionY位置开始剪裁出regionX，regionY大小的新图片并输出到newImagePath上
	 * @param resImagePath
	 * @param newImagePath
	 * @param regionX
	 * @param regionY
	 * @param positionX
	 * @param positionY
	 * 赵玉柱
	 */
	public static void sourceRegion(String resImagePath, String newImagePath, int regionX, int regionY, int positionX, int positionY)
	{
		try
		{
			Thumbnails.of(resImagePath).scale(1).sourceRegion(positionX, positionY, regionX, regionY).toFile(newImagePath);
		} catch (IOException e)
		{
			throw new RuntimeException("报错内容", e);
		}
	}

	/**
	 * 将图片的某一位置剪裁regionX，regionY大小
	 * @param resImagePath
	 * @param newImagePath
	 * @param positions
	 * @param regionX
	 * @param regionY
	 * 赵玉柱
	 */
	public static void sourceRegion(String resImagePath, String newImagePath, Positions positions, int regionX, int regionY)
	{
		sourceRegion(resImagePath, newImagePath, positions, regionX, regionY, 1);
	}

	/**
	 * 将某一个图片缩放后将某一位置裁剪出regionX，regionY大小的图片
	 * @param resImagePath
	 * @param newImagePath
	 * @param positions
	 * @param regionX
	 * @param regionY
	 * @param scale
	 * 赵玉柱
	 */
	public static void sourceRegion(String resImagePath, String newImagePath, Positions positions, int regionX, int regionY, double scale)
	{
		try
		{
			Thumbnails.of(resImagePath).sourceRegion(positions, regionX, regionY).scale(scale).toFile(newImagePath);
		} catch (IOException e)
		{
			throw new RuntimeException("报错内容", e);
		}
	}

	/**
	 * 将某一图片的进行缩放一定大小后进行裁剪，缩放后保持宽高比例
	 * @param resImagePath
	 * @param newImagePath
	 * @param positions
	 * @param sizeX
	 * @param sizeY
	 * @param regionX
	 * @param regionY
	 * 赵玉柱
	 */
	public static void sourceRegion(String resImagePath, String newImagePath, Positions positions, int sizeX, int sizeY, int regionX, int regionY)
	{
		sourceRegion(resImagePath, newImagePath, positions, sizeX, sizeY, regionX, regionY, true);
	}

	/**
	 * 将某一图片的进行resize后的某一位置进行裁剪，可设置resize后是否保持宽高比
	 * @param resImagePath
	 * @param newImagePath
	 * @param positions
	 * @param sizeX
	 * @param sizeY
	 * @param regionX
	 * @param regionY
	 * @param keepAutoAspectRatio
	 * 赵玉柱
	 */
	public static void sourceRegion(String resImagePath, String newImagePath, Positions positions, int sizeX, int sizeY, int regionX, int regionY, boolean keepAutoAspectRatio)
	{
		try
		{
			Thumbnails.of(resImagePath).sourceRegion(positions, regionX, regionY).size(sizeX, sizeY).keepAspectRatio(keepAutoAspectRatio).toFile(newImagePath);
		} catch (IOException e)
		{
			throw new RuntimeException("报错内容", e);
		}
	}

	/**
	* 将图片进行另一种格式的转换并输出到新文件中
	* @param resImagePath
	* @param newImagePath
	* @param format
	* 赵玉柱
	*/
	public static void outputFormate(String resImagePath, String newImagePath, String format)
	{
		outputFormate(resImagePath, newImagePath, format, 1);
	}

	/**
	 * 将图片输出到某一图片格式的图片文件中
	 * @param resImagePath
	 * @param newImagePath
	 * @param format
	 * @param scale
	 * 赵玉柱
	 */
	public static void outputFormate(String resImagePath, String newImagePath, String format, double scale)
	{
		try
		{
			Thumbnails.of(resImagePath).scale(scale).outputFormat(format).toFile(newImagePath);
		} catch (IOException e)
		{
			throw new RuntimeException("报错内容", e);
		}
	}

	/**
	 * 将源文件输出到新文件的输出流中
	 * @param resImagePath
	 * @param newImagePath
	 * @return
	 * 赵玉柱
	 */
	public static OutputStream toOutputStream(String resImagePath, String newImagePath)
	{
		try
		{
			OutputStream os = new FileOutputStream(newImagePath);
			toOutputStream(resImagePath, newImagePath);
			return os;
		} catch (FileNotFoundException e)
		{
			throw new RuntimeException("报错内容", e);
		}
	}

	/**
	 * 将源文件输出到新文件的输出流中
	 * @param resImagePath
	 * @param newImagePath
	 * @param os
	 * 赵玉柱
	 */
	public static void toOutputStream(String resImagePath, String newImagePath, OutputStream os)
	{
		if (os == null)
		{
			try
			{
				os = new FileOutputStream(newImagePath);
				Thumbnails.of(resImagePath).scale(1).toOutputStream(os);
			} catch (FileNotFoundException e)
			{
				throw new RuntimeException("报错内容", e);
			} catch (IOException e)
			{
				throw new RuntimeException("报错内容", e);
			}
		}
	}

	/**
	 * 将图片流输出到BufferedImage中
	 * @param resImagePath
	 * @param newImagePath
	 * @return
	 * 赵玉柱
	 */
	public static BufferedImage asBufferedImage(String resImagePath, String newImagePath)
	{
		BufferedImage bufferedImage = null;
		asBufferedImage(resImagePath, newImagePath, bufferedImage);
		return bufferedImage;
	}

	/**
	 * 将图片片流输出到bufferedImage中
	 * @param resImagePath
	 * @param newImagePath
	 * @param bufferedImage
	 * 赵玉柱
	 */
	public static void asBufferedImage(String resImagePath, String newImagePath, BufferedImage bufferedImage)
	{
		try
		{
			bufferedImage = Thumbnails.of(resImagePath).scale(1).asBufferedImage();
		} catch (IOException e)
		{
			throw new RuntimeException("报错内容", e);
		}
	}

	public static void main(String[] args)
	{
		String resImagePath = "D://1.jpg";
		String newImagePath = "D://_1.jpg";
		int sizeX = 100;
		int sizeY = 100;
		double scale = 4;
		String waterText = "测试水印";
		Color waterColor = Color.red;
		Font waterFont = new Font("宋体", Font.PLAIN, 50);
		int posX = 100;
		int posY = 100;
		watermarkText(resImagePath, newImagePath, waterText, waterColor, waterFont, -1, -1);
		//		compressBySize(resImagePath, newImagePath, sizeX, sizeY, false);
		//		compressByScale(resImagePath, newImagePath, scale);
		//		double rotate = 700;
		//		String watermarkImagePath = "D://textQRCode.jpg";
		//		rotate(resImagePath, newImagePath, rotate);
		//		System.out.println(System.getProperty("user.dir"));
		//		Positions position = Positions.BOTTOM_RIGHT;
		//		watermark(resImagePath, newImagePath, watermarkImagePath, position, 1);
	}
}
