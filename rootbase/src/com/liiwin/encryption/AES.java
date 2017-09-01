package com.liiwin.encryption;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年11月22日 下午4:45:34</p>
 * <p>类全名：encryption.AES</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class AES
{
	/**
	 * AES加密
	 * @param content 加密内容
	 * @param password 加密密钥
	 * @return 返回一个被加密后的byte数组
	 * @author 赵玉柱
	 */
	public static byte[] encrypt(String content, String password)
	{
		try
		{
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器  
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化  
			byte[] result = cipher.doFinal(byteContent);
			return result; // 加密  
		} catch (NoSuchAlgorithmException e)
		{
			throw new RuntimeException("报错内容", e);
		} catch (NoSuchPaddingException e)
		{
			throw new RuntimeException("报错内容", e);
		} catch (UnsupportedEncodingException e)
		{
			throw new RuntimeException("报错内容", e);
		} catch (InvalidKeyException e)
		{
			throw new RuntimeException("报错内容", e);
		} catch (IllegalBlockSizeException e)
		{
			throw new RuntimeException("报错内容", e);
		} catch (BadPaddingException e)
		{
			throw new RuntimeException("报错内容", e);
		}
	}

	/**
	 * AES解密
	 * @param content 被解密的byte数组
	 * @param password 解密/解密密钥
	 * @return 返回一个解密后的byte数组，如果解密成功
	 * @author 赵玉柱
	 */
	public static byte[] decrypt(byte[] content, String password)
	{
		try
		{
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器  
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化  
			byte[] result = cipher.doFinal(content);
			return result; // 加密  
		} catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		} catch (NoSuchPaddingException e)
		{
			e.printStackTrace();
		} catch (InvalidKeyException e)
		{
			e.printStackTrace();
		} catch (IllegalBlockSizeException e)
		{
			e.printStackTrace();
		} catch (BadPaddingException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将byte数组转换成16进制数组
	 * @param buf 将要转换的byte数组
	 * @return 转换后的16进制byte数组
	 * @author 赵玉柱
	 */
	public static String parseByte2HexStr(byte buf[])
	{
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++)
		{
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1)
			{
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 将16进制数组转换成byte数组
	 * @param hexStr 将要转换的16进制数组
	 * @return 转换后的byte数组
	 * @author 赵玉柱
	 */
	public static byte[] parseHexStr2Byte(String hexStr)
	{
		if (hexStr.length() < 1)
		{
			return null;
		}
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++)
		{
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
}
