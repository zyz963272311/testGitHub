package net.client.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.SocketTimeoutException;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.Socket;
/**
 * <p>标题：JAVA Socket编程 Client端</p>
 * <p>功能：JAVA Socket编程 Client端</p>
 * <p>所属模块： 赵玉柱使用</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年9月5日 上午9:52:19</p>
 * <p>类全名：net.client.socket.Client</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
//public class Client
//{
//	public static void main(String args[])
//	{
//		try
//		{
//			//向127.0.0.1(本机)的4700端口号发送消息
//			Socket socket = new Socket("127.0.0.1", 4700);
//			//系统的标准输入设备构造BufferedReader对象
//			BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
//			//由Socket对象得到输出流，并构造PrintWrite对象
//			PrintWriter os = new PrintWriter(socket.getOutputStream());
//			//由Socket对象得到输入流，并构造相应的BufferedReader对象
//			BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//			String readLine;
//			//从标准输入得到一个字符串
//			readLine = sin.readLine();
//			//若从标准输入内得到的字符串是bye，则停止读取
//			while (!readLine.equals("bye"))
//			{
//				//将标准输入输出的server
//				os.print(readLine);
//				//刷新输入流，使得服务器可以马上收到字符串
//				os.flush();
//				//在系统标准输入上打印字符串
//				System.out.println("Client2Server-Client端:\t" + readLine);
//				//从server读取字符串，并打印出来
//				System.out.println("Server2Client-Client端:\t" + is.readLine());
//				//从系统标准输入得到字符串
//				readLine = sin.readLine();
//			}
//			os.close();
//			is.close();
//			socket.close();
//		} catch (IOException e)
//		{
//			// TODO Auto-generated catch block
//			System.out.println("Client出错了");
//		}
//	}
//}
//public class Client
//{
//	public static void main(String args[]) throws Exception
//	{
//		//为了简单起见，所有的异常都直接往外抛
//		String host = "127.0.0.1"; //要连接的服务端IP地址
//		int port = 8899; //要连接的服务端对应的监听端口
//		//与服务端建立连接
//		Socket client = new Socket(host, port);
//		//建立连接后就可以往服务端写数据了
//		Writer writer = new OutputStreamWriter(client.getOutputStream());
//		boolean flag = true;
//		Scanner scanner = new Scanner(System.in);
//		while (flag)
//		{
//			String str = scanner.nextLine();
//			if ("bye".equals(str))
//			{
//				flag = false;
//			}
//			writer.write(str);
//			writer.flush();//写完后要记得flush
//		}
//		writer.close();
//		client.close();
//	}
//}
//public class Client
//{
//	public static void main(String args[]) throws Exception
//	{
//		//为了简单起见，所有的异常都直接往外抛
//		String host = "127.0.0.1"; //要连接的服务端IP地址
//		int port = 8899; //要连接的服务端对应的监听端口
//		//与服务端建立连接
//		Socket client = new Socket(host, port);
//		//建立连接后就可以往服务端写数据了
//		Writer writer = new OutputStreamWriter(client.getOutputStream());
//		writer.write("Hello Server.");
//		writer.write("eof");
//		writer.flush();
//		//写完以后进行读操作
//		Reader reader = new InputStreamReader(client.getInputStream());
//		char chars[] = new char[64];
//		int len;
//		StringBuffer sb = new StringBuffer();
//		String temp;
//		int index;
//		while ((len = reader.read(chars)) != -1)
//		{
//			temp = new String(chars, 0, len);
//			if ((index = temp.indexOf("eof")) != -1)
//			{
//				sb.append(temp.substring(0, index));
//				break;
//			}
//			sb.append(new String(chars, 0, len));
//		}
//		System.out.println("from server: " + sb);
//		writer.close();
//		reader.close();
//		client.close();
//	}
//}
//public class Client
//{
//	public static void main(String args[]) throws Exception
//	{
//		//为了简单起见，所有的异常都直接往外抛
//		String host = "127.0.0.1"; //要连接的服务端IP地址
//		int port = 8899; //要连接的服务端对应的监听端口
//		//与服务端建立连接
//		Socket client = new Socket(host, port);
//		//建立连接后就可以往服务端写数据了
//		Writer writer = new OutputStreamWriter(client.getOutputStream());
//		writer.write("Hello Server.");
//		writer.write("eof\n");
//		writer.flush();
//		//写完以后进行读操作
//		BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
//		StringBuffer sb = new StringBuffer();
//		String temp;
//		int index;
//		while ((temp = br.readLine()) != null)
//		{
//			if ((index = temp.indexOf("eof")) != -1)
//			{
//				sb.append(temp.substring(0, index));
//				break;
//			}
//			sb.append(temp);
//		}
//		System.out.println("from server: " + sb);
//		writer.close();
//		br.close();
//		client.close();
//	}
//}
public class Client
{
	public static void main(String args[]) throws Exception
	{
		//为了简单起见，所有的异常都直接往外抛
		String host = "127.0.0.1"; //要连接的服务端IP地址
		int port = 8899; //要连接的服务端对应的监听端口
		//与服务端建立连接
		Socket client = new Socket(host, port);
		//建立连接后就可以往服务端写数据了
		Writer writer = new OutputStreamWriter(client.getOutputStream(), "GBK");
		writer.write("你好，服务端。");
		writer.write("eof\n");
		writer.flush();
		//写完以后进行读操作
		BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));
		//设置超时间为10秒
		client.setSoTimeout(10 * 1000);
		StringBuffer sb = new StringBuffer();
		String temp;
		int index;
		try
		{
			while ((temp = br.readLine()) != null)
			{
				if ((index = temp.indexOf("eof")) != -1)
				{
					sb.append(temp.substring(0, index));
					break;
				}
				sb.append(temp);
			}
		} catch (SocketTimeoutException e)
		{
			System.out.println("数据读取超时。");
		}
		System.out.println("服务端: " + sb);
		writer.close();
		br.close();
		client.close();
	}
}
