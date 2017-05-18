package test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import util.StrUtil;
import annotation.Test1;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年4月15日 下午9:49:11</p>
 * <p>类全名：test.Testaaa</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
class Testaaa
{
	public static void main(String[] args)
	{
		TestAnno ann = new TestAnno();
		ann.setBbb("aaa");
		Class clazz = TestAnno.class;
		Field[] fields = clazz.getDeclaredFields();
		try
		{
			for (Field field : fields)
			{
				Test1 test1 = field.getAnnotation(Test1.class);
				if (null != test1)
				{
					Method method = clazz.getDeclaredMethod("get" + StrUtil.setFirstUpperOrLower(field.getName(), true));
					Object value = method.invoke(ann);
					if (null == value)
					{
						System.out.println(test1.name() + "【" + field.getName() + "】不能为空");
					}
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
