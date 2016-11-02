package org.neric.regents.dao;

import java.util.List;

import org.neric.regents.model.Document;
import org.neric.regents.model.OrderDocument;
import org.neric.regents.model.School;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


@Repository("orderDocumentDAO")
public class OrderDocumentDAOImpl extends AbstractDao<Integer, OrderDocument> implements OrderDocumentDAO{

	public OrderDocument findById(int id) {
		return getByKey(id);
	}

//	public School findByType(String type) {
//		Criteria crit = createEntityCriteria();
//		crit.add(Restrictions.eq("type", type));
//		return (UserProfile) crit.uniqueResult();
//	}
	
	@SuppressWarnings("unchecked")
	public List<OrderDocument> findAll()
	{
		Criteria crit = createEntityCriteria();
		crit.addOrder(Order.asc("id"));
		return (List<OrderDocument>)crit.list();
	}
	
}
