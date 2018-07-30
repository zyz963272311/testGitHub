package xyz.zyzhu.spring.boot.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * <p>标题： 微信路由Module</p>
 * <p>功能： </p>
 * <p>所属模块： 微信</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年7月30日 下午2:34:45</p>
 * <p>类全名：xyz.zyzhu.spring.boot.wx.module.WXRouterModule</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Entity
@Table(name = "wxroutermodule")
public class WXRouterModule implements Serializable
{
	private static final long	serialVersionUID	= 1411879407786673442L;
	@Id
	private String				id;											//主键
	private String				node;										//节点 排序用
	private String				name;										//名称 用于标记当前路由作用
	private String				matcherClassPath;							//匹配器路径
	private String				interClassPath;								//拦截器路径
	private String				handlerClassPath;							//处理器路径

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getNode()
	{
		return node;
	}

	public void setNode(String node)
	{
		this.node = node;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getMatcherClassPath()
	{
		return matcherClassPath;
	}

	public void setMatcherClassPath(String matcherClassPath)
	{
		this.matcherClassPath = matcherClassPath;
	}

	public String getInterClassPath()
	{
		return interClassPath;
	}

	public void setInterClassPath(String interClassPath)
	{
		this.interClassPath = interClassPath;
	}

	public String getHandlerClassPath()
	{
		return handlerClassPath;
	}

	public void setHandlerClassPath(String handlerClassPath)
	{
		this.handlerClassPath = handlerClassPath;
	}
}
