package com.liiwin.config;

import java.util.ArrayList;
import java.util.List;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： 配置信息</p>
 * <p>功能： </p>
 * <p>所属模块： rootbas</p>
 * <p>版权： Copyright © 2018 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2018年7月30日 下午2:53:05</p>
 * <p>类全名：com.liiwin.config.DataConfig</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class DataConfig
{
	/**
	 * 获取远程更新地址
	 * @return
	 * 赵玉柱
	 */
	public static String getRemotePathsConfig()
	{
		return BasConfig.getPropertie("REMOTE.PATHS");
	}

	/**
	 * 获取远程更新地址
	 * @return
	 * 赵玉柱
	 */
	public static List<String> getRemotePathList()
	{
		String remotePathsConfig = getRemotePathsConfig();
		if (StrUtil.isNoStrTrimNull(remotePathsConfig))
		{
			String[] pathArray = StrUtil.split(remotePathsConfig, ';');
			List<String> pathList = new ArrayList<>();
			for (int i = 0; i < pathArray.length; i++)
			{
				String path = pathArray[i];
				if (StrUtil.isNoStrTrimNull(path))
				{
					pathList.add(path);
				}
			}
			return pathList;
		}
		return null;
	}

	/**
	 * 获取远程测试方法
	 * @return
	 * 赵玉柱
	 */
	public static String getTestPath()
	{
		return BasConfig.getPropertie("TEST.METHOD");
	}

	/**
	 * 当前环境是否是开发/debug环境
	 * @return
	 * 赵玉柱
	 */
	public static boolean isTest()
	{
		return StrUtil.obj2bool(BasConfig.getPropertie("debug"));
	}
}
