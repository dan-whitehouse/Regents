package org.neric.regents.service;

import java.util.List;

import org.neric.regents.dao.SchoolDAO;
import org.neric.regents.dao.UserProfileDao;
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

//	public School findByType(String type){
//		return dao.findByType(type);
//	}

	public List<School> findAll() {
		return dao.findAll();
	}
}
