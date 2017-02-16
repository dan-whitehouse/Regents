package org.neric.regents.service;

import java.util.List;

import org.neric.regents.model.Document;
import org.neric.regents.model.Exam;
import org.neric.regents.model.School;
import org.neric.regents.model.UserProfile;


public interface DocumentService {

	Document findById(int id);

//	School findByType(String type);
	
	List<Document> findAllDocuments();
	
	void saveDocument(Document document);

	void updateDocument(Document document);

	void deleteByDocumentId(int id);
	
	void lockByDocumentId(int id, Boolean locked);
	
	void hideByDocumentId(int id, Boolean hidden);
	
}
