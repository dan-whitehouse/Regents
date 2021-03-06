package org.neric.regents.service;

import java.util.List;

import org.neric.regents.model.District;
import org.neric.regents.model.User;
import org.neric.regents.model.dashboard.DistrictOrder;


public interface DistrictService {
	
	District findById(int id);
	
	District findByUUID(String uuid);
	
	District findByCode(String bedsCode);
	
	void saveDistrict(District district);
	
	void updateDistrict(District district);
	
	void deleteByUUID(String uuid);
	
	void deleteDistrictByCode(String bedsCode);

	List<District> findAllDistricts();

	void lockById(int id, boolean isLocked);
	
	void lockByUUID(String uuid, boolean isLocked);

	void hideById(int id, boolean isHidden);

	void hideByUUID(String uuid, boolean isHidden);

	int count();

    List<District> findAllUndecidedDistrictsByActiveOrderForm(String uuid);

	List<DistrictOrder> findAllOrderedDistrictsByActiveOrderForm(String uuid);

	List<District> findAllNADistrictsByActiveOrderForm(String uuid);
}