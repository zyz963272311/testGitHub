package xyz.zyzhu.spring.boot.model;

import java.io.Serializable;
/**
 * <p>标题： boot回执参数</p>
 * <p>功能： </p>
 * <p>所属模块： boot</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月7日 下午4:05:02</p>
 * <p>类全名：xyz.zyzhu.spring.boot.model.BootStrapResponseModel</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
import java.util.List;
import java.util.Map;
import com.liiwin.utils.StrUtil;
public class BootStrapQueryResponseModel implements Serializable
{
	private static final long	serialVersionUID	= -263506411624342890L;
	//公共部分数据
	private int					totabSize;									//总记录数
	private int					pagefrom;									//页码
	private int					pagesize;									//每页行数
	List<Map<String,Object>>	rows;										//查询结果数据

	/**
	 * 设置返回数据的基本信息
	 * @param request
	 * @return
	 * 赵玉柱
	 */
	public BootStrapQueryResponseModel withPageMessage(BootStrapQueryRequestModel request)
	{
		if (request != null)
		{
			int pagesize = StrUtil.obj2int(request.getPagesize());
			if (pagesize > 0)
			{
				setPagesize(pagesize);
			}
			int pagefrom = StrUtil.obj2int(request.getPagefrom());
			if (pagefrom <= 0)
			{
				pagefrom = 1;
			}
			setPagefrom(pagefrom);
		}
		return this;
	}

	/**
	 * 设置数据信息
	 * @param queryData
	 * @return
	 * 赵玉柱
	 */
	public BootStrapQueryResponseModel withRows(List<Map<String,Object>> rows)
	{
		setRows(rows);
		return this;
	}

	public BootStrapQueryResponseModel withTotalSize(int totalsize)
	{
		setTotabSize(totalsize);
		return this;
	}

	public int getTotabSize()
	{
		return totabSize;
	}

	public void setTotabSize(int totabSize)
	{
		//setValue("totabSize", totabSize);
		this.totabSize = totabSize;
	}

	public int getPagefrom()
	{
		return pagefrom;
	}

	public void setPagefrom(int pagefrom)
	{
		//setValue("pagefrom", pagefrom);
		this.pagefrom = pagefrom;
	}

	public int getPagesize()
	{
		return pagesize;
	}

	public void setPagesize(int pagesize)
	{
		//setValue("pagesize", pagesize);
		this.pagesize = pagesize;
	}

	public void setRows(List<Map<String,Object>> rows)
	{
		//setValue("queryData", queryData);
		this.rows = rows;
	}

	public List<Map<String,Object>> getRows()
	{
		return rows;
	}
}
