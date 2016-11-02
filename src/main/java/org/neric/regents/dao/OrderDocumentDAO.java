package org.neric.regents.dao;

import java.util.List;

import org.neric.regents.model.Document;
import org.neric.regents.model.OrderDocument;
import org.neric.regents.model.School;

public interface OrderDocumentDAO {

	List<OrderDocument> findAll();
	
	//School findByType(String type);
	
	OrderDocument findById(int id);
}
