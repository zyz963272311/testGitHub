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
			o = getRandomObject((byte) 0, max);
		} else if (max instanceof Short)
		{
			o = getRandomObject((short) 0, max);
		} else if (max instanceof Integer)
		{
			o = getRandomObject(0, max);
		} else if (max instanceof Long)
		{
			o = getRandomObject(0, max);
		} else if (max instanceof Float)
		{
			o = getRandomObject(0.0f, max);
		} else if (max instanceof Double)
		{
			o = getRandomObject(0.0, max);
		}
		return o;
	}

	@Override
	public Object getRandomObject(Object min, Object max)
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
			o = getRandomObject((byte) 0, max);
		} else if (max instanceof Short)
		{
			o = getRandomObject((short) 0, max);
		} else if (max instanceof Integer)
		{
			o = getRandomObject(0, max);
		} else if (max instanceof Long)
		{
			o = getRandomObject(0, max);
		} else if (max instanceof Float)
		{
			o = getRandomObject(0.0f, max);
		} else if (max instanceof Double)
		{
			o = getRandomObject(0.0, max);
		}
		return o;
	}

	@Override
	public long getRandomObject(Object[] o, Object max, int length)
	{
		long time = 0;
		if (o instanceof Boolean[])
		{
			time = getRandomObject(o, false, max, length);
		} else if (o instanceof Character[])
		{
			time = getRandomObject(o, Character.MIN_VALUE, max, length);
		} else if (o instanceof Byte[])
		{
			time = getRandomObject(o, (byte) 0, max, length);
		} else if (o instanceof Short[])
		{
			time = getRandomObject(o, (short) 0, max, length);
		} else if (o instanceof Integer[])
		{
			time = getRandomObject(o, 0, max, length);
		} else if (o instanceof Long[])
		{
			time = getRandomObject(o, (long) 0, max, length);
		} else if (o instanceof Float[])
		{
			time = getRandomObject(o, 0.0f, max, length);
		} else if (o instanceof Double[])
		{
			time = getRandomObject(o, 0.0d, max, length);
		}
		return time;
	}

	@Override
	public long getRandomObject(Object[] o, Object min, Object max, int length)
	{
		long start = System.currentTimeMillis();
		//TODO impl
		long end = System.currentTimeMillis();
		return end - start;
	}

	/************************************私有方法分割线************************/
	/************************************getRandomObject实现****************/
	boolean getRandomBoolean(boolean min, boolean max)
	{
		return false;
	}

	char getRandomChar(char min, char max)
	{
		return Character.MIN_VALUE;
	}

	byte getRandomByte(byte min, byte max)
	{
		return Byte.MIN_VALUE;
	}

	short getRandomShort(short min, short max)
	{
		return Short.MIN_VALUE;
	}

	int getRandomInt(int min, int max)
	{
		return Integer.MIN_VALUE;
	}

	long getRandomLong(long min, long max)
	{
		return Long.MIN_VALUE;
	}

	float getRandomFloat(float min, float max)
	{
		return Float.MIN_VALUE;
	}

	double getRandomDouble(double min, double max)
	{
		return Double.MIN_VALUE;
	}

	/************************************getRandomObject数组实现****************/
	boolean[] getRandomBoolean(boolean[] o, boolean min, boolean max, int length)
	{
		return null;
	}

	char[] getRandomChar(char[] o, char min, char max, int length)
	{
		return null;
	}

	byte[] getRandomByte(byte[] o, byte min, byte max, int length)
	{
		return null;
	}

	short[] getRandomShort(short[] o, short min, short max, int length)
	{
		return null;
	}

	int[] getRandomInt(int[] o, int min, int max, int length)
	{
		return null;
	}

	long[] getRandomLong(long[] o, long min, long max, int length)
	{
		return null;
	}

	float[] getRandomFloat(float[] o, float min, float max, int length)
	{
		return null;
	}

	double[] getRandomDouble(double[] o, double min, double max, int length)
	{
		return null;
	}
}
