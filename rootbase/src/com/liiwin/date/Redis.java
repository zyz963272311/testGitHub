package com.liiwin.date;

import redis.clients.jedis.Jedis;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年6月7日 下午5:08:06</p>
 * <p>类全名：com.liiwin.date.Redis</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Redis
{
	private static Jedis	jedis;

	public Redis()
	{
		jedis = new Jedis("127.0.0.1", 6379);
	}

	public Jedis getJedis()
	{
		return Redis.jedis;
	}
}
