package org.neric.regents.dao;

import org.neric.regents.model.User;

import java.util.List;


public interface UserDao {

	User findById(int id);
	
	User findByUsername(String username);
	
	User findByUUID(String uuid);
	
	List<User> findAllUsers();
	
	List<User> findAllUsersByDistrictUuId(String uuid);
	
	void save(User user);
	
	void delete(User user);
	
	void deleteByUsername(String username);

	void deleteByUUID(String uuid);

	int count();
}

