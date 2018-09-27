package xyz.zyzhu.spring.boot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.liiwin.createdb.CreateDatabase;
import com.liiwin.utils.StrUtil;
import xyz.zyzhu.spring.boot.constance.CreateDatabaseConstance;
import xyz.zyzhu.spring.boot.utils.PropertiesUtils;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月27日 下午2:39:12</p>
 * <p>类全名：xyz.zyzhu.spring.boot.controller.CreateDatabaseController</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@EnableAutoConfiguration
@RestController
@RequestMapping("/createDatabase")
public class CreateDatabaseController implements CreateDatabaseConstance
{
	/**
	 * 获取页面
	 * @param reqt
	 * @param resp
	 * @return
	 * 赵玉柱
	 */
	@RequestMapping(path = "/get")
	public ModelAndView get(HttpServletRequest reqt, HttpServletResponse resp)
	{
		ModelAndView view = new ModelAndView("createDatabase");
		return view;
	}

	/**
	 * 页面初始化
	 * @param reqt
	 * @param resp
	 * @return
	 * 赵玉柱
	 */
	@RequestMapping(path = "/getDatabases")
	public List<Map<String,Object>> getDatabases(HttpServletRequest reqt, HttpServletResponse resp)
	{
		List<Map<String,Object>> result = new ArrayList<>();
		String databases = PropertiesUtils.getPropValue("databases");
		if (StrUtil.isNoStrTrimNull(databases))
		{
			String[][] split2 = StrUtil.split(databases, ',', ':');
			for (String[] split1 : split2)
			{
				if (split1 != null && split1.length >= 1)
				{
					String dbKey = split1[0];
					String dbName = split1.length > 1 ? split1[1] : dbKey;
					Map<String,Object> map = new HashMap<>();
					map.put(DB_KEY, dbKey);
					map.put(DB_NAME, dbName);
					result.add(map);
				}
			}
		}
		return result;
	}

	/**
	 * 生成数据库
	 * @param reqt
	 * @param resp
	 * @return
	 * 赵玉柱
	 */
	@RequestMapping(path = "/create", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String,Object> create(HttpServletRequest reqt, HttpServletResponse resp)
	{
		String databases = reqt.getParameter("databases");
		Map<String,Object> result = new HashMap<>();
		result.put(STATUS_KEY, STATUS_SUSSESS);
		if (StrUtil.isStrTrimNull(databases))
		{
			return result;
		}
		String[] databaseArray = StrUtil.split(databases, ',');
		try
		{
			CreateDatabase.createDatabase(databaseArray);
		} catch (Exception e)
		{
			result.put(STATUS_KEY, STATUS_ERROR);
			result.put(MESSAGE_KEY, e.getMessage());
		}
		return result;
	}
}
