package com.campusos.campusOS.dto;

import com.campusos.campusOS.enums.Branch;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
public class JobResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String location;
    private BigDecimal salary;
    private BigDecimal minCgpa;
    private Integer graduationYear;
    private Long companyId;
    private String companyName;
    private Set<Branch> eligibleBranches;
}
