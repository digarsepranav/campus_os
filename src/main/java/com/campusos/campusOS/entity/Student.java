package com.campusos.campusOS.entity;

import com.campusos.campusOS.enums.Branch;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    private Double cgpa;
    private Integer graduationYear;
    @Enumerated(EnumType.STRING)
    private Branch branch;
}
