package com.campusos.campusOS.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateJobRequestDTO {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Salary is required")
    @Positive(message = "Salary must be positive")
    private Double salary;

    @NotNull(message = "Minimum CGPA is required")
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "10.0")
    private Double minCgpa;

    @NotNull(message = "Graduation year is required")
    @Min(value = 2026)
    private Integer graduationYear;

    @NotNull(message = "Company ID is required")
    @Positive(message = "Company ID must be positive")
    private Long companyId;
}
