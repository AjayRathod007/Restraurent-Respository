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
import com.restaurant.zomato.entities.Users;
import com.restaurant.zomato.services.UserService;
@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<Users>getAllUser(){
		return this.userService.getAllUser();
	}
	@GetMapping("/users/{userId}")
	public Users getOneUser(@PathVariable long userId) {
		return this.userService.getOneUser(userId);
	}
	
	@PostMapping("/signup")
	public Users addUser(@RequestBody Users user) {
		return this.userService.addUser(user);
		
	}
	
	@PutMapping("/users")
	public Users updateUser(@RequestBody Users user) {
		return this.userService.updateUser(user);
		
	}
	
	@DeleteMapping("/users/{userId}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable long userId){
		try {
			this.userService.deleteUser(userId);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			
		}


}
	@GetMapping("/placeorders/{phoneNumber}/{address}/{name}")
	public Orders getPlaceOrder(@PathVariable long phoneNumber,@PathVariable String address,@PathVariable String name) {
		return userService.getOrderPlaced(phoneNumber, address, name);
		
	}

}
