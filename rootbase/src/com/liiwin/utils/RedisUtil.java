package com.liiwin.utils;

import redis.clients.jedis.Jedis;
import com.liiwin.date.Redis;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年6月7日 下午5:07:18</p>
 * <p>类全名：com.liiwin.utils.RedisUtil</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class RedisUtil
{
	public static final int	SECOND	= 1;
	public static final int	MINUTE	= 2;
	public static final int	HOUR	= 3;
	public static final int	DAY		= 4;
	public static final int	WEEK	= 5;
	public static final int	MONTH	= 6;
	public static final int	SEASON	= 7;
	public static final int	YEAR	= 8;

	/**
	 * 在redis缓存中添加数据，并设置默认的超时时间为10分钟，认为10分钟之后，这个数据将不在有用
	 * @param key
	 * @param value
	 * 赵玉柱
	 */
	public static void set(String key, Object value)
	{
		set(key, value, 600);
	}

	/**
	 * 在redis缓存中添加数据，并按照时间类型*数值存储时间，单位为秒，超时或被del之后，将不存在
	 * @param key
	 * @param value
	 * @param time 时间的值
	 * @param timeType 时间的类型，目前类型为周
	 * 赵玉柱
	 */
	public static void set(String key, Object value, int time, int timeType)
	{
		if (time > 0)
		{
			if (timeType == MINUTE)
			{
				time = time * 60;
			}
			if (timeType == HOUR)
			{
				time = time * 60 * 60;
			}
			if (timeType == DAY)
			{
				time = time * 60 * 60 * 24;
			}
			if (timeType == WEEK)
			{
				time = time * 60 * 60 * 24 * 7;
			}
			if (timeType == MONTH)
			{
				time = time * 60 * 60 * 24 * 30;
			}
			if (timeType == SEASON)
			{
				time = time * 60 * 60 * 24 * 30 * 4;
			}
			if (timeType == YEAR)
			{
				time = time * 60 * 60 * 24 * 360;
			}
		} else
		{
			time = 0;
		}
		int seconds = time;
		set(key, value, seconds);
	}

	public static void set(String key, Object value, int seconds)
	{
		if (StrUtil.isStrTrimNull(key))
		{
			throw new RuntimeException("redis存入数据的键不可为空");
		}
		if (value == null)
		{
			throw new RuntimeException("redis存入的值不可为空");
		}
		Redis redis = new Redis();
		Jedis jedis = redis.getJedis();
		byte[] valueByte = SerializableUtils.serializable(value);
		jedis.set(key.getBytes(), valueByte);
		jedis.expire(key.getBytes(), seconds);
	}

	public static Object get(String key)
	{
		if (StrUtil.isStrTrimNull(key))
		{
			throw new RuntimeException("redis存入数据的键不可为空");
		}
		Redis redis = new Redis();
		Jedis jedis = redis.getJedis();
		byte[] valueByte = jedis.get(key.getBytes());
		if (valueByte == null)
		{
			return null;
		}
		Object value = SerializableUtils.unSerializable(valueByte);
		return value;
	}

	public static void del(String key)
	{
		if (StrUtil.isStrTrimNull(key))
		{
			throw new RuntimeException("redis存入数据的键不可为空");
		}
		Redis redis = new Redis();
		Jedis jedis = redis.getJedis();
		jedis.del(key.getBytes());
	}

	public static void main(String[] args)
	{
		del("test");
		set("test", "test123", 20);
		System.out.println(get("test"));
		try
		{
			Thread.sleep(15 * 1000);
		} catch (InterruptedException e)
		{
			throw new RuntimeException("报错内容", e);
		}
		System.out.println("get1" + get("test"));
		try
		{
			Thread.sleep(15 * 1000);
		} catch (InterruptedException e)
		{
			throw new RuntimeException("报错内容", e);
		}
		System.out.println("get2" + get("test"));
	}
}
