package com.restaurant.zomato.services;

import java.util.Date;
import java.util.List;

import com.restaurant.zomato.dao.DeliveryBoyDao;
import com.restaurant.zomato.dao.ItemDao;
import com.restaurant.zomato.dto.PlacedOrder;
import com.restaurant.zomato.dto.PreviewOrder;
import com.restaurant.zomato.dto.UserSelectedItem;
import com.restaurant.zomato.entities.DeliveryBoy;
import com.restaurant.zomato.entities.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.zomato.dao.OrderDao;
import com.restaurant.zomato.entities.UserOrders;

@Service
public class OrderService {
	
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private ItemDao itemDao;
	@Autowired
	private DeliveryBoyDao deliveryBoyDao;
	@Autowired
	private ItemService itemService;

	public List<UserOrders> getAllOrder() {
		
		return orderDao.findAll();
	}

	public UserOrders getOneOrder(int orderId) {
	
		return orderDao.getById(orderId);
	}

	public UserOrders addOrder(UserOrders order) {
		orderDao.save(order);
		return order;
	}

	public UserOrders updateOrder(UserOrders order) {
		orderDao.save(order);
		return order;
		
	}

	public void deleteOrder(int orderId) {
		UserOrders entity=orderDao.getById(orderId);
		orderDao.delete(entity);
		
	}


	public PlacedOrder placeOrder(PreviewOrder previewOrder) throws Exception {
		
		List<UserSelectedItem> userSelectedItems=previewOrder.getListOfItems();
		
		//System.out.println(userSelectedItems.size());
		
		int totalAmt=0;
		int price;
		for(int i=0;i<userSelectedItems.size();i++){
			
			UserSelectedItem userSelectedItem=userSelectedItems.get(i);
			
			Items item= itemDao.getById(userSelectedItem.getItemId());
			
			//Verify that demanding items are in stock or not
			if(item.getQuantity()>userSelectedItem.getQuantity())
			{
				item.setQuantity(item.getQuantity()-userSelectedItem.getQuantity());
				price = item.getItemPrice()*userSelectedItem.getQuantity();
			}	
			else
			{
				item.setQuantity(0);
				price = item.getItemPrice()*item.getQuantity();
			}
			
			//After updating quantity of current item need to be store updated data into databse
            itemService.save(item);
            
			totalAmt=totalAmt+price;
		}
		
		if(totalAmt==0)
			throw new Exception("user items are not over");
		DeliveryBoy deliv = deliveryBoyDao.findByRestaurantId(previewOrder.getRestaurantId());
		UserOrders order = createOrder(totalAmt, previewOrder, deliv);
		return getPlaceOrder(order,deliv,previewOrder);
	}

	private PlacedOrder getPlaceOrder(UserOrders order, DeliveryBoy deliv, PreviewOrder previewOrder) {
		PlacedOrder p =new PlacedOrder();
		p.setOrderId(order.getOrderId());
		p.setPreviewOrder(previewOrder);
		p.setDeliveryBoy(deliv);
		return p;
	}

	private UserOrders createOrder(int totalAmt, PreviewOrder previewOrder, DeliveryBoy deliv) {
		UserOrders o = new UserOrders();
		o.setAmount(totalAmt);
		o.setDate(new Date());
		if(totalAmt!=0)
		o.setOrderStatus("SUCCESS");
		o.setDeliveryBoyId(deliv.getDeliveryBoyId());
		o.setSelectedMenu(previewOrder.getListOfItems().toString());
		o.setRestaurantId(previewOrder.getRestaurantId());
		o.setUserId(previewOrder.getUserId());
		
		return orderDao.save(o);
	}
}
