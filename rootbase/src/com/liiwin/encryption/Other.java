package com.liiwin.encryption;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年9月27日 下午2:45:47</p>
 * <p>类全名：encryption.Other</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Other implements Encryption
{
	@Override
	public String getEncryption(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException
	{
		// TODO Auto-generated method stub
		return "输入错误";
	}

	@Override
	public String getDecrypt(String str)
	{
		// TODO Auto-generated method stub
		return "输入错误";
	}
}
