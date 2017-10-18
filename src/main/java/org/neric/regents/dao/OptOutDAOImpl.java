package org.neric.regents.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.neric.regents.model.OptOut;
import org.neric.regents.model.Order;
import org.neric.regents.model.OrderForm;
import org.neric.regents.model.User;
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
				Hibernate.initialize(o.getOrderForm());
				Hibernate.initialize(o.getOptOutUser());
			}	
		}
		return (List<OptOut>)crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<OptOut> findAllOptOutsByUsername(String username)
	{
		Criteria crit = getSession().createCriteria(OptOut.class, "o");
		crit.createAlias("o.optOutUser",  "u");
		crit.add(Restrictions.eq("u.username", username));
		crit.addOrder(org.hibernate.criterion.Order.desc("optOutDate"));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		List<OptOut> optOuts = (List<OptOut>)crit.list();
		if(optOuts != null)
		{
			for(OptOut o : optOuts)
			{
				if(o != null)
				{
					Hibernate.initialize(o.getDistrict());
					Hibernate.initialize(o.getOrderForm());
					Hibernate.initialize(o.getOptOutUser());
				}
			}	
		}
		return optOuts;
	}

	@SuppressWarnings("unchecked")
	public List<OptOut> findAllOptOutsByUserUUID(String uuid)
	{
		Criteria crit = getSession().createCriteria(OptOut.class, "o");
		crit.createAlias("o.optOutUser",  "u");
		crit.add(Restrictions.eq("u.uuid", uuid));
		crit.addOrder(org.hibernate.criterion.Order.desc("optOutDate"));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		List<OptOut> optOuts = (List<OptOut>)crit.list();
		if(optOuts != null)
		{
			for(OptOut o : optOuts)
			{
				if(o != null)
				{
					Hibernate.initialize(o.getDistrict());
					Hibernate.initialize(o.getOrderForm());
					Hibernate.initialize(o.getOptOutUser());
				}
			}
		}
		return optOuts;
	}
	
	@SuppressWarnings("unchecked")
	public List<OptOut> findAllOptOutsByUserAndOrderForm(User user, OrderForm orderForm)
	{
		Criteria crit = getSession().createCriteria(OptOut.class, "o");
		crit.createAlias("o.optOutUser",  "u");
		crit.createAlias("o.orderForm",  "of");
		crit.add(Restrictions.eq("u.uuid", user.getUuid()));
		crit.add(Restrictions.eq("of.uuid", orderForm.getUuid()));
		crit.addOrder(org.hibernate.criterion.Order.desc("optOutDate"));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		List<OptOut> optOuts = (List<OptOut>)crit.list();
		if(optOuts != null)
		{
			for(OptOut o : optOuts)
			{
				if(o != null)
				{
					Hibernate.initialize(o.getDistrict());
					Hibernate.initialize(o.getOrderForm());
					Hibernate.initialize(o.getOptOutUser());
				}
			}	
		}
		return optOuts;
	}
	
	@SuppressWarnings("unchecked")
	public List<OptOut> findAllActiveOptOuts(int orderFormId)
	{
		Criteria crit = createEntityCriteria();
		crit.addOrder(org.hibernate.criterion.Order.desc("optOutDate"));
		crit.add(Restrictions.eq("orderForm.id", orderFormId));
		List<OptOut> optOuts = (List<OptOut>)crit.list();
		if(optOuts != null)
		{
			for(OptOut o : optOuts)
			{
				Hibernate.initialize(o.getDistrict());
				Hibernate.initialize(o.getOrderForm());
				Hibernate.initialize(o.getOptOutUser());
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

	@Override
	public int countByActiveOrderForm(int id){
		try {
			Criteria criteria = getSession().createCriteria(OptOut.class, "optOut");
			criteria.add(Restrictions.eq("orderForm.id", id));
			criteria.setProjection(Projections.countDistinct("district.id"));
			Number numRows = (Number) criteria.uniqueResult();
			return numRows.intValue();
		}
		catch(Exception e) { return 0;}
	}

}
