package xyz.zyzhu.spring.boot.model;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月24日 下午7:35:20</p>
 * <p>类全名：xyz.zyzhu.spring.boot.model.CkeditorSubmitResponse</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class CkeditorSubmitResponse extends BasObject
{
	private static final long	serialVersionUID	= 2705597218983768243L;
	//状态
	private Integer				status;
	//返回信息
	private String				msg;
	//回执的内码
	private String				id;
	//名称
	private String				name;
	//标题
	private String				title;

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		//setValue("status", status);
		this.status = status;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		//setValue("msg", msg);
		this.msg = msg;
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

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		//setValue("name", name);
		this.name = name;
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
