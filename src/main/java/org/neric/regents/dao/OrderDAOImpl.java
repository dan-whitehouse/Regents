package org.neric.regents.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.neric.regents.model.District;
import org.neric.regents.model.Order;
import org.neric.regents.model.OrderDocument;
import org.neric.regents.model.OrderExam;
import org.springframework.stereotype.Repository;


@Repository("orderDAO")
public class OrderDAOImpl extends AbstractDao<Integer, Order> implements OrderDAO{

	public Order findById(int id) 
	{
		Order order = getByKey(id);
		if(order!=null)
		{
			Hibernate.initialize(order.getUser());
		}
		return order;
	}
	
	public Order findByUUID(String uuid) 
	{
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("uuid", uuid));
		Order order = (Order)crit.uniqueResult();
				
		if(order!=null)
		{
			Hibernate.initialize(order.getUser());
			
			Hibernate.initialize(order.getOrderExams());			
			for(OrderExam e : order.getOrderExams())
			{
				Hibernate.initialize(e.getExam());
			}
			
			Hibernate.initialize(order.getOrderDocuments());			
			for(OrderDocument d : order.getOrderDocuments())
			{
				Hibernate.initialize(d.getDocument());
			}
			
			
		}
		return order;
	}

	@SuppressWarnings("unchecked")
	public List<Order> findAllOrders()
	{
		Criteria crit = createEntityCriteria();
		crit.addOrder(org.hibernate.criterion.Order.asc("id"));
		
		List<Order> orders = (List<Order>)crit.list();
		if(orders!=null)
		{
			for(Order o : orders)
			{
				Hibernate.initialize(o.getUser());
			}	
		}		
		return (List<Order>)crit.list();
	}

	@Override
	public void saveOrder(Order order)
	{
		persist(order);
		
	}

	@Override
	public void updateOrder(Order order)
	{
		
	}

	@Override
	public void deleteOrder(int id)
	{
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		Order order = (Order)crit.uniqueResult();
		delete(order);
	}
	
}
