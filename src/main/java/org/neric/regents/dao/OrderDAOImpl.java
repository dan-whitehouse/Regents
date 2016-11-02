package org.neric.regents.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.neric.regents.model.Order;
import org.springframework.stereotype.Repository;


@Repository("orderDAO")
public class OrderDAOImpl extends AbstractDao<Integer, Order> implements OrderDAO{

	public Order findById(int id) 
	{
		return getByKey(id);
	}

	@SuppressWarnings("unchecked")
	public List<Order> findAllOrders()
	{
		Criteria crit = createEntityCriteria();
		crit.addOrder(org.hibernate.criterion.Order.asc("id"));
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
