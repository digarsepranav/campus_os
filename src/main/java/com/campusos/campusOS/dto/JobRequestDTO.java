package com.campusos.campusOS.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class JobRequestDTO {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String location;
    @Positive
    @NotNull
    private BigDecimal salary;
    @Positive
    @NotNull
    @DecimalMin("0.01")
    @DecimalMax("10.0")
    private BigDecimal minCgpa;
    @NotNull
    private Integer graduationYear;
    @NotNull
    private Long companyId;
}
