package encrypt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
/**
 * <p>标题：文件加密解密  </p>
 * <p>功能：文件加密解密  </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年10月8日 下午3:30:43</p>
 * <p>类全名：encrypt.FileEncryptAndDecrypt</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class FileEncryptAndDecrypt
{
	/**
	 * 文件加密
	 * @param fileUrl 文件路径
	 * @param key 密钥
	 * @throws Exception
	 * x250-2
	 */
	public static void encrypt(String fileUrl, String key) throws Exception
	{
		File file = new File(fileUrl);
		String path = file.getPath();
		if (!file.exists())
		{
			return;
		}
		int index = path.lastIndexOf("\\");
		String destFile = path.substring(0, index) + "\\" + "abc";
		File dest = new File(destFile);
		InputStream in = new FileInputStream(fileUrl);
		OutputStream out = new FileOutputStream(destFile);
		byte[] buffer = new byte[1024];
		int r;
		byte[] buffer2 = new byte[1024];
		while ((r = in.read(buffer)) > 0)
		{
			for (int i = 0; i < r; i++)
			{
				byte b = buffer[i];
				buffer2[i] = b == 255 ? 0 : ++b;
			}
			out.write(buffer2, 0, r);
			out.flush();
		}
		in.close();
		out.close();
		file.delete();
		dest.renameTo(new File(fileUrl));
		appendMathodA(fileUrl, key);
		System.out.println("加密成功");
	}

	/**
	 * 文件加密实现
	 * @param fileUrl 文件路径
	 * @param key 加密密钥
	 * x250-2
	 */
	private static void appendMathodA(String fileUrl, String key)
	{
		try
		{
			//打开一个随机访问文件流，按读写方式
			RandomAccessFile randomFile = new RandomAccessFile(fileUrl, "rw");
			//文件的字节长度
			long fileLength = randomFile.length();
			//将文件读写指针移动到文件尾部
			randomFile.seek(fileLength);
			randomFile.writeBytes(key);
			randomFile.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 文件解密
	 * @param fileUrl 源文件路径
	 * @param tempUrl 临时文件
	 * @param keyLength 密钥长度
	 * @return
	 * @throws Exception
	 * x250-2
	 */
	public static String decrypt(String fileUrl, String tempUrl, int keyLength) throws Exception
	{
		File file = new File(fileUrl);
		if (!file.exists())
		{
			return null;
		}
		File dest = new File(tempUrl);
		if (!dest.getParentFile().exists())
		{
			dest.getParentFile().mkdirs();
		}
		InputStream in = new FileInputStream(fileUrl);
		OutputStream out = new FileOutputStream(tempUrl);
		byte[] buffer = new byte[1024];
		byte[] buffer2 = new byte[1024];
		byte bMax = (byte) 255;
		long size = file.length() - keyLength;
		int mod = (int) size % 1024;
		int div = (int) (size >> 10);
		int count = mod == 0 ? div : (div + 1);
		int k = 1, r;
		while ((k <= count && (r = in.read(buffer)) > 0))
		{
			if (mod != 0 && k == count)
			{
				r = mod;
			}
			for (int i = 0; i < r; i++)
			{
				byte b = buffer[i];
				buffer2[i] = b == 0 ? bMax : --b;
			}
			out.write(buffer2, 0, r);
			k++;
		}
		out.close();
		in.close();
		return tempUrl;
	}

	/**
	 * 判断文件是否被加密
	 * @param fileUrl 文件路径
	 * @param keyLength 密钥长度
	 * @return
	 * x250-2
	 */
	public static String readFileLastByte(String fileUrl, int keyLength)
	{
		File file = new File(fileUrl);
		if (!file.exists())
		{
			return null;
		}
		StringBuffer str = new StringBuffer();
		try
		{
			//打开一个随机文件流，按照读方式
			RandomAccessFile randomFile = new RandomAccessFile(fileUrl, "r");
			//文件长度，字节数
			long fileLength = randomFile.length();
			//将文件指针指向文件尾部
			for (int i = keyLength; i >= 1; i--)
			{
				randomFile.seek(fileLength - i);
				str.append((char) randomFile.read());
			}
			randomFile.close();
			return str.toString();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
