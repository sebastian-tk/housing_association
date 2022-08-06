package com.app.housing_association.common.service.files;


import com.app.housing_association.common.service.files.exception.ImageSaveException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.app.housing_association.common.utils.IValidation.*;
import static java.util.Objects.isNull;

@Slf4j
@Service
public class ImageService implements FileService {

    private static final String IMAGE_EXTENSION = ".png";

    @Value("${faults.image.data.path}")
    private String pathDirectory;

    @Value("${fault.images.max.height}")
    private Integer fileMaxHeight;

    @Value("${fault.images.max.width}")
    private Integer fileMaxWidth;

    @Override
    public String saveFile(MultipartFile image, Long id) {
        if (isNull(image) || isNull(id)) {
            log.error(IMAGE_LOG_SAVE_FILE_MESSAGE_ERROR, image, id);
            return null;
        }
        String completedPath = createPathToSave(id);
        try (FileOutputStream fos = new FileOutputStream(completedPath)) {
            if (isSizeIncorrect(image)) {
                throw new IllegalStateException(IMAGE_SIZE_ERROR);
            }
            createDirectoryIfNotExist(pathDirectory);
            fos.write(image.getBytes());
            return createImageName(id);
        } catch (IOException e) {
            log.error(IMAGE_SAVE_ERROR, id);
            throw new ImageSaveException(e.getMessage(), e);
        }
    }

    @Override
    public String updateFile(MultipartFile file, Long existFileId) {
        String completedPath = createPathToSave(existFileId);
        var image = new File(completedPath);
        if (image.delete()) {
            return saveFile(file, existFileId);
        }
        log.error(IMAGE_REMOVE_ERROR, completedPath);
        return null;
    }

    @Override
    public byte[] convertToByte(String path) {
        try {
            String completedPath = pathDirectory.concat(path);
            createDirectoryIfNotExist(pathDirectory);
            return Files.readAllBytes(Paths.get(completedPath));
        } catch (IOException e) {
            log.error("Error during read image: {} from directory, message: {}", path, e.getMessage());
        }
        return null;
    }

    private String createImageName(Long id) {
        return "/".concat(id.toString().concat(IMAGE_EXTENSION));
    }

    private String createPathToSave(Long id) {
        return pathDirectory.concat(createImageName(id));
    }

    private boolean isSizeIncorrect(MultipartFile image) throws IOException {
        ByteArrayInputStream byteStream = new ByteArrayInputStream(image.getBytes());
        var readImage = ImageIO.read(byteStream);
        return readImage.getWidth() > fileMaxWidth || readImage.getHeight() > fileMaxHeight;
    }
}
