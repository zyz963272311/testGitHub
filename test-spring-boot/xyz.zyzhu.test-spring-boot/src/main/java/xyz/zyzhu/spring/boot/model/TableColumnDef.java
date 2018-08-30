package xyz.zyzhu.spring.boot.model;

import com.liiwin.utils.StrUtil;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年8月28日 下午4:40:01</p>
 * <p>类全名：xyz.zyzhu.spring.boot.model.TableColumn</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TableColumnDef
{
	private static final long	serialVersionUID	= 332911711470920223L;
	//列名
	//	@FieldDef()
	private String				colname;
	//表名
	//	@FieldDef()
	private String				tbname;
	//说明
	//	@FieldDef()
	private String				comment;
	//默认值
	//	@FieldDef()
	private String				defaultvalue;
	//列类型
	//	@FieldDef()
	private String				datatype;
	//列长度
	//	@FieldDef()
	private Integer				dataLength;
	//列精度
	//	@FieldDef()
	private Integer				decimal;
	//标记
	//	@FieldDef()
	private Integer				flags;

	/**
	 * 是否主键
	 * @return
	 * 赵玉柱
	 */
	public boolean isPrimary()
	{
		int oldFlags = StrUtil.obj2int(getFlags());
		return (oldFlags & 2) > 0;
	}

	/**
	 * 是否非空
	 * @return
	 * 赵玉柱
	 */
	public boolean isNotNull()
	{
		int oldFlags = StrUtil.obj2int(getFlags());
		return (oldFlags & 1) > 0;
	}

	public String getColname()
	{
		return colname;
	}

	public void setColname(String colname)
	{
		//setValue("colname", colname);
		this.colname = colname;
	}

	public String getTbname()
	{
		return tbname;
	}

	public void setTbname(String tbname)
	{
		//setValue("tbname", tbname);
		this.tbname = tbname;
	}

	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		//setValue("comment", comment);
		this.comment = comment;
	}

	public String getDefaultvalue()
	{
		return defaultvalue;
	}

	public void setDefaultvalue(String defaultvalue)
	{
		//setValue("defaultvalue", defaultvalue);
		this.defaultvalue = defaultvalue;
	}

	public String getDatatype()
	{
		return datatype;
	}

	public void setDatatype(String datatype)
	{
		//setValue("datatype", datatype);
		this.datatype = datatype;
	}

	public Integer getDataLength()
	{
		return dataLength;
	}

	public void setDataLength(Integer dataLength)
	{
		//setValue("dataLength", dataLength);
		this.dataLength = dataLength;
	}

	public Integer getDecimal()
	{
		return decimal;
	}

	public void setDecimal(Integer decimal)
	{
		//setValue("decimal", decimal);
		this.decimal = decimal;
	}

	public Integer getFlags()
	{
		return flags;
	}

	public void setFlags(Integer flags)
	{
		//setValue("flags", flags);
		this.flags = flags;
	}
}
