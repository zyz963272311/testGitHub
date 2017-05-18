package test;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年2月16日 下午6:57:37</p>
 * <p>类全名：test.TestPoi</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestPoi
{
	public static void main(String[] args)
	{
		OutputStream stream = null;
		BufferedImage bufferImg = null;
		try
		{
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet();
			HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
			HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 255, 255, (short) 1, 1, (short) 4, 4);
			anchor.setAnchorType(3);
			ByteArrayOutputStream bateArrayOut = new ByteArrayOutputStream();
			bufferImg = ImageIO.read(new File("d://textQRCode.jpg"));
			ImageIO.write(bufferImg, "jpg", bateArrayOut);
			patriarch.createPicture(anchor, wb.addPicture(bateArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
			stream = new FileOutputStream(new File("d://test_poi_image.xls"));
			wb.write(stream);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
