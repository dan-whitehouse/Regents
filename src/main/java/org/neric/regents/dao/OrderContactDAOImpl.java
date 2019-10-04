package org.neric.regents.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.neric.regents.model.OrderContact;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("orderContactDAO")
public class OrderContactDAOImpl extends AbstractDao<Integer, OrderContact> implements OrderContactDAO {

    public OrderContact findById(int id) {
        return getByKey(id);
    }

//	public School findByType(String type) {
//		Criteria crit = createEntityCriteria();
//		crit.add(Restrictions.eq("type", type));
//		return (UserProfile) crit.uniqueResult();
//	}

    @SuppressWarnings("unchecked")
    public List<OrderContact> findAll() {
        Criteria crit = createEntityCriteria();
        crit.addOrder(Order.asc("id"));
        return (List<OrderContact>) crit.list();
    }

}
