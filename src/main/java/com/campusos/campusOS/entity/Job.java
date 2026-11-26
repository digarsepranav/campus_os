package com.campusos.campusOS.entity;

import com.campusos.campusOS.enums.Branch;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.resilience.annotation.EnableResilientMethods;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String location;
    @Positive
    @NotNull
    private BigDecimal salary;
    @NotNull
    @DecimalMax("10.0")
    @DecimalMin("0.01")
    private BigDecimal minCgpa;
    @NotNull
    private Integer graduationYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @ElementCollection
    @Enumerated(EnumType.STRING)
            @CollectionTable(name = "job_eligible_branches", joinColumns = @JoinColumn(name = "job_id"))
            @Column(name = "branch", nullable = false)
    private Set<Branch> eligibleBranches = new HashSet<>();
}
