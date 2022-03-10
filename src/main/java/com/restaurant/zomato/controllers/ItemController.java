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
import com.restaurant.zomato.services.ItemService;

@RestController
public class ItemController {
	@Autowired
	public ItemService itemService;
	
	
	@GetMapping("/items")
	public List<Items>getAllItem(){
		return this.itemService.getAllItem();
	}
	
	@GetMapping("/items/{itemName}")
	public Items getOneItem(@PathVariable String itemName) {
		return this.itemService.getOneItem(itemName);
	}
	
	@PostMapping("/items")
	public Items addItem(@RequestBody Items item) {
		return this.itemService.addItem(item);
		
	}
	
	@PutMapping("/items")
	public Items updateItem(@RequestBody Items item) {
		return this.itemService.updateItem(item);
		
	}
	
	@DeleteMapping("/items/{itemName}")
	public ResponseEntity<HttpStatus> deleteItem(@PathVariable String itemName){
		try {
			this.itemService.deleteItem(itemName);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			
		}


}

}
