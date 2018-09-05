package xyz.zyzhu.spring.boot.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.activemq.util.ByteArrayInputStream;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.liiwin.config.BasConfig;
import com.liiwin.db.Database;
import com.liiwin.db.pool.DatabasePoolManager;
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
	private static AtomicBoolean	zkLoaded	= new AtomicBoolean(false);
	private static Logger			logger		= LoggerFactory.getLogger(PropertiesUtils.class);
	private static Properties		properties;

	/**
	 * 加载ZK配置
	 * @param isForceLoad
	 * 赵玉柱
	 */
	public static void loadZKProperties(boolean isForceLoad)
	{
		if ((properties == null && !zkLoaded.get()) || isForceLoad)
		{
			properties = new Properties();
			Properties localProperties = loadLoaclProperties();
			for (Entry<Object,Object> sysEntry : localProperties.entrySet())
			{
				Object key = sysEntry.getKey();
				Object value = sysEntry.getValue();
				properties.put(key, value);
			}
			forceLoadZKProperties();
			loadDBProperties(localProperties);
			loadSystemProperties(localProperties);
			for (Entry<Object,Object> sysEntry : localProperties.entrySet())
			{
				Object key = sysEntry.getKey();
				Object value = sysEntry.getValue();
				properties.put(key, value);
			}
		}
	}

	/**
	 * 将本地配置装载到ZK
	 * 仅仅包括系统配置和数据库配置
	 * 
	 * 赵玉柱
	 */
	public static void putLocalPropertiesToZK()
	{
		synchronized (PropertiesUtils.class)
		{
			Properties loadLoaclProperties = loadLoaclProperties();
			loadDBProperties(loadLoaclProperties);
			String sysPropPath = loadLoaclProperties.getProperty("SystemPropertyesPath", "D://MyProject/OnGithub/system.properties");
			loadLoaclProperties.clear();
			loadLoaclProperties.put("SystemPropertyesPath", sysPropPath);
			loadSystemProperties(loadLoaclProperties);
			loadDBProperties(loadLoaclProperties);
			loadLoaclProperties.remove("SystemPropertyesPath");
			Watcher watcher = new Watcher()
			{
				@Override
				public void process(WatchedEvent arg0)
				{
					logger.info("WatchedEvent={}", arg0);
				}
			};
			ByteArrayOutputStream bio = new ByteArrayOutputStream();
			ZooKeeper zk = null;
			try
			{
				zk = new ZooKeeper(getPropValue("zooKeeperUrl", "127.0.0.1:2181"), 9999, watcher);
				loadLoaclProperties.store(bio, "备份");
				try
				{
					zk.setData("/properties", bio.toByteArray(), 1);
				} catch (Exception e)
				{
					zk.create("/properties", bio.toByteArray(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
				}
			} catch (Exception e)
			{
				logger.info("写入本地配置到ZK失败：" + e);
			} finally
			{
				if (zk != null)
				{
					try
					{
						zk.close();
					} catch (InterruptedException e)
					{
						logger.info("关闭ZK失败{}", e);
					}
				}
				if (bio != null)
				{
					try
					{
						bio.close();
					} catch (IOException e)
					{
						logger.info("关闭流失败{}", e);
					}
				}
			}
		}
	}

	/**
	 * 加载本地properties配置
	 * 
	 * 赵玉柱
	 */
	private static Properties loadLoaclProperties()
	{
		String localPropertiePath = "./resources/application.properties";
		Properties localProperties = new Properties();
		FileInputStream fis = null;
		try
		{
			fis = new FileInputStream(new File(localPropertiePath));
			if (fis != null)
			{
				localProperties.load(fis);
			}
		} catch (Exception e)
		{
			logger.info("加载本地配置{}失败，失败原因{}", localPropertiePath, e);
			localPropertiePath = "./resources/config.properties";
			try
			{
				fis = new FileInputStream(new File(localPropertiePath));
				if (fis != null)
				{
					localProperties.load(fis);
				}
			} catch (Exception e1)
			{
				logger.info("加载本地配置{}失败，失败原因{}", localPropertiePath, e);
				localPropertiePath = "./config.properties";
				try
				{
					fis = new FileInputStream(new File(localPropertiePath));
				} catch (Exception e2)
				{
					logger.info("加载本地配置{}失败,失败原因{}", localPropertiePath, e);
				}
			}
		} finally
		{
			if (fis != null)
			{
				try
				{
					fis.close();
				} catch (IOException e)
				{
					logger.info("关闭流失败{}", e);
					throw new RuntimeException("报错内容", e);
				}
			}
		}
		return localProperties;
	}

	/**
	 * 加载系统配置
	 * @param localProperties
	 * 赵玉柱
	 */
	private static void loadSystemProperties(Properties localProperties)
	{
		String systemPropertiesPath = StrUtil.obj2str(localProperties.getProperty("SystemPropertyesPath", "D://MyProject/OnGithub/system.properties"));
		Properties ststemPropertyes = new Properties();
		FileInputStream fis = null;
		try
		{
			fis = new FileInputStream(new File(systemPropertiesPath));
			if (fis != null)
			{
				ststemPropertyes.load(fis);
			}
		} catch (Exception e)
		{
			logger.info("获取系统配置{}失败,失败原因{}", systemPropertiesPath, e);
		}
		for (Entry<Object,Object> sysEntry : ststemPropertyes.entrySet())
		{
			Object key = sysEntry.getKey();
			String value = StrUtil.obj2str(sysEntry.getValue());
			localProperties.put(sysEntry.getKey(), StrUtil.obj2str(localProperties.get(key), value));
		}
	}

	/**
	 * 加载数据库中的配置
	 * @param properties
	 * 赵玉柱
	 */
	private static void loadDBProperties(Properties properties)
	{
		Database db = null;
		try
		{
			db = DatabasePoolManager.getNewInstance().getConfigDatabase();
			String sql = "select * from properties ";
			List<Map<String,Object>> propertiesFromDB = db.getListMapFromSql(sql);
			if (!propertiesFromDB.isEmpty())
			{
				for (Map<String,Object> propertiesMap : propertiesFromDB)
				{
					String key = StrUtil.obj2str(propertiesMap.get("pkey"));
					String value = StrUtil.obj2str(propertiesMap.get("pvalue"));
					if (!StrUtil.isNullIn(key, value))
					{
						properties.put(key, value);
					}
				}
			}
		} catch (Exception e)
		{
			logger.info("查询数据库配置失败{}", e);
		} finally
		{
			if (db != null)
			{
				DatabasePoolManager.getNewInstance().close(db);
			}
		}
	}

	/**
	 * 强制加载ZK配置
	 * @param key
	 * 赵玉柱
	 */
	private static void forceLoadZKProperties()
	{
		synchronized (PropertiesUtils.class)
		{
			zkLoaded.set(true);
			Watcher watcher = new Watcher()
			{
				@Override
				public void process(WatchedEvent arg0)
				{
					logger.info("WatchedEvent={}", arg0);
				}
			};
			ZooKeeper zk = null;
			try
			{
				String ZKUrl = StrUtil.obj2str(BasConfig.getPropertie("zooKeeperUrl"), "127.0.0.1:2181");
				zk = new ZooKeeper(ZKUrl, 9999, watcher);
				byte[] data = zk.getData("/properties", watcher, null);
				if (data != null)
				{
					properties.load(new ByteArrayInputStream(data));
				}
			} catch (Exception e)
			{
				logger.info("加载ZK配置失败{}", e);
			} finally
			{
				if (zk != null)
				{
					try
					{
						zk.close();
					} catch (InterruptedException e)
					{
						logger.info("关闭ZK客户端失败{}", e);
						throw new RuntimeException("报错内容", e);
					}
				}
			}
		}
		//如果获取失败，则重新将数据加载到Config配置
		if (properties.isEmpty())
		{
			putLocalPropertiesToZK();
		}
	}

	public static String getPropValue(String key)
	{
		return getPropValue(key, null);
	}

	public static String getPropValue(String key, String defaultValue)
	{
		loadZKProperties(false);
		return StrUtil.obj2str(properties.getProperty(key), defaultValue);
	}

	public static int getPropIntValue(String key)
	{
		return getPropIntValue(key, 0);
	}

	public static int getPropIntValue(String key, int defaultValue)
	{
		return StrUtil.obj2int(getPropValue(key), defaultValue);
	}

	public static boolean getPropBoolValue(String key)
	{
		return getPropBoolValue(key, false);
	}

	public static boolean getPropBoolValue(String key, boolean defaultValue)
	{
		return StrUtil.obj2bool(getPropValue(key), defaultValue);
	}
}
