package xyz.zyzhu.spring.boot.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import xyz.zyzhu.spring.boot.dao.WXRouterDao;
import xyz.zyzhu.spring.boot.db.BootDatabase;
import xyz.zyzhu.spring.boot.db.BootDatabasePoolManager;
import xyz.zyzhu.spring.boot.model.WXRouter;
/**
 * <p>标题： 微信路由dao实现类</p>
 * <p>功能： </p>
 * <p>所属模块： boot</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年7月30日 下午3:34:29</p>
 * <p>类全名：xyz.zyzhu.spring.boot.dao.impl.WXRouterDaoImpl</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Repository
public class WXRouterDaoImpl implements WXRouterDao
{
	private static final String dbname = "zyztest";
	@Cacheable(value = "WXRouterModule", key = "#id")
	@Override
	public WXRouter queryById(String id)
	{
		BootDatabase database = BootDatabasePoolManager.getDatabase(dbname);
		WXRouter router = new WXRouter();
		router.setId(id);;
		WXRouter query = database.query1(router);
		return query;
	}

	@CachePut(value = "WXRouterModule", key = "#m.id")
	@Override
	public void add(WXRouter m)
	{
		BootDatabase database = BootDatabasePoolManager.getDatabase(dbname);
		database.save(m);
	}

	@CacheEvict(value = "WXRouterModule")
	@Override
	public void delete(WXRouter m)
	{
		BootDatabase database = BootDatabasePoolManager.getDatabase(dbname);
		database.delete(m);
	}

	@CacheEvict(value = "WXRouterModule", key = "#id")
	@Override
	public void deleteById(String id)
	{
		BootDatabase database = BootDatabasePoolManager.getDatabase(dbname);
		WXRouter router = new WXRouter();
		router.setId(id);;
		database.delete(router);
	}

	@CachePut(value = "WXRouterModule", key = "#m.id")
	@Override
	public void update(WXRouter m)
	{
		BootDatabase database = BootDatabasePoolManager.getDatabase(dbname);
		database.update(m);
	}

	@Cacheable(value = "WXRouterModule")
	@Override
	public List<WXRouter> queryList()
	{
		BootDatabase database = BootDatabasePoolManager.getDatabase(dbname);
		List<WXRouter> query = database.query(new WXRouter());
		return query;
	}

	@Cacheable(value = "WXRouterModule")
	@Override
	public List<WXRouter> queryListByModule(WXRouter m)
	{
		BootDatabase database = BootDatabasePoolManager.getDatabase(dbname);
		List<WXRouter> query = database.query(m);
		return query;
	}
	@Deprecated
	@Cacheable(value = "WXRouterModule")
	@Override
	public List<WXRouter> queryListByFilter(WXRouter m, Map<String,Object> params)
	{
		return null;
	}
}
