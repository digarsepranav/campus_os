package com.campusos.campusOS.controller;

import com.campusos.campusOS.dto.ApplicationRequestDTO;
import com.campusos.campusOS.dto.ApplicationResponseDTO;
import com.campusos.campusOS.service.ApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;

    @PostMapping("/api/applications/apply")
    public ResponseEntity<ApplicationResponseDTO> apply(@Valid @RequestBody ApplicationRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(applicationService.application(dto.getStudentId(), dto.getJobId()));
    }
}
