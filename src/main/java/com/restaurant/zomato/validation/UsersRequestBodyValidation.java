package com.restaurant.zomato.validation;

public class UsersRequestBodyValidation {

	public static void validateUserPhoneNumber(long phoneNumber) throws Exception {
		String id;
		id = String.valueOf(phoneNumber);
		if (id.length() != 10)
			throw new Exception("invalid phone Number we");
	}

	public static void validateRestraurentId(int restraurentId) throws Exception {
		String id;

		id = String.valueOf(restraurentId);
		if (id.length() == 0)
			throw new Exception("null value sent");
	}

	public static void validateRestraurentByLocation(String restraurentAdd) throws Exception {
		
		if (restraurentAdd.length() == 0)
			throw new Exception("null value sent");
	}
	
	public static void validatePlaceOrderField(long phoneNumber, String address, String name) throws Exception {
		String phone;
		phone = String.valueOf(phoneNumber);
		if (phone.length() != 10 || address.length() == 0 || name.length() == 0) {
			throw new Exception("wrong details");
		}

	}

	public static void validateOrderId(int orderId) throws Exception {
		String id;
		id = String.valueOf(orderId);
		if (id.length() == 0) {
			throw new Exception("wrong order Id");
		}
	}

	public static void validateItemName(int itemId) throws Exception {
		if (itemId < 0)
			throw new Exception("wrong item Name");
	}

	public static void validateItemField(int restraurentId, String itemName, int itemPrice) throws Exception {
		String id;
		String price;
		id = String.valueOf(restraurentId);
		price = String.valueOf(itemPrice);
		if (id.length() == 0 || price.length() == 0 || itemName.length() == 0)
			throw new Exception("wrong item details");

	}

	public static void validateDeliveryBoyId(int deliveryBoyId) throws Exception {
		String id;
		id = String.valueOf(deliveryBoyId);
		if (id.length() == 0) {
			throw new Exception("wrong Delivery Boy Id");
		}

	}

	public static void validateDeliveryBoyField(int restraurentId, int deliveryBoyId, String address) throws Exception {
		String rsId;
		String dId;
		rsId = String.valueOf(restraurentId);
		dId = String.valueOf(deliveryBoyId);
		if (rsId.length() == 0 || dId.length() == 0 || address.length() == 0)
			throw new Exception("wrong delivery Boy Details sent");
	}

}
