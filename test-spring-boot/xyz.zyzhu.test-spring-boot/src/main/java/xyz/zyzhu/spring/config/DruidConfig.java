package xyz.zyzhu.spring.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
	@Value("${mysql.datasource.type}")
	private Class<? extends DataSource> dataSourceType;

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
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource defaultDataSource(String i)
	{
		DataSource dataSource = DataSourceBuilder.create().type(dataSourceType).build();
		return dataSource;
	}

	@Bean(name = "readDatasource")
	@ConfigurationProperties(prefix = "spring.datasource.read")
	public DataSource readDataSource()
	{
		DataSource dataSource = DataSourceBuilder.create().type(dataSourceType).build();
		return dataSource;
	}

	@Bean(name = "writeDatasource")
	@ConfigurationProperties(prefix = "spring.datasource.write")
	public DataSource writeDataSource()
	{
		DataSource dataSource = DataSourceBuilder.create().type(dataSourceType).build();
		return dataSource;
	}
}
