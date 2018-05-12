package com.liiwin.data.comm;

import java.io.Serializable;
import java.util.List;
/**
 * <p>标题： 导入导出节点定义</p>
 * <p>功能： </p>
 * <p>所属模块： rootbas</p>
 * <p>版权： Copyright © 2018 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2018年5月7日 下午4:46:17</p>
 * <p>类全名：com.liiwin.data.comm.FieldDef</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public abstract class FieldDef implements Serializable
{
	private static final long	serialVersionUID	= 1L;
	private String				no;						//编号
	private String				path;
	private String				element;					//节点名称
	private FieldDef			parentDef;					//父节点
	private List<FieldDef>		subDefs;					//子节点
	private String				tableName;					//表名
	private String				fieldName;					//字段名
	private Integer				notNull;					//非空
	private String				codedata;					//码名映射
	private Integer				codedataOptions;			//码名属性
	private Boolean				isTable;					//是否为表
	private Boolean				isLoop;					//是否循环
	private String				defaultvalue;				//默认值
	private String				copyMainField;				//拷贝主表字段

	public String getNo()
	{
		return no;
	}

	public void setNo(String no)
	{
		this.no = no;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public String getElement()
	{
		return element;
	}

	public void setElement(String element)
	{
		this.element = element;
	}

	public FieldDef getParentDef()
	{
		return parentDef;
	}

	public void setParentDef(FieldDef parentDef)
	{
		this.parentDef = parentDef;
	}

	public List<FieldDef> getSubDefs()
	{
		return subDefs;
	}

	public void setSubDefs(List<FieldDef> subDefs)
	{
		this.subDefs = subDefs;
	}

	public String getTableName()
	{
		return tableName;
	}

	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}

	public String getFieldName()
	{
		return fieldName;
	}

	public void setFieldName(String fieldName)
	{
		this.fieldName = fieldName;
	}

	public Integer getNotNull()
	{
		return notNull;
	}

	public void setNotNull(Integer notNull)
	{
		this.notNull = notNull;
	}

	public String getCodedata()
	{
		return codedata;
	}

	public void setCodedata(String codedata)
	{
		this.codedata = codedata;
	}

	public Integer getCodedataOptions()
	{
		return codedataOptions;
	}

	public void setCodedataOptions(Integer codedataOptions)
	{
		this.codedataOptions = codedataOptions;
	}

	public Boolean getIsTable()
	{
		return isTable;
	}

	public void setIsTable(Boolean isTable)
	{
		this.isTable = isTable;
	}

	public Boolean getIsLoop()
	{
		return isLoop;
	}

	public void setIsLoop(Boolean isLoop)
	{
		this.isLoop = isLoop;
	}

	public String getDefaultvalue()
	{
		return defaultvalue;
	}

	public void setDefaultvalue(String defaultvalue)
	{
		this.defaultvalue = defaultvalue;
	}

	public String getCopyMainField()
	{
		return copyMainField;
	}

	public void setCopyMainField(String copyMainField)
	{
		this.copyMainField = copyMainField;
	}

	/**
	 * 导入导出Flags
	 * @return
	 * 赵玉柱
	 */
	public abstract Integer getIEFlags();
}
