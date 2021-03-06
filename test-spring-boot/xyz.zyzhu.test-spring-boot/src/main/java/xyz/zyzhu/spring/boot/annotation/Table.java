package xyz.zyzhu.spring.boot.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2017年11月26日 下午5:20:27</p>
 * <p>类全名：xyz.zyzhu.spring.boot.annotation.Table</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table
{
	/**表名*/
	String name() default "";

	/**使用缓存*/
	boolean useCache() default false;

	/**
	 * 缓存的sql，将其存入redis中
	 * 配置形式为field1=${field1}&field2=${field2}&field3=${field}
	 * 最后的转换形式为 getClass().getName()+cacheSql宏替换
	 * 缓存时间1天
	 */
	String cacheSql() default "";
}
