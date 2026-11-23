package com.campusos.campusOS.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyResponseDTO {
    private Long id;
    private String name;
    private String website;
    private String description;
    private String location;
}
