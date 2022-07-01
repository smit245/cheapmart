package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Order;

public interface OrderService {

	List<Order> getAllOrderInfo();
	Order getOrderbyId(long id);
	void saveOrder(Order Order);
}
