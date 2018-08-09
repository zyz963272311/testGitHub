package xyz.zyzhu.spring.boot.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： 微信路由Module</p>
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
@Entity
@Table(name = "wxrouter")
public class WXRouter extends BasModel
{
	private static final long	serialVersionUID	= 1411879407786673442L;
	@Id
	private String				id;											//主键
	private String				node;										//节点 排序用
	private String				name;										//名称 用于标记当前路由作用
	private String				matcherClassPath;							//匹配器路径
	private String				interClassPath;								//拦截器路径
	private String				handlerClassPath;							//处理器路径
	private Integer				flags;										//1:启用;2:end;default:启用，next

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
			isEnd = (flags & 2) > 0;
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
		return !isEnd();
	}

	/**
	 * 是否已经启用
	 * @return
	 * 赵玉柱
	 */
	public boolean isEnable()
	{
		boolean isEnable = true;
		if (flags != null)
		{
			isEnable = (flags & 1) == 0;
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
	public WXRouter setEnable()
	{
		if (isDisable())
		{
			int flags = StrUtil.obj2int(getFlags());
			flags = flags ^ 1;
			setFlags(flags);
		}
		return this;
	}

	/**
	 * 设置停用并返回
	 * @return
	 * 赵玉柱
	 */
	public WXRouter setDisable()
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
	 * 设置结尾为end
	 * @return
	 * 赵玉柱
	 */
	public WXRouter setEnd()
	{
		if (isNext())
		{
			int flags = StrUtil.obj2int(getFlags());
			if (flags != 0)
			{
				flags = flags ^ 2;
			}
			setFlags(flags);
		}
		return this;
	}

	/**
	 * 设置next结尾
	 * @return
	 * 赵玉柱
	 */
	public WXRouter setNext()
	{
		if (isEnd())
		{
			int flags = StrUtil.obj2int(getFlags());
			if (flags != 0)
			{
				flags = flags - 2;
			}
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
		this.id = id;
	}

	public String getNode()
	{
		return node;
	}

	public void setNode(String node)
	{
		this.node = node;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getMatcherClassPath()
	{
		return matcherClassPath;
	}

	public void setMatcherClassPath(String matcherClassPath)
	{
		this.matcherClassPath = matcherClassPath;
	}

	public String getInterClassPath()
	{
		return interClassPath;
	}

	public void setInterClassPath(String interClassPath)
	{
		this.interClassPath = interClassPath;
	}

	public String getHandlerClassPath()
	{
		return handlerClassPath;
	}

	public void setHandlerClassPath(String handlerClassPath)
	{
		this.handlerClassPath = handlerClassPath;
	}

	public Integer getFlags()
	{
		return flags;
	}

	public void setFlags(Integer flags)
	{
		this.flags = flags;
	}
}
