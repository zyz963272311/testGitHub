package xyz.zyzhu.spring.boot.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.liiwin.utils.StrUtil;
import com.liiwin.wechat.WeChatUtil;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import xyz.zyzhu.spring.boot.utils.WXUtils;
/** 
 * <p>标题： 微信请求servlet</p>
 * <p>功能： </p>
 * <p>所属模块： boot</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年1月4日 下午10:36:46</p>
 * <p>类全名：xyz.zyzhu.spring.boot.servlet.WeCharServlet</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class WeChatServlet extends HttpServlet
{
	private static final long serialVersionUID = 3797138290938306115L;
	// 实例化 统一业务API入口

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		if (WeChatUtil.checkSignature(timestamp, nonce, signature))
		{
			out.print(echostr);
		}
		out.print("error");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String signature = request.getParameter("signature");
		String nonce = request.getParameter("nonce");
		String timestamp = request.getParameter("timestamp");
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		boolean checkSignature = WeChatUtil.checkSignature(timestamp, nonce, signature);
		if (!checkSignature)
		{
			response.getWriter().println("非法请求");
			return;
		}
		String echostr = request.getParameter("echostr");
		if (StrUtil.isNoStrTrimNull(echostr))
		{
			// 说明是一个仅仅用来验证的请求，回显echostr
			response.getWriter().println(echostr);
			return;
		}
		String encryptType = request.getParameter("encrypt_type");
		encryptType = StrUtil.isNoStrTrimNull(encryptType) ? encryptType : "raw";
		WxMpXmlMessage inMessage = null;
		if ("raw".equals(encryptType))
		{
			inMessage = WxMpXmlMessage.fromXml(request.getInputStream());
		} else if ("aes".equals(encryptType.toLowerCase()))
		{
			String msgSignature = request.getParameter("msg_signature");
			inMessage = WxMpXmlMessage.fromEncryptedXml(request.getInputStream(), WeChatUtil.getMpMemoryStorage(), timestamp, nonce, msgSignature);
		} else
		{
			response.getWriter().println("不可识别的加密类型");
			return;
		}
		//获取消息的路由
		WxMpMessageRouter wxMpMessageRouter = WXUtils.getRouter();
		//使用消息的路由进行消息的处理
		WxMpXmlOutMessage outMessage = wxMpMessageRouter.route(inMessage);
		if (outMessage != null)
		{
			if ("raw".equals(encryptType))
			{
				response.getWriter().write(outMessage.toXml());
			} else if ("aes".equals(encryptType))
			{
				response.getWriter().write(outMessage.toEncryptedXml(WeChatUtil.getMpMemoryStorage()));
			}
			return;
		}
	}
}
