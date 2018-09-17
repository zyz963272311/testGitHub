package com.liiwin.wechat;

import com.liiwin.config.BasConfig;
import com.liiwin.date.Redis;
import com.liiwin.utils.BeanUtils;
import com.liiwin.utils.StrUtil;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInRedisConfigStorage;
/**
 * <p>标题： 微信基于内存的配置 工厂</p>
 * <p>功能： </p>
 * <p>所属模块： rootbas</p>
 * <p>版权： Copyright © 2018 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2018年9月17日 下午1:08:31</p>
 * <p>类全名：com.liiwin.wechat.WxMpInMemoryConfigStorageFactory</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class WxMpInMemoryConfigStorageFactory
{
	private static WxMpInMemoryConfigStorageFactory	factory			= null;;
	private WxMpInMemoryConfigStorage				configStorage	= null;

	/**
	 * 构造方法私有化
	 */
	private WxMpInMemoryConfigStorageFactory()
	{
	}

	/**
	 * 单例
	 * @return
	 * 赵玉柱
	 */
	public static WxMpInMemoryConfigStorageFactory newFactory()
	{
		synchronized (factory)
		{
			if (factory == null)
			{
				factory = new WxMpInMemoryConfigStorageFactory();
			}
		}
		return factory;
	}

	/**
	 * 强制获取新的storage
	 * @param forceUpdate
	 * @return
	 * 赵玉柱
	 */
	public WxMpInMemoryConfigStorage getMemoryStore(boolean forceUpdate)
	{
		synchronized (configStorage)
		{
			if (configStorage == null || forceUpdate)
			{
				String configStorageClass = BasConfig.getPropertie("WxMpInMemoryConfigStorageFactory");
				if (StrUtil.isStrTrimNull(configStorageClass))
				{
					Class<Object> classByPath = BeanUtils.getClassByPath(configStorageClass);
					if (classByPath != null && (classByPath.isAssignableFrom(WxMpInMemoryConfigStorage.class)))
					{
						if (classByPath.equals(WxMpInMemoryConfigStorage.class))
						{
							configStorage = new WxMpInMemoryConfigStorage();
						} else if (classByPath.equals(WxMpInRedisConfigStorage.class))
						{
							configStorage = new WxMpInRedisConfigStorage(new Redis().getJedisPool());
						}
					}
				}
				if (configStorage == null)
				{
					configStorage = new WxMpInMemoryConfigStorage();
				}
			}
		}
		return configStorage;
	}

	/**
	 * 获取storage
	 * @return
	 * 赵玉柱
	 */
	public WxMpInMemoryConfigStorage getMemoryStore()
	{
		return getMemoryStore(false);
	}
}
