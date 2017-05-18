package com.liiwin.frame;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
/**
 * <p>标题： 正则表达式Document</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 LIIWIN</p>
 * <p>公司: 来往互动(北京)科技有限公司</p>
 * <p>创建日期：2017年5月18日 下午3:43:26</p>
 * <p>类全名：com.liiwin.frame.RegExpDocument</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class RegExpDocument extends PlainDocument
{
	private final Pattern	pattern;
	private Matcher			m;

	public RegExpDocument(String pat)
	{
		super();
		this.pattern = Pattern.compile(pat);
	}

	@Override
	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException
	{
		if (str == null)
		{
			return;
		}
		String tmp = getText(0, offset).concat(str);
		m = pattern.matcher(tmp);
		if (m.matches())
		{
			super.insertString(offset, str, attr);
		}
	}
}
