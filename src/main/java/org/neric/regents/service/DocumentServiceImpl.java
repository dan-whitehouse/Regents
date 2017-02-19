package org.neric.regents.service;

import java.util.List;

import org.neric.regents.dao.DocumentDAO;
import org.neric.regents.model.Document;
import org.neric.regents.model.Exam;
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

	@Override
	public void updateDocument(Document document)
	{
		Document entity = dao.findById(document.getId());
		if(entity != null)
		{
			entity.setName(document.getName());
		}
	}

	@Override
	public void deleteByDocumentId(int id)
	{
		dao.deleteByDocumentId(id);
	}

	@Override
	public void saveDocument(Document document)
	{
		dao.save(document);
	}

	@Override
	public void lockByDocumentId(int id, Boolean isLocked)
	{
		Document entity = dao.findById(id);
		if(entity != null)
		{
			entity.setLocked(isLocked);
		}
	}

	@Override
	public void hideByDocumentId(int id, Boolean isHidden)
	{
		Document entity = dao.findById(id);
		if(entity != null)
		{
			entity.setVisible(isHidden);
		}
		
	}
}
