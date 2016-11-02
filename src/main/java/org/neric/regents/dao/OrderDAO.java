package org.neric.regents.dao;

import java.util.List;

import org.neric.regents.model.Order;

public interface OrderDAO {

	Order findById(int id);
	
	Order findByUUID(String uuid);
	
	List<Order> findAllOrders();
	
	void saveOrder(Order order);
	
	void updateOrder(Order order);
	
	void deleteOrder(int id);
	
}
