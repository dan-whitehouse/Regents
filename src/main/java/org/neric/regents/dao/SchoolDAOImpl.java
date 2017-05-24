package org.neric.regents.dao;

import java.util.List;

import org.neric.regents.model.Document;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<School> findAllByDistrictId(int id) 
	{
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("district.id", id));
		crit.addOrder(Order.asc("name"));
		return (List<School>)crit.list();
	}
	
}
