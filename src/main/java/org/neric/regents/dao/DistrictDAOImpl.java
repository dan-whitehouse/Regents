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
		District district = getByKey(id);
		if(district!=null){
			Hibernate.initialize(district.getSchools());
		}
		return district;
	}

	public District findByCode(String bedsCode) 
	{
		logger.info("BEDs Code : {}", bedsCode);
		
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("code", bedsCode));
		District district = (District)crit.uniqueResult();
		if(district!=null)
		{
			Hibernate.initialize(district.getSchools());
		}
		return district;
	}

	@SuppressWarnings("unchecked")
	public List<District> findAllDistricts() 
	{
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("name"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<District> districts = (List<District>) criteria.list();
		
		// No need to fetch userProfiles since we are not showing them on list page. Let them lazy load. 
		// Uncomment below lines for eagerly fetching of userProfiles if you want.
		/*
		for(User user : users){
			Hibernate.initialize(user.getUserProfiles());
		}*/
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
}
