package util;

/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年10月27日 下午8:16:48</p>
 * <p>类全名：util.BasePackingUtil</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class BasePackingUtil
{
	/****************************基本数据类型与包装类型**********************/
	/************************boolean<---->Boolean*********************/
	public static Boolean base2Packing(boolean b)
	{
		return new Boolean(b);
	}

	public static boolean packing2Base(Boolean b)
	{
		if (b != null)
		{
			return b.booleanValue();
		} else
		{
			throw new NullPointerException("您输入的值为空");
		}
	}

	public static boolean[] packing2Base(Boolean[] b)
	{
		boolean[] nb;
		if (b != null && b.length > 0)
		{
			nb = new boolean[b.length];
			for (int i = 0; i < b.length; i++)
			{
				nb[i] = packing2Base(b[i]);
			}
		} else
		{
			throw new NullPointerException("您输入的数组为空");
		}
		return nb;
	}

	public static Boolean[] base2Packing(boolean[] b)
	{
		Boolean[] nb = new Boolean[b.length];
		for (int i = 0; i < b.length; i++)
		{
			nb[i] = base2Packing(b[i]);
		}
		return nb;
	}

	/************************boolean<---->Boolean********************/
	/************************char<---->Character*********************/
	public static Character base2Packing(char b)
	{
		return new Character(b);
	}

	public static char packing2Base(Character b)
	{
		if (b != null)
		{
			return b.charValue();
		} else
		{
			throw new NullPointerException("您输入的值为空");
		}
	}

	public static char[] packing2Base(Character[] b)
	{
		char[] nb;
		if (b != null && b.length > 0)
		{
			nb = new char[b.length];
			for (int i = 0; i < b.length; i++)
			{
				nb[i] = packing2Base(b[i]);
			}
		} else
		{
			throw new NullPointerException("您输入的数组为空");
		}
		return nb;
	}

	public static Character[] base2Packing(char[] b)
	{
		Character[] nb = new Character[b.length];
		for (int i = 0; i < b.length; i++)
		{
			nb[i] = base2Packing(b[i]);
		}
		return nb;
	}

	/************************char<---->Character********************/
	/***************************byte<---->Byte***********************/
	public static Byte base2Packing(byte b)
	{
		return new Byte(b);
	}

	public static byte packing2Base(Byte b)
	{
		if (b != null)
		{
			return b.byteValue();
		} else
		{
			throw new NullPointerException("您输入的值为空");
		}
	}

	public static byte[] packing2Base(Byte[] b)
	{
		byte[] nb;
		if (b != null && b.length > 0)
		{
			nb = new byte[b.length];
			for (int i = 0; i < b.length; i++)
			{
				nb[i] = packing2Base(b[i]);
			}
		} else
		{
			throw new NullPointerException("您输入的数组为空");
		}
		return nb;
	}

	public static Byte[] base2Packing(byte[] b)
	{
		Byte[] nb = new Byte[b.length];
		for (int i = 0; i < b.length; i++)
		{
			nb[i] = base2Packing(b[i]);
		}
		return nb;
	}

	/*************************short<---->Short*******************/
	public static Short base2Packing(short b)
	{
		return new Short(b);
	}

	public static short packing2Base(Short b)
	{
		if (b != null)
		{
			return b.shortValue();
		} else
		{
			throw new NullPointerException("您输入的值为空");
		}
	}

	public static short[] packing2Base(Short[] b)
	{
		short[] nb;
		if (b != null && b.length > 0)
		{
			nb = new short[b.length];
			for (int i = 0; i < b.length; i++)
			{
				nb[i] = packing2Base(b[i]);
			}
		} else
		{
			throw new NullPointerException("您输入的数组为空");
		}
		return nb;
	}

	public static Short[] base2Packing(short[] b)
	{
		Short[] nb = new Short[b.length];
		for (int i = 0; i < b.length; i++)
		{
			nb[i] = base2Packing(b[i]);
		}
		return nb;
	}

	/****************************short<---->Short****************/
	/******************************int<---->Integer**************/
	public static Integer base2Packing(int b)
	{
		return new Integer(b);
	}

	public static int packing2Base(Integer b)
	{
		if (b != null)
		{
			return b.intValue();
		} else
		{
			throw new NullPointerException("您输入的值为空");
		}
	}

	public static int[] packing2Base(Integer[] b)
	{
		int[] nb;
		if (b != null && b.length > 0)
		{
			nb = new int[b.length];
			for (int i = 0; i < b.length; i++)
			{
				nb[i] = packing2Base(b[i]);
			}
		} else
		{
			throw new NullPointerException("您输入的数组为空");
		}
		return nb;
	}

	public static Integer[] base2Packing(int[] b)
	{
		Integer[] nb = new Integer[b.length];
		for (int i = 0; i < b.length; i++)
		{
			nb[i] = base2Packing(b[i]);
		}
		return nb;
	}

	/******************************int<---->Integer****************/
	/*************************long<---->Long*******************/
	public static Long base2Packing(long b)
	{
		return new Long(b);
	}

	public static long packing2Base(Long b)
	{
		if (b != null)
		{
			return b.longValue();
		} else
		{
			throw new NullPointerException("您输入的值为空");
		}
	}

	public static long[] packing2Base(Long[] b)
	{
		long[] nb;
		if (b != null && b.length > 0)
		{
			nb = new long[b.length];
			for (int i = 0; i < b.length; i++)
			{
				nb[i] = packing2Base(b[i]);
			}
		} else
		{
			throw new NullPointerException("您输入的数组为空");
		}
		return nb;
	}

	public static Long[] base2Packing(long[] b)
	{
		Long[] nb = new Long[b.length];
		for (int i = 0; i < b.length; i++)
		{
			nb[i] = base2Packing(b[i]);
		}
		return nb;
	}

	/****************************long<---->Long****************/
	/*************************float<---->Float*******************/
	public static Float base2Packing(float b)
	{
		return new Float(b);
	}

	public static float packing2Base(Float b)
	{
		if (b != null)
		{
			return b.floatValue();
		} else
		{
			throw new NullPointerException("您输入的值为空");
		}
	}

	public static float[] packing2Base(Float[] b)
	{
		float[] nb;
		if (b != null && b.length > 0)
		{
			nb = new float[b.length];
			for (int i = 0; i < b.length; i++)
			{
				nb[i] = packing2Base(b[i]);
			}
		} else
		{
			throw new NullPointerException("您输入的数组为空");
		}
		return nb;
	}

	public static Float[] base2Packing(float[] b)
	{
		Float[] nb = new Float[b.length];
		for (int i = 0; i < b.length; i++)
		{
			nb[i] = base2Packing(b[i]);
		}
		return nb;
	}

	/****************************float<---->Float****************/
	/*************************double<---->Double*******************/
	public static Double base2Packing(double b)
	{
		return new Double(b);
	}

	public static double packing2Base(Double b)
	{
		if (b != null)
		{
			return b.doubleValue();
		} else
		{
			throw new NullPointerException("您输入的值为空");
		}
	}

	public static double[] packing2Base(Double[] b)
	{
		double[] nb;
		if (b != null && b.length > 0)
		{
			nb = new double[b.length];
			for (int i = 0; i < b.length; i++)
			{
				nb[i] = packing2Base(b[i]);
			}
		} else
		{
			throw new NullPointerException("您输入的数组为空");
		}
		return nb;
	}

	public static Double[] base2Packing(double[] b)
	{
		Double[] nb = new Double[b.length];
		for (int i = 0; i < b.length; i++)
		{
			nb[i] = base2Packing(b[i]);
		}
		return nb;
	}
	/****************************double<---->Double****************/
	/****************************基本数据类型数组与包装类数组***************/
}
