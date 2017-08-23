package org.neric.regents.dao;

import java.util.List;

import org.neric.regents.model.District;
import org.neric.regents.model.User;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;



@Repository("districtDAO")
public class DistrictDAOImpl extends AbstractDao<Integer, District> implements DistrictDAO {

	static final Logger logger = LoggerFactory.getLogger(DistrictDAOImpl.class);
	
	public District findById(int id) 
	{
		return getByKey(id);
	}

	public District findByCode(String bedsCode) 
	{
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("code", bedsCode));
		District district = (District)crit.uniqueResult();
		return district;
	}
	
	@Override
	public District findByUUID(String uuid)
	{
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("uuid", uuid));
		District district = (District)crit.uniqueResult();
		return district;
	}

	@SuppressWarnings("unchecked")
	public List<District> findAllDistricts() 
	{
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("name"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<District> districts = (List<District>) criteria.list();
		return districts;
	}

	public void save(District district) {
		persist(district);
	}

	public void deleteByCode(String bedsCode) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("code", bedsCode));
		District district = (District)crit.uniqueResult();
		delete(district);
	}

	@Override
	public void deleteByUUID(String uuid)
	{
		// TODO Auto-generated method stub
		
	}
}
