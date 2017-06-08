package com.zhu.ssm.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhu.ssm.common.PageInfo;
import com.zhu.ssm.dao.OrderDao;
import com.zhu.ssm.model.Order;
import com.zhu.ssm.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService {
	Log					logger	= LogFactory.getLog(OrderServiceImpl.class);
	@Autowired
	private OrderDao	orderDao;
	@Override
	public PageInfo findOrderByParams(PageInfo pageInfo, Order order) {
		Page<?> page = PageHelper.startPage(pageInfo.getPageNumber(), pageInfo.getPageSize());
		pageInfo.setRows(orderDao.findOrderByParams(order));
		pageInfo.setTotal(page.getTotal());
		return pageInfo;
	}

	@Override
	public void insertOrder(Order order) {
		orderDao.insert(order);
	}

	@Override
	public void deleteOrder(Order order) {
		orderDao.delete(order);
	}

}
