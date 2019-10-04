package org.neric.regents.service;

import org.neric.regents.model.Document;

import java.util.List;


public interface DocumentService {

    Document findById(int id);

    Document findByUUID(String uuid);

    List<Document> findAllDocuments();

    List<Document> findAllActiveDocuments();

    void saveDocument(Document document);

    void updateDocument(Document document);

    void deleteByDocumentId(int id);

    void deleteByDocumentUUID(String uuid);

    void lockByDocumentId(int id, Boolean locked);

    void hideByDocumentId(int id, Boolean hidden);

    void lockByDocumentUUID(String uuid, boolean isLocked);

    void hideByDocumentUUID(String uuid, boolean isHidden);

    List<Document> findAllDocumentsByOrderFormId(Integer id);
}
