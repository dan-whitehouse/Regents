package org.neric.regents.dao;

import java.util.List;

import org.neric.regents.model.Document;
import org.neric.regents.model.OrderContact;
import org.neric.regents.model.OrderDocument;
import org.neric.regents.model.School;

public interface OrderContactDAO {

	List<OrderContact> findAll();
	
	//School findByType(String type);
	
	OrderContact findById(int id);
}
