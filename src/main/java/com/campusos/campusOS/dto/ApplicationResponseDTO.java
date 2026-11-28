package com.campusos.campusOS.dto;

import com.campusos.campusOS.enums.ApplicationStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApplicationResponseDTO {
    private Long applicationId;
    private Long jobId;
    private Long studentId;
    private LocalDateTime appliedAt;
    private ApplicationStatus status;
}
