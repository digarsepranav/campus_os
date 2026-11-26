package com.campusos.campusOS.service;

import com.campusos.campusOS.dto.JobRequestDTO;
import com.campusos.campusOS.dto.JobResponseDTO;
import com.campusos.campusOS.dto.UpdateJobRequestDTO;
import com.campusos.campusOS.entity.Company;
import com.campusos.campusOS.entity.Job;
import com.campusos.campusOS.exception.CompanyNotFoundException;
import com.campusos.campusOS.exception.JobNotFoundException;
import com.campusos.campusOS.repository.CompanyRepository;
import com.campusos.campusOS.repository.JobRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;

    public JobService(JobRepository jobRepository, CompanyRepository companyRepository) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
    }
    @Transactional
    public JobResponseDTO createJob(JobRequestDTO jobRequestDTO) {

        Optional<Company> company = companyRepository.findById(jobRequestDTO.getCompanyId());

        Company c = company.orElseThrow(
                () -> new CompanyNotFoundException("No job/company exists for this ID")
        );

        Job job = new Job();
        job.setTitle(jobRequestDTO.getTitle());
        job.setCompany(c);
        job.setDescription(jobRequestDTO.getDescription());
        job.setLocation(jobRequestDTO.getLocation());
        job.setGraduationYear(jobRequestDTO.getGraduationYear());
        job.setSalary(jobRequestDTO.getSalary());
        job.setMinCgpa(jobRequestDTO.getMinCgpa());
        job.setEligibleBranches(jobRequestDTO.getEligibleBranches());

        Job savedJob = jobRepository.save(job);

        JobResponseDTO response = new JobResponseDTO();
        response.setId(savedJob.getId());
        response.setTitle(savedJob.getTitle());
        response.setDescription(savedJob.getDescription());
        response.setLocation(savedJob.getLocation());
        response.setSalary(savedJob.getSalary());
        response.setMinCgpa(savedJob.getMinCgpa());
        response.setGraduationYear(savedJob.getGraduationYear());
        response.setCompanyId(savedJob.getCompany().getId());
        response.setCompanyName(savedJob.getCompany().getName());
        response.setEligibleBranches(savedJob.getEligibleBranches());

        return response;
    }
    @Transactional()
    public Page<JobResponseDTO> getJobs(Long id, Pageable pageable) {
        if (!companyRepository.existsById(id)) {
            throw new CompanyNotFoundException("No company with this ID exist");
        }
        Page<Job> jobs = jobRepository.findByCompanyId(id, pageable);
        Page<JobResponseDTO> jobResponseDTOS = jobs.map(
                job -> {
                    JobResponseDTO responseDTO = new JobResponseDTO();
                    responseDTO.setCompanyName(job.getCompany().getName());
                    responseDTO.setId(job.getId());
                    responseDTO.setCompanyId(job.getCompany().getId());
                    responseDTO.setGraduationYear(job.getGraduationYear());
                    responseDTO.setDescription(job.getDescription());
                    responseDTO.setLocation(job.getLocation());
                    responseDTO.setMinCgpa(job.getMinCgpa());
                    responseDTO.setTitle(job.getTitle());
                    responseDTO.setSalary(job.getSalary());
                    responseDTO.setEligibleBranches(job.getEligibleBranches());

                    return responseDTO;
                }
        );
        return jobResponseDTOS;
    }
    @Transactional
    public JobResponseDTO getJob(Long id) {
        Optional<Job> job = jobRepository.findById(id);

        Job job1 = job.orElseThrow(
                () -> new JobNotFoundException("No job for this id")
        );

        JobResponseDTO responseDTO = new JobResponseDTO();
        responseDTO.setCompanyName(job1.getCompany().getName());
        responseDTO.setId(job1.getId());
        responseDTO.setCompanyId(job1.getCompany().getId());
        responseDTO.setGraduationYear(job1.getGraduationYear());
        responseDTO.setDescription(job1.getDescription());
        responseDTO.setLocation(job1.getLocation());
        responseDTO.setMinCgpa(job1.getMinCgpa());
        responseDTO.setTitle(job1.getTitle());
        responseDTO.setSalary(job1.getSalary());
        responseDTO.setEligibleBranches(job1.getEligibleBranches());
        return responseDTO;
    }

    @Transactional
    public JobResponseDTO updateJob(Long id, UpdateJobRequestDTO dto) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new JobNotFoundException("Job not found"));
        Company company = companyRepository.findById(dto.getCompanyId()).orElseThrow(() -> new CompanyNotFoundException("Company Not found with this ID : " + dto.getCompanyId()));

        job.setTitle(dto.getTitle());
        job.setDescription(dto.getDescription());
        job.setLocation(dto.getLocation());
        job.setSalary(BigDecimal.valueOf(dto.getSalary()));
        job.setMinCgpa(BigDecimal.valueOf(dto.getMinCgpa()));
        job.setGraduationYear(dto.getGraduationYear());
        job.setEligibleBranches(dto.getEligibleBranches());

        job.setCompany(company);        // isse ref change hora hai

        JobResponseDTO responseDTO = new JobResponseDTO();

        responseDTO.setId(job.getId());
        responseDTO.setTitle(job.getTitle());
        responseDTO.setDescription(job.getDescription());
        responseDTO.setLocation(job.getLocation());
        responseDTO.setSalary(job.getSalary());
        responseDTO.setMinCgpa(job.getMinCgpa());
        responseDTO.setGraduationYear(job.getGraduationYear());
        responseDTO.setCompanyId(company.getId());
        responseDTO.setCompanyName(company.getName());
        responseDTO.setEligibleBranches(job.getEligibleBranches());

        return responseDTO;
    }

    @Transactional
    public String deleteJob(Long id) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new JobNotFoundException("No job exist for this ID"));
        jobRepository.delete(job);
        return "Job Deleted";
    }
}
