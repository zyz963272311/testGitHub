package com.liiwin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * <p>标题： 字段配置</p>
 * <p>功能： </p>
 * <p>所属模块： boot</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年8月1日 上午9:31:17</p>
 * <p>类全名：xyz.zyzhu.spring.boot.annotation.FieldDef</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldDef
{
	/**数据库列名*/
	String column() default "";

	/**当前列非数据库列*/
	boolean notColumn() default false;

	/**
	 * 默认值
	 * <p>
	 * 1、普通的默认值
	 * <p>
	 * 2、AutoCode:开头，表示走编码规则定义或按照一定规则进行编码
	 * <p>
	 * 3、ExecMethod:开头，表示编码为某一个特定的java方法
	 * @return
	 * 赵玉柱
	 */
	String defaultValue() default "";

	/**
	 * 非空  x!=null
	 * @return
	 * 赵玉柱
	 */
	boolean notNull() default false;

	/**
	 * 非空 x!=null&&x.len>0
	 * <p>
	 * 包括 {@link #notNull()}
	 * @return
	 * 赵玉柱
	 */
	boolean notEmpty() default false;

	/**
	 * 非空 x!=null && x.trim.len>0
	 * <p>
	 * 包括 {@link #notEmpty()}
	 * @return
	 * 赵玉柱
	 */
	boolean notBlank() default false;

	/**
	 * 字段提示信息
	 * @return
	 * 赵玉柱
	 */
	String name() default "";

	/**
	 * 字段值在xxx之间
	 * @return
	 * 赵玉柱
	 */
	String[] valueIn() default {};

	/**
	 * 正则表达式
	 * @return
	 * 赵玉柱
	 */
	String regEx() default "";

	/**
	 * 字段值不在xxx之间
	 * @return
	 * 赵玉柱
	 */
	String[] valueNotIn() default {};
}
