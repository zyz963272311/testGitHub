package com.zhu.ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zhu.ssm.common.Constans;
import com.zhu.ssm.common.PageInfo;
import com.zhu.ssm.common.ResultBean;
import com.zhu.ssm.model.Goods;
import com.zhu.ssm.service.GoodsService;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年6月8日 上午10:03:14</p>
 * <p>类全名：com.zhu.ssm.controller.GoodsController</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Controller
@RequestMapping(value = "/goods")
public class GoodsController
{
	@Autowired
	private GoodsService	goodsService;

	@RequestMapping(value = "findGoods", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public PageInfo findGoods(PageInfo pageInfo, Goods goods)
	{
		goodsService.findGood(pageInfo, goods);
		return pageInfo;
	}

	@RequestMapping(value = "delete", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResultBean delete(Goods goods)
	{
		ResultBean resultBean = new ResultBean();
		goodsService.deleteGood(goods);
		resultBean.setFlag(Constans.SUCCESS);
		return resultBean;
	}

	@RequestMapping(value = "update", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResultBean update(Goods goods)
	{
		ResultBean resultBean = new ResultBean();
		goodsService.updateGood(goods);
		resultBean.setFlag(Constans.SUCCESS);
		return resultBean;
	}

	@RequestMapping(value = "insert", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResultBean insert(Goods goods)
	{
		ResultBean resultBean = new ResultBean();
		goodsService.createGood(goods);
		resultBean.setFlag(Constans.SUCCESS);
		return resultBean;
	}
}
