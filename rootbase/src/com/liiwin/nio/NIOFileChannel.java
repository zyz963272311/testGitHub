package com.liiwin.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
/**
 * <p>标题： 通道模式文件操作</p>
 * <p>功能： </p>
 * <p>所属模块： rootbas</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年7月11日 下午2:08:53</p>
 * <p>类全名：com.liiwin.nio.NIOFileChannel</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class NIOFileChannel
{
	public static void readFileByNIO(String fileName)
	{
		//第一步 获取通道
		FileInputStream fis = null;
		FileChannel channel = null;
		try
		{
			long start = System.currentTimeMillis();
			fis = new FileInputStream(fileName);
			channel = fis.getChannel();
			//文件内容的大小
			int size = (int) channel.size();
			//第二步 指定缓冲区 1K
			ByteBuffer buffer = ByteBuffer.allocate(size);
			//第三步 将通道中的数据读取到缓冲区中
			channel.read(buffer);
			Buffer bf = buffer.flip();
			System.out.println("fileChannel time=" + (System.currentTimeMillis() - start) + "ms");
			buffer.clear();
			buffer = null;
		} catch (FileNotFoundException e)
		{
			throw new RuntimeException("报错内容", e);
		} catch (IOException e)
		{
			throw new RuntimeException("报错内容", e);
		} finally
		{
			try
			{
				if (channel != null)
				{
					channel.close();
				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			try
			{
				if (fis != null)
				{
					fis.close();
				}
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args)
	{
		readFileByNIO("D:" + File.separator + "eclipse.zip");
	}
}
