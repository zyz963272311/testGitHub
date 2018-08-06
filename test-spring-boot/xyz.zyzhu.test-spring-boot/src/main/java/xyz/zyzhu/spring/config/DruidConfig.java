package xyz.zyzhu.spring.config;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.liiwin.utils.StrUtil;

import xyz.zyzhu.spring.boot.model.LoadBalance;
import xyz.zyzhu.spring.boot.utils.LoadBalanceUtils;
import xyz.zyzhu.spring.boot.utils.PropertiesUtils;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2017年11月23日 下午10:19:55</p>
 * <p>类全名：xyz.zyzhu.spring.config.DruidConfig</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Configuration
public class DruidConfig
{
	private static Logger logger = LoggerFactory.getLogger(DruidConfig.class);
	@Value("${spring.datasource.type}")
	private Class<? extends DataSource> dataSourceType;
	private static AtomicBoolean loadedRds = new AtomicBoolean(false);
	private static  List<LoadBalance> rdsBalances = new CopyOnWriteArrayList<>();
	private static AtomicBoolean loadedWds = new AtomicBoolean(false);
	private static  List<LoadBalance> wdsBalances = new CopyOnWriteArrayList<>();

	@Bean
	public ServletRegistrationBean druidStatViewServlet()
	{
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
		registrationBean.addInitParameter("allow", "127.0.0.1");
		registrationBean.addInitParameter("deny", "192.168.0.152");
		registrationBean.addInitParameter("loginUsername", "admin");
		registrationBean.addInitParameter("loginPassword", "123456");
		registrationBean.addInitParameter("resetEnable", "false");
		return registrationBean;
	}

	@Bean
	public FilterRegistrationBean druidWebStatViewFilter()
	{
		FilterRegistrationBean registrationBean = new FilterRegistrationBean(new WebStatFilter());
		registrationBean.addInitParameter("urlPatterns", "/*");
		registrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
		return registrationBean;
	}

	//	@Bean
	//	@ConfigurationProperties(prefix = "spring.datasource")
	//	public DataSource dataSourceOne()
	//	{
	//		return DruidDataSourceBuilder.create().build();
	//	}
	//	public DataSource druidDataSource(@Value("${spring.datasource.url}") String url, @Value("${spring.datasource.driverClassName}") String driver,
	//			@Value("${spring.datasource.username}") String userName, @Value("${spring.datasource.password}") String password, @Value("${spring.datasource.maxActive}") int maxActive,
	//			@Value("${spring.datasource.filters}") String filters, @Value("${spring.datasource.initialSize}") int initialSize, @Value("${spring.datasource.minIdle}") int minIdle,
	//			@Value("${spring.datasource.maxWait}") int maxWait, @Value("${spring.datasource.timeBetweenEvictionRunsMillis}") int timeBetweenEvictionRunsMillis,
	//			@Value("${spring.datasource.minEvictableIdleTimeMillis}") int minEvictableIdleTimeMillis, @Value("${spring.datasource.validationQuery}") String validationQuery,
	//			@Value("${spring.datasource.testWhileIdle}") boolean testWhileIdle, @Value("${spring.datasource.testOnBorrow}") boolean testOnBorrow,
	//			@Value("${spring.datasource.testOnReturn}") boolean testOnReturn, @Value("${spring.datasource.poolPreparedStatements}") boolean poolPreparedStatements,
	//			@Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}") int maxPoolPreparedStatementPerConnectionSize,
	//			@Value("${spring.datasource.connectionProperties}") String connectionProperties, @Value("${spring.datasource.useGlobalDataSourceStat}") boolean useGlobalDataSourceStat)
	@Bean(name = "defaultDatasource")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource defaultDataSource()
	{
		DataSource dataSource = DataSourceBuilder.create().type(dataSourceType).build();
		return dataSource;
	}
	/**
	 * 获取一个ReadDataSource
	 * @return
	 * 赵玉柱
	 */
	@Bean(name = "readDatasource")
	public DataSource readDataSource()
	{
		String key = "spring.datasource.read";
		int readSize = PropertiesUtils.getPropIntValue(key+".size", 1);
		int options = PropertiesUtils.getPropIntValue(key+".blance", 0);
		if(!loadedRds.get()&&rdsBalances.isEmpty())
		{
			loadBalances(key, readSize, true);
		}
		LoadBalance balance = LoadBalanceUtils.balance(key, rdsBalances, options);
		DataSource dataSource = null;
		if(balance !=null)
		{
			dataSource = getDataSource(key+"."+StrUtil.obj2int(balance.getIndex()));
		}
		else
		{
			dataSource = defaultDataSource();
		}
		return dataSource;
	}
	/**
	 * 获取一个WriteDataSource
	 * @return
	 * 赵玉柱
	 */
	@Bean(name = "writeDatasource")
	public DataSource writeDataSource()
	{
		String key = "spring.datasource.write";
		int readSize = PropertiesUtils.getPropIntValue(key+".size", 1);
		int options = PropertiesUtils.getPropIntValue(key+".blance", 0);
		if(!loadedWds.get()&&wdsBalances.isEmpty())
		{
			loadBalances(key, readSize, false);
		}
		LoadBalance balance = LoadBalanceUtils.balance(key, wdsBalances, options);
		DataSource dataSource = null;
		if(balance !=null)
		{
			dataSource = getDataSource(key+"."+StrUtil.obj2int(balance.getIndex()));
		}
		else
		{
			dataSource = defaultDataSource();
		}
		return dataSource;
	}
	/**
	 * 加载负载信息
	 * @param prefix
	 * @param size
	 * @param isRead
	 * 赵玉柱
	 */
	private void loadBalances(String prefix,int size,boolean isRead)
	{
		if(isRead)
		{
			loadedRds.set(true);
		}
		else
		{
			loadedWds.set(true);
		}
		for(int i = 0;i<size;i++)
		{
			String name = prefix+PropertiesUtils.getPropValue(prefix+"."+i+".name");
			String key = prefix+PropertiesUtils.getPropValue(prefix+"."+i+".key",name);
			int flags =  PropertiesUtils.getPropIntValue(prefix+"."+i+".flags");
			int weight =  PropertiesUtils.getPropIntValue(prefix+"."+i+".weight");
			LoadBalance balance = new LoadBalance();
			balance.setKey(key);
			balance.setName(name);
			balance.setFlags(flags);
			balance.setWeight(weight);
			balance.setIndex(i);
			if(isRead)
			{
				rdsBalances.add(balance);
			}
			else
			{
				wdsBalances.add(balance);
			}
		}
	}
	/**
	 * 获取某xxx前缀的Datasources
	 * @param prefix
	 * @return
	 * 赵玉柱
	 */
	private DataSource getDataSource(String prefix)
	{
		String url = PropertiesUtils.getPropValue(prefix+".url");
		String username = PropertiesUtils.getPropValue(prefix+".username");
		String password = PropertiesUtils.getPropValue(prefix+".password");
		String driverClassName = PropertiesUtils.getPropValue(prefix+".driverClassName");
		int maxActive = PropertiesUtils.getPropIntValue(prefix+".maxActive");
		int initialSize = PropertiesUtils.getPropIntValue(prefix+".initialSize");
		int minIdle = PropertiesUtils.getPropIntValue(prefix+".minIdle");
		int maxWait = PropertiesUtils.getPropIntValue(prefix+".maxWait");
		int timeBetweenEvictionRunsMillis = PropertiesUtils.getPropIntValue(prefix+".timeBetweenEvictionRunsMillis");
		int minEvictableIdleTimeMillis = PropertiesUtils.getPropIntValue(prefix+".minEvictableIdleTimeMillis");
		String validationQuery = PropertiesUtils.getPropValue(prefix+".validationQuery");
		boolean testOnBorrow = PropertiesUtils.getPropBoolValue(prefix+".testOnBorrow");
		boolean testOnReturn = PropertiesUtils.getPropBoolValue(prefix+".testOnReturn");
		boolean testWhileIdle = PropertiesUtils.getPropBoolValue(prefix+".testWhileIdle");
		boolean poolPreparedStatements = PropertiesUtils.getPropBoolValue(prefix+".poolPreparedStatements");
		boolean removeAbandoned = PropertiesUtils.getPropBoolValue(prefix+".removeAbandoned",true);
		boolean logAbandoned = PropertiesUtils.getPropBoolValue(prefix+".logAbandoned",true);
		int removeAbandonedTimeout = PropertiesUtils.getPropIntValue(prefix+".removeAbandonedTimeout",600);
		int maxPoolPreparedStatementPerConnectionSize = PropertiesUtils.getPropIntValue(prefix+".maxPoolPreparedStatementPerConnectionSize");
		String connectionProperties = PropertiesUtils.getPropValue(prefix+".connectionProperties");
		boolean  useGlobalDataSourceStat = PropertiesUtils.getPropBoolValue(prefix+".useGlobalDataSourceStat");
		String filters = PropertiesUtils.getPropValue(prefix+".filters");
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(url);
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		/*数据源补充配置*/
		//设置超时自动关闭
		dataSource.setRemoveAbandoned(removeAbandoned);
		//设置超时自动关闭时间
		dataSource.setRemoveAbandonedTimeout(removeAbandonedTimeout);
		//设置超时自动关闭时记录Log日志
		dataSource.setLogAbandoned(logAbandoned);
		dataSource.setMaxActive(maxActive);
		dataSource.setInitialSize(initialSize);
		dataSource.setMinIdle(minIdle);
		dataSource.setMaxWait(maxWait);
		dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		dataSource.setValidationQuery(validationQuery);
		dataSource.setTestOnBorrow(testOnBorrow);
		dataSource.setTestOnReturn(testOnReturn);
		dataSource.setTestWhileIdle(testWhileIdle);
		dataSource.setPoolPreparedStatements(poolPreparedStatements);
		dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
		dataSource.setConnectionProperties(connectionProperties);
		dataSource.setUseGlobalDataSourceStat(useGlobalDataSourceStat);
		try
		{
			dataSource.setFilters(filters);
			logger.info("Druid数据源初始化设置成功......");
		} catch (Exception e)
		{
			e.printStackTrace();
			logger.info("Druid数据源filters设置失败......");
		}
		return dataSource;
	}
}
