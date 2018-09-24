package xyz.zyzhu.spring.boot.model;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月24日 下午7:47:28</p>
 * <p>类全名：xyz.zyzhu.spring.boot.model.CkeditorSubmitRequest</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class CkeditorSubmitRequest extends BasObject
{
	private static final long	serialVersionUID	= -6357476832354259210L;
	private String				id;
	private String				title;
	private String				context;
	private String				service;

	public String getContext()
	{
		return context;
	}

	public void setContext(String context)
	{
		//setValue("context", context);
		this.context = context;
	}

	public String getService()
	{
		return service;
	}

	public void setService(String service)
	{
		//setValue("service", service);
		this.service = service;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		//setValue("id", id);
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		//setValue("title", title);
		this.title = title;
	}
}
