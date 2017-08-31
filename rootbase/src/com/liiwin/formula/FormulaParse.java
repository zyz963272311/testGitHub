package com.liiwin.formula;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.liiwin.utils.StrUtil;
/**
 * 
 * <p>标题： 公式</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2017年8月31日 下午4:14:43</p>
 * <p>类全名：com.liiwin.formula.FormulaParse</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class FormulaParse
{
	/*匹配#{xx}*/
	private static Pattern	pattern	= Pattern.compile("\\#\\{(.+?)\\}");

	public static String parse(String formula, Map<String,Object> values)
	{
		String result = formula;
		if (StrUtil.isStrTrimNull(formula))
		{
			return result;
		}
		if (values == null || values.isEmpty())
		{
			return formula;
		}
		result = finalExpression(formula, values);
		return result;
	}

	private static String finalExpression(String formula, Map<String,Object> values)
	{
		String result = formula;
		Matcher m = pattern.matcher(formula);
		if (!m.find())
		{
			return formula;
		}
		m.reset();
		while (m.find())
		{
			String key = m.group(1);
			Object value = values.get(key);
			if (value != null)
			{
				result = result.replace(m.group(), StrUtil.obj2str(value));
			}
		}
		return result;
	}
}
