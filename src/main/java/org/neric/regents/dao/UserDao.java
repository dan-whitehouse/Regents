package org.neric.regents.dao;

import java.util.List;

import org.neric.regents.model.User;


public interface UserDao {

	User findById(int id);
	
	User findByUsername(String username);
	
	User findByUUID(String uuid);
	
	List<User> findAllUsers();
	
	void save(User user);
	
	void delete(User user);
	
	void deleteByUsername(String username);

	void deleteByUUID(String uuid);
}

