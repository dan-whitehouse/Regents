package org.neric.regents.service;

import org.neric.regents.model.User;
import org.neric.regents.test.UserPassword;

import java.util.List;


public interface UserService {
	
	User findById(int id);
	
	User findByUUID(String uuid);
	
	User findByUsername(String username);
	
	List<User> findAllUsers(); 
	
	List<User> findAllUsersByDistrictUuId(String uuid);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void updatePassword(UserPassword userPassword);

	void deleteUserByUsername(String username);
	
	void deleteUserByUUID(String uuid);
	
	void deleteUser(User user);

	void lockByUUID(String uuid, boolean isLocked);

	void hideByUUID(String uuid, boolean isHidden); 
	
	boolean isUserUsernameUnique(Integer id, String username);

	int count();

}