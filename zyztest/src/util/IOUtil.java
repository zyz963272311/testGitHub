package util;

import java.io.Closeable;
import java.io.IOException;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年5月16日 下午12:29:09</p>
 * <p>类全名：util.IOUtil</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class IOUtil
{
	/**
	 * 关闭一个或多个流对象
	 * 
	 * @param closeables
	 *            可关闭的流对象列表
	 * @throws IOException
	 */
	public static void close(Closeable... closeables) throws IOException
	{
		if (closeables != null)
		{
			for (Closeable closeable : closeables)
			{
				if (closeable != null)
				{
					closeable.close();
				}
			}
		}
	}

	/**
	 * 关闭一个或多个流对象
	 * 
	 * @param closeables
	 *            可关闭的流对象列表
	 */
	public static void closeQuietly(Closeable... closeables)
	{
		try
		{
			close(closeables);
		} catch (IOException e)
		{
			// do nothing
		}
	}
}
