package org.neric.regents.dao;

import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.neric.regents.model.Config;
import org.neric.regents.model.District;
import org.neric.regents.model.OptOut;
import org.neric.regents.model.Order;
import org.neric.regents.model.User;
import org.neric.regents.model.dashboard.DistrictOrder;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mysql.fabric.xmlrpc.base.Array;



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
		return null;
	}

	public void save(Config district) {
		persist(district);
	}
}
