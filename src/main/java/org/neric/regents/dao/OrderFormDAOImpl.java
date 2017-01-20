package org.neric.regents.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.neric.regents.model.District;
import org.neric.regents.model.Order;
import org.neric.regents.model.OrderDocument;
import org.neric.regents.model.OrderExam;
import org.neric.regents.model.OrderForm;
import org.springframework.stereotype.Repository;


@Repository("orderFormDAO")
public class OrderFormDAOImpl extends AbstractDao<Integer, OrderForm> implements OrderFormDAO{

	public OrderForm findById(int id) 
	{
		OrderForm order = getByKey(id);
		if(order!=null)
		{
			//Hibernate.initialize(order.getUser());
		}
		return order;
	}
	
	public OrderForm findByUUID(String uuid) 
	{
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("uuid", uuid));
		OrderForm order = (OrderForm)crit.uniqueResult();
				
		if(order!=null)
		{
//			Hibernate.initialize(order.getUser());
//			
//			Hibernate.initialize(order.getOrderExams());			
//			for(OrderExam e : order.getOrderExams())
//			{
//				Hibernate.initialize(e.getExam());
//			}
//			
//			Hibernate.initialize(order.getOrderDocuments());			
//			for(OrderDocument d : order.getOrderDocuments())
//			{
//				Hibernate.initialize(d.getDocument());
//			}
			
			
		}
		return order;
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