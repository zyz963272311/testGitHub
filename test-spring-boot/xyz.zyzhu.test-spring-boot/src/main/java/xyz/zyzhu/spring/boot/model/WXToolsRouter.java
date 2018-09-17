package xyz.zyzhu.spring.boot.model;

import javax.persistence.Table;
import com.liiwin.utils.StrUtil;
import xyz.zyzhu.spring.boot.annotation.FieldDef;
/**
 * <p>标题： 微信路由Module for wx-tools-java</p>
 * <p>功能： </p>
 * <p>所属模块： 微信</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年7月30日 下午2:34:45</p>
 * <p>类全名：xyz.zyzhu.spring.boot.wx.module.WXRouterModule</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
//@Entity
@Table(name = "wxtoolsrouter")
public class WXToolsRouter extends BasModel
{
	private static final long	serialVersionUID	= 1411879407786673442L;
	@FieldDef(defaultValue = "AutoCode:id________")
	private String				id;											//主键
	@FieldDef()
	private String				node;										//节点 排序用
	@FieldDef()
	private String				name;										//名称 用于标记当前路由作用
	@FieldDef()
	private String				fromUser;									//用户来源
	@FieldDef()
	private String				msgType;									//消息类型
	@FieldDef()
	private String				event;										//事件
	@FieldDef()
	private String				eventKey;									//事件key
	@FieldDef()
	private String				eventKeyRegex;								//事件key通配
	@FieldDef()
	private String				content;									//内容
	@FieldDef()
	private String				rcontent;									//内容
	@FieldDef()
	private String				matcherClassPath;							//匹配器路径
	@FieldDef()
	private String				interClassPath;								//拦截器路径
	@FieldDef()
	private String				handlerClassPath;							//处理器路径
	@FieldDef(defaultValue = "0")
	private Integer				flags;										//1:启用;2:next;4：end,8:sync

	/**
	 * 判断当前对象是否是课可用的对象
	 * @return
	 * 赵玉柱
	 */
	public boolean isAvailable()
	{
		String fromUser2 = getFromUser();
		String msgType2 = getMsgType();
		String event2 = getEvent();
		String eventKey2 = getEventKey();
		String eventKeyRegex2 = getEventKeyRegex();
		String content2 = getContent();
		String rcontent2 = getRcontent();
		String matcherClassPath2 = getMatcherClassPath();
		String interClassPath2 = getInterClassPath();
		String handlerClassPath2 = getHandlerClassPath();
		boolean allNullIn = StrUtil.isAllNullIn(true, fromUser2, msgType2, event2, eventKey2, eventKeyRegex2, content2, rcontent2, matcherClassPath2, interClassPath2, handlerClassPath2);
		return !allNullIn;
	}

	public boolean isAsync()
	{
		boolean isAsync = false;
		if (flags != null)
		{
			isAsync = (isEnable()) && ((flags & 8) > 0);
		}
		return isAsync;
	}

	/**
	 * 后缀加End
	 * @return
	 * 赵玉柱
	 */
	public boolean isEnd()
	{
		boolean isEnd = false;
		if (flags != null)
		{
			isEnd = (isEnable()) && ((flags & 4) > 0);
		}
		return isEnd;
	}

	/**
	 * 后缀加next
	 * @return
	 * 赵玉柱
	 */
	public boolean isNext()
	{
		boolean isNext = false;
		if (flags != null)
		{
			isNext = (isEnable()) && ((flags & 2) > 0);
		}
		return isNext;
	}

	/**
	 * 是否已经启用
	 * @return
	 * 赵玉柱
	 */
	public boolean isEnable()
	{
		boolean isEnable = false;
		if (flags != null)
		{
			isEnable = (flags & 1) != 0;
		}
		return isEnable;
	}

	/**
	 * 是否已经停用
	 * @return
	 * 赵玉柱
	 */
	public boolean isDisable()
	{
		return !isEnable();
	}

	/**
	 * 设置启用并返回
	 * @return
	 * 赵玉柱
	 */
	public WXToolsRouter setEnable()
	{
		if (isDisable())
		{
			int flags = StrUtil.obj2int(getFlags());
			flags = flags | 1;
			setFlags(flags);
		}
		return this;
	}

	public WXToolsRouter setAsync_()
	{
		if (isAsync())
		{
			int flags = StrUtil.obj2int(getFlags());
			flags = flags - 8;
			setFlags(flags);
		}
		return this;
	}

	public WXToolsRouter setAsync()
	{
		if (!isAsync())
		{
			int flags = StrUtil.obj2int(getFlags());
			flags = flags | 8;
			setFlags(flags);
		}
		return this;
	}

	/**
	 * 设置停用并返回
	 * @return
	 * 赵玉柱
	 */
	public WXToolsRouter setDisable()
	{
		if (isEnable())
		{
			int flags = StrUtil.obj2int(getFlags());
			if (flags != 0)
			{
				flags = flags - 1;
			}
			setFlags(flags);
		}
		return this;
	}

	/**
	 * 取消结尾
	 * @return
	 * 赵玉柱
	 */
	public WXToolsRouter setEnd_()
	{
		if (isEnd())
		{
			int flags = StrUtil.obj2int(getFlags());
			flags = flags ^ 4;
			setFlags(flags);
		}
		return this;
	}

	/**
	 * 设置结尾为end
	 * @return
	 * 赵玉柱
	 */
	public WXToolsRouter setEnd()
	{
		if (!isEnd())
		{
			int flags = StrUtil.obj2int(getFlags());
			flags = flags | 4;
			setFlags(flags);
		}
		return this;
	}

	/**
	 * 取消过程
	 * @return
	 * 赵玉柱
	 */
	public WXToolsRouter setNext_()
	{
		if (isNext())
		{
			int flags = StrUtil.obj2int(getFlags());
			flags = flags - 2;
			setFlags(flags);
		}
		return this;
	}

	/**
	 * 设置next结尾
	 * @return
	 * 赵玉柱
	 */
	public WXToolsRouter setNext()
	{
		if (!isNext())
		{
			int flags = StrUtil.obj2int(getFlags());
			flags = flags | 2;
			setFlags(flags);
		}
		return this;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		setValue("id", id);
	}

	public String getNode()
	{
		return node;
	}

	public void setNode(String node)
	{
		setValue("node", node);
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		setValue("name", name);
	}

	public String getMatcherClassPath()
	{
		return matcherClassPath;
	}

	public void setMatcherClassPath(String matcherClassPath)
	{
		setValue("matcherClassPath", matcherClassPath);
	}

	public String getInterClassPath()
	{
		return interClassPath;
	}

	public void setInterClassPath(String interClassPath)
	{
		setValue("interClassPath", interClassPath);
	}

	public String getHandlerClassPath()
	{
		return handlerClassPath;
	}

	public void setHandlerClassPath(String handlerClassPath)
	{
		setValue("handlerClassPath", handlerClassPath);
	}

	public Integer getFlags()
	{
		return flags;
	}

	public void setFlags(Integer flags)
	{
		setValue("flags", flags);
	}

	public String getFromUser()
	{
		return fromUser;
	}

	public void setFromUser(String fromUser)
	{
		setValue("fromUser", fromUser);
	}

	public String getMsgType()
	{
		return msgType;
	}

	public void setMsgType(String msgType)
	{
		setValue("msgType", msgType);
	}

	public String getEvent()
	{
		return event;
	}

	public void setEvent(String event)
	{
		setValue("event", event);
	}

	public String getEventKey()
	{
		return eventKey;
	}

	public void setEventKey(String eventKey)
	{
		setValue("eventKey", eventKey);
	}

	public String getEventKeyRegex()
	{
		return eventKeyRegex;
	}

	public void setEventKeyRegex(String eventKeyRegex)
	{
		setValue("eventKeyRegex", eventKeyRegex);
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		setValue("content", content);
	}

	public String getRcontent()
	{
		return rcontent;
	}

	public void setRcontent(String rcontent)
	{
		setValue("rcontent", rcontent);
	}
}
