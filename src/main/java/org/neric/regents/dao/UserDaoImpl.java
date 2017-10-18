package org.neric.regents.dao;

import java.util.List;

import org.neric.regents.model.User;
import org.neric.regents.model.UserDistrict;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;


@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	public User findById(int id) {
		User user = getByKey(id);
		if(user!=null)
		{
			Hibernate.initialize(user.getUserProfiles());
			for(UserDistrict ud : user.getUserDistricts())
			{
				Hibernate.initialize(ud.getDistrict());
			}
		}
		return user;
	}

	public User findByUsername(String username) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("username", username));
		User user = (User)crit.uniqueResult();
		if(user!=null)
		{
			Hibernate.initialize(user.getUserProfiles());

			Hibernate.initialize(user.getUserDistricts());
			for(UserDistrict ud : user.getUserDistricts())
			{
				Hibernate.initialize(ud.getDistrict());
			}


			Hibernate.initialize(user.getOrders());
			user.getOrders().forEach(order -> {
				Hibernate.initialize(order.getOrderForm());
				Hibernate.initialize(order.getSchool());
				Hibernate.initialize(order.getDistrict());
			});
		}
		return user;
	}
	
	@Override
	public User findByUUID(String uuid)
	{
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("uuid", uuid));
		User user = (User)crit.uniqueResult();
		if(user!=null)
		{
			Hibernate.initialize(user.getUserProfiles());
			
			Hibernate.initialize(user.getUserDistricts());
			for(UserDistrict ud : user.getUserDistricts())
			{
				Hibernate.initialize(ud.getDistrict());
			}


			Hibernate.initialize(user.getOrders());
			user.getOrders().forEach(order -> {
				Hibernate.initialize(order.getOrderForm());
				Hibernate.initialize(order.getSchool());
				Hibernate.initialize(order.getDistrict());
			});
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<User> users = (List<User>) criteria.list();
		
		// No need to fetch userProfiles since we are not showing them on list page. Let them lazy load. 
		// Uncomment below lines for eagerly fetching of userProfiles if you want.
		/*
		for(User user : users){
			Hibernate.initialize(user.getUserProfiles());
		}*/
		return users;
	}

	public void save(User user) {
		persist(user);
	}

	public void deleteByUsername(String username) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("username", username));
		User user = (User)crit.uniqueResult();
		delete(user);
	}
	
	public void deleteByUUID(String uuid) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("uuid", uuid));
		User user = (User)crit.uniqueResult();
		delete(user);
	}

	@Override
	public int count(){
		int count = ((Long)getSession().createQuery("select count(*) from User").uniqueResult()).intValue();
		return count;
	}
	

}
