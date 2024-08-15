package com.globits.qlvbcc.service;

import com.globits.qlvbcc.dto.StudentDto;

import java.util.List;

public interface StudentService {

    List<StudentDto> getAll();
    StudentDto addStudent(StudentDto dto);
    StudentDto updateStudent(StudentDto dto, Long id);
    StudentDto removeStudent(Long id);
    String removeAll();
    StudentDto getStudent(Long id);
}
