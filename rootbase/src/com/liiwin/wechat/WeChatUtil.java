package com.liiwin.wechat;

import com.liiwin.config.BasConfig;
import com.liiwin.utils.StrUtil;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
/**
 * <p>标题： 微信工具类</p>
 * <p>功能： </p>
 * <p>所属模块： rootbas</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2017年10月11日 上午10:42:13</p>
 * <p>类全名：com.liiwin.wechat.WeChatUtil</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class WeChatUtil
{
	private static WxMpService					wxService;
	private static WxMpInMemoryConfigStorage	storage	= new WxMpInMemoryConfigStorage();
	static
	{
		String appid = BasConfig.getPropertie("wx_appid");
		String secret = BasConfig.getPropertie("wx_secret");
		String token = BasConfig.getPropertie("wx_token");
		String encodingAESKey = BasConfig.getPropertie("wx_EncodingAESKey");
		if (StrUtil.isStrTrimNull(appid))
		{
			throw new RuntimeException("请设置wx_appid");
		}
		if (StrUtil.isStrTrimNull(secret))
		{
			throw new RuntimeException("请设置wx_secret");
		}
		if (StrUtil.isStrTrimNull(token))
		{
			throw new RuntimeException("请设置wx_token");
		}
		storage.setAppId(appid);
		storage.setSecret(secret);
		storage.setToken(token);
		storage.setAesKey(encodingAESKey);
	}

	/**
	 * 获取微信service
	 * @return
	 * 赵玉柱
	 */
	public static WxMpService wxService()
	{
		synchronized (wxService)
		{
			if (wxService == null)
			{
				wxService = new WxMpServiceImpl();
				wxService.setWxMpConfigStorage(storage);
			}
		}
		return wxService;
	}
}
