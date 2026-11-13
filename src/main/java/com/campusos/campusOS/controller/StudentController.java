package com.campusos.campusOS.controller;

import com.campusos.campusOS.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    private final StudentService service;
    public StudentController(StudentService service) {
        this.service = service;
    }
    @GetMapping("/api/student/message")
    public String message() {
        return service.getMessage();
    }
}
