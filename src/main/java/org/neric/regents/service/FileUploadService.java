package org.neric.regents.service;


import org.neric.regents.model.UploadedFile;

import java.util.List;

public interface FileUploadService {
    List<UploadedFile> listFiles();

    UploadedFile getFile(Long id);

    UploadedFile saveFile(UploadedFile uploadedFile);
}