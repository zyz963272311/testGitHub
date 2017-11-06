package xyz.zyzhu;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.IOException;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;

/**
 * <p>标题： TestBrow</p>
 * <p>内容： TestBrow</p>
 * <p>创建时间： 2017年5月8日</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>类全名： xyz.zyzhu.TestBrow</p>
 * <p>作者： Administrator</p>
 */
public class TestBrow extends JFrame implements HyperlinkListener
{

	public TestBrow() {
		setSize(640, 480);
		setTitle("隔叶黄莺:The Blog of Unmi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JEditorPane editorPane = new JEditorPane();
		// 放到滚动窗格中才能滚动查看所有内容
		JScrollPane scrollPane = new JScrollPane(editorPane);
		// 设置是显示网页 html 文件,可选项
		// editorPane.setContentType("text/html");
		// 设置成只读，如果是可编辑，你会看到显示的样子也是不一样的，true显示界面
		editorPane.setEditable(false);
		// 要能响应网页中的链接，则必须加上超链监听器
		editorPane.addHyperlinkListener(this);
		String path = "http://login.weixin.qq.com/qrcode/gcpNdVruYA==";
		try
		{
			editorPane.setPage(path);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		// editorPane.setText("");
		Container container = getContentPane();
		// 让editorPane总是填满整个窗体
		container.add(scrollPane, BorderLayout.CENTER);
	}

	@Override
	public void hyperlinkUpdate(HyperlinkEvent e)
	{
		 if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)  
	      {  
	         JEditorPane pane = (JEditorPane) e.getSource();  
	         if (e instanceof HTMLFrameHyperlinkEvent)  
	         {  
	            HTMLFrameHyperlinkEvent evt = (HTMLFrameHyperlinkEvent) e;  
	            HTMLDocument doc = (HTMLDocument) pane.getDocument();  
	            doc.processHTMLFrameHyperlinkEvent(evt);  
	         }  
	         else  
	         {  
	            try  
	            {  
	               pane.setPage(e.getURL());  
	            }  
	            catch (Throwable t)  
	            {  
	               t.printStackTrace();  
	            }  
	         }  
		}
	}
}
