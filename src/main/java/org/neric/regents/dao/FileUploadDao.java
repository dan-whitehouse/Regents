package org.neric.regents.dao;

import org.neric.regents.model.UploadedFile;

import java.util.List;

public interface FileUploadDao {
    List<UploadedFile> listFiles();

    UploadedFile getFile(Long id);

    UploadedFile saveFile(UploadedFile uploadedFile);
}