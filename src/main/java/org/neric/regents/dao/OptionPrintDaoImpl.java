package org.neric.regents.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.neric.regents.model.OptionPrint;
import org.springframework.stereotype.Repository;

@Repository("optionPrintDao")
public class OptionPrintDaoImpl extends AbstractDao<Integer, OptionPrint> implements OptionPrintDao {
    public OptionPrint findById(int id) {
        OptionPrint option = getByKey(id);
        if(option != null) {
            Hibernate.initialize(option.getOrdersPrint());
        }
        return option;
    }

    @Override
    public OptionPrint findByUUID(String uuid) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("uuid", uuid));
        OptionPrint optionPrint = (OptionPrint) crit.uniqueResult();
        if(optionPrint != null) {
            Hibernate.initialize(optionPrint.getOrdersPrint());
        }
        return optionPrint;
    }

    @SuppressWarnings("unchecked")
    public List<OptionPrint> findAllOptionPrints() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
	    return (List<OptionPrint>) criteria.list();
    }

    @Override
    public List<OptionPrint> findAllActiveOptionPrints() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
        criteria.add(Restrictions.eq("visible", true));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
	    return (List<OptionPrint>) criteria.list();
    }

    public void save(OptionPrint optionPrint) {
        persist(optionPrint);
    }

    public void delete(int id) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));
        OptionPrint optionPrint = (OptionPrint) crit.uniqueResult();
        delete(optionPrint);
    }

    @Override
    public void deleteByUUID(String uuid) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("uuid", uuid));
        OptionPrint optionPrint = (OptionPrint) crit.uniqueResult();
        delete(optionPrint);
    }
}
