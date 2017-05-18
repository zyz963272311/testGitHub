package util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年5月16日 下午1:47:45</p>
 * <p>类全名：util.NetWork</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class NetWork
{
	/**
	 * 判断网络是否是连通状态
	 * @param url
	 * @return
	 * 赵玉柱
	 */
	public static boolean isConnect(String url)
	{
		return isConnect(url, true);
	}

	/**
	 * 判断网络是否为连通状态
	 * @param url 要连接的URL地址
	 * @param isThrow 是否抛出异常
	 * @return
	 * 赵玉柱
	 */
	public static boolean isConnect(String url, boolean isThrow)
	{
		boolean result = false;
		Runtime runtime = Runtime.getRuntime();
		Process process;
		try
		{
			if (StrUtil.isStrTrimNull(url))
			{
				throw new RuntimeException("url地址不可为空");
			}
			process = runtime.exec("ping " + url);
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "GBK");
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = br.readLine()) != null)
			{
				sb.append(line).append("\r\n");
			}
			br.close();
			isr.close();
			is.close();
			if (null != sb && StrUtil.isNoStrTrimNull(sb.toString()))
			{
				if (sb.toString().indexOf("TTL") > 0)
				{
					result = true;
				}
				System.out.println("ping URL:【" + url + "】返回的信息为" + sb);
			}
		} catch (Exception e)
		{
			if (isThrow)
			{
				throw new RuntimeException(e);
			}
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args)
	{
		boolean isConnect = false;
		try
		{
			isConnect = isConnect("www.baidu.com", false);
			if (isConnect)
			{
				System.out.println("网络连接成功");
			} else
			{
				System.out.println("网络连接异常");
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
