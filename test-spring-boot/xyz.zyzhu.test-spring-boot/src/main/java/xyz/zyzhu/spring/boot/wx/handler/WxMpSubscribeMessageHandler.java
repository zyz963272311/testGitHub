package xyz.zyzhu.spring.boot.wx.handler;

import java.util.Map;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.liiwin.date.DateUtil;
import com.liiwin.utils.DomainUtils;
import com.liiwin.wechat.WeChatUtil;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpUserService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import xyz.zyzhu.spring.boot.db.BootDatabase;
import xyz.zyzhu.spring.boot.db.BootDatabasePoolManager;
import xyz.zyzhu.spring.boot.model.WXUser;
/**
 * <p>标题： 微信关注事件处理器</p>
 * <p>功能： </p>
 * <p>所属模块： boot-wx</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月23日 下午10:37:08</p>
 * <p>类全名：xyz.zyzhu.spring.boot.wx.handler.WxMpSubscribeMessageHandler</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class WxMpSubscribeMessageHandler implements WxMpMessageHandler
{
	private static Logger logger = LoggerFactory.getLogger(WxMpSubscribeMessageHandler.class);

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String,Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException
	{
		String fromOpenId = wxMessage.getFromUser();
		//1、获取用户信息
		WxMpUserService userService = WeChatUtil.wxMPService().getUserService();
		WxMpUser userInfo = userService.userInfo(fromOpenId);
		logger.info("用户关注公众号，用户信息:{}", userInfo.toString());
		BootDatabase db = null;
		boolean rollback = false;
		try
		{
			String openId = userInfo.getOpenId();
			db = BootDatabasePoolManager.getWriteDatabaseByClass(WXUser.class);
			WXUser query = new WXUser();
			query.setOpenId(openId);
			WXUser dbUser = db.query1(query);
			if (dbUser != null)
			{
				//用户已经关注过此公众号,执行更新操作
				WXUser temp = DomainUtils.domain2Domain(userInfo, WXUser.class);
				Map<String,Object> newValues = temp.getNewValues();
				if (newValues != null && !newValues.isEmpty())
				{
					for (Entry<String,Object> entry : newValues.entrySet())
					{
						dbUser.setValue(entry.getKey(), entry.getValue());
					}
				}
				db.update(dbUser);
			} else
			{
				//执行入库操作
				dbUser = DomainUtils.domain2Domain(userInfo, WXUser.class);
				db.insert(dbUser);
			}
			db.beginTrans();
			db.commit();
			rollback = false;
		} catch (Exception e)
		{
			logger.error("用户关注公众号异常，异常原因", e.getMessage());
		} finally
		{
			if (db != null)
			{
				try
				{
					db.rollback(rollback);
				} finally
				{
					BootDatabasePoolManager.close(db);
				}
			}
		}
		WxMpXmlOutTextMessage outMessage = new WxMpXmlOutTextMessage();
		outMessage.setFromUserName(wxMessage.getToUser());
		outMessage.setToUserName(fromOpenId);
		outMessage.setContent("关注成功");
		outMessage.setCreateTime(DateUtil.getCurDate().getTime());
		return outMessage;
	}
}
