package org.neric.regents.dao;

import org.neric.regents.model.UserProfile;

import java.util.List;


public interface UserProfileDao {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(int id);
}
