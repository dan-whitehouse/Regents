package org.neric.regents.service;

import java.util.List;
import java.util.Set;

import org.neric.regents.model.District;
import org.neric.regents.model.School;
import org.neric.regents.model.UserProfile;


public interface SchoolService {

	School findById(int id);

	List<School> findAllByDistrictId(int id);
	
//	School findByType(String type);
	
	List<School> findAll();

	void update(School school);

	void updateAll(Set<School> set);
	
	void deleteById(int id);

	void save(School school);
	
}
