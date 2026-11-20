package com.campusos.campusOS.service;

import com.campusos.campusOS.dto.CompanyRequestDTO;
import com.campusos.campusOS.dto.CompanyResponseDTO;
import com.campusos.campusOS.entity.Company;
import com.campusos.campusOS.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyResponseDTO createCompany(CompanyRequestDTO companyRequestDTO) {
        Company company = new Company();
        company.setName(companyRequestDTO.getName());
        company.setDescription(companyRequestDTO.getDescription());
        company.setWebsite(companyRequestDTO.getWebsite());
        company.setLocation(companyRequestDTO.getLocation());

        Company c = companyRepository.save(company);

        CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO();
        companyResponseDTO.setId(c.getId());
        companyResponseDTO.setName(c.getName());
        companyResponseDTO.setDescription(c.getDescription());
        companyResponseDTO.setWebsite(c.getWebsite());
        companyResponseDTO.setLocation(c.getLocation());

        return companyResponseDTO;
    }
}
