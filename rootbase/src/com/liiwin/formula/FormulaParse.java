package com.liiwin.formula;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.liiwin.utils.StrUtil;

public class FormulaParse 
{
	/*匹配#{xx}*/
	private static Pattern pattern = Pattern.compile("\\#\\{(.+?)\\}");
	public static String parse(String formula,Map<String, Object> values)
	{
		String result = formula;
		if(StrUtil.isStrTrimNull(formula))
		{
			return result;
		}
		if(values == null || values.isEmpty())
		{
			return formula;
		}
		result = finalExpression(formula, values);
		return result;
	}
	private static String finalExpression(String formula,Map<String, Object> values)
	{
		String result = formula;
		Matcher m = pattern.matcher(formula);
		if(!m.find())
		{
			return formula;
		}
		m.reset();
		while(m.find())
		{
			String key = m.group(1);
			Object value = values.get(key);
			if(value!=null)
			{
				result = result.replace(m.group(), StrUtil.obj2str(value));
			}
		}
		return result;
	}
}
