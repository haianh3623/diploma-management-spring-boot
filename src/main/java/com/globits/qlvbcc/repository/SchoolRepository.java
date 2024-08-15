package com.globits.qlvbcc.repository;

import com.globits.qlvbcc.domain.School;
import com.globits.qlvbcc.dto.SchoolDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {

    @Query("select new com.globits.qlvbcc.dto.SchoolDto(cs) from School cs")
    List<SchoolDto> getAll();

    @Query("select u from School u where u.id = ?1")
    School findSchoolById(Long id);

    @Query("select new com.globits.qlvbcc.dto.SchoolDto(cs) from School cs where cs.id = ?1")
    SchoolDto findSchoolDtoById(Long id);

    @Query("select u from School u where u.name = ?1 and u.code = ?2")
    School findSchoolByInfo(String name, String code);

}
