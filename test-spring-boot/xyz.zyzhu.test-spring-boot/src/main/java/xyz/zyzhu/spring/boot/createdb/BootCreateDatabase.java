package xyz.zyzhu.spring.boot.createdb;

import com.liiwin.createdb.CreateDatabase;
import xyz.zyzhu.spring.boot.utils.ModelUtils;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月29日 上午9:21:54</p>
 * <p>类全名：xyz.zyzhu.spring.boot.createdb.BootCreateDatabase</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class BootCreateDatabase extends CreateDatabase
{
	public static void createDatabase(String[] dbNames)
	{
		//1.更新数据库
		CreateDatabase.createDatabase(dbNames);
	}

	public static void updateCahce()
	{
		//1、清除缓存的表结构，此处需要提供一个接口用来更新表结构的缓存
		ModelUtils.clearAll();
	}
}
