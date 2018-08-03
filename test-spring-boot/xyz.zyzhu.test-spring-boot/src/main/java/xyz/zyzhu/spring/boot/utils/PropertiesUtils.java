package xyz.zyzhu.spring.boot.utils;

import org.springframework.core.env.Environment;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： 获取SpringBoot配置值</p>
 * <p>功能： </p>
 * <p>所属模块： 获取SpringBoot配置值</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年8月3日 下午3:35:15</p>
 * <p>类全名：xyz.zyzhu.spring.boot.utils.PropertiesUtils</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class PropertiesUtils
{
	public static String getPropValue(String key)
	{
		return getPropValue(key, null);
	}

	public static String getPropValue(String key, String defaultValue)
	{
		Environment env = SpringBeanUtils.getBean(Environment.class);
		return StrUtil.obj2str(env.getProperty(key), defaultValue);
	}

	public static int getPropIntValue(String key)
	{
		return getPropIntValue(key, 0);
	}

	public static int getPropIntValue(String key, int defaultValue)
	{
		Environment env = SpringBeanUtils.getBean(Environment.class);
		return StrUtil.obj2int(env.getProperty(key));
	}

	public static boolean getPropBoolValue(String key)
	{
		return getPropBoolValue(key, false);
	}

	public static boolean getPropBoolValue(String key, boolean defaultValue)
	{
		Environment env = SpringBeanUtils.getBean(Environment.class);
		return StrUtil.obj2bool(env.getProperty(key), defaultValue);
	}
}
