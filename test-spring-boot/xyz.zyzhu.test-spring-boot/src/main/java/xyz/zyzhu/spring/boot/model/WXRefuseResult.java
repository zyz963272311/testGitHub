package xyz.zyzhu.spring.boot.model;

import xyz.zyzhu.spring.boot.annotation.FieldDef;
/**
 * <p>标题： 微信退款结果</p>
 * <p>功能： </p>
 * <p>所属模块： boot</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年8月28日 上午11:09:45</p>
 * <p>类全名：xyz.zyzhu.spring.boot.model.WXRefuseResult</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class WXRefuseResult extends BasModel
{
	private static final long	serialVersionUID	= 5623694850263171751L;
	//微信退单id
	@FieldDef(defaultValue = "AutoCode:ridYYMMdd____________")
	private String				refundid;
	//退单申请id
	@FieldDef()
	private String				refuseappid;
	//公众号ID
	@FieldDef()
	private String				appid;
	//商户号
	@FieldDef()
	private String				mch_id;
	//随机字符串
	@FieldDef()
	private String				nonce_str;
	//签名
	@FieldDef()
	private String				sign;
	//微信订单id
	@FieldDef()
	private String				transactionid;
	//商户订单id
	@FieldDef()
	private String				outtradeno;
	//退单id
	@FieldDef()
	private String				outrefundno;
	//退单金额
	@FieldDef()
	private Integer				refundfee;
	//订单总金额
	@FieldDef()
	private Integer				totalfee;
	//现金金额
	@FieldDef()
	private Integer				cashfee;
	//现金退款金额
	@FieldDef()
	private Integer				cashrefundfee;
	//微信退款申请状态返回码 SUCCESS/FAIL
	@FieldDef()
	private String				returncode;
	//微信退款申请返回信息
	@FieldDef()
	private String				returnmsg;
	//微信退款申请 业务结果 SUCCESS/FAIL
	@FieldDef()
	private String				resultcode;
	// 错误代码
	@FieldDef()
	private String				errcode;
	//错误代码描述
	@FieldDef()
	private String				errcodedes;

	public String getRefundid()
	{
		return refundid;
	}

	public void setRefundid(String refundid)
	{
		setValue("refundid", refundid);
	}

	public String getRefuseappid()
	{
		return refuseappid;
	}

	public void setRefuseappid(String refuseappid)
	{
		setValue("refuseappid", refuseappid);
	}

	public String getAppid()
	{
		return appid;
	}

	public void setAppid(String appid)
	{
		setValue("appid", appid);
	}

	public String getMch_id()
	{
		return mch_id;
	}

	public void setMch_id(String mch_id)
	{
		setValue("mch_id", mch_id);
	}

	public String getNonce_str()
	{
		return nonce_str;
	}

	public void setNonce_str(String nonce_str)
	{
		setValue("nonce_str", nonce_str);
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

	public String getOutrefundno()
	{
		return outrefundno;
	}

	public void setOutrefundno(String outrefundno)
	{
		setValue("outrefundno", outrefundno);
	}

	public Integer getRefundfee()
	{
		return refundfee;
	}

	public void setRefundfee(Integer refundfee)
	{
		setValue("refundfee", refundfee);
	}

	public Integer getTotalfee()
	{
		return totalfee;
	}

	public void setTotalfee(Integer totalfee)
	{
		setValue("totalfee", totalfee);
	}

	public Integer getCashfee()
	{
		return cashfee;
	}

	public void setCashfee(Integer cashfee)
	{
		setValue("cashfee", cashfee);
	}

	public Integer getCashrefundfee()
	{
		return cashrefundfee;
	}

	public void setCashrefundfee(Integer cashrefundfee)
	{
		setValue("cashrefundfee", cashrefundfee);
	}

	public String getReturncode()
	{
		return returncode;
	}

	public void setReturncode(String returncode)
	{
		setValue("returncode", returncode);
	}

	public String getReturnmsg()
	{
		return returnmsg;
	}

	public void setReturnmsg(String returnmsg)
	{
		setValue("returnmsg", returnmsg);
	}

	public String getResultcode()
	{
		return resultcode;
	}

	public void setResultcode(String resultcode)
	{
		setValue("resultcode", resultcode);
	}

	public String getErrcode()
	{
		return errcode;
	}

	public void setErrcode(String errcode)
	{
		setValue("errcode", errcode);
	}

	public String getErrcodedes()
	{
		return errcodedes;
	}

	public void setErrcodedes(String errcodedes)
	{
		setValue("errcodedes", errcodedes);
	}
}
