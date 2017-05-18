package test;

import qrcode.QRCode;
/**
 * <p>标题： TestQRCode</p>
 * <p>功能： </p>
 * <p>所属模块： ICIP/PSP/AQSIQ</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年12月27日 下午2:18:34</p>
 * <p>类全名：test.TestQRCode</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestQRCode
{
	//	public void encoderQRCode(String content, String imagePath)
	//	{
	//		this.encoderRQCode(content, imagePath, "png", 7);
	//	}
	//
	//	public void encoderQRCode(String content, OutputStream output)
	//	{
	//		this.encoderQRCode(content, output, "png", 7);
	//	}
	//
	//	public void encoderQRCode(String content, String imagePath, String imageType)
	//	{
	//		this.encoderRQCode(content, imagePath, imageType, 7);
	//	}
	//
	//	public void encoderQRCode(String content, OutputStream output, String imageType)
	//	{
	//		this.encoderQRCode(content, output, imageType, 7);
	//	}
	//
	//	/**
	//	 * @param content
	//	 * @param imagePath
	//	 * @param string
	//	 * @param i
	//	 * @author 赵玉柱
	//	 */
	//	private void encoderRQCode(String content, String imagePath, String imageType, int size)
	//	{
	//		try
	//		{
	//			BufferedImage bufImg = this.qRCodeCommon(content, imageType, size);
	//			File imgFile = new File(imagePath);
	//			// 生成二维码QRCode图片  
	//			ImageIO.write(bufImg, imageType, imgFile);
	//		} catch (Exception e)
	//		{
	//			e.printStackTrace();
	//		}
	//	}
	//
	//	/**
	//	 * @param content
	//	 * @param output
	//	 * @param imageType
	//	 * @param size
	//	 * @author 赵玉柱
	//	 */
	//	private void encoderQRCode(String content, OutputStream output, String imageType, int size)
	//	{
	//		try
	//		{
	//			BufferedImage bufImg = this.qRCodeCommon(content, imageType, size);
	//			ImageIO.write(bufImg, imageType, output);
	//		} catch (Exception e)
	//		{
	//			e.printStackTrace();
	//		}
	//	}
	//
	//	/**
	//	 * @param content
	//	 * @param imageType
	//	 * @param size
	//	 * @return
	//	 * @author 赵玉柱
	//	 */
	//	private BufferedImage qRCodeCommon(String content, String imageType, int size)
	//	{
	//		BufferedImage bufImage = null;
	//		try
	//		{
	//			Qrcode qrcodeHeader = new Qrcode();
	//			//设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)
	//			qrcodeHeader.setQrcodeErrorCorrect('M');
	//			qrcodeHeader.setQrcodeEncodeMode('B');
	//			//设置二维码尺寸，1-40
	//			qrcodeHeader.setQrcodeVersion(size);
	//			//获得内容的字节数组
	//			byte[] contentByte = content.getBytes("utf-8");
	//			//设置图片尺寸
	//			int imageSize = 67 + 12 * (size - 1);
	//			bufImage = new BufferedImage(imageSize, imageSize, BufferedImage.TYPE_INT_RGB);
	//			Graphics2D gs = bufImage.createGraphics();
	//			gs.setBackground(Color.WHITE);
	//			gs.clearRect(0, 0, imageSize, imageSize);
	//			gs.setColor(Color.BLACK);
	//			int picoff = 2;
	//			if (contentByte.length > 0 && contentByte.length < 800)
	//			{
	//				boolean[][] codeOut = qrcodeHeader.calQrcode(contentByte);
	//				for (int i = 0; i < codeOut.length; i++)
	//				{
	//					for (int j = 0; j < codeOut.length; j++)
	//					{
	//						if (codeOut[j][i])
	//						{
	//							gs.fillRect(j * 3 + picoff, i * 3 + picoff, 3, 3);
	//						}
	//					}
	//				}
	//			} else
	//			{
	//				throw new Exception("length 不在[0-800]之间");
	//			}
	//			gs.dispose();
	//			bufImage.flush();
	//		} catch (Exception e)
	//		{
	//			e.printStackTrace();
	//		}
	//		return bufImage;
	//	}
	public static void main(String[] args)
	{
		String imagePath = "D://textQRCode.png";
		String content = "的撒欢的绝杀的机会萨克讲话的时间啊dsadsadsa";
		QRCode handler = new QRCode();
		handler.encoderQRCode(content, imagePath, "png");
	}
}
