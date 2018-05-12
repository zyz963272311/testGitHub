package com.liiwin.config;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import com.liiwin.db.Database;
import com.liiwin.db.pool.DatabasePoolManager;
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
	protected static Properties properties;

	public static void LoadConfig()
	{
		LoadConfig(null);
	}

	/**
	 * 加载配置文件 userdir + "/resources/config.properties"
	 * 
	 * 赵玉柱
	 */
	synchronized public static void LoadConfig(String dbName)
	{
		if (properties == null)
		{
			if (StrUtil.isStrTrimNull(dbName))
			{
				dbName = "zyztest";
			}
			properties = new Properties();
			String configFilePath1 = "/resources/config.properties";
			try
			{
				properties.load(BasConfig.class.getResourceAsStream(configFilePath1));
			} catch (IOException e)
			{
				String configFilePath2 = "/config.properties";
				try
				{
					properties.load(BasConfig.class.getResourceAsStream(configFilePath2));
				} catch (IOException e1)
				{
					throw new RuntimeException("报错内容", e1);
				}
			}
			//获取db中的config配置信息
			List<Map<String,Object>> dbConfigList = getDBConfig("zyztest");
			//将db中的配置插入到config中
			rebuildProperties(properties, dbConfigList);
		}
	}

	/**
	 * config配置重新装载
	 * 
	 * 赵玉柱
	 */
	public static void reLoadConfig()
	{
		reLoadConfig(null);
	}

	/**
	 * config配置重新装载
	 * @param dbName
	 * 赵玉柱
	 */
	public static void reLoadConfig(String dbName)
	{
		properties = null;
		LoadConfig(dbName);
	}

	/**
	 * 将db中的config插入到properties中
	 * @param properties
	 * @param dbConfigList
	 * 赵玉柱
	 */
	private static void rebuildProperties(Properties properties, List<Map<String,Object>> dbConfigList)
	{
		for (Map<String,Object> dbConfig : dbConfigList)
		{
			boolean useflags = StrUtil.obj2bool(dbConfig.get("useflags"));
			if (useflags)
			{
				String key = StrUtil.obj2str(dbConfig.get("key"));
				if (StrUtil.isNoStrTrimNull(key))
				{
					String value = StrUtil.obj2str(dbConfig.get("value"));
					properties.put(key, value);
				}
			}
		}
	}

	/**
	 * 获取DB中的config信息
	 * @return
	 * 赵玉柱
	 */
	public static List<Map<String,Object>> getDBConfig(String dbName)
	{
		DatabasePoolManager poolManager = DatabasePoolManager.getNewInstance();
		Database database = poolManager.getDatabase(dbName);
		String sql = "select * from dbconfig";
		return database.getListMapFromSql(sql, null);
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

	protected static String getConfigFilePath()
	{
		return "/resources/config.properties";
	}
}
