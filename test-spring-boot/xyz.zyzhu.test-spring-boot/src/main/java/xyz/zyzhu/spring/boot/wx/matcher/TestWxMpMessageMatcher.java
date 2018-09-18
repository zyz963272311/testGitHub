package xyz.zyzhu.spring.boot.wx.matcher;

import me.chanjar.weixin.mp.api.WxMpMessageMatcher;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
/**
 * <p>标题： 测试微信消息匹配器</p>
 * <p>功能： </p>
 * <p>所属模块： boot</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月18日 下午3:20:07</p>
 * <p>类全名：xyz.zyzhu.spring.boot.wx.matcher.TestWxMpMessageMatcher</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestWxMpMessageMatcher implements WxMpMessageMatcher
{
	@Override
	public boolean match(WxMpXmlMessage message)
	{
		String content = message.getContent();
		return "Hello World".equals(content);
	}
}
