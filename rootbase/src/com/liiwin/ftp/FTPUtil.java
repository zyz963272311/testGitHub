package com.liiwin.ftp;

import java.io.IOException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
/**
 * <p>标题： FTP工具类</p>
 * <p>功能： </p>
 * <p>所属模块： rootbase</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2017年9月7日 下午1:54:26</p>
 * <p>类全名：com.liiwin.ftp.FTPUtil</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class FTPUtil
{
	/**
	 * 创建一个FTP连接
	 * @param ftpURL
	 * @param port
	 * @param username
	 * @param password
	 * @return
	 * 赵玉柱
	 */
	public static FTPClient connFTP(String ftpURL, int port, String username, String password)
	{
		FTPClient client = new FTPClient();
		try
		{
			//1.创建FTP连接
			client.connect(ftpURL, port);
			//2.验证连接地址
			int replyCode = client.getReplyCode();
			if (FTPReply.isPositiveCompletion(replyCode))
			{
				client.disconnect();
				throw new RuntimeException("创建FTP连接失败");
			}
			//3.登陆
			client.login(username, password);
		} catch (IOException e)
		{
			throw new RuntimeException("报错内容", e);
		}
		return client;
	}

	/**
	 * 断开FTP连接
	 * @param client
	 * 赵玉柱
	 */
	public static void disConnFTP(FTPClient client)
	{
		if (client == null)
		{
			return;
		}
		try
		{
			if (!client.isAvailable())
			{
				client.logout();
			}
			if (client.isConnected())
			{
				client.disconnect();
			}
		} catch (IOException e)
		{
			throw new RuntimeException("报错内容", e);
		}
	}

	public static void main(String[] args)
	{
		FTPClient client = connFTP("FTP://", 321, "admin", "admin");
		disConnFTP(client);
	}
}
