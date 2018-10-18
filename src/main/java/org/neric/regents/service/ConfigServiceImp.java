package org.neric.regents.service;

import java.util.List;

import org.neric.regents.dao.ConfigDAO;
import org.neric.regents.model.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("configService")
@Transactional
public class ConfigServiceImp implements ConfigService{

	@Autowired
	private ConfigDAO dao;

	public Config findById(String id) {
		return dao.findById(id);
	}
	
	@Override
	public Config findByUUID(String uuid)
	{
		return dao.findByUUID(uuid);
	}

	public void saveConfig(Config user) 
	{
		dao.save(user);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	@Override
	public void updateConfig(Config config) 
	{
		Config entity = dao.findById(config.getId());
		if(entity!=null)
		{
			entity.setData(config.getData());
		}
	}

	@Override
	public List<Config> findAll() {
		return dao.findAll();
	}
}
