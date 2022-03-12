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
import com.restaurant.zomato.validation.UsersRequestBodyValidation;
@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<Users>getAllUser(){
		List<Users> user = null;
		try {
			user = this.userService.getAllUser();
			if(user.size()==0)
				throw new Exception("not found any user");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return user;
	}
	@GetMapping("/users/{userId}")
	public Users getOneUser(@PathVariable long userId) {
		Users temp=null;
		try
		{
			UsersRequestBodyValidation.validateUserPhoneNumber(userId);
		    temp = userService.getOneUser(userId); 
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		return temp;
	}
		
	@PostMapping("/signup")
	public Users addUser(@RequestBody Users user) {
		
		Users temp=null;
		try
		{
			UsersRequestBodyValidation.validateUserPhoneNumber(user.getPhoneNumber());
		    temp = this.userService.addUser(user);
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		return temp;
	}
	
	@PutMapping("/users")
	public Users updateUser(@RequestBody Users user) {
		Users temp=null;
		try
		{
			UsersRequestBodyValidation.validateUserPhoneNumber(user.getPhoneNumber());
		    temp = this.userService.updateUser(user);
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		return temp;
	}

	
	@DeleteMapping("/users/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable long userId){
		try
		{
			UsersRequestBodyValidation.validateUserPhoneNumber(userId);
				this.userService.deleteUser(userId);
				return new ResponseEntity<String>( "user has been deleted successfully", HttpStatus.OK);
		}		
			catch(Exception e){
				return new ResponseEntity<String>("user has not been deleted", HttpStatus.NOT_FOUND);
				
		}
		
	
}
	
	
	@GetMapping("/placeorders/{phoneNumber}/{address}/{name}")
	public Orders getPlaceOrder(@PathVariable long phoneNumber,@PathVariable String address,@PathVariable String name) {
		Orders entity = null;
		try {
			UsersRequestBodyValidation.validatePlaceOrderField(phoneNumber, address, name);
			entity = this.userService.getOrderPlaced(phoneNumber, address, name);
			
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return entity;
		
	}
	

}
