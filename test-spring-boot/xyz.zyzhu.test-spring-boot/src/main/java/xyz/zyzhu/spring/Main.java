package xyz.zyzhu.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import xyz.zyzhu.spring.boot.servlet.WeChatServlet;
import xyz.zyzhu.spring.boot.utils.SpringBeanUtils;
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
@Configuration
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
//添加缓存
@EnableCaching
//@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
public class Main
{
	//	@Override
	//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
	//	{
	//		return application.sources(Main.class);
	//	}
	//
	//	@Bean
	//	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer()
	//	{
	//		PropertySourcesPlaceholderConfigurer c = new PropertySourcesPlaceholderConfigurer();
	//		c.setIgnoreUnresolvablePlaceholders(true);
	//		return c;
	//	}
	public static void main(String[] args)
	{
		SpringApplication springApplication = new SpringApplication(Main.class);
		springApplication.setBannerMode(Mode.CONSOLE);
		springApplication.run(args);
	}

	/**
	 * 解决问题 java.lang.IllegalArgumentException: Could not resolve placeholder 
	 * @return
	 * 赵玉柱
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer()
	{
		PropertySourcesPlaceholderConfigurer c = new PropertySourcesPlaceholderConfigurer();
		c.setIgnoreUnresolvablePlaceholders(true);
		return c;
	}

	@Bean
	public ServletRegistrationBean testServletRegistration()
	{
		ServletRegistrationBean registration = new ServletRegistrationBean(new WeChatServlet());
		registration.addUrlMappings("/we");
		return registration;
	}
	/**
	 * 字符过滤器
	 * @return
	 * 赵玉柱
	 */
	//	@Bean("charsetFilterRegister")
	//	public FilterRegistrationBean charsetFilterRegister()
	//	{
	//		FilterRegistrationBean registration = new FilterRegistrationBean();
	//		Filter filter = new CharacterFilter();
	//		registration.setFilter(filter);
	//		registration.addInitParameter("urlPatterns", "/*");
	//		return registration;
	//	}
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
	//		registration.addInitParameter("exclusions", "index.html*,*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/user/login,/login*,/login.jsp*,/index.jsp*");
	//		return registration;
	//	}

	/**
	 *错误页面
	 * @return
	 * 赵玉柱
	 */
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer()
	{
		return new EmbeddedServletContainerCustomizer()
		{
			@Override
			public void customize(ConfigurableEmbeddedServletContainer container)
			{
				ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
				ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
				ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");
				container.addErrorPages(error401Page, error404Page, error500Page);
			}
		};
	}

	/**
	 * 支持在本地获取servletRequest与Responst
	 * @return
	 * 赵玉柱
	 */
	@Bean
	@Lazy
	public ServletRequestAttributes getServletRequestAttributes()
	{
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return attributes;
	}

	/**
	 * 获取httpServletRequest
	 * @param attributes
	 * @return
	 * 赵玉柱
	 */
	@Bean
	@Lazy
	public HttpServletRequest getHttpServletRequest(ServletRequestAttributes attributes)
	{
		if (attributes == null)
		{
			attributes = SpringBeanUtils.getBean(ServletRequestAttributes.class);
		}
		if (attributes == null)
		{
			attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		}
		if (attributes == null)
		{
			return null;
		}
		HttpServletRequest request = attributes.getRequest();
		return request;
	}

	/**
	 * 获取httpResponse
	 * @param attributes
	 * @return
	 * 赵玉柱
	 */
	@Bean
	@Lazy
	public HttpServletResponse getHttpServletResponst(ServletRequestAttributes attributes)
	{
		if (attributes == null)
		{
			attributes = SpringBeanUtils.getBean(ServletRequestAttributes.class);
		}
		if (attributes == null)
		{
			attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		}
		if (attributes == null)
		{
			return null;
		}
		HttpServletResponse response = attributes.getResponse();
		return response;
	}
}
