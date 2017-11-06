package com.liiwin.wechat;

/**
 * <p>标题： 微信支付参数</p>
 * <p>功能： </p>
 * <p>所属模块： rootbas</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2017年10月11日 上午10:45:27</p>
 * <p>类全名：com.liiwin.wechat.WeChatPayParams</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class WeChatPayParams
{
	private String	appid;		//公众号ID	String(16)
	private String	timeStamp;	//时间戳 String(32)
	private String	nonceStr;	//随即字符串 String(32)
	private String	pkg;		//package 订单详情扩展字符串 String(128)
	private String	signType;	//签名方式 String(32) 目前仅支持MD5
	private String	paySign;	//签名 String(32) 

	public String getAppid()
	{
		return appid;
	}

	public void setAppid(String appid)
	{
		this.appid = appid;
	}

	public String getTimeStamp()
	{
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp)
	{
		this.timeStamp = timeStamp;
	}

	public String getNonceStr()
	{
		return nonceStr;
	}

	public void setNonceStr(String nonceStr)
	{
		this.nonceStr = nonceStr;
	}

	public String getPkg()
	{
		return pkg;
	}

	public void setPkg(String pkg)
	{
		this.pkg = pkg;
	}

	public String getSignType()
	{
		return signType;
	}

	public void setSignType(String signType)
	{
		this.signType = signType;
	}

	public String getPaySign()
	{
		return paySign;
	}

	public void setPaySign(String paySign)
	{
		this.paySign = paySign;
	}
}
