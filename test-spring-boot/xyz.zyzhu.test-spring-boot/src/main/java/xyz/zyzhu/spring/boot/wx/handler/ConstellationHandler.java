package xyz.zyzhu.spring.boot.wx.handler;

import java.util.Map;
import com.liiwin.utils.StrUtil;
import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxMessageHandler;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.exception.WxErrorException;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年1月17日 下午8:39:16</p>
 * <p>类全名：xyz.zyzhu.spring.boot.wx.handler.ConstellationHandler</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class ConstellationHandler implements WxMessageHandler
{
	@Override
	public WxXmlOutMessage handle(WxXmlMessage wxMessage, Map<String,Object> context, IService iService) throws WxErrorException
	{
		String content = wxMessage.getContent();
		String[] constellationArr = { "魔羯座", "水瓶座", "双鱼座", "牡羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "魔羯座" };
		int[] constellationEdgeDay = { 20, 18, 20, 20, 20, 21, 22, 22, 22, 22, 21, 21 };
		String[] substring = StrUtil.getSubstring(content, new int[] { 0 }, new int[] { 2 });
		//月份
		int month = StrUtil.obj2int(substring[0]);
		//日
		int day = StrUtil.obj2int(substring[1]);
		String constellation = null;
		if (day >= constellationEdgeDay[month - 1])
		{
			constellation = constellationArr[month == 12 ? 0 : month];
		} else
		{
			constellation = constellationArr[month - 1];
		}
		WxXmlOutMessage xmlOutMsg = WxXmlOutMessage.TEXT().content("生日是：" + content + "\t星座是：" + constellation).toUser(wxMessage.getFromUserName()).fromUser(wxMessage.getToUserName()).build();
		return xmlOutMsg;
	}
}
