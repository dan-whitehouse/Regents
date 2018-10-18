package org.neric.regents.dao;

import java.util.List;

import org.neric.regents.model.Config;

public interface ConfigDAO {

	Config findById(String id);
	
	Config findByUUID(String uuid);

	void save(Config config);
	
	List<Config> findAll();
}

