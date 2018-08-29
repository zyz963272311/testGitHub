package xyz.zyzhu.spring.boot.model;

import java.util.Date;
import javax.persistence.Table;
import com.liiwin.annotation.FieldDef;
/**
 * <p>标题： 微信订单</p>
 * <p>功能： </p>
 * <p>所属模块： boot</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年8月28日 上午10:12:33</p>
 * <p>类全名：xyz.zyzhu.spring.boot.model.WXOrder</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Table(name = "wxorder")
public class WXOrder extends BasModel
{
	private static final long	serialVersionUID	= -3827461053269356437L;
	//订单号，对应out_trade_no；商户系统内部订单号，要求32个字符内、且在同一个商户号下唯一。
	@FieldDef(defaultValue = "AutoCode:orderYYMMdd____________")
	private String				orderid;
	//商品描述，对应body
	@FieldDef()
	private String				ordername;
	//商品详情
	@FieldDef()
	private String				detail;
	//价格，单位分
	@FieldDef()
	private String				totalfee;
	//附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用。
	@FieldDef()
	private String				attach;
	//trade_type=NATIVE时（即扫码支付），此参数必传。此参数为二维码中包含的商品ID，商户自行定义。
	@FieldDef()
	private String				productid;
	//取值如下：JSAPI，NATIVE，APP等
	@FieldDef()
	private String				tradetype			= "JSAPI";
	//商品数量
	@FieldDef()
	private Integer				goodscount;
	//商品总价
	@FieldDef()
	private Integer				totalgoodsprice;
	//快递总价
	@FieldDef()
	private Integer				totalexpprice;
	//订单总价
	@FieldDef()
	private Integer				totalprice;
	//下单时间
	@FieldDef()
	private Date				createtime;
	//支付时间
	@FieldDef()
	private Date				paytime;
	//完成时间	
	@FieldDef()
	private Date				finishtime;
	//退单申请时间	
	@FieldDef()
	private Date				backapplytime;
	//退单完成时间
	@FieldDef()
	private Date				backfinishtime;
	//作废时间
	@FieldDef()
	private Date				abandontime;
	//制单人
	@FieldDef()
	private String				creater;
	//状态  10:制单；20:支付中；30：支付完成；35：订单完成；40：申请退单；50：退单中；60：退单完成；90作废
	@FieldDef(defaultValue = "10")
	private String				status;

	public String getOrderid()
	{
		return orderid;
	}

	public void setOrderid(String orderid)
	{
		setValue("orderid", orderid);
	}

	public String getOrdername()
	{
		return ordername;
	}

	public void setOrdername(String ordername)
	{
		setValue("ordername", ordername);
	}

	public String getDetail()
	{
		return detail;
	}

	public void setDetail(String detail)
	{
		setValue("detail", detail);
	}

	public String getTotalfee()
	{
		return totalfee;
	}

	public void setTotalfee(String totalfee)
	{
		setValue("totalfee", totalfee);
	}

	public String getAttach()
	{
		return attach;
	}

	public void setAttach(String attach)
	{
		setValue("attach", attach);
	}

	public String getProductid()
	{
		return productid;
	}

	public void setProductid(String productid)
	{
		setValue("productid", productid);
	}

	public String getTradetype()
	{
		return tradetype;
	}

	public void setTradetype(String tradetype)
	{
		setValue("tradetype", tradetype);
	}

	public Integer getGoodscount()
	{
		return goodscount;
	}

	public void setGoodscount(Integer goodscount)
	{
		setValue("goodscount", goodscount);
	}

	public Integer getTotalprice()
	{
		return totalprice;
	}

	public void setTotalprice(Integer totalprice)
	{
		setValue("totalprice", totalprice);
	}

	public Date getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Date createtime)
	{
		setValue("createtime", createtime);
	}

	public String getCreater()
	{
		return creater;
	}

	public void setCreater(String creater)
	{
		setValue("creater", creater);
	}

	public Date getPaytime()
	{
		return paytime;
	}

	public void setPaytime(Date paytime)
	{
		setValue("paytime", paytime);
	}

	public Date getFinishtime()
	{
		return finishtime;
	}

	public void setFinishtime(Date finishtime)
	{
		setValue("finishtime", finishtime);
	}

	public Date getBackapplytime()
	{
		return backapplytime;
	}

	public void setBackapplytime(Date backapplytime)
	{
		setValue("backapplytime", backapplytime);
	}

	public Date getBackfinishtime()
	{
		return backfinishtime;
	}

	public void setBackfinishtime(Date backfinishtime)
	{
		setValue("backfinishtime", backfinishtime);
	}

	public Date getAbandontime()
	{
		return abandontime;
	}

	public void setAbandontime(Date abandontime)
	{
		setValue("abandontime", abandontime);
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		setValue("status", status);
	}

	public Integer getTotalgoodsprice()
	{
		return totalgoodsprice;
	}

	public void setTotalgoodsprice(Integer totalgoodsprice)
	{
		setValue("totalgoodsprice", totalgoodsprice);
	}

	public Integer getTotalexpprice()
	{
		return totalexpprice;
	}

	public void setTotalexpprice(Integer totalexpprice)
	{
		setValue("totalexpprice", totalexpprice);
	}
}
