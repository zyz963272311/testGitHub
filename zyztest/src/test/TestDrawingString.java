package test;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 * <p>标题： TestDrawingString</p>
 * <p>功能： </p>
 * <p>所属模块： ICIP/PSP/AQSIQ</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年12月28日 下午12:14:31</p>
 * <p>类全名：test.TestDrawingString</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestDrawingString
{
	public static void main(String[] args)
	{
		BufferedImage bufImg = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bufImg.createGraphics();
		Font font = new Font("宋体", Font.BOLD, 20);
		g.setFont(font);
		g.drawString("嗯", 20, 20);
		File file = new File("D://TestDrawingString.jpg");
		try
		{
			ImageIO.write(bufImg, "jpg", file);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			throw new RuntimeException("报错内容", e);
		}
	}
}
