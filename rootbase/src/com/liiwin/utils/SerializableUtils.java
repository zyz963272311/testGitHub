package com.liiwin.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.liiwin.test.Test;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年6月7日 下午6:27:27</p>
 * <p>类全名：com.liiwin.utils.SerializableUtils</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class SerializableUtils
{
	/**
	 * 序列化
	 * @param obj
	 * @return
	 * 赵玉柱
	 */
	public static byte[] serializable(Object obj)
	{
		try (ByteArrayOutputStream bai = new ByteArrayOutputStream(); ObjectOutputStream obi = new ObjectOutputStream(bai);)
		{
			obi.writeObject(obj);
			return bai.toByteArray();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 反序列化
	 * @param bytes
	 * @return
	 * 赵玉柱
	 */
	public static Object unSerializable(byte[] bytes)
	{
		try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes); ObjectInputStream oii = new ObjectInputStream(bis);)
		{
			Object obj = oii.readObject();
			return obj;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 克隆
	 * @param t
	 * @return
	 * 赵玉柱
	 */
	public static <T extends Serializable> T clone(T t)
	{
		byte[] objBytes = serializable(t);
		return (T) unSerializable(objBytes);
	}

	public static void main(String[] args)
	{
		com.liiwin.test.Test t = new com.liiwin.test.Test();
		t.setA(1);
		t.setE(1);
		System.out.println(t);
		com.liiwin.test.Test b = null;
		byte[] byt = null;
		try (ByteArrayOutputStream bai = new ByteArrayOutputStream(); ObjectOutputStream obi = new ObjectOutputStream(bai);)
		{
			obi.writeObject(t);
			byt = bai.toByteArray();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		try (ByteArrayInputStream bis = new ByteArrayInputStream(byt); ObjectInputStream oii = new ObjectInputStream(bis);)
		{
			b = (Test) oii.readObject();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		System.out.println(b);
	}
}
