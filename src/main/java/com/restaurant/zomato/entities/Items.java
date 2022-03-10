package com.restaurant.zomato.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Items {
	public Items() {
		super();
		// TODO Auto-generated constructor stub
	}
	private int restraurentId;
	@Id
	private String itemName;
	private int itemPrice;
	public Items(int restraurentId, String itemName, int itemPrice) {
		super();
		this.restraurentId = restraurentId;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
	}
	public int getRestraurentId() {
		return restraurentId;
	}
	public void setRestraurentId(int restraurentId) {
		this.restraurentId = restraurentId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}
	@Override
	public String toString() {
		return "Items [restraurentId=" + restraurentId + ", itemName=" + itemName + ", itemPrice=" + itemPrice + "]";
	}
	

}
