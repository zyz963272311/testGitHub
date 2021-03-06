package com.liiwin.ui;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.text.Document;

import com.liiwin.frame.RegExpDocument;
import com.liiwin.service.LoginService;
import com.liiwin.service.impl.LoginServiceImpl;
import com.liiwin.util.MD5Encrypt;
import com.liiwin.util.StrUtil;
import com.liiwin.util.WindowsSizeUtil;
import com.liiwin.utils.NetUtil;
/**
 * <p>
 * 标题： 登陆界面
 * </p>
 * <p>
 * 功能：
 * </p>
 * <p>
 * 所属模块： 登陆
 * </p>
 * <p>
 * 版权： Copyright © 2017 LIIWIN
 * </p>
 * <p>
 * 公司: 来往互动(北京)科技有限公司
 * </p>
 * <p>
 * 创建日期：2017年5月18日 上午11:19:27
 * </p>
 * <p>
 * 类全名：com.liiwin.ui.Login
 * </p>
 * 作者：赵玉柱 初审： 复审： 监听使用界面:
 * 
 * @version 8.0
 */
public class Login extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JLabel			labUser		= new JLabel("登录名");
	private final JLabel			labPwd		= new JLabel("密    码");
	private final String			hintUser	= "请输入账号/用户名/电话号码";
	private final HintTextField		txtUser		= new HintTextField(hintUser);
	private final JPasswordField	txtPwd		= new JPasswordField();
	private final JButton			btnLogin	= new JButton("登陆");
	private final JButton			btnRegist	= new JButton("注册");
	private final int				minWidth	= 300;
	private final int				minHeight	= 300;
	private final int				userX		= 230;
	private final int				userY		= 180;

	public Login()
	{
		if (NetUtil.ipPortIsUseful("www.baidu.com", 80) == false)
		{
			JOptionPane.showConfirmDialog(this, "当前网络环境不可用", "提示信息", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE);
		} else
		{
			if (NetUtil.ipPortIsUseful("localhost", 6666) == false)
			{
				JOptionPane.showConfirmDialog(this, "服务器已关闭", "提示信息", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE);
			}
		}
		Rectangle aThridWindowSizeNoToolbar = WindowsSizeUtil.getProportUserWindowsNoToolbar(0.33f, 0.5f);
		basSet(aThridWindowSizeNoToolbar);
		layoutSet(aThridWindowSizeNoToolbar);
		addListener();
		addTxtLimit();
	}

	/******** 私有方法分割线 ***********/
	/**
	 * 基本设置
	 * 
	 * @param aForthWindowSizeNoToolbar
	 *            赵玉柱
	 */
	private void basSet(Rectangle aThridWindowSizeNoToolbar)
	{
		setSize((int) aThridWindowSizeNoToolbar.getWidth(), (int) aThridWindowSizeNoToolbar.getHeight());
		// setSize(600, 600);
		setLocation(aThridWindowSizeNoToolbar.x, aThridWindowSizeNoToolbar.y);
		setVisible(true);
		setMinimumSize(new Dimension(minWidth, minHeight));
		setMaximumSize(new Dimension(400, 400));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * 设置布局分为三个部分，上部分为空白部分，中间部分为用户输入区域，下部分为按钮区域
	 * 
	 * @param aThridWindowSizeNoToolbar
	 *            赵玉柱
	 */
	private void layoutSet(Rectangle aThridWindowSizeNoToolbar)
	{
		int height = getHeight();
		int width = getWidth();
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		Box txtRegion = Box.createVerticalBox();
		Box userRegion = Box.createHorizontalBox();
		Box pwdRegion = Box.createHorizontalBox();
		Box btnRegion = Box.createHorizontalBox();
		getContentPane().add(txtRegion);
		// 用户区域
		txtRegion.add(Box.createVerticalStrut(height - userY - 50));
		txtRegion.add(userRegion);
		txtRegion.add(Box.createVerticalStrut(20));
		txtRegion.add(pwdRegion);
		txtRegion.add(Box.createVerticalStrut(30));
		txtRegion.add(btnRegion);
		txtRegion.add(Box.createVerticalStrut(70));
		// 用户名区域
		userRegion.add(Box.createHorizontalStrut((width - userX) / 2));
		userRegion.add(labUser);
		userRegion.add(Box.createHorizontalStrut(20));
		userRegion.add(txtUser);
		userRegion.add(Box.createHorizontalStrut((width - userX) / 2));
		// 密码区域
		pwdRegion.add(Box.createHorizontalStrut((width - userX) / 2));
		pwdRegion.add(labPwd);
		pwdRegion.add(Box.createHorizontalStrut(20));
		pwdRegion.add(txtPwd);
		pwdRegion.add(Box.createHorizontalStrut((width - userX) / 2));
		// 按钮区域
		btnRegion.add(Box.createHorizontalStrut((width - userX) / 2));
		btnRegion.add(btnLogin);
		btnRegion.add(Box.createHorizontalStrut(20));
		btnRegion.add(btnRegist);
		btnRegion.add(Box.createHorizontalStrut((width - userX) / 2));
	}

	/**
	 * 添加监听
	 * 
	 * 赵玉柱
	 */
	private void addListener()
	{
		btnLogin.addActionListener(this);
		btnRegist.addActionListener(this);
	}

	/**
	 * 为textfield添加Document，限制不能输入空格
	 * 
	 * 赵玉柱
	 */
	private void addTxtLimit()
	{
		Document userDoc = new RegExpDocument("^[^ ]*$");
		txtUser.setDocument(userDoc);
		Document pwdDoc = new RegExpDocument("^[^ ]*$");
		txtPwd.setDocument(pwdDoc);
	}

	/***************** 重载方法分割线 *************/
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource().equals(btnLogin))
		{
			String username = txtUser.getText();
			char[] passwordArray = txtPwd.getPassword();
			String password = String.valueOf(passwordArray);
			StringBuffer errMsg = new StringBuffer();
			if (StrUtil.isStrTrimNull(username))
			{
				errMsg.append("登录名不能为空，");
			}
			if (StrUtil.isStrTrimNull(password))
			{
				errMsg.append("密码不能为空。");
			}
			if (errMsg.length() > 0)
			{
				errMsg.setLength(errMsg.length() - 1);
				errMsg.append('。');
				System.out.println(errMsg.toString());
				JOptionPane.showConfirmDialog(this, errMsg, "登陆失败", JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE);
				errMsg.setLength(0);
				txtUser.grabFocus();
			} else
			{
				// 进行账号密码的校验
				String md5Password = MD5Encrypt.MD5Encode(password);
				System.out.println(md5Password);
				LoginService service = new LoginServiceImpl();
				Map<String,String> loginInfo = service.login(username, md5Password);
				if (loginInfo == null || loginInfo.isEmpty())
				{
					if (NetUtil.ipPortIsUseful("localhost", 6666) == false)
					{
						JOptionPane.showConfirmDialog(this, "服务器已关闭", "提示信息", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		} else if (e.getSource().equals(btnRegist))
		{
			new Register(this);
		}
	}
}
