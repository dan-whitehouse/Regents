package org.neric.regents.service;

import java.util.List;

import org.neric.regents.model.School;
import org.neric.regents.model.UserProfile;


public interface SchoolService {

	School findById(int id);

//	School findByType(String type);
	
	List<School> findAll();
	
}
