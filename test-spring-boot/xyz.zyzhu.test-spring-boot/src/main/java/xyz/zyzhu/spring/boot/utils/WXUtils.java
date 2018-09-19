package xyz.zyzhu.spring.boot.utils;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import com.liiwin.utils.BeanUtils;
import com.liiwin.utils.StrUtil;
import com.liiwin.wechat.WeChatUtil;
import jodd.util.collection.SortedArrayList;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpMessageInterceptor;
import me.chanjar.weixin.mp.api.WxMpMessageMatcher;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpMessageRouterRule;
import xyz.zyzhu.spring.boot.cache.CacheFactory;
import xyz.zyzhu.spring.boot.comparator.ModelComparator;
import xyz.zyzhu.spring.boot.db.BootDatabase;
import xyz.zyzhu.spring.boot.db.BootDatabasePoolManager;
import xyz.zyzhu.spring.boot.model.WXToolsRouter;
/**
 * <p>标题： 微信开发工具类</p>
 * <p>功能： </p>
 * <p>所属模块： 微信开发工具类</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年7月30日 下午2:18:23</p>
 * <p>类全名：xyz.zyzhu.spring.boot.utils.WXUtils</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class WXUtils
{
	private static WxMpMessageRouter	router			= null;
	//运行锁
	//router运行锁
	private static Lock					routerLock		= new ReentrantLock();
	//查询锁
	private static Lock					routerQueryLock	= new ReentrantLock();

	/**
	 * 获取一个微信路由
	 * @return
	 * 赵玉柱
	 */
	public static WxMpMessageRouter getRouter()
	{
		return getRouter(false);
	}

	/**
	 * 获取一个微信路由 如果微信路由为空或需要强制刷新，则new一个新的路由
	 * @param updateForce
	 * @return
	 * 赵玉柱
	 */
	public static WxMpMessageRouter getRouter(boolean updateForce)
	{
		try
		{
			routerLock.lock();
			if (router == null || updateForce)
			{
				router = new WxMpMessageRouter(WeChatUtil.wxMPService());
				WXToolsRouter queryRouter = new WXToolsRouter();
				List<WXToolsRouter> routerModels = getRouterModels(queryRouter, true);
				if (routerModels != null && !routerModels.isEmpty())
				{
					for (WXToolsRouter r : routerModels)
					{
						WxMpMessageRouterRule rule = router.rule();
						//private String				fromUser;									//用户来源
						//private String				msgType;									//消息类型
						//private String				event;										//事件
						//private String				eventKey;									//事件key
						//private String				eventKeyRegex;								//事件key通配
						//private String				content;									//内容
						//private String				rContent;									//内容
						//private String				matcherClassPath;							//匹配器路径
						//private String				interClassPath;								//拦截器路径
						//private String				handlerClassPath;							//处理器路径
						String fromUser = r.getFromUser();//用户来源
						String msgType = r.getMsgType();//消息类型
						String eventKey = r.getEventKey();//事件key
						String eventKeyRegex = r.getEventKeyRegex();//事件key正则匹配
						String content = r.getContent();//内容匹配
						String rcontent = r.getRcontent();//内容匹配正则
						String matcherClassPath = r.getMatcherClassPath();//匹配器类路径
						String interClassPath = r.getInterClassPath();//拦截器类路径
						String handlerClassPath = r.getHandlerClassPath();//处理器类路径
						boolean isEnd = r.isEnd();//是否当前消息在此完结
						boolean async = r.isAsync();
						rule.async(async);
						if (!StrUtil.isStrTrimNull(handlerClassPath))
						{
							Class<WxMpMessageHandler> handlerClazz = BeanUtils.getClassByPath(handlerClassPath, false);
							if (handlerClazz != null && handlerClazz.isAssignableFrom(WxMpMessageHandler.class))
							{
								WxMpMessageHandler handler = BeanUtils.newInstanceByClass(handlerClazz, null, null, false);
								if (handler != null)
								{
									rule.handler(handler);
								}
							}
						}
						if (!StrUtil.isStrTrimNull(fromUser))
						{
							rule.fromUser(fromUser);
						}
						if (!StrUtil.isStrTrimNull(msgType))
						{
							rule.msgType(msgType);
						}
						if (!StrUtil.isStrTrimNull(eventKey))
						{
							rule.eventKey(eventKey);
						}
						if (!StrUtil.isStrTrimNull(eventKeyRegex))
						{
							rule.eventKeyRegex(eventKeyRegex);
						}
						if (!StrUtil.isStrTrimNull(content))
						{
							rule.content(content);
						}
						if (StrUtil.isStrTrimNull(rcontent))
						{
							rule.rContent(rcontent);
						}
						if (!StrUtil.isStrTrimNull(matcherClassPath))
						{
							Class<WxMpMessageMatcher> clazz = BeanUtils.getClassByPath(matcherClassPath, false);
							if (clazz != null && clazz.isAssignableFrom(WxMpMessageMatcher.class))
							{
								WxMpMessageMatcher matcher = BeanUtils.newInstanceByClass(clazz, null, null, false);
								if (matcher != null)
								{
									rule.matcher(matcher);
								}
							}
						}
						if (!StrUtil.isStrTrimNull(interClassPath))
						{
							Class<WxMpMessageInterceptor> clazz = BeanUtils.getClassByPath(interClassPath, false);
							if (clazz != null && clazz.isAssignableFrom(WxMpMessageInterceptor.class))
							{
								WxMpMessageInterceptor interceptor = BeanUtils.newInstanceByClass(clazz, null, null, false);
								if (interceptor != null)
								{
									rule.interceptor(interceptor);
								}
							}
						}
						if (isEnd)
						{
							rule.end();
						} else
						{
							rule.next();
						}
					}
				}
			}
		} finally
		{
			routerLock.unlock();
		}
		return router;
	}

	public static List<WXToolsRouter> getRouterModels(WXToolsRouter queryRouter, boolean removeDisabled)
	{
		return getRouterModels(queryRouter, removeDisabled, true);
	}

	@SuppressWarnings("unchecked")
	public static List<WXToolsRouter> getRouterModels(WXToolsRouter queryRouter, boolean removeDisabled, boolean removeDisAvailable)
	{
		List<WXToolsRouter> routers = null;
		try
		{
			routerQueryLock.lock();
			if (queryRouter == null)
			{
				queryRouter = new WXToolsRouter();
			}
			BootDatabase db = null;
			try
			{
				Cache cache = CacheFactory.factory().getModelCache(WXToolsRouter.class);
				String modelTable = ModelUtils.getModelTable(WXToolsRouter.class);
				ValueWrapper valueWrapper = cache.get(modelTable);
				//				List<WXToolsRouter> query = db.query(queryRouter);
				List<WXToolsRouter> query = null;
				if (valueWrapper != null)
				{
					Object object = valueWrapper.get();
					if (object != null)
					{
						query = (List<WXToolsRouter>) object;
					}
				}
				if (query == null)
				{
					db = BootDatabasePoolManager.getDatabaseByClass(WXToolsRouter.class, false);
					query = db.query(queryRouter);
					cache.put(modelTable, query);
				}
				ModelComparator<WXToolsRouter> comparator = new ModelComparator<>("node");
				routers = new SortedArrayList<>(comparator);
				if (query != null)
				{
					routers.addAll(query);
				}
			} finally
			{
				if (db != null)
				{
					BootDatabasePoolManager.close(db);
				}
			}
		} finally
		{
			routerQueryLock.unlock();
		}
		//从后往前进行数据移除
		if ((removeDisabled || removeDisAvailable) && routers != null)
		{
			int length = routers.size();
			for (int i = length - 1; i >= 0; i--)
			{
				WXToolsRouter router = routers.get(i);
				if (removeDisabled)
				{
					boolean disable = router.isDisable();
					if (disable)
					{
						routers.remove(i);
					}
				} else
				{
					boolean available = router.isAvailable();
					if (!available)
					{
						routers.remove(i);
					}
				}
			}
		}
		return routers;
	}

	public static List<WXToolsRouter> getRouterModels(WXToolsRouter queryRouter)
	{
		return getRouterModels(queryRouter, true);
	}
}
