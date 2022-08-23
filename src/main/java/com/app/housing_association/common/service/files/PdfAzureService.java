package com.app.housing_association.common.service.files;


import com.azure.storage.blob.BlobContainerClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.app.housing_association.common.utils.IValidation.PDF_SERVICE_SIZE_ERROR;

@Slf4j
@Service
public class PdfAzureService extends AzureBlobAbstractService {

    private static final String PDF_EXTENSION = ".pdf";
    private static final long CONVERT_MB_TO_BYTES = 1024 * 1024;

    private final String filePath;
    private final Integer fileMaxSize;

    public PdfAzureService(BlobContainerClient blobContainerClient,
                           @Value("${vote.files.data.path}") String filePath,
                           @Value("${vote.pdf.max.size}") Integer fileMaxSize) {
        super(blobContainerClient);
        this.filePath = filePath;
        this.fileMaxSize = fileMaxSize;
    }


    @Override
    boolean isNotCorrectFileSize(MultipartFile pdf) {
        try {
            return pdf.getBytes().length / CONVERT_MB_TO_BYTES > fileMaxSize;
        } catch (IOException e) {
            log.error(PDF_SERVICE_SIZE_ERROR, pdf.getName(), fileMaxSize);
            return false;
        }
    }

    @Override
    String getFileExtension() {
        return PDF_EXTENSION;
    }

    @Override
    String getFilePath() {
        return filePath;
    }
}
