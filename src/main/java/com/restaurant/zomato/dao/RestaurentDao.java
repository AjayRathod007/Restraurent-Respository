package com.restaurant.zomato.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.zomato.entities.Restraurent;


public interface RestaurentDao extends JpaRepository<Restraurent, Integer> {

	List<Restraurent> findByAddress(String address);
	

}
