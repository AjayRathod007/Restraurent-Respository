package com.restaurant.zomato.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.zomato.entities.Orders;


public interface OrderDao extends JpaRepository<Orders, Integer> {

}
