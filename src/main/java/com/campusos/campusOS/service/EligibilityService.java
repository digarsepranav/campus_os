package com.campusos.campusOS.service;

import com.campusos.campusOS.dto.EligibilityResponseDTO;
import com.campusos.campusOS.entity.Job;
import com.campusos.campusOS.entity.Student;
import com.campusos.campusOS.enums.Branch;
import com.campusos.campusOS.exception.JobNotFoundException;
import com.campusos.campusOS.exception.StudentNotFoundException;
import com.campusos.campusOS.repository.JobRepository;
import com.campusos.campusOS.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EligibilityService {
    private final StudentRepository studentRepository;
    private final JobRepository jobRepository;


    public EligibilityResponseDTO checkEligibility(Long jobId, Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new StudentNotFoundException("No student found for this id")
        );
        Job job = jobRepository.findById(jobId).orElseThrow(
                () -> new JobNotFoundException("No job found for this id")
        );

        List<String> reasons = new ArrayList<>();
        BigDecimal cgpa = job.getMinCgpa();
        Double studentCgpa = student.getCgpa();
        if (BigDecimal.valueOf(studentCgpa).compareTo(cgpa) < 0) {
            reasons.add("Your CGPA is lower than the eligibility criteria. ");
        }
        if (!student.getGraduationYear().equals(job.getGraduationYear())) {
            reasons.add("You are not eligible because this job is for students of: " + job.getGraduationYear());
        }
        Set<Branch> eligibleBranch = job.getEligibleBranches();
        if (!eligibleBranch.contains(student.getBranch())) {
            reasons.add("Your Branch is not eligible for this course. ");
        }
        boolean eligible = reasons.isEmpty();

            EligibilityResponseDTO dto = new EligibilityResponseDTO();
            dto.setEligible(eligible);
            dto.setReasons(reasons);
            dto.setJobId(jobId);
            dto.setStudentId(studentId);
            return dto;
        }
}
