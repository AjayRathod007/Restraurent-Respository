package com.restaurant.zomato.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.zomato.entities.Orders;
import com.restaurant.zomato.services.OrderService;

@RestController
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/orders")
	public List<Orders>getAllOrder(){
		return this.orderService.getAllOrder();
	}
	
	@GetMapping("/orders/{orderId}")
	public Orders getOneOrder(@PathVariable int orderId) {
		return this.orderService.getOneOrder(orderId);
	}
	
	@PostMapping("/orders")
	public Orders addOrder(@RequestBody Orders order) {
		return this.orderService.addOrder(order);
		
	}
	
	@PutMapping("/orders")
	public Orders updateOrder(@RequestBody Orders order) {
		return this.orderService.updateOrder(order);
		
	}
	
	@DeleteMapping("/orders/{orderId}")
	public ResponseEntity<HttpStatus> deleteOrder(@PathVariable int orderId){
		try {
			this.orderService.deleteOrder(orderId);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			
		}


}

}
