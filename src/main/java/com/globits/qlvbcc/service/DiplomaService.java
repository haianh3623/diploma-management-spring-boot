package com.globits.qlvbcc.service;

import com.globits.qlvbcc.domain.NumOfDiploma;
import com.globits.qlvbcc.dto.DiplomaDto;
import com.globits.qlvbcc.dto.SchoolDto;
import com.globits.qlvbcc.dto.StudentDto;
import org.antlr.v4.runtime.misc.Pair;

import java.util.List;

public interface DiplomaService {

    List<DiplomaDto> getAll();
    DiplomaDto addDiploma(DiplomaDto dto);
    DiplomaDto updateDiploma(DiplomaDto dto, Long id);
    DiplomaDto removeDiploma(Long id);
    String removeAll();
    DiplomaDto getDiploma(Long id);
    String updateStudent(Long id, StudentDto dto);
    String updateSchool(Long id, SchoolDto dto);

    Long numOfStudentGotDiploma();
    NumOfDiploma maxNumOfDiploma();
    List<NumOfDiploma> numOfDiplomas();

    String clearInfo(Long id);

}
