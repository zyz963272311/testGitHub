package test;

import java.util.Scanner;
import encrypt.FileEncryptAndDecrypt;
/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年10月8日 下午4:22:44</p>
 * <p>类全名：test.TestEncrypt</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestEncrypt
{
	/**
	 * @param args
	 * x250-2
	 * @throws Exception 
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception
	{
		String[] method = { "加密", "解密", "查看是否加密" };
		Scanner scanner;
		String fileUrl, key;
		int keyLength;
		while (true)
		{
			fileUrl = null;
			key = null;
			keyLength = 0;
			System.out.println("==============================START==========================");
			scanner = new Scanner(System.in);
			System.out.println("按照菜单输入您想要进行的操作");
			int i;
			for (i = 0; i < method.length; i++)
			{
				System.out.println((i + 1) + "\t" + method[i]);
			}
			i = scanner.nextInt();
			err: switch (i)
			{
			case 1:
				//TODO 加密
				System.out.println("请输入文件全路径，如：C:/Users/x250-2/Desktop/xxx.txt");
				fileUrl = scanner.next();
				fileUrl = fileUrl.replace("\\\\", "/").replace("\\", "/");
				System.out.println("请输入密钥");
				key = scanner.next();
				FileEncryptAndDecrypt.encrypt(fileUrl, key);
				break;
			case 2:
				//TODO 解密
				System.out.println("请输入文件全路径，如：C:/Users/x250-2/Desktop/xxx.txt");
				fileUrl = scanner.next();
				fileUrl = fileUrl.replace("\\\\", "/").replace("\\", "/");
				System.out.println("请输入密钥长度");
				keyLength = scanner.nextInt();
				System.out.println(FileEncryptAndDecrypt.decrypt(fileUrl, fileUrl + "abc", keyLength));
				break;
			case 3:
				//TODO 查看是否被加密
				System.out.println("请输入文件全路径，如：C:/Users/x250-2/Desktop/xxx.txt");
				fileUrl = scanner.next();
				fileUrl = fileUrl.replace("\\\\", "/").replace("\\", "/");
				System.out.println("请输入密钥长度");
				keyLength = scanner.nextInt();
				System.out.println(FileEncryptAndDecrypt.readFileLastByte(fileUrl, keyLength));
				break;
			default:
				System.out.println("您输入的菜单选项【i】" + i + "有错误，请重新输入");
				for (i = 0; i < method.length; i++)
				{
					System.out.println((i + 1) + "\t" + method[i]);
				}
				break err;
			}
			System.out.println("===============================END===========================");
		}
	}
}
