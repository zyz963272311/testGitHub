package xyz.zyzhu.spring.boot.service;

import java.util.List;

import org.springframework.data.domain.Page;

import xyz.zyzhu.spring.boot.model.Menu;


/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2017年12月3日 上午12:06:53</p>
 * <p>类全名：xyz.zyzhu.spring.boot.service.MenuService</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public interface MenuService {
	public List<Menu> listMenu();
	public void insert(Menu menu);
	/**
	 * @param page
	 * @param size
	 * @return
	 * 赵玉柱
	 */
	Page<Menu> listPage(int page, int size);
	/**
	 * @param page
	 * @param size
	 * @param params
	 * @return
	 * 赵玉柱
	 */
	public Page<Menu> listPage(int page, int size, Menu menu);
}
