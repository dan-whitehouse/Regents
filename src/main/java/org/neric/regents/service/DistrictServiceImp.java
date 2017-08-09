package org.neric.regents.service;

import java.util.List;

import org.neric.regents.dao.DistrictDAO;
import org.neric.regents.model.District;
import org.neric.regents.model.OptionPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("districtService")
@Transactional
public class DistrictServiceImp implements DistrictService{

	@Autowired
	private DistrictDAO dao;

	
	public District findById(int id) {
		return dao.findById(id);
	}

	public District findByCode(String bedsCode) {
		District user = dao.findByCode(bedsCode);
		return user;
	}

	public void saveDistrict(District user) 
	{
		dao.save(user);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateDistrict(District district) 
	{
		District entity = dao.findById(district.getId());
		if(entity!=null)
		{
			entity.setName(district.getName());
			entity.setBedsCode(district.getBedsCode());
		}
	}
	
	public void deleteDistrictByCode(String bedsCode) 
	{
		dao.deleteByCode(bedsCode);
	}

	public List<District> findAllDistricts() 
	{
		return dao.findAllDistricts();
	}

	@Override
	public void lockById(int id, boolean isLocked)
	{
		District entity = dao.findById(id);
		if(entity != null)
		{
			entity.setLocked(isLocked);
		}
	}

	@Override
	public void hideById(int id, boolean isHidden)
	{
		District entity = dao.findById(id);
		if(entity != null)
		{
			entity.setVisible(isHidden);
		}
	}
	
}
