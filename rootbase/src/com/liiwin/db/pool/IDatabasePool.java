package com.liiwin.db.pool;

import java.sql.SQLException;
import com.liiwin.db.Database;
/**
 * <p>标题：  数据库连接池 接口</p>
 * <p>功能： </p>
 * <p>所属模块： rootbase</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2017年8月29日 下午1:56:52</p>
 * <p>类全名：com.liiwin.db.pool.IConnectionPool</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public interface IDatabasePool
{
	/**
	 * 获取DB
	 * @return
	 * 赵玉柱
	 */
	public Database getDatabase();

	/**
	 * 获取当前的DB
	 * @return
	 * 赵玉柱
	 */
	public Database getCurrentDatabase();

	/**
	 * 释放DB
	 * @param db
	 * @throws SQLException
	 * 赵玉柱
	 */
	public <T extends Database> void releaseDatabase(T db) throws SQLException;

	/**
	 * 销毁db
	 * 
	 * 赵玉柱
	 */
	public void destroy();

	/**
	 * 是否在活动
	 * @return
	 * 赵玉柱
	 */
	public boolean isActive();

	/**
	 * 检查数据库链接池
	 * 
	 * 赵玉柱
	 */
	public void checkPool();
}
