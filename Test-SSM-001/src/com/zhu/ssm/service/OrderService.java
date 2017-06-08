package com.zhu.ssm.service;

import com.zhu.ssm.common.PageInfo;
import com.zhu.ssm.model.Order;

public interface OrderService {
	PageInfo findOrderByParams(PageInfo pageInfo,Order order);
	void insertOrder(Order order);
	void deleteOrder(Order order);
}
