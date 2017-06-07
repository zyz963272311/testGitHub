package com.zhu.redis;

import java.util.Iterator;
import java.util.Set;
import redis.clients.jedis.Jedis;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年6月7日 下午12:41:52</p>
 * <p>类全名：com.zhu.redis.RedisSet</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class RedisSet
{
	private final Jedis	jedis;

	/**
	 * 
	 */
	public RedisSet(Jedis jedis)
	{
		this.jedis = jedis;
	}

	public Set<String> get(String key)
	{
		return jedis.smembers(key);
	}

	public void set(String key, Set<String> value)
	{
		String[] values = new String[value.size()];
		Iterator<String> iterator = value.iterator();
		int i = 0;
		while (iterator.hasNext())
		{
			values[i] = iterator.next();
			i++;
		}
		jedis.sadd(key, values);
	}

	public void del(String key, String[] members)
	{
		jedis.srem(key, members);
	}

	public void del(String key)
	{
		jedis.del(key);
	}
}
