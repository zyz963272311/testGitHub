package xyz.zyzhu.spring.boot.wx.matcher;

import java.text.SimpleDateFormat;
import com.soecode.wxtools.api.WxMessageMatcher;
import com.soecode.wxtools.bean.WxXmlMessage;
/**
 * <p>标题： 星座匹配器</p>
 * <p>功能： </p>
 * <p>所属模块： 微信</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年1月17日 下午8:10:42</p>
 * <p>类全名：xyz.zyzhu.spring.boot.wx.matcher.ConstellationMatcher</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class ConstellationMatcher implements WxMessageMatcher
{
	@Override
	public boolean match(WxXmlMessage message)
	{
		SimpleDateFormat formate = new SimpleDateFormat("yyyyMMdd");
		formate.setLenient(false);
		try
		{
			//采用闰年得方式，可以校验通过2月29日
			formate.parse("1980" + message.getContent());
			return true;
		} catch (Exception e)
		{
			return false;
		}
	}
}
