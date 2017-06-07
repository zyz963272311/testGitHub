package com.zhu.redis;

import java.util.List;
import redis.clients.jedis.Jedis;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年6月7日 下午12:29:25</p>
 * <p>类全名：com.zhu.redis.RedisList</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class RedisList
{
	private final Jedis	jedis;

	/**
	 * 
	 */
	public RedisList(Jedis jedis)
	{
		this.jedis = jedis;
	}

	public List<String> get(String key)
	{
		return jedis.lrange(key, 0, -1);
	}

	public void set(String key, List<String> value)
	{
		String[] values = new String[value.size()];
		for (int i = 0; i < values.length; i++)
		{
			values[i] = value.get(i);
		}
		jedis.lpush(key, values);
	}

	public void del(String key)
	{
		jedis.del(key);
	}
}
