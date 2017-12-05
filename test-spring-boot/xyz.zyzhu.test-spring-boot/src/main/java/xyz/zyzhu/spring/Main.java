package xyz.zyzhu.spring;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import xyz.zyzhu.spring.boot.filter.LoginFilter;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2017年11月18日 上午11:03:44</p>
 * <p>类全名：xyz.zyzhu.spring.Main</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@SpringBootApplication
public class Main
{
	public static void main(String[] args)
	{
		SpringApplication springApplication = new SpringApplication(Main.class);
		springApplication.setBannerMode(Mode.OFF);
		springApplication.run(args);
	}

	/**
	 * 添加过滤器 遇到坑了，暂时不实现了
	 * @return
	 * 赵玉柱
	 */
//	@Bean
//	public FilterRegistrationBean loginFilterRegister()
//	{
//		FilterRegistrationBean registration = new FilterRegistrationBean();
//		registration.setFilter(new LoginFilter());
//		registration.addInitParameter("urlPatterns", "/*");
//		registration.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/user/login,/login*,/login.jsp*,/index.jsp*");
//		return registration;
//	}
}
