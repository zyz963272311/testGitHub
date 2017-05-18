package file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年9月27日 下午5:58:45</p>
 * <p>类全名：file.TXT</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TXT
{
	public static BufferedReader	bufread;
	private static String			path		= "D:/suncity.txt";
	private static File				filename	= new File(path);
	private static String			readStr		= "";

	public static void creatTxtFile() throws IOException
	{
		if (!filename.exists())
		{
			filename.createNewFile();
			System.err.println(filename + "已创建！");
		}
	}

	public static String readTxtFile()
	{
		String read;
		FileReader fileread;
		try
		{
			fileread = new FileReader(filename);
			bufread = new BufferedReader(fileread);
			try
			{
				while ((read = bufread.readLine()) != null)
				{
					readStr = readStr + read + "\r\n";
				}
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		System.out.println("文件内容是:" + "\r\n" + readStr);
		return readStr;
	}

	public static void writeTxtFile(String newStr) throws IOException
	{
		String filein = newStr + "\r\n" + readStr + "\r\n";
		RandomAccessFile mm = null;
		try
		{
			mm = new RandomAccessFile(filename, "rw");
			mm.writeBytes(filein);
		} catch (IOException e1)
		{
			e1.printStackTrace();
		} finally
		{
			if (mm != null)
			{
				try
				{
					mm.close();
				} catch (IOException e2)
				{
					e2.printStackTrace();
				}
			}
		}
	}

	public static void replaceTxtByStr(String oldStr, String replaceStr)
	{
		String temp = "";
		try
		{
			File file = new File(path);
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			StringBuffer buf = new StringBuffer();
			for (; (temp = br.readLine()) != null && !temp.equals(oldStr);)
			{
				buf = buf.append(temp);
				buf = buf.append(System.getProperty("line.separator"));
			}
			buf = buf.append(replaceStr);
			while ((temp = br.readLine()) != null)
			{
				buf = buf.append(System.getProperty("line.separator"));
				buf = buf.append(temp);
			}
			br.close();
			FileOutputStream fos = new FileOutputStream(file);
			PrintWriter pw = new PrintWriter(fos);
			pw.write(buf.toString().toCharArray());
			pw.flush();
			pw.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] s) throws IOException
	{
		TXT.creatTxtFile();
		TXT.readTxtFile();
		for (int i = 0; i < 1000; i++)
		{
			TXT.writeTxtFile("20080808:12:13");
			TXT.readTxtFile();
		}
		TXT.replaceTxtByStr("ken", "zhang");
		TXT.readTxtFile();
	}
}
