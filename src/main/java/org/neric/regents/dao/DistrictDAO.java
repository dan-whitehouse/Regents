package org.neric.regents.dao;

import java.util.List;

import org.neric.regents.model.District;


public interface DistrictDAO {

	District findById(int id);
	
	District findByCode(String bedsCode);
	
	void save(District district);
	
	void deleteByCode(String bedsCode);
	
	List<District> findAllDistricts();

}

