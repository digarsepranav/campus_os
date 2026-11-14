package com.campusos.campusOS.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.TargetEmbeddable;

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
}
