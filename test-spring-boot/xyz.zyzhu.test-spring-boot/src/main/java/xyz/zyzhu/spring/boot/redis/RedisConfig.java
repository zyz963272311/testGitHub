package xyz.zyzhu.spring.boot.redis;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2017年11月23日 下午8:02:21</p>
 * <p>类全名：xyz.zyzhu.spring.boot.redis.RedisConfig</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class RedisConfig
{
	//	@Bean
	//	@Override
	//	public KeyGenerator keyGenerator()
	//	{
	//		return new KeyGenerator()
	//		{
	//			@Override
	//			public Object generate(Object target, Method method, Object... params)
	//			{
	//				StringBuffer sb = new StringBuffer();
	//				sb.append(target.getClass().getName()).append(method.getName());
	//				for (Object param : params)
	//				{
	//					sb.append(param.toString());
	//				}
	//				return sb.toString();
	//			}
	//		};
	//	}
	//
	//	public CacheManager cacheManager(RedisTemplate template)
	//	{
	//		RedisCacheManager cacheManager = new RedisCacheManager(template);
	//		return cacheManager;
	//	}
	//
	//	public RedisTemplate<String,String> redisTemplete(RedisConnectionFactory factory)
	//	{
	//		StringRedisTemplate template = new StringRedisTemplate(factory);
	//		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
	//		ObjectMapper mapper = new ObjectMapper();
	//		mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
	//		mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
	//		jackson2JsonRedisSerializer.setObjectMapper(mapper);
	//		template.setValueSerializer(jackson2JsonRedisSerializer);
	//		template.afterPropertiesSet();
	//		return template;
	//	}
}