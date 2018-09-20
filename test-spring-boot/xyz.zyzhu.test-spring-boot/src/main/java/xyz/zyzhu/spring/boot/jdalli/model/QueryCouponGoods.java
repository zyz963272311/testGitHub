package xyz.zyzhu.spring.boot.jdalli.model;

import xyz.zyzhu.spring.boot.model.BasObject;
/**
 * <p>标题： 获取优惠商品【申请】</p>
 * <p>功能： </p>
 * <p>所属模块： JDAlli</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月20日 下午10:06:25</p>
 * <p>类全名：xyz.zyzhu.spring.boot.jdalli.model.QueryCouponGoods</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class QueryCouponGoods extends BasObject
{
	private static final long	serialVersionUID	= 8843716634879231970L;
	private Integer				from;										//起始条数（例如，每页100条，第一页0，第二页100)。 
	private Integer				pageSize;									//每页多少条 
	private Integer				cid3;										//三级类目 

	public Integer getFrom()
	{
		return from;
	}

	public void setFrom(Integer from)
	{
		//setValue("from", from);
		this.from = from;
	}

	public Integer getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(Integer pageSize)
	{
		//setValue("pageSize", pageSize);
		this.pageSize = pageSize;
	}

	public Integer getCid3()
	{
		return cid3;
	}

	public void setCid3(Integer cid3)
	{
		//setValue("cid3", cid3);
		this.cid3 = cid3;
	}
}
