package com.campusos.campusOS.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class StudentRegistrationRequestDTO {
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
}
