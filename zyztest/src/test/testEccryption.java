package test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import encryption.Encryption;
import encryption.MD5;
import encryption.Other;
import encryption.SHA;
/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年9月27日 下午2:09:02</p>
 * <p>类全名：test.testMD5</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class testEccryption
{
	/**
	 * @param args
	 * x250-2
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException
	{
		// TODO Auto-generated method stub
		Scanner scanner;
		String SourceStr;
		String encryptionStr = null;
		String decrptyStr = null;
		int encryptionType;
		Encryption encryption;
		String[] encryptionTypes = { "MD5加密", "SHA加密" };
		while (true)
		{
			SourceStr = null;
			encryptionStr = null;
			decrptyStr = null;
			encryptionType = -1;
			System.out.println("=====================START========================");
			scanner = new Scanner(System.in);
			System.out.println("请输入源字符串");
			SourceStr = scanner.nextLine();
			System.out.println("请按照加密列表选择加密算法");
			for (int i = 0; i < encryptionTypes.length; i++)
			{
				System.out.println("序号:\t" + i + "\t名称:\t" + encryptionTypes[i]);
			}
			encryptionType = scanner.nextInt();
			switch (encryptionType)
			{
			case 0:
				encryption = new MD5();
				break;
			case 1:
				encryption = new SHA();
				break;
			default:
				encryption = new Other();
				break;
			}
			encryptionStr = encryption.getEncryption(SourceStr);
			decrptyStr = encryption.getDecrypt(encryptionStr);
			System.out.println("源字符串为：\t" + SourceStr + "\r\n转码后字符串为：\t" + encryptionStr + "\r\n解密后字符串为\t" + decrptyStr);
			System.out.println("32位加密位\t\t" + new MD5().MD5_32bit(SourceStr));
			System.out.println("======================END=========================\r\n\r\n");
		}
	}
}
