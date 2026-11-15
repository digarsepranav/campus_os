package com.campusos.campusOS.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String message) {
        super(message);
    }
}
