package mq;

import java.io.IOException;
import com.ibm.mq.MQC;
import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQPutMessageOptions;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年12月15日 上午10:00:30</p>
 * <p>类全名：mq.MessageByMQ</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class MessageByMQ
{
	private static String			qmName;
	private static String			qName;
	private static MQQueueManager	qmgr;
	static
	{
		MQEnvironment.hostname = "127.0.0.1";
		MQEnvironment.channel = "S_FENGLB";
		MQEnvironment.CCSID = 1381;
		MQEnvironment.port = 1414;
		qmName = "QM_FENGLB";
		qName = "testMQ";
		try
		{
			qmgr = new MQQueueManager(qmName);
		} catch (MQException e)
		{
			System.out.println("消息队列初始化失败");
			e.printStackTrace();
		}
	}

	public static int sendMessage(String message)
	{
		int result = 0;
		try
		{
			int openOptions = MQC.MQOO_OUTPUT | MQC.MQOO_FAIL_IF_QUIESCING;
			if (qmgr == null || !qmgr.isConnected())
			{
				qmgr = new MQQueueManager(qmName);
			}
			MQQueue queue = qmgr.accessQueue(qName, openOptions);
			MQMessage putMessage = new MQMessage();
			putMessage.writeUTF(message);
			MQPutMessageOptions pmo = new MQPutMessageOptions();
			queue.put(putMessage, pmo);
			System.out.println("收到Message=" + message);
			queue.close();
		} catch (MQException e)
		{
			System.out.println("MQ报错");
			e.printStackTrace();
		} catch (IOException e)
		{
			System.out.println("IO报错");
			e.printStackTrace();
		} finally
		{
			try
			{
				qmgr.disconnect();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return result;
	}

	public static String getMessage()
	{
		String message = null;
		try
		{
			int openOptions = MQC.MQOO_INPUT_AS_Q_DEF | MQC.MQOO_OUTPUT;
			MQMessage retrieve = new MQMessage();
			MQGetMessageOptions gmp = new MQGetMessageOptions();
			gmp.options = gmp.options + MQC.MQGMO_SYNCPOINT;
			gmp.options = gmp.options + MQC.MQGMO_WAIT;
			gmp.options = gmp.options + MQC.MQGMO_FAIL_IF_QUIESCING;
			gmp.waitInterval = 1000;
			if (qmgr == null || !qmgr.isConnected())
			{
				qmgr = new MQQueueManager(qmName);
			}
			MQQueue queue = qmgr.accessQueue(qmName, openOptions);
			queue.get(retrieve, gmp);
			message = retrieve.readUTF();
			queue.close();
		} catch (MQException e)
		{
			System.out.println("MQ报错");
			e.printStackTrace();
		} catch (IOException e)
		{
			System.out.println("IO报错");
			e.printStackTrace();
		}
		return message;
	}

	public static void main(String[] args)
	{
		sendMessage("testMQ");
	}
}
