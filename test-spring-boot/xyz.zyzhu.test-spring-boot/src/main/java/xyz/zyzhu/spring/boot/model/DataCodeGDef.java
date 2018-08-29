package xyz.zyzhu.spring.boot.model;

import com.liiwin.annotation.FieldDef;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： 码表字段定义</p>
 * <p>功能： </p>
 * <p>所属模块： boot</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年8月28日 下午3:01:34</p>
 * <p>类全名：xyz.zyzhu.spring.boot.model.DataCodeGDef</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class DataCodeGDef extends BasModel
{
	private static final long	serialVersionUID	= 4615926611696839387L;
	//内码
	@FieldDef(defaultValue = "AutoCode:dgicode_______")
	private String				gicode;
	//主表内码
	@FieldDef(notEmpty = true)
	private String				icode;
	//序号，用于字段排序使用
	@FieldDef(notEmpty = true)
	private String				rno;
	//码值列
	@FieldDef(notEmpty = true)
	private String				codecol;
	//码值列  物理表    {codecolas} as {codecol}
	@FieldDef()
	private String				codecolas;
	//码名，用于码的名显示的列
	@FieldDef(notEmpty = true)
	private String				namecol;
	//标识  1：隐藏;2：禁用
	@FieldDef()
	private Integer				flags;

	/**
	 * 字段是否为隐藏字段
	 * @return
	 * 赵玉柱
	 */
	public boolean isHidden()
	{
		if (flags == null)
		{
			return false;
		}
		return (flags & 1) > 0;
	}

	/**
	 * 设置隐藏
	 * @return
	 * 赵玉柱
	 */
	public DataCodeGDef setHidden()
	{
		if (!isHidden())
		{
			Integer oldFlags = getFlags();
			int newflags = StrUtil.obj2int(oldFlags);
			newflags = newflags + 1;
			setFlags(newflags);
		}
		return this;
	}

	/**
	 * 设置 取消隐藏
	 * @return
	 * 赵玉柱
	 */
	public DataCodeGDef setUnHidden()
	{
		if (isHidden())
		{
			Integer oldFlags = getFlags();
			int newflags = StrUtil.obj2int(oldFlags);
			newflags = newflags - 1;
			setFlags(newflags);
		}
		return this;
	}

	/**
	 * 字段是否为禁用字段  禁用字段将不会出现在查询条件中
	 * @return
	 * 赵玉柱
	 */
	public boolean isAbandon()
	{
		if (flags == null)
		{
			return false;
		}
		return (flags & 2) > 0;
	}

	/**
	 * 设置字段禁用
	 * @return
	 * 赵玉柱
	 */
	public DataCodeGDef setAbandon()
	{
		if (!isAbandon())
		{
			Integer oldFlags = getFlags();
			int newFlags = StrUtil.obj2int(oldFlags);
			newFlags = newFlags + 2;
			setFlags(newFlags);
		}
		return this;
	}

	/**
	 * 设置字段 取消禁用
	 * @return
	 * 赵玉柱
	 */
	public DataCodeGDef setUnAbandon()
	{
		if (isAbandon())
		{
			Integer oldFlags = getFlags();
			int newFlags = StrUtil.obj2int(oldFlags);
			newFlags = newFlags - 2;
			setFlags(newFlags);
		}
		return this;
	}

	public String getGicode()
	{
		return gicode;
	}

	public void setGicode(String gicode)
	{
		//setValue("gicode", gicode);
		this.gicode = gicode;
	}

	public String getIcode()
	{
		return icode;
	}

	public void setIcode(String icode)
	{
		setValue("icode", icode);
	}

	public String getRno()
	{
		return rno;
	}

	public void setRno(String rno)
	{
		setValue("rno", rno);
	}

	public String getCodecol()
	{
		return codecol;
	}

	public void setCodecol(String codecol)
	{
		setValue("codecol", codecol);
	}

	public String getCodecolas()
	{
		return codecolas;
	}

	public void setCodecolas(String codecolas)
	{
		setValue("codecolas", codecolas);
	}

	public String getNamecol()
	{
		return namecol;
	}

	public void setNamecol(String namecol)
	{
		setValue("namecol", namecol);
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
