package com.restaurant.zomato.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.zomato.dao.UserDao;
import com.restaurant.zomato.dto.LoginResult;
import com.restaurant.zomato.dto.UserLoginResponseBody;
import com.restaurant.zomato.entities.DeliveryBoy;
import com.restaurant.zomato.entities.Items;
import com.restaurant.zomato.entities.LoginUser;
import com.restaurant.zomato.entities.UserOrders;
import com.restaurant.zomato.entities.Restaurant;
import com.restaurant.zomato.entities.Users;

@Service
public class UserService {
	
	//@Lazy
	//@Autowired
	//private PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserDao userDao;
	@Autowired
	public RestaurantService restaurantService;
	@Autowired
	public ItemService itemService;
	@Autowired
	public DeliveryBoyService deliveryBoyService;
	@Autowired
	public OrderService orderService;

	public List<Users> getAllUser() {

		return userDao.findAll();
	}

	public Users getOneUser(long userId) {

		return userDao.getById(userId);
	}

	public Users addUser(Users user)
	{
		//user.setPassword(passwordEncoder.encode(user.getPassword()));
		userDao.save(user);
		return user;
	}

	public Users updateUser(Users user) {
		userDao.save(user);
		return user;

	}

	public void deleteUser(long userId) {
		Users entity = userDao.getById(userId);
		userDao.delete(entity);

	}

	
	//ORDER WPULD BE CREATED AND STORED IN DATABASE
	public UserOrders getOrderPlaced(long phoneNumber, String address, String name) {
		Restaurant temp;
		List<Items> item;
		DeliveryBoy boy;
		int amount = 0;
		Date date = new Date();

		temp = restaurantService.getAllRestaurantByAddress(address, name);
		item = itemService.getAllRestaurantItems(temp.getRestaurantId());
		for (Items x : item) {
			amount += x.getItemPrice();

		}
		boy = deliveryBoyService.getDeliveryBoyByRestaurantId(temp.getRestaurantId());

		UserOrders order = new UserOrders();
		order.setOrderStatus("placed");
		order.setAmount(amount);
		order.setDate(new Date());
		order.setUserId(phoneNumber);
		order.setRestaurantId(temp.getRestaurantId());
		order.setDeliveryBoyId(boy.getDeliveryBoyId());
		return orderService.addOrder(order);
	}

	public UserLoginResponseBody userAuthentication(long userid, String password) throws Exception {
		Users user = userDao.getById(userid);
		
		
		LoginResult lor = new LoginResult();
		
		UserLoginResponseBody res = new UserLoginResponseBody();
		lor.setName(user.getName());
		lor.setPhoneNumber(user.getPhoneNumber());
		
		ArrayList<LoginResult> arr = new ArrayList<>();
		
		arr.add(lor);
		res.setRes(arr);
		
		
		if(!user.getPassword().equals(password))
		    throw new Exception("Invalid Credentials");
		
		res.setStatus("PASS");
		res.setStatusCode(200);
		
		
	return res;
	}


}
