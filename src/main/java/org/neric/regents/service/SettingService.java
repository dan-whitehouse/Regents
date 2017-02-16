package org.neric.regents.service;

import java.util.List;
import org.neric.regents.model.Setting;

public interface SettingService {

	Setting findById(int id);

	Setting findByKey(String key);
	
	List<Setting> findAll();
	
	void saveSetting(Setting setting);

	void updateSetting(Setting setting);

	void deleteBySettingId(int id);
}
