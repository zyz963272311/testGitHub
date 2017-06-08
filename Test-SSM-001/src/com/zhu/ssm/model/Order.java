package com.zhu.ssm.model;

import java.io.Serializable;
import java.util.Date;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年6月8日 上午9:43:30</p>
 * <p>类全名：com.zhu.ssm.model.Order</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Order implements Serializable
{
	private String	orderid;
	private String	userid;
	private String	goodsid;
	private double	price;
	private Date	predate;
	private double	qty;
	private String	market;
	private Goods	good;
	private User	user;

	public String getOrderid()
	{
		return orderid;
	}

	public void setOrderid(String orderid)
	{
		this.orderid = orderid;
	}

	public String getUserid()
	{
		return userid;
	}

	public void setUserid(String userid)
	{
		this.userid = userid;
	}

	public String getGoodsid()
	{
		return goodsid;
	}

	public void setGoodsid(String goodsid)
	{
		this.goodsid = goodsid;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public Date getPredate()
	{
		return predate;
	}

	public void setPredate(Date predate)
	{
		this.predate = predate;
	}

	public double getQty()
	{
		return qty;
	}

	public void setQty(double qty)
	{
		this.qty = qty;
	}

	public String getMarket()
	{
		return market;
	}

	public void setMarket(String market)
	{
		this.market = market;
	}

	public Goods getGood()
	{
		return good;
	}

	public void setGood(Goods good)
	{
		this.good = good;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}
}
