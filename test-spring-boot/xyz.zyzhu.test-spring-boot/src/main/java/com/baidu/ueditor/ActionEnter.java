package com.baidu.ueditor;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.baidu.ueditor.define.ActionMap;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.State;
import com.baidu.ueditor.hunter.FileManager;
import com.baidu.ueditor.hunter.ImageHunter;
import com.baidu.ueditor.upload.Uploader;
/**
 * 
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月24日 下午1:21:38</p>
 * <p>类全名：xyz.zyzhu.spring.boot.baidu.ueditor.ActionEnter</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class ActionEnter
{
	private HttpServletRequest	request			= null;
	private String				rootPath		= null;
	private String				contextPath		= null;
	private String				actionType		= null;
	private ConfigManager		configManager	= null;

	public ActionEnter(HttpServletRequest request, String rootPath)
	{
		this.request = request;
		this.rootPath = rootPath;
		this.actionType = request.getParameter("action");
		this.contextPath = request.getContextPath();
		this.configManager = ConfigManager.getInstance(this.rootPath, this.contextPath, request.getRequestURI());
	}

	public String exec()
	{
		String callbackName = this.request.getParameter("callback");
		if (callbackName != null)
		{
			if (!(validCallbackName(callbackName)))
			{
				return new BaseState(false, 401).toJSONString();
			}
			return callbackName + "(" + invoke() + ");";
		}
		return invoke();
	}

	public String invoke()
	{
		if ((this.actionType == null) || (!(ActionMap.mapping.containsKey(this.actionType))))
		{
			return new BaseState(false, 101).toJSONString();
		}
		if ((this.configManager == null) || (!(this.configManager.valid())))
		{
			return new BaseState(false, 102).toJSONString();
		}
		State state = null;
		int actionCode = ActionMap.getType(this.actionType);
		Map conf = null;
		switch (actionCode)
		{
		case 0:
			return this.configManager.getAllConfig().toString();
		case 1:
		case 2:
		case 3:
		case 4:
			conf = this.configManager.getConfig(actionCode);
			state = new Uploader(this.request, conf).doExec();
			break;
		case 5:
			conf = this.configManager.getConfig(actionCode);
			String[] list = this.request.getParameterValues((String) conf.get("fieldName"));
			state = new ImageHunter(conf).capture(list);
			break;
		case 6:
		case 7:
			conf = this.configManager.getConfig(actionCode);
			int start = getStartIndex();
			state = new FileManager(conf).listFile(start);
		}
		return state.toJSONString();
	}

	public int getStartIndex()
	{
		String start = this.request.getParameter("start");
		try
		{
			return Integer.parseInt(start);
		} catch (Exception e)
		{
		}
		return 0;
	}

	public boolean validCallbackName(String name)
	{
		return (name.matches("^[a-zA-Z_]+[\\w0-9_]*$"));
	}
}