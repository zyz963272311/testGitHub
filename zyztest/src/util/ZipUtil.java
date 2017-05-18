package util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年5月16日 下午12:29:49</p>
 * <p>类全名：util.ZipUtil</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class ZipUtil
{
	private ZipUtil()
	{
		// empty
	}

	/**
	 * 压缩文件
	 * 
	 * @param filePath
	 *            待压缩的文件路径
	 * @return 压缩后的文件
	 */
	public static File zip(String filePath)
	{
		File target = null;
		File source = new File(filePath);
		System.out.println(source.getParent());
		if (source.exists())
		{
			// 压缩文件名=源文件名[去掉后缀名].zip
			String zipName = FileUtil.getNamePart(source.getName()) + ".zip";
			target = new File(source.getParent(), zipName);
			if (target.exists())
			{
				target.delete(); // 删除旧的文件
			}
			FileOutputStream fos = null;
			ZipOutputStream zos = null;
			try
			{
				fos = new FileOutputStream(target);
				zos = new ZipOutputStream(new BufferedOutputStream(fos));
				// 添加对应的文件Entry
				addEntry("/", source, zos);
			} catch (IOException e)
			{
				throw new RuntimeException(e);
			} finally
			{
				IOUtil.closeQuietly(zos, fos);
			}
		}
		return target;
	}

	/**
	 * 扫描添加文件Entry
	 * 
	 * @param base
	 *            基路径
	 * 
	 * @param source
	 *            源文件
	 * @param zos
	 *            Zip文件输出流
	 * @throws IOException
	 */
	private static void addEntry(String base, File source, ZipOutputStream zos) throws IOException
	{
		// 按目录分级，形如：/aaa/bbb.txt
		String entry = base + source.getName();
		if (source.isDirectory())
		{
			for (File file : source.listFiles())
			{
				// 递归列出目录下的所有文件，添加文件Entry
				addEntry(entry + "/", file, zos);
			}
		} else
		{
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			try
			{
				byte[] buffer = new byte[1024 * 10];
				fis = new FileInputStream(source);
				bis = new BufferedInputStream(fis, buffer.length);
				int read = 0;
				zos.putNextEntry(new ZipEntry(entry));
				while ((read = bis.read(buffer, 0, buffer.length)) != -1)
				{
					zos.write(buffer, 0, read);
				}
				zos.closeEntry();
			} finally
			{
				IOUtil.closeQuietly(bis, fis);
			}
		}
	}

	/**
	 * 解压文件
	 * 
	 * @param filePath
	 *            压缩文件路径
	 */
	public static void unzip(String filePath)
	{
		File source = new File(filePath);
		if (source.exists())
		{
			ZipInputStream zis = null;
			BufferedOutputStream bos = null;
			try
			{
				zis = new ZipInputStream(new FileInputStream(source));
				ZipEntry entry = null;
				while ((entry = zis.getNextEntry()) != null && !entry.isDirectory())
				{
					File target = new File(source.getParent(), entry.getName());
					if (!target.getParentFile().exists())
					{
						// 创建文件父目录
						target.getParentFile().mkdirs();
					}
					// 写入文件
					bos = new BufferedOutputStream(new FileOutputStream(target));
					int read = 0;
					byte[] buffer = new byte[1024 * 10];
					while ((read = zis.read(buffer, 0, buffer.length)) != -1)
					{
						bos.write(buffer, 0, read);
					}
					bos.flush();
				}
				zis.closeEntry();
			} catch (IOException e)
			{
				throw new RuntimeException(e);
			} finally
			{
				IOUtil.closeQuietly(zis, bos);
			}
		}
	}

	public static void main(String[] args)
	{
		String targetPath = "D:\\MySelf\\餐巾纸.jpg";
		File file = ZipUtil.zip(targetPath);
		System.out.println(file);
		ZipUtil.unzip("D:\\MySelf\\餐巾纸.jpg.zip");
	}
}
