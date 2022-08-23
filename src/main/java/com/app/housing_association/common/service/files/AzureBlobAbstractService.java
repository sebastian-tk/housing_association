package com.app.housing_association.common.service.files;

import com.app.housing_association.common.service.files.exception.UploadFileFailedException;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static com.app.housing_association.common.utils.IValidation.*;
import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static org.apache.commons.io.FilenameUtils.getExtension;

@Slf4j
public abstract class AzureBlobAbstractService {

    private final BlobContainerClient blobContainerClient;

    public AzureBlobAbstractService(BlobContainerClient blobContainerClient) {
        this.blobContainerClient = blobContainerClient;
    }

    public String checkAndSaveFile(MultipartFile file, Long id) {
        if (isNull(file) || isNull(id)) {
            log.error(BLOB_STORAGE_FILE_OR_ID_TO_SAVE_NULL, file, id);
            return null;
        }
        try {
            validateInputFile(file);
            String fileName = createFileName(id);
            return saveFile(file, fileName);
        } catch (Exception e) {
            log.error(BLOB_STORAGE_SAVE_ERROR, id);
            throw new UploadFileFailedException(e.getMessage(), e);
        }
    }

    public String updateFile(MultipartFile file, Long existFileId) {
        return checkAndSaveFile(file, existFileId);
    }

    public byte[] downloadFile(String fileName) {
        try {
            BlobClient blob = blobContainerClient.getBlobClient(createFilePath(fileName));
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            blob.download(outputStream);
            return outputStream.toByteArray();
        } catch (Exception e) {
            log.error(BLOB_STORAGE_DOWNLOAD_ERROR, fileName);
            throw new UploadFileFailedException(e.getMessage(), e);
        }
    }

    protected String saveFile(MultipartFile multipartFile, String fileName) throws IOException {
        String filePath = createFilePath(fileName);
        BlobClient blob = blobContainerClient.getBlobClient(filePath);
        blob.upload(multipartFile.getInputStream(), multipartFile.getSize(), true);
        return fileName;
    }


    private String createFileName(Long id) {
        return "/".concat(id.toString().concat(getFileExtension()));
    }

    private String createFilePath(String fileName) {
        return getFilePath().concat(fileName);
    }

    private void validateInputFile(MultipartFile file) {
        if (isNotCorrectExtension(requireNonNull(getExtension(file.getOriginalFilename())))) {
            throw new IllegalArgumentException("Invalid file extension");
        }
        if (isNotCorrectFileSize(file)) {
            throw new IllegalArgumentException("Invalid file size");
        }
    }

    private boolean isNotCorrectExtension(String extension) {
        return !".".concat(extension).equals(getFileExtension());
    }

    abstract boolean isNotCorrectFileSize(MultipartFile file);

    abstract String getFileExtension();

    abstract String getFilePath();
}
