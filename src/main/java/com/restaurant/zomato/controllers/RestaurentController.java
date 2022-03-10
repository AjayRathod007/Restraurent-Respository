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

import com.restaurant.zomato.entities.Restraurent;
import com.restaurant.zomato.services.RestraurentService;

@RestController
public class RestaurentController {
	@Autowired
	private RestraurentService restaurentService;
	//get the restraurent
	@GetMapping("/restraurents")
	public List<Restraurent>getAllRestaurent(){
		return this.restaurentService.getAllRestraurent();
	}
	@GetMapping("/restraurents/{restraurentId}")
	public Restraurent getOneRestraurent(@PathVariable int restraurentId) {
		return this.restaurentService.getOneRestraurent(restraurentId);
	}
	//add restraurent
	@PostMapping("/restraurents")
	public Restraurent addRestraurent(@RequestBody Restraurent restraurent) {
		return this.restaurentService.addRestraurent(restraurent);
		
	}
	// update restraurent
	@PutMapping("/restraurents")
	public Restraurent updateRestraurent(@RequestBody Restraurent restraurent) {
		return this.restaurentService.updateRestraurent(restraurent);
		
	}
	// delete restraurent
	@DeleteMapping("/restraurents/{restraurentId}")
	public ResponseEntity<HttpStatus> deleteRestraurent(@PathVariable int restraurentId){
		try {
			this.restaurentService.deleteRestraurent(restraurentId);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			
		}


}
}