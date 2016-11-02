/**
 * @author      Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version     x.x.x
 * @since       Nov 2, 2016
 * @filename	OptionScanService.java
 */
package org.neric.regents.service;

import java.util.List;

import org.neric.regents.model.Order;

public interface OrderService
{
	Order findById(int id);
	
	List<Order> findAllOrders();
	
	void saveOrder(Order order);
	
	void updateOrder(Order order);
	
	void deleteOrder(int id);
}
