package xyz.zyzhu.spring.boot.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liiwin.utils.BeanUtils;
import com.liiwin.utils.StrUtil;
import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxMessageHandler;
import com.soecode.wxtools.api.WxMessageInterceptor;
import com.soecode.wxtools.api.WxMessageMatcher;
import com.soecode.wxtools.api.WxMessageRouter;
import com.soecode.wxtools.api.WxMessageRouterRule;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.util.xml.XStreamTransformer;

import xyz.zyzhu.spring.boot.model.WXRouter;
import xyz.zyzhu.spring.boot.service.WXRouterService;
import xyz.zyzhu.spring.boot.utils.SpringBeanUtils;
/** 
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
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
	private static final long	serialVersionUID	= 3797138290938306115L;
	// 实例化 统一业务API入口
	private IService			iService			= new WxService();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		if (iService.checkSignature(signature, timestamp, nonce, echostr))
		{
			out.print(echostr);
		}
		out.print("error");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 返回消息给微信服务器
		PrintWriter out = response.getWriter();
		// 创建一个路由器
		WxMessageRouter router = new WxMessageRouter(iService);
		try
		{
			// 微信服务器推送过来的是XML格式。
			WxXmlMessage wx = XStreamTransformer.fromXml(WxXmlMessage.class, request.getInputStream());
			System.out.println("消息：\n " + wx.toString());
			// 添加规则；这里的规则是所有消息都交给DemoMatcher处理，交给DemoInterceptor处理，交给DemoHandler处理
			// 注意！！每一个规则，必须由end()或者next()结束。不然不会生效。
			// end()是指消息进入该规则后不再进入其他规则。 而next()是指消息进入了一个规则后，如果满足其他规则也能进入，处理。
			//			router.rule().matcher(new DemoMatcher()).interceptor(new DemoInterceptor()).handler(new DemoHandler()).end();
			//匹配生日到星座
			WXRouterService service = SpringBeanUtils.getBean(WXRouterService.class);
			if(service == null)
			{
				throw new RuntimeException("获取service失败"+WXRouterService.class);
			}
			List<WXRouter> queryList = service.queryList();
			if(queryList!=null)
			{
				for(WXRouter wxRouter:queryList)
				{
					if(wxRouter.isEnable())
					{
						String matcherClassPath = wxRouter.getMatcherClassPath();
						WxMessageRouterRule rule = router.rule();
						String handlerClassPath = wxRouter.getHandlerClassPath();
						String interClassPath = wxRouter.getInterClassPath();
						boolean notAllNull = false;
						if(StrUtil.isNoStrTrimNull(matcherClassPath))
						{
							WxMessageMatcher matcher = (WxMessageMatcher) BeanUtils.newInstance(matcherClassPath);
							rule.matcher(matcher);
							notAllNull = true;
						}
						if(StrUtil.isNoStrTrimNull(interClassPath))
						{
							WxMessageInterceptor interceptor = (WxMessageInterceptor) BeanUtils.newInstance(interClassPath);
							rule.interceptor(interceptor);
							notAllNull = true;
						}
						if(StrUtil.isNoStrTrimNull(handlerClassPath))
						{
							WxMessageHandler handler = (WxMessageHandler) BeanUtils.newInstance(handlerClassPath);
							rule.handler(handler);
							notAllNull = true;
						}
						if(notAllNull)
						{
							if(wxRouter.isEnd())
							{
								rule.end();
							}
							else
							{
								rule.next();
							}
						}
					}
				}
			}
			// 把消息传递给路由器进行处理
			WxXmlOutMessage xmlOutMsg = router.route(wx);
			if (xmlOutMsg != null)
				out.print(xmlOutMsg.toXml());// 因为是明文，所以不用加密，直接返回给用户。
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			out.close();
		}
	}
}
