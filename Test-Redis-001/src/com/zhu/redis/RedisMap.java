package com.zhu.redis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import redis.clients.jedis.Jedis;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年6月7日 下午12:08:20</p>
 * <p>类全名：com.zhu.redis.RedisMap</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class RedisMap
{
	private final Jedis	jedis;

	/**
	 * 
	 */
	public RedisMap(Jedis jedis)
	{
		this.jedis = jedis;
	}

	public Map<String,String> get(String key, String[] fields)
	{
		List<String> lietTemp = jedis.hmget(key, fields);
		Map<String,String> result = new HashMap<String,String>();
		int i = 0;
		for (String field : fields)
		{
			result.put(field, lietTemp.get(i));
			i++;
		}
		return result;
	}

	public void set(String key, Map<String,String> value)
	{
		jedis.hmset(key, value);
	}

	public void del(String key, String[] fields)
	{
		jedis.hdel(key, fields);
	}
}
