package org.neric.regents.dao;

import java.util.List;

import org.neric.regents.model.School;
import org.neric.regents.model.Setting;

public interface SettingDAO {

	List<Setting> findAll();
	
	//School findByType(String type);
	
	Setting findById(int id);
}
