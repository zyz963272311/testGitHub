package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年11月24日 下午5:26:45</p>
 * <p>类全名：test.TestFile</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestFile
{
	public static void main(String[] args)
	{
		//		File file = new File("user.ini");
		//		try
		//		{
		//			String cfgPath = file.getAbsolutePath().replace("\\", "/");
		//			System.out.println(cfgPath);
		//			if (!file.exists())
		//			{
		//				file.createNewFile();
		//			}
		//			file.setWritable(true);
		//			BufferedReader br = new BufferedReader(new FileReader(file));
		//			String s = null;
		//			while ((s = br.readLine()) != null)
		//			{
		//				System.out.println(s);
		//			}
		//		} catch (Exception e)
		//		{
		//			throw new RuntimeException(e.getMessage());
		//		} finally
		//		{
		//		}
		try
		{
			readGBK("user.ini");
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			throw new RuntimeException("报错内容", e);
		}
	}

	static void readGBK(String filePath) throws IOException
	{
		FileInputStream fis = new FileInputStream(new File(filePath));
		InputStreamReader isr = new InputStreamReader(fis, Charset.forName("GBK"));
		BufferedReader br = new BufferedReader(isr);
		String line = null;
		while ((line = br.readLine()) != null)
		{
			System.out.println(line);
		}
	}
}
