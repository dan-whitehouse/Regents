package org.neric.regents.service;

import java.util.List;

import org.neric.regents.model.Document;
import org.neric.regents.model.School;
import org.neric.regents.model.UserProfile;


public interface DocumentService {

	Document findById(int id);

//	School findByType(String type);
	
	List<Document> findAllDocuments();
	
}
