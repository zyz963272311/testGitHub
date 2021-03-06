package xyz.zyzhu.spring.boot.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2017年11月26日 下午10:21:31</p>
 * <p>类全名：xyz.zyzhu.spring.boot.interceptor.InterceptorConfiger</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Configuration
public class InterceptorConfiger extends WebMvcConfigurerAdapter
{
	@Override
	public void addInterceptors(InterceptorRegistry registry)
	{
		//		registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/*/*");
		//		registry.addInterceptor(new IPHandlerInterceptor()).addPathPatterns("/*");
		super.addInterceptors(registry);
	}
}
