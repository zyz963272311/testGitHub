package com.liiwin.file.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import com.liiwin.config.BasConfig;
import com.liiwin.encryption.MD5;
import com.liiwin.file.types.FileTypeEnum;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： 文件工具类</p>
 * <p>功能： </p>
 * <p>所属模块： rootbase</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年6月27日 下午4:52:48</p>
 * <p>类全名：com.liiwin.file.FileUtil</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class FileUtil
{
	/**
	 * 将文件读取成字符串形式
	 * @param filePath
	 * @return
	 * 赵玉柱
	 */
	public static String readFileStr(String filePath)
	{
		try
		{
			String result = "";
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String temp = "";
			while ((temp = br.readLine()) != null)
			{
				result += "\r\n" + temp;
			}
			return result;
		} catch (FileNotFoundException e)
		{
			throw new RuntimeException("报错内容", e);
		} catch (IOException e)
		{
			throw new RuntimeException("报错内容", e);
		}
	}

	/**
	 * 根据文件路径删除文件
	 * @param filePath
	 * 赵玉柱
	 */
	public static void deleteFileByPath(String filePath)
	{
		File file = new File(filePath);
		deleteFile(file);
	}

	/**
	 * 删除文件/文件夹
	 * @param file
	 * 赵玉柱
	 */
	public static void deleteFile(File file)
	{
		if (file == null)
		{
			return;
		}
		if (!file.exists())
		{
			return;
		}
		if (file.isDirectory())
		{
			File[] listFiles = file.listFiles();
			if (listFiles != null)
			{
				for (File tempFile : listFiles)
				{
					deleteFile(tempFile);
				}
			}
		}
		file.delete();
	}

	/**
	 * 若文件夹不存在则创建新文件夹
	 * @param file
	 * 赵玉柱
	 */
	public static void newDir(File file)
	{
		if (file == null)
		{
			return;
		}
		if (file.exists())
		{
			return;
		}
		File parentFile = file.getParentFile();
		if (!parentFile.exists())
		{
			newDir(parentFile);
		}
		file.mkdir();
	}

	/**
	 * 若文件不存在则创建新文件
	 * @param file
	 * 赵玉柱
	 */
	public static void newFile(File file)
	{
		try
		{
			if (file == null)
			{
				return;
			}
			if (file.exists())
			{
				return;
			}
			File parentFile = file.getParentFile();
			if (!parentFile.exists())
			{
				newDir(parentFile);
			}
			if (file.isDirectory())
			{
				file.mkdir();
			} else
			{
				file.createNewFile();
			}
		} catch (IOException e)
		{
			throw new RuntimeException("报错内容", e);
		}
	}

	/**
	 * 创建新文件，联通路径创建
	 * @param absPath
	 * 赵玉柱
	 */
	public static void newFileByPath(String absPath)
	{
		File file = new File(absPath);
		newFile(file);
	}

	/**
	 * 根据文件名获取文件后缀名
	 * @param name
	 * @return
	 * 赵玉柱
	 */
	public static String getExtNameByFileName(String name)
	{
		if (StrUtil.isStrTrimNull(name))
		{
			throw new RuntimeException("文件名称不合法" + name);
		}
		int p = name.lastIndexOf('.');
		if (p <= 0 || p == name.length() - 1)
		{
			throw new RuntimeException("文件名称不合法" + name);
		}
		String extName = name.substring(p + 1);
		return extName;
	}

	/**
	 * 获取文件后缀名
	 * @param file
	 * @return
	 * 赵玉柱
	 */
	public static String getExtName(File file)
	{
		if (file == null || !file.isFile())
		{
			return null;
		}
		return getExtNameByFileName(file.getName());
	}

	/**
	 * 根据文件名获取文件名称，不包括后缀名
	 * @param name
	 * @return
	 * 赵玉柱
	 */
	public static String getFileNameWithoutExtnameByFileName(String name)
	{
		if (StrUtil.isStrTrimNull(name))
		{
			throw new RuntimeException("文件名称不合法" + name);
		}
		int p = name.lastIndexOf('.');
		if (p <= 0 || p == name.length() - 1)
		{
			throw new RuntimeException("文件名称不合法" + name);
		}
		String nameWithoutExt = name.substring(0, p);
		return nameWithoutExt;
	}

	/**
	 * 获取文件名称，不包括后缀名
	 * @param file
	 * @return
	 * 赵玉柱
	 */
	public static String getFileNameWithoutExtname(File file)
	{
		if (file == null || !file.isFile())
		{
			return null;
		}
		return getFileNameWithoutExtnameByFileName(file.getName());
	}

	/**
	 * 根据文件名获取文件名称和后缀名
	 * @param name
	 * @return
	 * 赵玉柱
	 */
	public static String[] getFileNameAndExtNameByFileName(String name)
	{
		String[] result = new String[2];
		result[0] = getFileNameWithoutExtnameByFileName(name);
		result[1] = getExtNameByFileName(name);
		return result;
	}

	/**
	 * 获取文件名称和后缀名
	 * @param file
	 * @return["不包括后缀名的文件名称","后缀名"]
	 * 赵玉柱
	 */
	public static String[] getFileNameAndExtName(File file)
	{
		if (file == null || !file.isFile())
		{
			return null;
		}
		return getFileNameAndExtNameByFileName(file.getName());
	}

	/**
	 * 根据文件获取md5路径
	 * @param file
	 * @return
	 * 赵玉柱
	 */
	public static String getMD5PathByFile(File file)
	{
		if (file == null || !file.isFile())
		{
			return null;
		}
		return getMD5Path(file.getName());
	}

	/**
	 * 根据文件名获取MD5路径
	 * @param filename
	 * @return
	 * 赵玉柱
	 */
	public static String getMD5Path(String filename)
	{
		try
		{
			if (StrUtil.isStrTrimNull(filename))
			{
				return null;
			}
			MD5 md5 = new MD5();
			String md5path = md5.MD5_32bit(filename);
			String mdPath = "";
			for (int i = 0; i < 6; i++)
			{
				char c = md5path.charAt(i);
				if (i % 2 == 0)
				{
					mdPath += File.separator;
				}
				mdPath += c;
			}
			return mdPath;
		} catch (NoSuchAlgorithmException e)
		{
			throw new RuntimeException("报错内容", e);
		}
	}

	/**
	 * 根据文件拓展名获取文件上传路径
	 * @param filename
	 * @return
	 * 赵玉柱
	 */
	public static String getUploadPathByFileName(String filename)
	{
		FileTypeEnum fileType = FileTypeEnum.getFileTypeEnum(filename);
		return getUploadPathByFileTypeEnum(fileType);
	}

	/**
	 * 根据文件类型枚举获取文件上传路径
	 * @param typeEnum
	 * @return
	 * 赵玉柱
	 */
	public static String getUploadPathByFileTypeEnum(FileTypeEnum typeEnum)
	{
		if (typeEnum == null)
		{
			return BasConfig.getPropertie("rootPath");
		}
		String path = BasConfig.getPropertie("update-" + typeEnum.getTypename() + "-path");
		return StrUtil.isNoStrTrimNull(path) ? path : BasConfig.getPropertie("rootPath");
	}

	/**
	 * 根据文件类型编码获取文件上传路径
	 * @param fileType
	 * @return
	 * 赵玉柱
	 */
	public static String getUploadPathByFileType(int fileType)
	{
		FileTypeEnum typeEnum = FileTypeEnum.getFileTypeEnumByFileType(fileType);
		return getUploadPathByFileTypeEnum(typeEnum);
	}

	/**
	 * 根据文件名称获取文件的上传MD5路径
	 * @param filename
	 * @return
	 * 赵玉柱
	 */
	public static String getUploadMD5FilePathByFileName(String filename)
	{
		String md5Path = getMD5Path(filename);
		String uploadPath = getUploadPathByFileName(filename);
		return uploadPath + File.separator + md5Path + File.separator + filename;
	}

	/**
	 * 根据文件获取文件的上传MD5路径
	 * @param file
	 * @return
	 * 赵玉柱
	 */
	public static String getUploadMD5FilePathByFile(File file)
	{
		if (file == null || !file.isFile())
		{
			return null;
		}
		return getUploadMD5FilePathByFileName(file.getName());
	}
}
