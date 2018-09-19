package com.liiwin.wechat;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
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
	private static WxMpInMemoryConfigStorageFactory	factory				= null;;
	private WxMpInMemoryConfigStorage				configStorage		= null;
	private static Lock								factoryLock			= new ReentrantLock();
	private static Lock								configStorageLock	= new ReentrantLock();

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
		try
		{
			factoryLock.lock();
			if (factory == null)
			{
				factory = new WxMpInMemoryConfigStorageFactory();
			}
		} finally
		{
			factoryLock.unlock();
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
		try
		{
			configStorageLock.lock();
			if (configStorage == null || forceUpdate)
			{
				String configStorageClass = BasConfig.getPropertie("WxMpInMemoryConfigStorageFactory");
				WxMpInMemoryConfigStorage tempStorage = null;
				if (StrUtil.isNoStrTrimNull(configStorageClass))
				{
					Class<Object> classByPath = BeanUtils.getClassByPath(configStorageClass);
					if (classByPath != null && (classByPath.isAssignableFrom(WxMpInMemoryConfigStorage.class)))
					{
						if (classByPath.equals(WxMpInMemoryConfigStorage.class))
						{
							tempStorage = new WxMpInMemoryConfigStorage();
						} else if (classByPath.equals(WxMpInRedisConfigStorage.class))
						{
							tempStorage = new WxMpInRedisConfigStorage(new Redis().getJedisPool());
						}
					}
				}
				if (tempStorage == null)
				{
					configStorage = getDefaultMemoryStore();
				} else
				{
					configStorage = tempStorage;
				}
			}
		} finally
		{
			configStorageLock.unlock();
		}
		return configStorage;
	}

	/**
	 * 此方法为后续支持拓展使用，可以继承此类，在没有配置MemoryStore的情况下，根据自己的选择获取对应的 MemoryStore
	 * @return
	 * 赵玉柱
	 */
	protected WxMpInMemoryConfigStorage getDefaultMemoryStore()
	{
		return new WxMpInMemoryConfigStorage();
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
