package util;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年5月16日 上午11:13:33</p>
 * <p>类全名：util.PropsUtil</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class PropsUtil
{
	//属性文件一般放到
	////把db.properties放在系统属性java.home下，一般是jdk目录下的 jre目录里。
	//private static final String javaHome = System.getProperty("java.home");
	//private static final String FS = System.getProperty("file.separator");
	//private static final String propsFileName = javaHome + FS + "destinations.properties";
	/**
	  * 构造函数
	  * 找到数据源，并用这个数据源创建连接
	  */
	public PropsUtil()
	{
	}

	public String getPropsFilePath()
	{
		String filePath = this.getClass().getResource("/").getPath().toString();
		filePath = filePath.substring(0, filePath.indexOf("classes") - 1) + "/destinations.properties";
		return filePath;
	}

	public InputStream getPropsIS()
	{
		InputStream ins = this.getClass().getResourceAsStream("/destinations.properties");
		return ins;
	}

	/**
	 * 读取属性文件中的属性值
	 * @param attr
	 * @return
	 */
	public String readSingleProps(String attr)
	{
		String retValue = "";
		Properties props = new Properties();
		try
		{
			/*if (!FileUtil.isFileExist(getPropsFilePath())) {
			    return "";
			}
			FileInputStream fi = new FileInputStream(getPropsFilePath());*/
			InputStream fi = getPropsIS();
			props.load(fi);
			fi.close();
			retValue = props.getProperty(attr);
		} catch (Exception e)
		{
			return "";
		}
		return retValue;
	}

	/**
	 * 读取属性文件中的属性值
	 * @return
	 */
	public HashMap readAllProps()
	{
		HashMap h = new HashMap();
		Properties props = new Properties();
		try
		{
			/*if (!FileUtil.isFileExist(getPropsFilePath()))
			    return new HashMap();
			FileInputStream fi = new FileInputStream(getPropsFilePath());*/
			InputStream fi = getPropsIS();
			props.load(fi);
			fi.close();
			Enumeration er = props.propertyNames();
			while (er.hasMoreElements())
			{
				String paramName = (String) er.nextElement();
				h.put(paramName, props.getProperty(paramName));
			}
		} catch (Exception e)
		{
			return new HashMap();
		}
		return h;
	}
}
