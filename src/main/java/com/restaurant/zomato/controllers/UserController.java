package com.restaurant.zomato.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.restaurant.zomato.dto.UserLoginResponseBody;
import com.restaurant.zomato.entities.LoginUser;
import com.restaurant.zomato.entities.UserOrders;
import com.restaurant.zomato.entities.Users;
import com.restaurant.zomato.services.UserService;
import com.restaurant.zomato.validation.UsersRequestBodyValidation;
@RestController
@CrossOrigin(origins= {"http://localhost:3000"})
public class UserController {
	
	 Logger logger = LoggerFactory.getLogger(UserController.class);
	 
	@Autowired
	private UserService userService;
	
	@GetMapping("/form")
	public String showRagistrationForm(Model model) {
		System.out.println("opening form");
	    model.addAttribute("user", new Users() );
	    return "form";
	}
	// handler for processing form
	@PostMapping("/process")
	public String processForm(@ModelAttribute("user")Users user) {
		System.out.println(user);
		return "sucess";
		
	}
	
	@PostMapping("/adduser")
    public String addUser(@Valid Users user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }
        
        //userRepository.save(user);
        return "redirect:/index";
    }
	
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
//	
//	@GetMapping("/login/{userid}/{password}")
//	public ResponseEntity<?> authenticateUser(@PathVariable long userid, @PathVariable String password) {
//		try
//		{
//			UsersRequestBodyValidation.validateUserPhoneNumber(userid);
//		  UserLoginResponseBody res =  this.userService.userAuthentication(userid,password);
//		 	ArrayList<UserLoginResponseBody> arr = new ArrayList<>();
//		 	arr.add(res);
//		  return new ResponseEntity<>(arr, HttpStatus.ACCEPTED);
//		}catch(Exception e)
//		{
//			return new ResponseEntity<>(e.getMessage(), HttpStatus.FAILED_DEPENDENCY);
//		}
//		
//		
//	    
//	}
	
	@PostMapping("/login")
	public UserLoginResponseBody userLogin(@RequestBody LoginUser user) {
		UserLoginResponseBody temp=null;
		try {
			UsersRequestBodyValidation.validateUserPhoneNumber(user.getPhoneNumber());
			temp=this.userService.userAuthentication(user.getPhoneNumber(), user.getPassword());
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return temp;
		
	}
		
	@PostMapping("/signup")
	public Users addUser(@RequestBody Users user) {
		
		Users temp=null;
		try
		{
			logger.info("Received user {}",user);
			UsersRequestBodyValidation.validateUserPhoneNumber(user.getPhoneNumber());
		    temp = this.userService.addUser(user);
		    logger.info("user saved");
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
	public UserOrders getPlaceOrder(@PathVariable long phoneNumber, @PathVariable String address, @PathVariable String name) {
		UserOrders entity = null;
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
