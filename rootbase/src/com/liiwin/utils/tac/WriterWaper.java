package com.liiwin.utils.tac;

import java.io.Closeable;
import java.io.IOException;
import java.io.Writer;
/**
 * <p>标题： Writer 适配器</p>
 * <p>功能： </p>
 * <p>所属模块： rootbas</p>
 * <p>版权： Copyright © 2018 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2018年9月20日 下午3:26:26</p>
 * <p>类全名：com.liiwin.utils.tac.WriteWaper</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class WriterWaper implements Closeable
{
	protected Writer writer;

	public WriterWaper()
	{
	}

	public WriterWaper(Writer writer)
	{
		this.writer = writer;
	}

	/***********************************write方法************************************/
	public void write(String str) throws IOException
	{
		if (writer == null)
		{
			return;
		}
		writer.write(str);
	}

	public void write(String str, int off, int len) throws IOException
	{
		if (writer == null)
		{
			return;
		}
		writer.write(str, off, len);
	}

	public void write(char[] cbff) throws IOException
	{
		if (writer == null)
		{
			return;
		}
		writer.write(cbff);
	}

	public void write(char[] cbff, int off, int len) throws IOException
	{
		if (writer == null)
		{
			return;
		}
		writer.write(cbff, off, len);
	}

	public void write(char c) throws IOException
	{
		if (writer == null)
		{
			return;
		}
		writer.write(c);
	}

	/***********************************append方法************************************/
	public WriterWaper append(char c) throws IOException
	{
		if (writer != null)
		{
			writer.append(c);
		}
		return this;
	}

	public WriterWaper append(CharSequence cs) throws IOException
	{
		if (writer != null)
		{
			writer.append(cs);
		}
		return this;
	}

	public WriterWaper append(CharSequence cs, int start, int end) throws IOException
	{
		if (writer != null)
		{
			writer.append(cs, start, end);
		}
		return this;
	}

	/***********************************flush方法************************************/
	public void flush() throws IOException
	{
		if (writer != null)
		{
			writer.flush();
		}
	}

	/***********************************close方法************************************/
	@Override
	public void close() throws IOException
	{
		if (writer != null)
		{
			writer.close();
		}
	}
}
