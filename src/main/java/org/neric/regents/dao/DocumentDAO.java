package org.neric.regents.dao;

import java.util.List;

import org.neric.regents.model.Document;
import org.neric.regents.model.Exam;
import org.neric.regents.model.School;

public interface DocumentDAO {

	List<Document> findAll();
	
	List<Document> findAllActiveDocuments();
	
	//School findByType(String type);
	
	Document findById(int id);
	
	void save(Document document);

	void deleteByDocumentId(int id);
}
