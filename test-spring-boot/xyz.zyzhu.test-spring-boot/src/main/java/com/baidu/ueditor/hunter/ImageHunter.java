package com.baidu.ueditor.hunter;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import com.baidu.ueditor.PathFormat;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.MIMEType;
import com.baidu.ueditor.define.MultiState;
import com.baidu.ueditor.define.State;
import com.baidu.ueditor.upload.StorageManager;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月24日 下午1:07:10</p>
 * <p>类全名：xyz.zyzhu.spring.boot.baidu.ueditor.hunter.ImageHunter</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class ImageHunter
{
	private String			filename	= null;
	private String			savePath	= null;
	private String			rootPath	= null;
	private List<String>	allowTypes	= null;
	private long			maxSize		= -1L;
	private List<String>	filters		= null;

	public ImageHunter(Map<String,Object> conf)
	{
		this.filename = ((String) conf.get("filename"));
		this.savePath = ((String) conf.get("savePath"));
		this.rootPath = ((String) conf.get("rootPath"));
		this.maxSize = ((Long) conf.get("maxSize")).longValue();
		this.allowTypes = Arrays.asList((String[]) conf.get("allowFiles"));
		this.filters = Arrays.asList((String[]) conf.get("filter"));
	}

	public State capture(String[] list)
	{
		MultiState state = new MultiState(true);
		for (String source : list)
		{
			state.addState(captureRemoteData(source));
		}
		return state;
	}

	public State captureRemoteData(String urlStr)
	{
		HttpURLConnection connection = null;
		URL url = null;
		String suffix = null;
		try
		{
			url = new URL(urlStr);
			if (!(validHost(url.getHost())))
			{
				return new BaseState(false, 201);
			}
			connection = (HttpURLConnection) url.openConnection();
			connection.setInstanceFollowRedirects(true);
			connection.setUseCaches(true);
			if (!(validContentState(connection.getResponseCode())))
			{
				return new BaseState(false, 202);
			}
			suffix = MIMEType.getSuffix(connection.getContentType());
			if (!(validFileType(suffix)))
			{
				return new BaseState(false, 8);
			}
			if (!(validFileSize(connection.getContentLength())))
			{
				return new BaseState(false, 1);
			}
			String savePath = getPath(this.savePath, this.filename, suffix);
			String physicalPath = this.rootPath + savePath;
			State state = StorageManager.saveFileByInputStream(connection.getInputStream(), physicalPath);
			if (state.isSuccess())
			{
				state.putInfo("url", PathFormat.format(savePath));
				state.putInfo("source", urlStr);
			}
			return state;
		} catch (Exception e)
		{
		}
		return new BaseState(false, 203);
	}

	private String getPath(String savePath, String filename, String suffix)
	{
		return PathFormat.parse(savePath + suffix, filename);
	}

	private boolean validHost(String hostname)
	{
		return (!(this.filters.contains(hostname)));
	}

	private boolean validContentState(int code)
	{
		return (200 == code);
	}

	private boolean validFileType(String type)
	{
		return this.allowTypes.contains(type);
	}

	private boolean validFileSize(int size)
	{
		return (size < this.maxSize);
	}
}
