package xyz.zyzhu.spring.boot.model;

import java.io.Serializable;
import com.alibaba.fastjson.JSONArray;
/**
 * <p>标题： 界面更新回执pojo</p>
 * <p>功能： </p>
 * <p>所属模块： boot</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月11日 上午9:28:52</p>
 * <p>类全名：xyz.zyzhu.spring.boot.model.BootStrapUpdateResponseModel</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class BootStrapUpdateResponseModel implements Serializable
{
	private static final long	serialVersionUID	= 8807352824961685158L;
	JSONArray					resultValues;

	public JSONArray getResultValues()
	{
		return resultValues;
	}

	public void setResultValues(JSONArray resultValues)
	{
		//setValue("resultValues", resultValues);
		this.resultValues = resultValues;
	}
}
