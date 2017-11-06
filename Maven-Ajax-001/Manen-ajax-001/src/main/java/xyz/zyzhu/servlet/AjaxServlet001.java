package xyz.zyzhu.servlet;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>���⣺ AjaxServlet001</p>
 * <p>���ݣ� AjaxServlet001</p>
 * <p>����ʱ�䣺 2017��4��18��</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>��ȫ���� xyz.zyzhu.servlet.AjaxServlet001</p>
 * <p>���ߣ� Administrator</p>
 */
public class AjaxServlet001 extends HttpServlet
{

	public AjaxServlet001() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		resp.setCharacterEncoding("UTF-8");
		// String id = req.getParameter("id");
		// System.out.println(req);
		// System.out.println(id);
		// resp.setCharacterEncoding("UTF-8");
		// JSONObject json = new JSONObject();
		// json.put("id", id);
		// json.put("name", "name");
		// json.put("age", "age");
		// System.out.println(json);
		// resp.getWriter().write(json.toString());
		// resp.flushBuffer();
		String _downLoadFlags = req.getParameter("_downloadFlags");
		if (null == _downLoadFlags || _downLoadFlags.equals("1"))
		{
			// �ջ���1��Ĭ����Ϊ�Ǵ��ļ�
			openFile(req, resp);
		} else
		{
			// 2��Ϊ�������ļ�
			downloadFile(req, resp);
		}
	}

	private void openFile(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		StringBuffer requestURL = req.getRequestURL();
		req.setCharacterEncoding("UTF-8");
		String filePath = req.getParameter("filePath");
		int last = requestURL.lastIndexOf("/");
		requestURL.setLength(last + 1);
		if (filePath == null || filePath.trim().equals(""))
		{
			throw new RuntimeException("�ļ�·��Ϊ��");
		}
		// ���һ��/Ĭ����Ϊ�ǡ�http://��վĿ¼/·��/�ļ���.��չ����
		int start = filePath.lastIndexOf('/');
		if (start < 0)
		{
			throw new RuntimeException("�ļ�·������");
		}
		// aaa.txt
		String fileName = filePath.substring(start + 1);
		int start1 = fileName.lastIndexOf('.');
		if (start1 <= 0)
		{
			throw new RuntimeException("�ļ����Ƹ�ʽ����");
		}
		String extra = fileName.substring(start1 + 1);
		if (extra == null || extra.trim().length() == 0)
		{
			throw new RuntimeException("�ļ���׺������Ϊ��");
		}
		// pdf
		if (extra.toUpperCase().equals("PDF"))
		{
			// resp.setHeader("Content-type", "application/pdf");
		}
		// excel,��ʱ������
		if (extra.toUpperCase().equals("XLS") || extra.toUpperCase().equals("XLSX"))
		{
			resp.setHeader("Content-type", "application/vnd.ms-excel");
		}
		// word����ʱ������
		if (extra.toUpperCase().equals("DOC") || extra.toUpperCase().equals("DOCX"))
		{
			resp.setHeader("Content-type", "application/msword");
		}
		URL url = new URL(requestURL.append(filePath).toString());
		HttpURLConnection httpURL = (HttpURLConnection) url.openConnection();
		httpURL.connect();
		InputStream inputStream = new BufferedInputStream(httpURL.getInputStream());
		OutputStream sos = resp.getOutputStream();
		byte[] buffer = new byte[1024];
		int readBytes = -1;
		while ((readBytes = inputStream.read(buffer)) != -1)
		{
			sos.write(buffer, 0, readBytes);
		}
		sos.flush();
		inputStream.close();
	}

	private void downloadFile(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		StringBuffer requestURL = req.getRequestURL();
		String filePath = req.getParameter("filePath");
		int last = requestURL.lastIndexOf("/");
		requestURL.setLength(last + 1);
		if (filePath == null || filePath.trim().equals(""))
		{
			throw new RuntimeException("�ļ�·��Ϊ��");
		}
		// ���һ��/Ĭ����Ϊ�ǡ�http://��վĿ¼/·��/�ļ���.��չ����
		int start = filePath.lastIndexOf('/');
		if (start < 0)
		{
			throw new RuntimeException("�ļ�·������");
		}
		// aaa.txt
		String fileName = filePath.substring(start + 1);
		int start1 = fileName.lastIndexOf('.');
		if (start1 <= 0)
		{
			throw new RuntimeException("�ļ����Ƹ�ʽ����");
		}
		String extra = fileName.substring(start1 + 1);
		if (extra == null || extra.trim().length() == 0)
		{
			throw new RuntimeException("�ļ���׺������Ϊ��");
		}
		if (extra.toUpperCase().equals("PDF"))
		{
			resp.setHeader("Content-type", "application/pdf");
		}
		resp.setHeader("Content-Disposition", "attachment; filename=" + fileName);
		URL url = new URL(requestURL.append(filePath).toString());
		HttpURLConnection httpURL = (HttpURLConnection) url.openConnection();
		httpURL.connect();
		InputStream inputStream = new BufferedInputStream(httpURL.getInputStream());
		OutputStream sos = resp.getOutputStream();
		byte[] buffer = new byte[1024];
		int readBytes = -1;
		while ((readBytes = inputStream.read(buffer)) != -1)
		{
			sos.write(buffer, 0, readBytes);
		}
		sos.flush();
		inputStream.close();
	}
}
