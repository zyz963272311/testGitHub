package com.liiwin.nio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
/**
 * <p>标题： 测试NIO</p>
 * <p>功能： </p>
 * <p>所属模块： 测试NIO</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2017年8月10日 下午3:27:15</p>
 * <p>类全名：com.liiwin.nio.TestNio</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestNio
{
	public static void main(String[] args) throws ParseException
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		System.out.println(dateFormat.parse("2017-08-07 16:59:38"));
	}
}
