package com.app.housing_association.common.service.files;


import com.app.housing_association.common.service.files.exception.PdfSaveException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.app.housing_association.common.utils.IValidation.*;
import static java.lang.String.format;
import static java.util.Objects.isNull;

@Slf4j
@Service
public class PdfService implements FileService {

    private static final String PDF_EXTENSION = ".pdf";
    private static final long CONVERT_MB_TO_BYTES = 1024 * 1024;

    @Value("${vote.files.data.path}")
    private String pathDirectory;

    @Value("${vote.pdf.max.size}")
    private Integer fileMaxSize;

    @Override
    public String saveFile(MultipartFile pdf, Long id) {
        if (isNull(pdf) || isNull(id)) {
            log.error(PDF_LOG_SAVE_FILE_MESSAGE_ERROR, pdf, id);
            return null;
        }
        String completedPath = createPathToSave(id);
        try (FileOutputStream fos = new FileOutputStream(completedPath)) {
            if (isSizeIncorrect(pdf)) {
                throw new IllegalStateException(format(PDF_SIZE_ERROR, fileMaxSize));
            }
            createDirectoryIfNotExist(pathDirectory);
            fos.write(pdf.getBytes());
            return createFileName(id);
        } catch (IOException e) {
            log.error(PDF_SAVE_ERROR, id);
            throw new PdfSaveException(e.getMessage(), e);
        }
    }

    @Override
    public String updateFile(MultipartFile file, Long existFileId) {
        String completedPath = createPathToSave(existFileId);
        var image = new File(completedPath);
        if (image.delete()) {
            return saveFile(file, existFileId);
        }
        log.error(PDF_REMOVE_ERROR, completedPath);
        return null;
    }

    @Override
    public byte[] convertToByte(String path) {
        try {
            String completedPath = pathDirectory.concat(path);
            createDirectoryIfNotExist(pathDirectory);
            var x = Paths.get(completedPath);
            return Files.readAllBytes(x);
        } catch (IOException e) {
            log.error(PDF_READ_ERROR, path, e.getLocalizedMessage());
        }
        return null;
    }

    private String createFileName(Long id) {
        return "/".concat(id.toString().concat(PDF_EXTENSION));
    }

    private String createPathToSave(Long id) {
        return pathDirectory.concat(createFileName(id));
    }

    private boolean isSizeIncorrect(MultipartFile pdf) throws IOException {
        return pdf.getBytes().length / CONVERT_MB_TO_BYTES > fileMaxSize;
    }
}
