package org.neric.regents.dao;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.neric.regents.model.District;
import org.neric.regents.model.OptOut;
import org.neric.regents.model.Order;
import org.neric.regents.model.dashboard.DistrictOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;


@Repository("districtDAO")
public class DistrictDAOImpl extends AbstractDao<Integer, District> implements DistrictDAO {

	static final Logger logger = LoggerFactory.getLogger(DistrictDAOImpl.class);
	
	public District findById(int id) 
	{
		return getByKey(id);
	}

	public District findByCode(String bedsCode) 
	{
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("code", bedsCode));
		District district = (District)crit.uniqueResult();
		return district;
	}
	
	@Override
	public District findByUUID(String uuid)
	{
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("uuid", uuid));
		District district = (District)crit.uniqueResult();

		Hibernate.initialize(district.getUserDistricts());
		Hibernate.initialize(district.getSchools());
		return district;
	}


	@SuppressWarnings("unchecked")
	public List<District> findAllDistricts() 
	{
		Criteria criteria = createEntityCriteria().addOrder(org.hibernate.criterion.Order.asc("name"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<District> districts = (List<District>) criteria.list();
		return districts;
	}
	
	/*@SuppressWarnings("unchecked")
	public List<District> findAllActiveDistricts() 
	{
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("name"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<District> districts = (List<District>) criteria.list();
		//return districts;
		return districts.stream().filter(d-> d.getVisible()).collect(Collectors.toList());
	}*/

	public void save(District district) {
		persist(district);
	}

	public void deleteByCode(String bedsCode) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("code", bedsCode));
		District district = (District)crit.uniqueResult();
		delete(district);
	}

	@Override
	public void deleteByUUID(String uuid)
	{
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("uuid", uuid));
		District district = (District)crit.uniqueResult();
		delete(district);
	}

	@Override
	public int count(){
		int count = ((Long)getSession().createQuery("select count(*) from District").uniqueResult()).intValue();
		return count;
	}

	@Override
	public List<District> findAllUndecidedDistrictsByActiveOrderForm(String uuid) {
		List<District> orderedDistricts = new ArrayList<>();
		List<DistrictOrder> ordered = findAllOrderedDistrictsByActiveOrderForm(uuid);
		ordered.forEach(o -> orderedDistricts.add(o.getDistrict()));
		
		
		List<District> all = findAllDistricts();

		Collection<District> subtracted = CollectionUtils.subtract(all, orderedDistricts);
		List<District> districts = new ArrayList<>(subtracted);
		return districts;
	}

	@Override
	public List<DistrictOrder> findAllOrderedDistrictsByActiveOrderForm(String uuid) {
		Criteria criteria = getSession().createCriteria(Order.class,"o");
		criteria.createAlias("o.orderForm",  "of");
		criteria.add(Restrictions.eq("of.uuid", uuid));
		criteria.addOrder(org.hibernate.criterion.Order.asc("o.uuid"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		
		List<Order> orders = (List<Order>) criteria.list();
		orders.forEach(order -> {
			Hibernate.initialize(order.getDistrict());
		});
		
		//https://stackoverflow.com/questions/31537763/java8-stream-groupingby-enum-and-counting
		List<DistrictOrder> districtsOrders = new ArrayList<>();
		Map<District, Long> districtOrderMap = orders.stream().collect(Collectors.groupingBy(Order::getDistrict, Collectors.counting()));
		districtOrderMap.forEach((k,v) -> districtsOrders.add(new DistrictOrder(k, v)));
		
		for(DistrictOrder districtOrder : districtsOrders) {
			for(Order order : orders) {
				if(StringUtils.equalsIgnoreCase(districtOrder.getDistrict().getUuid(), order.getDistrict().getUuid())) {
					districtOrder.getOrders().add(order);
				}
			}
		}
		return districtsOrders;
	}

	@Override
	public List<District> findAllNADistrictsByActiveOrderForm(String uuid) {
		Criteria criteria = getSession().createCriteria(OptOut.class,"o");
		criteria.createAlias("o.orderForm",  "of");
		criteria.add(Restrictions.eq("of.uuid", uuid));
		criteria.addOrder(org.hibernate.criterion.Order.asc("o.uuid"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<OptOut> optOuts = (List<OptOut>) criteria.list();
		List<District> districts = new ArrayList<>();
		Set<District> set = new HashSet<>();
		optOuts.forEach(optOut -> {
			Hibernate.initialize(optOut.getDistrict());
			set.add(optOut.getDistrict());
		});
		districts.addAll(set);
		return districts;
	}
}
