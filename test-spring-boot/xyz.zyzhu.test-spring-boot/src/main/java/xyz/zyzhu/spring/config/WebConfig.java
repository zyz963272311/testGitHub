package xyz.zyzhu.spring.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import xyz.zyzhu.spring.boot.constance.LoginConstance;
import xyz.zyzhu.spring.boot.interceptor.LoginHandlerInterceptor;
import xyz.zyzhu.spring.boot.utils.PropertiesUtils;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月24日 下午7:10:27</p>
 * <p>类全名：xyz.zyzhu.spring.config.WebConfig</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Component
public class WebConfig extends WebMvcConfigurerAdapter implements LoginConstance
{
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		registry.addResourceHandler("/upload/**").addResourceLocations("file:///" + PropertiesUtils.getPropValue("web.upload-path"));
		super.addResourceHandlers(registry);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry)
	{
		//登录拦截器
		registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/login.jsp").excludePathPatterns("/js/**").excludePathPatterns("/templates/**")
				.excludePathPatterns("/css/**").excludePathPatterns("/assets/**").excludePathPatterns("/wx").excludePathPatterns("/user/login").excludePathPatterns("/user/logout");
		super.addInterceptors(registry);
	}
}
