package com.campusos.campusOS.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CompanyRequestDTO {
    @NotBlank
    private String name;
    private String website;
    private String description;
    @NotBlank
    private String location;
}
