package xyz.zyzhu.spring.boot.model;

import javax.persistence.Table;
import xyz.zyzhu.spring.boot.annotation.FieldDef;
/**
 * <p>标题：微信用户</p>
 * <p>功能： </p>
 * <p>所属模块： boot-wx</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月18日 下午12:19:04</p>
 * <p>类全名：xyz.zyzhu.spring.boot.model.WXUser</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Table(name = "wxusers")
public class WXUser extends BasModel
{
	private static final long	serialVersionUID	= -1383800262157308796L;
	@FieldDef
	private Integer				subscribe;
	@FieldDef
	private String				openId;
	@FieldDef
	private String				jdAlliId;									//京东联盟id
	@FieldDef
	private String				nickname;
	/**
	 * 性别描述信息：男、女、未知等.
	 */
	@FieldDef
	private String				sexDesc;
	/**
	 * 性别表示：1，2等数字.
	 */
	@FieldDef
	private Integer				sex;
	@FieldDef
	private String				language;
	@FieldDef
	private String				city;
	@FieldDef
	private String				province;
	@FieldDef
	private String				country;
	@FieldDef
	private String				headImgUrl;
	@FieldDef
	private Long				subscribeTime;
	/**
	 * https://mp.weixin.qq.com/cgi-bin/announce?action=getannouncement&announce_id=11513156443eZYea&version=&lang=zh_CN
	 * <pre>
	 * 只有在将公众号绑定到微信开放平台帐号后，才会出现该字段。
	 * 另外，在用户未关注公众号时，将不返回用户unionID信息。
	 * 已关注的用户，开发者可使用“获取用户基本信息接口”获取unionID；
	 * 未关注用户，开发者可使用“微信授权登录接口”并将scope参数设置为snsapi_userinfo，获取用户unionID
	 * </pre>
	 */
	@FieldDef
	private String				unionId;
	@FieldDef
	private String				remark;
	@FieldDef
	private Integer				groupId;
	@FieldDef
	private String				tagIds;
	/**
	 * 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）.
	 */
	@FieldDef
	private String				privileges;
	/**
	 * subscribe_scene 返回用户关注的渠道来源.
	 * ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE 扫描二维码，ADD_SCENEPROFILE LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，ADD_SCENE_PAID 支付后关注，ADD_SCENE_OTHERS 其他
	 */
	@FieldDef
	private String				subscribeScene;
	/**
	 * qr_scene 二维码扫码场景（开发者自定义）.
	 */
	@FieldDef
	private String				qrScene;
	/**
	 * qr_scene_str 二维码扫码场景描述（开发者自定义）.
	 */
	@FieldDef
	private String				qrSceneStr;

	public Integer getSubscribe()
	{
		return subscribe;
	}

	public void setSubscribe(Integer subscribe)
	{
		setValue("subscribe", subscribe);
	}

	public void setTagIds(String tagIds)
	{
		setValue("tagIds", tagIds);
	}

	public void setPrivileges(String privileges)
	{
		setValue("privileges", privileges);
	}

	public String getOpenId()
	{
		return openId;
	}

	public void setOpenId(String openId)
	{
		setValue("openId", openId);
	}

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String nickname)
	{
		setValue("nickname", nickname);
	}

	public String getSexDesc()
	{
		return sexDesc;
	}

	public void setSexDesc(String sexDesc)
	{
		setValue("sexDesc", sexDesc);
	}

	public Integer getSex()
	{
		return sex;
	}

	public void setSex(Integer sex)
	{
		setValue("sex", sex);
	}

	public String getLanguage()
	{
		return language;
	}

	public void setLanguage(String language)
	{
		setValue("language", language);
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		setValue("city", city);
	}

	public String getProvince()
	{
		return province;
	}

	public void setProvince(String province)
	{
		setValue("province", province);
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		setValue("country", country);
	}

	public String getHeadImgUrl()
	{
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl)
	{
		setValue("headImgUrl", headImgUrl);
	}

	public Long getSubscribeTime()
	{
		return subscribeTime;
	}

	public void setSubscribeTime(Long subscribeTime)
	{
		setValue("subscribeTime", subscribeTime);
	}

	public String getUnionId()
	{
		return unionId;
	}

	public void setUnionId(String unionId)
	{
		setValue("unionId", unionId);
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		setValue("remark", remark);
	}

	public Integer getGroupId()
	{
		return groupId;
	}

	public void setGroupId(Integer groupId)
	{
		setValue("groupId", groupId);
	}

	public String getSubscribeScene()
	{
		return subscribeScene;
	}

	public void setSubscribeScene(String subscribeScene)
	{
		setValue("subscribeScene", subscribeScene);
	}

	public String getQrScene()
	{
		return qrScene;
	}

	public void setQrScene(String qrScene)
	{
		setValue("qrScene", qrScene);
	}

	public String getQrSceneStr()
	{
		return qrSceneStr;
	}

	public void setQrSceneStr(String qrSceneStr)
	{
		setValue("qrSceneStr", qrSceneStr);
	}

	public String getTagIds()
	{
		return tagIds;
	}

	public String getPrivileges()
	{
		return privileges;
	}

	public String getJdAlliId()
	{
		return jdAlliId;
	}

	public void setJdAlliId(String jdAlliId)
	{
		setValue("jdAlliId", jdAlliId);
	}
}
