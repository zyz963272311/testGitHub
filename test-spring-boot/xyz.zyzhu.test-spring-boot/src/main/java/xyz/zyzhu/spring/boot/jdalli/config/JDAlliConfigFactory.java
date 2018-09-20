package xyz.zyzhu.spring.boot.jdalli.config;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import com.liiwin.utils.StrUtil;
import xyz.zyzhu.spring.boot.utils.PropertiesUtils;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月20日 下午8:36:45</p>
 * <p>类全名：xyz.zyzhu.spring.boot.jdalli.config.JDAlliConfigFactory</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class JDAlliConfigFactory
{
	private static JDAlliConfigFactory	factory;
	private static Lock					factoryLock	= new ReentrantLock();
	private JDAlliConfig				config;
	private Lock						configLock	= new ReentrantLock();

	protected JDAlliConfigFactory()
	{
	}

	public static JDAlliConfigFactory factory()
	{
		try
		{
			factoryLock.lock();
			if (factory == null)
			{
				factory = new JDAlliConfigFactory();
			}
		} finally
		{
			factoryLock.unlock();
		}
		return factory;
	}

	public JDAlliConfig config()
	{
		try
		{
			configLock.lock();
			if (config == null)
			{
				config = new JDAlliConfig();
				String app_key = PropertiesUtils.getPropValue("JDAlli_app_key");
				if (StrUtil.isStrTrimNull(app_key))
				{
					throw new RuntimeException("请配置参数[JDAlli_app_key]");
				}
				String format = PropertiesUtils.getPropValue("JDAlli_format", "json");
				String v = PropertiesUtils.getPropValue("JDAlli_v");
				config.setApp_key(app_key);
				config.setFormat(format);
				config.setV(v);
			}
		} finally
		{
			configLock.unlock();
		}
		return config;
	}

	class JDAlliConfig
	{
		private String	app_key;
		private String	format;
		private String	v;

		public String getApp_key()
		{
			return app_key;
		}

		protected void setApp_key(String app_key)
		{
			//setValue("app_key", app_key);
			this.app_key = app_key;
		}

		public String getV()
		{
			return v;
		}

		protected void setV(String v)
		{
			//setValue("v", v);
			this.v = v;
		}

		public String getFormat()
		{
			return format;
		}

		protected void setFormat(String format)
		{
			//setValue("format", format);
			this.format = format;
		}
	}
}
