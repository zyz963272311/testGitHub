package xyz.zyzhu.windows;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import xyz.zyzhu.util.WindowsUtil;

/**
 * <p>标题： </p>
 * <p>内容： Login</p>
 * <p>创建时间： 2017年5月16日</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>类全名： xyz.zyzhu.Login</p>
 * <p>作者： Administrator</p>
 */
public class Login extends JFrame implements ActionListener, MouseListener
{

	private JLabel labUser = new JLabel("登录名");
	private JLabel labPwd = new JLabel("密     码");
	private JTextField txtUser = new JTextField(20);
	private JPasswordField txtPwd = new JPasswordField(20);
	private JButton btnLogin = new JButton("登录");
	private JButton btnReg = new JButton("注册");
	private JPanel txtRegion = new JPanel();
	private JPanel btnRegion = new JPanel();
	public Login() 
	{
		basSet();
		addComp();
	}
	public void mouseClicked(MouseEvent arg0)
	{

	}
	public void mouseEntered(MouseEvent arg0)
	{
	}

	public void mouseExited(MouseEvent arg0)
	{
	}

	public void mousePressed(MouseEvent arg0)
	{
	}

	public void mouseReleased(MouseEvent arg0)
	{
	}

	public void actionPerformed(ActionEvent arg0)
	{
	}

	/**
	 * 基本设置
	 */
	private void basSet()
	{
		setTitle("登录");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		getContentPane().add(Box.createVerticalStrut(100));
		Dimension aThridDimension = WindowsUtil.getHalfWindowSize();
		setSize(aThridDimension);
		setLocation(aThridDimension.width, aThridDimension.height);
		setResizable(false);
		setVisible(true);
	}

	private void addComp()
	{
		txtRegion.setLayout(new BoxLayout(txtRegion, BoxLayout.Y_AXIS));
		btnRegion.setLayout(new BoxLayout(btnRegion, BoxLayout.X_AXIS));
		Box boxUser = Box.createHorizontalBox();
		Box boxPwd = Box.createHorizontalBox();
		Box boxBtn = Box.createHorizontalBox();
		boxUser.add(Box.createHorizontalStrut(250));
		boxUser.add(labUser);
		boxUser.add(txtUser);
		boxUser.add(Box.createHorizontalStrut(250));
		boxPwd.add(Box.createHorizontalStrut(250));
		boxPwd.add(labPwd);
		boxPwd.add(txtPwd);
		boxPwd.add(Box.createHorizontalStrut(250));
		boxBtn.add(btnLogin);
		boxBtn.add(Box.createHorizontalStrut(20));
		boxBtn.add(btnReg);
		txtRegion.add(boxUser);
		txtRegion.add(Box.createVerticalStrut(10));
		txtRegion.add(boxPwd);
		btnRegion.add(boxBtn);
		getContentPane().add(txtRegion, BorderLayout.CENTER);
		getContentPane().add(Box.createVerticalStrut(100));
		getContentPane().add(btnRegion, BorderLayout.SOUTH);
		getContentPane().add(Box.createVerticalStrut(40));
	}
}
