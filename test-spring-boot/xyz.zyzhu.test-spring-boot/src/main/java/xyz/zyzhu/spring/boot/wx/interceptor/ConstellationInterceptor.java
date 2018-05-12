package xyz.zyzhu.spring.boot.wx.interceptor;

import java.util.Map;
import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxMessageInterceptor;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.exception.WxErrorException;
/**
 * <p>标题： 星座拦截器</p>
 * <p>功能： </p>
 * <p>所属模块： 星座拦截器</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年1月17日 下午8:40:54</p>
 * <p>类全名：xyz.zyzhu.spring.boot.wx.interceptor.ConstellationInterceptor</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class ConstellationInterceptor implements WxMessageInterceptor
{
	@Override
	public boolean intercept(WxXmlMessage wxMessage, Map<String,Object> context, IService iService) throws WxErrorException
	{
		//仅仅拦截text类型的消息类型
		return WxConsts.XML_MSG_TEXT.equals(wxMessage.getMsgType());
	}
}
