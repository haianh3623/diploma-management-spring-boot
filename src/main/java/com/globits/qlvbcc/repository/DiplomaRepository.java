package com.globits.qlvbcc.repository;

import com.globits.qlvbcc.domain.Diploma;
import com.globits.qlvbcc.domain.School;
import com.globits.qlvbcc.dto.DiplomaDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiplomaRepository extends JpaRepository<Diploma, Long> {

    @Query("select u from Diploma u where u.id = ?1")
    Diploma findDiplomaById(Long id);

    @Query("select new com.globits.qlvbcc.dto.DiplomaDto(cs) from Diploma cs")
    List<DiplomaDto> getAll();

    @Query("select new com.globits.qlvbcc.dto.DiplomaDto(cs) from Diploma cs where cs.id = ?1")
    DiplomaDto findDiplomaDtoById(Long id);

    @Query("select u from Diploma u where u.name = ?1 and u.code = ?2 ")
    Diploma findDiplomaByInfo(String name, String code);

    @Query("select count(u) from Diploma u where u.type = ?1")
    Long getNumOfDiplomaByType(String type);

    @Query("select count(u) from Diploma u where u.type = ?1 and u.schoolDip = ?2")
    Long getNumOfDiplomaByTypeAndSchoolId(String type, School schoolDip);

    @Query("select count(u) from Diploma u where u.schoolDip = ?1")
    Long getNumOfDiplomaBySchoolId(School schoolDip);

}
