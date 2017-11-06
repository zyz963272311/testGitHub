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
 * <p>���⣺ TestBrow</p>
 * <p>���ݣ� TestBrow</p>
 * <p>����ʱ�䣺 2017��5��8��</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>��ȫ���� xyz.zyzhu.TestBrow</p>
 * <p>���ߣ� Administrator</p>
 */
public class TestBrow extends JFrame implements HyperlinkListener
{

	public TestBrow() {
		setSize(640, 480);
		setTitle("��Ҷ��ݺ:The Blog of Unmi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JEditorPane editorPane = new JEditorPane();
		// �ŵ����������в��ܹ����鿴��������
		JScrollPane scrollPane = new JScrollPane(editorPane);
		// ��������ʾ��ҳ html �ļ�,��ѡ��
		// editorPane.setContentType("text/html");
		// ���ó�ֻ��������ǿɱ༭����ῴ����ʾ������Ҳ�ǲ�һ���ģ�true��ʾ����
		editorPane.setEditable(false);
		// Ҫ����Ӧ��ҳ�е����ӣ��������ϳ���������
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
		// ��editorPane����������������
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
