package com.campusos.campusOS.entity;

import com.campusos.campusOS.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(uniqueConstraints =
        {@UniqueConstraint(columnNames ={"student_id", "job_id"})})
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Job job;
    @Column(nullable = false)
    private LocalDateTime appliedAt;
    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;

    @PrePersist
    private void onCreate() {
        appliedAt = LocalDateTime.now();
    }
}
