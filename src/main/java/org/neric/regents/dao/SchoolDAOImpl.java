package org.neric.regents.dao;

import java.util.List;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.neric.regents.model.Document;
import org.neric.regents.model.OrderContact;
import org.neric.regents.model.School;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


@Repository("schoolDAO")
public class SchoolDAOImpl extends AbstractDao<Integer, School> implements SchoolDAO{

	public School findById(int id) 
	{
		School school = getByKey(id);
		if(school != null)
		{
			Hibernate.initialize(school.getDistrict());
		}
		return school;
	}
	
	@Override
	public School findByUUID(String uuid)
	{
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("uuid", uuid));
		School school = (School)crit.uniqueResult();
		if(school != null)
		{
			Hibernate.initialize(school.getDistrict());
		}
		return school;
	}

	@SuppressWarnings("unchecked")
	public List<School> findAll()
	{
		Criteria crit = createEntityCriteria();
		crit.addOrder(Order.asc("name"));
		
		for(School school : (List<School>)crit.list())
		{
			Hibernate.initialize(school.getDistrict());	
		}	
		return (List<School>)crit.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<School> findAllByDistrictId(int id) 
	{
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("district.id", id));
		crit.addOrder(Order.asc("name"));
		List<School> schools = (List<School>)crit.list();
		
		for(School school : schools)
		{
			Hibernate.initialize(school.getDistrict());	
		}
		return schools.stream().filter(sch-> sch.getVisible()).collect(Collectors.toList());
	}

	@Override
	public void save(School school) 
	{
		persist(school);
	}

	@Override
	public void deleteById(int id) 
	{
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		School school = (School)crit.uniqueResult();
		delete(school);
	}

	@Override
	public void deleteByUUID(String uuid)
	{
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("uuid", uuid));
		School school = (School)crit.uniqueResult();
		delete(school);
	}
	
}
