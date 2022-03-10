package com.restaurant.zomato.services;

import java.util.List;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.zomato.dao.ItemDao;
import com.restaurant.zomato.entities.Items;


@Service
public class ItemService {
	@Autowired
	public ItemDao itemDao;

	public List<Items> getAllItem() {
		
		return itemDao.findAll();
	}

	public Items getOneItem(String itemName) {
	
		return itemDao.getById(itemName);
	}

	public Items addItem(Items item) {
		itemDao.save(item);
		return item;
	}

	public Items updateItem(Items item) {
	
		itemDao.save(item);
		return item;
		
	}

	public void deleteItem(String itemName) {
		Items entity=itemDao.getById(itemName);
		itemDao.delete(entity);
		
	}
	public List<Items> getAllRestraurentItems(int restraurentId){
		
		List<Items> item = itemDao.findByRestraurentId(restraurentId);
		return item;
	}

}
