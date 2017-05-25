package com.liiwin.ui;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.Document;

import com.liiwin.frame.RegExpDocument;
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
	private final JFrame from ;
	//用户的登录名将由系统自动生成，并返回
	private final JLabel			labUsername	= new JLabel("用  户  名");
	private final JLabel			labTel		= new JLabel("电  话  号");
	private final JLabel			labPwd		= new JLabel("密        码");
	private final JLabel			labPwd2		= new JLabel("重输密码");
	private final String			hintUsername	= "请输入用户名";
	private final HintTextField		txtUsername		= new HintTextField(hintUsername);
	private final JTextField		txtTel		= new JTextField();
	private final JPasswordField	txtPwd		= new JPasswordField();
	private final JPasswordField	txtPwd2		= new JPasswordField();
	private final JButton			btnRegist	= new JButton("注册");
	private final JButton			btnCancel	= new JButton("取消");
	private final int				minWidth	= 300;
	private final int				minHeight	= 300;
	private final int				userX		= 230;
	private final int				userY		= 230;

	public Register(JFrame jFrame)
	{
		from = jFrame;
		jFrame.setVisible(false);
		Rectangle aThridWindowSizeNoToolbar = WindowsSizeUtil.getProportUserWindowsNoToolbar(0.33f, 0.5f);
		setAlwaysOnTop(true);
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
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void setLayout()
	{

		int height = getHeight();
		int width = getWidth();
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		Box txtRegion = Box.createVerticalBox();
		Box userRegion = Box.createHorizontalBox();
		Box telRegion = Box.createHorizontalBox();
		Box pwdRegion = Box.createHorizontalBox();
		Box pwd2Region = Box.createHorizontalBox();
		Box btnRegion = Box.createHorizontalBox();
		getContentPane().add(txtRegion);
		//用户区域
		txtRegion.add(Box.createVerticalStrut(height - userY - 50));
		txtRegion.add(userRegion);
		txtRegion.add(Box.createVerticalStrut(20));
		txtRegion.add(telRegion);
		txtRegion.add(Box.createVerticalStrut(20));
		txtRegion.add(pwdRegion);
		txtRegion.add(Box.createVerticalStrut(20));
		txtRegion.add(pwd2Region);
		txtRegion.add(Box.createVerticalStrut(30));
		txtRegion.add(btnRegion);
		txtRegion.add(Box.createVerticalStrut(70));
		//用户名区域
		userRegion.add(Box.createHorizontalStrut((width - userX) / 2));
		userRegion.add(labUsername);
		userRegion.add(Box.createHorizontalStrut(20));
		userRegion.add(txtUsername);
		userRegion.add(Box.createHorizontalStrut((width - userX) / 2));
		//电话区域
		telRegion.add(Box.createHorizontalStrut((width - userX) / 2));
		telRegion.add(labTel);
		telRegion.add(Box.createHorizontalStrut(20));
		telRegion.add(txtTel);
		telRegion.add(Box.createHorizontalStrut((width - userX) / 2));
		//密码区域
		pwdRegion.add(Box.createHorizontalStrut((width - userX) / 2));
		pwdRegion.add(labPwd);
		pwdRegion.add(Box.createHorizontalStrut(20));
		pwdRegion.add(txtPwd);
		pwdRegion.add(Box.createHorizontalStrut((width - userX) / 2));
		//重复密码区域
		pwd2Region.add(Box.createHorizontalStrut((width - userX) / 2));
		pwd2Region.add(labPwd2);
		pwd2Region.add(Box.createHorizontalStrut(20));
		pwd2Region.add(txtPwd2);
		pwd2Region.add(Box.createHorizontalStrut((width - userX) / 2));
		//按钮区域
		btnRegion.add(Box.createHorizontalStrut((width - userX) / 2));
		btnRegion.add(btnRegist);
		btnRegion.add(Box.createHorizontalStrut(20));
		btnRegion.add(btnCancel);
		btnRegion.add(Box.createHorizontalStrut((width - userX) / 2));
	
	}

	private void addListener()
	{
		btnRegist.addActionListener(this);
		btnCancel.addActionListener(this);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				from.setVisible(true);
			}
		});
	}

	private void addTextlimit()
	{
		Document telDoc = new RegExpDocument("^[0-9|-]*$");
		txtTel.setDocument(telDoc);
		Document pwdDoc = new RegExpDocument("^[^ ]*$");
		txtPwd.setDocument(pwdDoc);
		Document pwd2Doc = new RegExpDocument("^[^ ]*$");
		txtPwd2.setDocument(pwd2Doc);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==btnCancel)
		{
			System.out.println("点击了关闭按钮，界面即将退出");
			int result =JOptionPane.showConfirmDialog(this,"是否取消注册","提示信息",JOptionPane.YES_NO_OPTION);
			if(result==JOptionPane.YES_OPTION)
			{
				from.setVisible(true);
				this.dispose();
			}
			else if(result == JOptionPane.NO_OPTION)
			{
				//否，动作暂时保留
				System.out.println("选择了否");
			}
		}else if(e.getSource()==btnRegist)
		{
			System.out.println("点击了注册按钮，进行注册操作");
		}
	}
}
