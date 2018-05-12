package xyz.zyzhu.spring.config;

import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPoolConfig;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年2月4日 下午1:34:31</p>
 * <p>类全名：xyz.zyzhu.spring.config.RedisConfig</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Configuration
@EnableAutoConfiguration
public class RedisConfig
{
	private static Logger logger = Logger.getLogger(RedisConfig.class);

	@Bean
	@ConfigurationProperties(prefix = "spring.redis")
	public JedisPoolConfig getRedisConfig()
	{
		JedisPoolConfig config = new JedisPoolConfig();
		return config;
	}

	@Bean
	@ConfigurationProperties(prefix = "spring.redis")
	public JedisConnectionFactory getConnectionFactory()
	{
		JedisConnectionFactory factory = new JedisConnectionFactory();
		JedisPoolConfig config = getRedisConfig();
		factory.setPoolConfig(config);
		factory.afterPropertiesSet();
		logger.info("JedisConnectionFactory bean init success.");
		return factory;
	}

	@Bean
	public RedisTemplate<?,?> getRedisTemplate()
	{
		return getRedisTemplete(Object.class, Object.class);
	}

	@Bean
	public <K extends Object,V extends Object> RedisTemplate<K,V> getRedisTemplete(Class<K> k, Class<V> v)
	{
		RedisTemplate<K,V> template = new RedisTemplate<>();
		template.setConnectionFactory(getConnectionFactory());
		template.afterPropertiesSet();
		return template;
	}
}
