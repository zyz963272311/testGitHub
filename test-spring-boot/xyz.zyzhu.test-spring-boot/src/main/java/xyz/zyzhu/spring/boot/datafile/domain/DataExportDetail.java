package xyz.zyzhu.spring.boot.datafile.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import xyz.zyzhu.spring.boot.model.DataexpgDef;
/**
 * <p>标题： 数据导出定义明细</p>
 * <p>功能： </p>
 * <p>所属模块： boot</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年8月29日 下午3:44:29</p>
 * <p>类全名：xyz.zyzhu.spring.boot.datafile.export.DataExportDetail</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class DataExportDetail implements Serializable
{
	private static final long			serialVersionUID	= -7828774921773832683L;
	//导出明细定义
	private DataexpgDef					dataexpDef;
	//导出数据
	private List<Map<String,Object>>	exportData;

	public DataexpgDef getDataexpDef()
	{
		return dataexpDef;
	}

	public void setDataexpDef(DataexpgDef dataexpDef)
	{
		this.dataexpDef = dataexpDef;
	}

	public List<Map<String,Object>> getExportData()
	{
		return exportData;
	}

	public void setExportData(List<Map<String,Object>> exportData)
	{
		this.exportData = exportData;
	}
}
