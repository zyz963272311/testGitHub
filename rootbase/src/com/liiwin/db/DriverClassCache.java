package com.liiwin.db;

import java.util.HashMap;
import java.util.Map;
/**
 * <p>标题： DatabaseDriverClass的缓存</p>
 * <p>功能： </p>
 * <p>所属模块： Rootbas</p>
 * <p>版权： Copyright © 2018 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2018年2月4日 上午11:49:19</p>
 * <p>类全名：com.liiwin.db.DriverClassCache</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class DriverClassCache
{
	private static Map<String,Boolean> driverClassCache = new HashMap<>();

	/**
	 * 采用缓存的方式防止多次加载数据库驱动的drivreclass
	 * @param driverClass
	 * @return
	 * 赵玉柱
	 */
	synchronized public static boolean loadDriver(String driverClass)
	{
		if (driverClassCache.containsKey(driverClass))
		{
			return driverClassCache.get(driverClass);
		}
		try
		{
			Class.forName(driverClass);
			driverClassCache.put(driverClass, true);
			return true;
		} catch (Exception e)
		{
			driverClassCache.put(driverClass, false);
			return false;
		}
	}
}
