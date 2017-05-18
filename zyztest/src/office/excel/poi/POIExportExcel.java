package office.excel.poi;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFHyperlink;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
/**
 * <p>标题： 利用apache的poi.jar包导出excel文件(*.xls  1997-2003 的兼容版本)</p>
 * <p>功能： 利用apache的poi.jar包导出excel文件(*.xls  1997-2003 的兼容版本)</p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年2月7日 上午10:59:10</p>
 * <p>类全名：office.excel.poi.POIExportExcel</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class POIExportExcel
{
	/**
	 * @param args
	 * @author 赵玉柱
	 */
	public static void main(String[] args)
	{
		//设置表格的列名
		String[] title = { "id", "name", "age" };
		//创建工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		//创建sheet
		HSSFSheet sheet = workbook.createSheet("sheet0");
		//设置标题
		HSSFRow row_title = sheet.createRow(0);
		//设置样式
		HSSFCellStyle style = workbook.createCellStyle();
		//设置行标题
		for (int i = 0; i < title.length; i++)
		{
			HSSFCell cell = row_title.createCell(i);
			//设置列单元格的宽度(列索引,字符数*256)
			sheet.setColumnWidth(i, 20 * 256);
			//设置单元格的值
			cell.setCellValue(title[i]);
			//设置居中
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			//设置单元格高度
			row_title.setHeight((short) 300);
			//字体
			HSSFFont font = workbook.createFont();
			//设置粗体
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			//设置文字名称
			font.setFontName("仿宋_GB2312");
			//设置字体大小
			//font.setFontHeight((short) 200);
			font.setFontHeightInPoints((short) 14);
			//设置字体颜色
			font.setColor(HSSFFont.COLOR_RED);
			//将字体绑定到style中
			style.setFont(font);
			cell.setCellStyle(style);
		}
		//设置每一个单元格
		final int ROW_FROM = sheet.getLastRowNum() + 1;
		final int ROW_TO = 10;
		System.out.println("当前行数应该为2行，实际为" + ROW_FROM);
		for (int i = ROW_FROM; i < ROW_TO; i++)
		{
			//创建行
			HSSFRow row_content = sheet.createRow(i);
			//循环创建cell
			for (int j = 0; j < title.length; j++)
			{
				HSSFCell cell_content = row_content.createCell(j);
				cell_content.setCellValue("aaaaa" + i + "bbbbb" + j);
				HSSFCellStyle cell_style = cell_content.getCellStyle();
				cell_style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
				//字体
				HSSFFont font = workbook.createFont();
				//设置粗体
				//font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				//设置文字名称
				font.setFontName("仿宋_GB2312");
				//设置字体大小
				font.setFontHeightInPoints((short) 12);
				//将字体绑定到style中
				cell_style.setFont(font);
			}
		}
		HSSFRow row = sheet.createRow(ROW_TO);
		HSSFCell cellA11 = row.createCell(0);
		cellA11.setCellValue(10);
		HSSFCell cellB11 = row.createCell(1);
		cellB11.setCellValue(20);
		HSSFCell cellC11 = row.createCell(2);
		cellC11.setCellType(HSSFCell.CELL_TYPE_FORMULA);
		cellC11.setCellFormula("SUM(A11:A12)");
		//设置单元格格式
		HSSFRow row12 = sheet.createRow(ROW_TO + 1);
		row12.createCell(0).setCellValue("日期");
		row12.createCell(1).setCellValue("两位小数");
		row12.createCell(2).setCellValue("货币");
		row12.createCell(3).setCellValue("百分比");
		row12.createCell(4).setCellValue("中文大写");
		row12.createCell(5).setCellValue("科学计数");
		HSSFRow row13 = sheet.createRow(ROW_TO + 2);
		//日期
		HSSFDataFormat format = workbook.createDataFormat();
		HSSFCell cellA13 = row13.createCell(0);
		cellA13.setCellValue(new Date());
		HSSFCellStyle styleA13 = workbook.createCellStyle();
		styleA13.setRotation((short) -90);
		styleA13.setDataFormat(format.getFormat("yyyy年mm月dd日"));
		cellA13.setCellStyle(styleA13);
		//两位小数
		HSSFCell cellB13 = row13.createCell(1);
		cellB13.setCellValue("132323.4");
		HSSFCellStyle styleB13 = workbook.createCellStyle();
		//styleB13.setDataFormat(format.getFormat("0.00"));
		styleB13.setAlignment(HSSFCellStyle.ALIGN_JUSTIFY);
		//styleB13.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
		cellB13.setCellStyle(styleB13);
		//货币
		HSSFCell cellC13 = row13.createCell(2);
		cellC13.setCellValue(123456789);
		HSSFCellStyle styleC13 = workbook.createCellStyle();
		styleC13.setDataFormat(format.getFormat("￥#,##0"));
		cellC13.setCellStyle(styleC13);
		//百分比
		HSSFCell cellD13 = row13.createCell(3);
		HSSFCellStyle styleD13 = workbook.createCellStyle();
		styleD13.setDataFormat(format.getFormat("0.00%"));
		cellD13.setCellStyle(styleD13);
		cellD13.setCellValue(0.4);
		//转汉字大写
		HSSFCell cellE13 = row13.createCell(4);
		HSSFCellStyle styleE13 = workbook.createCellStyle();
		styleE13.setDataFormat(format.getFormat("[DbNum2][$-804]0"));
		cellE13.setCellStyle(styleE13);
		cellE13.setCellValue(20000);
		//科学计数法
		HSSFCell cellF13 = row13.createCell(5);
		HSSFCellStyle styleF13 = workbook.createCellStyle();
		styleF13.setDataFormat(format.getFormat("0.00E+00"));
		cellF13.setCellValue(200000);
		HSSFRow row14 = sheet.createRow(13);
		HSSFCell cellA14 = row14.createCell(0);
		cellA14.setCellType(HSSFCell.CELL_TYPE_BLANK);
		cellA14.setCellValue(false);
		HSSFCell cellB14 = row14.createCell(1);
		cellB14.setCellType(HSSFCell.CELL_TYPE_BOOLEAN);
		//cellB14.setCellValue("真假");
		HSSFCell cellC14 = row14.createCell(2);
		cellC14.setCellType(HSSFCell.CELL_TYPE_ERROR);
		//Error Value can only be 0,7,15,23,29,36 or 42
		cellC14.setCellErrorValue((byte) 42);
		HSSFHyperlink link = new HSSFHyperlink(HSSFHyperlink.LINK_URL);
		link.setAddress("http://www.baidu.com");
		cellC11.setHyperlink(link);
		//cellC14.setCellValue(12);
		HSSFCell cellD14 = row14.createCell(3);
		cellD14.setCellType(HSSFCell.CELL_TYPE_FORMULA);
		//cellD14.setCellValue("公式");
		HSSFCell cellE14 = row14.createCell(4);
		cellE14.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		//cellE14.setCellValue("数值");
		HSSFCell cellF14 = row14.createCell(5);
		cellF14.setCellType(HSSFCell.CELL_TYPE_STRING);
		//cellF14.setCellValue("字符串");
		//创建一个合并单元格的样式Region的参数是(从哪行，(short)从哪列，到哪行，(short)到哪列)
		sheet.addMergedRegion(new org.apache.poi.hssf.util.Region(2, (short) 2, 4, (short) 4));
		//创建xls文件
		File file = new File("D://poi_test_exp_excel.xls");
		try
		{
			//创建文件输出流
			FileOutputStream stream = new FileOutputStream(file);
			workbook.write(stream);
			stream.close();
		} catch (Exception e)
		{
			throw new RuntimeException("报错内容", e);
		}
	}
}
