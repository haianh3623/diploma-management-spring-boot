package com.globits.qlvbcc.service;

import com.globits.qlvbcc.domain.NumOfDiploma;
import com.globits.qlvbcc.domain.SchoolDiploma;
import com.globits.qlvbcc.dto.DiplomaDto;
import com.globits.qlvbcc.dto.SchoolDto;

import java.util.List;

public interface SchoolService {

    List<SchoolDto> getAll();
    SchoolDto addSchool(SchoolDto dto);
    SchoolDto updateSchool(SchoolDto dto, Long id);
    SchoolDto removeSchool(Long id);
    String removeAll();
    SchoolDto getSchool(Long id);

    List<NumOfDiploma> typeOfDiploma(Long id);
    SchoolDiploma maxNumOfDiploma();
    List<SchoolDiploma> diplomaAwared();

    DiplomaDto addDiploma(DiplomaDto dto);

}
