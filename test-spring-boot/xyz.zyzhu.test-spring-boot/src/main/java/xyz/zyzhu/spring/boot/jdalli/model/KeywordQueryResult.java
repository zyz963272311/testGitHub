package xyz.zyzhu.spring.boot.jdalli.model;

import java.math.BigDecimal;
import xyz.zyzhu.spring.boot.model.BasObject;
/**
 * <p>标题： 关键词查询结果</p>
 * <p>功能： </p>
 * <p>所属模块：JDALLI</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月20日 下午9:51:04</p>
 * <p>类全名：xyz.zyzhu.spring.boot.jdalli.model.KeywordQueryResult</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class KeywordQueryResult extends BasObject
{
	private static final long	serialVersionUID	= 5339931444844987023L;
	//	[skuId:商品id],[skuName:商品名称],[imageUrl:商品url],[pcPrice:PC价格],[wlPrice:无线价格],[
	//pcCommission:PC佣金],[wlCommission:无线佣金],[pcCommissionShare:PC佣金比例],[wlCommissionShare:无线佣金比例],
	//[materialUrl:商品落地页],[total:商品数量]  
	private String				skuId;										//商品id
	private String				skuName;									//商品名称
	private String				imageUrl;									//商品url
	private BigDecimal			pcPrice;									//PC价格
	private BigDecimal			wlPrice;									//无线价格
	private BigDecimal			pcCommission;								//PC佣金
	private BigDecimal			wlCommission;								//无线佣金
	private BigDecimal			pcCommissionShare;							//PC佣金比例
	private BigDecimal			wlCommissionShare;							//无线佣金比例
	private String				materialUrl;								//商品落地页
	private BigDecimal			total;										//商品总数量

	public String getSkuId()
	{
		return skuId;
	}

	public void setSkuId(String skuId)
	{
		//setValue("skuId", skuId);
		this.skuId = skuId;
	}

	public String getSkuName()
	{
		return skuName;
	}

	public void setSkuName(String skuName)
	{
		//setValue("skuName", skuName);
		this.skuName = skuName;
	}

	public String getImageUrl()
	{
		return imageUrl;
	}

	public void setImageUrl(String imageUrl)
	{
		//setValue("imageUrl", imageUrl);
		this.imageUrl = imageUrl;
	}

	public BigDecimal getPcPrice()
	{
		return pcPrice;
	}

	public void setPcPrice(BigDecimal pcPrice)
	{
		//setValue("pcPrice", pcPrice);
		this.pcPrice = pcPrice;
	}

	public BigDecimal getWlPrice()
	{
		return wlPrice;
	}

	public void setWlPrice(BigDecimal wlPrice)
	{
		//setValue("wlPrice", wlPrice);
		this.wlPrice = wlPrice;
	}

	public BigDecimal getPcCommission()
	{
		return pcCommission;
	}

	public void setPcCommission(BigDecimal pcCommission)
	{
		//setValue("pcCommission", pcCommission);
		this.pcCommission = pcCommission;
	}

	public BigDecimal getWlCommission()
	{
		return wlCommission;
	}

	public void setWlCommission(BigDecimal wlCommission)
	{
		//setValue("wlCommission", wlCommission);
		this.wlCommission = wlCommission;
	}

	public BigDecimal getPcCommissionShare()
	{
		return pcCommissionShare;
	}

	public void setPcCommissionShare(BigDecimal pcCommissionShare)
	{
		//setValue("pcCommissionShare", pcCommissionShare);
		this.pcCommissionShare = pcCommissionShare;
	}

	public BigDecimal getWlCommissionShare()
	{
		return wlCommissionShare;
	}

	public void setWlCommissionShare(BigDecimal wlCommissionShare)
	{
		//setValue("wlCommissionShare", wlCommissionShare);
		this.wlCommissionShare = wlCommissionShare;
	}

	public String getMaterialUrl()
	{
		return materialUrl;
	}

	public void setMaterialUrl(String materialUrl)
	{
		//setValue("materialUrl", materialUrl);
		this.materialUrl = materialUrl;
	}

	public BigDecimal getTotal()
	{
		return total;
	}

	public void setTotal(BigDecimal total)
	{
		//setValue("total", total);
		this.total = total;
	}
}
