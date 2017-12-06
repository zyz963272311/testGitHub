package xyz.zyzhu.spring.boot.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import xyz.zyzhu.spring.boot.dao.MenuDao;
import xyz.zyzhu.spring.boot.model.Menu;
import xyz.zyzhu.spring.boot.params.QueryParams;
import xyz.zyzhu.spring.boot.repository.MenuRepository;

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
public class MenuDaoImpl implements MenuDao {

	@Autowired
	MenuRepository menuRepository;
	@Override
	public List<Menu> listAll() {
		return menuRepository.findAll();
	}
	@Override
	public void insert(Menu menu) {
		menuRepository.save(menu);
	}
	@Override
	public Page<Menu> listPage(int page,int size)
	{
		Pageable pageable = new PageRequest(page, size);
		return  menuRepository.findAll(pageable);
	}
	@Override
	public Page<Menu> listPage(int page, int size, Menu menu) {
		Pageable pageable = new PageRequest(page, size);
		if(menu == null)
		{
			return menuRepository.findAll( pageable);
		}
		Example<Menu> example = Example.of(menu);
		return menuRepository.findAll(example , pageable);
	}
	@Override
	public Page<Menu> listPageParams(int page, int size, Menu menu,Map<String, Object> params) {
		Pageable pageable = new PageRequest(page, size);
		if(menu == null)
		{
			return menuRepository.findAll( pageable);
		}
		QueryParams<Menu> qoeryParams = new QueryParams<Menu>(menu, params);
		Example<Menu> example = qoeryParams.buildExample();
		return  menuRepository.findAll(example , pageable);
	}
	@Override
	public Page<Menu> listPageParams(int page, int size, Menu menu) {
		return null;
	}

}
