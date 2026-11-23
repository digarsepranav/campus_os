package com.campusos.campusOS.controller;

import com.campusos.campusOS.dto.JobRequestDTO;
import com.campusos.campusOS.dto.JobResponseDTO;
import com.campusos.campusOS.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController
@RequiredArgsConstructor
public class JobController {
    private final JobService service;

    @PostMapping("/api/jobs/create")
    public ResponseEntity<JobResponseDTO> createJob(@Valid @RequestBody JobRequestDTO jobRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createJob(jobRequestDTO));
    }
}
