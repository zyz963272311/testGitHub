package com.liiwin.file.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import com.liiwin.utils.StrUtil;
public class FileZipUtil
{
	/**
	 * 将文件列表srcFile 打包成zip文件到zipFilePath下的fileName文件中
	 * @param srcFile
	 * @param zipFilePath
	 * @param fileName
	 * @return
	 */
	public static boolean file2Zip(String[] srcFile, String zipFilePath, String fileName)
	{
		boolean flag = false;
		if (StrUtil.isNullIn(true, srcFile))
		{
			return flag;
		}
		OutputStream os = null;
		ZipOutputStream zos = null;
		try
		{
			String path = (StrUtil.isStrTrimNull(zipFilePath) ? "" : (zipFilePath + "/")) + fileName;
			os = new BufferedOutputStream(new FileOutputStream(path));
			zos = new ZipOutputStream(os, Charset.forName("GBK"));
			byte[] buffer = new byte[8192];
			int len = 0;
			for (int i = 0; i < srcFile.length; i++)
			{
				File file = new File(srcFile[i]);
				if (!file.isFile())
				{
					directory2Zip(zos, new String[] { file.getAbsolutePath() }, zipFilePath, fileName);
				} else
				{
					ZipEntry ze = new ZipEntry(file.getName());
					zos.putNextEntry(ze);
					BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
					while ((len = bis.read(buffer)) != -1)
					{
						zos.write(buffer, 0, len);
						zos.flush();
					}
					zos.closeEntry();
					bis.close();
				}
				zos.closeEntry();
			}
			flag = true;
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally
		{
			if (zos != null)
			{
				try
				{
					zos.close();
				} catch (Exception e)
				{
				}
			}
			if (os != null)
			{
				try
				{
					os.close();
				} catch (Exception e)
				{
				}
			}
		}
		return flag;
	}

	/**
	 * 将文件夹列表打包到zipPath下的zipName文件中
	 * @param srcDirs
	 * @param zipPath
	 * @param zipName
	 * @return
	 */
	public static boolean directory2Zip(String[] srcDirs, String zipPath, String zipName)
	{
		boolean flag = false;
		try
		{
			ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipPath + "/" + zipName), Charset.forName("GBK"));
			flag = directory2Zip(zos, srcDirs, zipPath, zipName);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return flag;
	}

	/**
	 * 将文件夹列表中的文件和文件夹打包到zos中，如果zos问空，则打包到zipPath下的zipName压缩文件中
	 * @param zos
	 * @param srcDirs
	 * @param zipPath
	 * @param zipName
	 * @return
	 */
	public static boolean directory2Zip(ZipOutputStream zos, String[] srcDirs, String zipPath, String zipName)
	{
		return directory2Zip(zos, srcDirs, "", zipPath, zipName);
	}

	/**
	 * 将文件夹列表srcDirs下的文件和文件夹打包到zos，如果zos为空，则打包到zipPath下的zipName文件下的entry节点下
	 * @param zos
	 * @param srcDirs
	 * @param entryName
	 * @param zipPath
	 * @param zipName
	 * @return
	 */
	public static boolean directory2Zip(ZipOutputStream zos, String[] srcDirs, String entryName, String zipPath, String zipName)
	{
		boolean flag = false;
		if (StrUtil.isNullIn(true, srcDirs))
		{
			return false;
		}
		boolean needClose = false;
		if (zos == null)
		{
			needClose = true;
			try
			{
				String path = (StrUtil.isStrTrimNull(zipPath) ? "" : (zipPath + "/")) + zipName;
				zos = new ZipOutputStream(new FileOutputStream(path), Charset.forName("GBK"));
			} catch (FileNotFoundException e)
			{
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		try
		{
			for (int i = 0; i < srcDirs.length; i++)
			{
				File dir = new File(srcDirs[i]);
				if (StrUtil.isStrTrimNull(entryName))
				{
					entryName = dir.getName();
				}
				if (dir.isDirectory())
				{
					zos.putNextEntry(new ZipEntry(entryName + "/"));
					File[] files = dir.listFiles();
					for (File file : files)
					{
						if (file.isDirectory())
						{
							String entryNameTemp = entryName + "/" + file.getName();
							directory2Zip(zos, new String[] { file.getAbsolutePath() }, entryNameTemp, zipPath, zipName);
						} else
						{
							FileInputStream fis = new FileInputStream(file);
							zos.putNextEntry(new ZipEntry(entryName + "/" + file.getName()));
							int len = 0;
							byte[] buffer = new byte[8192];
							while ((len = fis.read(buffer)) != -1)
							{
								zos.write(buffer, 0, len);
								zos.flush();
							}
							fis.close();
						}
					}
				}
			}
			if (needClose)
			{
				zos.close();
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return flag;
	}

	/**
	 * 将zip文件解压缩为文件或目录
	 * @param zipName
	 * @param dirName
	 * @return
	 * 赵玉柱
	 */
	public static boolean zip2Dir(String zipName, String dirName)
	{
		boolean flag = false;
		if (StrUtil.isNullIn(true, zipName, dirName))
		{
			return false;
		}
		try
		{
			ZipInputStream zis = new ZipInputStream(new FileInputStream(zipName), Charset.forName("GBK"));
			BufferedInputStream bin = new BufferedInputStream(zis);
			File file;
			ZipEntry ze;
			while ((ze = zis.getNextEntry()) != null)
			{
				file = new File(new File(dirName), ze.getName());
				if (!file.exists())
				{
					new File(file.getParent()).mkdir();
				}
				if (!ze.isDirectory())
				{
					FileOutputStream fos = new FileOutputStream(file);
					BufferedOutputStream bos = new BufferedOutputStream(fos);
					int len = 0;
					byte[] buffer = new byte[8192];
					while ((len = bin.read(buffer)) != -1)
					{
						bos.write(buffer, 0, len);
						bos.flush();
					}
					bos.close();
					fos.close();
				}
			}
			bin.close();
			zis.close();
			flag = true;
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return flag;
	}

	public static void main(String[] args)
	{
		testFile2Zip();
		String zipName = "D:" + "/" + "临时.zip";
		String dirName = "D:" + "/" + "临时";
		boolean flag = zip2Dir(zipName, dirName);
		if (!flag)
		{
			System.out.println("文件解压失败");
		}
	}

	private static void testFile2Zip()
	{
		String[] srcFile = new String[4];
		srcFile[0] = "D:" + "/" + "aaa.pdf";
		srcFile[1] = "D:" + "/" + "cun.txt";
		srcFile[2] = "D:" + "/" + "test_hssf.xls";
		srcFile[3] = "D:" + "/" + "临时文件夹";
		String zipFilePath = "D:";
		String fileName = "临时.zip";
		boolean flag = file2Zip(srcFile, zipFilePath, fileName);
		if (!flag)
		{
			System.out.println("文件下载失败");
		}
	}
}
