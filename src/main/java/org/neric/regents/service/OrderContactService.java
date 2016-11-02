package org.neric.regents.service;

import java.util.List;

import org.neric.regents.model.OrderContact;


public interface OrderContactService {

	OrderContact findById(int id);

//	School findByType(String type);
	
	List<OrderContact> findAll();
	
}
