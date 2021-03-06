package xyz.zyzhu.spring.boot.model;

import javax.persistence.Table;
import com.liiwin.utils.StrUtil;
import xyz.zyzhu.spring.boot.annotation.FieldDef;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年7月31日 上午10:21:10</p>
 * <p>类全名：xyz.zyzhu.spring.boot.model.AutoCode</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
//@Entity
@Table(name = "autocode")
public class AutoCode extends BasModel
{
	private static final long	serialVersionUID	= -8812813800065226766L;
	@FieldDef(defaultValue = "AutoCode:autoid__________")
	private String				id;											//id 内码
	@FieldDef(defaultValue = "ExecMethod:new xyz.zyzhu.spring.boot.test.Test().getCode()")
	private String				code;										//编码
	@FieldDef(defaultValue = "ExecMethod:xyz.zyzhu.spring.boot.test.Test.getName()", regEx = "name33321")
	private String				name;										//编码名称
	@FieldDef(defaultValue = "1")
	private Integer				flags;										//1:启用;0:停用

	/**
	 * 已经启用
	 * @return
	 * 赵玉柱
	 */
	public boolean isEnabled()
	{
		int flagInt = StrUtil.obj2int(flags);
		return (flagInt & 1) == 1;
	}

	/**
	 * 已经禁用
	 * @return
	 * 赵玉柱
	 */
	public boolean isDisabled()
	{
		return !isEnabled();
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		setValue("id", id);
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		setValue("code", code);
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		setValue("name", name);
	}

	public Integer getFlags()
	{
		return flags;
	}

	public void setFlags(Integer flags)
	{
		setValue("flags", flags);
	}
}
