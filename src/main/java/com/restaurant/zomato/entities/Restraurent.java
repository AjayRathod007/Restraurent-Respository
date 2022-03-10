package com.restaurant.zomato.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Restraurent {
	@Id
	private int restraurentId;
	private String name;
	private String address;
	public Restraurent(int restraurentId, String name, String address) {
		super();
		this.restraurentId = restraurentId;
		this.name = name;
		this.address = address;
		
	}
	public Restraurent() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getrestraurentId() {
		return restraurentId;
	}
	public void setId(int restraurentId) {
		this.restraurentId = restraurentId;
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
		return "Restraurent [restraurentId=" + restraurentId + ", name=" + name + ", address=" + address + ", menuId="  + "]";
	}
	

}
