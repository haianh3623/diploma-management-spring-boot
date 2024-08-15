package com.globits.qlvbcc.rest;

import com.globits.qlvbcc.domain.NumOfDiploma;
import com.globits.qlvbcc.domain.SchoolDiploma;
import com.globits.qlvbcc.dto.SchoolDto;
import com.globits.qlvbcc.service.SchoolService;
import jdk.jfr.BooleanFlag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/school")
public class RestSchoolController {

    @Autowired
    SchoolService schoolService;

    @GetMapping("/get-all")
    public List<SchoolDto> getAll(){
        return schoolService.getAll();
    }

    @PostMapping("/add")
    public SchoolDto addSchool(@RequestBody SchoolDto dto){
        return schoolService.addSchool(dto);
    }

    @PostMapping("/update/{id}")
    public SchoolDto updateSchool(@RequestBody SchoolDto dto, @PathVariable Long id){
        return schoolService.updateSchool(dto, id);
    }

    @DeleteMapping("/remove/{id}")
    public SchoolDto removeSchool(@PathVariable Long id){
        return schoolService.removeSchool(id);
    }

    @DeleteMapping("/remove-all")
    public String removeAll(){
        return schoolService.removeAll();
    }

    @GetMapping("/get/{id}")
    public SchoolDto getSchool(@PathVariable Long id){
        return schoolService.getSchool(id);
    }

    @GetMapping("/type-of-diploma/{id}")
    public List<NumOfDiploma> typeOfDiploma(@PathVariable Long id){
        return schoolService.typeOfDiploma(id);
    }

    @GetMapping("/max-num-of-diploma")
    public SchoolDiploma maxNumOfDiploma(){
        return schoolService.maxNumOfDiploma();
    }

    @GetMapping("/list-diploma")
    public List<SchoolDiploma> diplomaAwared(){
        return schoolService.diplomaAwared();
    }

}
