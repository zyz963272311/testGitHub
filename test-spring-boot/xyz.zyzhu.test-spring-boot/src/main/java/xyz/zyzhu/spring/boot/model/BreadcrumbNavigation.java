package xyz.zyzhu.spring.boot.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class BreadcrumbNavigation extends BasObject
{
	private static final long					serialVersionUID	= -3664739569059664526L;
	private String								url;										//菜单url
	private String								node;										//菜单节点
	private String								name;										//菜单名称
	private Map<String,BreadcrumbNavigation>	children;									//子节点

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

	public Map<String,BreadcrumbNavigation> getChildren()
	{
		return children;
	}

	public void setChildren(Map<String,BreadcrumbNavigation> children)
	{
		//setValue("children", children);
		this.children = children;
	}

	public BreadcrumbNavigation addChildRen(List<BreadcrumbNavigation> navigations)
	{
		for (BreadcrumbNavigation navigation : navigations)
		{
			addChild(navigation);
		}
		return this;
	}

	public BreadcrumbNavigation addChild(BreadcrumbNavigation navigation)
	{
		Map<String,BreadcrumbNavigation> children2 = getChildren();
		if (children2 == null)
		{
			children2 = new HashMap<>();
			setChildren(children2);
		}
		String node2 = navigation.getNode();
		children2.put(node2, navigation);
		return this;
	}
}
