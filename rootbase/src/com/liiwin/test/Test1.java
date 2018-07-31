package com.liiwin.test;

import com.liiwin.test.Test_84.TTTStatic;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2018年7月31日 下午4:35:57</p>
 * <p>类全名：com.liiwin.test.Test1</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Test1
{
	public static void main(String[] args)
	{
		Test_84 t84 = new Test_84();
		String a = TTTStatic.getA();
		System.out.println(a);
		String c = t84.new TTTNoStatic().getC();
		System.out.println(c);
		String b = new com.liiwin.test.Test_84.TTTStatic().getB();
		System.out.println(b);
	}
}
