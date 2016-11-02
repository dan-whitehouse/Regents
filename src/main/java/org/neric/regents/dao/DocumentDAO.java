package org.neric.regents.dao;

import java.util.List;

import org.neric.regents.model.Document;
import org.neric.regents.model.School;

public interface DocumentDAO {

	List<Document> findAll();
	
	//School findByType(String type);
	
	Document findById(int id);
}
