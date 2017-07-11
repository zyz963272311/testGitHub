package com.liiwin.bas;

import java.io.Serializable;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年6月7日 下午5:31:19</p>
 * <p>类全名：com.liiwin.bas.BaseBean</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class BaseBean implements Serializable
{
	private static final long	serialVersionUID	= 1L;
	//	public String toString(BaseBean bean)
	//	{
	//		if (bean == null)
	//		{
	//			throw new RuntimeException("Bean不可为空");
	//		}
	//		Class<BaseBean> clazz = (Class<BaseBean>) bean.getClass();
	//		Field[] fields = clazz.getDeclaredFields();
	//		Set<Method> methods = new HashSet<>();
	//		StringBuffer result = new StringBuffer();
	//		try
	//		{
	//			for (Field field : fields)
	//			{
	//				if (field.getAnnotation(Column.class) != null)
	//				{
	//					//是表字段
	//					String methodName = "get" + StrUtil.setFirstUpperOrLower(field.getName(), true);
	//					Method method = clazz.getDeclaredMethod(methodName);
	//					if (method == null)
	//					{
	//						throw new RuntimeException(clazz.toString() + "不存在" + methodName + "方法，修改程序");
	//					}
	//					methods.add(method);
	//				}
	//			}
	//			Iterator<Method> iterator = methods.iterator();
	//			while (iterator.hasNext())
	//			{
	//				Object vle = iterator.next().invoke(bean);
	//				if (vle != null)
	//				{
	//					result.append(vle).append(",");
	//				}
	//			}
	//		} catch (Exception e)
	//		{
	//			throw new RuntimeException(e);
	//		}
	//		return "";
	//	}
}
