package org.neric.regents.service;

import org.neric.regents.model.Config;

public interface ConfigService {
	
	Config findById(String id);
	
	Config findByUUID(String uuid);
	
	void saveConfig(Config config);
	
	void updateConfig(Config district);
}