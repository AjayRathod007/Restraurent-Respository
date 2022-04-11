package com.restaurant.zomato.controllers;

import java.util.List;

import com.restaurant.zomato.dto.OrderRequestBody;
import com.restaurant.zomato.dto.PlacedOrder;
import com.restaurant.zomato.dto.PreviewOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.zomato.entities.Cart;
import com.restaurant.zomato.entities.UserOrders;
import com.restaurant.zomato.services.OrderService;
import com.restaurant.zomato.validation.UsersRequestBodyValidation;

@RestController
@CrossOrigin(origins= {"http://localhost:3000"})
public class OrderController {
	@Autowired
	private OrderService orderService;

	@GetMapping("/orders")
	public List<UserOrders> getAllOrder() {
		List<UserOrders> temp = null;
		try {
			temp = this.orderService.getAllOrder();
			if (temp.size() == 0)
				throw new Exception("not found any Order");

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
		return temp;
	}

	@GetMapping("/orders/{orderId}")
	public UserOrders getOneOrder(@PathVariable int orderId) {
		UserOrders temp = null;
		try {
			UsersRequestBodyValidation.validateOrderId(orderId);
			temp = this.orderService.getOneOrder(orderId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return temp;
	}

	@PostMapping("/orders")
	public UserOrders addOrder(@RequestBody UserOrders order) {
		return this.orderService.addOrder(order);

	}

	@PutMapping("/orders")
	public UserOrders updateOrder(@RequestBody UserOrders order) {
		return this.orderService.updateOrder(order);

	}

	@DeleteMapping("/orders/{orderId}")
	public ResponseEntity<HttpStatus> deleteOrder(@PathVariable int orderId) {
		try {
			this.orderService.deleteOrder(orderId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@PostMapping("order/placeorder")
	public ResponseEntity<UserOrders> confirmOrder(@RequestBody OrderRequestBody orderRequestBody){
		UserOrders pp;
		try {
			pp = orderService.confirmOrder(orderRequestBody);
			return new ResponseEntity<UserOrders>(pp,HttpStatus.OK);
			} catch (Exception e) {
				
				System.out.println("Exception occured : "+e.getMessage());
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			}
		}
	}



//	@PostMapping("/placedOrder")
//	public ResponseEntity<PlacedOrder> placedOrder(@RequestBody PreviewOrder previewOrder) {
//		PlacedOrder pp;
//		try {
//			pp = orderService.placeOrder(previewOrder);
//			return new ResponseEntity<PlacedOrder>(pp,HttpStatus.OK);
//		} catch (Exception e) {
//			
//			System.out.println("Exception occured : "+e.getMessage());
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}}


