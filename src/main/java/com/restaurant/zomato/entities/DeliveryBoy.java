package com.restaurant.zomato.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DeliveryBoy {
	private String name;
	@Id
	private int deliveryBoyId;
	private String address;
	private int restraurentId;
	public DeliveryBoy() {
		super();
		// TODO Auto-generated constructor stub
	}

	private String status;

	public DeliveryBoy(String name, int deliveryBoyId, String address, int restraurentId, String status) {
		super();
		this.name = name;
		this.deliveryBoyId = deliveryBoyId;
		this.address = address;
		this.restraurentId = restraurentId;
		this.status = status;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getdeliveryBoyId() {
		return deliveryBoyId;
	}
	public void setId(int deliveryBoyId) {
		this.deliveryBoyId = deliveryBoyId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getRestraurentId() {
		return restraurentId;
	}
	public void setRestraurentId(int restraurentId) {
		this.restraurentId = restraurentId;
	}
	@Override
	public String toString() {
		return "DeliveryBoy [name=" + name + ", deliveryBoyId=" + deliveryBoyId + ", address=" + address + ", restraurentId=" + restraurentId
				+ ", status=" + status + "]";
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
