package xyz.zyzhu.spring.boot.model;

import javax.persistence.Table;
import xyz.zyzhu.spring.boot.annotation.FieldDef;
/**
 * <p>标题：微信订单商家</p>
 * <p>功能： </p>
 * <p>所属模块： 微信订单商家</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年8月28日 上午10:38:30</p>
 * <p>类全名：xyz.zyzhu.spring.boot.model.WXOrderBusiness</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Table(name = "wxorderbusiness")
public class WXOrderBusiness extends BasModel
{
	private static final long	serialVersionUID	= 1926550348903702192L;
	//商家订单内码
	@FieldDef(defaultValue = "AutoCode:busiYYMMdd____________")
	private String				busiid;
	//订单内码
	@FieldDef()
	private String				orderid;
	//商家唯一编号
	@FieldDef()
	private String				busiopenid;
	//商品数量
	@FieldDef()
	private Integer				goodscount;
	//商品总价
	@FieldDef()
	private Integer				goodstotalprice;
	//快递公司
	@FieldDef()
	private String				expcode;
	//商品总重量
	@FieldDef()
	private Integer				totalweight;
	//快递价格
	@FieldDef()
	private Integer				expprice;
	//总价  商品总价+快递价格
	@FieldDef()
	private Integer				totalprice;

	public String getBusiid()
	{
		return busiid;
	}

	public void setBusiid(String busiid)
	{
		setValue("busiid", busiid);
	}

	public String getOrderid()
	{
		return orderid;
	}

	public void setOrderid(String orderid)
	{
		setValue("orderid", orderid);
	}

	public String getBusiopenid()
	{
		return busiopenid;
	}

	public void setBusiopenid(String busiopenid)
	{
		setValue("busiopenid", busiopenid);
	}

	public Integer getGoodscount()
	{
		return goodscount;
	}

	public void setGoodscount(Integer goodscount)
	{
		setValue("goodscount", goodscount);
	}

	public Integer getGoodstotalprice()
	{
		return goodstotalprice;
	}

	public void setGoodstotalprice(Integer goodstotalprice)
	{
		setValue("goodstotalprice", goodstotalprice);
	}

	public String getExpcode()
	{
		return expcode;
	}

	public void setExpcode(String expcode)
	{
		setValue("expcode", expcode);
	}

	public Integer getTotalweight()
	{
		return totalweight;
	}

	public void setTotalweight(Integer totalweight)
	{
		setValue("totalweight", totalweight);
	}

	public Integer getExpprice()
	{
		return expprice;
	}

	public void setExpprice(Integer expprice)
	{
		setValue("expprice", expprice);
	}

	public Integer getTotalprice()
	{
		return totalprice;
	}

	public void setTotalprice(Integer totalprice)
	{
		setValue("totalprice", totalprice);
	}
}
