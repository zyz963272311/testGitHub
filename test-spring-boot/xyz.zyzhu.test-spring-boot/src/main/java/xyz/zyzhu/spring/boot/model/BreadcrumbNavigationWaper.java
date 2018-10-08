package xyz.zyzhu.spring.boot.model;

import java.util.ArrayList;
import java.util.List;
/**
 * <p>标题： 编码生成规则Controller</p>
 * <p>功能： </p>
 * <p>所属模块： boot</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年7月31日 上午10:12:24</p>
 * <p>类全名：xyz.zyzhu.spring.boot.controller.AotoCodeController</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class BreadcrumbNavigationWaper<T extends Object> extends BasObject
{
	private static final long					serialVersionUID	= -3664739569059664526L;
	private String								url;										//菜单url
	private String								node;										//菜单节点
	private String								name;										//菜单名称
	private T									obj;										//缓存对象使用
	private List<BreadcrumbNavigationWaper<T>>	children;									//子节点

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		//setValue("url", url);
		this.url = url;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		//setValue("name", name);
		this.name = name;
	}

	public String getNode()
	{
		return node;
	}

	public void setNode(String node)
	{
		//setValue("node", node);
		this.node = node;
	}

	public List<BreadcrumbNavigationWaper<T>> getChildren()
	{
		return children;
	}

	public void setChildren(List<BreadcrumbNavigationWaper<T>> children)
	{
		this.children = children;
	}

	public BreadcrumbNavigationWaper<T> addChildRen(List<BreadcrumbNavigationWaper<T>> navigations)
	{
		for (BreadcrumbNavigationWaper<T> navigation : navigations)
		{
			addChild(navigation);
		}
		return this;
	}

	public BreadcrumbNavigationWaper<T> addChild(BreadcrumbNavigationWaper<T> navigation)
	{
		if (navigation == null)
		{
			return this;
		}
		List<BreadcrumbNavigationWaper<T>> children2 = getChildren();
		if (children2 == null)
		{
			children2 = new ArrayList<>();
			setChildren(children2);
		}
		children2.add(navigation);
		return this;
	}

	public T getObj()
	{
		return obj;
	}

	public BreadcrumbNavigationWaper<T> set(T obj)
	{
		this.obj = obj;
		return this;
	}
}
