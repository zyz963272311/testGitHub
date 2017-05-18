package net.server.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * <p>标题：JAVA Socket编程 Server端 </p>
 * <p>功能：JAVA Socket编程 Server端 </p>
 * <p>所属模块： 赵玉柱</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年9月5日 上午11:26:58</p>
 * <p>类全名：net.server.socket.Server</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
//public class Server
//{
//	public static void main(String[] args)
//	{
//		try
//		{
//			ServerSocket server = null;
//			try
//			{
//				//创建一个ServerSocket在端口4700监听客户端请求
//				server = new ServerSocket(4700);
//			} catch (Exception e)
//			{
//				// TODO Auto-generated catch block
//				System.out.println("Server1出错了");
//			}
//			Socket socket = null;
//			try
//			{
//				//使用accept()阻塞等待客户请求，有客户
//				//请求到来则产生一个Socket对象，并继续执行
//				socket = server.accept();
//			} catch (Exception e)
//			{
//				System.out.println("Server2出错了");
//			}
//			String line;
//			//由Socket对象得到输入流，并构造相应的BufferedReader对象
//			BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//			//由Socket对象得到输出流，并构造PrintWriter对象
//			PrintWriter os = new PrintWriter(socket.getOutputStream());
//			//由系统标准输入设备构造BufferedReader对象
//			BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
//			//在标准输入打印得到的数据
//			System.out.println("Client2Server-Server:\t" + is.readLine());
//			//从标准输入读入一字符串
//			line = sin.readLine();
//			//如果读取到bye，停止读取
//			while (!line.equals("bye"))
//			{
//				//向客户端输入该字符串
//				os.print(line);
//				//刷新输出流，让Client端马上收到该字符串
//				os.flush();
//				//在系统上打印度入的字符串
//				System.out.println("Server2Client-Server端\t" + line);
//				//从Client端读取字符串，并打印出来
//				System.out.println("Client2Server-Server端\t" + is.readLine());
//				//从系统标准中读入字符串
//				line = sin.readLine();
//			}
//			os.close();
//			is.close();
//			socket.close();
//			server.close();
//		} catch (Exception e)
//		{
//			System.out.println("Server3出错了");
//		}
//	}
//}
//public class Server
//{
//	public static void main(String args[]) throws IOException
//	{
//		//为了简单起见，所有的异常信息都往外抛
//		int port = 8899;
//		//定义一个ServerSocket监听在端口8899上
//		ServerSocket server = new ServerSocket(port);
//		//server尝试接收其他Socket的连接请求，server的accept方法是阻塞式的
//		Socket socket = server.accept();
//		//跟客户端建立好连接之后，我们就可以获取socket的InputStream，并从中读取客户端发过来的信息了。
//		Reader reader = new InputStreamReader(socket.getInputStream());
//		char chars[] = new char[64];
//		int len;
//		StringBuilder sb = new StringBuilder();
//		while ((len = reader.read(chars)) != -1)
//		{
//			sb.append(new String(chars, 0, len));
//		}
//		System.out.println("from client: " + sb);
//		reader.close();
//		socket.close();
//		server.close();
//	}
//}
//public class Server
//{
//	public static void main(String args[]) throws IOException
//	{
//		//为了简单起见，所有的异常信息都往外抛
//		int port = 8899;
//		//定义一个ServerSocket监听在端口8899上
//		ServerSocket server = new ServerSocket(port);
//		//server尝试接收其他Socket的连接请求，server的accept方法是阻塞式的
//		Socket socket = server.accept();
//		//跟客户端建立好连接之后，我们就可以获取socket的InputStream，并从中读取客户端发过来的信息了。
//		Reader reader = new InputStreamReader(socket.getInputStream());
//		char chars[] = new char[64];
//		int len;
//		StringBuilder sb = new StringBuilder();
//		String temp;
//		int index;
//		while ((len = reader.read(chars)) != -1)
//		{
//			temp = new String(chars, 0, len);
//			if ((index = temp.indexOf("eof")) != -1)
//			{//遇到eof时就结束接收
//				sb.append(temp.substring(0, index));
//				break;
//			}
//			sb.append(temp);
//		}
//		System.out.println("from client: " + sb);
//		//读完后写一句
//		Writer writer = new OutputStreamWriter(socket.getOutputStream());
//		writer.write("Hello Client.");
//		writer.flush();
//		writer.close();
//		reader.close();
//		socket.close();
//		server.close();
//	}
//}
//public class Server
//{
//	public static void main(String args[]) throws IOException
//	{
//		//为了简单起见，所有的异常信息都往外抛
//		int port = 8899;
//		//定义一个ServerSocket监听在端口8899上
//		ServerSocket server = new ServerSocket(port);
//		while (true)
//		{
//			//server尝试接收其他Socket的连接请求，server的accept方法是阻塞式的
//			Socket socket = server.accept();
//			//每接收到一个Socket就建立一个新的线程来处理它
//			new Thread(new Task(socket)).start();
//		}
//	}
//
//	/**
//	 * 用来处理Socket请求的
//	*/
//	static class Task implements Runnable
//	{
//		private final Socket	socket;
//
//		public Task(Socket socket)
//		{
//			this.socket = socket;
//		}
//
//		@Override
//		public void run()
//		{
//			try
//			{
//				handleSocket();
//			} catch (Exception e)
//			{
//				e.printStackTrace();
//			}
//		}
//
//		/**
//		 * 跟客户端Socket进行通信
//		* @throws Exception
//		 */
//		private void handleSocket() throws Exception
//		{
//			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//			StringBuilder sb = new StringBuilder();
//			String temp;
//			int index;
//			while ((temp = br.readLine()) != null)
//			{
//				System.out.println(temp);
//				if ((index = temp.indexOf("eof")) != -1)
//				{//遇到eof时就结束接收
//					sb.append(temp.substring(0, index));
//					break;
//				}
//				sb.append(temp);
//			}
//			System.out.println("from client: " + sb);
//			//读完后写一句
//			Writer writer = new OutputStreamWriter(socket.getOutputStream());
//			writer.write("Hello Client.");
//			writer.write("eof\n");
//			writer.flush();
//			writer.close();
//			br.close();
//			socket.close();
//		}
//	}
//}
public class Server
{
	public static void main(String args[]) throws IOException
	{
		//为了简单起见，所有的异常信息都往外抛
		int port = 8899;
		//定义一个ServerSocket监听在端口8899上
		@SuppressWarnings("resource")
		ServerSocket server = new ServerSocket(port);
		while (true)
		{
			//server尝试接收其他Socket的连接请求，server的accept方法是阻塞式的
			Socket socket = server.accept();
			//每接收到一个Socket就建立一个新的线程来处理它
			new Thread(new Task(socket)).start();
		}
	}

	/**
	 * 用来处理Socket请求的
	 */
	static class Task implements Runnable
	{
		private final Socket	socket;

		public Task(Socket socket)
		{
			this.socket = socket;
		}

		@Override
		public void run()
		{
			try
			{
				handleSocket();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		/**
		 * 跟客户端Socket进行通信
		* @throws Exception
		 */
		private void handleSocket() throws Exception
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "GBK"));
			StringBuilder sb = new StringBuilder();
			String temp;
			int index;
			while ((temp = br.readLine()) != null)
			{
				System.out.println(temp);
				if ((index = temp.indexOf("eof")) != -1)
				{//遇到eof时就结束接收
					sb.append(temp.substring(0, index));
					break;
				}
				sb.append(temp);
			}
			System.out.println("客户端: " + sb);
			//读完后写一句
			Writer writer = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
			writer.write("你好，客户端。");
			writer.write("eof\n");
			writer.flush();
			writer.close();
			br.close();
			socket.close();
		}
	}
}
