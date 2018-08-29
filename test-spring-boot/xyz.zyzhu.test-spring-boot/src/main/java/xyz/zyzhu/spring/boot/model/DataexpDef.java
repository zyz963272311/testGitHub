package xyz.zyzhu.spring.boot.model;

import javax.persistence.Table;
import xyz.zyzhu.spring.boot.annotation.FieldDef;
/**
 * <p>标题： 数据导出定义</p>
 * <p>功能： </p>
 * <p>所属模块： 数据导出定义</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年8月29日 下午3:21:06</p>
 * <p>类全名：xyz.zyzhu.spring.boot.model.DataexpDef</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Table(name = "dataexp")
public class DataexpDef extends BasModel
{
	private static final long	serialVersionUID	= 4733020995758947708L;
	//内码
	@FieldDef(defaultValue = "AutoCode:exp________")
	private String				id;
	//导出编号
	@FieldDef(notEmpty = true)
	private String				exportcode;
	//导出名称
	@FieldDef()
	private String				name;
	//描述
	@FieldDef()
	private String				remark;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		setValue("id", id);
	}

	public String getExportcode()
	{
		return exportcode;
	}

	public void setExportcode(String exportcode)
	{
		setValue("exportcode", exportcode);
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		setValue("name", name);
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		setValue("remark", remark);
	}
}
