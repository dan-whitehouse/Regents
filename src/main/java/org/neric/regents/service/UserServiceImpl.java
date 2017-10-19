package org.neric.regents.service;

import org.neric.regents.dao.UserDao;
import org.neric.regents.model.District;
import org.neric.regents.model.User;
import org.neric.regents.test.UserPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.authentication.PasswordEncoderParser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao dao;

	@Autowired
    private PasswordEncoder passwordEncoder;
	
	public User findById(int id) {
		return dao.findById(id);
	}

	public User findByUsername(String username) {
		User user = dao.findByUsername(username);
		return user;
	}

	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		dao.save(user);
	}
	
	
	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateUser(User user) {
		User entity = dao.findById(user.getId());
		if(entity!=null){
			entity.setUsername(user.getUsername());
			if(!user.getPassword().equals(entity.getPassword())){
				entity.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setUserProfiles(user.getUserProfiles());
			
			entity.getUserDistricts().clear();
			entity.getUserDistricts().addAll(user.getUserDistricts());
		}
	}
	
	public void updatePassword(UserPassword userPassword) 
	{
		User entity = dao.findById(userPassword.getUserId());
		if(entity!=null)
		{
			if(userPassword.getNewPassword().equalsIgnoreCase(userPassword.getNewPasswordConfirm())) //Do both new passwords match
			{
				if(passwordEncoder.matches(userPassword.getOldPassword(), entity.getPassword())) //Does provided old password match the encrypted old password
				{
					if(!passwordEncoder.matches(userPassword.getNewPassword(), entity.getPassword())) //Does the new password not match the old encrypted password
					{
						entity.setPassword(passwordEncoder.encode(userPassword.getNewPassword())); //Update the User's password
					}
				}
			}	
		}
	}

	public List<User> findAllUsers() 
	{
		return dao.findAllUsers();
	}

	public boolean isUserUsernameUnique(Integer id, String username) 
	{
		User user = findByUsername(username);
		return ( user == null || ((id != null) && (user.getId() == id)));
	}

	@Override
	public User findByUUID(String uuid)
	{
		User user = dao.findByUUID(uuid);
		return user;
	}

	public void deleteUserByUsername(String username) 
	{
		dao.deleteByUsername(username);
	}
	
	public void deleteUserByUUID(String uuid) 
	{
		dao.deleteByUUID(uuid);
	}
	
	@Override
	public void deleteUser(User user)
	{
		dao.delete(user);	
	}

	@Override
	public void lockByUUID(String uuid, boolean isLocked)
	{
		User entity = dao.findByUUID(uuid);
		if(entity != null)
		{
			entity.setLocked(isLocked);
		}
	}

	@Override
	public void hideByUUID(String uuid, boolean isHidden)
	{
		User entity = dao.findByUUID(uuid);
		if(entity != null)
		{
			entity.setVisible(isHidden);
		}
	}

	@Override
	public int count()
	{
		return dao.count();
	}
}
