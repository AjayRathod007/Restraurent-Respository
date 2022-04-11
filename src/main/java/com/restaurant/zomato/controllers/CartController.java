package com.restaurant.zomato.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.zomato.dto.CartResponseBody;
import com.restaurant.zomato.entities.Items;
import com.restaurant.zomato.services.CartService;
import com.restaurant.zomato.validation.CartRequestBodyValidation;
import com.restaurant.zomato.validation.UsersRequestBodyValidation;

@RestController
@CrossOrigin(origins= {"http://localhost:3000"})
public class CartController {
	
	 Logger logger = LoggerFactory.getLogger(CartController.class);
	 
	@Autowired
	private CartService cartService;
	
	@PostMapping("/addtocart/{phoneNumber}")
	public ResponseEntity<?> addCart(@PathVariable long phoneNumber, @RequestBody List<Items>selectedItem ) throws Exception
	{
		CartResponseBody  CartResponseBody = null;
	   
		try {
			
			CartRequestBodyValidation.checkItemsList(selectedItem);
			CartResponseBody=cartService.saveCart(phoneNumber,selectedItem);
			return new ResponseEntity<>(CartResponseBody, HttpStatus.ACCEPTED);
		}catch(Exception e)
		{
			logger.error("Error : {}", e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



     @GetMapping("/getcartitem/{phoneNumber}")
     public List<Items>getAllSelectedItemByPhoneNumber(@PathVariable long phoneNumber){
 		List<Items> temp = null;
 		try {
 			
 
 			UsersRequestBodyValidation.validateUserPhoneNumber(phoneNumber);
 			temp=this.cartService.getAllItemByPhoneNumber(phoneNumber);
 		}catch(Exception e){
 			System.out.println(e.getMessage());
 			
 		}
 		return temp;
 	}

}