package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Order;
import com.example.demo.repos.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public List<Order> getAllOrderInfo() {
		return orderRepository.findAll();
	}

	@Override
	public Order getOrderbyId(long id) {
		Optional<Order> optional = orderRepository.findById(id);
		Order order = null;
		if(optional.isPresent())
		{
			order = optional.get();
		}
		else
		{
			throw new RuntimeException("Order not found - > "+id);
		}
		return order;
	}

	@Override
	public void saveOrder(Order order) {
		this.orderRepository.save(order);
	}

}
