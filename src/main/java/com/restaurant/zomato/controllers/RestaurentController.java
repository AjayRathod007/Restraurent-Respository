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
import com.restaurant.zomato.entities.Users;
import com.restaurant.zomato.services.RestraurentService;
import com.restaurant.zomato.validation.UsersRequestBodyValidation;

@RestController
public class RestaurentController {
	@Autowired
	private RestraurentService restaurentService;

	// get the restraurent
	@GetMapping("/restraurents")
	public List<Restraurent> getAllRestaurent() {
		List<Restraurent> restraurent = null;
		try {

			restraurent = this.restaurentService.getAllRestraurent();
			if (restraurent.size() == 0)
				throw new Exception("not found any Restraurent");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return restraurent;
	}

	@GetMapping("/restraurents/{restraurentId}")
	public Restraurent getOneRestraurent(@PathVariable int restraurentId) {
		Restraurent temp = null;
		try {
			UsersRequestBodyValidation.validateRestraurentId(restraurentId);
			temp = restaurentService.getOneRestraurent(restraurentId);

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
		return temp;

	}

	// add restraurent
	@PostMapping("/restraurents")
	public Restraurent addRestraurent(@RequestBody Restraurent restraurent) {
		Restraurent temp = null;
		try {
			UsersRequestBodyValidation.validateRestraurentId(restraurent.getrestraurentId());
			temp = restaurentService.addRestraurent(restraurent);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return temp;

	}

	// update restraurent
	@PutMapping("/restraurents")
	public Restraurent updateRestraurent(@RequestBody Restraurent restraurent) {
		Restraurent temp = null;
		try {
			UsersRequestBodyValidation.validateRestraurentId(restraurent.getrestraurentId());
			temp = restaurentService.addRestraurent(restraurent);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return temp;

	}

	// delete restraurent
	@DeleteMapping("/restraurents/{restraurentId}")
	public ResponseEntity<String> deleteRestraurent(@PathVariable int restraurentId) {
		try {
			UsersRequestBodyValidation.validateRestraurentId(restraurentId);
			this.restaurentService.deleteRestraurent(restraurentId);
			return new ResponseEntity<String>("Restraurent has been deleted successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Restraurent has not been deleted", HttpStatus.NOT_FOUND);

		}

	}
}