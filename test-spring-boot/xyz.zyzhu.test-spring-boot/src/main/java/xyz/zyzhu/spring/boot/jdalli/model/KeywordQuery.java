package xyz.zyzhu.spring.boot.jdalli.model;

import xyz.zyzhu.spring.boot.model.BasObject;
/**
 * <p>标题： 关键词查询</p>
 * <p>功能： </p>
 * <p>所属模块： JDAlli</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月20日 下午9:50:45</p>
 * <p>类全名：xyz.zyzhu.spring.boot.jdalli.model.KeywordQuery</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class KeywordQuery extends BasObject
{
	private static final long	serialVersionUID	= -4549441913489525221L;
	private Integer				cat1Id;										//一级类目
	private Integer				cat2Id;										//二级类目
	private Integer				cat3Id;										//三级类目
	private String				keyword;									//关键词
	private Integer				page_index;									//页码
	private Integer				page_size;									//每页行数
	private String				sort_name;									//排序字段[pcPrice pc价],[pcCommission pc佣金],[pcCommissionShare pc佣金比例],[inOrderCount30Days 30天引入订单量],[inOrderComm30Days 30天支出佣金] 
	private String				sort;										// asc,desc升降序,默认降序 

	public Integer getCat1Id()
	{
		return cat1Id;
	}

	public void setCat1Id(Integer cat1Id)
	{
		//setValue("cat1Id", cat1Id);
		this.cat1Id = cat1Id;
	}

	public Integer getCat2Id()
	{
		return cat2Id;
	}

	public void setCat2Id(Integer cat2Id)
	{
		//setValue("cat2Id", cat2Id);
		this.cat2Id = cat2Id;
	}

	public Integer getCat3Id()
	{
		return cat3Id;
	}

	public void setCat3Id(Integer cat3Id)
	{
		//setValue("cat3Id", cat3Id);
		this.cat3Id = cat3Id;
	}

	public String getKeyword()
	{
		return keyword;
	}

	public void setKeyword(String keyword)
	{
		//setValue("keyword", keyword);
		this.keyword = keyword;
	}

	public Integer getPage_index()
	{
		return page_index;
	}

	public void setPage_index(Integer page_index)
	{
		//setValue("page_index", page_index);
		this.page_index = page_index;
	}

	public Integer getPage_size()
	{
		return page_size;
	}

	public void setPage_size(Integer page_size)
	{
		//setValue("page_size", page_size);
		this.page_size = page_size;
	}

	public String getSort_name()
	{
		return sort_name;
	}

	public void setSort_name(String sort_name)
	{
		//setValue("sort_name", sort_name);
		this.sort_name = sort_name;
	}

	public String getSort()
	{
		return sort;
	}

	public void setSort(String sort)
	{
		//setValue("sort", sort);
		this.sort = sort;
	}
}
