package xyz.zyzhu.spring.boot.model;

import javax.persistence.Table;
import com.liiwin.annotation.FieldDef;
/**
 * <p>标题： 数据导出定义 子表</p>
 * <p>功能： </p>
 * <p>所属模块： 数据导出定义子表</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年8月29日 下午3:28:43</p>
 * <p>类全名：xyz.zyzhu.spring.boot.model.DataexpgDef</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Table(name = "dataexpg")
public class DataexpgDef extends BasModel
{
	private static final long	serialVersionUID	= 6135398017810960366L;
	@FieldDef(defaultValue = "AutoCode:expg________")
	private String				id;
	@FieldDef(notEmpty = true)
	private String				mid;
	@FieldDef(notEmpty = true)
	private String				rno;
	@FieldDef(notEmpty = true)
	private String				tablename;
	@FieldDef()
	private String				querycolumns;
	@FieldDef()
	private String				remark;
	@FieldDef()
	private String				queryfilter;
	@FieldDef()
	private Integer				flags;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		setValue("id", id);
	}

	public String getMid()
	{
		return mid;
	}

	public void setMid(String mid)
	{
		setValue("mid", mid);
	}

	public String getRno()
	{
		return rno;
	}

	public void setRno(String rno)
	{
		setValue("rno", rno);
	}

	public String getTablename()
	{
		return tablename;
	}

	public String getQuerycolumns()
	{
		return querycolumns;
	}

	public void setQuerycolumns(String querycolumns)
	{
		setValue("querycolumns", querycolumns);
	}

	public void setTablename(String tablename)
	{
		setValue("tablename", tablename);
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		setValue("remark", remark);
	}

	public String getQueryfilter()
	{
		return queryfilter;
	}

	public void setQueryfilter(String queryfilter)
	{
		setValue("queryfilter", queryfilter);
	}

	public Integer getFlags()
	{
		return flags;
	}

	public void setFlags(Integer flags)
	{
		setValue("flags", flags);
	}
}
