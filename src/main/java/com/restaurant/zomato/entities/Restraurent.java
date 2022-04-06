package com.restaurant.zomato.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Restraurent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int restaurantId;
	private String name;
	private String address;
	public Restraurent(int restaurantId, String name, String address) {
		super();
		this.restaurantId = restaurantId;
		this.name = name;
		this.address = address;
	}
	public Restraurent() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Restraurent [restaurantId=" + restaurantId + ", name=" + name + ", address=" + address + "]";
	}
	
	


}
