package _db;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import util.StrUtil;
import annotation.Test2;
import annotation.Test3;
import annotation.Test4;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年4月15日 下午10:22:06</p>
 * <p>类全名：annotation.Test</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Test
{
	@SuppressWarnings("unchecked")
	public static void main(String[] args)
	{
		TestAnno anno = new TestAnno();
		anno.setStr1("321");
		Class clazz = _db.TestAnno.class;
		Test2 test2 = (Test2) clazz.getAnnotation(Test2.class);
		Test3[] t3 = test2.names();
		String errMsg = "";
		try
		{
			for (Test3 test3 : t3)
			{
				int count = test3.count();
				if (count == 0)
				{
					continue;
				}
				String[] fields = test3.fields();
				int test3Count = fields.length;
				if (count > test3Count)
				{
					count = test3Count;
				}
				//空字段个数
				int cnt = 0;
				String message = "";
				for (String fld : fields)
				{
					Field field = clazz.getDeclaredField(fld);
					Test4 test4 = field.getAnnotation(Test4.class);
					Method method = clazz.getDeclaredMethod("get" + StrUtil.setFirstUpperOrLower(field.getName(), true));
					Object obj = method.invoke(anno);
					message += "参数" + (test4 == null ? field.getName() : test4.name()) + "【" + field.getName() + "】";
					if (null == obj)
					{
						cnt++;
					}
				}
				if (test3Count - cnt < count)
				{
					errMsg += message + "至少有" + count + "个不为空\r\n";
				}
			}
			if (null != errMsg)
			{
				System.out.println(errMsg);
			}
		} catch (Exception ex)
		{
			ex.printStackTrace();
		} finally
		{
		}
	}
}
