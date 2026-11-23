package com.campusos.campusOS.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

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
}
