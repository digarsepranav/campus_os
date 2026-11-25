package com.campusos.campusOS.controller;

import com.campusos.campusOS.dto.JobRequestDTO;
import com.campusos.campusOS.dto.JobResponseDTO;
import com.campusos.campusOS.dto.UpdateJobRequestDTO;
import com.campusos.campusOS.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class JobController {
    private final JobService service;

    @PostMapping("/api/jobs/create")
    public ResponseEntity<JobResponseDTO> createJob(@Valid @RequestBody JobRequestDTO jobRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createJob(jobRequestDTO));
    }

    @GetMapping("/api/jobs/company/{id}")
    public ResponseEntity<Page<JobResponseDTO>> getJobs(@PathVariable Long id, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "asc") String direction) {
        //1
        if (page < 0) {
            throw new IllegalArgumentException("Page cannot be negative");
        }

        //2
        if (size < 1 || size > 100) {
            throw new IllegalArgumentException("Size must be between 1 and 100");
        }

        // 3
        if (!List.of("id", "title", "location", "salary", "minCgpa", "graduationYear").contains(sortBy)) {
                throw new IllegalArgumentException("Invalid sorting field");
        }

        // 4
        if (!direction.equalsIgnoreCase("asc") && !direction.equalsIgnoreCase("desc")) {
            throw new IllegalArgumentException("Invalid sorting direction");
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction.toUpperCase()), sortBy));

        return ResponseEntity.ok(service.getJobs(id, pageable));
    }

    @GetMapping("/api/jobs/{id}")
    public ResponseEntity<JobResponseDTO> getJob(@PathVariable Long id) {
        return ResponseEntity.ok(service.getJob(id));
    }

    @PutMapping("api/jobs/{id}")
    public ResponseEntity<JobResponseDTO> updateJob(@PathVariable Long id, @Valid @RequestBody UpdateJobRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.updateJob(id, dto));
    }

    @DeleteMapping("/api/jobs/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(service.deleteJob(id));
    }
}
