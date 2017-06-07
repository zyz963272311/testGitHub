package com.zhu.redis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import redis.clients.jedis.Jedis;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年6月7日 上午11:21:17</p>
 * <p>类全名：com.zhu.redis.TestRedis</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestRedis
{
	private final Jedis	jedis;

	/**
	 * 
	 */
	public TestRedis()
	{
		jedis = new Jedis("127.0.0.1", 6379);
		//设置连接密码
		//jedis.auth("admin");
	}

	public String set(String key, String value)
	{
		return jedis.set(key, value);
	}

	public long del(String key)
	{
		return jedis.del(key);
	}

	public String get(String key)
	{
		return jedis.get(key);
	}

	public Jedis getJedis()
	{
		return this.jedis;
	}

	public static void main(String[] args)
	{
		TestRedis redis = new TestRedis();
		testMap(redis);
	}

	/**
	 * @param redis
	 * 赵玉柱
	 */
	private static void testSet(TestRedis redis)
	{
		RedisSet redisSet = new RedisSet(redis.getJedis());
		String key = "set";
		String[] members = new String[] { "name", "age", "sex" };
		Set<String> set = new HashSet<>();
		for (String member : members)
		{
			set.add(member);
		}
		set.add("address");
		System.out.println(redisSet.get(key));
		redisSet.set(key, set);
		System.out.println(redisSet.get(key));
		redisSet.del(key, members);
		System.out.println(redisSet.get(key));
		redisSet.del(key);
		System.out.println(redisSet.get(key));
	}

	/**
	 * @param redis
	 * 赵玉柱
	 */
	private static void testList(TestRedis redis)
	{
		String key = "list";
		List<String> value = new ArrayList<>();
		value.add("name");
		value.add("age");
		value.add("sex");
		RedisList redisList = new RedisList(redis.getJedis());
		System.out.println(redisList.get(key));
		redisList.set(key, value);
		System.out.println(redisList.get(key));
		redisList.del(key);
		System.out.println(redisList.get(key));
	}

	/**
	 * @param redis
	 * 赵玉柱
	 */
	private static void testMap(TestRedis redis)
	{
		Map<String,String> values = new HashMap<String,String>();
		String key = "user";
		values.put("name", "zyz");
		values.put("age", "13");
		values.put("sex", "男");
		Set<String> keys = values.keySet();
		String[] fields = new String[keys.size()];
		int i = 0;
		for (String k : keys)
		{
			fields[i] = k;
			i++;
		}
		RedisMap redisMap = new RedisMap(redis.getJedis());
		System.out.println(redisMap.get(key, fields));
		redisMap.set(key, values);
		System.out.println(redisMap.get(key, fields));
		redisMap.del(key, new String[] { "sex" });
		System.out.println(redisMap.get(key, fields));
	}

	/**
	 * 
	 * 赵玉柱
	 */
	@Deprecated
	private static void testRedis(TestRedis redis)
	{
		String key = "aaa";
		String value1 = "abc";
		String value2 = "def";
		System.out.println(redis.get(key));
		System.out.println(redis.del(key));
		System.out.println(redis.set(key, value1));
		System.out.println(redis.get(key));
		System.out.println(redis.del(key));
		System.out.println(redis.get(key));
		System.out.println(redis.set(key, value2));
	}
}
