package xyz.zyzhu.spring.boot.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * <p>标题： 界面删除基础model</p>
 * <p>功能： </p>
 * <p>所属模块： boot</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月10日 下午2:22:04</p>
 * <p>类全名：xyz.zyzhu.spring.boot.model.BootStrapDeleteRequestModel</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class BootStrapDeleteRequestModel implements Serializable
{
	private static final long	serialVersionUID	= 1624936098457347127L;
	String						table;
	List<Map<String,Object>>	delRequest;

	public List<Map<String,Object>> getDelRequest()
	{
		return delRequest;
	}

	public void setDelRequest(List<Map<String,Object>> delRequest)
	{
		//setValue("delRequest", delRequest);
		this.delRequest = delRequest;
	}

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

	public String getTable()
	{
		return table;
	}

	public void setTable(String table)
	{
		//setValue("table", table);
		this.table = table;
	}
}
