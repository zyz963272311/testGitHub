package property;

import util.Validator;
/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年11月23日 下午8:27:36</p>
 * <p>类全名：property.Property</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Property
{
	public static String getPreperty(String key)
	{
		Validator.isNotNull(key, "环境变量名称");
		String value = System.getenv(key);
		return value;
	}

	public static void main(String[] args)
	{
	}
}
