package com.campusos.campusOS.service;

import com.campusos.campusOS.dto.CompanyRequestDTO;
import com.campusos.campusOS.dto.CompanyResponseDTO;
import com.campusos.campusOS.entity.Company;
import com.campusos.campusOS.exception.CompanyAlreadyExistsException;
import com.campusos.campusOS.exception.CompanyNotFoundException;
import com.campusos.campusOS.exception.StudentNotFoundException;
import com.campusos.campusOS.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

        if (companyRepository.existsByName(company.getName())) {
            throw new CompanyAlreadyExistsException("Company already exists");
        }

        Company c = companyRepository.save(company);

        CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO();
        companyResponseDTO.setId(c.getId());
        companyResponseDTO.setName(c.getName());
        companyResponseDTO.setDescription(c.getDescription());
        companyResponseDTO.setWebsite(c.getWebsite());
        companyResponseDTO.setLocation(c.getLocation());

        return companyResponseDTO;
    }

    public CompanyResponseDTO getCompany(Long id) {
       return companyRepository.findById(id)
               .map(company -> {
                   CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO();
                   companyResponseDTO.setId(company.getId());
                   companyResponseDTO.setName(company.getName());
                   companyResponseDTO.setWebsite(company.getWebsite());
                   companyResponseDTO.setLocation(company.getLocation());
                   companyResponseDTO.setDescription(company.getDescription());

                   return companyResponseDTO;
               }).orElseThrow(() -> new CompanyNotFoundException("Company doesnt exist"));
    }

    public Page<CompanyResponseDTO> getAllCompany(Pageable pageable) {
        Page<Company> companies = companyRepository.findAll(pageable);
        Page<CompanyResponseDTO> companyResponseDTOS = companies.map(
                company -> {
                    CompanyResponseDTO dto = new CompanyResponseDTO();
                    dto.setName(company.getName());
                    dto.setDescription(company.getDescription());
                    dto.setId(company.getId());
                    dto.setWebsite(company.getWebsite());
                    dto.setLocation(company.getLocation());

                    return dto;
                }
        );
        return companyResponseDTOS;
    }
}
