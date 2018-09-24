package xyz.zyzhu.spring.boot.model;

import javax.persistence.Table;
import xyz.zyzhu.spring.boot.annotation.FieldDef;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月24日 下午8:01:55</p>
 * <p>类全名：xyz.zyzhu.spring.boot.model.CkeditorModel</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Table(name = "ckeditor")
public class CkeditorModel extends BasModel
{
	private static final long	serialVersionUID	= -4797500490017214011L;
	@FieldDef(defaultValue = "AutoCode:ckYYMMDD_______")
	private String				id;
	@FieldDef()
	private String				name;
	@FieldDef()
	private String				title;
	@FieldDef()
	private String				context;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		setValue("id", id);
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		setValue("name", name);
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		setValue("title", title);
	}

	public String getContext()
	{
		return context;
	}

	public void setContext(String context)
	{
		setValue("context", context);
	}
}
