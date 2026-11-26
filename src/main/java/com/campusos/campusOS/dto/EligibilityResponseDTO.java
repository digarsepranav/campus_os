package com.campusos.campusOS.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EligibilityResponseDTO {
    private Long studentId;
    private Long jobId;
    private boolean eligible;
    private List<String> reasons;
}
