package com.globits.qlvbcc.serviceimpl;

import com.globits.qlvbcc.domain.Diploma;
import com.globits.qlvbcc.domain.School;
import com.globits.qlvbcc.domain.Student;
import com.globits.qlvbcc.dto.DiplomaDto;
import com.globits.qlvbcc.dto.SchoolDto;
import com.globits.qlvbcc.dto.StudentDto;
import com.globits.qlvbcc.repository.DiplomaRepository;
import com.globits.qlvbcc.repository.SchoolRepository;
import com.globits.qlvbcc.repository.StudentRepository;
import com.globits.qlvbcc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    SchoolRepository schoolRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    DiplomaRepository diplomaRepository;

    @Override
    public List<StudentDto> getAll() {
        return studentRepository.getAll();
    }

    @Override
    public StudentDto addStudent(StudentDto dto) {

        Student student = new Student();
        student.setName(dto.getName());
        student.setCode(dto.getCode());
        studentRepository.save(student);

        return dto;
    }

    @Override
    public StudentDto updateStudent(StudentDto dto, Long id) {

        Student student = studentRepository.findStudentById(id);
        student.setName(dto.getName());
        student.setCode(dto.getCode());

        if (dto.getDiplomas() != null && !dto.getDiplomas().isEmpty()) {
            Set<Diploma> newDiplomas = new HashSet<Diploma>();
            for (DiplomaDto item : dto.getDiplomas()) {
                Diploma diploma = null;
                if (item.getId() != null && item.getId() > 0) {
                    diploma = diplomaRepository.findDiplomaById(item.getId());
                } else {
                    diploma = new Diploma();
                }
                diploma.setName(item.getName());
                diploma.setCode(item.getCode());
                diploma.setType(item.getType());
                newDiplomas.add(diploma);
                diplomaRepository.save(diploma);
            }

            if(student.getDiplomas() != null){
                student.getDiplomas().clear();
                student.getDiplomas().addAll(newDiplomas);
            } else{
                student.setDiplomas(newDiplomas);
            }

        }

//        if(dto.getSchool() != null){
//            SchoolDto schoolDto = dto.getSchool();
//            School school = schoolRepository.findSchoolById(schoolDto.getId());
//            Set<Student> students = null;
//            if(school.getStudents() != null){
//                students = school.getStudents();
//            } else{
//                students = new HashSet<Student>();
//            }
//            students.add(student);
//            school.setStudents(students);
//            schoolRepository.save(school);
//            student.setSchoolStu(school);
//        }
//
//        studentRepository.save(student);

        return dto;
    }

    @Override
    public StudentDto removeStudent(Long id) {
        studentRepository.deleteById(id);
        return null;
    }

    @Override
    public String removeAll() {
        return "";
    }

    @Override
    public StudentDto getStudent(Long id) {
        return studentRepository.findStudentDtoById(id);
    }
}
