package org.neric.regents.service;

import java.util.List;

import org.neric.regents.dao.SchoolDAO;
import org.neric.regents.dao.SettingDAO;
import org.neric.regents.dao.UserProfileDao;
import org.neric.regents.model.School;
import org.neric.regents.model.Setting;
import org.neric.regents.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("settingService")
@Transactional
public class SettingServiceImpl implements SettingService{
	
	@Autowired
	SettingDAO dao;
	
	public Setting findById(int id) {
		return dao.findById(id);
	}

//	public School findByType(String type){
//		return dao.findByType(type);
//	}

	public List<Setting> findAll() {
		return dao.findAll();
	}
}
