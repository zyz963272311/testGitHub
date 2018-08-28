package xyz.zyzhu.spring.boot.model;

import javax.persistence.Table;
import xyz.zyzhu.spring.boot.annotation.FieldDef;
/**
 * <p>标题： 微信退款申请</p>
 * <p>功能： </p>
 * <p>所属模块： boot</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年8月28日 上午10:59:53</p>
 * <p>类全名：xyz.zyzhu.spring.boot.model.WXRefuseApp</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Table(name = "wxrefuseapp")
public class WXRefuseApp extends BasModel
{
	private static final long	serialVersionUID	= -6723360211596300225L;
	//退单申请内码
	@FieldDef(defaultValue = "AutoCode:ridYYMMdd____________")
	private String				refuseappid;
	//退单申请编号
	@FieldDef(defaultValue = "AutoCode:rnoYYMMdd____________")
	private String				outrefundno;
	//openid
	@FieldDef()
	private String				appid;
	//商户编号
	@FieldDef()
	private String				mchid;
	//随机字符串
	@FieldDef()
	private String				noncestr;
	//签名
	@FieldDef()
	private String				sign;
	//微信订单号
	@FieldDef()
	private String				transactionid;
	//商户订单号
	@FieldDef()
	private String				outtradeno;
	//总价
	@FieldDef()
	private Integer				totalprice;
	//退单价格
	@FieldDef()
	private Integer				refundprice;
	//退单原因
	@FieldDef()
	private String				refunddesc;
	//退单完成通知地址
	@FieldDef()
	private String				notifyurl;

	public String getRefuseid()
	{
		return refuseappid;
	}

	public String getRefuseappid()
	{
		return refuseappid;
	}

	public void setRefuseappid(String refuseappid)
	{
		setValue("refuseappid", refuseappid);
	}

	public String getOutrefundno()
	{
		return outrefundno;
	}

	public void setOutrefundno(String outrefundno)
	{
		setValue("outrefundno", outrefundno);
	}

	public String getAppid()
	{
		return appid;
	}

	public void setAppid(String appid)
	{
		setValue("appid", appid);
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

	public String getSign()
	{
		return sign;
	}

	public void setSign(String sign)
	{
		setValue("sign", sign);
	}

	public String getTransactionid()
	{
		return transactionid;
	}

	public void setTransactionid(String transactionid)
	{
		setValue("transactionid", transactionid);
	}

	public String getOuttradeno()
	{
		return outtradeno;
	}

	public void setOuttradeno(String outtradeno)
	{
		setValue("outtradeno", outtradeno);
	}

	public Integer getTotalprice()
	{
		return totalprice;
	}

	public void setTotalprice(Integer totalprice)
	{
		setValue("totalprice", totalprice);
	}

	public Integer getRefundprice()
	{
		return refundprice;
	}

	public void setRefundprice(Integer refundprice)
	{
		setValue("refundprice", refundprice);
	}

	public String getRefunddesc()
	{
		return refunddesc;
	}

	public void setRefunddesc(String refunddesc)
	{
		setValue("refunddesc", refunddesc);
	}

	public String getNotifyurl()
	{
		return notifyurl;
	}

	public void setNotifyurl(String notifyurl)
	{
		setValue("notifyurl", notifyurl);
	}
}
