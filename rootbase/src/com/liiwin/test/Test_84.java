package com.liiwin.test;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2017年8月28日 下午10:23:31</p>
 * <p>类全名：com.liiwin.test.Test_84</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Test_84
{
	public static void main(String[] args)
	{
		String s = "dsajkdjsakljdksajdklsaIOPIOPIOP./\123123  12310-=0=-0-=0-0-";
		int l = s.length();
		for (int i = 0; i < l; i++)
		{
			System.out.println("字符\t" + s.charAt(i) + "\t标志\t" + Character.isJavaIdentifierStart(s.charAt(i)) + "\tpath\t" + Character.isJavaIdentifierPart(s.charAt(i)));
		}
	}
}
