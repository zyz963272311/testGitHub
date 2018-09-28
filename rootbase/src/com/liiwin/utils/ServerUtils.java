package com.liiwin.utils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2018年9月28日 上午10:04:45</p>
 * <p>类全名：com.liiwin.utils.ServerUtils</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class ServerUtils
{
	private static String	hostIP;
	private static Lock		hostIPLock	= new ReentrantLock();
	private static Logger	logger		= LoggerFactory.getLogger(ServerUtils.class);

	public static String getHostIP()
	{
		try
		{
			hostIPLock.lock();
			if (hostIP == null)
			{
				Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
				while (allNetInterfaces.hasMoreElements())
				{
					NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
					Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
					while (addresses.hasMoreElements())
					{
						InetAddress ip = (InetAddress) addresses.nextElement();
						if (ip != null && ip instanceof Inet4Address && !ip.isLoopbackAddress() //loopback地址即本机地址，IPv4的loopback范围是127.0.0.0 ~ 127.255.255.255
								&& ip.getHostAddress().indexOf(":") == -1)
						{
							hostIP = ip.getHostAddress();
							break;
						}
					}
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			hostIPLock.unlock();
		}
		logger.info("获取本机IP地址{}", hostIP);
		return hostIP;
	}
}
