package xyz.zyzhu.spring.boot.redis.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年2月4日 下午1:37:57</p>
 * <p>类全名：xyz.zyzhu.spring.boot.redis.impl.IRedisService</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Service
public class IRedisServiceImpl implements xyz.zyzhu.spring.boot.redis.IRedisService
{
	@Autowired
	private RedisTemplate<String,?> redisTemplate;

	@Override
	public boolean set(final String key, final String value)
	{
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>()
		{
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException
			{
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				connection.set(serializer.serialize(key), serializer.serialize(value));
				return true;
			}
		});
		return result;
	}

	public String get(final String key)
	{
		String result = redisTemplate.execute(new RedisCallback<String>()
		{
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException
			{
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] value = connection.get(serializer.serialize(key));
				return serializer.deserialize(value);
			}
		});
		return result;
	}

	@Override
	public boolean expire(final String key, long expire)
	{
		return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
	}

	@Override
	public <T> boolean setList(String key, List<T> list)
	{
		//		String value = JSONUtil.toJson(list);
		String value = JSONObject.toJSONString(list);
		return set(key, value);
	}

	@Override
	public <T> List<T> getList(String key, Class<T> clz)
	{
		String json = get(key);
		if (json != null)
		{
			//			List<T> list = JSONUtil.toList(json, clz);
			List<T> list = JSONObject.parseArray(json, clz);
			return list;
		}
		return null;
	}

	@Override
	public long lpush(final String key, Object obj)
	{
		//		final String value = JSONUtil.toJson(obj);
		final String value = JSONObject.toJSONString(obj);
		long result = redisTemplate.execute(new RedisCallback<Long>()
		{
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException
			{
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				long count = connection.lPush(serializer.serialize(key), serializer.serialize(value));
				return count;
			}
		});
		return result;
	}

	@Override
	public long rpush(final String key, Object obj)
	{
		//		final String value = JSONUtil.toJson(obj);
		final String value = JSONObject.toJSONString(obj);
		long result = redisTemplate.execute(new RedisCallback<Long>()
		{
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException
			{
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				long count = connection.rPush(serializer.serialize(key), serializer.serialize(value));
				return count;
			}
		});
		return result;
	}

	@Override
	public String lpop(final String key)
	{
		String result = redisTemplate.execute(new RedisCallback<String>()
		{
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException
			{
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] res = connection.lPop(serializer.serialize(key));
				return serializer.deserialize(res);
			}
		});
		return result;
	}
}
