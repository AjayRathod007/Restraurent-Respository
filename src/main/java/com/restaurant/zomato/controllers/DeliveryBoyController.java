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

import com.restaurant.zomato.entities.DeliveryBoy;
import com.restaurant.zomato.services.DeliveryBoyService;
import com.restaurant.zomato.validation.UsersRequestBodyValidation;

@RestController
public class DeliveryBoyController {
	@Autowired
	private DeliveryBoyService deliveryBoyService;
	
	@GetMapping("/deliveries")
	public List<DeliveryBoy>getAllDeliveryBoy(){
		List<DeliveryBoy> temp=null;
		try {
			temp=this.deliveryBoyService.getAllDeliveryBoy();
			throw new Exception("delivery Boy not found");
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return temp;
	}
	@GetMapping("/deliveries/{deliveryBoyId}")
	public DeliveryBoy getOneDeliveryBoy(@PathVariable int deliveryBoyId) {
		DeliveryBoy boy = null;
		try{
			 UsersRequestBodyValidation.validateDeliveryBoyId(deliveryBoyId);
			 boy = this.deliveryBoyService.getOneDeliveryBoy(deliveryBoyId);
	
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return boy;
	}
	
	@PostMapping("/deliveries")
	public DeliveryBoy addDeliveryBoy(@RequestBody DeliveryBoy deliveryBoy) {
		DeliveryBoy boy = null;
		try{
			 UsersRequestBodyValidation.validateDeliveryBoyField(deliveryBoy.getDeliveryBoyId(),deliveryBoy.getRestaurantId(),deliveryBoy.getAddress());
			 boy=this.deliveryBoyService.addDeliveryBoy(deliveryBoy);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			
		}
		
		
		return boy;
		
	}
	
	@PutMapping("/deliveries")
	public DeliveryBoy updateDeliveryBoy(@RequestBody DeliveryBoy deliveryBoy) {
		DeliveryBoy boy = null;
		try{
			 UsersRequestBodyValidation.validateDeliveryBoyField(deliveryBoy.getRestaurantId(),deliveryBoy.getDeliveryBoyId(),deliveryBoy.getAddress());
			 boy=this.deliveryBoyService.updateDeliveryBoy(deliveryBoy);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			
		}
		
		
		return boy;
		
	}
	
	@DeleteMapping("/deliveries/{deliveriesId}")
	public ResponseEntity<HttpStatus> deleteDeliveryBoy(@PathVariable int deliveryBoyId){
		try {
			UsersRequestBodyValidation.validateDeliveryBoyId(deliveryBoyId);
			this.deliveryBoyService.deleteDeliveryBoy(deliveryBoyId);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			
		}


}

}
