package org.neric.regents.service;

import org.neric.regents.dao.FileUploadDao;
import org.neric.regents.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FileUploadServiceImpl implements FileUploadService {

       @Autowired
       private FileUploadDao dao;

       @Transactional(readOnly = true)
       public List<UploadedFile> listFiles() {

              return dao.listFiles();
       }

       @Transactional(readOnly = true)
       public UploadedFile getFile(Long id) {
              
              return dao.getFile(id);
       }

       @Transactional
       public UploadedFile saveFile(UploadedFile uploadedFile) {
              
              return dao.saveFile(uploadedFile);
       }
}