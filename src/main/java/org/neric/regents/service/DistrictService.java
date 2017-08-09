package org.neric.regents.service;

import java.util.List;

import org.neric.regents.model.District;
import org.neric.regents.model.User;


public interface DistrictService {
	
	District findById(int id);
	
	District findByCode(String bedsCode);
	
	void saveDistrict(District district);
	
	void updateDistrict(District district);
	
	void deleteDistrictByCode(String bedsCode);

	List<District> findAllDistricts();

	void lockById(int id, boolean isLocked);

	void hideById(int id, boolean isHidden); 
}