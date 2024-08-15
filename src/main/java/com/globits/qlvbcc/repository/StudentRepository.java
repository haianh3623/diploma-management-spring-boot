package com.globits.qlvbcc.repository;

import com.globits.qlvbcc.domain.Student;
import com.globits.qlvbcc.dto.StudentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("select u from Student u where u.id = ?1")
    Student findStudentById(Long id);

    @Query("select new com.globits.qlvbcc.dto.StudentDto(cs) from Student cs")
    List<StudentDto> getAll();

    @Query("select new com.globits.qlvbcc.dto.StudentDto(cs) from Student cs where cs.id = ?1")
    StudentDto findStudentDtoById(Long id);

    @Query("select u from Student u where u.name = ?1 and u.code = ?2")
    Student findStudentByInfo(String name, String code);

}
