package com.zhu.ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zhu.ssm.common.Constans;
import com.zhu.ssm.common.PageInfo;
import com.zhu.ssm.common.ResultBean;
import com.zhu.ssm.model.Order;
import com.zhu.ssm.service.OrderService;
@Controller
@RequestMapping(value = "/order")
public class OrderController
{
	@Autowired
	private OrderService	orderService;

	@RequestMapping(value = "findOrder", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public PageInfo findOrder(PageInfo pageInfo, Order order)
	{
		orderService.findOrderByParams(pageInfo, order);
		return pageInfo;
	}

	@RequestMapping(value = "delete", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResultBean delete(Order order)
	{
		ResultBean resultBean = new ResultBean();
		orderService.deleteOrder(order);
		resultBean.setFlag(Constans.SUCCESS);
		return resultBean;
	}

	@RequestMapping(value = "insert", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResultBean insert(Order order)
	{
		ResultBean resultBean = new ResultBean();
		orderService.insertOrder(order);
		resultBean.setFlag(Constans.SUCCESS);
		return resultBean;
	}
}
