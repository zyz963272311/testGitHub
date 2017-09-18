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
import javax.activation.DataHandler;
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
	 * 
	 * @param userAddress
	 * @param password
	 * @param addressFrom
	 * @param addressTos
	 * @param subject
	 * @param abbressToCC
	 * @param abbressToBCC
	 * @param contexts
	 * @param sendDate
	 * 赵玉柱
	 */
	public static void SendQQEMail(String userAddress, String password, InternetAddress addressFrom, List<InternetAddress> addressTos, String subject, List<InternetAddress> abbressToCC,
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

	public static void sendEMail(String userAddress, String password, InternetAddress addressFrom, List<InternetAddress> addressTos, String subject, List<InternetAddress> abbressToCC,
			List<InternetAddress> abbressToBCC, List<Map<String,Object>> contexts, Date sendDate)
	{
		if (StrUtil.isStrTrimNull(userAddress))
		{
			throw new RuntimeException("邮箱用户名称不可为空");
		}
		if (userAddress.endsWith("163.com"))
		{
			Send163EMail(userAddress, password, addressFrom, addressTos, subject, abbressToCC, abbressToBCC, contexts, sendDate);
		} else if (userAddress.endsWith("qq.com"))
		{
			SendQQEMail(userAddress, password, addressFrom, addressTos, subject, abbressToCC, abbressToBCC, contexts, sendDate);
		} else if (userAddress.endsWith("126.com"))
		{
			Send126EMail(userAddress, password, addressFrom, addressTos, subject, abbressToCC, abbressToBCC, contexts, sendDate);
		}
	}

	/**
	 * 
	 * @param userAddress
	 * @param password
	 * @param addressFrom
	 * @param addressTos
	 * @param subject
	 * @param abbressToCC
	 * @param abbressToBCC
	 * @param contexts
	 * @param sendDate
	 * 赵玉柱
	 */
	public static void Send163EMail(String userAddress, String password, InternetAddress addressFrom, List<InternetAddress> addressTos, String subject, List<InternetAddress> abbressToCC,
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

	public static void Send126EMail(String userAddress, String password, InternetAddress addressFrom, List<InternetAddress> addressTos, String subject, List<InternetAddress> abbressToCC,
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
							//普通文本
							MimeBodyPart text = new MimeBodyPart();
							text.setContent(ctx, "text/html;charset=UTF-8");
							multipart.addBodyPart(text);
						} else if ("image".equals(type))
						{
							//添加图片
							MimeBodyPart image = new MimeBodyPart();
							DataHandler dh = null;
							if (File.class.isAssignableFrom(ctx.getClass()))
							{
								dh = new DataHandler(new FileDataSource((File) ctx));
							} else if (String.class.equals(ctx.getClass()))
							{
								dh = new DataHandler(new FileDataSource(new File(StrUtil.obj2str(ctx))));
							}
							image.setDataHandler(dh);
							image.setContentID("image_" + i);
							MimeBodyPart text = new MimeBodyPart();
							text.setContent("<img src='cid:" + "image_" + i + "'/>", "text/html;charset=UTF-8");
							MimeMultipart mm_text_image = new MimeMultipart();
							mm_text_image.addBodyPart(text);
							mm_text_image.addBodyPart(image);
							mm_text_image.setSubType("related");
							MimeBodyPart text_image = new MimeBodyPart();
							text_image.setContent(mm_text_image);
							multipart.addBodyPart(text_image);
						} else if ("attach".equals(type))
						{
							// 添加附件
							MimeBodyPart attach = new MimeBodyPart();
							DataHandler dh = null;
							if (File.class.isAssignableFrom(ctx.getClass()))
							{
								dh = new DataHandler(new FileDataSource((File) ctx));
							} else if (String.class.equals(ctx.getClass()))
							{
								dh = new DataHandler(new FileDataSource(new File(StrUtil.obj2str(ctx))));
							}
							attach.setFileName(MimeUtility.encodeText(dh.getName()));
							multipart.addBodyPart(attach);
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

	public static void main(String[] args)
	{
		try
		{
			InternetAddress addressFrom = new InternetAddress("963272311@qq.com", "赵玉柱", "UTF-8");
			InternetAddress addressFrom163 = new InternetAddress("18513455445@163.com", "赵玉柱", "UTF-8");
			InternetAddress addressFrom126 = new InternetAddress("18513455445@163.com", "zyz-test", "UTF-8");
			List<InternetAddress> addressTos = new ArrayList<>();
			InternetAddress addressTo = new InternetAddress("963272311@qq.com", "赵玉柱", "UTF-8");
			addressTos.add(addressTo);
			String subject = "测试发送邮箱";
			List<InternetAddress> abbressToCCs = new ArrayList<>();
			InternetAddress abbressToCC = new InternetAddress("963272311@qq.com", "赵玉柱", "UTF-8");
			abbressToCCs.add(abbressToCC);
			List<InternetAddress> abbressToBCCs = new ArrayList<>();
			InternetAddress abbressToBCC = new InternetAddress("963272311@qq.com", "赵玉柱", "UTF-8");
			abbressToCCs.add(abbressToBCC);
			List<Map<String,Object>> contexts = new ArrayList<>();
			Map<String,Object> contextMap = new HashMap<String,Object>();
			contextMap.put("context", "context");
			contextMap.put("type", "text");
			contexts.add(contextMap);
			Map<String,Object> contextMap1 = new HashMap<String,Object>();
			contextMap1.put("context", "D://textQRCode.png");
			contextMap1.put("type", "image");
			contexts.add(contextMap1);
			Map<String,Object> contextMap2 = new HashMap<String,Object>();
			contextMap2.put("context", "D://test0000001.pdf");
			contextMap2.put("type", "attach");
			contexts.add(contextMap2);
			Date sendDate = new Date();
			//SendMailDemo(addressFrom, addressTos, subject, abbressToCCs, abbressToBCCs, contexts, sendDate);
			SendQQEMail("963272311@qq.com", "irowtqpmmsjubeee", addressFrom, addressTos, subject, abbressToCCs, abbressToBCCs, contexts, sendDate);
			//			Send163EMail("18513455445@163.com", "521419521419zyz", addressFrom163, addressTos, subject, abbressToCCs, abbressToBCCs, contexts, sendDate);
			//Send126EMail("18513455445@163.com", "521419521419zyz", addressFrom163, addressTos, subject, abbressToCCs, abbressToBCCs, contexts, sendDate);
		} catch (UnsupportedEncodingException e)
		{
			throw new RuntimeException("报错内容", e);
		}
	}
}
