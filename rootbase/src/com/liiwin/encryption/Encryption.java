package com.liiwin.encryption;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年9月27日 下午2:22:20</p>
 * <p>类全名：encryption.Encryption</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public interface Encryption
{
	/**
	 * 
	 * @param str 要加密的字符串
	 * @return 加密的字符串
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 * x250-2
	 */
	String getEncryption(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException;

	/**
	 * 
	 * @param str 要解密的字符串
	 * @return  若可以解密，则解密，若不可以解密，则返回字符串禁止解密
	 * x250-2
	 */
	String getDecrypt(String str);
}
