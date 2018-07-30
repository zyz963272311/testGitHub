package xyz.zyzhu.spring.boot.dao;

import java.util.List;
import java.util.Map;
import xyz.zyzhu.spring.boot.model.WXRouterModule;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年7月30日 下午3:33:40</p>
 * <p>类全名：xyz.zyzhu.spring.boot.dao.WXRouterDao</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public interface WXRouterDao
{
	/**
	 * 根据id查询数据
	 * @param id
	 * @return
	 * 赵玉柱
	 */
	WXRouterModule queryById(String id);

	/**
	 * 添加
	 * @param m
	 * 赵玉柱
	 */
	void add(WXRouterModule m);

	/**
	 * 删除
	 * @param m
	 * 赵玉柱
	 */
	void delete(WXRouterModule m);

	/**
	 * 根据ID删除
	 * @param id
	 * 赵玉柱
	 */
	void deleteById(String id);

	/**
	 * 更新
	 * @param m
	 * 赵玉柱
	 */
	void update(WXRouterModule m);

	/**
	 * 数据查询
	 * @return
	 * 赵玉柱
	 */
	List<WXRouterModule> queryList();

	/**
	 * 根据Module查询List
	 * @param m
	 * @return
	 * 赵玉柱
	 */
	List<WXRouterModule> queryListByModule(WXRouterModule m);

	/**
	 * 根据查询条件查询list
	 * @param params
	 * @return
	 * 赵玉柱
	 */
	List<WXRouterModule> queryListByFilter(WXRouterModule m, Map<String,Object> params);
}
