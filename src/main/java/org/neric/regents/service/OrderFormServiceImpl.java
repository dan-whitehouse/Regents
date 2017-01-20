/**
 * @author      Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version     x.x.x
 * @since       Nov 2, 2016
 * @filename	OptionScanServiceImpl.java
 */
package org.neric.regents.service;

import java.util.List;
import org.neric.regents.dao.OrderDAO;
import org.neric.regents.dao.OrderFormDAO;
import org.neric.regents.model.OrderForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("orderFormService")
@Transactional
public class OrderFormServiceImpl implements OrderFormService
{

	@Autowired
	private OrderFormDAO dao;
	
	public OrderForm findById(int id)
	{
		return dao.findById(id);
	}
	
	public OrderForm findByUUID(String uuid)
	{
		return dao.findByUUID(uuid);
	}

	public List<OrderForm> findAllOrderForms()
	{
		return dao.findAllOrderForms();
	}

	public void saveOrderForm(OrderForm order)
	{
		dao.saveOrderForm(order);
		
	}

	public void updateOrderForm(OrderForm order)
	{
		OrderForm entity = dao.findById(order.getId());
		if(entity != null)
		{
//			entity.setOrderDocuments(order.getOrderDocuments());
//			entity.setOrderExams(order.getOrderExams());
//			entity.setOrderPrint(order.getOrderPrint());
//			entity.setOrderScan(order.getOrderScan());
//			entity.setReportToLevelOne(order.getReportToLevelOne());
//			entity.setUser(order.getUser());
		}
	}

	public void deleteOrderForm(int id)
	{
		dao.deleteOrderForm(id);
		
	}
}
