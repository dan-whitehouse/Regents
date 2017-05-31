package org.neric.regents.service;

import java.util.List;

import org.neric.regents.model.User;
import org.neric.regents.test.UserPassword;


public interface UserService {
	
	User findById(int id);
	
	User findByUsername(String username);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void updatePassword(UserPassword userPassword);

	void deleteUserByUsername(String username);

	List<User> findAllUsers(); 
	
	boolean isUserUsernameUnique(Integer id, String username);

}