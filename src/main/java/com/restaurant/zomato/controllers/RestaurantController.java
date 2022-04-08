package com.restaurant.zomato.controllers;

import java.util.List;

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

import com.restaurant.zomato.entities.Restaurant;
import com.restaurant.zomato.services.RestaurantService;
import com.restaurant.zomato.validation.UsersRequestBodyValidation;

@RestController
@CrossOrigin(origins= {"http://localhost:3000"})
public class RestaurantController {
	@Autowired
	private RestaurantService restaurantService;

	// get the restaurant
	@GetMapping("/restaurants")
	public List<Restaurant> getAllRestaurant() {
		List<Restaurant> restaurant = null;
		try {

			restaurant = this.restaurantService.getAllRestaurant();
			if (restaurant.size() == 0)
				throw new Exception("not found any Restaurant");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return restaurant;
	}

	@GetMapping("/restaurants/{restaurantId}")
	public Restaurant getOneRestaurant(@PathVariable int restaurantId) {
		Restaurant temp = null;
		try {
			UsersRequestBodyValidation.validateRestaurantId(restaurantId);
			temp = restaurantService.getOneRestaurantById(restaurantId);

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
		return temp;
		
	}
	
	@GetMapping("/address/{restaurantLocation}")
	public List<Restaurant> getAllRestaurant(@PathVariable String restaurantLocation) {
		List<Restaurant> temp = null;
		try {
			UsersRequestBodyValidation.validateRestaurantByLocation(restaurantLocation);
			temp = restaurantService.getAllRestaurantByAdd(restaurantLocation);

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
		return temp;

	}

	// add restaurant
	@PostMapping("/restaurants")
	public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
		Restaurant temp = null;
		try {
			UsersRequestBodyValidation.validateRestaurantId(restaurant.getRestaurantId());
			temp = restaurantService.addRestaurant(restaurant);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return temp;

	}

	// update restaurant
	@PutMapping("/restaurants")
	public Restaurant updateRestaurant(@RequestBody Restaurant restaurant) {
		Restaurant temp = null;
		try {
			UsersRequestBodyValidation.validateRestaurantId(restaurant.getRestaurantId());
			temp = restaurantService.addRestaurant(restaurant);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return temp;

	}

	// delete restaurant
	@DeleteMapping("/restaurants/{restaurantId}")
	public ResponseEntity<String> deleteRestaurant(@PathVariable int restaurantId) {
		try {
			UsersRequestBodyValidation.validateRestaurantId(restaurantId);
			this.restaurantService.deleteRestaurant(restaurantId);
			return new ResponseEntity<String>("Restaurant has been deleted successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Restaurant has not been deleted", HttpStatus.NOT_FOUND);

		}

	}

	@GetMapping("/restaurants/{location}")
	public ResponseEntity<List<Restaurant>> getRestaurantByLocation(@PathVariable String location){
		return new ResponseEntity<>(restaurantService.getRestaurantByAddress(location), HttpStatus.OK);
	}


}