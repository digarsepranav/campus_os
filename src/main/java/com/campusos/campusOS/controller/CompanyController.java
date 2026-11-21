package com.campusos.campusOS.controller;

import com.campusos.campusOS.dto.CompanyRequestDTO;
import com.campusos.campusOS.dto.CompanyResponseDTO;
import com.campusos.campusOS.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {
    private final CompanyService service;

    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @PostMapping("/api/companies/create")
    public ResponseEntity<CompanyResponseDTO> createCompany(@Valid @RequestBody CompanyRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createCompany(dto));
    }

    @GetMapping("/api/companies/{id}")
    public ResponseEntity<CompanyResponseDTO> getCompanyByID(@PathVariable Long id) {
        return ResponseEntity.ok(
                service.getCompany(id)
        );
    }

    @GetMapping("/api/companies")
    public ResponseEntity<Page<CompanyResponseDTO>> getAllCompanies(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size,@RequestParam(defaultValue = "id") String sortBy,@RequestParam(defaultValue = "asc") String direction) {
        //1
        if (page < 0) {
            throw new IllegalArgumentException("Page cannot be negative");
        }

        //2
        if (size < 1 || size > 100) {
            throw new IllegalArgumentException("Size must be between 1 and 100");
        }

        // 3
        if (!List.of("id", "name", "location", "website").contains(sortBy)) {
            throw new IllegalArgumentException("Invalid sorting field");
        }

        // 4
        if (!direction.equalsIgnoreCase("asc") && !direction.equalsIgnoreCase("desc")) {
            throw new IllegalArgumentException("Invalid sorting direction");
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction.toUpperCase()), sortBy));

        return ResponseEntity
                .ok(service.getAllCompany(pageable));
    }
}
