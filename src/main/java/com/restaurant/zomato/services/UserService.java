package com.restaurant.zomato.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.restaurant.zomato.dao.UserDao;
import com.restaurant.zomato.entities.DeliveryBoy;
import com.restaurant.zomato.entities.Items;
import com.restaurant.zomato.entities.Orders;
import com.restaurant.zomato.entities.Restraurent;
import com.restaurant.zomato.entities.Users;

@Service
public class UserService {
	
	@Lazy
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserDao userDao;
	@Autowired
	public RestraurentService restraurentService;
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
		user.setPassword(passwordEncoder.encode(user.getPassword()));
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

	public Orders getOrderPlaced(long phoneNumber, String address, String name) {
		Restraurent temp;
		List<Items> item;
		DeliveryBoy boy;
		int amount = 0;
		Date date = new Date();

		temp = restraurentService.getAllRestraurentByAddress(address, name);
		item = itemService.getAllRestraurentItems(temp.getrestraurentId());
		for (Items x : item) {
			amount += x.getItemPrice();

		}
		boy = deliveryBoyService.getDeliveryBoyByRestraurentId(temp.getrestraurentId());

		Orders order = new Orders(1001, phoneNumber, temp.getrestraurentId(), boy.getdeliveryBoyId(), "placed", amount,
				date);
		orderService.addOrder(order);
		return order;

	}

}
