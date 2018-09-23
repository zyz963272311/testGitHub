package xyz.zyzhu.spring.boot.model;

import javax.persistence.Table;
import xyz.zyzhu.spring.boot.annotation.FieldDef;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月23日 下午9:11:15</p>
 * <p>类全名：xyz.zyzhu.spring.boot.model.WxMenuButtonModel</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Table(name = "wxmenubutton")
public class WxMenuButtonModel extends BasModel
{
	private static final long	serialVersionUID	= -4672373359773625254L;
	/**
	 * 菜单的主键值
	 */
	private String				id;
	/**
	 * 菜单的节点
	 */
	private String				node;
	/**
	  * <pre>
	  * 菜单的响应动作类型.
	  * view表示网页类型，
	  * click表示点击类型，
	  * miniprogram表示小程序类型
	  * </pre>
	  */
	@FieldDef
	private String				type;
	/**
	 * 菜单标题，不超过16个字节，子菜单不超过60个字节.
	 */
	private String				name;
	/**
	 * <pre>
	 * 菜单KEY值，用于消息接口推送，不超过128字节.
	 * click等点击类型必须
	 * </pre>
	 */
	private String				key;
	/**
	 * <pre>
	 * 网页链接.
	 * 用户点击菜单可打开链接，不超过1024字节。type为miniprogram时，不支持小程序的老版本客户端将打开本url。
	 * view、miniprogram类型必须
	 * </pre>
	 */
	private String				url;
	/**
	 * <pre>
	 * 调用新增永久素材接口返回的合法media_id.
	 * media_id类型和view_limited类型必须
	 * </pre>
	 */
	private String				mediaId;
	/**
	 * <pre>
	 * 小程序的appid.
	 * miniprogram类型必须
	 * </pre>
	 */
	private String				appId;
	/**
	 * <pre>
	 * 小程序的页面路径.
	 * miniprogram类型必须
	 * </pre>
	 */
	private String				pagePath;

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		setValue("type", type);
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		setValue("name", name);
	}

	public String getKey()
	{
		return key;
	}

	public void setKey(String key)
	{
		setValue("key", key);
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		setValue("url", url);
	}

	public String getMediaId()
	{
		return mediaId;
	}

	public void setMediaId(String mediaId)
	{
		setValue("mediaId", mediaId);
	}

	public String getAppId()
	{
		return appId;
	}

	public void setAppId(String appId)
	{
		setValue("appId", appId);
	}

	public String getPagePath()
	{
		return pagePath;
	}

	public void setPagePath(String pagePath)
	{
		setValue("pagePath", pagePath);
	}
}
