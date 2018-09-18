package xyz.zyzhu.spring.boot.wx_old.matcher;

import com.liiwin.utils.StrUtil;
import com.soecode.wxtools.api.WxMessageMatcher;
import com.soecode.wxtools.bean.WxXmlMessage;
public class SubscribeEventMatcher implements WxMessageMatcher
{
	@Override
	public boolean match(WxXmlMessage message)
	{
		String event = message.getEvent();
		if (StrUtil.equals(event, "subscribe"))
		{
			//订阅事件
			return true;
		}
		return false;
	}
}
