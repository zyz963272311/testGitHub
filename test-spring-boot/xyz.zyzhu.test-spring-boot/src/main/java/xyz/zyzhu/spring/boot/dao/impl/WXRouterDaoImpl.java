package xyz.zyzhu.spring.boot.dao.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import xyz.zyzhu.spring.boot.dao.WXRouterDao;
import xyz.zyzhu.spring.boot.model.WXRouterModule;
import xyz.zyzhu.spring.boot.repository.WXRouterRepository;
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
	@Autowired
	WXRouterRepository wxRouterRepository;

	@Cacheable(value = "WXRouterModule", key = "#id")
	@Override
	public WXRouterModule queryById(String id)
	{
		return wxRouterRepository.findOne(id);
	}

	@CachePut(value = "WXRouterModule", key = "#m.id")
	@Override
	public void add(WXRouterModule m)
	{
		wxRouterRepository.save(m);
	}

	@CacheEvict(value = "WXRouterModule")
	@Override
	public void delete(WXRouterModule m)
	{
		wxRouterRepository.delete(m);
	}

	@CacheEvict(value = "WXRouterModule", key = "#id")
	@Override
	public void deleteById(String id)
	{
		wxRouterRepository.delete(id);
	}

	@CachePut(value = "WXRouterModule", key = "#m.id")
	@Override
	public void update(WXRouterModule m)
	{
		wxRouterRepository.save(m);
	}

	@Cacheable(value = "WXRouterModule")
	@Override
	public List<WXRouterModule> queryList()
	{
		return wxRouterRepository.findAll();
	}

	@Cacheable(value = "WXRouterModule")
	@Override
	public List<WXRouterModule> queryListByModule(WXRouterModule m)
	{
		return null;
	}

	@Cacheable(value = "WXRouterModule")
	@Override
	public List<WXRouterModule> queryListByFilter(WXRouterModule m, Map<String,Object> params)
	{
		return null;
	}
}
