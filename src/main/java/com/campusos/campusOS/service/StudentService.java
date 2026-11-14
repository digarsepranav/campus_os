package com.campusos.campusOS.service;

import com.campusos.campusOS.dto.StudentRegistrationRequestDTO;
import com.campusos.campusOS.dto.StudentResponseDTO;
import com.campusos.campusOS.entity.Student;
import com.campusos.campusOS.repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentResponseDTO saveStudent(StudentRegistrationRequestDTO studentRegistrationRequestDTO) {
        String name = studentRegistrationRequestDTO.getName();
        String email = studentRegistrationRequestDTO.getEmail();
        double cgpa = studentRegistrationRequestDTO.getCgpa();

        Student student = new Student();
        student.setName(name);
        student.setCgpa(cgpa);
        student.setEmail(email);

        if (studentRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already registered");
        }

        Student savedUser = studentRepository.save(student);

        StudentResponseDTO responseDTO = new StudentResponseDTO();
        responseDTO.setId(savedUser.getId());
        responseDTO.setCgpa(savedUser.getCgpa());
        responseDTO.setEmail(savedUser.getEmail());
        responseDTO.setName(savedUser.getName());

        return responseDTO;
    }
}
