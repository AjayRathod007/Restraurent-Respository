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

import com.restaurant.zomato.entities.Items;
import com.restaurant.zomato.entities.Orders;
import com.restaurant.zomato.services.ItemService;
import com.restaurant.zomato.validation.UsersRequestBodyValidation;

@RestController
public class ItemController {
	@Autowired
	public ItemService itemService;
	
	
	@GetMapping("/items")
	public List<Items>getAllItem(){
		List<Items> temp = null;
		try {
			temp=this.itemService.getAllItem();
			if(temp.size()==0)
				throw new Exception("not found any Item");
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			
		}
		return temp;
	}
	
	@GetMapping("/items/{itemName}")
	public Items getOneItem(@PathVariable String itemName) {
		Items item = null;
		try {
			UsersRequestBodyValidation.validateItemName(itemName);
			item = this.itemService.getOneItem(itemName);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			
		}
		return item;
	}
	
	@PostMapping("/items")
	public Items addItem(@RequestBody Items item) {
		Items temp = null;
		try {
			UsersRequestBodyValidation.validateItemField(item.getRestraurentId(),item.getItemName(),item.getItemPrice());
			temp = this.itemService.addItem(item);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return temp;
		
	}
	
	@PutMapping("/items")
	public Items updateItem(@RequestBody Items item) {
		Items temp = null;
		try {
			UsersRequestBodyValidation.validateItemField(item.getRestraurentId(),item.getItemName(),item.getItemPrice());
			temp = this.itemService.updateItem(item);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return temp;
		
	}
	
	@DeleteMapping("/items/{itemName}")
	public ResponseEntity<HttpStatus> deleteItem(@PathVariable String itemName){
		try {
			UsersRequestBodyValidation.validateItemName(itemName);
			this.itemService.deleteItem(itemName);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			
		}


}

}
