package xyz.zyzhu;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 * <p>���⣺ Test</p>
 * <p>���ݣ� Test</p>
 * <p>����ʱ�䣺 2017��5��8��</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>��ȫ���� xyz.zyzhu.Test</p>
 * <p>���ߣ� Administrator</p>
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
