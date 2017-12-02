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
	private Logger	logger	= LoggerFactory.getLogger(DruidConfig.class);

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

	@Bean
	public DataSource druidDataSource(@Value("${spring.datasource.bas.url}") String url, @Value("${spring.datasource.bas.driverClassName}") String driver,
			@Value("${spring.datasource.bas.username}") String userName, @Value("${spring.datasource.bas.password}") String password, @Value("${spring.datasource.bas.maxActive}") int maxActive,
			@Value("${spring.datasource.bas.filters}") String filters, @Value("${spring.datasource.bas.initialSize}") int initialSize, @Value("${spring.datasource.bas.minIdle}") int minIdle,
			@Value("${spring.datasource.bas.maxWait}") int maxWait, @Value("${spring.datasource.bas.timeBetweenEvictionRunsMillis}") int timeBetweenEvictionRunsMillis,
			@Value("${spring.datasource.bas.minEvictableIdleTimeMillis}") int minEvictableIdleTimeMillis, @Value("${spring.datasource.bas.validationQuery}") String validationQuery,
			@Value("${spring.datasource.bas.testWhileIdle}") boolean testWhileIdle, @Value("${spring.datasource.bas.testOnBorrow}") boolean testOnBorrow,
			@Value("${spring.datasource.bas.testOnReturn}") boolean testOnReturn, @Value("${spring.datasource.bas.poolPreparedStatements}") boolean poolPreparedStatements,
			@Value("${spring.datasource.bas.maxPoolPreparedStatementPerConnectionSize}") int maxPoolPreparedStatementPerConnectionSize,
			@Value("${spring.datasource.bas.connectionProperties}") String connectionProperties, @Value("${spring.datasource.bas.useGlobalDataSourceStat}") boolean useGlobalDataSourceStat)
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
