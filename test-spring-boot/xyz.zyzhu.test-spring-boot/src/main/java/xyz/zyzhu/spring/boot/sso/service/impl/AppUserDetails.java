package xyz.zyzhu.spring.boot.sso.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
/**
 * <p>
 * 标题： TODO
 * </p>
 * <p>
 * 功能：
 * </p>
 * <p>
 * 所属模块： TODO
 * </p>
 * <p>
 * 版权： Copyright © 2017 SNSOFT
 * </p>
 * <p>
 * 公司: 赵玉柱练习
 * </p>
 * <p>
 * 创建日期：2017年12月7日 下午9:13:57
 * </p>
 * <p>
 * 类全名：xyz.zyzhu.spring.boot.sso.service.impl.AppUserDetails
 * </p>
 * 作者：赵玉柱 初审： 复审： 监听使用界面:
 * 
 * @version 8.0
 */
public class AppUserDetails implements UserDetails
{
	/** */
	private static final long						serialVersionUID	= -4777124807325532850L;
	private String									username;
	private String									password;
	private boolean									accountNonExpired;
	private boolean									accountNonLocked;
	private boolean									credentialsNonExpired;
	private boolean									enabled;
	private Collection<? extends GrantedAuthority>	authorities;
	private List<String>							roles;

	public AppUserDetails()
	{
		super();
	}

	public AppUserDetails(String username, Collection<? extends GrantedAuthority> authorities)
	{
		super();
		this.username = username;
		this.password = "";
		this.accountNonExpired = true;
		this.accountNonLocked = true;
		this.credentialsNonExpired = true;
		this.enabled = true;
		this.authorities = authorities;
		this.roles = new ArrayList<String>();
		this.roles.addAll(authorities.stream().map((Function<GrantedAuthority,String>) GrantedAuthority::getAuthority).collect(Collectors.toList()));
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		/*
		 * List<GrantedAuthority> l = new ArrayList<GrantedAuthority>(); l.add(new
		 * GrantedAuthority() { private static final long serialVersionUID = 1L;
		 * 
		 * @Override public String getAuthority() { return "ROLE_AUTHENTICATED"; } });
		 * return l;
		 */
		return authorities;
	}

	@Override
	public String getPassword()
	{
		return password;
	}

	@Override
	public String getUsername()
	{
		return username;
	}

	@Override
	public boolean isAccountNonExpired()
	{
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked()
	{
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired()
	{
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled()
	{
		return enabled;
	}
}
