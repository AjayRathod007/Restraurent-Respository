package com.restaurant.zomato.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.zomato.dao.RestaurentDao;
import com.restaurant.zomato.entities.Restraurent;

@Service
public class RestraurentService {
	@Autowired
	public RestaurentDao restraurentDao;

	public List<Restraurent> getAllRestraurent() {
		
		return restraurentDao.findAll();
	}

	public Restraurent getOneRestraurent(int restraurentId) {
	
		return restraurentDao.getById(restraurentId);
	}

	public Restraurent addRestraurent(Restraurent restraurent) {
		restraurentDao.save(restraurent);
		return restraurent;
	}

	public Restraurent updateRestraurent(Restraurent restraurent) {
		restraurentDao.save(restraurent);
		return restraurent;
		
	}

	public void deleteRestraurent(int restraurentId) {
		Restraurent entity=restraurentDao.getById(restraurentId);
		restraurentDao.delete(entity);
		
	}
	public Restraurent getAllRestraurentByAddress(String address,String name) {
		Restraurent temp = null;
		List<Restraurent> choiceRestraurent= restraurentDao.findByAddress(address);
		
		for(Restraurent x:choiceRestraurent) {
			if(x.getName().equals(name)) {
				temp= x;
			}
			
		}
		return temp;
	}


	

}
