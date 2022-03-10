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

@RestController
public class DeliveryBoyController {
	@Autowired
	private DeliveryBoyService deliveryBoyService;
	
	@GetMapping("/deliveries")
	public List<DeliveryBoy>getAllDeliveryBoy(){
		return this.deliveryBoyService.getAllDeliveryBoy();
	}
	@GetMapping("/deliveries/{deliveryBoyId}")
	public DeliveryBoy getOneDeliveryBoy(@PathVariable int deliveryBoyId) {
		return this.deliveryBoyService.getOneDeliveryBoy(deliveryBoyId);
	}
	
	@PostMapping("/deliveries")
	public DeliveryBoy addDeliveryBoy(@RequestBody DeliveryBoy deliveryBoy) {
		return this.deliveryBoyService.addDeliveryBoy(deliveryBoy);
		
	}
	
	@PutMapping("/deliveries")
	public DeliveryBoy updateDeliveryBoy(@RequestBody DeliveryBoy deliveryBoy) {
		return this.deliveryBoyService.updateDeliveryBoy(deliveryBoy);
		
	}
	
	@DeleteMapping("/deliveries/{deliveriesId}")
	public ResponseEntity<HttpStatus> deleteDeliveryBoy(@PathVariable int deliveryBoyId){
		try {
			this.deliveryBoyService.deleteDeliveryBoy(deliveryBoyId);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			
		}


}

}
