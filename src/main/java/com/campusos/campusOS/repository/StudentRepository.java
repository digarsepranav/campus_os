package com.campusos.campusOS.repository;

import com.campusos.campusOS.dto.StudentResponseDTO;
import com.campusos.campusOS.entity.Student;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByEmail(String email);
    Optional<Student> findByEmail(String email);
    List<Student> findByCgpaGreaterThan(double cgpa);
    List<Student> findByCgpaBetween(double minCgpa, double maxCgpa);
    boolean existsByEmailAndIdNot(String name, Long id);
    Page<Student> findAll(Pageable pageable);
}
