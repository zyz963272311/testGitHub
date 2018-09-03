package xyz.zyzhu.spring.boot.datafile.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import com.liiwin.utils.StrUtil;
import xyz.zyzhu.spring.boot.model.DataimpgDef;
/**
 * <p>标题： 数据导入定义详情</p>
 * <p>功能： </p>
 * <p>所属模块： 数据导入定义详情</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月3日 下午8:55:48</p>
 * <p>类全名：xyz.zyzhu.spring.boot.datafile.domain.DataImportDetail</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class DataImportDetail implements Serializable
{
	private static final long	serialVersionUID	= -8293687658510579690L;
	private DataimpgDef			impgDef;
	List<Map<String,Object>>	impDatas;

	/**
	 * 获取表名
	 * @return
	 * 赵玉柱
	 */
	public String getTablename()
	{
		if (impgDef == null)
		{
			return null;
		}
		return impgDef.getTablename();
	}

	/**
	 * 获取所有的字段
	 * @return
	 * 赵玉柱
	 */
	public String getColumns()
	{
		if (impgDef == null)
		{
			return null;
		}
		return impgDef.getColumns();
	}

	/**
	 * 获取版本号
	 * @return
	 * 赵玉柱
	 */
	public String getVersion()
	{
		if (impgDef == null)
		{
			return null;
		}
		return StrUtil.obj2str(impgDef.getVersion(), "1.0");
	}

	/**
	 * 获取数据存盘类型
	 * @return
	 * 赵玉柱
	 */
	public int getDataSaveMode()
	{
		if (impgDef == null)
		{
			return 0;
		}
		return StrUtil.obj2int(impgDef.getSavemode(), 0);
	}

	/**
	 * 执行数据插入
	 * @return
	 * 赵玉柱
	 */
	public boolean isInsertMode()
	{
		int dataSaveMode = getDataSaveMode();
		return (dataSaveMode & 1) > 0;
	}

	/**
	 * 执行数据删除操作
	 * @return
	 * 赵玉柱
	 */
	public boolean isDeleteMode()
	{
		int dataSaveMode = getDataSaveMode();
		return (dataSaveMode & 2) > 0;
	}

	public DataimpgDef getImpgDef()
	{
		return impgDef;
	}

	public void setImpgDef(DataimpgDef impgDef)
	{
		this.impgDef = impgDef;
	}

	public List<Map<String,Object>> getImpDatas()
	{
		return impDatas;
	}

	public void setImpDatas(List<Map<String,Object>> impDatas)
	{
		this.impDatas = impDatas;
	}
}
