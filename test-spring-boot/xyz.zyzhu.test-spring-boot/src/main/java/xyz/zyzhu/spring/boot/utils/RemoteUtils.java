package xyz.zyzhu.spring.boot.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.liiwin.utils.ServerUtils;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： 用于远程调用与更新数据，主要用于清理缓存使用</p>
 * <p>功能： </p>
 * <p>所属模块： boot</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月29日 上午11:14:18</p>
 * <p>类全名：xyz.zyzhu.spring.boot.utils.RemoteUtils</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class RemoteUtils
{
	public static List<String> getRemoteServer(HttpServletRequest req, HttpServletResponse resp)
	{
		String remoteServers = PropertiesUtils.getPropValue("remote-server");
		List<String> result = new ArrayList<>();
		if (StrUtil.isNoStrTrimNull(remoteServers))
		{
			String[] split = remoteServers.split(";");
			String contextPath = req.getContextPath();
			for (String serv : split)
			{
				if (!serv.endsWith("/"))
				{
					serv += "/";
				}
				if (StrUtil.isNoStrTrimNull(contextPath))
				{
					serv += contextPath + "/";
				}
				result.add(serv);
			}
		}
		return result;
	}

	public static List<String> getLocalServer(HttpServletRequest req, HttpServletResponse resp)
	{
		String hostIP = ServerUtils.getHostIP();
		String scheme = req.getScheme();//http
		String serverName = req.getServerName();//localhost
		int serverPort = req.getServerPort();//8081
		String contextPath = req.getContextPath();//项目名称
		String localServPath = scheme + "://" + serverName + ":" + serverPort + "/";
		String lodalAddr = (hostIP != null) ? (scheme + "://" + hostIP + ":" + serverPort + "/") : null;
		String localHost1 = scheme + "://127.0.0.1:" + serverPort + "/";
		String localHost2 = scheme + "://localhost:" + serverPort + "/";
		if (StrUtil.isNoStrTrimNull(contextPath))
		{
			localServPath += contextPath + "/";
			localHost1 += contextPath + "/";
			localHost2 += contextPath + "/";
			if (lodalAddr != null)
			{
				lodalAddr += contextPath + "/";
			}
		}
		List<String> result = Arrays.asList(localServPath, localHost1, localHost2);
		return result;
	}
}
