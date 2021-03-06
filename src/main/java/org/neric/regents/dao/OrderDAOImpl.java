package org.neric.regents.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.neric.regents.model.District;
import org.neric.regents.model.Document;
import org.neric.regents.model.Order;
import org.neric.regents.model.OrderContact;
import org.neric.regents.model.OrderDocument;
import org.neric.regents.model.OrderExam;
import org.neric.regents.model.UserDistrict;
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
			
			Hibernate.initialize(order.getUser().getUserDistricts());	
			for(UserDistrict ud : order.getUser().getUserDistricts())
			{
				Hibernate.initialize(ud.getDistrict());
			}

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
			
			Hibernate.initialize(order.getOrderPrint());
			Hibernate.initialize(order.getOrderScan());
			
			Hibernate.initialize(order.getOrderContact());
			Hibernate.initialize(order.getDistrict());
			Hibernate.initialize(order.getSchool());
			
			Hibernate.initialize(order.getOrderForm());
			
		}
		return order;
	}

	@SuppressWarnings("unchecked")
	public List<Order> findAllOrders()
	{
		Criteria crit = createEntityCriteria();
		crit.addOrder(org.hibernate.criterion.Order.desc("orderDate"));
		List<Order> orders = (List<Order>)crit.list();
		if(orders!=null)
		{
			for(Order o : orders)
			{
				if(o != null)
				{
					Hibernate.initialize(o.getUser());
					Hibernate.initialize(o.getOrderForm());
					Hibernate.initialize(o.getDistrict());
					Hibernate.initialize(o.getOrderContact());
				}
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
		saveOrUpdate(order);
	}
	
	
	@Override
	public void updateStatusOfOrders(List<String> uuids, String status)
	{
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.in("uuid", uuids));
		List<Order> orders = (List<Order>)crit.list();
		for(Order order : orders) {
			order.setOrderStatus(status);
			update(order);
		}
	}

	@Override
	public void deleteOrder(int id)
	{
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		Order order = (Order)crit.uniqueResult();
		delete(order);
	}
	
	@Override
	public void deleteOrder(String uuid)
	{
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("uuid", uuid));
		Order order = (Order)crit.uniqueResult();
		delete(order);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findAllOrdersByUserId(int id) 
	{
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("user.id", id));
		crit.addOrder(org.hibernate.criterion.Order.desc("orderDate"));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	
		
		List<Order> orders = (List<Order>)crit.list();
		if(orders!=null)
		{
			for(Order o : orders)
			{
				if(o != null)
				{
					Hibernate.initialize(o.getUser());
					Hibernate.initialize(o.getOrderForm());
					Hibernate.initialize(o.getDistrict());
					Hibernate.initialize(o.getOrderContact());
				}
			}	
		}		
		return (List<Order>)crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findAllOrdersByUsername(String username) 
	{
		
		Criteria criteria = getSession().createCriteria(Order.class, "o");
		criteria.createAlias("o.user", "u");
		criteria.add(Restrictions.eq("u.username", username));
		criteria.addOrder(org.hibernate.criterion.Order.desc("orderDate"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		List<Order> orders = (List<Order>)criteria.list();
		if(orders!=null)
		{
			for(Order o : orders)
			{
				if(o != null)
				{
					Hibernate.initialize(o.getUser());
					Hibernate.initialize(o.getOrderForm());
					Hibernate.initialize(o.getDistrict());
					
					if(o.getOrderContact() != null)
					{
						Hibernate.initialize(o.getOrderContact());
						
					}
				}
			}	
		}		
		return orders;
	}

	@Override
	public int count(){
		int count = ((Long)getSession().createQuery("select count(*) from Order").uniqueResult()).intValue();
		return count;
	}

	@Override
	public int countByActiveOrderForm(int id){
		try {
			Criteria criteria = getSession().createCriteria(Order.class,"order");
			criteria.add(Restrictions.eq("orderForm.id", id));
			criteria.setProjection(Projections.rowCount());
			Number numRows = (Number)criteria.uniqueResult();
			return numRows.intValue();
		}
		catch(Exception e) { return 0;}
	}

	@Override
	public int countByActiveOrderFormUniqueDistrict(int id){
		try {
			Criteria criteria = getSession().createCriteria(Order.class,"order");
			criteria.add(Restrictions.eq("orderForm.id", id));
			criteria.setProjection(Projections.countDistinct("district.id"));
			Number numRows = (Number)criteria.uniqueResult();
			return numRows.intValue();
		}
		catch(Exception e) { return 0;}
	}

	@Override
	public List<Order> findAllOrdersByActiveOrderForm(int id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("orderForm.id", id));
		crit.addOrder(org.hibernate.criterion.Order.desc("orderDate"));

		List<Order> orders = (List<Order>)crit.list();
		if(orders!=null)
		{
			for(Order o : orders)
			{
				if(o != null)
				{
					Hibernate.initialize(o.getUser());
					Hibernate.initialize(o.getOrderForm());
					Hibernate.initialize(o.getDistrict());
					Hibernate.initialize(o.getOrderContact());
				}
			}
		}
		return orders;
	}
}
