package com.liiwin.monitor;

import java.io.File;
import java.text.SimpleDateFormat;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;
/**
 * <p>标题： 文件系统监听</p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年11月23日 下午7:53:53</p>
 * <p>类全名：monitor.ZJPFileListener</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class ZJPFileListener implements FileAlterationListener
{
	ZJPFileMonitor	monitor	= null;

	@Override
	public void onDirectoryChange(File directory)
	{
		System.out.println("onDirectoryChange\t" + directory.getName());
	}

	@Override
	public void onDirectoryCreate(File directory)
	{
		System.out.println("onDirectoryCreate\t" + directory.getName());
	}

	@Override
	public void onDirectoryDelete(File directory)
	{
		System.out.println("onDirectoryDelete\t" + directory.getName());
	}

	@Override
	public void onFileChange(File directory)
	{
		System.out.println("onFileChange\t" + directory.getName());
	}

	@Override
	public void onFileCreate(File directory)
	{
		System.out.println("onFileCreate\t" + directory.getName());
	}

	@Override
	public void onFileDelete(File directory)
	{
		System.out.println("onFileDelete\t" + directory.getName());
	}

	@Override
	public void onStart(FileAlterationObserver observer)
	{
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String TimeString = time.format(new java.util.Date());
		System.out.println("开始扫描" + TimeString);
	}

	@Override
	public void onStop(FileAlterationObserver observer)
	{
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String TimeString = time.format(new java.util.Date());
		System.out.println("结束扫描" + TimeString);
	}
}
