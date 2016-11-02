package org.neric.regents.dao;

import java.util.List;

import org.neric.regents.model.School;

public interface SchoolDAO {

	List<School> findAll();
	
	//School findByType(String type);
	
	School findById(int id);
}
