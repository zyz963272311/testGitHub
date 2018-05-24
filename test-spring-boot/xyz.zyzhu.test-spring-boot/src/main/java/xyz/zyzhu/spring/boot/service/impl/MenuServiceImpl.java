package xyz.zyzhu.spring.boot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import xyz.zyzhu.spring.boot.dao.MenuDao;
import xyz.zyzhu.spring.boot.model.Menu;
import xyz.zyzhu.spring.boot.service.MenuService;

/**
 * <p>
 * 标题： TODO
 * </p>
 * <p>
 * 功能：
 * </p>
 * <p>
 * 所属模块： TODO
 * </p>
 * <p>
 * 版权： Copyright © 2017 SNSOFT
 * </p>
 * <p>
 * 公司: 赵玉柱练习
 * </p>
 * <p>
 * 创建日期：2017年12月3日 上午12:07:58
 * </p>
 * <p>
 * 类全名：xyz.zyzhu.spring.boot.service.impl.MenuServiceImpl
 * </p>
 * 作者：赵玉柱 初审： 复审： 监听使用界面:
 * 
 * @version 8.0
 */
@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	MenuDao menuDao;

	@Override
	public List<Menu> listMenu() {
		return menuDao.listAll();
	}

	@Override
	public void insert(Menu menu) {
		menuDao.insert(menu);
	}
	@Override
	public Page<Menu> listPage(int page,int size)
	{
		return menuDao.listPage(page, size);
	}

	@Override
	public Page<Menu> listPage(int page, int size, Menu menu) {
		return  menuDao.listPage(page, size,menu);
	}
	

}
