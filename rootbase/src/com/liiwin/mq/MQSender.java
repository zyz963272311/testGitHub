package com.liiwin.mq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.liiwin.config.BasConfig;
/**
 * <p>标题： 发送MQ消息</p>
 * <p>功能： </p>
 * <p>所属模块： rootbase</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2017年9月1日 下午5:05:03</p>
 * <p>类全名：com.liiwin.mq.MQSender</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class MQSender
{
	private static Logger logger = LoggerFactory.getLogger(MQSender.class);

	public static void main(String[] args)
	{
		ConnectionFactory connectionFactory;//ConnectionFactory 连接工厂 JMS用此进行连接
		Connection connection = null;//Connection JMS客户端连接到JMS
		Session session;//session 一个发送或接收消息的线程
		Destination destination;//Destination ：消息的目的地;消息发送给谁.
		MessageProducer producer;//MessageProducer：消息发送者
		connectionFactory = new ActiveMQConnectionFactory(BasConfig.getPropertie("active-mq-user"), BasConfig.getPropertie("active-mq-password"), BasConfig.getPropertie("active-mq-url"));
		try
		{
			connection = connectionFactory.createConnection();//从连接工厂获得连接对象
			connection.start();//启动
			session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);//创建连接 
			destination = session.createQueue("dsada");//xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
			producer = session.createProducer(destination);//得到消息生成者【发送者】
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);//设置不持久化 此处学习，实际根据项目决定
			sendMessage(session, producer);
		} catch (JMSException e)
		{
			e.printStackTrace();
			throw new RuntimeException("报错内容", e);
		}
	}

	private static void sendMessage(Session session, MessageProducer producer) throws JMSException
	{
		for (int i = 0; i < 10; i++)
		{
			TextMessage message = session.createTextMessage("发送方发送了消息" + i);
			logger.error("发送方发送了消息" + i);
			producer.send(message);
		}
	}
}
