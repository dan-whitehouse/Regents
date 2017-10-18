package org.neric.regents.dao;

import org.hibernate.SessionFactory;
import org.neric.regents.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FileUploadDaoImpl extends AbstractDao<Integer, UploadedFile>  implements FileUploadDao {

       @Autowired
       private SessionFactory sessionFactory;

       public List<UploadedFile> listFiles() {
              return getSession().createCriteria(UploadedFile.class).list();
       }

       public UploadedFile getFile(Long id) {
              return (UploadedFile) getSession().get(UploadedFile.class, id);
       }

       public UploadedFile saveFile(UploadedFile uploadedFile) {
              return (UploadedFile) getSession().merge(uploadedFile);
       }

       private SessionFactory getSessionFactory() {
              return sessionFactory;
       }
}