/**
 * @author      Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version     x.x.x
 * @since       Nov 2, 2016
 * @filename	OptionScanServiceImpl.java
 */
package org.neric.regents.service;

import java.util.List;
import org.neric.regents.dao.OrderDAO;
import org.neric.regents.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService
{

	@Autowired
	private OrderDAO dao;
	
	public Order findById(int id)
	{
		return dao.findById(id);
	}
	
	public Order findByUUID(String uuid)
	{
		return dao.findByUUID(uuid);
	}

	public List<Order> findAllOrders()
	{
		return dao.findAllOrders();
	}

	public void saveOrder(Order order)
	{
		dao.saveOrder(order);
		
	}

	public void updateOrder(Order order)
	{
		Order entity = dao.findById(order.getId());
		if(entity != null)
		{
			entity.setOrderDocuments(order.getOrderDocuments());
			entity.setOrderExams(order.getOrderExams());
			entity.setOrderPrint(order.getOrderPrint());
			entity.setOrderScan(order.getOrderScan());
			entity.setReportToLevelOne(order.getReportToLevelOne());
			entity.setUser(order.getUser());
		}
	}

	public void deleteOrder(int id)
	{
		dao.deleteOrder(id);
	}
	
	public void deleteOrder(String uuid)
	{
		dao.deleteOrder(uuid);
	}
}
