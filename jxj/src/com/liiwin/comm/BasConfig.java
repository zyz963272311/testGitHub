package com.liiwin.comm;

/**
 * <p>标题： 基础配置</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年5月18日 下午5:45:52</p>
 * <p>类全名：com.liiwin.comm.BasConfig</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class BasConfig
{
	private static BasConfig	basConfig;

	private BasConfig()
	{
	}

	public static BasConfig getInstance()
	{
		synchronized (BasConfig.class)
		{
			if (basConfig == null)
			{
				basConfig = new BasConfig();
			}
		}
		return basConfig;
	}
}
