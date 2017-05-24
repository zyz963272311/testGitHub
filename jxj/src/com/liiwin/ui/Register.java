package com.liiwin.ui;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import com.liiwin.util.WindowsSizeUtil;
/**
 * <p>标题： 注册界面</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年5月24日 下午1:42:17</p>
 * <p>类全名：com.liiwin.ui.Register</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Register extends JFrame implements ActionListener
{
	//用户的登录名将由系统自动生成，并返回
	//private final JLabel			labUser		= new JLabel("登录名");
	private final JLabel			labUsername	= new JLabel("用  户  名");
	private final JLabel			labTel		= new JLabel("电  话  号");
	private final JLabel			labPwd		= new JLabel("密        码");
	private final JLabel			labPwd2		= new JLabel("重输密码");
	private final String			hintUser	= "请输入账号/用户名/电话号码";
	private final HintTextField		txtUser		= new HintTextField(hintUser);
	private final JPasswordField	txtPwd		= new JPasswordField();
	private final JButton			btnLogin	= new JButton("登陆");
	private final JButton			btnRegist	= new JButton("注册");
	private final int				minWidth	= 300;
	private final int				minHeight	= 300;
	private final int				userX		= 230;
	private final int				userY		= 180;

	public Register()
	{
		Rectangle aThridWindowSizeNoToolbar = WindowsSizeUtil.getProportUserWindowsNoToolbar(0.33f, 0.5f);
		basSet(aThridWindowSizeNoToolbar);
		setLayout();
		addListener();
		addTextlimit();
	}

	private void basSet(Rectangle aThridWindowSizeNoToolbar)
	{
		setSize((int) aThridWindowSizeNoToolbar.getWidth(), (int) aThridWindowSizeNoToolbar.getHeight());
		setLocation(aThridWindowSizeNoToolbar.x, aThridWindowSizeNoToolbar.y);
		setVisible(true);
		setMinimumSize(new Dimension(minWidth, minHeight));
		setMaximumSize(new Dimension(400, 400));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void setLayout()
	{
	}

	private void addListener()
	{
	}

	private void addTextlimit()
	{
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
	}
}
