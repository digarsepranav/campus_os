package com.campusos.campusOS.controller;

import com.campusos.campusOS.dto.StudentRegistrationRequestDTO;
import com.campusos.campusOS.dto.StudentResponseDTO;
import com.campusos.campusOS.entity.Student;
import com.campusos.campusOS.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    private final StudentService service;
    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping("/api/students")
    public ResponseEntity<StudentResponseDTO> registerUser(
            @Valid @RequestBody StudentRegistrationRequestDTO studentRegistrationRequestDTO
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.saveStudent(studentRegistrationRequestDTO));
    }

    @GetMapping("/api/students/getAll")
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents() {
        return ResponseEntity
                .ok(service.getAllStudents());
    }
    @GetMapping("/api/students/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable Long id) {
        return ResponseEntity
                .ok(service.getStudentById(id));
    }
}
