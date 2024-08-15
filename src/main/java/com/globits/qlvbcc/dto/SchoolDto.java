package com.globits.qlvbcc.dto;

import com.globits.qlvbcc.domain.Diploma;
import com.globits.qlvbcc.domain.School;
import com.globits.qlvbcc.domain.Student;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class SchoolDto {

    private Long id;
    private String name;
    private String code;
    private List<StudentDto> students;
    private List<DiplomaDto> diplomas;

    public SchoolDto(){

    }

    public SchoolDto(School entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.code = entity.getCode();
        this.diplomas = new ArrayList<>();
        if(entity.getDiplomas() != null){
            List<Diploma> list = entity.getDiplomas();
            for(Diploma item : list){
                DiplomaDto dto = new DiplomaDto();
                dto.setId(item.getId());
                dto.setName(item.getName());
                dto.setCode(item.getCode());
                dto.setType(item.getType());
                this.diplomas.add(dto);
            }
        }
        this.students = new ArrayList<>();
        if(entity.getStudents() != null){
            List<Student> list = entity.getStudents();
            for(Student item : list){
                StudentDto dto = new StudentDto();
                dto.setId(item.getId());
                dto.setName(item.getName());
                dto.setCode(item.getCode());
                this.students.add(dto);
            }
        }
    }

    public SchoolDto(School entity, boolean simple){
        this.id = entity.getId();
        this.name = entity.getName();
        this.code = entity.getCode();
    }

}
