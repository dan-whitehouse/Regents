package org.neric.regents.dao;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.neric.regents.model.OptionScan;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("optionScanDao")
public class OptionScanDaoImpl extends AbstractDao<Integer, OptionScan> implements OptionScanDao
{
	public OptionScan findById(int id)
	{
		OptionScan option = getByKey(id);
		if(option != null)
		{
			Hibernate.initialize(option.getOrdersScan());
		}
		return option;
	}
	
	@Override
	public OptionScan findByUUID(String uuid)
	{
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("uuid", uuid));
		OptionScan option = (OptionScan)crit.uniqueResult();
		if(option != null)
		{
			Hibernate.initialize(option.getOrdersScan());
		}
		return option;
	}
	
	@SuppressWarnings("unchecked")
	public List<OptionScan> findAllOptionScans()
	{
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (List<OptionScan>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<OptionScan> findAllActiveOptionScans()
	{
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.add(Restrictions.eq("visible", true));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (List<OptionScan>) criteria.list();
	}
	
	public void save(OptionScan optionScan)
	{
		persist(optionScan);	
	}

	public void delete(int id)
	{
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		OptionScan optionScan = (OptionScan)crit.uniqueResult();
		delete(optionScan);
	}

	@Override
	public void deleteByUUID(String uuid)
	{
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("uuid", uuid));
		OptionScan optionScan = (OptionScan)crit.uniqueResult();
		delete(optionScan);
	}
}
