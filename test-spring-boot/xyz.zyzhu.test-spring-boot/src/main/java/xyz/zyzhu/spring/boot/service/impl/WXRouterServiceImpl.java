package xyz.zyzhu.spring.boot.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.zyzhu.spring.boot.dao.WXRouterDao;
import xyz.zyzhu.spring.boot.model.WXRouterModule;
import xyz.zyzhu.spring.boot.service.WXRouterService;
/**
 * <p>标题： WX路由实现类</p>
 * <p>功能： </p>
 * <p>所属模块： boot</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年7月30日 下午3:29:12</p>
 * <p>类全名：xyz.zyzhu.spring.boot.service.impl.WXRouterServiceImpl</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class WXRouterServiceImpl implements WXRouterService
{
	@Autowired
	WXRouterDao wxRouterDao;

	@Override
	public WXRouterModule queryById(String id)
	{
		return wxRouterDao.queryById(id);
	}

	@Override
	public void add(WXRouterModule m)
	{
		wxRouterDao.add(m);
	}

	@Override
	public void delete(WXRouterModule m)
	{
		wxRouterDao.delete(m);
	}

	@Override
	public void deleteById(String id)
	{
		wxRouterDao.deleteById(id);
	}

	@Override
	public void update(WXRouterModule m)
	{
		wxRouterDao.update(m);
	}

	@Override
	public List<WXRouterModule> queryList()
	{
		return wxRouterDao.queryList();
	}

	@Override
	public List<WXRouterModule> queryListByModule(WXRouterModule m)
	{
		return wxRouterDao.queryListByModule(m);
	}

	@Override
	public List<WXRouterModule> queryListByFilter(WXRouterModule m, Map<String,Object> params)
	{
		return wxRouterDao.queryListByFilter(m, params);
	}
}
