package com.app.housing_association.common.service.files;


import com.azure.storage.blob.BlobContainerClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;

import static com.app.housing_association.common.utils.IValidation.IMAGE_SERVICE_SIZE_ERROR;

@Slf4j
@Service
public class ImageServiceAzure extends AzureBlobAbstractService {

    private static final String IMAGE_EXTENSION = ".png";

    private final String filePath;
    private final Integer fileMaxHeight;
    private final Integer fileMaxWidth;

    public ImageServiceAzure(BlobContainerClient blobContainerClient,
                             @Value("${faults.image.data.path}") String filePath,
                             @Value("${fault.images.max.height}") Integer fileMaxHeight,
                             @Value("${fault.images.max.width}") Integer fileMaxWidth) {
        super(blobContainerClient);
        this.filePath = filePath;
        this.fileMaxHeight = fileMaxHeight;
        this.fileMaxWidth = fileMaxWidth;
    }

    @Override
    protected boolean isNotCorrectFileSize(MultipartFile image) {
        try {
            ByteArrayInputStream byteStream = new ByteArrayInputStream(image.getBytes());
            var readImage = ImageIO.read(byteStream);
            return readImage.getWidth() > fileMaxWidth || readImage.getHeight() > fileMaxHeight;
        } catch (Exception e) {
            log.error(IMAGE_SERVICE_SIZE_ERROR, image.getName());
            return false;
        }
    }

    @Override
    String getFileExtension() {
        return IMAGE_EXTENSION;
    }

    @Override
    String getFilePath() {
        return filePath;
    }
}
