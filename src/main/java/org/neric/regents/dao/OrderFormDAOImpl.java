package org.neric.regents.dao;

import java.util.List;
import java.util.UUID;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.neric.regents.model.District;
import org.neric.regents.model.Document;
import org.neric.regents.model.Exam;
import org.neric.regents.model.Order;
import org.neric.regents.model.OrderDocument;
import org.neric.regents.model.OrderExam;
import org.neric.regents.model.OrderForm;
import org.springframework.stereotype.Repository;


@Repository("orderFormDAO")
public class OrderFormDAOImpl extends AbstractDao<Integer, OrderForm> implements OrderFormDAO{

	public OrderForm findById(int id) 
	{
		OrderForm orderForm = getByKey(id);
		if(orderForm!=null)
		{
			Hibernate.initialize(orderForm.getExams());			
			Hibernate.initialize(orderForm.getDocuments());	
		}
		return orderForm;
	}
	
	public OrderForm findByUUID(String uuid) 
	{
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("uuid", uuid));
		OrderForm orderForm = (OrderForm)crit.uniqueResult();
				
		if(orderForm!=null)
		{
			Hibernate.initialize(orderForm.getExams());			
			Hibernate.initialize(orderForm.getDocuments());			
		}
		return orderForm;
	}

	@SuppressWarnings("unchecked")
	public List<OrderForm> findAllOrderForms()
	{
		Criteria crit = createEntityCriteria();
		crit.addOrder(org.hibernate.criterion.Order.asc("id"));
		
		List<OrderForm> orders = (List<OrderForm>)crit.list();
		if(orders!=null)
		{
//			for(OrderForm o : orders)
//			{
//				Hibernate.initialize(o.getUser());
//			}	
		}		
		return (List<OrderForm>)crit.list();
	}

	@Override
	public void saveOrderForm(OrderForm order)
	{
		order.setUuid(UUID.randomUUID().toString());
		
		for(Exam o : order.getExams())
		{
			System.err.println("Exams in OrderForm: " + o.getName());
		}
		
		for(Document o : order.getDocuments())
		{
			System.err.println("Documents in OrderForm: " + o.getName());
		}
		
		
		persist(order);
		
	}

	@Override
	public void updateOrderForm(OrderForm order)
	{
		
	}

	@Override
	public void deleteOrderForm(int id)
	{
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		OrderForm order = (OrderForm)crit.uniqueResult();
		delete(order);
	}
	
}
