package xyz.zyzhu.spring.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.zyzhu.spring.boot.model.Menu;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2017年12月5日 下午7:26:24</p>
 * <p>类全名：xyz.zyzhu.spring.boot.repository.MenuRepository</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public interface MenuRepository extends JpaRepository<Menu,Integer>  {
}
