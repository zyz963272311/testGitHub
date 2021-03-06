package com.liiwin.ui;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;
/**
 * <p>标题： 显示提示信息的TextField</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 LIIWIN</p>
 * <p>公司: 来往互动(北京)科技有限公司</p>
 * <p>创建日期：2017年5月18日 下午2:30:18</p>
 * <p>类全名：com.liiwin.ui.HintTextField</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class HintTextField extends JTextField implements FocusListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1214333959161370580L;
	private String	hint;
	private boolean	showHint	= true;

	public HintTextField()
	{
		super();
	}

	public HintTextField(String hint)
	{
		super();
		this.hint = hint;
		showHint = true;
		super.addFocusListener(this);
	}

	public HintTextField(String hint, String txt)
	{
		super(txt);
		this.hint = hint;
		showHint = true;
		super.addFocusListener(this);
	}

	@Override
	public  void focusGained(FocusEvent e)
	{
		if (this.getText().isEmpty() || this.getText().equals(hint))
		{
			super.setText("");
			showHint = false;
		}
	}

	@Override
	public  void focusLost(FocusEvent e)
	{
		if (this.getText().isEmpty())
		{
			super.setText(hint);
			showHint = true;
		}
	}

	@Override
	public String getText()
	{
		return showHint ? "" : super.getText();
	}
}
