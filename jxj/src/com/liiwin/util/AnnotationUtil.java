package com.liiwin.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
/**
 * <p>标题： 注解工具类</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年5月18日 下午4:50:56</p>
 * <p>类全名：com.liiwin.util.AnnotationUtil</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class AnnotationUtil
{
	/**
	* @param packName 要扫描的包
	*/
	public static Set<Class<?>> findFileClass(String packName)
	{
		Set<Class<?>> clazzs = new LinkedHashSet<Class<?>>();
		String packageDirName = packName.replace('.', '/');
		Enumeration<URL> dirs;
		try
		{
			dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
			while (dirs.hasMoreElements())
			{
				URL url = dirs.nextElement();
				String protocol = url.getProtocol();
				if ("file".equals(protocol))
				{//扫描file包中的类
					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
					getFileClass(packName, filePath, clazzs);
				} else if ("jar".equals(protocol))
				{//扫描jar包中的类
					JarFile jarFile = ((JarURLConnection) url.openConnection()).getJarFile();
					getJarClass(jarFile, packageDirName, clazzs);
				}
			}
		} catch (Exception e)
		{
			e.getStackTrace();
		}
		return clazzs;
	}

	/**
	 * 获取文件中的class
	 */
	public static void getFileClass(String packName, String filePath, Set<Class<?>> clazzs)
	{
		File dir = new File(filePath);
		if (!dir.exists() || !dir.isDirectory())
		{
			System.out.println("包目录不存在!");
			return;
		}
		File[] dirFiles = dir.listFiles(new FileFilter()
		{
			@Override
			public boolean accept(File file)
			{
				boolean acceptDir = file.isDirectory();// 接受dir目录
				boolean acceptClass = file.getName().endsWith(".class");// 接受class文件
				return acceptDir || acceptClass;
			}
		});
		for (File file : dirFiles)
		{
			if (file.isDirectory())
			{
				getFileClass(packName + "." + file.getName(), file.getAbsolutePath(), clazzs);
			} else
			{
				String className = file.getName().substring(0, file.getName().length() - 6);
				try
				{
					Class<?> clazz = Thread.currentThread().getContextClassLoader().loadClass(packName + "." + className);
					clazzs.add(clazz);
				} catch (ClassNotFoundException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 获取jar中的class
	 * @throws IOException 
	 */
	public static void getJarClass(JarFile jarFile, String filepath, Set<Class<?>> clazzs) throws IOException
	{
		List<JarEntry> jarEntryList = new ArrayList<JarEntry>();
		Enumeration<JarEntry> enumes = jarFile.entries();
		while (enumes.hasMoreElements())
		{
			JarEntry entry = enumes.nextElement();
			// 过滤出满足我们需求的东西
			if (entry.getName().startsWith(filepath) && entry.getName().endsWith(".class"))
			{
				jarEntryList.add(entry);
			}
		}
		for (JarEntry entry : jarEntryList)
		{
			String className = entry.getName().replace('/', '.');
			className = className.substring(0, className.length() - 6);
			try
			{
				clazzs.add(Thread.currentThread().getContextClassLoader().loadClass(className));
			} catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception
	{
		Set<Class<?>> clazzs = findFileClass("com.liiwin.util");
		for (Class<?> clazz : clazzs)
		{
			System.out.println(clazz.getName());
			System.out.println(clazz.getAnnotations().length);
			if (clazz.getSuperclass() != null)
			{
				System.out.println(clazz.getSuperclass().getName());
			}
		}
	}
}
