package com.baidu.ueditor.upload;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.baidu.ueditor.define.State;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月24日 下午1:05:55</p>
 * <p>类全名：xyz.zyzhu.spring.boot.baidu.ueditor.upload.Uploader</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Uploader
{
	private HttpServletRequest	request	= null;
	private Map<String,Object>	conf	= null;

	public Uploader(HttpServletRequest request, Map<String,Object> conf)
	{
		this.request = request;
		this.conf = conf;
	}

	public final State doExec()
	{
		String filedName = (String) this.conf.get("fieldName");
		State state = null;
		if ("true".equals(this.conf.get("isBase64")))
			state = Base64Uploader.save(this.request.getParameter(filedName), this.conf);
		else
		{
			state = BinaryUploader.save(this.request, this.conf);
		}
		return state;
	}
}
