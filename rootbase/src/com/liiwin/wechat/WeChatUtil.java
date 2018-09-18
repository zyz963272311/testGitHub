package com.liiwin.wechat;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
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
	private static WxMpService					wxMPService;
	private static WxMpInMemoryConfigStorage	memoryStorage		= null;
	private static String						appid				= BasConfig.getPropertie("wx_appid");
	private static String						secret				= BasConfig.getPropertie("wx_secret");
	private static String						token				= BasConfig.getPropertie("wx_token");
	private static String						encodingAESKey		= BasConfig.getPropertie("wx_EncodingAESKey");
	//运行锁
	//wxMPService运行锁
	private static Lock							wxMPServiceLock		= new ReentrantLock();
	//memoryStorage运行锁
	private static Lock							memoryStorageLock	= new ReentrantLock();

	/**
	 * 获取基于内存的配置
	 * @return
	 * 赵玉柱
	 */
	public static WxMpInMemoryConfigStorage getMpMemoryStorage(boolean forceUpdate)
	{
		try
		{
			memoryStorageLock.lock();
			if (memoryStorage == null || forceUpdate)
			{
				memoryStorage = WxMpInMemoryConfigStorageFactory.newFactory().getMemoryStore(forceUpdate);
				if (StrUtil.isStrTrimNull(appid))
				{
					throw new RuntimeException("请设置wx_appid");
				}
				if (StrUtil.isStrTrimNull(token))
				{
					throw new RuntimeException("请设置wx_token");
				}
				memoryStorage.setAppId(appid);
				memoryStorage.setSecret(secret);
				memoryStorage.setToken(token);
				memoryStorage.setAesKey(encodingAESKey);
			}
			return memoryStorage;
		} finally
		{
			memoryStorageLock.unlock();
		}
	}

	/**
	 * 
	 * @return
	 * 赵玉柱
	 */
	public static WxMpInMemoryConfigStorage getMpMemoryStorage()
	{
		return getMpMemoryStorage(false);
	}

	/**
	 * 获取微信service
	 * @return
	 * 赵玉柱
	 */
	public static WxMpService wxMPService()
	{
		try
		{
			wxMPServiceLock.lock();
			if (wxMPService == null)
			{
				wxMPService = new WxMpServiceImpl();
				wxMPService.setWxMpConfigStorage(getMpMemoryStorage());
			}
		} finally
		{
			wxMPServiceLock.unlock();
		}
		return wxMPService;
	}

	/**
	 * 验证消息是否合法
	 * @param timestamp
	 * @param nonce
	 * @param signature
	 * @return
	 * 赵玉柱
	 */
	public static boolean checkSignature(String timestamp, String nonce, String signature)
	{
		boolean checkResult = wxMPService().checkSignature(timestamp, nonce, signature);
		return checkResult;
	}
}
