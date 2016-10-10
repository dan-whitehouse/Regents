package org.neric.regents.dao;

import java.util.List;

import org.neric.regents.model.UserProfile;


public interface UserProfileDao {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(int id);
}
