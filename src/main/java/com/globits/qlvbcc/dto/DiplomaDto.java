package com.globits.qlvbcc.dto;

import com.globits.qlvbcc.domain.Diploma;
import lombok.Data;

@Data
public class DiplomaDto {

    private Long id;
    private String name;
    private String code;
    private String type;
    private SchoolDto school;
    private StudentDto student;

    public DiplomaDto(){

    }

    public DiplomaDto(Diploma entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.code = entity.getCode();
        this.type = entity.getType();
        this.school = new SchoolDto(entity.getSchoolDip(), true);
        this.student = new StudentDto(entity.getStudent(), true);
    }

}
