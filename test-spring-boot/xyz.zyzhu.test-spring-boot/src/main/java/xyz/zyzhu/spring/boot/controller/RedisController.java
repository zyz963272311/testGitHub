package xyz.zyzhu.spring.boot.controller;

import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.liiwin.date.DateUtil;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2017年11月23日 下午8:26:43</p>
 * <p>类全名：xyz.zyzhu.spring.boot.controller.RedisController</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@EnableAutoConfiguration
@RestController
@RequestMapping("/redis")
public class RedisController
{
	@Autowired
	RedisTemplate<String,String> template;

	@Primary
	@RequestMapping(value = "set", method = { RequestMethod.GET, RequestMethod.POST })
	public String putToRedis(String key)
	{
		String value = key + "_" + DateUtil.formateDate(DateUtil.getCurDate(), "yyyy-MM-dd hh:mm:ss");
		ValueOperations<String,String> operations = template.opsForValue();
		operations.set(key, value, 10, TimeUnit.MINUTES);
		return key;
	}

	@RequestMapping(value = "get", method = { RequestMethod.GET, RequestMethod.POST })
	public String getFromRedis(String key)
	{
		ValueOperations<String,String> operations = template.opsForValue();
		String value = operations.get(key);
		return value;
	}
}
