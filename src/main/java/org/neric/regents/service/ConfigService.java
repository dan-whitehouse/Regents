package org.neric.regents.service;

import java.util.List;

import org.neric.regents.model.Config;
import org.neric.regents.model.District;

public interface ConfigService {
	
	Config findById(String id);
	
	Config findByUUID(String uuid);
	
	void saveConfig(Config config);
	
	void updateConfig(Config district);
	
	List<Config> findAll();
}