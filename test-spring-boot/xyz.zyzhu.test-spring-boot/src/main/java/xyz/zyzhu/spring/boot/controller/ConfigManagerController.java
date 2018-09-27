package xyz.zyzhu.spring.boot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import org.plutext.jaxb.svg11.Mpath;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
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
import org.springframework.web.servlet.ModelAndView;
import com.liiwin.config.BasConfig;
import xyz.zyzhu.spring.boot.utils.PropertiesUtils;
@EnableAutoConfiguration
@RestController
@RequestMapping("/configManager")
public class ConfigManagerController
{
	/**
	 * 获取页面
	 * @return
	 * 赵玉柱
	 */
	@RequestMapping("/get")
	public ModelAndView get()
	{
		ModelAndView view = new ModelAndView("createDatabase");
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
		for (Entry<Object,Object> entry : properties.entrySet())
		{
			Object key = entry.getKey();
			Object value = entry.getValue();
			Map<String,Object> map = new HashMap<>();
			map.put("key", key);
			map.put("value", value);
		}
		return list;
	}

	/**
	 * config配置重新装载
	 * @return
	 * 赵玉柱
	 */
	@RequestMapping("/reloadProperties")
	@ResponseBody
	public Map<String,Object> reloadProperties()
	{
		Map<String,Object> map = new HashMap<>();
		return map;
	}
}
