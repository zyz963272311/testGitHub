package annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年4月15日 下午10:17:17</p>
 * <p>类全名：annotation.Test3</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Test3
{
	/**
	 * 字段名称
	 * @return
	 * 赵玉柱
	 */
	public String[] fields() default { "" };

	/**
	 * 数量
	 * @return
	 * 赵玉柱
	 */
	public int count() default 0;
}
