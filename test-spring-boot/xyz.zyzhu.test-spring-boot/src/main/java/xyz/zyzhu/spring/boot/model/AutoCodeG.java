package xyz.zyzhu.spring.boot.model;

import javax.persistence.Table;
import com.liiwin.annotation.FieldDef;
import com.liiwin.code.CodePart;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年7月31日 上午10:22:19</p>
 * <p>类全名：xyz.zyzhu.spring.boot.model.AutoCodeG</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
//@Entity
@Table(name = "autocodeg")
public class AutoCodeG extends BasModel
{
	private static final long	serialVersionUID	= 8480040917605629855L;
	@FieldDef(defaultValue = "AutoCode:autogid__________")
	private String				id;											//内码
	@FieldDef()
	private String				mid;										//主表内码
	@FieldDef()
	private String				num;										//序号
	@FieldDef()
	private String				formate;									//格式
	@FieldDef()
	private Integer				length;										//长度
	/**@see CodePart*/
	@FieldDef(defaultValue = "4")
	private String				type;										//类型

	public String getMid()
	{
		return mid;
	}

	public void setMid(String mid)
	{
		setValue("mid", mid);
	}

	public String getNum()
	{
		return num;
	}

	public void setNum(String num)
	{
		setValue("num", num);
	}

	public String getFormate()
	{
		return formate;
	}

	public void setFormate(String formate)
	{
		setValue("formate", formate);
	}

	public Integer getLength()
	{
		return length;
	}

	public void setLength(Integer length)
	{
		setValue("length", length);
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		setValue("type", type);
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		setValue("id", id);
	}
}
