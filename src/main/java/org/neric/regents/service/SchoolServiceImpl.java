package org.neric.regents.service;

import java.util.List;
import java.util.Set;

import org.neric.regents.dao.SchoolDAO;
import org.neric.regents.dao.UserProfileDao;
import org.neric.regents.model.District;
import org.neric.regents.model.Document;
import org.neric.regents.model.School;
import org.neric.regents.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("schoolService")
@Transactional
public class SchoolServiceImpl implements SchoolService{
	
	@Autowired
	SchoolDAO dao;
	
	public School findById(int id) {
		return dao.findById(id);
	}
	
	@Override
	public School findByUUID(String uuid)
	{
		return dao.findByUUID(uuid);
	}

	public List<School> findAll() {
		return dao.findAll();
	}


	@Override
	public List<School> findAllByDistrictId(int id) {
		return dao.findAllByDistrictId(id);
	}


	@Override
	public void save(School school) 
	{
		dao.save(school);
	}

	@Override
	public void update(School school) 
	{
		School entity = dao.findById(school.getId());
		if(entity != null)
		{
			entity.setName(school.getName());
			entity.setDistrict(school.getDistrict());
		}
	}
	
	@Override
	public void updateAll(Set<School> schools) 
	{
		for(School sch : schools)
		{
			School entity = dao.findById(sch.getId());
			if(entity != null)
			{
				entity.setName(sch.getName());
				entity.setDistrict(sch.getDistrict());
			}
		}
		
	}

	@Override
	public void deleteById(int id) 
	{
		dao.deleteById(id);
	}

	@Override
	public void deleteByUUID(String uuid)
	{
		dao.deleteByUUID(uuid);
	}
	
	@Override
	public void lockById(int id, boolean isLocked)
	{
		School entity = dao.findById(id);
		if(entity != null)
		{
			entity.setLocked(isLocked);
		}
	}
	
	@Override
	public void lockByUUID(String uuid, boolean isLocked)
	{
		School entity = dao.findByUUID(uuid);
		if(entity != null)
		{
			entity.setLocked(isLocked);
		}
	}

	@Override
	public void hideById(int id, boolean isHidden)
	{
		School entity = dao.findById(id);
		if(entity != null)
		{
			entity.setVisible(isHidden);
		}
	}

	@Override
	public void hideByUUID(String uuid, boolean isHidden)
	{
		School entity = dao.findByUUID(uuid);
		if(entity != null)
		{
			entity.setVisible(isHidden);
		}
	}
}
