package xyz.zyzhu.spring.boot.redis;

import java.util.List;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年2月4日 下午1:36:43</p>
 * <p>类全名：xyz.zyzhu.spring.boot.redis.IRedisService</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public interface IRedisService
{
	public boolean set(String key, String value);

	public String get(String key);

	public boolean expire(String key, long expire);

	public <T> boolean setList(String key, List<T> list);

	public <T> List<T> getList(String key, Class<T> clz);

	public long lpush(String key, Object obj);

	public long rpush(String key, Object obj);

	public String lpop(String key);
}
