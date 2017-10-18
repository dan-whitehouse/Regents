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
import org.neric.regents.model.OrderForm;
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

	@Override
	public List<Order> findAllOrdersByActiveOrderForm(int id) {
		return dao.findAllOrdersByActiveOrderForm(id);
	}

	@Override
	public List<Order> findAllOrdersByUsername(String username)
	{
		return dao.findAllOrdersByUsername(username);
	}

	public void saveOrder(Order order)
	{
		dao.saveOrder(order);
		
	}

	public void updateOrder(Order order)
	{
		dao.updateOrder(order);	
	}

	public void deleteOrder(int id)
	{
		dao.deleteOrder(id);
	}
	
	public void deleteOrder(String uuid)
	{
		dao.deleteOrder(uuid);
	}

	@Override
	public void updateStatus(String uuid, Boolean isComplete)
	{
		Order entity = dao.findByUUID(uuid);
		if(entity != null)
		{
			if(isComplete)
			{
				entity.setOrderStatus("Complete");
			}
			else
			{
				entity.setOrderStatus("Processing");
			}
		}
	}

	@Override
	public int count()
	{
		return dao.count();
	}

	@Override
	public int countByActiveOrderForm(int id) {
		return dao.countByActiveOrderForm(id);
	}

	@Override
	public int countByActiveOrderFormUniqueDistrict(int id)
	{
		return dao.countByActiveOrderFormUniqueDistrict(id);
	}
}
