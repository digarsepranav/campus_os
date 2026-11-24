package com.campusos.campusOS.repository;

import com.campusos.campusOS.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    public boolean existsByName(String name);
}
