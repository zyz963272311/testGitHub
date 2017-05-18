package monitor;

import java.io.File;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年11月23日 下午7:59:53</p>
 * <p>类全名：monitor.ZJPFileMonitor</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class ZJPFileMonitor
{
	FileAlterationMonitor	monitor	= null;

	public ZJPFileMonitor(long interval) throws Exception
	{
		monitor = new FileAlterationMonitor(interval);
	}

	public void monitor(String path, FileAlterationListener listener)
	{
		FileAlterationObserver observer = new FileAlterationObserver(new File(path));
		monitor.addObserver(observer);
		observer.addListener(listener);
	}

	public void start() throws Exception
	{
		monitor.start();
	}

	public void stop() throws Exception
	{
		monitor.stop();
	}

	public static void main(String[] args) throws Exception
	{
		ZJPFileMonitor m = new ZJPFileMonitor(5000);
		m.monitor("D:/mylib", new ZJPFileListener());
		m.start();
	}
}
