package com.liiwin.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
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
	final static int	BUFFER_SIZE	= 0x3000000;	//缓冲区3M

	/**
	 * 用nio的方式读取大文件，这种方式的限制是当文件过大会造成内存溢出
	 * @param fileName
	 */
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
			//第二步 指定缓冲区 1K
			ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024 * 216);
			//第三步 将通道中的数据读取到缓冲区中
			channel.read(buffer);
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

	public static void reafFileByStream(String filePath)
	{
		File file = new File(filePath);
		FileInputStream fis = null;
		try
		{
			long start = System.currentTimeMillis();
			fis = new FileInputStream(file);
			System.out.println("reafFileByStream time:" + (System.currentTimeMillis() - start));
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} finally
		{
			if (fis != null)
			{
				try
				{
					fis.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	public static void readFileByMappedNio(String filePath)
	{
		File file = new File(filePath);
		try
		{
			@SuppressWarnings("resource")
			MappedByteBuffer buffer = new RandomAccessFile(file, "r").getChannel().map(FileChannel.MapMode.READ_ONLY, file.length() / 2, file.length() / 2);
			byte[] dst = new byte[BUFFER_SIZE];
			long start = System.currentTimeMillis();
			for (int offset = 0; offset < buffer.capacity(); offset += BUFFER_SIZE)
			{
				if (buffer.capacity() - offset > BUFFER_SIZE)
				{
					buffer.get(dst, 0, BUFFER_SIZE);
				} else
				{
					buffer.get(dst, 0, buffer.capacity() - offset);
				}
			}
			System.out.println("MappedByteBuffer time=" + (System.currentTimeMillis() - start));
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		String fileName = "电影/[电影天堂www.dy2018.com]功夫熊猫3DVD中英双字.avi";
		readFileByMappedNio("D:" + File.separator + fileName);
		reafFileByStream("D:" + File.separator + fileName);
	}
}
