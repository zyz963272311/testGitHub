package util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年10月20日 上午11:19:56</p>
 * <p>类全名：util.GetMax</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Compare
{
	/**
	 * 获取一个继承自Comparable类型的Object的最大值
	 * @param o Comparable类型的Object对象
	 * @return 最大值
	 * @throws Exception 抛出的异常
	 * x250-2
	 */
	public Object getMax(Object o) throws Exception
	{
		return compareMax(o);
	}

	/**
	 * 获取一个继承自Comparable类型的Object的最小值
	 * @param o Comparable类型的Object对象
	 * @return 最小值
	 * x250-2
	 */
	public Object getMin(Object o)
	{
		return compareMin(o);
	}

	/**
	 * 获取数组的最大值
	 * @param o 一个继承了Comparable的类型
	 * @return 获取数组内的最大值
	 * x250-2
	 */
	/*****************************基本数据类型与String类型**********************************/
	@SuppressWarnings("unchecked")
	private Object compareMax(Object o)
	{
		if (null == o)
		{
			throw new NullPointerException("请为函数传入参数");
		}
		if (!(o instanceof Comparable<?>) && !(o instanceof Comparable<?>[]))
		{
			throw new NullPointerException("输入的参数不能比较大小");
		}
		Object result = null;
		if (o instanceof List)
		{
			result = compareMax((List<Comparable>) o, true);
		} else if (o instanceof Map)
		{
		} else if (o instanceof Boolean[])
		{
			result = compareMax((Boolean[]) o, true);
		} else if (o instanceof Character[])
		{
			result = compareMax((Character[]) o, true);
		} else if (o instanceof Byte[])
		{
			result = compareMax((Byte[]) o, true);
		} else if (o instanceof Short[])
		{
			result = compareMax((Short[]) o, true);
		} else if (o instanceof Integer[])
		{
			result = compareMax((Integer[]) o, true);
		} else if (o instanceof Long[])
		{
			result = compareMax((Long[]) o, true);
		} else if (o instanceof Float[])
		{
			result = compareMax((Float[]) o, true);
		} else if (o instanceof Double[])
		{
			result = compareMax((Double[]) o, true);
		} else if (o instanceof String[])
		{
			result = compareMax((String[]) o, true);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	private Object compareMin(Object o)
	{
		if (null == o)
		{
			throw new NullPointerException("请为函数传入参数");
		}
		if (!(o instanceof Comparable<?>))
		{
			throw new NullPointerException("输入的参数不能比较大小");
		}
		Object result = null;
		if (o instanceof List)
		{
			result = compareMin((List<Comparable>) o, true);
		} else if (o instanceof Map)
		{
		} else if (o instanceof Boolean[])
		{
			result = compareMin((Boolean[]) o, true);
		} else if (o instanceof Character[])
		{
			result = compareMin((Character[]) o, true);
		} else if (o instanceof Byte[])
		{
			result = compareMin((Byte[]) o, true);
		} else if (o instanceof Short[])
		{
			result = compareMin((Short[]) o, true);
		} else if (o instanceof Integer[])
		{
			result = compareMin((Integer[]) o, true);
		} else if (o instanceof Long[])
		{
			result = compareMin((Long[]) o, true);
		} else if (o instanceof Float[])
		{
			result = compareMin((Float[]) o, true);
		} else if (o instanceof Double[])
		{
			result = compareMin((Double[]) o, true);
		} else if (o instanceof String[])
		{
			result = compareMax((String[]) o, true);
		}
		return result;
	}

	/********************************基本类型结束********************************/
	/**********************************LIST***********************************/
	@SuppressWarnings("unchecked")
	private <O extends Comparable<O>> Object compareMax(List<O> o, boolean flag)
	{
		Object result = null;
		if (null == o || o.size() == 0)
		{
			throw new NullPointerException("请为函数传入参数");
		}
		if (o.get(0) instanceof Boolean)
		{
			for (Object obj : o)
			{
				if (!(obj instanceof Boolean))
				{
					throw new RuntimeException("不同类型之间无法进行比较");
				}
			}
			result = compareListMax((List<Boolean>) o, true);
		} else if (o.get(0) instanceof Character)
		{
			for (Object obj : o)
			{
				if (!(obj instanceof Character))
				{
					throw new RuntimeException("不同类型之间无法进行比较");
				}
			}
			result = compareListMax((List<Character>) o, true);
		} else if (o.get(0) instanceof Byte)
		{
			for (Object obj : o)
			{
				if (!(obj instanceof Byte))
				{
					throw new RuntimeException("不同类型之间无法进行比较");
				}
			}
			result = compareListMax((List<Byte>) o, true);
		} else if (o.get(0) instanceof Short)
		{
			for (Object obj : o)
			{
				if (!(obj instanceof Short))
				{
					throw new RuntimeException("不同类型之间无法进行比较");
				}
			}
			result = compareListMax((List<Short>) o, true);
		} else if (o.get(0) instanceof Integer)
		{
			for (Object obj : o)
			{
				if (!(obj instanceof Integer))
				{
					throw new RuntimeException("不同类型之间无法进行比较");
				}
			}
			result = compareListMax((List<Integer>) o, true);
		} else if (o.get(0) instanceof Long)
		{
			for (Object obj : o)
			{
				if (!(obj instanceof Long))
				{
					throw new RuntimeException("不同类型之间无法进行比较");
				}
			}
			result = compareListMax((List<Long>) o, true);
		} else if (o.get(0) instanceof Float)
		{
			for (Object obj : o)
			{
				if (!(obj instanceof Float))
				{
					throw new RuntimeException("不同类型之间无法进行比较");
				}
			}
			result = compareListMax((List<Float>) o, true);
		} else if (o.get(0) instanceof Double)
		{
			for (Object obj : o)
			{
				if (!(obj instanceof Double))
				{
					throw new RuntimeException("不同类型之间无法进行比较");
				}
			}
			result = compareListMax((List<Double>) o, true);
		} else if (o.get(0) instanceof String)
		{
			for (Object obj : o)
			{
				if (!(obj instanceof String))
				{
					throw new RuntimeException("不同类型之间无法进行比较");
				}
			}
			result = compareListMax((List<String>) o, true);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	private <O extends Comparable<O>> Object compareMin(List<O> o, boolean flag)
	{
		Object result = null;
		if (null == o || o.size() == 0)
		{
			throw new NullPointerException("请为函数传入参数");
		}
		if (o.get(0) instanceof Boolean)
		{
			for (Object obj : o)
			{
				if (!(obj instanceof Boolean))
				{
					throw new RuntimeException("不同类型之间无法进行比较");
				}
			}
			result = compareListMin((List<Boolean>) o, true);
		} else if (o.get(0) instanceof Character)
		{
			for (Object obj : o)
			{
				if (!(obj instanceof Character))
				{
					throw new RuntimeException("不同类型之间无法进行比较");
				}
			}
			result = compareListMin((List<Character>) o, true);
		} else if (o.get(0) instanceof Byte)
		{
			for (Object obj : o)
			{
				if (!(obj instanceof Byte))
				{
					throw new RuntimeException("不同类型之间无法进行比较");
				}
			}
			result = compareListMin((List<Byte>) o, true);
		} else if (o.get(0) instanceof Short)
		{
			for (Object obj : o)
			{
				if (!(obj instanceof Short))
				{
					throw new RuntimeException("不同类型之间无法进行比较");
				}
			}
			result = compareListMin((List<Short>) o, true);
		} else if (o.get(0) instanceof Integer)
		{
			for (Object obj : o)
			{
				if (!(obj instanceof Integer))
				{
					throw new RuntimeException("不同类型之间无法进行比较");
				}
			}
			result = compareListMin((List<Integer>) o, true);
		} else if (o.get(0) instanceof Long)
		{
			for (Object obj : o)
			{
				if (!(obj instanceof Long))
				{
					throw new RuntimeException("不同类型之间无法进行比较");
				}
			}
			result = compareListMin((List<Long>) o, true);
		} else if (o.get(0) instanceof Float)
		{
			for (Object obj : o)
			{
				if (!(obj instanceof Float))
				{
					throw new RuntimeException("不同类型之间无法进行比较");
				}
			}
			result = compareListMin((List<Float>) o, true);
		} else if (o.get(0) instanceof Double)
		{
			for (Object obj : o)
			{
				if (!(obj instanceof Double))
				{
					throw new RuntimeException("不同类型之间无法进行比较");
				}
			}
			result = compareListMin((List<Double>) o, true);
		} else if (o.get(0) instanceof String)
		{
			for (Object obj : o)
			{
				if (!(obj instanceof String))
				{
					throw new RuntimeException("不同类型之间无法进行比较");
				}
			}
			result = compareListMin((List<String>) o, true);
		}
		return result;
	}

	/*****************************LIST结束*********************************/
	/*******************************MAP***********************************/
	private <O extends Comparable<O>> Object compareMapMax(Map<Object,O> o, boolean flag)
	{
		if (null == o || o.size() == 0)
		{
			throw new NullPointerException("输入的值为空");
		}
		Iterator it = o.entrySet().iterator();
		if (it.hasNext())
		{
		}
		return null;
	}

	private <O extends Comparable<O>> Object compareMapMin(Map<Object,O> o, boolean flag)
	{
		return null;
	}

	private <O extends Comparable<O>> Object compareMapMax(Map<Object,O> o, Class cls, boolean flag)
	{
		Object odd = null;
		return null;
	}

	/********************************MAP结束*******************************/
	private <O extends Comparable<O>> Object compareListMax(List<O> o, boolean flag)
	{
		return compare(o, flag);
	}

	private <O extends Comparable<O>> Object compareListMin(List<O> o, boolean flag)
	{
		return compare(o, flag);
	}

	private <O extends Comparable<O>> Object compareMax(O[] o, boolean flag)
	{
		return compare(o, flag);
	}

	private <O extends Comparable<O>> Object compareMin(O[] o, boolean flag)
	{
		return compare(o, flag);
	}

	/**
	 * 获取数组的最值
	 * @param o
	 * @param isGetMax
	 * @return
	 * x250-2
	 */
	private <O extends Comparable<O>> Object compare(O[] o, boolean isGetMax)
	{
		if (null == o || o.length == 0)
		{
			throw new NullPointerException("输入的值为空");
		}
		O result = o[0];
		for (O obj : o)
		{
			if ((result.compareTo(obj) < 0) == isGetMax)
			{
				//如果当前的最大值小于现在进行比较的值，并且是获取最大值，则进行交换
				result = obj;
			}
		}
		return result;
	}

	private <O extends Comparable<O>> Object compare(List<O> o, boolean isGetMax)
	{
		if (null == o || o.size() == 0)
		{
			throw new NullPointerException("输入的值为空");
		}
		O result = o.get(0);
		for (O obj : o)
		{
			if ((result.compareTo(obj) < 0) == isGetMax)
			{
				result = obj;
			}
		}
		return result;
	}
}
