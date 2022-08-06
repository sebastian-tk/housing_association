package com.app.housing_association.common.service.files;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import static com.app.housing_association.common.utils.IValidation.FILE_CREATE_DIRECTORY_ERROR;

public interface FileService {

    byte[] convertToByte(String path);

    String saveFile(MultipartFile file, Long id);

    String updateFile(MultipartFile file, Long existFileId);

    default void createDirectoryIfNotExist(String path){
        var file = new File(path);
        if(!file.exists()){
            if(!file.mkdirs()){
                throw new IllegalStateException(FILE_CREATE_DIRECTORY_ERROR +path);
            }
        }
    }
}
