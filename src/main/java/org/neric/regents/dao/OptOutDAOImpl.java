package org.neric.regents.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.neric.regents.model.OptOut;
import org.springframework.stereotype.Repository;



@Repository("optOutDao")
public class OptOutDAOImpl extends AbstractDao<Integer, OptOut> implements OptOutDAO 
{
	public OptOut findById(int id)
	{
		OptOut optOut = getByKey(id);
		
		if(optOut != null)
		{
			Hibernate.initialize(optOut.getId());
		}
		
		return optOut;
	}
	
	@Override
	public OptOut findByUUID(String uuid)
	{
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("uuid", uuid));
		OptOut optOut = (OptOut)crit.uniqueResult();
		
		if(optOut != null)
		{
			Hibernate.initialize(optOut.getDistrict());
			Hibernate.initialize(optOut.getOrderForm());
			Hibernate.initialize(optOut.getOptOutUser());
		}
		return optOut;
	}
	
	@SuppressWarnings("unchecked")
	public List<OptOut> findAllOptOuts()
	{
		Criteria crit = createEntityCriteria();
		crit.addOrder(org.hibernate.criterion.Order.desc("optOutDate"));
		List<OptOut> optOuts = (List<OptOut>)crit.list();
		if(optOuts != null)
		{
			for(OptOut o : optOuts)
			{
				Hibernate.initialize(o.getDistrict());
			}	
		}
		return (List<OptOut>)crit.list();
	}
	
	@Override
	public void save(OptOut optOut)
	{
		persist(optOut);
	}
	
	@Override
	public void deleteById(int id)
	{
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		OptOut optOut = (OptOut)crit.uniqueResult();
		delete(optOut);
	}
	
	@Override
	public void deleteByUUID(String uuid)
	{
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("uuid", uuid));
		OptOut optOut = (OptOut)crit.uniqueResult();
		delete(optOut);
	}

}
