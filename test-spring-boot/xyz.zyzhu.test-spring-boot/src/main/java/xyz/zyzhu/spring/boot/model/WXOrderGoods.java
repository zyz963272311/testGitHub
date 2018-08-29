package xyz.zyzhu.spring.boot.model;

import javax.persistence.Table;
import com.liiwin.annotation.FieldDef;
/**
 * <p>标题： 订单商品表</p>
 * <p>功能： </p>
 * <p>所属模块： boot</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年8月28日 上午10:16:22</p>
 * <p>类全名：xyz.zyzhu.spring.boot.model.WXOrderG</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Table(name = "wxordergoods")
public class WXOrderGoods extends BasModel
{
	private static final long	serialVersionUID	= -5554999472023394879L;
	//订单子表内码
	@FieldDef(defaultValue = "AutoCode:ordergYYMMdd____________")
	private String				ordergid;
	//订单内码
	@FieldDef()
	private String				orderid;
	@FieldDef()
	private String				busiid;
	//商品内码
	@FieldDef()
	private String				goodsid;
	//商品名称
	@FieldDef()
	private String				goodsname;
	//单价
	@FieldDef()
	private Integer				unitprice;
	//数量
	@FieldDef()
	private Integer				quantity;
	//商品重量
	@FieldDef()
	private Integer				weight;
	//商品总重
	@FieldDef()
	private Integer				totalweight;
	//总价
	@FieldDef()
	private Integer				totalprice;

	public String getOrdergid()
	{
		return ordergid;
	}

	public void setOrdergid(String ordergid)
	{
		setValue("ordergid", ordergid);
	}

	public String getOrderid()
	{
		return orderid;
	}

	public void setOrderid(String orderid)
	{
		setValue("orderid", orderid);
	}

	public String getGoodsid()
	{
		return goodsid;
	}

	public void setGoodsid(String goodsid)
	{
		setValue("goodsid", goodsid);
	}

	public String getGoodsname()
	{
		return goodsname;
	}

	public void setGoodsname(String goodsname)
	{
		setValue("goodsname", goodsname);
	}

	public Integer getUnitprice()
	{
		return unitprice;
	}

	public void setUnitprice(Integer unitprice)
	{
		setValue("unitprice", unitprice);
	}

	public Integer getQuantity()
	{
		return quantity;
	}

	public void setQuantity(Integer quantity)
	{
		setValue("quantity", quantity);
	}

	public Integer getTotalprice()
	{
		return totalprice;
	}

	public void setTotalprice(Integer totalprice)
	{
		setValue("totalprice", totalprice);
	}

	public String getBusiid()
	{
		return busiid;
	}

	public void setBusiid(String busiid)
	{
		setValue("busiid", busiid);
	}

	public Integer getWeight()
	{
		return weight;
	}

	public void setWeight(Integer weight)
	{
		setValue("weight", weight);
	}

	public Integer getTotalweight()
	{
		return totalweight;
	}

	public void setTotalweight(Integer totalweight)
	{
		setValue("totalweight", totalweight);
	}
}
