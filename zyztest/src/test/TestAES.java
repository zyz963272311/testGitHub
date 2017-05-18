package test;

import encryption.AES;
/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年11月22日 下午4:58:05</p>
 * <p>类全名：test.TestAES</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestAES
{
	public static void main(String[] args)
	{
		String content = "ewqewqeq";
		String password = "321321";
		System.out.println("加密前" + content);
		//加密
		byte[] encryptResult = AES.encrypt(content, password);
		String encryptResultStr = AES.parseByte2HexStr(encryptResult);
		System.out.println("加密后" + encryptResultStr);
		//解密
		byte[] decryptFrom = AES.parseHexStr2Byte(encryptResultStr);
		byte[] decreptResult = AES.decrypt(decryptFrom, password);
		System.out.println("解密后" + new String(decreptResult));
	}
}
