package org.neric.regents.dao;

import java.util.List;

import org.neric.regents.model.School;
import org.neric.regents.model.Setting;

public interface SettingDAO {

	List<Setting> findAll();

	Setting findById(int id);
	
	Setting findByKey(String key);

	void deleteBySettingId(int id);

	void save(Setting setting);
}
