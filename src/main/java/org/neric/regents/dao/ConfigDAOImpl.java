package org.neric.regents.dao;

import java.util.*;
import org.neric.regents.model.Config;
import org.neric.regents.model.District;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;



@Repository("configDAO")
public class ConfigDAOImpl extends AbstractDao<String, Config> implements ConfigDAO {

	static final Logger logger = LoggerFactory.getLogger(ConfigDAOImpl.class);
	
	public Config findById(String id) 
	{
		return getByKey(id);
	}
	
	@Override
	public Config findByUUID(String uuid)
	{
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("uuid", uuid));
		Config config = (Config)crit.uniqueResult();

		return config;
	}

	public void save(Config config) {
		persist(config);
	}

	@Override
	public List<Config> findAll() {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Config> configurables = (List<Config>) criteria.list();
		return configurables;
	}
}
