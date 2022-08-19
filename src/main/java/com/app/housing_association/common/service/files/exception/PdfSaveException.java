package com.app.housing_association.common.service.files.exception;

public class PdfSaveException extends RuntimeException {
    public PdfSaveException(String message, Throwable cause) {
        super(message, cause);
    }
}
