package org.neric.regents.service;

import java.util.List;

import org.neric.regents.dao.DocumentDAO;
import org.neric.regents.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("documentService")
@Transactional
public class DocumentServiceImpl implements DocumentService{
	
	@Autowired
	DocumentDAO dao;
	
	public Document findById(int id) {
		return dao.findById(id);
	}

//	public School findByType(String type){
//		return dao.findByType(type);
//	}

	public List<Document> findAllDocuments() {
		return dao.findAll();
	}
}
