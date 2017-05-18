package design.pattern.prototype.shollow;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年2月22日 下午4:30:01</p>
 * <p>类全名：design.pattern.prototype.shollow.Shollow</p>
 * 作者：x250-2
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Shollow implements Cloneable, Serializable
{
	private int		a;
	private String	b;
	private int[]	c;

	public int getA()
	{
		return a;
	}

	public void setA(int a)
	{
		this.a = a;
	}

	public String getB()
	{
		return b;
	}

	public void setB(String b)
	{
		this.b = b;
	}

	public int[] getC()
	{
		return c;
	}

	public void setC(int[] c)
	{
		this.c = c;
	}

	@Override
	public Object clone() throws CloneNotSupportedException
	{
		Shollow shollow = null;
		shollow = (Shollow) super.clone();
		return shollow;
	}

	@Override
	public String toString()
	{
		return "Shollow [a=" + a + ", b=" + b + ", c=" + Arrays.toString(c) + "]";
	}

	public Shollow deepCopy() throws IOException, ClassNotFoundException
	{
		//将对象存入到二进制流中
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(this);
		//将流从二进制中读取出来
		ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(bis);
		return (Shollow) ois.readObject();
	}
}
