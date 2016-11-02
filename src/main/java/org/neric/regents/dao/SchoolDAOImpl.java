package org.neric.regents.dao;

import java.util.List;

import org.neric.regents.model.School;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


@Repository("schoolDAO")
public class SchoolDAOImpl extends AbstractDao<Integer, School> implements SchoolDAO{

	public School findById(int id) {
		return getByKey(id);
	}

//	public School findByType(String type) {
//		Criteria crit = createEntityCriteria();
//		crit.add(Restrictions.eq("type", type));
//		return (UserProfile) crit.uniqueResult();
//	}
	
	@SuppressWarnings("unchecked")
	public List<School> findAll()
	{
		Criteria crit = createEntityCriteria();
		crit.addOrder(Order.asc("name"));
		return (List<School>)crit.list();
	}
	
}
