package xyz.zyzhu.spring.config;

import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
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
	private Logger logger = LoggerFactory.getLogger(DruidConfig.class);

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
	@Bean
	public DataSource druidDataSource(@Value("${spring.datasource.url}") String url, @Value("${spring.datasource.driverClassName}") String driver,
			@Value("${spring.datasource.username}") String userName, @Value("${spring.datasource.password}") String password, @Value("${spring.datasource.maxActive}") int maxActive,
			@Value("${spring.datasource.filters}") String filters, @Value("${spring.datasource.initialSize}") int initialSize, @Value("${spring.datasource.minIdle}") int minIdle,
			@Value("${spring.datasource.maxWait}") int maxWait, @Value("${spring.datasource.timeBetweenEvictionRunsMillis}") int timeBetweenEvictionRunsMillis,
			@Value("${spring.datasource.minEvictableIdleTimeMillis}") int minEvictableIdleTimeMillis, @Value("${spring.datasource.validationQuery}") String validationQuery,
			@Value("${spring.datasource.testWhileIdle}") boolean testWhileIdle, @Value("${spring.datasource.testOnBorrow}") boolean testOnBorrow,
			@Value("${spring.datasource.testOnReturn}") boolean testOnReturn, @Value("${spring.datasource.poolPreparedStatements}") boolean poolPreparedStatements,
			@Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}") int maxPoolPreparedStatementPerConnectionSize,
			@Value("${spring.datasource.connectionProperties}") String connectionProperties, @Value("${spring.datasource.useGlobalDataSourceStat}") boolean useGlobalDataSourceStat)
	{
		DruidDataSource dataSource = new DruidDataSource();
		/*数据源主要配置*/
		dataSource.setUrl(url);
		dataSource.setDriverClassName(driver);
		dataSource.setUsername(userName);
		dataSource.setPassword(password);
		/*数据源补充配置*/
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
