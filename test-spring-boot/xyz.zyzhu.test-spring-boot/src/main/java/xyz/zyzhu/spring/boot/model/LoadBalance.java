package xyz.zyzhu.spring.boot.model;

import java.io.Serializable;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： 负载均衡对象</p>
 * <p>功能： </p>
 * <p>所属模块： boot</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年8月3日 下午4:20:24</p>
 * <p>类全名：xyz.zyzhu.spring.boot.model.LoadBalance</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class LoadBalance implements Serializable
{
	private static final long	serialVersionUID	= 1L;
	private String				key;						//键
	private String				name;						//名
	private Integer				weight;						//权重
	private Integer				flags;						//标志
	private Integer index;//下标
	/**
	 * 获取权重的int值
	 * @return
	 * 赵玉柱
	 */
	public int getIntWeight()
	{
		return StrUtil.obj2int(getWeight(), 1);
	}

	/**
	 * 是否不在线
	 * @return
	 * 赵玉柱
	 */
	public boolean isOffLine()
	{
		return flags != null && (StrUtil.obj2int(flags) & 1) == 0;
	}

	/**
	 * 是否在线
	 * @return
	 * 赵玉柱
	 */
	public boolean isOnLine()
	{
		return !isOffLine();
	}

	/**
	 * 设置在线
	 * @return
	 * 赵玉柱
	 */
	public LoadBalance setOnLine()
	{
		if (flags == null)
		{
			flags = new Integer(1);
		} else
		{
			flags = flags | 1;
		}
		return this;
	}

	/**
	 * 设置离线
	 * @return
	 * 赵玉柱
	 */
	public LoadBalance setOffLine()
	{
		if (flags == null)
		{
			flags = new Integer(0);
		} else
		{
			flags = flags | 1;
		}
		return this;
	}

	public String getKey()
	{
		return key;
	}

	public void setKey(String key)
	{
		this.key = key;
	}

	public Integer getWeight()
	{
		return weight;
	}

	public void setWeight(Integer weight)
	{
		this.weight = weight;
	}

	public Integer getFlags()
	{
		return flags;
	}

	public void setFlags(Integer flags)
	{
		this.flags = flags;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}
	
}
