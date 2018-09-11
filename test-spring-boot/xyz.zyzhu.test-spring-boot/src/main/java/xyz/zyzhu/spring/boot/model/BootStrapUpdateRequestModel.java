package xyz.zyzhu.spring.boot.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
/**
 * <p>标题： bootstrap table更新model</p>
 * <p>功能： </p>
 * <p>所属模块： boot</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月11日 上午9:24:49</p>
 * <p>类全名：xyz.zyzhu.spring.boot.model.BootStrapUpdateRequestModel</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class BootStrapUpdateRequestModel implements Serializable
{
	private static final long			serialVersionUID	= 529590156807340834L;
	private String						table;
	//存盘类型 1:调用insert方法;2:调用update方法;4:删除更新[删除空字段]; 默认位1
	private Integer						options;
	private List<Map<String,Object>>	tableValues;
	private String						modelClass;

	public String getTable()
	{
		return table;
	}

	public void setTable(String table)
	{
		//setValue("table", table);
		this.table = table;
	}

	public Integer getOptions()
	{
		return options;
	}

	public void setOptions(Integer options)
	{
		//setValue("options", options);
		this.options = options;
	}

	public List<Map<String,Object>> getTableValues()
	{
		return tableValues;
	}

	public void setTableValues(List<Map<String,Object>> tableValues)
	{
		//setValue("tableValues", tableValues);
		this.tableValues = tableValues;
	}

	public String getModelClass()
	{
		return modelClass;
	}

	public void setModelClass(String modelClass)
	{
		//setValue("modelClass", modelClass);
		this.modelClass = modelClass;
	}
}
