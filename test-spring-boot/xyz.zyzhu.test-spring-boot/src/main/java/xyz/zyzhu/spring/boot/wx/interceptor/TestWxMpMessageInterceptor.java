package xyz.zyzhu.spring.boot.wx.interceptor;

import java.util.Map;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageInterceptor;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
/**
 * <p>标题： 测试微信消息拦截器</p>
 * <p>功能： </p>
 * <p>所属模块： boot</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月18日 下午3:22:28</p>
 * <p>类全名：xyz.zyzhu.spring.boot.wx.interceptor.TestWxMpMessageInterceptor</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestWxMpMessageInterceptor implements WxMpMessageInterceptor
{
	@Override
	public boolean intercept(WxMpXmlMessage wxMessage, Map<String,Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException
	{
		String content = wxMessage.getContent();
		return "Hello World".equals(content);
	}
}
