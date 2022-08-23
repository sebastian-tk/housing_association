package com.app.housing_association.common.service.files.exception;

public class UploadFileFailedException extends RuntimeException {
    public UploadFileFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
