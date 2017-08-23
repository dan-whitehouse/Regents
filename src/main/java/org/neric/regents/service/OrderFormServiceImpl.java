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
import org.neric.regents.model.Document;
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
		dao.updateOrderForm(order);
//		OrderForm entity = dao.findById(order.getId());
//		if(entity != null)
//		{
//			entity.setName(order.getName());
//			entity.setStartDate(order.getStartDate());
//			entity.setEndDate(order.getEndDate());
//			entity.setVisible(order.getVisible());
//			entity.setLocked(order.getLocked());
//			entity.setActive(order.getActive());
//			entity.setOrderFormDocuments(order.getOrderFormDocuments());
//			entity.setOrderFormExams(order.getOrderFormExams());
//		}
	}

	public void deleteOrderForm(int id)
	{
		dao.deleteOrderForm(id);
		
	}

	@Override
	public void lockByOrderFormId(int id, Boolean isLocked)
	{
		OrderForm entity = dao.findById(id);
		if(entity != null)
		{
			entity.setLocked(isLocked);
		}
	}

	@Override
	public void hideByOrderFormId(int id, Boolean isHidden)
	{
		OrderForm entity = dao.findById(id);
		if(entity != null)
		{
			entity.setVisible(isHidden);
		}
	}

	@Override
	public void lockByOrderFormUUID(String uuid, Boolean isLocked) 
	{	
		OrderForm entity = dao.findByUUID(uuid);
		if(entity != null)
		{
			entity.setLocked(isLocked);
		}	
	}

	@Override
	public void hideByOrderFormUUID(String uuid, Boolean isHidden) 
	{
		OrderForm entity = dao.findByUUID(uuid);
		if(entity != null)
		{
			entity.setVisible(isHidden);
		}	
		
	}
	
	@Override
	public void activateOrderFormUUID(String uuid, Boolean isActive) 
	{
		//Set each OrderForm as inactive
		if(isActive)
		{
			dao.setAllInactive();
		}
		
		//Set this OrderForm as active
		OrderForm entity = dao.findByUUID(uuid);
		if(entity != null)
		{
			entity.setActive(isActive);
		}	
	}

	@Override
	public boolean hasActiveOrderForm()
	{
		return dao.hasActiveOrderForm();
	}

	@Override
	public OrderForm getActiveOrderForm()
	{
		return dao.getActiveOrderForm();
	}
}
