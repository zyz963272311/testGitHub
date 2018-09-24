package com.baidu.ueditor;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月24日 下午1:00:36</p>
 * <p>类全名：xyz.zyzhu.spring.boot.baidu.ueditor.Encoder</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Encoder
{
	public static String toUnicode(String input)
	{
		StringBuilder builder = new StringBuilder();
		char[] chars = input.toCharArray();
		for (char ch : chars)
		{
			if (ch < 256)
				builder.append(ch);
			else
			{
				builder.append("\\u" + Integer.toHexString(ch & 0xFFFF));
			}
		}
		return builder.toString();
	}
}