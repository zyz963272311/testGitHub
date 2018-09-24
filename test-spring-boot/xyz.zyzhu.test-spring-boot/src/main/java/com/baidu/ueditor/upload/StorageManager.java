package com.baidu.ueditor.upload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.FileUtils;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.State;
import com.liiwin.random.RandomString;
import com.liiwin.random.RandomStringImpl;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月24日 下午1:04:50</p>
 * <p>类全名：xyz.zyzhu.spring.boot.baidu.ueditor.upload.StorageManager</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class StorageManager
{
	public static final int BUFFER_SIZE = 8192;

	public static State saveBinaryFile(byte[] data, String path)
	{
		File file = new File(path);
		State state = valid(file);
		if (!(state.isSuccess()))
		{
			return state;
		}
		try
		{
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
			bos.write(data);
			bos.flush();
			bos.close();
		} catch (IOException ioe)
		{
			return new BaseState(false, 4);
		}
		state = new BaseState(true, file.getAbsolutePath());
		state.putInfo("size", data.length);
		state.putInfo("title", file.getName());
		return state;
	}

	public static State saveFileByInputStream(InputStream is, String path, long maxSize)
	{
		State state = null;
		File tmpFile = getTmpFile();
		byte[] dataBuf = new byte[2048];
		BufferedInputStream bis = new BufferedInputStream(is, 8192);
		try
		{
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(tmpFile), 8192);
			int count = 0;
			while ((count = bis.read(dataBuf)) != -1)
			{
				bos.write(dataBuf, 0, count);
			}
			bos.flush();
			bos.close();
			if (tmpFile.length() > maxSize)
			{
				tmpFile.delete();
				return new BaseState(false, 1);
			}
			state = saveTmpFile(tmpFile, path);
			if (!(state.isSuccess()))
			{
				tmpFile.delete();
			}
			return state;
		} catch (IOException localIOException)
		{
		}
		return new BaseState(false, 4);
	}

	public static State saveFileByInputStream(InputStream is, String path)
	{
		State state = null;
		File tmpFile = getTmpFile();
		byte[] dataBuf = new byte[2048];
		BufferedInputStream bis = new BufferedInputStream(is, 8192);
		try
		{
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(tmpFile), 8192);
			int count = 0;
			while ((count = bis.read(dataBuf)) != -1)
			{
				bos.write(dataBuf, 0, count);
			}
			bos.flush();
			bos.close();
			state = saveTmpFile(tmpFile, path);
			if (!(state.isSuccess()))
			{
				tmpFile.delete();
			}
			return state;
		} catch (IOException localIOException)
		{
		}
		return new BaseState(false, 4);
	}

	private static File getTmpFile()
	{
		File tmpDir = FileUtils.getTempDirectory();
		RandomString rand = new RandomStringImpl();
		String tmpFileName = rand.getRandomString(10, 'a', 'z');
		return new File(tmpDir, tmpFileName);
	}

	private static State saveTmpFile(File tmpFile, String path)
	{
		State state = null;
		File targetFile = new File(path);
		if (targetFile.canWrite())
			return new BaseState(false, 2);
		try
		{
			FileUtils.moveFile(tmpFile, targetFile);
		} catch (IOException e)
		{
			return new BaseState(false, 4);
		}
		state = new BaseState(true);
		state.putInfo("size", targetFile.length());
		state.putInfo("title", targetFile.getName());
		return state;
	}

	private static State valid(File file)
	{
		File parentPath = file.getParentFile();
		if ((!(parentPath.exists())) && (!(parentPath.mkdirs())))
		{
			return new BaseState(false, 3);
		}
		if (!(parentPath.canWrite()))
		{
			return new BaseState(false, 2);
		}
		return new BaseState(true);
	}
}
