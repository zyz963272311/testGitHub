package com.liiwin.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： 获取config中的配置</p>
 * <p>功能： </p>
 * <p>所属模块： 获取config中的配置</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2017年8月11日 上午10:26:21</p>
 * <p>类全名：com.liiwin.config.BasConfig</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class BasConfig
{
	private static Properties	properties;

	/**
	 * 加载配置文件 userdir + "/resources/config.properties"
	 * 
	 * 赵玉柱
	 */
	public static void LoadConfig()
	{
		String userdir = System.getProperty("user.dir");
		System.out.println(userdir);
		String configFilePath = userdir + "/resources/config.properties";
		properties = new Properties();
		File file = new File(configFilePath);
		if (file.isFile() && file.exists())
		{
			try
			{
				FileInputStream fileInputStream = new FileInputStream(file);
				properties.load(fileInputStream);
			} catch (IOException e)
			{
				throw new RuntimeException("报错内容", e);
			}
		}
	}

	/**
	 * 获取配置文件中的某一个值
	 * @param key
	 * @return
	 * 赵玉柱
	 */
	public static String getPropertie(String key)
	{
		if (StrUtil.isStrTrimNull(key))
		{
			return null;
		}
		if (properties == null)
		{
			LoadConfig();
		}
		return properties.getProperty(key);
	}

	/**
	 * 获取配置文件中的所有值
	 * @return
	 * 赵玉柱
	 */
	public static Properties getProperties()
	{
		if (properties == null)
		{
			LoadConfig();
		}
		return properties;
	}

	/**
	 * 测试方法
	 * @param args
	 * 赵玉柱
	 */
	public static void main(String[] args)
	{
		LoadConfig();
	}
}