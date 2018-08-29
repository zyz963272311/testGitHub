package xyz.zyzhu.spring.boot.model;

import javax.persistence.Table;
import com.liiwin.annotation.FieldDef;
/**
 * <p>标题： 微信支付结果对象</p>
 * <p>功能： </p>
 * <p>所属模块： boot</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年8月28日 上午9:55:11</p>
 * <p>类全名：xyz.zyzhu.spring.boot.model.WXPayResult</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Table(name = "wxpay")
public class WXPayResult extends BasModel
{
	private static final long	serialVersionUID	= 5885815657259692256L;
	@FieldDef(defaultValue = "AutoCode:payYYMMdd____________")
	private String				id;
	@FieldDef()
	private String				appid;
	@FieldDef()
	private String				banktype;
	@FieldDef()
	private String				cashfee;
	@FieldDef()
	private String				couponcount;
	@FieldDef()
	private String				couponfee;
	@FieldDef()
	private String				couponfee0;
	@FieldDef()
	private String				couponid0;
	@FieldDef()
	private String				deviceinfo;
	@FieldDef()
	private String				feetype;
	@FieldDef()
	private String				issubscribe;
	@FieldDef()
	private String				mchid;
	@FieldDef()
	private String				noncestr;
	@FieldDef()
	private String				openid;
	@FieldDef()
	private String				outtradeno;
	@FieldDef()
	private String				resultcode;
	@FieldDef()
	private String				returncode;
	@FieldDef()
	private String				sign;
	@FieldDef()
	private String				timeend;
	@FieldDef()
	private Integer				totalfree;
	@FieldDef()
	private String				tradetype;
	@FieldDef()
	private String				transactionid;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		setValue("id", id);
	}

	public String getAppid()
	{
		return appid;
	}

	public void setAppid(String appid)
	{
		setValue("appid", appid);
	}

	public String getBanktype()
	{
		return banktype;
	}

	public void setBanktype(String banktype)
	{
		setValue("banktype", banktype);
	}

	public String getCashfee()
	{
		return cashfee;
	}

	public void setCashfee(String cashfee)
	{
		setValue("cashfee", cashfee);
	}

	public String getCouponcount()
	{
		return couponcount;
	}

	public void setCouponcount(String couponcount)
	{
		setValue("couponcount", couponcount);
	}

	public String getCouponfee()
	{
		return couponfee;
	}

	public void setCouponfee(String couponfee)
	{
		setValue("couponfee", couponfee);
	}

	public String getCouponfee0()
	{
		return couponfee0;
	}

	public void setCouponfee0(String couponfee0)
	{
		setValue("couponfee0", couponfee0);
	}

	public String getCouponid0()
	{
		return couponid0;
	}

	public void setCouponid0(String couponid0)
	{
		setValue("couponid0", couponid0);
	}

	public String getDeviceinfo()
	{
		return deviceinfo;
	}

	public void setDeviceinfo(String deviceinfo)
	{
		setValue("deviceinfo", deviceinfo);
	}

	public String getFeetype()
	{
		return feetype;
	}

	public void setFeetype(String feetype)
	{
		setValue("feetype", feetype);
	}

	public String getIssubscribe()
	{
		return issubscribe;
	}

	public void setIssubscribe(String issubscribe)
	{
		setValue("issubscribe", issubscribe);
	}

	public String getMchid()
	{
		return mchid;
	}

	public void setMchid(String mchid)
	{
		setValue("mchid", mchid);
	}

	public String getNoncestr()
	{
		return noncestr;
	}

	public void setNoncestr(String noncestr)
	{
		setValue("noncestr", noncestr);
	}

	public String getOpenid()
	{
		return openid;
	}

	public void setOpenid(String openid)
	{
		setValue("openid", openid);
	}

	public String getOuttradeno()
	{
		return outtradeno;
	}

	public void setOuttradeno(String outtradeno)
	{
		setValue("outtradeno", outtradeno);
	}

	public String getResultcode()
	{
		return resultcode;
	}

	public void setResultcode(String resultcode)
	{
		setValue("resultcode", resultcode);
	}

	public String getReturncode()
	{
		return returncode;
	}

	public void setReturncode(String returncode)
	{
		setValue("returncode", returncode);
	}

	public String getSign()
	{
		return sign;
	}

	public void setSign(String sign)
	{
		setValue("sign", sign);
	}

	public String getTimeend()
	{
		return timeend;
	}

	public void setTimeend(String timeend)
	{
		setValue("timeend", timeend);
	}

	public Integer getTotalfree()
	{
		return totalfree;
	}

	public void setTotalfree(Integer totalfree)
	{
		setValue("totalfree", totalfree);
	}

	public String getTradetype()
	{
		return tradetype;
	}

	public void setTradetype(String tradetype)
	{
		setValue("tradetype", tradetype);
	}

	public String getTransactionid()
	{
		return transactionid;
	}

	public void setTransactionid(String transactionid)
	{
		setValue("transactionid", transactionid);
	}
}
