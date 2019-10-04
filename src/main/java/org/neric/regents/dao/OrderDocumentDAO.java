package org.neric.regents.dao;

import org.neric.regents.model.OrderDocument;

import java.util.List;

public interface OrderDocumentDAO {

    List<OrderDocument> findAll();

    //School findByType(String type);

    OrderDocument findById(int id);
}
