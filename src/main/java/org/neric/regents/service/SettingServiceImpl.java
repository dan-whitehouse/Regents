package org.neric.regents.service;

import java.util.List;

import org.neric.regents.dao.SchoolDAO;
import org.neric.regents.dao.SettingDAO;
import org.neric.regents.dao.UserProfileDao;
import org.neric.regents.model.Exam;
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
	
	public Setting findById(int id) 
	{
		return dao.findById(id);
	}

	public List<Setting> findAll() {
		return dao.findAll();
	}

	@Override
	public Setting findByKey(String key)
	{
		return dao.findByKey(key);
	}


	@Override
	public void saveSetting(Setting setting)
	{
		dao.save(setting);
	}


	@Override
	public void updateSetting(Setting setting)
	{
		Setting entity = dao.findById(setting.getId());
		if(entity != null)
		{
			entity.setKey(setting.getKey());
			entity.setValue(setting.getValue());
		}
	}


	@Override
	public void deleteBySettingId(int id)
	{
		dao.deleteBySettingId(id);
	}
}
