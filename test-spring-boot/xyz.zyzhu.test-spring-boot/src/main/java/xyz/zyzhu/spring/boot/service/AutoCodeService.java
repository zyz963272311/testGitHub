package xyz.zyzhu.spring.boot.service;

import java.util.List;
import java.util.Map;
import xyz.zyzhu.spring.boot.model.AutoCode;
import xyz.zyzhu.spring.boot.model.AutoCodeG;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年7月31日 下午2:19:59</p>
 * <p>类全名：xyz.zyzhu.spring.boot.service.AutoCodeService</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public interface AutoCodeService
{
	/**
	 * 插入
	 * @param c
	 * @return
	 * 赵玉柱
	 */
	AutoCode insertM(AutoCode c);

	/**
	 * 主表存盘
	 * @param c
	 * 赵玉柱
	 */
	void saveM(AutoCode c);

	/**
	 * 子表插入
	 * @param g
	 * @return
	 * 赵玉柱
	 */
	AutoCodeG insertG(AutoCodeG g);

	/**
	 * 子表存盘
	 * @param g
	 * 赵玉柱
	 */
	void saveG(AutoCodeG g);

	/**
	 * 主表查询
	 * @return
	 * 赵玉柱
	 */
	List<AutoCode> queryListM();

	/**
	 * 子表查询
	 * @return
	 * 赵玉柱
	 */
	List<AutoCodeG> queryListG();

	/**
	 * 条件查询主表
	 * @param c
	 * @param params
	 * @return
	 * 赵玉柱
	 */
	List<AutoCode> queryListMByFilter(AutoCode c, Map<String,Object> p);

	/**
	 * 条件查询子表
	 * @param c
	 * @param p
	 * @return
	 * 赵玉柱
	 */
	List<AutoCodeG> queryListGByFilter(AutoCode c, Map<String,Object> p);

	/**
	 * 主表更新
	 * @param c
	 * 赵玉柱
	 */
	void updateM(AutoCode c);

	/**
	 * 子表更新
	 * @param g
	 * 赵玉柱
	 */
	void updateG(AutoCodeG g);

	/**
	 * 主表删除
	 * @param c
	 * 赵玉柱
	 */
	void deleteM(AutoCode c);

	/**
	 * 根据主表删除子表
	 * @param c
	 * 赵玉柱
	 */
	void deleteGByM(AutoCode c);

	/**
	 * 根据ID删除主表
	 * @param id
	 * 赵玉柱
	 */
	void deleteMById(String id);

	/**
	 * 根据主表id删除子表
	 * @param id
	 * 赵玉柱
	 */
	void deleteGByMid(String id);
}
