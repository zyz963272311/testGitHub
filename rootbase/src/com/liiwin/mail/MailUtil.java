package com.liiwin.mail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import com.liiwin.config.BasConfig;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： 邮件工具类</p>
 * <p>功能： </p>
 * <p>所属模块： rootbas</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2017年9月18日 上午10:11:38</p>
 * <p>类全名：com.liiwin.mail.MailUtil</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class MailUtil
{
	/**
	 * 发送邮件的demo,主要用来保存邮件文件
	 * @param addressFrom
	 * @param addressTos
	 * @param subject
	 * @param abbressToCC
	 * @param abbressToBCC
	 * @param contexts
	 * @param sendDate
	 * 赵玉柱
	 */
	public static void SendMailDemo(InternetAddress addressFrom, List<InternetAddress> addressTos, String subject, List<InternetAddress> abbressToCC, List<InternetAddress> abbressToBCC,
			List<Map<String,Object>> contexts, Date sendDate)
	{
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);
		try
		{
			if (addressFrom == null)
			{
				throw new RuntimeException("邮件发件人不可为空");
			}
			//发件人
			if (addressTos == null || addressTos.isEmpty())
			{
				throw new RuntimeException("邮件收件人不可为空");
			}
			message.setFrom(addressFrom);
			//收件人
			for (InternetAddress addressTo : addressTos)
			{
				message.setRecipient(MimeMessage.RecipientType.TO, addressTo);
			}
			//抄送人
			if (abbressToCC != null && !abbressToCC.isEmpty())
			{
				for (InternetAddress addressTo : abbressToCC)
				{
					message.setRecipient(MimeMessage.RecipientType.CC, addressTo);
				}
			}
			//密送人
			if (abbressToBCC != null && !abbressToBCC.isEmpty())
			{
				for (InternetAddress addressTo : abbressToBCC)
				{
					message.setRecipient(MimeMessage.RecipientType.BCC, addressTo);
				}
			}
			if (StrUtil.isNoStrTrimNull(subject))
			{
				message.setSubject(subject, "UTF-8");
			}
			if (contexts != null && !contexts.isEmpty())
			{
				for (Map<String,Object> context : contexts)
				{
					if (context != null && context.containsKey("context") && context.containsKey("type"))
					{
						message.setContent(context.get("context"), StrUtil.obj2str(context.get("type")));
					}
				}
			}
			message.setSentDate(sendDate);
			message.saveChanges();
			OutputStream out = new FileOutputStream("D://MyMail.eml");
			message.writeTo(out);
			out.flush();
			out.close();
		} catch (MessagingException e)
		{
			throw new RuntimeException("报错内容", e);
		} catch (FileNotFoundException e)
		{
			throw new RuntimeException("报错内容", e);
		} catch (IOException e)
		{
			throw new RuntimeException("报错内容", e);
		}
	}

	/**
	 * 用QQ邮箱发送邮件
	 * @param userAddress 用户名
	 * @param password 密码（授权码）
	 * @param addressFrom 发送方 必须是当前用户名
	 * @param addressTos 接收方，支持多个，不可为空
	 * @param subject 主题
	 * @param abbressToCC 抄送，支持多个，可以为空
	 * @param abbressToBCC 密送 可以为空
	 * @param contexts 内容的List 暂时分为
	 * {
	 * 		{"type":"image","context":"图片地址","html":"html格式的文本,可以为空"}
	 * 		,{"type":"text","context":"显示的文本"}
	 * 		,{"type":"attach","context":"附件地址"}
	 * }
	 * @param sendDate 发送时间
	 * 赵玉柱
	 */
	public static void sendQQEMail(String userAddress, String password, InternetAddress addressFrom, List<InternetAddress> addressTos, String subject, List<InternetAddress> abbressToCC,
			List<InternetAddress> abbressToBCC, List<Map<String,Object>> contexts, Date sendDate)
	{
		Properties props = new Properties();
		//使用的协议
		props.setProperty("mail.transport.protocol", BasConfig.getPropertie("E-MAIL-QQ-PROTOCAL"));
		//发件人邮箱smtp地址
		props.setProperty("mail.smtp.host", BasConfig.getPropertie("E-MAIL-QQ-HOST"));
		//需要申请验证
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.port", BasConfig.getPropertie("E-MAIL-QQ-PORT"));
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.socketFactory.port", BasConfig.getPropertie("E-MAIL-QQ-PORT"));
		Session session = Session.getDefaultInstance(props);
		session.setDebug(StrUtil.obj2bool(BasConfig.getPropertie("debug")));
		try
		{
			Transport transport = session.getTransport();
			transport.connect(userAddress, password);
			MimeMessage message = createMailMessage(session, addressFrom, addressTos, subject, abbressToCC, abbressToBCC, contexts, sendDate);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (MessagingException e)
		{
			throw new RuntimeException("报错内容", e);
		}
	}

	public static void sendEMail(String userAddress, String password, InternetAddress addressFrom, List<InternetAddress> addressTos, String subject, List<InternetAddress> abbressToCC,
			List<InternetAddress> abbressToBCC, List<Map<String,Object>> contexts, Date sendDate)
	{
		if (StrUtil.isStrTrimNull(userAddress))
		{
			throw new RuntimeException("邮箱用户名称不可为空");
		}
		if (userAddress.endsWith("163.com"))
		{
			send163EMail(userAddress, password, addressFrom, addressTos, subject, abbressToCC, abbressToBCC, contexts, sendDate);
		} else if (userAddress.endsWith("qq.com"))
		{
			sendQQEMail(userAddress, password, addressFrom, addressTos, subject, abbressToCC, abbressToBCC, contexts, sendDate);
		} else if (userAddress.endsWith("126.com"))
		{
			send126EMail(userAddress, password, addressFrom, addressTos, subject, abbressToCC, abbressToBCC, contexts, sendDate);
		}
	}

	/**
	 * 用163邮箱发送邮件
	 * @param userAddress 用户名
	 * @param password 密码（授权码）
	 * @param addressFrom 发送方 必须是当前用户名
	 * @param addressTos 接收方，支持多个，不可为空
	 * @param subject 主题
	 * @param abbressToCC 抄送，支持多个，可以为空
	 * @param abbressToBCC 密送 可以为空
	 * @param contexts 内容的List 暂时分为
	 * {
	 * 		{"type":"image","context":"图片地址","html":"html格式的文本,可以为空"}
	 * 		,{"type":"text","context":"显示的文本"}
	 * 		,{"type":"attach","context":"附件地址"}
	 * }
	 * @param sendDate 发送时间
	 * 赵玉柱
	 */
	public static void send163EMail(String userAddress, String password, InternetAddress addressFrom, List<InternetAddress> addressTos, String subject, List<InternetAddress> abbressToCC,
			List<InternetAddress> abbressToBCC, List<Map<String,Object>> contexts, Date sendDate)
	{
		Properties props = new Properties();
		//使用的协议
		props.setProperty("mail.transport.protocol", BasConfig.getPropertie("E-MAIL-163-PROTOCAL"));
		//发件人邮箱smtp地址
		props.setProperty("mail.smtp.host", BasConfig.getPropertie("E-MAIL-163-HOST"));
		//需要申请验证
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.port", BasConfig.getPropertie("E-MAIL-163-PORT"));
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.socketFactory.port", BasConfig.getPropertie("E-MAIL-163-PORT"));
		Session session = Session.getDefaultInstance(props);
		session.setDebug(true);
		try
		{
			Transport transport = session.getTransport();
			transport.connect(userAddress, password);
			MimeMessage message = createMailMessage(session, addressFrom, addressTos, subject, abbressToCC, abbressToBCC, contexts, sendDate);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (MessagingException e)
		{
			throw new RuntimeException("报错内容", e);
		}
	}

	/**
	 * 用QQ邮箱发送邮件
	 * @param userAddress 用户名
	 * @param password 密码（授权码）
	 * @param addressFrom 发送方 必须是当前用户名
	 * @param addressTos 接收方，支持多个，不可为空
	 * @param subject 主题
	 * @param abbressToCC 抄送，支持多个，可以为空
	 * @param abbressToBCC 密送 可以为空
	 * @param contexts 内容的List 暂时分为
	 * {
	 * 		{"type":"image","context":"图片地址","html":"html格式的文本,可以为空"}
	 * 		,{"type":"text","context":"显示的文本"}
	 * 		,{"type":"attach","context":"附件地址"}
	 * }
	 * @param sendDate 发送时间
	 * 赵玉柱
	 */
	public static void send126EMail(String userAddress, String password, InternetAddress addressFrom, List<InternetAddress> addressTos, String subject, List<InternetAddress> abbressToCC,
			List<InternetAddress> abbressToBCC, List<Map<String,Object>> contexts, Date sendDate)
	{
		Properties props = new Properties();
		//使用的协议
		props.setProperty("mail.transport.protocol", BasConfig.getPropertie("E-MAIL-126-PROTOCAL"));
		//发件人邮箱smtp地址
		props.setProperty("mail.smtp.host", BasConfig.getPropertie("E-MAIL-126-HOST"));
		//需要申请验证
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.port", BasConfig.getPropertie("E-MAIL-126-PORT"));
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.socketFactory.port", BasConfig.getPropertie("E-MAIL-126-PORT"));
		Session session = Session.getDefaultInstance(props);
		session.setDebug(true);
		try
		{
			Transport transport = session.getTransport();
			transport.connect(userAddress, password);
			MimeMessage message = createMailMessage(session, addressFrom, addressTos, subject, abbressToCC, abbressToBCC, contexts, sendDate);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (MessagingException e)
		{
			throw new RuntimeException("报错内容", e);
		}
	}

	/**
	 * 创建一个邮件Message
	 * @param session
	 * @param addressFrom
	 * @param addressTos
	 * @param subject
	 * @param abbressToCC
	 * @param abbressToBCC
	 * @param contexts
	 * @param sendDate
	 * @return
	 * 赵玉柱
	 */
	private static MimeMessage createMailMessage(Session session, InternetAddress addressFrom, List<InternetAddress> addressTos, String subject, List<InternetAddress> abbressToCC,
			List<InternetAddress> abbressToBCC, List<Map<String,Object>> contexts, Date sendDate)
	{
		MimeMessage message = new MimeMessage(session);
		try
		{
			if (addressFrom == null)
			{
				throw new RuntimeException("邮件发件人不可为空");
			}
			//发件人
			if (addressTos == null || addressTos.isEmpty())
			{
				throw new RuntimeException("邮件收件人不可为空");
			}
			message.setFrom(addressFrom);
			//收件人
			for (InternetAddress addressTo : addressTos)
			{
				message.setRecipient(MimeMessage.RecipientType.TO, addressTo);
			}
			//抄送人
			if (abbressToCC != null && !abbressToCC.isEmpty())
			{
				for (InternetAddress addressTo : abbressToCC)
				{
					message.setRecipient(MimeMessage.RecipientType.CC, addressTo);
				}
			}
			//密送人
			if (abbressToBCC != null && !abbressToBCC.isEmpty())
			{
				for (InternetAddress addressTo : abbressToBCC)
				{
					message.setRecipient(MimeMessage.RecipientType.BCC, addressTo);
				}
			}
			if (StrUtil.isNoStrTrimNull(subject))
			{
				message.setSubject(subject, "UTF-8");
			}
			if (contexts != null && !contexts.isEmpty())
			{
				int i = 0;
				MimeMultipart multipart = new MimeMultipart();
				for (Map<String,Object> context : contexts)
				{
					if (context != null && context.containsKey("context") && context.containsKey("type"))
					{
						//添加普通文本
						String type = StrUtil.obj2str(context.get("type"));
						Object ctx = context.get("context");
						if ("text".equals(type))
						{
							createTextContext(multipart, ctx);
						} else if ("image".equals(type))
						{
							//添加图片
							createImageContext(i, multipart, context, ctx);
						} else if ("attach".equals(type))
						{
							// 添加附件
							createAttachContext(multipart, ctx);
						}
						//message.setContent(context.get("context"), StrUtil.obj2str(context.get("type")));
					}
					i++;
				}
				multipart.setSubType("mixed");
				message.setContent(multipart);
			}
			message.setSentDate(sendDate);
			message.saveChanges();
		} catch (MessagingException e)
		{
			throw new RuntimeException("报错内容", e);
		} catch (UnsupportedEncodingException e)
		{
			throw new RuntimeException("报错内容", e);
		}
		return message;
	}

	/**
	 * 创建一个附件类型的
	 * @param multipart
	 * @param ctx
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 * 赵玉柱
	 */
	private static void createAttachContext(MimeMultipart multipart, Object ctx) throws MessagingException, UnsupportedEncodingException
	{
		MimeBodyPart attach = new MimeBodyPart();
		//							attach.setContent("", "text/html;charset=UTF-8");
		DataSource ds = null;
		if (File.class.isAssignableFrom(ctx.getClass()))
		{
			ds = new FileDataSource((File) ctx);
		} else if (String.class.equals(ctx.getClass()))
		{
			ds = new FileDataSource(new File(StrUtil.obj2str(ctx)));
		}
		DataHandler dh = new DataHandler(ds);
		attach.setDataHandler(dh);
		attach.setFileName(MimeUtility.encodeText(ds.getName()));
		multipart.addBodyPart(attach);
	}

	/**
	 * 创建一个 图片类型的 part
	 * @param i
	 * @param multipart
	 * @param context
	 * @param ctx
	 * @throws MessagingException
	 * 赵玉柱
	 */
	private static void createImageContext(int i, MimeMultipart multipart, Map<String,Object> context, Object ctx) throws MessagingException
	{
		MimeBodyPart image = new MimeBodyPart();
		DataSource ds = null;
		if (File.class.isAssignableFrom(ctx.getClass()))
		{
			ds = new FileDataSource((File) ctx);
		} else if (String.class.equals(ctx.getClass()))
		{
			ds = new FileDataSource(new File(StrUtil.obj2str(ctx)));
		}
		DataHandler dh = new DataHandler(ds);
		//							image.setContent("我是图片解释", "text/html; charset=utf-8");
		image.setDataHandler(dh);
		image.setContentID("image_" + i);
		MimeBodyPart text = new MimeBodyPart();
		//			
		String html = StrUtil.obj2str(context.get("html"));
		text.setContent((StrUtil.isStrTrimNull(html) ? "" : html) + "<img src='cid:" + "image_" + i + "'/><br/>", "text/html; charset=utf-8");
		MimeMultipart mm_text_image = new MimeMultipart("related");
		mm_text_image.addBodyPart(text);
		mm_text_image.addBodyPart(image);
		mm_text_image.setSubType("related");
		MimeBodyPart text_image = new MimeBodyPart();
		text_image.setContent(mm_text_image);
		multipart.addBodyPart(text_image);
	}

	/**
	 * 创建一个普通文本类型的bodypart
	 * @param multipart
	 * @param ctx
	 * @throws MessagingException
	 * 赵玉柱
	 */
	private static void createTextContext(MimeMultipart multipart, Object ctx) throws MessagingException
	{
		//普通文本
		MimeMultipart mimeMultipart = new MimeMultipart();
		MimeBodyPart text = new MimeBodyPart();
		text.setContent(ctx + "<br/>", "text/html; charset=utf-8");
		mimeMultipart.addBodyPart(text);
		MimeBodyPart textPart = new MimeBodyPart();
		mimeMultipart.setSubType("related");
		textPart.setContent(mimeMultipart);
		multipart.addBodyPart(textPart);
	}

	public static void main(String[] args)
	{
		try
		{
			InternetAddress addressFrom = new InternetAddress("963272311@qq.com", "赵玉柱", "UTF-8");
			InternetAddress addressFrom163 = new InternetAddress("18513455445@163.com", "赵玉柱", "UTF-8");
			InternetAddress addressFrom126 = new InternetAddress("18513455445@163.com", "zyz-test", "UTF-8");
			List<InternetAddress> addressTos = new ArrayList<>();
			//			InternetAddress addressTo = new InternetAddress("1121646970@qq.com", "我不是小丑皇", "UTF-8");
			//			addressTos.add(addressTo);
			InternetAddress addressTo1 = new InternetAddress("963272311@qq.com", "赵玉柱", "UTF-8");
			addressTos.add(addressTo1);
			String subject = "激活邮箱";
			List<InternetAddress> abbressToCCs = new ArrayList<>();
			InternetAddress abbressToCC = new InternetAddress("963272311@qq.com", "赵玉柱", "UTF-8");
			abbressToCCs.add(abbressToCC);
			List<InternetAddress> abbressToBCCs = new ArrayList<>();
			InternetAddress abbressToBCC = new InternetAddress("963272311@qq.com", "赵玉柱", "UTF-8");
			abbressToCCs.add(abbressToBCC);
			List<Map<String,Object>> contexts = new ArrayList<>();
			Map<String,Object> contextMap = new HashMap<String,Object>();
			contextMap.put("context", "验证码:" + new Random().nextInt(9999) + "，请在12小时之内完成注册,<a href='http://www.liiwin.com'>注册地址</a>");
			contextMap.put("type", "text");
			contexts.add(contextMap);
			Map<String,Object> contextMap1 = new HashMap<String,Object>();
			contextMap1.put("context", "D:\\1.jpg");
			contextMap1.put("type", "image");
			contextMap1.put("html", "<a href='http://www.baidu.com'>我看一下这个</a>");
			contexts.add(contextMap1);
			Map<String,Object> contextMap3 = new HashMap<String,Object>();
			contextMap3.put("context", "D:\\2.jpg");
			contextMap3.put("type", "image");
			contexts.add(contextMap3);
			Map<String,Object> contextMap4 = new HashMap<String,Object>();
			contextMap4.put("html", "<a href='http://www.baidu.com'>嗯的图片</a>");
			contextMap4.put("context", "D:\\TestDrawingString.jpg");
			contextMap4.put("type", "image");
			contexts.add(contextMap4);
			Map<String,Object> contextMap2 = new HashMap<String,Object>();
			contextMap2.put("context", "D:\\阿里巴巴Java开发手册.pdf");
			contextMap2.put("type", "attach");
			contexts.add(contextMap2);
			Date sendDate = new Date();
			//SendMailDemo(addressFrom, addressTos, subject, abbressToCCs, abbressToBCCs, contexts, sendDate);
			sendEMail(BasConfig.getPropertie("E-MAIL-QQ-USER"), BasConfig.getPropertie("E-MAIL-QQ-PWD"), addressFrom, addressTos, subject, abbressToCCs, abbressToBCCs, contexts, sendDate);
			//			send163EMail(BasConfig.getPropertie("E-MAIL-163-USER"), BasConfig.getPropertie("E-MAIL-163-PWD"), addressFrom163, addressTos, subject, null, null, contexts, sendDate);
			//Send126EMail("18513455445@163.com", "521419521419zyz", addressFrom163, addressTos, subject, abbressToCCs, abbressToBCCs, contexts, sendDate);
		} catch (UnsupportedEncodingException e)
		{
			throw new RuntimeException("报错内容", e);
		}
	}
}
