package com.liiwin.date;

import redis.clients.jedis.Jedis;
import com.liiwin.config.BasConfig;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： Redis数据库</p>
 * <p>功能： </p>
 * <p>所属模块： Redis数据库</p>
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
		String redis_ip = BasConfig.getPropertie("redis-ip");
		int redis_port = StrUtil.obj2int(BasConfig.getPropertie("redis-port"), -1);
		if (StrUtil.isStrTrimNull(redis_ip))
		{
			throw new RuntimeException("请在config.properties中配置【redis-ip】");
		}
		if (redis_port == -1)
		{
			throw new RuntimeException("请在config.properties中配置【redis-port】");
		}
		jedis = new Jedis(redis_ip, redis_port);
	}

	public Jedis getJedis()
	{
		return Redis.jedis;
	}
}
