package xyz.zyzhu.spring.boot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
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
import xyz.zyzhu.spring.boot.utils.PropertiesUtils;
import xyz.zyzhu.spring.boot.utils.RemoteUtils;
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
					final List<String> localServer = RemoteUtils.getLocalServer(req, resp);
					final String requestURI = req.getRequestURI();
					List<String> remoteServer2 = RemoteUtils.getRemoteServer(req, resp);
					String params = "remote=false";
					if (remoteServer2 != null && remoteServer2.size() > 0)
					{
						List<String> remoteServers = remoteServer2.stream().filter(s -> !localServer.contains(s)).collect(Collectors.toList());
						remoteServers.stream().forEach(s -> {
							try
							{
								if (!s.endsWith("/"))
								{
									s += "/";
								}
								s += requestURI + "/";
								String sendHttpPost = HttpClientUtil.sendHttpPost(s, params);
								logger.error("进行远程调用{}执行config重新装载完成：{}", s + params, sendHttpPost);
							} catch (Exception e)
							{
								logger.error("进行远程调用{}执行config重新装载失败：{}", s + params, e.getMessage());
							}
						});
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
