package serializable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年10月9日 下午4:27:25</p>
 * <p>类全名：serializable.Serializable</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Serializable implements java.io.Serializable
{
	private static final long	serialVersionUID	= 1l;
	private String				userName			= "赵玉柱";
	private int					userAge				= 24;

	public void serializable(String shortFileName)
	{
		try
		{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(shortFileName));
			Serializable serializable = new Serializable();
			oos.writeObject(serializable);
			oos.flush();
			oos.close();
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void deserialize(String shortFileName)
	{
		ObjectInputStream ois = null;
		FileInputStream fis = null;
		userName = "我不是赵玉柱";
		userAge = 110;
		try
		{
			fis = new FileInputStream(shortFileName);
			ois = new ObjectInputStream(fis);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		Serializable serializable = null;
		try
		{
			serializable = (Serializable) ois.readObject();
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		System.out.println();
		System.out.println("name=" + serializable.userName);
		System.out.println("age=" + serializable.userAge);
	}
}
