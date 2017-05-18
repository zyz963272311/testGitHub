package mq;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.ibm.mq.MQC;
import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年12月15日 下午12:32:04</p>
 * <p>类全名：mq.MQClient</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class MQClient
{
	/**
	 * Logger for this class
	 */
	private static final Log	logger					= LogFactory.getLog(MQClient.class);
	private static final String	strExtraSendXmlFileName	= "D:\\jndi.properties";
	private static Properties	props;
	static
	{
		props = new Properties();
		props.put("mqHostName", "124.127.161.254");
		props.put("mqPort", "4032");
		props.put("mqCCSID", "1381");
		props.put("mqUserName", "liujx");
		props.put("mqPassword", "linux");
		props.put("mqQManager", "Monitor_Queue_Manager");
		props.put("mqChannel", "Monitor_Conn_Chanel");
		props.put("mqLocalOutQueue", "Q_RECEIVE");
		props.put("mqLocalInQueue", "Q_RECEIVE");
		try
		{
			PrintStream fw = new PrintStream(new File(strExtraSendXmlFileName));
			props.list(fw);
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			throw new RuntimeException("报错内容", e);
		}
	}

	/**
	 * 
	 * 函数功能：TODO 主测试方法 <br>
	 * 开发人员：@author Zeming_gao <br>
	 * 开发时间：2013-3-21 <br>
	 * 相关参数： <br>
	 * 
	 * @param args
	 *            修改记录： <br>
	 */
	public static void main(String[] args)
	{
		MQClient test = new MQClient();
		// 发送消息
		test.putMsg();
		// 接收消息
		test.getMsg();
	}

	public void putMsg()
	{
		// MQ发送数据
		try
		{
			// 建立MQ客户端应用上下文环境
			MQEnvironment.port = Integer.parseInt(props.getProperty("mqPort"));
			MQEnvironment.CCSID = Integer.parseInt(props.getProperty("mqCCSID"));
			MQEnvironment.channel = props.getProperty("mqChannel");
			MQEnvironment.userID = props.getProperty("mqUserName");
			MQEnvironment.password = props.getProperty("mqPassword");
			MQEnvironment.hostname = props.getProperty("mqHostName");
			// 连接队列管理器
			MQQueueManager qMgr = new MQQueueManager(props.getProperty("mqQManager"));
			int openOptions = MQC.MQOO_OUTPUT | MQC.MQOO_FAIL_IF_QUIESCING;
			// 打开队列
			MQQueue q = null;
			try
			{
				q = qMgr.accessQueue(props.getProperty("mqLocalOutQueue"), openOptions);
			} catch (MQException me)
			{
				System.out.println("打开队列出现通讯异常" + me.getMessage() + "\n");
				return;
			}
			InputStream fins = new FileInputStream(new File(strExtraSendXmlFileName));
			byte[] data = new byte[fins.available()];
			fins.read(data);
			fins.close();
			MQMessage msg = new MQMessage();
			msg.write(data);
			// 放入消息
			q.put(msg);
			System.out.println("客户端发送数据包成功..");
			// 关闭队列
			q.close();
			// 断开队列管理器连接
			qMgr.disconnect();
		} catch (MQException e)
		{
			if (logger.isDebugEnabled())
			{
				logger.debug(e.getMessage());
			}
			e.printStackTrace();
		} catch (Exception e)
		{
			if (logger.isDebugEnabled())
			{
				logger.debug(e.getMessage());
			}
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 函数功能：TODO 获取数据 <br>
	 * 开发人员：@author Zeming_gao <br>
	 * 开发时间：2013-3-21 <br>
	 * 相关参数： <br>
	 * 修改记录： <br>
	 */
	public void getMsg()
	{
		// MQ接收数据
		try
		{
			// 建立用上下文环境
			MQEnvironment.hostname = props.getProperty("mqHostName");
			MQEnvironment.port = Integer.parseInt(props.getProperty("mqPort"));
			MQEnvironment.CCSID = Integer.parseInt(props.getProperty("mqCCSID"));
			MQEnvironment.channel = props.getProperty("mqChannel");
			MQEnvironment.userID = props.getProperty("mqUserName");
			MQEnvironment.password = props.getProperty("mqPassword");
			// 建立队列管理器
			MQQueueManager qMgr = new MQQueueManager(props.getProperty("mqQManager"));
			int openOptions = MQC.MQOO_INPUT_AS_Q_DEF | MQC.MQOO_FAIL_IF_QUIESCING;
			// 打开队列
			MQQueue q = qMgr.accessQueue(props.getProperty("mqLocalInQueue"), openOptions);
			MQGetMessageOptions mgo = new MQGetMessageOptions();
			mgo.options |= MQC.MQGMO_NO_WAIT;
			// 构造返回消息
			MQMessage msg = new MQMessage();
			if ((msg = fetchOneMsg(q)) != null)
			{
				byte[] xmlData = new byte[msg.getDataLength()];
				msg.readFully(xmlData);
				logger.info(new String(xmlData));
				System.out.println("接收服务器端返回数据包成功..\n接收数据为:\n" + new String(xmlData));
			}
			// 关闭队列
			q.close();
			// 断开队列管理器
			qMgr.disconnect();
		} catch (MQException e)
		{
			logger.error(e);
			e.printStackTrace();
		} catch (Exception e)
		{
			logger.error(e);
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 函数功能：TODO 从队列中取出消息 <br>
	 * 开发人员：@author Zeming_gao <br>
	 * 开发时间：2013-3-21 <br>
	 * 相关参数： <br>
	 * 
	 * @param q
	 * @return
	 * @throws Exception
	 *             修改记录： <br>
	 */
	private static MQMessage fetchOneMsg(MQQueue q) throws Exception
	{
		MQGetMessageOptions mgo = new MQGetMessageOptions();
		mgo.options |= MQC.MQGMO_NO_WAIT;
		MQMessage msg = new MQMessage();
		try
		{
			// 获取消息
			q.get(msg, mgo);
		} catch (MQException e)
		{
			return null;
		}
		return msg;
	}
}
