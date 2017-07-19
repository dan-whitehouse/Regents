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
import org.neric.regents.model.OrderFormDocument;
import org.neric.regents.model.OrderFormExam;
import org.springframework.stereotype.Repository;


@Repository("orderFormDAO")
public class OrderFormDAOImpl extends AbstractDao<Integer, OrderForm> implements OrderFormDAO{

	public OrderForm findById(int id) 
	{
		OrderForm orderForm = getByKey(id);
		if(orderForm!=null)
		{
			Hibernate.initialize(orderForm.getOrderFormDocuments());	
			for(OrderFormDocument ofd : orderForm.getOrderFormDocuments())
			{
				Hibernate.initialize(ofd.getDocument());
			}
					
			Hibernate.initialize(orderForm.getOrderFormExams());
			for(OrderFormExam ofe: orderForm.getOrderFormExams())
			{
				Hibernate.initialize(ofe.getExam());
			}
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
			Hibernate.initialize(orderForm.getOrderFormDocuments());	
			for(OrderFormDocument ofd : orderForm.getOrderFormDocuments())
			{
				Hibernate.initialize(ofd.getDocument());
			}
					
			Hibernate.initialize(orderForm.getOrderFormExams());
			for(OrderFormExam ofe: orderForm.getOrderFormExams())
			{
				Hibernate.initialize(ofe.getExam());
			}		
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
