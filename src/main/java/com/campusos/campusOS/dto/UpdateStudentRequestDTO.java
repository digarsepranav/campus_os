package com.campusos.campusOS.dto;

import com.campusos.campusOS.enums.Branch;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStudentRequestDTO {
    @NotBlank
    @Size(max = 100)
    private String name;
    @NotBlank
    @Email
    private String email;
    @NotNull
    @DecimalMin("0.0")
    @DecimalMax("10.0")
    private Double cgpa;

    @NotNull
    private Integer graduationYear;

    @NotNull
    private Branch branch;
}
