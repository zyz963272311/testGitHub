package xyz.zyzhu.spring.boot.sso.service.impl;

import javax.inject.Inject;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
/**
 * <p>标题： TODO</p>
 * <p>功能：</p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2017年12月7日 下午9:16:36</p></p>
 * 作者：赵玉柱 
 * 初审： 
 * 复审： 
 * 监听使用界面:
 * 
 * @version 8.0
 */
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter
{
	private static final String	CAS_URL_LOGIN		= "cas.service.login";
	private static final String	CAS_URL_LOGOUT		= "cas.service.logout";
	private static final String	CAS_URL_PREFIX		= "cas.url.prefix";
	private static final String	CAS_SERVICE_URL		= "app.service.security";
	private static final String	APP_SERVICE_HOME	= "app.service.home";
	@Inject
	private Environment			env;

	@Bean
	public ServiceProperties serviceProperties()
	{
		ServiceProperties sp = new ServiceProperties();
		sp.setService(env.getRequiredProperty(CAS_SERVICE_URL));
		sp.setSendRenew(false);
		return sp;
	}

	@Bean
	public CasAuthenticationProvider casAuthenticationProvider()
	{
		CasAuthenticationProvider casAuthenticationProvider = new CasAuthenticationProvider();
		casAuthenticationProvider.setAuthenticationUserDetailsService(customUserDetailsService());
		casAuthenticationProvider.setServiceProperties(serviceProperties());
		casAuthenticationProvider.setTicketValidator(cas20ServiceTicketValidator());
		casAuthenticationProvider.setKey("an_id_for_this_auth_provider_only");
		return casAuthenticationProvider;
	}

	@Bean
	public AuthenticationUserDetailsService<CasAssertionAuthenticationToken> customUserDetailsService()
	{
		return new CustomUserDetailsService();
	}

	@Bean
	public Cas20ServiceTicketValidator cas20ServiceTicketValidator()
	{
		return new Cas20ServiceTicketValidator(env.getRequiredProperty(CAS_URL_PREFIX));
	}

	@Bean
	public CasAuthenticationFilter casAuthenticationFilter() throws Exception
	{
		CasAuthenticationFilter casAuthenticationFilter = new CasAuthenticationFilter();
		casAuthenticationFilter.setAuthenticationManager(authenticationManager());
		casAuthenticationFilter.setFilterProcessesUrl("/j_spring_cas_security_check");
		return casAuthenticationFilter;
	}

	@Bean
	public CasAuthenticationEntryPoint casAuthenticationEntryPoint()
	{
		CasAuthenticationEntryPoint casAuthenticationEntryPoint = new CasAuthenticationEntryPoint();
		casAuthenticationEntryPoint.setLoginUrl(env.getRequiredProperty(CAS_URL_LOGIN));
		casAuthenticationEntryPoint.setServiceProperties(serviceProperties());
		return casAuthenticationEntryPoint;
	}

	@Bean
	public SingleSignOutFilter singleSignOutFilter()
	{
		SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
		singleSignOutFilter.setCasServerUrlPrefix(env.getRequiredProperty(CAS_URL_PREFIX));
		return singleSignOutFilter;
	}

	@Bean
	public LogoutFilter requestCasGlobalLogoutFilter()
	{
		LogoutFilter logoutFilter = new LogoutFilter(env.getRequiredProperty(CAS_URL_LOGOUT) + "?service=" + env.getRequiredProperty(APP_SERVICE_HOME), new SecurityContextLogoutHandler());
		logoutFilter.setLogoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"));
		return logoutFilter;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.authenticationProvider(casAuthenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.addFilter(casAuthenticationFilter());
		http.exceptionHandling().authenticationEntryPoint(casAuthenticationEntryPoint());
		http.addFilterBefore(singleSignOutFilter(), CasAuthenticationFilter.class).addFilterBefore(requestCasGlobalLogoutFilter(), LogoutFilter.class);
		http.csrf().disable();
		http.headers().frameOptions().disable();
		http.authorizeRequests().antMatchers("/assets/**").permitAll().anyRequest().authenticated();
		http.logout().logoutUrl("/logout").logoutSuccessUrl("/").invalidateHttpSession(true).deleteCookies("JSESSIONID");
	}
}
