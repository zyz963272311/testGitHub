package xyz.zyzhu;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 * <p>标题： Test</p>
 * <p>内容： Test</p>
 * <p>创建时间： 2017年5月8日</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>类全名： xyz.zyzhu.Test</p>
 * <p>作者： Administrator</p>
 */
public class Test
{

	public static void main(String[] args)
	{
		JFrame frame = new TestBrow();
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter()
		{

			@Override
			public void windowClosing(WindowEvent e)
			{
				System.exit(-1);
			}
		});
	}
}
