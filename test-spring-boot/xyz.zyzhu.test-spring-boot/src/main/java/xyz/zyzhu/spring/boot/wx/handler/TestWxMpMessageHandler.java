package xyz.zyzhu.spring.boot.wx.handler;

import java.util.Map;
import com.liiwin.date.DateUtil;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;
/**
 * <p>标题： 测试微信消息处理器</p>
 * <p>功能： </p>
 * <p>所属模块： boot</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月18日 下午3:23:26</p>
 * <p>类全名：xyz.zyzhu.spring.boot.wx.handler.TestWxMpMessageHandler</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestWxMpMessageHandler implements WxMpMessageHandler
{
	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String,Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException
	{
		WxMpXmlOutTextMessage outMessage = new WxMpXmlOutTextMessage();
		outMessage.setCreateTime(DateUtil.getCurDate().getTime());
		outMessage.setFromUserName(wxMessage.getToUser());
		outMessage.setToUserName(wxMessage.getFromUser());
		outMessage.setContent("Hello World");
		return outMessage;
	}
}
