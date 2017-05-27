package com.liiwin.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
public class NetUtil
{
	public static boolean execPingNet(String url)
	{
		boolean onNet = false;
		Runtime runtime = Runtime.getRuntime();
		try
		{
			Process process = runtime.exec("ping " + url);
			InputStream iStream = process.getInputStream();
			InputStreamReader iSReader = new InputStreamReader(iStream, "UTF-8");
			BufferedReader bReader = new BufferedReader(iSReader);
			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = bReader.readLine()) != null)
			{
				sb.append(line);
			}
			iStream.close();
			iSReader.close();
			bReader.close();
			String result = new String(sb.toString().getBytes("UTF-8"));
			if (result != null && result.length() > 0)
			{
				if (result.indexOf("TTL") > 0 || result.indexOf("ttl") > 0)
				{
					onNet = true;
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return onNet;
	}

	public static boolean ipPortIsUseful(String address, int port)
	{
		boolean useful = false;
		Socket socket = new Socket();
		try
		{
			socket.connect(new InetSocketAddress(address, port));
			useful = true;
		} catch (IOException e)
		{
			e.printStackTrace();
			useful = false;
		} finally
		{
			try
			{
				socket.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return useful;
	}
}
