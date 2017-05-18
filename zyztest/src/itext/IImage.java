package itext;

import java.io.IOException;
import java.net.MalformedURLException;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;
/**
 * <p>标题： IImage</p>
 * <p>功能： </p>
 * <p>所属模块： ICIP/PSP/AQSIQ</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年12月27日 下午1:44:15</p>
 * <p>类全名：itext.IImage</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class IImage
{
	private final Image	image;

	public IImage(Image image)
	{
		this.image = image;
	}

	public IImage(String imagePath)
	{
		try
		{
			this.image = Image.getInstance(imagePath);
		} catch (BadElementException e)
		{
			throw new RuntimeException("报错内容", e);
		} catch (MalformedURLException e)
		{
			throw new RuntimeException("报错内容", e);
		} catch (IOException e)
		{
			throw new RuntimeException("报错内容", e);
		}
	}

	public IImage(byte[] imageByte)
	{
		try
		{
			this.image = Image.getInstance(imageByte);
		} catch (BadElementException e)
		{
			throw new RuntimeException("报错内容", e);
		} catch (MalformedURLException e)
		{
			throw new RuntimeException("报错内容", e);
		} catch (IOException e)
		{
			throw new RuntimeException("报错内容", e);
		}
	}

	public Image getImage()
	{
		return this.image;
	}
}
