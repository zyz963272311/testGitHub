package xyz.zyzhu.spring.boot.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import xyz.zyzhu.spring.boot.dao.MenuDao;
import xyz.zyzhu.spring.boot.db.BootDatabase;
import xyz.zyzhu.spring.boot.db.BootDatabasePoolManager;
import xyz.zyzhu.spring.boot.model.Menu;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2017年12月5日 下午7:27:39</p>
 * <p>类全名：xyz.zyzhu.spring.boot.dao.impl.MenuDaoImpl</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Repository
public class MenuDaoImpl implements MenuDao
{
	private static final  String  dbName = "zyztest";
	@Override
	public List<Menu> listAll()
	{
		BootDatabase database = BootDatabasePoolManager.getDatabase(dbName);
		List<Menu> query = database.query(new Menu());
		return query;
	}

	@Override
	public void insert(Menu menu)
	{
		BootDatabase database = BootDatabasePoolManager.getDatabase(dbName);
		database.insert(menu);
	}
	@Deprecated
	@Override
	public Page<Menu> listPage(int page, int size)
	{
		return null;
	}
	@Deprecated
	@Override
	public Page<Menu> listPage(int page, int size, Menu menu)
	{
		return null;
	}
	@Deprecated
	@Override
	public Page<Menu> listPageParams(int page, int size, Menu menu, Map<String,Object> params)
	{
		return null;
	}
	@Deprecated
	@Override
	public Page<Menu> listPageParams(int page, int size, Menu menu)
	{
		return null;
	}
}
