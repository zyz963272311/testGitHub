package com.zhu.ssm.dao;

import java.util.List;

import com.zhu.ssm.model.Order;

public interface OrderDao {
	List<Order> findOrderByParams(Order order);
	void delete(Order order);
	void insert(Order order);
}
