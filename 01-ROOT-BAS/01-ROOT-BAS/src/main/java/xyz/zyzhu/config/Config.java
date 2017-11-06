package xyz.zyzhu.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



/**
 * <p>标题： Config</p>
 * <p>内容： Config</p>
 * <p>创建时间： 2017年4月24日</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>类全名： xyz.zyzhu.config.Config</p>
 * <p>作者： Administrator</p>
 */
public class Config extends BasConfig
{

	public static Properties getConfig(String prepertiesName) throws IOException
	{
		Properties properties = new Properties();
		InputStream inputStream = new FileInputStream(prepertiesName);
		properties.load(inputStream);
		return properties;
	}

	public static String getConfigValue(Properties preProperties, String key)
	{
		return preProperties.getProperty(key);
	}
}
