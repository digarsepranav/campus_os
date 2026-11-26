package com.campusos.campusOS.controller;

import com.campusos.campusOS.dto.EligibilityResponseDTO;
import com.campusos.campusOS.service.EligibilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EligibilityController {
    private final EligibilityService eligibilityService;

    @GetMapping("/api/jobs/{jobId}/eligibility/{studentId}")
    public ResponseEntity<EligibilityResponseDTO> eligibleStudent(@PathVariable Long jobId, @PathVariable Long studentId) {
        return ResponseEntity.ok(
                eligibilityService.checkEligibility(jobId, studentId)
        );
    }
}
