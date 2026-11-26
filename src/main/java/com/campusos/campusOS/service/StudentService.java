package com.campusos.campusOS.service;

import com.campusos.campusOS.dto.StudentRegistrationRequestDTO;
import com.campusos.campusOS.dto.StudentResponseDTO;
import com.campusos.campusOS.dto.UpdateStudentRequestDTO;
import com.campusos.campusOS.entity.Student;
import com.campusos.campusOS.exception.DuplicateEmailException;
import com.campusos.campusOS.exception.StudentNotFoundException;
import com.campusos.campusOS.repository.StudentRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        student.setGraduationYear(studentRegistrationRequestDTO.getGraduationYear());
        student.setBranch(studentRegistrationRequestDTO.getBranch());

        if (studentRepository.existsByEmail(email)) {
            throw new DuplicateEmailException("Email already registered");
        }

        Student savedUser = studentRepository.save(student);

        StudentResponseDTO responseDTO = new StudentResponseDTO();
        responseDTO.setId(savedUser.getId());
        responseDTO.setCgpa(savedUser.getCgpa());
        responseDTO.setEmail(savedUser.getEmail());
        responseDTO.setName(savedUser.getName());
        responseDTO.setBranch(savedUser.getBranch());
        responseDTO.setGraduationYear(savedUser.getGraduationYear());

        return responseDTO;
    }

    public Page<StudentResponseDTO> getAllStudents(Pageable pageable) {

        Page<Student> students = studentRepository.findAll(pageable);
        Page<StudentResponseDTO> studentResponseDTOS = students.
                map(student -> {
                    StudentResponseDTO dto = new StudentResponseDTO();
                    dto.setId(student.getId());
                    dto.setName(student.getName());
                    dto.setCgpa(student.getCgpa());
                    dto.setEmail(student.getEmail());
                    dto.setBranch(student.getBranch());
                    dto.setGraduationYear(dto.getGraduationYear());

                    return dto;
                });

        return studentResponseDTOS;
    }

    public StudentResponseDTO getStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);

        if (student.isPresent()) {
            StudentResponseDTO studentResponseDTO = new StudentResponseDTO();
            studentResponseDTO.setName(student.get().getName());
            studentResponseDTO.setEmail(student.get().getEmail());
            studentResponseDTO.setId(student.get().getId());
            studentResponseDTO.setCgpa(student.get().getCgpa());
            studentResponseDTO.setGraduationYear(student.get().getGraduationYear());
            studentResponseDTO.setBranch(student.get().getBranch());
            return studentResponseDTO;
        }
        throw new StudentNotFoundException("No student found for this ID");
    }

    public StudentResponseDTO getStudentByEmail(String email) {
        Optional<Student> student = studentRepository.findByEmail(email);

        Student foundStudent = student.orElseThrow(
                () -> new StudentNotFoundException("No student with this email")
        );

        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();
        studentResponseDTO.setName(foundStudent.getName());
        studentResponseDTO.setEmail(foundStudent.getEmail());
        studentResponseDTO.setId(foundStudent.getId());
        studentResponseDTO.setCgpa(foundStudent.getCgpa());
        studentResponseDTO.setBranch(foundStudent.getBranch());
        studentResponseDTO.setGraduationYear(foundStudent.getGraduationYear());
        return studentResponseDTO;
    }

    public List<StudentResponseDTO> getStudentCgpaGreaterThan(double cgpa) {
        List<Student> students = studentRepository.findByCgpaGreaterThan(cgpa);
        List<StudentResponseDTO> studentResponseDTOS = students.stream()
                .map(student -> new StudentResponseDTO(
                        student.getId(), student.getName(), student.getEmail(), student.getCgpa(), student.getGraduationYear(), student.getBranch()
                )).toList();
        return studentResponseDTOS;
    }

    public List<StudentResponseDTO> getStudentByCgpaBetween(double minCgpa, double maxCgpa) {
        List<Student> students = studentRepository.findByCgpaBetween(minCgpa, maxCgpa);
        List<StudentResponseDTO> studentResponseDTOS = students.stream().map(
                student -> new StudentResponseDTO(
                        student.getId(), student.getName(), student.getEmail(), student.getCgpa(), student.getGraduationYear(), student.getBranch()
                )
        ).toList();
        return studentResponseDTOS;
    }

    @Transactional
    public StudentResponseDTO updateStudent(Long id, UpdateStudentRequestDTO dto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));

        boolean noDuplicateEmail = studentRepository.existsByEmailAndIdNot(dto.getEmail(), id);
        if (noDuplicateEmail) {
            throw new DuplicateEmailException("Email ID already exists");
        }


        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setCgpa(dto.getCgpa());
        student.setBranch(dto.getBranch());
        student.setGraduationYear(dto.getGraduationYear());

        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();
        studentResponseDTO.setName(student.getName());
        studentResponseDTO.setCgpa(student.getCgpa());
        studentResponseDTO.setEmail(student.getEmail());
        studentResponseDTO.setId(student.getId());
        studentResponseDTO.setGraduationYear(student.getGraduationYear());
        studentResponseDTO.setBranch(student.getBranch());

        return studentResponseDTO;
    }
}
