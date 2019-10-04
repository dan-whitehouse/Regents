package org.neric.regents.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.neric.regents.model.OrderExam;
import org.springframework.stereotype.Repository;


@Repository("orderExamDAO")
public class OrderExamDAOImpl extends AbstractDao<Integer, OrderExam> implements OrderExamDAO {

    public OrderExam findById(int id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    public List<OrderExam> findAllOrderExams() {
        Criteria crit = createEntityCriteria();
        crit.addOrder(org.hibernate.criterion.Order.asc("id"));
        return (List<OrderExam>) crit.list();
    }

    @Override
    public void saveOrderExam(OrderExam orderExam) {
        persist(orderExam);

    }

    @Override
    public void deleteOrderExam(int id) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));
        OrderExam orderExam = (OrderExam) crit.uniqueResult();
        delete(orderExam);
    }

}
