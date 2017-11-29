package org.neric.regents.dao;

import java.util.List;

import org.neric.regents.model.District;
import org.neric.regents.model.dashboard.DistrictOrder;


public interface DistrictDAO {

	District findById(int id);
	
	District findByUUID(String uuid);
	
	District findByCode(String bedsCode);
	
	List<District> findAllDistricts();
	
	void save(District district);
	
	void deleteByCode(String bedsCode);
	
	void deleteByUUID(String uuid);

	int count();

	List<District> findAllUndecidedDistrictsByActiveOrderForm(String uuid);

	List<DistrictOrder> findAllOrderedDistrictsByActiveOrderForm(String uuid);

	List<District> findAllNADistrictsByActiveOrderForm(String uuid);
}

