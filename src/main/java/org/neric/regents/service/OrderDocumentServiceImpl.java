package org.neric.regents.service;

import java.util.List;

import org.neric.regents.dao.OrderDocumentDAO;
import org.neric.regents.model.OrderDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("orderDocumentService")
@Transactional
public class OrderDocumentServiceImpl implements OrderDocumentService{
	
	@Autowired
	OrderDocumentDAO dao;
	
	public OrderDocument findById(int id) {
		return dao.findById(id);
	}

//	public School findByType(String type){
//		return dao.findByType(type);
//	}

	public List<OrderDocument> findAll() {
		return dao.findAll();
	}
}
