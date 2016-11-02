package org.neric.regents.service;

import java.util.List;

import org.neric.regents.model.School;
import org.neric.regents.model.Setting;
import org.neric.regents.model.UserProfile;


public interface SettingService {

	Setting findById(int id);

//	School findByType(String type);
	
	List<Setting> findAll();
	
}
