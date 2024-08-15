package com.globits.qlvbcc.dto;

import com.globits.qlvbcc.domain.Diploma;
import com.globits.qlvbcc.domain.Student;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class StudentDto {

    private Long id;
    private String name;
    private String code;
    private SchoolDto school;
    private Set<DiplomaDto> diplomas;

    public StudentDto(){

    }

    public StudentDto(Student entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.code = entity.getCode();
        this.school = new SchoolDto(entity.getSchoolStu(), true);
        this.diplomas = new HashSet<DiplomaDto>();
        if(entity.getDiplomas() != null && !entity.getDiplomas().isEmpty()){
            Set<Diploma> list = entity.getDiplomas();
            for(Diploma item : list){
                DiplomaDto dto = new DiplomaDto();
                dto.setId(item.getId());
                dto.setName(item.getName());
                dto.setCode(item.getCode());
                dto.setType(item.getType());
                this.diplomas.add(dto);
            }
        }
    }

    public StudentDto(Student entity, boolean simple){
        this.id = entity.getId();
        this.name = entity.getName();
        this.code = entity.getCode();
    }

}
