package xyz.zyzhu.spring.boot.jdalli.model;

import java.math.BigDecimal;
import xyz.zyzhu.spring.boot.model.BasObject;
/**
 * <p>标题： 获取优惠商品【申请】结果</p>
 * <p>功能： </p>
 * <p>所属模块： JDAlli</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月20日 下午10:08:26</p>
 * <p>类全名：xyz.zyzhu.spring.boot.jdalli.model.QueryCouponGoodsResult</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class QueryCouponGoodsResult extends BasObject
{
	private static final long	serialVersionUID	= 4316595047996196874L;
	private String				skuId;										//商品id
	private String				skuName;									//商品名称
	private String				skuUrl;										//商品URL地址
	private String				shopName;									//店铺名称
	private String				shopUrl;									//店铺地址
	private String				imgUrl;										//图片地址
	private BigDecimal			pcPrice;									//pc价格
	private BigDecimal			wlPrice;									//无线价格
	private BigDecimal			exPrice;									//专享价格
	private String				couponTab;									//优惠券标签
	private String				couponNote;									//优惠券话术
	private BigDecimal			discountPrice;								//商品折扣金额
	private BigDecimal			discountRate;								//折扣
	private String				startTime;									//推广开始时间
	private String				endTime;									//推广结束时间
	private String				isLock;										//锁定计划标志
	private String				manJianNote;								//满减话术
	private BigDecimal			pcCommission;								// pc佣金金额
	private BigDecimal			pcCommissionShare;							// PC佣金比例
	private BigDecimal			wlCommission;								// wl佣金金额
	private BigDecimal			wlCommissionShare;							//无线佣金比例
	private String				inOrderComm;								// 排序标示30天引入佣金金额
	private String				inOrderCount;								// 排序标示30天引入量
	private String				plan;										// 计划
	private String				isPlan;										// 定向计划
	private String				prmTab;										// 促销标签
	private String				realRate;									// 商品几折
	private String				adowner;									// 广告主
	private String				vid;										//商家id
	private BigDecimal			total;										//商品数量
	private String				resultCode;									//接口调用是否成功（200：接口返回成功）]

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

	public String getSkuUrl()
	{
		return skuUrl;
	}

	public void setSkuUrl(String skuUrl)
	{
		//setValue("skuUrl", skuUrl);
		this.skuUrl = skuUrl;
	}

	public String getShopName()
	{
		return shopName;
	}

	public void setShopName(String shopName)
	{
		//setValue("shopName", shopName);
		this.shopName = shopName;
	}

	public String getShopUrl()
	{
		return shopUrl;
	}

	public void setShopUrl(String shopUrl)
	{
		//setValue("shopUrl", shopUrl);
		this.shopUrl = shopUrl;
	}

	public String getImgUrl()
	{
		return imgUrl;
	}

	public void setImgUrl(String imgUrl)
	{
		//setValue("imgUrl", imgUrl);
		this.imgUrl = imgUrl;
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

	public BigDecimal getExPrice()
	{
		return exPrice;
	}

	public void setExPrice(BigDecimal exPrice)
	{
		//setValue("exPrice", exPrice);
		this.exPrice = exPrice;
	}

	public String getCouponTab()
	{
		return couponTab;
	}

	public void setCouponTab(String couponTab)
	{
		//setValue("couponTab", couponTab);
		this.couponTab = couponTab;
	}

	public String getCouponNote()
	{
		return couponNote;
	}

	public void setCouponNote(String couponNote)
	{
		//setValue("couponNote", couponNote);
		this.couponNote = couponNote;
	}

	public BigDecimal getDiscountPrice()
	{
		return discountPrice;
	}

	public void setDiscountPrice(BigDecimal discountPrice)
	{
		//setValue("discountPrice", discountPrice);
		this.discountPrice = discountPrice;
	}

	public BigDecimal getDiscountRate()
	{
		return discountRate;
	}

	public void setDiscountRate(BigDecimal discountRate)
	{
		//setValue("discountRate", discountRate);
		this.discountRate = discountRate;
	}

	public String getStartTime()
	{
		return startTime;
	}

	public void setStartTime(String startTime)
	{
		//setValue("startTime", startTime);
		this.startTime = startTime;
	}

	public String getEndTime()
	{
		return endTime;
	}

	public void setEndTime(String endTime)
	{
		//setValue("endTime", endTime);
		this.endTime = endTime;
	}

	public String getIsLock()
	{
		return isLock;
	}

	public void setIsLock(String isLock)
	{
		//setValue("isLock", isLock);
		this.isLock = isLock;
	}

	public String getManJianNote()
	{
		return manJianNote;
	}

	public void setManJianNote(String manJianNote)
	{
		//setValue("manJianNote", manJianNote);
		this.manJianNote = manJianNote;
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

	public BigDecimal getPcCommissionShare()
	{
		return pcCommissionShare;
	}

	public void setPcCommissionShare(BigDecimal pcCommissionShare)
	{
		//setValue("pcCommissionShare", pcCommissionShare);
		this.pcCommissionShare = pcCommissionShare;
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

	public BigDecimal getWlCommissionShare()
	{
		return wlCommissionShare;
	}

	public void setWlCommissionShare(BigDecimal wlCommissionShare)
	{
		//setValue("wlCommissionShare", wlCommissionShare);
		this.wlCommissionShare = wlCommissionShare;
	}

	public String getInOrderComm()
	{
		return inOrderComm;
	}

	public void setInOrderComm(String inOrderComm)
	{
		//setValue("inOrderComm", inOrderComm);
		this.inOrderComm = inOrderComm;
	}

	public String getInOrderCount()
	{
		return inOrderCount;
	}

	public void setInOrderCount(String inOrderCount)
	{
		//setValue("inOrderCount", inOrderCount);
		this.inOrderCount = inOrderCount;
	}

	public String getPlan()
	{
		return plan;
	}

	public void setPlan(String plan)
	{
		//setValue("plan", plan);
		this.plan = plan;
	}

	public String getIsPlan()
	{
		return isPlan;
	}

	public void setIsPlan(String isPlan)
	{
		//setValue("isPlan", isPlan);
		this.isPlan = isPlan;
	}

	public String getPrmTab()
	{
		return prmTab;
	}

	public void setPrmTab(String prmTab)
	{
		//setValue("prmTab", prmTab);
		this.prmTab = prmTab;
	}

	public String getRealRate()
	{
		return realRate;
	}

	public void setRealRate(String realRate)
	{
		//setValue("realRate", realRate);
		this.realRate = realRate;
	}

	public String getAdowner()
	{
		return adowner;
	}

	public void setAdowner(String adowner)
	{
		//setValue("adowner", adowner);
		this.adowner = adowner;
	}

	public String getVid()
	{
		return vid;
	}

	public void setVid(String vid)
	{
		//setValue("vid", vid);
		this.vid = vid;
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

	public String getResultCode()
	{
		return resultCode;
	}

	public void setResultCode(String resultCode)
	{
		//setValue("resultCode", resultCode);
		this.resultCode = resultCode;
	}
}
