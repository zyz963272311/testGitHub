package xyz.zyzhu.spring.boot.service;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import xyz.zyzhu.spring.boot.model.WXRouter;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年7月30日 下午3:17:27</p>
 * <p>类全名：xyz.zyzhu.spring.boot.service.WXRouterService</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Service
public interface WXRouterService
{
	/**
	 * 根据id查询数据
	 * @param id
	 * @return
	 * 赵玉柱
	 */
	WXRouter queryById(String id);

	/**
	 * 添加
	 * @param m
	 * 赵玉柱
	 */
	void add(WXRouter m);

	/**
	 * 删除
	 * @param m
	 * 赵玉柱
	 */
	void delete(WXRouter m);

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
	void update(WXRouter m);

	/**
	 * 数据查询
	 * @return
	 * 赵玉柱
	 */
	List<WXRouter> queryList();

	/**
	 * 根据Module查询List
	 * @param m
	 * @return
	 * 赵玉柱
	 */
	List<WXRouter> queryListByModule(WXRouter m);

	/**
	 * 根据查询条件查询list
	 * @param params
	 * @return
	 * 赵玉柱
	 */
	List<WXRouter> queryListByFilter(WXRouter m, Map<String,Object> params);
}
