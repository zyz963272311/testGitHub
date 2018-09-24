package xyz.zyzhu.spring.boot.model;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月24日 下午7:01:31</p>
 * <p>类全名：xyz.zyzhu.spring.boot.model.CkeditorFileUploadResponse</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class CkeditorFileUploadResponse extends BasObject
{
	private static final long	serialVersionUID	= -3498971423702591657L;
	private Integer				uploaded;
	private String				fileName;
	private String				url;

	public Integer getUploaded()
	{
		return uploaded;
	}

	public void setUploaded(Integer uploaded)
	{
		//setValue("uploaded", uploaded);
		this.uploaded = uploaded;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		//setValue("fileName", fileName);
		this.fileName = fileName;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		//setValue("url", url);
		this.url = url;
	}
}
