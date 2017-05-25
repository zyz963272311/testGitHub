package com.liiwin.utils;

import java.io.UnsupportedEncodingException;
/**
 * <p>标题： 用于字符或字符串的相互之间转码</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年4月6日 下午9:09:28</p>
 * <p>类全名：code.CodeUtil</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class CodeUtil
{
	/**
	 * ISO-8859-1 --> GB2312
	 * @param text
	 * @return
	 * 赵玉柱
	 */
	public static String ISO2GB(String text)
	{
		return getTextByCharset(text, "ISO-8859-1", "GB2312");
	}

	/**
	 * GB2312 --> ISO8859-1
	 * @param text
	 * @return
	 * 赵玉柱
	 */
	public static String GB2ISO(String text)
	{
		return getTextByCharset(text, "GB2312", "ISO-8859-1");
	}

	/**
	 * 将字符串从A字符集转换到B字符集
	 * @param text
	 * @param fromCharset
	 * @param toCharset
	 * @return
	 * 赵玉柱
	 */
	public static String getTextByCharset(String text, String fromCharset, String toCharset)
	{
		if (StrUtil.isStrTrimNull(text) || StrUtil.isStrTrimNull(fromCharset) || StrUtil.isStrTrimNull(toCharset))
		{
			return null;
		}
		try
		{
			return new String(text.getBytes(fromCharset), toCharset);
		} catch (UnsupportedEncodingException e)
		{
			throw new RuntimeException("报错内容", e);
		}
	}

	public static String Utf8URLDecode(String text)
	{
		if (StrUtil.isStrTrimNull(text))
		{
			return null;
		}
		String result = "";
		int p = 0;
		text = text.toLowerCase();
		p = text.indexOf("%e");
		if (p == -1)
		{
			return text;
		}
		while (p != -1)
		{
			result += text.substring(0, p);
			text = text.substring(p, text.length());
			if (text == "" || text.length() < 9)
			{
				return result;
			}
			result += CodeToWord(text.substring(0, 9));
			text = text.substring(9, text.length());
			p = text.indexOf("%e");
		}
		return result + text;
	}

	private static final String CodeToWord(String text)
	{
		String result;
		if (Utf8codeCheck(text))
		{
			byte[] code = new byte[3];
			code[0] = (byte) (Integer.parseInt(text.substring(1, 3), 16) - 256);
			code[1] = (byte) (Integer.parseInt(text.substring(4, 6), 16) - 256);
			code[2] = (byte) (Integer.parseInt(text.substring(7, 9), 16) - 256);
			try
			{
				result = new String(code, "UTF-8");
			} catch (UnsupportedEncodingException ex)
			{
				result = null;
			}
		} else
		{
			result = text;
		}
		return result;
	}

	private static final boolean Utf8codeCheck(String text)
	{
		String sign = "";
		if (text.startsWith("%e"))
		{
			for (int i = 0, p = 0; p != -1; i++)
			{
				p = text.indexOf("%", p);
				if (p != -1)
				{
					p++;
				}
				sign += p;
			}
		}
		return sign.equals("147-1");
	}

	/**
	 * 将字符串进行utf-8编码
	 * @param text
	 * @return
	 * 赵玉柱
	 */
	public static String Utf8URLEncode(String text)
	{
		StringBuffer sb = new StringBuffer();
		if (StrUtil.isStrTrimNull(text))
		{
			return null;
		}
		for (int i = 0; i < text.length(); i++)
		{
			char c = text.charAt(i);
			if (c >= 0 && c <= 255)
			{
				sb.append(c);
			} else
			{
				byte[] b = new byte[0];
				try
				{
					b = Character.toString(c).getBytes("UTF-8");
				} catch (UnsupportedEncodingException e)
				{
					throw new RuntimeException("报错内容", e);
				}
				for (int j = 0; j < b.length; j++)
				{
					int k = b[j];
					if (k < 0)
					{
						k += 256;
					}
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) throws UnsupportedEncodingException
	{
		String str = "阿萨德法规和健康";
		String enc = Utf8URLEncode(str);
		System.out.println(enc);
		System.out.println(Utf8URLDecode(enc));
		System.out.println(str);
		str = new String(str.getBytes("GB2312"), "ISO-8859-1");
		System.out.println(str);
		System.out.println(ISO2GB(str));
	}
}
