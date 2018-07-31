package com.liiwin.utils;

import com.liiwin.date.Redis;
import redis.clients.jedis.Jedis;
/**
 * <p>标题： RedisUtil</p>
 * <p>功能： </p>
 * <p>所属模块： rootbas</p>
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
	/**秒 1秒*/
	public static final int	SECOND	= 1;
	/**分 60秒*/
	public static final int	MINUTE	= 2;
	/**时 60*60秒*/
	public static final int	HOUR	= 3;
	/**日 60*60*24秒*/
	public static final int	DAY		= 4;
	/**周 60*60*24*7秒*/
	public static final int	WEEK	= 5;
	/**月 60*60*24*30秒*/
	public static final int	MONTH	= 6;
	/**季 60*60*24*30*3秒*/
	public static final int	SEASON	= 7;
	/**年 60*60*24*365秒 */
	public static final int	YEAR	= 8;

	/**
	 * 在redis缓存中添加数据，此处不设置默认的超时时间，按照不删除的原则插入
	 * @param key
	 * @param value
	 * 赵玉柱
	 */
	public static void set(String key, Object value)
	{
		set(key, value, -1);
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
				time = time * 60 * 60 * 24 * 30 * 3;
			}
			if (timeType == YEAR)
			{
				time = time * 60 * 60 * 24 * 365;
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
		if (seconds > 0)
		{
			jedis.expire(key.getBytes(), seconds);
		}
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

	/**
	 * 给某一个redis加锁 需要设置同步锁
	 * @param key 键
	 * @param time 时间数量
	 * @param timeType 时间类型
	 * @return
	 * 赵玉柱
	 */
	synchronized public static int setLock(String key, int time, int timeType)
	{
		if (key == null)
		{
			throw new RuntimeException("redis存入数据的键不可为空");
		}
		//获取上一次的锁，如果为0或空表示没有锁
		int value = StrUtil.obj2int(get(key), 0);
		//将锁的次数加一
		set(key, ++value, time, timeType);
		//返回锁的次数
		return value;
	}

	/**
	 * 给某一个redis解锁 需要设置同步锁
	 * @param key
	 * @param time 时间数量
	 * @param timeType 时间类型
	 * @return
	 * 赵玉柱
	 */
	synchronized public static int unLock(String key, int time, int timeType)
	{
		if (key == null)
		{
			throw new RuntimeException("redis存入数据的键不可为空");
		}
		//获取上一次的锁，如果为0或空表示没有锁
		int value = StrUtil.obj2int(get(key), 0);
		//将锁的次数减一
		if (value != 0)
		{
			set(key, --value, time, timeType);
		}
		//返回锁的次数
		return value;
	}
}
