package xyz.zyzhu.spring.boot.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
/**
 * <p>标题： bootStrap查询请求参数</p>
 * <p>功能： </p>
 * <p>所属模块： boot</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月7日 下午3:56:35</p>
 * <p>类全名：xyz.zyzhu.spring.boot.model.BootStrapQueryRequestModel</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class BootStrapQueryRequestModel implements Serializable
{
	private static final long			serialVersionUID	= 6871825031919095203L;
	private String						tablename;									//查询表名
	private String						columns;									//查询列名
	private String						queryFilter;								//查询条件
	//格式：{"name":"列名","sqlname":"sql字段名","value":"值","compareMethod":"比较方式","prefix","表名前缀"}
	//说明：sqlname权值大于name权值
	//当查询的表名多余一个的情况下prefix字段标识当前的字段名作用在哪个表上
	//compareMethod为比较方式，默认为等于
	private List<Map<String,Object>>	moreQueryFilter;							//更多查询条件
	private Integer						pagefrom;									//查询记录从
	private Integer						pagesize;									//单页记录数
	private String						sortColumns;								//排序字段
	private String						havingFilter;								//having条件
	private String						groupFilter;								//group by 字段
	private String						treeField;									//树状结构根据字段
	private String						parentField;								//父子关系列
	private String						splitStr			= ".";					//树状结构父子关系分割节点

	public String getTablename()
	{
		return tablename;
	}

	public void setTablename(String tablename)
	{
		//setValue("tablename", tablename);
		this.tablename = tablename;
	}

	public String getColumns()
	{
		return columns;
	}

	public void setColumns(String columns)
	{
		//setValue("columns", columns);
		this.columns = columns;
	}

	public String getQueryFilter()
	{
		return queryFilter;
	}

	public void setQueryFilter(String queryFilter)
	{
		//setValue("queryFilter", queryFilter);
		this.queryFilter = queryFilter;
	}

	public Integer getPagefrom()
	{
		return pagefrom;
	}

	public void setPagefrom(Integer pagefrom)
	{
		//setValue("pagefrom", pagefrom);
		this.pagefrom = pagefrom;
	}

	public Integer getPagesize()
	{
		return pagesize;
	}

	public void setPagesize(Integer pagesize)
	{
		//setValue("pagesize", pagesize);
		this.pagesize = pagesize;
	}

	public String getSortColumns()
	{
		return sortColumns;
	}

	public void setSortColumns(String sortColumns)
	{
		//setValue("sortColumns", sortColumns);
		this.sortColumns = sortColumns;
	}

	public String getHavingFilter()
	{
		return havingFilter;
	}

	public void setHavingFilter(String havingFilter)
	{
		//setValue("havingFilter", havingFilter);
		this.havingFilter = havingFilter;
	}

	public String getGroupFilter()
	{
		return groupFilter;
	}

	public void setGroupFilter(String groupFilter)
	{
		//setValue("groupFilter", groupFilter);
		this.groupFilter = groupFilter;
	}

	public List<Map<String,Object>> getMoreQueryFilter()
	{
		return moreQueryFilter;
	}

	public void setMoreQueryFilter(List<Map<String,Object>> moreQueryFilter)
	{
		//setValue("moreQueryFilter", moreQueryFilter);
		this.moreQueryFilter = moreQueryFilter;
	}

	public String getTreeField()
	{
		return treeField;
	}

	public void setTreeField(String treeField)
	{
		//setValue("treeField", treeField);
		this.treeField = treeField;
	}

	public String getSplitStr()
	{
		return splitStr;
	}

	public void setSplitStr(String splitStr)
	{
		//setValue("splitStr", splitStr);
		this.splitStr = splitStr;
	}

	public String getParentField()
	{
		return parentField;
	}

	public void setParentField(String parentField)
	{
		//setValue("parentField", parentField);
		this.parentField = parentField;
	}
}
