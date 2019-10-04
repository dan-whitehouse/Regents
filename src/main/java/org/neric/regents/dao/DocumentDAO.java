package org.neric.regents.dao;

import org.neric.regents.model.Document;

import java.util.List;

public interface DocumentDAO {

    List<Document> findAll();

    List<Document> findAllActiveDocuments();

    Document findById(int id);

    Document findByUUID(String uuid);

    void save(Document document);

    void deleteByDocumentId(int id);

    void deleteByDocumentUUID(String uuid);

    List<Document> findAllDocumentsByOrderFormId(Integer id);
}
