package xyz.zyzhu.spring.boot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.liiwin.config.BasConfig;
import com.liiwin.http.HttpClientUtil;
import com.liiwin.utils.ServerUtils;
import com.liiwin.utils.StrUtil;
import xyz.zyzhu.spring.boot.utils.PropertiesUtils;
/**
 * <p>标题： 配置管理</p>
 * <p>功能： </p>
 * <p>所属模块： boot</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月27日 下午9:08:56</p>
 * <p>类全名：xyz.zyzhu.spring.boot.controller.ConfigManagerController</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@EnableAutoConfiguration
@RestController
@RequestMapping("/configManager")
public class ConfigManagerController
{
	private static Logger	logger		= LoggerFactory.getLogger(ConfigManagerController.class);
	private static Lock		loadLock	= new ReentrantLock();

	/**
	 * 获取页面
	 * @return
	 * 赵玉柱
	 */
	@RequestMapping("/get")
	public ModelAndView get()
	{
		ModelAndView view = new ModelAndView("configManager");
		return view;
	}

	/**
	 * 获取config配置
	 * @param types 类型  root,boot
	 * @return
	 * 赵玉柱
	 */
	@RequestMapping("/getProperties")
	@ResponseBody
	public List<Map<String,Object>> getProperties(String types)
	{
		List<Map<String,Object>> list = new ArrayList<>();
		Properties properties = null;
		if ("boot".equals(types))
		{
			properties = PropertiesUtils.getProperties();
		} else
		{
			properties = BasConfig.getProperties();
		}
		if (properties != null)
		{
			for (Entry<Object,Object> entry : properties.entrySet())
			{
				Object key = entry.getKey();
				Object value = entry.getValue();
				Map<String,Object> map = new HashMap<>();
				map.put("key", key);
				map.put("value", value);
				list.add(map);
			}
		}
		return list;
	}

	/**
	 * config配置重新装载
	 * @return
	 * 赵玉柱
	 */
	@RequestMapping(path = "/reloadProperties", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String,Object> reloadProperties(HttpServletRequest req, HttpServletResponse resp, @RequestParam(name = "remote", defaultValue = "false") Boolean remote)
	{
		Map<String,Object> map = new HashMap<>();
		if (loadLock.tryLock())
		{
			try
			{
				//先重新加载rootbas的config
				BasConfig.loadConfig(true);
				//然后再重新加载Boot的config
				PropertiesUtils.loadZKProperties(true);
				if (remote)
				{
					String requestURI = req.getRequestURI();
					String scheme = req.getScheme();//http
					String serverName = req.getServerName();//localhost
					int serverPort = req.getServerPort();//8081
					String contextPath = req.getContextPath();//项目名称
					String remoteServers = PropertiesUtils.getPropValue("remote-server");
					String localServPath = scheme + "://" + serverName + ":" + serverPort + "/";
					String hostIP = ServerUtils.getHostIP();
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
					String params = "remote=false";
					if (StrUtil.isNoStrTrimNull(remoteServers))
					{
						String[] remoteServerArray = StrUtil.split(remoteServers, ';');
						for (String remoteServer : remoteServerArray)
						{
							if (!remoteServer.endsWith("/"))
							{
								remoteServer += "/";
							}
							//当前地址及本机地址
							if (remoteServer.indexOf(localServPath) >= 0 || remoteServer.indexOf(lodalAddr) >= 0 || remoteServer.indexOf(localHost1) >= 0 || remoteServer.indexOf(localHost2) >= 0)
							{
								continue;
							}
							remoteServer += requestURI;
							try
							{
								String sendHttpPost = HttpClientUtil.sendHttpPost(remoteServer, params);
								logger.error("进行远程调用{}执行config重新装载完成：{}", remoteServer + params, sendHttpPost);
							} catch (Exception e)
							{
								logger.error("进行远程调用{}执行config重新装载失败：{}", remoteServer + params, e.getMessage());
							}
						}
					}
				}
			} finally
			{
				loadLock.unlock();
			}
		} else
		{
			map.put("status", "error");
			map.put("message", "已存在相同操作，请取消此操作或稍后重试");
		}
		return map;
	}
}
