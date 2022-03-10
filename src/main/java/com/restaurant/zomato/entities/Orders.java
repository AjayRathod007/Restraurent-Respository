package com.restaurant.zomato.entities;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;


@Entity
public class Orders {

	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Id
	private int orderId;
	@Column(nullable = false)
	private long userPhoneNumber;
	@Column(nullable = false)
	private int restaurentId;
	@Column(nullable = false)
	private int deliveryBoyId ;
	private String orderStatus;
	private int amount;
	private Date date;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public long getUserPhoneNumber() {
		return userPhoneNumber;
	}
	public void setUserPhoneNumber(long userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}
	public int getRestaurentId() {
		return restaurentId;
	}
	public void setRestaurentId(int restaurentId) {
		this.restaurentId = restaurentId;
	}
	public int getDeliveryBoyId() {
		return deliveryBoyId;
	}
	public void setDeliveryBoyId(int deliveryBoyId) {
		this.deliveryBoyId = deliveryBoyId;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", userPhoneNumber=" + userPhoneNumber + ", restaurentId=" + restaurentId
				+ ", deliveryBoyId=" + deliveryBoyId + ", orderStatus=" + orderStatus + ", amount=" + amount + ", date="
				+ date + "]";
	}
	public Date getDate() {
		return date;
	}
	public Orders(int orderId, long userPhoneNumber, int restaurentId, int deliveryBoyId, String orderStatus,
			int amount, Date date) {
		super();
		this.orderId = orderId;
		this.userPhoneNumber = userPhoneNumber;
		this.restaurentId = restaurentId;
		this.deliveryBoyId = deliveryBoyId;
		this.orderStatus = orderStatus;
		this.amount = amount;
		this.date = date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}
