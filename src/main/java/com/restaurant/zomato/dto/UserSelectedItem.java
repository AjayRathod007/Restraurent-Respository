package com.restaurant.zomato.dto;

import lombok.Data;

@Data
public class UserSelectedItem {
    private int itemId;
    private int quantity;
    
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
    
    
}
