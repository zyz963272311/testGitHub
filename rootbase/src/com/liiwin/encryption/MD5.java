package com.liiwin.encryption;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;
/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年9月27日 下午2:04:52</p>
 * <p>类全名：md5.MD5</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class MD5 implements Encryption
{
	@Override
	public String getEncryption(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException
	{
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		BASE64Encoder base64enCode = new BASE64Encoder();
		str = base64enCode.encode(md5.digest(str.getBytes("utf-8")));
		return str;
	}

	@Override
	public String getDecrypt(String str)
	{
		// TODO Auto-generated method stub
		return "MD5加密方式禁止解密";
	}

	public String MD5_32bit(String str) throws NoSuchAlgorithmException
	{
		if (str != null)
		{
			StringBuffer sb = new StringBuffer();
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte[] b = md.digest();
			int temp = 0;
			for (int offset = 0; offset < b.length; offset++)
			{
				temp = b[offset];
				if (temp < 0)
				{
					temp += 256;
				}
				if (temp < 16)
				{
					sb.append("0");
				}
				sb.append(Integer.toHexString(temp));
			}
			return sb.toString();
		} else
		{
			return null;
		}
	}
}
