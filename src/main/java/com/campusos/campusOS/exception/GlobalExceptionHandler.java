package com.campusos.campusOS.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<String> handleDuplicateEmail(DuplicateEmailException dex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(dex.getMessage());
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<String> noStudentFound(StudentNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }
    @ExceptionHandler(CompanyAlreadyExistsException.class)
    public ResponseEntity<String> companyAlreadyExists(CompanyAlreadyExistsException cx) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(cx.getMessage());
    }
    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<String> companyNotFound(CompanyNotFoundException cx) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(cx.getMessage());
    }
    @ExceptionHandler(JobNotFoundException.class)
    public ResponseEntity<String> jobNotFound(JobNotFoundException cx) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(cx.getMessage());
    }
}
