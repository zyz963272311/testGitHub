package com.liiwin.util;

import java.security.MessageDigest;
/**
 * <p>标题： MD5加密</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 LIIWIN</p>
 * <p>公司: 来往互动(北京)科技有限公司</p>
 * <p>创建日期：2017年5月16日 上午11:21:45</p>
 * <p>类全名：util.MD5Encrypt</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class MD5Encrypt
{
	public MD5Encrypt()
	{
	}

	private final static String[]	hexDigits	= { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	/**
	 * 转换字节数组为16进制字串
	 * @param b 字节数组
	 * @return 16进制字串
	 */
	public static String byteArrayToHexString(byte[] b)
	{
		return byteArrayToString(b, 16);
	}

	/**
	 * 转换字节数组为10进制字串
	 * @param b 字节数组
	 * @return 10进制字串
	 */
	public static String byteArrayToTenString(byte[] b)
	{
		return byteArrayToString(b, 10);
	}

	public static String byteArrayToString(byte[] b, int dec)
	{
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
		{
			if (dec == 16)
			{
				resultSb.append(byteToHexString(b[i]));//若使用本函数转换则可得到加密结果的16进制表示，即数字字母混合的形式
			} else if (dec == 10)
			{
				resultSb.append(byteToNumString(b[i]));//使用本函数则返回加密结果的10进制数字字串，即全数字形式
			}
		}
		return resultSb.toString();
	}

	private static String byteToNumString(byte b)
	{
		int _b = b;
		if (_b < 0)
		{
			_b = 256 + _b;
		}
		return String.valueOf(_b);
	}

	private static String byteToHexString(byte b)
	{
		int n = b;
		if (n < 0)
		{
			n = 256 + n;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String MD5Encode(String origin)
	{
		String resultString = null;
		try
		{
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return resultString;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		//	    MD5Encrypt md5encrypt = new MD5Encrypt();
		System.out.println(MD5Encode("126"));
	}
}
