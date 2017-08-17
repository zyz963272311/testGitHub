package com.liiwin.random;

/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年9月28日 下午4:52:51</p>
 * <p>类全名：random.RandomImpl</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class RandomImpl implements Random
{
	@Override
	public Object getRandomObject(Object max)
	{
		Object o = null;
		if (max instanceof Boolean)
		{
			o = getRandomObject(false, max);
		} else if (max instanceof Character)
		{
			o = getRandomObject(Character.MIN_SURROGATE, max);
		} else if (max instanceof Byte)
		{
			o = getRandomObject(Byte.MIN_VALUE, max);
		} else if (max instanceof Short)
		{
			o = getRandomObject(Short.MIN_VALUE, max);
		} else if (max instanceof Integer)
		{
			o = getRandomObject(Integer.MIN_VALUE, max);
		} else if (max instanceof Long)
		{
			o = getRandomObject(Long.MIN_VALUE, max);
		} else if (max instanceof Float)
		{
			o = getRandomObject(Float.MIN_VALUE, max);
		} else if (max instanceof Double)
		{
			o = getRandomObject(Double.MIN_VALUE, max);
		}
		return o;
	}

	@Override
	public Object getRandomObject(Object min, Object max)
	{
		Object o = null;
		if (min.getClass().equals(max.getClass()))
		{
			if (max instanceof Boolean)
			{
				o = getRandomBoolean((boolean) min, (boolean) max);
			} else if (max instanceof Character)
			{
				o = getRandomChar((char) min, (char) max);
			} else if (max instanceof Byte)
			{
				o = getRandomByte((byte) min, (byte) max);
			} else if (max instanceof Short)
			{
				o = getRandomShort((short) min, (short) max);
			} else if (max instanceof Integer)
			{
				o = getRandomInt((int) min, (int) max);
			} else if (max instanceof Long)
			{
				o = getRandomLong((long) min, (long) max);
			} else if (max instanceof Float)
			{
				o = getRandomFloat((float) min, (float) max);
			} else if (max instanceof Double)
			{
				o = getRandomDouble((double) min, (double) max);
			}
		}
		return o;
	}

	@Override
	public Object[] getRandomObjectArray(Object max, int length)
	{
		Object[] array = null;
		if (max instanceof Boolean[])
		{
			array = getRandomObjectArray(false, max, length);
		} else if (max instanceof Character[])
		{
			array = getRandomObjectArray(Character.MIN_VALUE, max, length);
		} else if (max instanceof Byte[])
		{
			array = getRandomObjectArray((byte) 0, max, length);
		} else if (max instanceof Short[])
		{
			array = getRandomObjectArray((short) 0, max, length);
		} else if (max instanceof Integer[])
		{
			array = getRandomObjectArray(0, max, length);
		} else if (max instanceof Long[])
		{
			array = getRandomObjectArray((long) 0, max, length);
		} else if (max instanceof Float[])
		{
			array = getRandomObjectArray(0.0f, max, length);
		} else if (max instanceof Double[])
		{
			array = getRandomObjectArray(0.0d, max, length);
		}
		return array;
	}

	@Override
	public Object[] getRandomObjectArray(Object min, Object max, int length)
	{
		Object[] array = null;
		if (min.getClass().equals(max.getClass()))
		{
			if (max instanceof Boolean)
			{
				array = getRandomBooleanArray((boolean) min, (boolean) max, length);
			} else if (max instanceof Character)
			{
				array = getRandomCharArray((char) min, (char) max, length);
			} else if (max instanceof Byte)
			{
				array = getRandomByteArray((byte) min, (byte) max, length);
			} else if (max instanceof Short)
			{
				array = getRandomShortArray((short) min, (short) max, length);
			} else if (max instanceof Integer)
			{
				array = getRandomIntArray((int) min, (int) max, length);
			} else if (max instanceof Long)
			{
				array = getRandomLongArray((long) min, (long) max, length);
			} else if (max instanceof Float)
			{
				array = getRandomFloatArray((float) min, (float) max, length);
			} else if (max instanceof Double)
			{
				array = getRandomDoubleArray((double) min, (double) max, length);
			}
		}
		return array;
	}

	/************************************私有方法分割线************************/
	/************************************getRandomObject实现****************/
	boolean getRandomBoolean(boolean min, boolean max)
	{
		if (min)
		{
			return true;
		}
		if (!max)
		{
			return false;
		}
		return new java.util.Random().nextBoolean();
	}

	char getRandomChar(char min, char max)
	{
		return (char) (min + (max - min) * Math.random());
	}

	byte getRandomByte(byte min, byte max)
	{
		return (byte) (min + (max - min) * Math.random());
	}

	short getRandomShort(short min, short max)
	{
		return (short) (min + (max - min) * Math.random());
	}

	int getRandomInt(int min, int max)
	{
		return (int) (min + (max - min) * Math.random());
	}

	long getRandomLong(long min, long max)
	{
		return (long) (min + (max - min) * Math.random());
	}

	float getRandomFloat(float min, float max)
	{
		return (float) (min + (max - min) * Math.random());
	}

	double getRandomDouble(double min, double max)
	{
		return (min + (max - min) * Math.random());
	}

	/************************************getRandomObjectArray实现****************/
	Object[] getRandomBooleanArray(boolean min, boolean max, int length)
	{
		Object[] o = new Object[length];
		for (int i = 0; i < length; i++)
		{
			o[i] = getRandomBoolean(min, max);
		}
		return o;
	}

	Object[] getRandomCharArray(char min, char max, int length)
	{
		Object[] o = new Object[length];
		for (int i = 0; i < length; i++)
		{
			o[i] = getRandomChar(min, max);
		}
		return o;
	}

	Object[] getRandomByteArray(byte min, byte max, int length)
	{
		Object[] o = new Object[length];
		for (int i = 0; i < length; i++)
		{
			o[i] = getRandomByte(min, max);
		}
		return o;
	}

	Object[] getRandomShortArray(short min, short max, int length)
	{
		Object[] o = new Object[length];
		for (int i = 0; i < length; i++)
		{
			o[i] = getRandomShort(min, max);
		}
		return o;
	}

	Object[] getRandomIntArray(int min, int max, int length)
	{
		Object[] o = new Object[length];
		for (int i = 0; i < length; i++)
		{
			o[i] = getRandomInt(min, max);
		}
		return o;
	}

	Object[] getRandomLongArray(long min, long max, int length)
	{
		Object[] o = new Object[length];
		for (int i = 0; i < length; i++)
		{
			o[i] = getRandomLong(min, max);
		}
		return o;
	}

	Object[] getRandomFloatArray(float min, float max, int length)
	{
		Object[] o = new Object[length];
		for (int i = 0; i < length; i++)
		{
			o[i] = getRandomFloat(min, max);
		}
		return o;
	}

	Object[] getRandomDoubleArray(double min, double max, int length)
	{
		Object[] o = new Object[length];
		for (int i = 0; i < length; i++)
		{
			o[i] = getRandomDouble(min, max);
		}
		return o;
	}

	/*************************测试main方法***********************/
	public static void main(String[] args)
	{
		Random random = new RandomImpl();
		System.out.println(random.getRandomObject(new Integer(1), new Integer(10)));
		long start = System.currentTimeMillis();
		Object[] array = random.getRandomObjectArray(new Integer(-10), new Integer(10), 1000000);
		System.out.println("耗时" + (System.currentTimeMillis() - start));
		for (Object object : array)
		{
			System.out.println(object);
		}
	}
}
