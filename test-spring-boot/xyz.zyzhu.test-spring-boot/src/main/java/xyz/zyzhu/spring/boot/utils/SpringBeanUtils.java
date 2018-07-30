package xyz.zyzhu.spring.boot.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： SpringBean工具类</p>
 * <p>功能： </p>
 * <p>所属模块： boot</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年7月30日 下午5:04:11</p>
 * <p>类全名：xyz.zyzhu.spring.boot.utils.SpringBeanUtils</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Component
public class SpringBeanUtils implements ApplicationContextAware
{
	private static Logger				logger	= LoggerFactory.getLogger(SpringBeanUtils.class);
	private static ApplicationContext	applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
	{
		if (SpringBeanUtils.applicationContext == null)
		{
			SpringBeanUtils.applicationContext = applicationContext;
		}
		logger.info("加载SpringBeanUtils.applicationContext");
	}

	/**
	 * 获取 ApplicationContext
	 * @return
	 * 赵玉柱
	 */
	public static ApplicationContext getApplicationContext()
	{
		return applicationContext;
	}

	/**
	 * 根据beanName获取Bean
	 * @param name
	 * @return
	 * 赵玉柱
	 */
	public static Object getBean(String name)
	{
		if (StrUtil.isStrTrimNull(name))
		{
			return null;
		}
		logger.info("根据beanName{}获取bean", name);
		return getApplicationContext().getBean(name);
	}

	/**
	 * 根据接口或实现类获取Bean
	 * @param clazz
	 * @return
	 * 赵玉柱
	 */
	public static <T> T getBean(Class<T> clazz)
	{
		if (clazz == null)
		{
			return null;
		}
		logger.info("根据beanClass{}获取bean", clazz.getName());
		return getApplicationContext().getBean(clazz);
	}

	/**
	 * 根据name和指定的class获取Bean
	 * @param name
	 * @param clazz
	 * @return
	 * 赵玉柱
	 */
	public static <T> T getBean(String name, Class<T> clazz)
	{
		if (StrUtil.isNoStrTrimNull(name) && clazz == null)
		{
			return null;
		}
		logger.info("根据beanName{}与beanClass{}获取Bean", name, clazz != null ? clazz.getName() : null);
		return getApplicationContext().getBean(name, clazz);
	}
}
