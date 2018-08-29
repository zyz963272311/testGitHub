package com.liiwin.encryption;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2018年8月29日 下午12:21:19</p>
 * <p>类全名：com.liiwin.encryption.DES</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class DES implements Encryption
{
	@SuppressWarnings("restriction")
	@Override
	public String getEncryption(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException
	{
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		//KeyGenerator 提供对称密钥生成器的功能，支持各种算法
		KeyGenerator keygen = KeyGenerator.getInstance("DES");
		//SecretKey 负责保存对称密钥
		SecretKey deskey = keygen.generateKey();
		//Cipher负责完成加密或解密工作
		Cipher c = null;
		try
		{
			c = Cipher.getInstance("DES");
			//该字节数组负责保存加密的结果
			c.init(Cipher.ENCRYPT_MODE, deskey);
			byte[] src = str.getBytes();
			// 加密，结果保存进cipherByte
			byte[] cipherByte = c.doFinal(src);
			return new String(cipherByte);
		} catch (NoSuchPaddingException e)
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

	@SuppressWarnings("restriction")
	@Override
	public String getDecrypt(String str)
	{
		try
		{
			Security.addProvider(new com.sun.crypto.provider.SunJCE());
			//KeyGenerator 提供对称密钥生成器的功能，支持各种算法
			KeyGenerator keygen = KeyGenerator.getInstance("DES");
			//SecretKey 负责保存对称密钥
			SecretKey deskey = keygen.generateKey();
			//Cipher负责完成加密或解密工作
			Cipher c = Cipher.getInstance("DES");
			// 根据密钥，对Cipher对象进行初始化，ENCRYPT_MODE表示加密模式
			c.init(Cipher.DECRYPT_MODE, deskey);
			byte[] src = str.getBytes();
			// 加密，结果保存进cipherByte
			byte[] cipherByte = c.doFinal(src);
			return new String(cipherByte);
		} catch (InvalidKeyException e)
		{
			throw new RuntimeException("报错内容", e);
		} catch (NoSuchAlgorithmException e)
		{
			throw new RuntimeException("报错内容", e);
		} catch (IllegalBlockSizeException e)
		{
			throw new RuntimeException("报错内容", e);
		} catch (BadPaddingException e)
		{
			throw new RuntimeException("报错内容", e);
		} catch (NoSuchPaddingException e)
		{
			throw new RuntimeException("报错内容", e);
		}
	}
}
