package test;

import java.util.Scanner;
import singleton.HungrySingleton;
import singleton.LazySingletonSecurity;
import singleton.LazySingletonUnSecurity;
import singleton.SingletonDoubleCheck;
import singleton.SingletonStaticInnerClass;
/**
 * <p>标题：测试单例模式-懒汉安全模式 </p>
 * <p>功能：测试单例模式 </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年9月12日 下午5:48:02</p>
 * <p>类全名：test.TestSingleton</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestSingleton
{
	/**
	 * @param args
	 * x250-2
	 * @throws InterruptedException 
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) throws InterruptedException
	{
		int i;
		int times = 6000;
		Scanner scanner;
		String[] singleton = { "HungrySingleton", "LazySingletonSecurity", "LazySingletonUnSecurity", "SingletonDoubleCheck", "SingletonEnum", "SingletonStaticInnerClass" };
		while (true)
		{
			System.out.println("================================================");
			System.out.println("请按照菜单序号输入想要使用的方法");
			for (int j = 0; j < singleton.length; j++)
			{
				System.out.println("菜单名称:\t" + singleton[j] + "\t菜单号:\t" + (j + 1));
			}
			scanner = new Scanner(System.in);
			i = scanner.nextInt();
			switch (i)
			{
			case 1:
				for (int k = 0; k < times; k++)
				{
					new Thread(new Runnable()
					{
						@Override
						public void run()
						{
							// TODO Auto-generated method stub
							System.out.println(HungrySingleton.getClassName() + ":\t" + HungrySingleton.getCount());
						}
					}).start();
				}
				break;
			case 2:
				for (int k = 0; k < times; k++)
				{
					new Thread(new Runnable()
					{
						@Override
						public void run()
						{
							// TODO Auto-generated method stub
							System.out.println(LazySingletonSecurity.getClassName() + ":\t" + LazySingletonSecurity.getCount());
						}
					}).start();
				}
				break;
			case 3:
				for (int k = 0; k < times; k++)
				{
					new Thread(new Runnable()
					{
						@Override
						public void run()
						{
							System.out.println(LazySingletonUnSecurity.getClassName() + ":\t" + LazySingletonUnSecurity.getCount());
						}
					}).start();
				}
				break;
			case 4:
				for (int k = 0; k < times; k++)
				{
					new Thread(new Runnable()
					{
						@Override
						public void run()
						{
							System.out.println(SingletonDoubleCheck.getClassName() + ":\t" + SingletonDoubleCheck.getCount());
						}
					}).start();
				}
				break;
			//			case 5:
			//				System.out.println(SingletonEnum.getClassName() + ":\t" + SingletonEnum.getCount());
			//				break;
			case 6:
				for (int k = 0; k < times; k++)
				{
					new Thread(new Runnable()
					{
						@Override
						public void run()
						{
							// TODO Auto-generated method stub
							System.out.println(SingletonStaticInnerClass.getClassName() + ":\t" + SingletonStaticInnerClass.getCount());
						}
					}).start();
				}
				break;
			default:
				//				try
				//				{
				//					Runtime.getRuntime().exec("COLOR 9E");
				//				} catch (IOException e)
				//				{
				//					// TODO Auto-generated catch block
				//					throw new RuntimeException("报错内容", e);
				//				}
				System.out.println("没有此菜单，请重新输入");
				break;
			}
			System.out.println("==============================================\r\n\r\n");
		}
	}
}
