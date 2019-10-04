package org.neric.regents.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.neric.regents.model.OrderDocument;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("orderDocumentDAO")
public class OrderDocumentDAOImpl extends AbstractDao<Integer, OrderDocument> implements OrderDocumentDAO {

    public OrderDocument findById(int id) {
        return getByKey(id);
    }

//	public School findByType(String type) {
//		Criteria crit = createEntityCriteria();
//		crit.add(Restrictions.eq("type", type));
//		return (UserProfile) crit.uniqueResult();
//	}

    @SuppressWarnings("unchecked")
    public List<OrderDocument> findAll() {
        Criteria crit = createEntityCriteria();
        crit.addOrder(Order.asc("id"));
        return (List<OrderDocument>) crit.list();
    }

}
