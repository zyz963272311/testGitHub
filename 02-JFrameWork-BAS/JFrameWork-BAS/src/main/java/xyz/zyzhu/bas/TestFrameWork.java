package xyz.zyzhu.bas;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.TableColumnModel;
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;


/**
 * <p>标题： TestFrameWork</p>
 * <p>内容： TestFrameWork</p>
 * <p>创建时间： 2017年5月7日</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>类全名： xyz.zyzhu.bas.TestFrameWork</p>
 * <p>作者： Administrator</p>
 */
public class TestFrameWork
{

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException
	{
		// JFrame.setDefaultLookAndFeelDecorated(true);
		// UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		// UIManager.setLookAndFeel(new
		// SubstanceBusinessBlackSteelLookAndFeel());
		JFrame frame = new JFrame("测试使用");
		frame.addWindowListener(new WindowAdapter()
		{

			@Override
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		frame.setLayout(new GridLayout(5, 5));
		frame.add(new JLabel("喜好"));
		frame.add(new JCheckBox("音乐"));
		frame.add(new JCheckBox("音乐"));
		frame.add(new JCheckBox("音乐"));
		frame.add(new JCheckBox("音乐"));
		frame.add(new JCheckBox("音乐"));
		frame.add(new JCheckBox("音乐"));
		Choice c = new Choice();
		c.add("Green");
		c.add("Red");
		c.add("Blue");
		frame.add(c);
		JList<JLabel> list = new JList<JLabel>();
		list.add("一年级", new JLabel("一年级"));
		list.add("一年级", new JLabel("二年级"));
		list.add("一年级", new JLabel("三年级"));
		frame.add(list);
		JButton b = new JButton("测试换肤");
		b.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				JFrame.setDefaultLookAndFeelDecorated(true);
				try
				{
					UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
					UIManager.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());
				} catch (ClassNotFoundException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InstantiationException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedLookAndFeelException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		frame.add(b);
		Object[][] tableData = { new Object[] { 1, "测试1", "女", false }, //
				new Object[] { 2, "测试2", "男", true },//
				new Object[] { 3, "测试3", "其他", false } };
		Object[] tableColumn = { "ID", "姓名", "性别", "boolean" };
		JTable table = new JTable(tableData, tableColumn);
		TableColumnModel tcm = table.getColumnModel();
		tcm.getColumn(3).setCellEditor(new DefaultCellEditor(new JCheckBox("全选/不全选")));
		frame.add(new JScrollPane(table));
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * 
	 */
	private static void main1()
	{
		Frame frame = new Frame("测试");
		frame.addWindowListener(new WindowAdapter()
		{

			@Override
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		frame.setLayout(new GridLayout(3, 3));
		frame.add(new Label("喜好，可多选"));
		frame.add(new Checkbox("音乐"));
		frame.add(new Checkbox("音乐"));
		frame.add(new Checkbox("音乐"));
		frame.add(new Checkbox("音乐"));
		frame.add(new Checkbox("音乐"));
		Choice c = new Choice();
		c.add("Green");
		c.add("Red");
		c.add("Blue");
		frame.add(c);
		List list = new List(3, false);
		list.add("一年级");
		list.add("二年级");
		list.add("三年级");
		frame.add(list);
		Button b = new Button("测试按钮");
		frame.add(b);
		frame.pack();
		frame.setVisible(true);
	}
}
