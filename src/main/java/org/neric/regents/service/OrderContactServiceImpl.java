package org.neric.regents.service;

import java.util.List;

import org.neric.regents.dao.OrderContactDAO;
import org.neric.regents.model.OrderContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("orderContactService")
@Transactional
public class OrderContactServiceImpl implements OrderContactService
{	
	@Autowired
	OrderContactDAO dao;
	
	public OrderContact findById(int id) {
		return dao.findById(id);
	}

//	public School findByType(String type){
//		return dao.findByType(type);
//	}

	public List<OrderContact> findAll() {
		return dao.findAll();
	}
}
