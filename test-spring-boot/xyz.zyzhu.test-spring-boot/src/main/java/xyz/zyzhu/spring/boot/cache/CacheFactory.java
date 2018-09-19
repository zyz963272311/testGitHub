package xyz.zyzhu.spring.boot.cache;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import com.liiwin.utils.StrUtil;
import xyz.zyzhu.spring.boot.model.BasModel;
import xyz.zyzhu.spring.boot.utils.ModelUtils;
import xyz.zyzhu.spring.boot.utils.PropertiesUtils;
import xyz.zyzhu.spring.boot.utils.SpringBeanUtils;
/**
 * <p>标题： 缓存工厂</p>
 * <p>功能： </p>
 * <p>所属模块： boot</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月19日 下午12:14:08</p>
 * <p>类全名：xyz.zyzhu.spring.boot.cache.CacheFactory</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class CacheFactory
{
	//缓存管理
	protected CacheManager		cacheManager;
	//缓存工厂
	private static CacheFactory	factory				= null;
	//工厂锁
	private static Lock			factoryLoch			= new ReentrantLock();
	//缓存锁
	private Lock				cacheManagerLock	= new ReentrantLock();

	protected CacheFactory()
	{
	}

	/**
	 * 获取工厂
	 * @return
	 * 赵玉柱
	 */
	public static CacheFactory factory()
	{
		try
		{
			factoryLoch.lock();
			if (factory == null)
			{
				factory = new CacheFactory();
			}
		} finally
		{
			factoryLoch.unlock();
		}
		return factory;
	}

	public CacheManager cacheManager()
	{
		return cacheManager(false);
	}

	public CacheManager cacheManager(boolean forceUpdate)
	{
		try
		{
			cacheManagerLock.lock();
			if (cacheManager == null || forceUpdate)
			{
				String cacheManagerName = PropertiesUtils.getPropValue("cacheManagerName");
				CacheManager bean = null;
				if (!StrUtil.isStrTrimNull(cacheManagerName))
				{
					bean = SpringBeanUtils.getBean(cacheManagerName, CacheManager.class);
					if (bean != null)
					{
						cacheManager = bean;
					}
				}
				if (bean == null)
				{
					cacheManager = defaultCacheManager();
				} else
				{
					cacheManager = bean;
				}
			}
		} finally
		{
			cacheManagerLock.unlock();
		}
		return cacheManager;
	}

	public <T extends BasModel> Cache getModelCache(Class<T> clazz)
	{
		if (clazz == null)
		{
			return null;
		}
		String modelTable = ModelUtils.getModelTable(clazz);
		String simpleName = clazz.getSimpleName();
		Cache cache = cacheManager().getCache(simpleName + "|" + modelTable);
		return cache;
	}

	/**
	 * 获取默认的缓存manager
	 * @return
	 * 赵玉柱
	 */
	protected CacheManager defaultCacheManager()
	{
		return SpringBeanUtils.getBean("RedisCacheManager", CacheManager.class);
	}
}
