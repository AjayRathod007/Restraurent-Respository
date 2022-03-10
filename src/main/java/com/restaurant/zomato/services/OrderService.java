package com.restaurant.zomato.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.zomato.dao.OrderDao;
import com.restaurant.zomato.entities.Orders;

@Service
public class OrderService {
	
	@Autowired
	public OrderDao orderDao;

	public List<Orders> getAllOrder() {
		
		return orderDao.findAll();
	}

	public Orders getOneOrder(int orderId) {
	
		return orderDao.getById(orderId);
	}

	public Orders addOrder(Orders order) {
		orderDao.save(order);
		return order;
	}

	public Orders updateOrder(Orders order) {
		orderDao.save(order);
		return order;
		
	}

	public void deleteOrder(int orderId) {
		Orders entity=orderDao.getById(orderId);
		orderDao.delete(entity);
		
	}


}
