package org.neric.regents.service;

import org.neric.regents.model.OrderDocument;

import java.util.List;


public interface OrderDocumentService {

    OrderDocument findById(int id);

//	School findByType(String type);

    List<OrderDocument> findAll();

}
